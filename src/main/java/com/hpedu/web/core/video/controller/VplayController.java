package com.hpedu.web.core.video.controller;

import com.hpedu.util.codeUtil.BaseUtil;
import com.hpedu.util.codeUtil.StringUtil;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.evaluation.pojo.Evaluation;
import com.hpedu.web.core.evaluation.service.EvaluationService;
import com.hpedu.web.core.order.service.OrderService;
import com.hpedu.web.core.user.pojo.User;
import com.hpedu.web.core.user.service.UserService;
import com.hpedu.web.core.video.pojo.GeneralVideo;
import com.hpedu.web.core.video.pojo.VideoChild;
import com.hpedu.web.core.video.service.ContestVideoService;
import com.hpedu.web.core.video.service.GeneralVideoService;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalField;
import java.util.*;

@Controller
@RequestMapping("/")
public class VplayController {

    private static Map<String, String> weekMap = new HashMap<String, String>();

    static {
        weekMap.put("星期一", "每周一");
        weekMap.put("星期二", "每周二");
        weekMap.put("星期三", "每周三");
        weekMap.put("星期四", "每周四");
        weekMap.put("星期五", "每周五");
        weekMap.put("星期六", "每周六");
        weekMap.put("星期日", "每周日");
    }

    @Resource
    EvaluationService evaluationService;
    @Resource
    GeneralVideoService generalVideoService;
    @Resource
    OrderService orderService;
    @Resource
    ContestVideoService contestVideoService;
    @Resource
    UserService userService;
    private Logger log = BaseUtil.getLogger(VplayController.class);

    /**
     * 点击播放视频（常规）
     */
    @PostMapping(value = "/general/general.html", produces = {"text/html"})
    public String toGenPlayer(HttpServletRequest req, HttpSession session, String classNo, String className, Model model) {
        this.editUserInSession(session);
        model = getMode(model, session, classNo, className, false);

        return "general/general";
    }

    //更新session中用户的信息
    private void editUserInSession(HttpSession session) {
        User u = (User) session.getAttribute("user");
        if (u != null) {
            session.setAttribute("user", userService.findUserByUid(u.getUid()));
        }
    }

