package com.hpedu.web.core.back;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.hpedu.util.ResultBean;
import com.hpedu.util.codeUtil.BaseUtil;
import com.hpedu.web.core.shiro.ShiroUtils;
import com.hpedu.web.core.shiro.pojo.SysMenuEntity;
import com.hpedu.web.core.shiro.pojo.SysUserEntity;
import com.hpedu.web.core.shiro.service.SysMenuEntityService;
import com.hpedu.web.core.shiro.service.SysUserEntityService;
import com.hpedu.web.core.user.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@Controller
public class BackUserController {
    @Autowired
    SysUserEntityService sysUserEntityService;
    @Autowired
    SysMenuEntityService menuService ;
//    @Resource
//    TeacherService teacherService;
    @Autowired
    private UserService userService;
    private Logger log = BaseUtil.getLogger(BackUserController.class);
    @Autowired
    private Producer producer;

    @GetMapping("/back/backindex.html")
    public String toBackIndex(HttpServletRequest req, HttpSession session, Model model) {
        SysUserEntity user = ShiroUtils.getUserEntity();
        if (user != null) {
            model.addAttribute("backuser", user);
            int visitorNum = userService.getVisitorNum();
            model.addAttribute("visitorNum", visitorNum);
            
            List<SysMenuEntity> list = menuService.getMenuList(user.getUserId() ) ;
            model.addAttribute("menuList", list) ;
            
            return null;
        }
        return "/back/backlogin";

    }

    @GetMapping("/back/backlogin.html")
    public void toBackUserLogin(HttpServletRequest req, HttpSession session, Model model) {


    }

   

    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    /**
     * 登录
     */
    @ResponseBody
    @RequestMapping(value = {"/sys/login", "back/backUserLogin"}, method = RequestMethod.POST)
    public ResultBean login(String userName, String passWord, String captcha) {
        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!captcha.equalsIgnoreCase(kaptcha)) {
            return ResultBean.failed("验证码不正确");
        }

        try {
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return ResultBean.failed(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return ResultBean.failed("账号或密码不正确");
        } catch (LockedAccountException e) {
            return ResultBean.failed("账号已被锁定,请联系管理员");
        } catch (AuthenticationException e) {
            return ResultBean.failed("账户验证失败");
        }

        return ResultBean.ok();
    }

    /**
     * 退出
     */
    @RequestMapping(value = "back/logout", method = RequestMethod.GET)
    public String logout() {
        ShiroUtils.logout();
        return "/back/backlogin";
    }

}