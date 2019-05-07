package com.hpedu.web.core.shiro;

import com.baomidou.mybatisplus.extension.api.R;
import com.hpedu.util.ResultBean;
import com.hpedu.web.core.shiro.service.SysMenuEntityService;
import com.hpedu.web.core.shiro.service.SysUserEntityService;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShiroController {
    
    @Autowired
    SysMenuEntityService menuService ;
    @Autowired
    SysUserEntityService sysUserService ;
    @GetMapping("sys/menu/nav")
    public ResultBean getNaVMenu () {
        return ResultBean.ok( menuService.getMenuList(ShiroUtils.getUserId()) ) ;
    }
    
    
    @GetMapping("sys/user/info")
    public ResultBean getuser(){
        return  ResultBean.ok(ShiroUtils.getUserEntity()) ;
    }
    
    
    /**
     * 修改登录用户密码
     */
//    @SysLog("修改密码")
    @PostMapping("sys/user/password")
    public ResultBean password(String password, String newPassword){
        Assert.hasText(newPassword, "新密码不为能空");

        //原密码
        password = ShiroUtils.sha256(password, ShiroUtils.getUserEntity().getSalt());
        //新密码
        newPassword = ShiroUtils.sha256(newPassword, ShiroUtils.getUserEntity().getSalt());

        //更新密码
        boolean flag = sysUserService.updatePassword(ShiroUtils.getUserId(), password, newPassword);
        if(!flag){
            return ResultBean.failed("原密码不正确");
        }

        return ResultBean.ok();
    }
}
