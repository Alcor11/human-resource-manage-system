package com.hrm.model.domain.mapstruct;

import com.hrm.model.SysRole;
import com.hrm.model.domain.dto.SysRoleDTO;
import org.mapstruct.Mapper;

/**
 * @author guchun
 * @description TODO
 * @date 2022/5/16 15:44
 */
@Mapper(componentModel = "spring")
public interface SysRoleMap {

    SysRole dto2vo(SysRoleDTO sysRoleDTO);

}
