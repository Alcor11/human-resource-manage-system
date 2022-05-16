package com.hrm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrm.mapper.SysRoleMapper;
import com.hrm.model.SysRole;
import com.hrm.model.domain.dto.SysRoleDTO;
import com.hrm.model.domain.mapstruct.SysRoleMap;
import com.hrm.service.SysRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author guchun
* @description 针对表【sys_role(角色权限)】的数据库操作Service实现
* @createDate 2022-05-16 15:17:55
*/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements SysRoleService {

    @Resource
    SysRoleMap sysRoleMap;

    @Override
    public List<SysRole> queryRoleListByUserRoleId(Integer userRoleId) {

        return null;
    }

    @Override
    public boolean addRole(SysRoleDTO sysRoleDTO) {
        SysRole sysRole = sysRoleMap.dto2vo(sysRoleDTO);
        return this.save(sysRole);
    }


    private SysRole queryRoleByRoleId(int id) {
        return this.queryRoleByRoleId(id);
    }

    private boolean updateRole(SysRole sysRole) {
        return this.updateById(sysRole);
    }

    private int delRole(int id) {

        return this.delRole(id);

    }

}




