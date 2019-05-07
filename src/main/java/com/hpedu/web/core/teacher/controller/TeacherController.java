package com.hpedu.web.core.teacher.controller;

import com.hpedu.util.ResultBean;
import com.hpedu.util.codeUtil.BaseUtil;
import com.hpedu.util.codeUtil.UUIDUtil;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.shiro.ShiroUtils;
import com.hpedu.web.core.shiro.pojo.SysUserEntity;
import com.hpedu.web.core.teacher.pojo.Teacher;
import com.hpedu.web.core.teacher.service.TeacherService;
import com.hpedu.web.core.user.pojo.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @Value("${uploadAbsolutePath}")
    private String uploadAbsolutePath;
    private Logger log = BaseUtil.getLogger(TeacherController.class);


    /**
     * 上移、下移
     */
    @RequestMapping("/back-authc/changeTeacherSort")
    @ResponseBody
    public ResultBean<String> updateBannerSort(HttpServletRequest req, HttpServletResponse response,
                                               String id1, long sort1, String id2, long sort2) {
        return teacherService.updateTeacherSort(id1, sort1, id2, sort2);
    }


    /**
     * @param req
     * @param session
     * @param id
     * @param mode
     */
    @RequestMapping("/back/teacherUp.html")
    public void toUpTeacher(HttpServletRequest req, HttpSession session, String id, Model mode) {
        Teacher teacher = teacherService.findTeacherById(id);
        if (teacher != null) {
            mode.addAttribute("teacher", teacher);
        }
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
    }

    /**
     * 修改教师信息
     */
    @RequestMapping(value = "/back-authc/teacherUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("back-authc/teacherUpdate")
    public ResultBean<?> teacherUpdate( @RequestParam(value = "timgUrl1", required = false) MultipartFile file,
                                       @RequestParam(value = "file1", required = false) MultipartFile file1,
                                       Teacher teacher) throws Exception {

//        SysUserEntity user = ShiroUtils.getUserEntity();
//        if (user != null) {
            return teacherService.updateTeacher(teacher, file, file1);
//        }
//        return ResultBean.failed("更新失败");
    }

    /***
     * 删除教师信息
     * */
    @RequestMapping("/back-authc/deleteTeacher")
    public ModelAndView deleteTeacher(HttpServletRequest req, String id, HttpSession session) {
        User user = (User) session.getAttribute("backuser");
        if (user != null) {
            try {
                teacherService.deleteTeacherById(id);
            } catch (Exception e) {
                log.error("删除教师【id:" + id + "】失败：", e);
            }
            return new ModelAndView("redirect:/back/teacher.html");
        }
        return new ModelAndView("redirect:/back/backlogin.html");
    }

    /**
     * 新增教师
     */
    @RequestMapping("/back/addTeacher.html")
    public void toAddTeacher(HttpServletRequest req, HttpSession session, Model mode) {
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
    }

    @RequestMapping(value = "/back-authc/addTeacher", method = RequestMethod.POST)
    @RequiresPermissions("/back-authc/addTeacher")
    @ResponseBody
    public ModelAndView addTeacher(HttpServletRequest req, HttpSession session,
                                   @RequestParam(value = "timgUrl1", required = false) MultipartFile file,
                                   @RequestParam(value = "file1", required = false) MultipartFile file1,
                                   Teacher teacher) {
        User user = (User) session.getAttribute("backuser");
        if (user != null) {
            try {
                String realPath = uploadAbsolutePath;
                String path = req.getContextPath().substring(1);
//			 realPath = realPath.replace(path, "teacherImg");
                realPath = uploadAbsolutePath + "/" + "teacherImg";
                if (file.getSize() > 0) {
                    String fileName = UUIDUtil.getUUID();
                    String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                    file.transferTo(new File(realPath, fileName + "." + suffix));
                    teacher.setTimgUrl("/teacherImg/" + fileName + "." + suffix);
                }
                if (file1.getSize() > 0) {
                    String fileName = UUIDUtil.getUUID();
                    String suffix = file1.getOriginalFilename().substring(file1.getOriginalFilename().lastIndexOf(".") + 1);
                    file.transferTo(new File(realPath, fileName + "." + suffix));
                    teacher.setBackImg("/teacherImg/" + fileName + "." + suffix);
                }
                teacher.setSort(new Date().getTime());
                teacher.setTid(UUIDUtil.getUUID());
                teacherService.addTeacher(teacher);

                return new ModelAndView("redirect:/back/teacher.html");
            } catch (Exception e) {
                session.setAttribute("error", "出错了：" + e.getMessage());
                log.error("新增教师出错了:", e);
                return new ModelAndView("redirect:/back/addTeacher.html");
            }
        }
        return new ModelAndView("redirect:/back/backlogin.html");
    }

    /**
     * 所有教师
     */
    @RequestMapping("/back/teacher.html")
    public void toTeacher(HttpServletRequest req, HttpSession session, Model model) {
        List<Teacher> teacher = null;
        try {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("isShow", 1);
            teacher = teacherService.findAllTeacher(param);
        } catch (Exception e) {
            teacher = new ArrayList<Teacher>();
            log.error("查询所有教师出错了：", e);

        }
        model.addAttribute("teacher", teacher);
    }
    //分页查看所有可显示的教师信息
    @RequestMapping("/teacher/teacherList.html")
    public ModelAndView teacherList(HttpServletRequest req, HttpServletResponse response) {
        ModelAndView mode = new ModelAndView();
        Map<String, Object> map = new HashMap<>();
        map.put("isShow", "1");
        com.github.pagehelper.PageInfo<Teacher> pages = null;
        Page<Teacher> page = new Page<>();
        int pageNo = 0;
        int pageSize = 4;
        if (StringUtils.isEmpty(req.getParameter("pageNo"))
                && StringUtil.isNumeric(req.getParameter("pageNo"))) {
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        if (pageNo < 1) {
            pageNo = 1;
        }

//            pages = teacherService.searchTeacherList2(map, pageNo, pageSize);
        List<Teacher> list = teacherService.searchTeacherList3(map, pageNo, pageSize);

        page.setResult(list);
        mode.addObject("pages", page);
        mode.setViewName("order/teacherList");
        return mode;

    }

    //查看教师信息
    @RequestMapping("/teacher/checkTeacher.html")
    public ModelAndView checkTeacher(HttpServletRequest req, HttpServletResponse response, String id) {
        ModelAndView mode = new ModelAndView();
        Teacher teacher = null;
        try {
            teacher = teacherService.findTeacherById(id);
        } catch (Exception e) {
            teacher = new Teacher();
            log.error("查询学员【id:" + id + "】异常:", e);
        }
        mode.setViewName("order/checkTeacher");
        mode.addObject("teacher", teacher);
        return mode;
    }
}
