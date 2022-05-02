package com.hrm.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hrm.common.ResCodeEnum;
import com.hrm.exception.BusinessException;
import com.hrm.model.SysUser;
import com.hrm.model.domain.bo.LoginUser;
import com.hrm.utils.RedisCache;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.hrm.contant.UserContant.JWT_SECRET;
import static com.hrm.contant.UserContant.REDIS_TOKEN_SAVE_KEY;

/**
 * @author guchun
 * @description TODO
 * @date 2022/5/1 19:34
 */
@Component
@Slf4j
public class TokenService {

    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    // 令牌有效期（默认30分钟）
    @Value("${token.expireTime}")
    private int expireTime;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    @Resource
    private RedisCache redisCache;

    /**
     * 创建token
     * @param loginUser
     * @return
     */
    public String createToken(LoginUser loginUser) {
        String uuid = UUID.randomUUID().toString();
        loginUser.setUuid(uuid);

        String tokenString = getTokenString(loginUser);
        loginUser.setToken(tokenString);

        refreshToken(loginUser);
        return tokenString;
    }

    public boolean delToken(String token) {
        LoginUser loginUser = verifyToken(token);
        return delTokenCache(loginUser.getUuid());
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    public LoginUser verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0") //匹配指定的token发布者 auth0
                    .build();
            jwt = verifier.verify(token); //解码JWT ，verifier 可复用
        }catch (JWTVerificationException e){
            //无效的签名/声明
            log.warn("无效token" + e.getMessage());
            throw new BusinessException(ResCodeEnum.PARAMS_ERROR, "无效的token");
        }
        String userAccount = jwt.getClaim("userAccount").asString();
        String uuid = jwt.getClaim("UUID").asString();
        LoginUser loginUser = getTokenCache(uuid);
        if (StringUtils.hasText(userAccount) && userAccount.equals(loginUser.getUser().getUserAccount())) {
            return loginUser;
        } else {
            throw new BusinessException(ResCodeEnum.PARAMS_ERROR, "验证失败");
        }
    }

    private String getTokenString(LoginUser loginUser) {
        String token = "";
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withIssuedAt(new Date())
                    .withClaim("userAccount", loginUser.getUser().getUserAccount())
                    .withClaim("UUID", loginUser.getUuid())
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            log.warn(e.getMessage());
        }
        return token;
    }

    private void refreshToken(LoginUser loginUser) {
        String userKey = getTokenKey(loginUser.getUuid());
        redisCache.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
    }

    private String getTokenKey(String uuid) {
        return REDIS_TOKEN_SAVE_KEY + uuid;
    }

    private LoginUser getTokenCache(String uuid) {
        String userKey = getTokenKey(uuid);
        Object cacheObject = redisCache.getCacheObject(userKey);
        if (cacheObject == null) {
            throw new BusinessException(ResCodeEnum.NOT_LOGIN, "token过期");
        }
        return (LoginUser) cacheObject;
    }

    private boolean delTokenCache(String uuid) {
        String userKey = getTokenKey(uuid);
        return redisCache.deleteObject(userKey);
    }
}
