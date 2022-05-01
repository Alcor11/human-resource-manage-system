package com.hrm.model.domain.vo;

import lombok.Data;

/**
 * @author guchun
 * @description 用户登录请求
 * @date 2022/4/29 19:16
 */
@Data
public class UserLoginRequest {

    private String userAccount;

    private String password;

}
