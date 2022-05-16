package com.hrm.service;

import com.hrm.model.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hrm.model.SysUser;

import java.util.List;

/**
* @author guchun
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
* @createDate 2022-05-16 15:07:24
*/
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> getUserSysMenuList(SysUser sysUser);

}
