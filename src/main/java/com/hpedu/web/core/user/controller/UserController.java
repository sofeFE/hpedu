package com.hpedu.web.core.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hpedu.util.CloudSMSUtil;
import com.hpedu.util.ResultBean;
import com.hpedu.util.codeUtil.*;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.shiro.ShiroUtils;
import com.hpedu.web.core.user.pojo.User;
import com.hpedu.web.core.user.pojo.UserLearn;
import com.hpedu.web.core.user.pojo.UserLevel;
import com.hpedu.web.core.user.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/")
public class UserController {
    @Resource
    UserService userService;
    @Value("${uploadAbsolutePath}")
    private String uploadAbsolutePath;
    private Logger log = BaseUtil.getLogger(UserController.class);

    /*public static void main(String[] args) {
//        String salt = RandomStringUtils.randomAlphanumeric(20);
        String salt = "Lr7yty2yq1L8C2oprtvb";
//        System.out.println("salt:   " + salt);

        System.out.println("password:   " + ShiroUtils.sha256("1", salt));

    }*/


    /**
     * 打开在线客服
     */
    @RequestMapping("/openKF.html")
    public void openKF(HttpServletRequest req, HttpSession session, Model model) {

    }

    /**
     * 修改学生学习时间
     */
    @RequestMapping("/user/changeStuLeanTime")
    public void changeStuLeanTime(HttpServletRequest req, HttpSession session, String vid, String vclassify, String time) {
        User u = (User) session.getAttribute("user");
        if (u != null) {
            //System.out.println(vid+"=="+vclassify+"=="+time+"=="+u.getUid());
            time = time == null ? "0" : time;
            Long realTime = Long.parseLong(time.substring(0, time.indexOf(".")));
            try {
                userService.updateLearnTotalTime(u.getUid(), realTime);
                u.setLearnTime(u.getLearnTime() + realTime);
                session.setAttribute("user", u);
            } catch (Exception e) {
                log.error("修改学生【uid:" + u.getUid() + "】总学习时间失败：", e);
            }
            //修改当天的学习记录
            try {
                String ulid = userService.selectIsExitUserLearn(u.getUid(), vid, vclassify);
                UserLearn ul = new UserLearn();
                if (ulid == null) {//新增学习记录
                    ul.setLearnTime(realTime);
                    ul.setUlid(UUIDUtil.getUUID());
                    ul.setUserid(u.getUid());
                    ul.setVctype(Integer.parseInt(vclassify));
                    ul.setVid(vid);
                    userService.insertLearnTimeByDay(ul);
                } else {//修改学习记录
                    ul.setLearnTime(realTime);
                    ul.setUlid(ulid);
                    userService.updateLearnTimeByDay(ul);
                }
            } catch (Exception e) {
                log.error("修改学生【uid:" + u.getUid() + "】当天学习时间失败：", e);
            }
        }
    }


    /**
     * 用户信息页面
     *
     * @param req
     * @param session
     * @param model
     * @
     */
    @RequestMapping("/userNews.html")
    public void findUserNews(HttpServletRequest req, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", userService.findUserByUid(user.getUid()));
        }
        //学生等级
        Integer leval = 0;
        int learnCount = 0;
        try {
            List<UserLevel> levelList = userService.selectAllLevel();
            if (user != null) {
                Long learnTime = user.getLearnTime();//单位是秒
                learnTime = learnTime == null ? 0l : learnTime;
                for (UserLevel u : levelList) {
                    Long minNum = u.getMinNum();//单位是小时
                    Long maxNum = u.getMaxNum();//单位是小时
                    if ((learnTime >= minNum * 3600 && maxNum == null) || (learnTime >= minNum * 3600 && maxNum != null && learnTime < maxNum * 3600)) {
                        leval = u.getLevel();
                        break;
                    }
                }
                //学习中的课程总计
                learnCount = userService.getLearnVideoTotalCount(user.getUid());
            }

        } catch (Exception e) {
            log.error("查询学生等级异常：", e);
        }

