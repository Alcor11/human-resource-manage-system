package com.hrm.service;

import com.hrm.model.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hrm.model.domain.dto.UserRegisterDTO;
import com.hrm.model.domain.vo.UserDataVO;
import com.hrm.model.domain.vo.UserLoginRequest;

/**
* @author guchun
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2022-04-28 21:32:49
*/
public interface SysUserService extends IService<SysUser> {

    long userRegister(UserRegisterDTO userRegister);

    UserDataVO userLogin(UserLoginRequest loginRequest);

}
