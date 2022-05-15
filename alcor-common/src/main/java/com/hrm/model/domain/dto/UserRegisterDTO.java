package com.hrm.model.domain.dto;

import lombok.Data;

/**
 * @author guchun
 * @description TODO
 * @date 2022/4/29 17:20
 */
@Data
public class UserRegisterDTO {

    private String userAccount;

    private String userPassword;

    private Integer gender;

    private String userName;

    private String userPhone;

}
