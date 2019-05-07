package com.hpedu.web.core.shiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hpedu.web.core.shiro.pojo.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserEntityDao extends BaseMapper<SysUserEntity> {


    List<String> queryAllPerms(Long userId);
}
