package com.hrm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrm.mapper.SysMenuMapper;
import com.hrm.model.SysMenu;
import com.hrm.model.SysUser;
import com.hrm.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author guchun
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
* @createDate 2022-05-16 15:07:24
*/
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
    implements SysMenuService {

    @Override
    public List<SysMenu> getUserSysMenuList(SysUser sysUser) {

        Integer userRole = sysUser.getUserRole();

        return null;
    }
}




