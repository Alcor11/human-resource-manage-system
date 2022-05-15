package com.hrm.aspectlj;

import com.hrm.exception.BusinessException;
import com.hrm.common.ResCodeEnum;
import com.hrm.service.impl.TokenService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author guchun
 * @description Token验证切面
 * @date 2022/5/1 21:24
 */
@Aspect
@Component
@Slf4j
public class TokenAspect {

    @Resource
    TokenService tokenService;

    @Pointcut("@annotation(com.hrm.aspectlj.config.TokenVerify)")
    public void TokenAspect() {}

    @Around("TokenAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            throw new BusinessException(ResCodeEnum.NULL_ERROR, "token不能为空");
        }
        tokenService.verifyToken(token);
        return joinPoint.proceed();
    }


}
