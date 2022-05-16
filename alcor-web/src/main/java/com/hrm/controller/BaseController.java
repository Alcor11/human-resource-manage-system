package com.hrm.controller;

import com.hrm.exception.BusinessException;
import com.hrm.common.exception.ResCodeEnum;
import com.hrm.model.domain.bo.LoginUser;
import com.hrm.service.impl.TokenService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guchun
 * @description TODO
 * @date 2022/4/29 14:57
 */
@RestController
public class BaseController {

    @Resource
    TokenService tokenService;

    protected LoginUser getLoginUser(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        if (!StringUtils.hasText(token)) {
            throw new BusinessException(ResCodeEnum.PARAMS_ERROR, "token不存在");
        }
        return tokenService.verifyToken(token);
    }

    protected boolean offLineUser(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        return tokenService.delToken(token);
    }

}
