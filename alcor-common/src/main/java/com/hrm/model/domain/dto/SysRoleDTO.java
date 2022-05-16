package com.hrm.model.domain.dto;

import lombok.Data;

/**
 * @author guchun
 * @description 直接添加某个权限名
 * @date 2022/5/16 15:30
 */

@Data
public class SysRoleDTO {

    /**
     * 权限key
     */
    private Integer roleKey;

    /**
     * 权限名
     */
    private String roleName;

}
