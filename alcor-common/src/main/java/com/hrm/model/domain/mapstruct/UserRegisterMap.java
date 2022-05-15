package com.hrm.model.domain.mapstruct;

import com.hrm.model.SysUser;
import com.hrm.model.domain.dto.UserRegisterDTO;
import com.hrm.model.domain.vo.UserRegisterRequest;
import org.mapstruct.Mapper;

/**
 * @author guchun
 * @description TODO
 * @date 2022/4/29 17:39
 */
@Mapper(componentModel = "spring")
public interface UserRegisterMap {

    UserRegisterDTO voToDto(UserRegisterRequest userRegisterRequest);

    SysUser dtoToDo(UserRegisterDTO userRegisterDTO);
}
