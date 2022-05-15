package com.hrm.controller.sys;

import com.hrm.aspectlj.config.TokenVerify;
import com.hrm.exception.BusinessException;
import com.hrm.common.BaseResponse;
import com.hrm.common.ResCodeEnum;
import com.hrm.common.ResultUtils;
import com.hrm.controller.BaseController;
import com.hrm.model.domain.bo.LoginUser;
import com.hrm.model.domain.dto.UserRegisterDTO;
import com.hrm.model.domain.mapstruct.UserRegisterMap;
import com.hrm.model.domain.vo.UserDataVO;
import com.hrm.model.domain.vo.UserLoginRequest;
import com.hrm.model.domain.vo.UserRegisterRequest;
import com.hrm.service.SysUserService;
import com.hrm.service.impl.TokenService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author guchun
 * @description TODO
 * @date 2022/4/29 14:59
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseController {

    @Resource
    UserRegisterMap map;

    @Resource
    SysUserService sysUserService;

    @Resource
    TokenService tokenService;

    @PostMapping("/register")
    public BaseResponse<Long> register(@RequestBody UserRegisterRequest registerRequest) {

        if (!StringUtils.hasText(registerRequest.getUserAccount()) || !StringUtils.hasText(registerRequest.getUserPassword())) {
            throw new BusinessException(ResCodeEnum.NULL_ERROR);
        }
        if (registerRequest.isValid()) {
            throw new BusinessException(ResCodeEnum.PARAMS_ERROR);
        }

        UserRegisterDTO userRegisterDTO = map.voToDto(registerRequest);
        long result = sysUserService.userRegister(userRegisterDTO);
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<UserDataVO> login(@RequestBody UserLoginRequest loginRequest) {
        if (!StringUtils.hasText(loginRequest.getUserAccount()) || !StringUtils.hasText(loginRequest.getPassword())) {
            throw new BusinessException(ResCodeEnum.NULL_ERROR);
        }
        UserDataVO userDataVO = sysUserService.userLogin(loginRequest);
        System.out.println(userDataVO);
        return ResultUtils.success(userDataVO);

    }

    @GetMapping("/token")
    @TokenVerify
    public BaseResponse checkToken(@RequestParam(value = "token") String token) {
        return ResultUtils.success("success");
    }

    @GetMapping("/current")
    public BaseResponse currentUserInfo(HttpServletRequest httpServletRequest) {
        LoginUser loginUser = super.getLoginUser(httpServletRequest);
        return ResultUtils.success(loginUser);
    }

    @GetMapping("/offline")
    public BaseResponse offLineAccount(HttpServletRequest httpServletRequest) {
        boolean b = super.offLineUser(httpServletRequest);
        return b ? ResultUtils.success("下线成功") : ResultUtils.success("下线失败或已下线");
    }

}