        model.addAttribute("leval", leval);
        model.addAttribute("learnCount", learnCount);

        //学习总年份
        String uid = user == null ? "-1" : user.getUid();
        List<String> yearList = userService.getYearByUserId(uid);

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        if (yearList.size() == 0) {
            yearList.add(String.valueOf(year));
        }
        model.addAttribute("yearList", yearList);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
    }


    /**
     * /学习时间表查询
     *
     * @param req
     * @param session
     * @param year
     * @param month
     * @return
     * @
     */
    @RequestMapping("/getUserlearnList")
    @ResponseBody
    public List<Map> getUserlearnList(HttpServletRequest req, HttpSession session, int year, int month) {
        String monthStr = month < 10 ? "0" + month : "" + month;
        User user = (User) session.getAttribute("user");
        String userId = user == null ? "-1" : user.getUid();

        List<Map> learnList = userService.getLearnTimeByUserId(userId, year + "-" + monthStr);//学习时长

        //System.out.println(year+"-"+monthStr+"--"+learnList.size());
        return learnList;
    }

    /**
     * /学习中的课程分页查询
     *
     * @param req
     * @param session
     * @param model
     * @
     */
    @RequestMapping("/learnVideoPage.html")
    public void learnVideoPage(HttpServletRequest req, HttpSession session, Model model) {
        int pageNo = 0;
        int pageSize = 8;
        if (req.getParameter("pageNo") != null
                && StringUtil.isNumeric(req.getParameter("pageNo"))) {
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        if (pageNo < 1) {
            pageNo = 1;
        }

        List<Map> list = null;
        int totalCount = 0;
        String userId = "";
        User user = (User) session.getAttribute("user");
        if (user != null) {
            userId = user.getUid();
        }

        try {
            list = userService.findlearnListByPage(userId, pageNo, pageSize);
            totalCount = userService.getLearnVideoTotalCount(userId);
        } catch (Exception e) {
            log.info("分页查看用户学习中课程出错:", e);
            list = new ArrayList<Map>();
        }
        //System.out.println("size:=========================="+list.size());
        Page pages = new Page();
        pages.setResult(list);
        pages.setPageNo(pageNo);
        pages.setPageSize(pageSize);
        pages.setTotalCount(totalCount);
        model.addAttribute("pages", pages);
    }


    /**
     * /学生历史分数分页查询
     *
     * @param req
     * @param session
     * @param model
     * @
     */
    @RequestMapping("/historyScoresPage.html")
    public void historyScoresPage(HttpServletRequest req, HttpSession session, Model model) {

        int pageNo = 0;
        int pageSize = 15;
        if (req.getParameter("pageNo") != null
                && StringUtil.isNumeric(req.getParameter("pageNo"))) {
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        if (pageNo < 1) {
            pageNo = 1;
        }

        String userId = "";
        User user = (User) session.getAttribute("user");
        if (user != null) {
            userId = user.getUid();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        try {
            model.addAttribute("pages", userService.searchUnitTestList(map, pageNo, pageSize));
        } catch (Exception e) {
            log.error("学生【userid:" + userId + "】历史分数分页查询失败：", e);
        }
    }

    /**
     * 查看简答题详情
     */
    @RequestMapping("/checUnitTestJDTDetail.html")
    public void checUnitTestJDTDetail(HttpServletRequest req, HttpSession session, Model model, String usid, String gname) {
        List<Map> jdt_list = null;
        try {
            jdt_list = userService.getJDTScoresDetail(usid);
        } catch (Exception e) {
            log.info("查看简答题详情出错:", e);
        }
        model.addAttribute("jdt_list", jdt_list);
        model.addAttribute("gname", gname);
    }

    /**
     * 修改用户信息
     */
    @RequestMapping(value = "/user/updateUserNews", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView updateUserNews(HttpServletRequest req, HttpSession session,
                                       @RequestParam(value = "timgUrl1", required = false) MultipartFile file,
                                       User user) throws Exception {
        User users = (User) session.getAttribute("user");
        if (users == null) {
            String uid = user.getUid();
            if (uid != null && uid.length() > 0) {
                users = userService.findUserByUid(uid);
            }
        }

        if (users != null) {
            if (file.getSize() > 0) {
                String realPath = uploadAbsolutePath;
                String path = req.getContextPath().substring(1);
//				realPath = realPath.replace(path, "userHeadImg");
                realPath = uploadAbsolutePath + "/" + "userHeadImg";
                String fileName = UUIDUtil.getUUID();
                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                file.transferTo(new File(realPath, fileName + "." + suffix));
                users.setHeadImgUrl("/userHeadImg/" + fileName + "." + suffix);
            }
            users.setEmail(user.getEmail());
            users.setUserName(user.getUserName());
            try {
                userService.updateUserNews(users);
                session.setAttribute("user", users);
                return new ModelAndView("redirect:/classindex.html");
            } catch (Exception e) {
                log.error("修改前端用户信息【id:" + users.getUid() + "】失败：", e);
            }
        }
        return new ModelAndView("redirect:/login.html");
    }

    /**
     * 用户注册
     *
     * @param session
     * @param ycode
     * @param user
     * @return
     * @
     */
    @RequestMapping(value = "/reg/user", method = RequestMethod.POST)
    public ModelAndView req(HttpSession session, String ycode, User user) {
        String code = String.valueOf(session.getAttribute("ycodes"));
        ModelAndView mode = new ModelAndView();
        String res = userService.insertUser(ycode, user, code, uploadAbsolutePath);

        if (StringUtils.isBlank(res)) {
            session.setAttribute("user", user);
            mode.setViewName("redirect:/userNews.html");
        } else {
            session.setAttribute("regError", res);
            mode.setViewName("redirect:/classReg.html");
        }

        return mode;
    }


    /**
     * 手机验证码校验
     */
    @RequestMapping("/user/yzCode")
    public void yzCode(HttpServletRequest req, HttpServletResponse response, HttpSession session,
                       int type, String code, String phone) throws Exception {
        String ycode = String.valueOf(session.getAttribute("ycodes"));
        String res = "ok";
        if (ycode != null && code != null && ycode.equals(code)) {
            if (type == 0) {
                //验证手机号是否唯一
                int count = userService.getIsExitsByPhone(phone);
                if (count > 0) {
                    res = "手机号【" + phone + "】已存在，不可重复注册！";
                }
            }
        } else {
            res = "手机验证码不正确！";
        }
        PrintHelper.sendJsonString(response, res);
    }

    /**
     * 手机验证码校验
     */
    @RequestMapping("/user/ckPhoneIsExsits")
    public void ckPhoneIsExsits(HttpServletRequest req, HttpServletResponse response,
                                HttpSession session, String phone) throws Exception {
        String res = "ok";
        try {
            int count = userService.getIsExitsByPhone(phone);
            if (count > 0) {
                res = "exsits";
            }
        } catch (Exception e) {
            e.printStackTrace();
            res = e.getMessage();
        }
        PrintHelper.sendJsonString(response, res);
    }

    /**
     * 找回密码
     *
     * @param req
     * @param response
     * @param session
     * @param ycode
     * @param user
     * @return
     * @
     */
    @RequestMapping(value = "/reg/backPass", method = RequestMethod.POST)
    public ModelAndView backPass(HttpServletRequest req, HttpServletResponse response, HttpSession session, String ycode, User user) {
        String code = String.valueOf(session.getAttribute("ycodes"));
        ModelAndView mode = new ModelAndView();
        String res = "";
        if (ycode != null && code != null && ycode.equals(code)) {
            //验证手机号是否存在
            int count = userService.getIsExitsByPhone(user.getPhoneNo());
            if (count > 0) {
                String newPwd = new MD5(user.getPassWord()).compute_upper();
                try {
                    userService.updatePwdByPhone(user.getPhoneNo(), newPwd);
                } catch (Exception e) {
                    res = "找回密码失败：" + e.getMessage();
                }
            } else {
                res = "手机号不存在，请先注册";
            }
        } else {
            res = "手机验证码不正确";
        }
        if (res.length() == 0) {
            mode.setViewName("redirect:/login.html");
        } else {
            session.setAttribute("regError", res);
            mode.setViewName("redirect:/backPass.html");
        }
        return mode;
    }

    /**
     * 短信验证码
     */
    @RequestMapping(value = "/reg/ycode", method = RequestMethod.POST)
    public void sendMessage(HttpServletRequest reqt, HttpServletResponse response, String tel, HttpSession session, Integer type)
            throws Exception {
        String res = CloudSMSUtil.sendSMS(tel, session, true, type);//type:0:注册验证码：1：普通验证码如密码找回
        PrintHelper.sendJsonString(response, res);
    }

    /**
     * 登陆页面跳转
     */
    @RequestMapping("/login.html")
    public void toLogin(HttpServletRequest req, HttpSession session, Model model) {
        String loginError = (String) session.getAttribute("loginError");
        if (loginError != null) {
            model.addAttribute("loginError", loginError);
            session.removeAttribute("loginError");
        }
    }

    /**
     * 用户登录
     *
     * @param req
     * @param session
     * @param user
     * @return
     * @
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean login(HttpServletRequest req, HttpSession session, User user) {
        User DBuser = userService.getOne(new QueryWrapper<User>().eq("phoneNo", user.getPhoneNo()), true);
        if (DBuser == null) {
            return ResultBean.failed("账号不存在");
        }
        String loginPwd = ShiroUtils.sha256(user.getPassWord(), DBuser.getSalt());

        if (StringUtils.isBlank(loginPwd) || !loginPwd.equals(DBuser.getPassWord())) {
            return ResultBean.failed("密码错误");
        }
        session.setAttribute("user", DBuser);
        return ResultBean.ok();
    }

    /**
     * 修改密码
     */
    @RequestMapping("/backPass")
    public void backPass(HttpServletRequest req) {

    }

    /**
     * reg注册页面跳转
     */
    @RequestMapping("/classReg.html")
    public void toreg(HttpServletRequest req, HttpSession session, Model model) {
        String regError = (String) session.getAttribute("regError");
        if (regError != null) {
            model.addAttribute("regError", regError);
            session.removeAttribute("regError");
        }

    }

    /**
     * 用户退出
     */
    @RequestMapping("/user/exitUser")
    @ResponseBody
    public ResultBean userExit(HttpServletRequest req) {
        req.getSession().removeAttribute("user");
        return ResultBean.ok();
    }

    /**
     * 批量生成邀请码
     */
    @RequestMapping("/user/makingYQCode")
    public void makingYQCode(HttpServletRequest req, HttpServletResponse response,
                             HttpSession session) throws Exception {
        List<User> list = userService.searchUserList(null);
        for (int i = 0; i < list.size(); i++) {
            String[] codes = userService.getNewYQCode(uploadAbsolutePath);
            userService.updateYQCodeByUserId(list.get(i).getUid(), codes[0], codes[1]);
        }
        response.getWriter().print("ok");
    }

    /**
     * 邀请码是否存在
     */
    @RequestMapping("/user/ckYQCodeExsits")
    public void ckYQCodeExsits(HttpServletRequest req, HttpServletResponse response,
                               HttpSession session,
                               String yqCode) throws Exception {
        try {
            User user = userService.getUserByYQCode(yqCode);
            if (user != null) {
                response.getWriter().print("ok");
            } else {
                response.getWriter().print("notExsits");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("error");
        }
    }

}
