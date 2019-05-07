package com.hpedu.web.core.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hpedu.util.Constant;
import com.hpedu.web.core.shiro.dao.SysMenuDao;
import com.hpedu.web.core.shiro.pojo.SysMenuEntity;
import com.hpedu.web.core.shiro.service.SysMenuEntityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenuEntityServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuEntityService {

    /*获取用户对应的菜单*/
    @Override
    public List<SysMenuEntity> getMenuList(Long userId) {
        if(userId == Constant.SUPER_ADMIN){
            userId = null ;
        }
        return handleMenu( baseMapper.getMenuList(userId) );
    }

    private List<SysMenuEntity>  handleMenu(List<SysMenuEntity> menuList) {
        /*整理父菜单*/
        List<SysMenuEntity> parentMenu = new ArrayList<>();
        if(menuList!=null) {
            for (SysMenuEntity entity : menuList) {
                if (entity.getParentId() == Constant.MenuType.CATALOG.getValue()) {
                    parentMenu.add(entity);
                }
            }

            /*整理子菜单*/
            for (SysMenuEntity parent :
                    parentMenu) {
                List<SysMenuEntity> childList = new ArrayList<>();
                for (SysMenuEntity child :
                        menuList) {
                    if (parent.getMenuId() == child.getParentId()) {
                        childList.add(child);
                    }
                }
                parent.setList(childList);
            }
        }
       return parentMenu ;  
    }


}
