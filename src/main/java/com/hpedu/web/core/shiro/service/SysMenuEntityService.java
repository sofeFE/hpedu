package com.hpedu.web.core.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hpedu.web.core.shiro.pojo.SysMenuEntity;
import com.hpedu.web.core.shiro.pojo.SysUserEntity;

import java.util.List;

public interface SysMenuEntityService extends IService<SysMenuEntity> {


    List<SysMenuEntity> getMenuList(Long userId);
}
