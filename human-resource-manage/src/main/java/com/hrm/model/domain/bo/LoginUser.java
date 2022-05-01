package com.hrm.model.domain.bo;

import com.hrm.model.SysUser;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author guchun
 * @description 用户登陆信息
 * @date 2022/5/1 20:27
 */
@Data
public class LoginUser implements Serializable {

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * redis验证uuid
     */
    private String uuid;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;


    /**
     * 用户信息
     */
    private SysUser user;


}
