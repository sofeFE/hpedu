package com.hpedu.web.core.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hpedu.web.core.shiro.dao.SysUserEntityDao;
import com.hpedu.web.core.shiro.pojo.SysUserEntity;
import com.hpedu.web.core.shiro.service.SysUserEntityService;
import org.springframework.stereotype.Service;

@Service
public class SysUserEntityServiceImpl extends ServiceImpl<SysUserEntityDao, SysUserEntity> implements SysUserEntityService {



    @Override
    public boolean updatePassword(long userId, String password, String newPassword) {
        SysUserEntity userEntity = new SysUserEntity();
        userEntity.setPassword(newPassword);
        return this.update(userEntity,
                new QueryWrapper<SysUserEntity>().eq("user_id", userId).eq("password", password));
    }
}
