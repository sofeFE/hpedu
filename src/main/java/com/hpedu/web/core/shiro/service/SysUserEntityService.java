package com.hpedu.web.core.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hpedu.web.core.shiro.dao.SysUserEntityDao;
import com.hpedu.web.core.shiro.pojo.SysUserEntity;

public interface SysUserEntityService extends IService<SysUserEntity> {


    boolean updatePassword(long userId, String password, String newPassword);
}
