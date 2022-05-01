package com.hrm.model.domain.mapstruct;

import com.hrm.model.SysUser;
import com.hrm.model.domain.vo.UserDataVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author guchun
 * @description TODO
 * @date 2022/4/29 20:17
 */
@Mapper(componentModel = "spring")
public interface UserLoginMap {

    UserDataVO doToVo(SysUser sysUser);

}
