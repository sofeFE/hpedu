package com.hpedu.web.core.index.controller;

import com.hpedu.annotation.SysLog;
import com.hpedu.util.codeUtil.BaseUtil;
import com.hpedu.web.core.order.pojo.Banner;
import com.hpedu.web.core.order.service.OrderService;
import com.hpedu.web.core.teacher.pojo.Teacher;
import com.hpedu.web.core.teacher.service.TeacherService;
import com.hpedu.web.core.trophy.pojo.Trophy;
import com.hpedu.web.core.trophy.service.TrophyService;
import com.hpedu.web.core.user.pojo.User;
import com.hpedu.web.core.video.pojo.ContestVideo;
import com.hpedu.web.core.video.pojo.GeneralVideo;
import com.hpedu.web.core.video.service.ContestVideoService;
import com.hpedu.web.core.video.service.GeneralVideoService;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Resource
    TeacherService teacherService;
    @Resource
    TrophyService trophyService;
    @Resource
    ContestVideoService contestVideoService;
    @Resource
    GeneralVideoService generalVideoService;
    @Resource
    OrderService orderService;
    private Logger log = BaseUtil.getLogger(IndexController.class);

    @GetMapping(value={"/classindex.html","/index"})
    @SysLog("访问主页")
    public String welcome( Model model, HttpSession session) throws Throwable {
        
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }
        fillModel(model);
        return "classindex";
    }

    public void fillModel(Model model) {
        List<Trophy> trolist = null;//奖杯-学员展示
        List<ContestVideo> conlist = null;//竞赛课展示
        List<GeneralVideo> genlist = null;//常规课展示
        List<Banner> blist = null;//轮播图 展示
        List<Teacher> tlist = null;//教师展示
        StringBuffer error = new StringBuffer();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("isShow", 1);
        param.put("limit",7) ;

//        tlist = teacherService.findAllTeacher(param);// 教师
        tlist = teacherService.findTeacherByIsShow(param);// 教师
        trolist = trophyService.findAllTrophy(param);// 学员展示
        conlist = contestVideoService.findContestVideoIndex();// 竞赛
        genlist = generalVideoService.findGeneralVideo();// 常规课
        blist = orderService.selectAllWebBanner();// 轮播图片
        model.addAttribute("tlist", tlist);
        model.addAttribute("trolist", trolist);
        model.addAttribute("conlist", conlist);
        model.addAttribute("genlist", genlist);
        model.addAttribute("blist", blist);
    }
}