    //获取常规视频试图信息
    private Model getMode(Model model, HttpSession session, String classNo, String className, boolean isMore) {
        User user = (User) session.getAttribute("user");
        if (classNo != null) {
            session.setAttribute("g_classNo", classNo);
        } else {
            classNo = (String) session.getAttribute("g_classNo");
        }
        if (className != null) {
            session.setAttribute("g_className", className);
        } else {
            className = (String) session.getAttribute("g_className");
        }
        GeneralVideo generalVideo = null;
        try {
            if (classNo != null && !"".equals(classNo)) {
                try {
                    generalVideo = generalVideoService.findGeneralVideoByVid(classNo);
                    if (isMore) {//有多个子视频
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("pid", classNo);
                        if (!StringUtils.isEmpty(generalVideo.getWeekVal())) {
                            map.put("weekVal", "true");
                            String[] strArr = generalVideo.getWeekVal().split("-");
                            model.addAttribute("weekVal", weekMap.get(strArr[0]) + strArr[1] + ":00");
                        }
                        List<VideoChild> vclist = generalVideoService.selectAllChildVideo(map);
                        generalVideo.setVclist(vclist);
                        if (!StringUtils.isEmpty(generalVideo.getWeekVal())) {
                            //检查是否要开启页面定时
                            LocalDateTime date = LocalDateTime.now();
                            String[] dateArr = new SimpleDateFormat("yyyy-MM-dd#EEEE-HH").format(date).split("#");
                            String[] curr_week = dateArr[1].split("-");//当前星期和小时
                            String[] edit_week = generalVideo.getWeekVal().split("-");//更新星期和小时
                            
                            if (edit_week[0].equals(curr_week[0]) && date.getHour() < Integer.parseInt(edit_week[1])) {
                                Date editDate_now = new SimpleDateFormat("yyyy-MM-dd HH").parse(dateArr[0] + " " + edit_week[1]);
                                VideoChild lastVC = vclist.size() == 0 ? null : vclist.get(vclist.size() - 1);
                                if (lastVC == null || lastVC.getEditDate().getTime() < editDate_now.getTime()) {
                                    model.addAttribute("editTimerNum", editDate_now.getTime() - date.toInstant(ZoneOffset.of("+8")).toEpochMilli()    );//更新下一集的毫秒数
                                    if (lastVC != null) {
                                        model.addAttribute("lastVcid", lastVC.getVcid());
                                    }
                                }
                            }
                        }
                        if (generalVideo.getVclist().size() > 0) {
                            model.addAttribute("videoChild", generalVideo.getVclist().get(0));
                        } else {
                            model.addAttribute("videoChild", new VideoChild());
                        }
                    }
                    generalVideo.setPdflist(contestVideoService.selectPdfByVid(classNo, "0"));
                } catch (Exception e) {
                    log.info("首页查询常规视频和相关pdf出错：", e);
                }
                List<GeneralVideo> glist = null;
                if (generalVideo != null) {
                    try {
                        glist = generalVideoService.findGeneralVideoByVideo(generalVideo.getGsbuject(), generalVideo.getGclass(), generalVideo.getGclassify(), classNo);
                    } catch (Exception e) {
                        log.info("首页查询常规相关视频出错：", e);
                    }
                } else {
                    glist = new ArrayList<GeneralVideo>();
                    generalVideo = new GeneralVideo();
                }
                model.addAttribute("generalVideo", generalVideo);
                model.addAttribute("glist", glist);
                //评论
                //List<Evaluation> elist = evaluationService.findAllEvaluationByEid(classNo);
                List<Evaluation> elist = null;
                try {
                    elist = evaluationService.findTop20EvaluationByEid(classNo);
                } catch (Exception e) {
                    elist = new ArrayList<Evaluation>();
                    log.info("首页查询常规评论出错：", e);
                }
                model.addAttribute("elist", elist);
            }
            int isBuy = 0;
            if (user != null) {
                model.addAttribute("user", user);
                //检查用户是否购买过此视频
                try {
                    isBuy = orderService.getIsBuyVideoByVid(classNo, "0", user.getUid());
                } catch (Exception e) {
                    log.info("首页查询常规用户是否购买出错：", e);
                }

            }
            model.addAttribute("isBuy", isBuy);
            if (generalVideo != null) {
                //获取去秒杀价格相关信息
                Integer isKill = generalVideo.getIsKill();
                if (isKill == 1) {//执行秒杀活动
                    String killStartTime = generalVideo.getKillStartTime();
                    String killEndTime = generalVideo.getKillEndTime();
                    Map<String, Object> map = BaseUtil.getKillInfo(killStartTime, killEndTime);
                    model.addAttribute("killInfo", map);
                }
            } else {
                log.info("generalVideo是空！id:" + classNo);
            }
        } catch (Exception e) {
            log.info("首页点击播放常规视频视频出错：", e);
        }
        return model;
    }

    /**
     * 点击播放视频（常规-专题-多关联视频）
     */
    @RequestMapping(value = "/general/generalMore.html", produces = {"text/html"})
    public String toGenPlayerMore(HttpServletRequest req, HttpSession session, String classNo, String className, Model model, String vcid) {
        this.editUserInSession(session);
        model = getMode(model, session, classNo, className, true);
        return "general/generalMore";
    }

