package com.hrm.model.domain.vo;

import lombok.Data;

import static com.hrm.contant.UserContant.*;
import static com.hrm.contant.UserContant.USER_PASSWORD_MIN_SIZE;

/**
 * @author guchun
 * @description 用户注册请求body
 * @date 2022/4/29 17:14
 */
@Data
public class UserRegisterRequest {

    private String userAccount;

    private String userPassword;

    private Integer gender;

    private String userName;

    private String userPhone;

    public boolean isValid() {
        return this.getUserAccount().length() > USER_ACCOUNT_MAXSIZE ||
                this.getUserAccount().length() < USER_ACCOUNT_MIN_SIZE ||
                this.getUserPassword().length() > USER_PASSWORD_MAXSIZE ||
                this.getUserPassword().length() < USER_PASSWORD_MIN_SIZE;
    }

}
