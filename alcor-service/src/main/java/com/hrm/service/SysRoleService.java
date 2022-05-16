package com.hrm.service;

import com.hrm.model.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hrm.model.domain.dto.SysRoleDTO;

import java.util.List;

/**
* @author guchun
* @description 针对表【sys_role(角色权限)】的数据库操作Service
* @createDate 2022-05-16 15:17:55
*/
public interface SysRoleService extends IService<SysRole> {

    List<SysRole> queryRoleListByUserRoleId(Integer userRoleId);

    boolean addRole(SysRoleDTO sysRoleDTO);


}