    /**
     * 获取最新更新视频
     */
    @RequestMapping("/general/getLastNewVideo")
    @ResponseBody
    public VideoChild getLastNewVideo(HttpServletRequest req, HttpSession session, String gid, String lastVcid) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("gid", gid);
        if (!StringUtils.isEmpty(lastVcid)) {
            map.put("lastVcid", lastVcid);
        }
        VideoChild vc = generalVideoService.getLastNewEditVideoChild(map);
        return vc;
    }

    /**
     * 常规课 查询LIST
     *
     * @param gsbuject  学科
     * @param gclass    年级
     * @param gclassify 年级下的分类（例：五年级:阅读）
     */
    @RequestMapping("/general/generalVideoList.html")
    public void searchGeneralVideoList(HttpServletRequest req, HttpSession session, String gsbuject, String gclass, String gclassify, String sort, String nameType, Model model) {
        User user = (User) session.getAttribute("user");
        Map<String, String> maps = new HashMap<String, String>();
		/*maps.put("gsbuject",new String(gsbuject.getBytes("iso8859-1"),"utf-8"));
		maps.put("gclass",new String(gclass.getBytes("iso8859-1"),"utf-8"));
		 gclassify=new String(gclassify.getBytes("iso8859-1"),"utf-8");
		 String gclassify_new=gclassify.replace("各学校", "");
		maps.put("gclassify",gclassify_new);*/
        maps.put("gsbuject", gsbuject);
        maps.put("gclass", gclass);
        String gclassify_new = gclassify.replace("各学校", "");
        maps.put("gclassify", gclassify_new);
        sort = StringUtils.isEmpty(sort) ? "desc" : sort;
        maps.put("sort", sort);
        if (gclass != null && gclass.equals("新概念") && !StringUtils.isEmpty(nameType)) {
            maps.put("nameType", nameType);
        }
        int pageNo = 0;
        int pageSize = 20;
        if (req.getParameter("pageNo") != null
                && StringUtil.isNumeric(req.getParameter("pageNo"))) {
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        if (pageNo < 1) {
            pageNo = 1;
        }
        Page<GeneralVideo> pages = null;
        try {
            pages = generalVideoService.searchGeneralVideoList(maps, pageNo, pageSize);
        } catch (Exception e) {
            pages = new Page<GeneralVideo>();
            pages.setResult(new ArrayList<GeneralVideo>());
            log.error("首页查询常规视频list出错:", e);
        }
        model.addAttribute("page", pages);
        model.addAttribute("gsbuject", maps.get("gsbuject"));
        model.addAttribute("gclass", maps.get("gclass"));
        model.addAttribute("gclassify", maps.get("gclassify"));
        model.addAttribute("sort", sort);
        model.addAttribute("nameType", nameType);
        if (user != null) {
            model.addAttribute("user", user);
        }
    }

    /**
     * 常规课 查询LIST
     *
     * @param gsbuject  学科
     * @param gclass    年级
     * @param gclassify 年级下的分类（例：五年级:阅读）
     */
    @RequestMapping("/general/generalVideoList_zt.html")
    public void searchGeneralVideoList_zt(HttpServletRequest req, HttpSession session, String gsbuject, String gclass, String gclassify, Model model) {
        User user = (User) session.getAttribute("user");
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("gsbuject", gsbuject);
        maps.put("gclass", gclass);
        String gclassify_new = gclassify.replace("各学校", "");
        maps.put("gclassify", gclassify_new);
        List<GeneralVideo> list = null;
        try {
            list = generalVideoService.searchGeneralVideoList(maps);
        } catch (Exception e) {
            list = new ArrayList<GeneralVideo>();
            log.error("首页查询常规视频list出错:", e);
        }

        String[] gclassify2Arr = new String[]{"计算", "数论", "几何", "计数", "应用题", "方程", "行程"};
        List<GeneralVideo> jisuan = new ArrayList<GeneralVideo>();
        List<GeneralVideo> shulun = new ArrayList<GeneralVideo>();
        List<GeneralVideo> jihe = new ArrayList<GeneralVideo>();
        List<GeneralVideo> jishu = new ArrayList<GeneralVideo>();
        List<GeneralVideo> yingyongti = new ArrayList<GeneralVideo>();
        List<GeneralVideo> fangcheng = new ArrayList<GeneralVideo>();
        List<GeneralVideo> xingcheng = new ArrayList<GeneralVideo>();
        List<GeneralVideo> qita = new ArrayList<GeneralVideo>();

        List<String> gclassify2List = Arrays.asList(gclassify2Arr);
        for (GeneralVideo g : list) {
            String gclassify2 = g.getGclassify2();
            gclassify2 = gclassify2 == null ? "" : gclassify2;
            if (!gclassify2List.contains(gclassify2)) {
                qita.add(g);
            } else {
                if (gclassify2.equals("计算")) {
                    jisuan.add(g);
                } else if (gclassify2.equals("数论")) {
                    shulun.add(g);
                } else if (gclassify2.equals("几何")) {
                    jihe.add(g);
                } else if (gclassify2.equals("计数")) {
                    jishu.add(g);
                } else if (gclassify2.equals("应用题")) {
                    yingyongti.add(g);
                } else if (gclassify2.equals("方程")) {
                    fangcheng.add(g);
                } else if (gclassify2.equals("行程")) {
                    xingcheng.add(g);
                }
            }

        }
        model.addAttribute("gsbuject", maps.get("gsbuject"));
        model.addAttribute("gclass", maps.get("gclass"));
        model.addAttribute("gclassify", maps.get("gclassify"));

        model.addAttribute("totalCount", list.size());

        model.addAttribute("qita", qita);
        model.addAttribute("jisuan", jisuan);
        model.addAttribute("shulun", shulun);
        model.addAttribute("jihe", jihe);
        model.addAttribute("jishu", jishu);
        model.addAttribute("yingyongti", yingyongti);
        model.addAttribute("fangcheng", fangcheng);
        model.addAttribute("xingcheng", xingcheng);

        if (user != null) {
            model.addAttribute("user", user);
        }
    }

}
