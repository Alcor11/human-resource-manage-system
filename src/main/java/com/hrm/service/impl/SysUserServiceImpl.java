package com.hrm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrm.common.ResCodeEnum;
import com.hrm.exception.BusinessException;
import com.hrm.model.SysUser;
import com.hrm.model.domain.bo.LoginUser;
import com.hrm.model.domain.dto.UserRegisterDTO;
import com.hrm.model.domain.mapstruct.UserLoginMap;
import com.hrm.model.domain.mapstruct.UserRegisterMap;
import com.hrm.model.domain.vo.UserDataVO;
import com.hrm.model.domain.vo.UserLoginRequest;
import com.hrm.service.SysUserService;
import com.hrm.mapper.SysUserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import static com.hrm.contant.UserContant.SALT;
import static com.hrm.contant.UserContant.USER_ACCOUNT_MAXSIZE;

/**
* @author guchun
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2022-04-28 21:32:49
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

    @Resource
    private SysUserMapper userMapper;

    @Resource
    private UserLoginMap loginMap;
    @Resource
    private UserRegisterMap registerMap;

    @Resource
    private TokenService tokenService;

    @Override
    public long userRegister(UserRegisterDTO userRegister) {
        String userAccount = userRegister.getUserAccount();
        String userPassword = userRegister.getUserPassword();


        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_account", userAccount);
        long count = userMapper.selectCount(wrapper);

        if (count > 0) {
            throw new BusinessException(ResCodeEnum.PARAMS_ERROR, "账号重复");
        }

        userRegister.setUserPassword(getDigestPassword(userPassword));

        SysUser sysUser = registerMap.dtoToDo(userRegister);
        boolean isSave = this.save(sysUser);
        if (!isSave) {
            return -1;
        }
        return sysUser.getId();
    }

    @Override
    public UserDataVO userLogin(UserLoginRequest loginRequest) {

        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();

        wrapper.eq("user_account", loginRequest.getUserAccount());
        wrapper.eq("user_password", getDigestPassword(loginRequest.getPassword()));
        SysUser sysUser = userMapper.selectOne(wrapper);

        if (ObjectUtils.isEmpty(sysUser)) {
            throw new BusinessException(ResCodeEnum.PARAMS_ERROR, "账号名或者密码错误");
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(sysUser);

        UserDataVO userDataVO = loginMap.doToVo(sysUser);
        userDataVO.setToken(tokenService.createToken(loginUser));

        return userDataVO;
    }

    private String getDigestPassword(String userPassword) {

        return DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

    }
}




