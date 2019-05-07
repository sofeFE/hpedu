package com.hpedu.web.core.video.controller;

import com.hpedu.util.ResultBean;
import com.hpedu.util.codeUtil.BaseUtil;
import com.hpedu.util.codeUtil.StringUtil;
import com.hpedu.util.codeUtil.TxtUtil;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.evaluation.pojo.Evaluation;
import com.hpedu.web.core.evaluation.service.EvaluationService;
import com.hpedu.web.core.order.service.OrderService;
import com.hpedu.web.core.user.pojo.User;
import com.hpedu.web.core.user.service.UserService;
import com.hpedu.web.core.video.pojo.ContestVideo;
import com.hpedu.web.core.video.service.ContestVideoService;
import com.hpedu.web.core.video.service.GeneralVideoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/")
public class CplayController {
    @Resource
    ContestVideoService contestVideoService;
    @Resource
    EvaluationService evaluationService;
    @Resource
    OrderService orderService;
    @Resource
    GeneralVideoService generalVideoService;
    @Resource
    UserService userService;
    @Value("${uploadAbsolutePath}")
    private String uploadAbsolutePath;
    private Logger log = BaseUtil.getLogger(CplayController.class);

    /**
     * 首页点击播放竞赛视频视频
     */
    @RequestMapping("/contest/contest.html")
    public void toContextVideo(HttpServletRequest req, HttpSession session,
                               String classNo, Model model) {
        this.editUserInSession(session);
        if (!StringUtils.isBlank(classNo)) {
            session.setAttribute("c_classNo", classNo);
        } else {
            classNo = (String) session.getAttribute("c_classNo");
        }
        //点击播放的视频
        ContestVideo contestVideo = null;
//        try {
            User user = (User) session.getAttribute("user");
            if (!StringUtils.isBlank(classNo)) {
//                try {
                    contestVideo = contestVideoService.findContestVideoById(classNo);
                    contestVideo.setPdflist(contestVideoService.selectPdfByVid(classNo, "1"));
//                } catch (Exception e) {
//                    log.info("首页查询竞赛视频和相关pdf出错：", e);
//                }
                List<ContestVideo> conList = null;
                if (contestVideo != null) {
                    //相关视频
//                    try {
                        conList = contestVideoService.findContestVideoByVideo(
                                contestVideo.getCompetitionName(),
                                contestVideo.getCclass(),
                                contestVideo.getCclassify(), 
                                classNo);
//                    } catch (Exception e) {
//                        log.info("首页查询竞赛相关视频出错：", e);
//                    }

                } else {
                    contestVideo = new ContestVideo();
                    conList = new ArrayList<ContestVideo>();
                }
                model.addAttribute("conList", conList);
                model.addAttribute("contestVideo", contestVideo);
                //评论
                //   List<Evaluation> elist = evaluationService.findAllEvaluationByEid(classNo);
                List<Evaluation> elist = null;
//                try {
                    elist = evaluationService.findTop20EvaluationByEid(classNo);
//                } catch (Exception e) {
//                    elist = new ArrayList<Evaluation>();
//                    log.info("首页查询竞赛评论出错：", e);
//                }
                model.addAttribute("elist", elist);
            }
            
            int isBuy = 0;
            if (user != null) {
                model.addAttribute("user", user);
                //检查用户是否购买过此视频
//                try {
                    isBuy = orderService.getIsBuyVideoByVid(classNo, "1", user.getUid());
//                } catch (Exception e) {
//                    log.info("首页查询竞赛用户是否购买出错：", e);
//                }

            }
            model.addAttribute("isBuy", isBuy);
            //获取去秒杀价格相关信息
            if (contestVideo != null) {
                Integer isKill = contestVideo.getIsKill();
                if (isKill == 1 && !contestVideo.getCmoney().equals("0")) {//执行秒杀活动
                    String killStartTime = contestVideo.getKillStartTime();
                    String killEndTime = contestVideo.getKillEndTime();
                    Map<String, Object> map = BaseUtil.getKillInfo(killStartTime, killEndTime);
                    model.addAttribute("killInfo", map);
                }
            } else {
                log.info("contestVideo是空！id:" + classNo);
            }

//        } catch (Exception e) {
//            log.error("首页点击播放竞赛视频视频出错：", e);
//        }
    }


    //更新session中用户的信息
    private void editUserInSession(HttpSession session) {
        User u = (User) session.getAttribute("user");
        if (u != null) {
            session.setAttribute("user", userService.findUserByUid(u.getUid()));
        }
    }

    /**
     * 竞赛课程 list
     */
    @RequestMapping("/contest/contestVideoList.html")
    public void searchContestList(HttpServletRequest req, HttpSession session,
                                  String competitionName, String cclass, String cclassify, Model model) {
        User user = (User) session.getAttribute("user");
        Map<String, String> maps = new HashMap<String, String>();
		/*maps.put("competitionName",new String(competitionName.getBytes("iso8859-1"),"utf-8"));
		maps.put("cclass",new String(cclass.getBytes("iso8859-1"),"utf-8"));
		maps.put("cclassify",new String(cclassify.getBytes("iso8859-1"),"utf-8"));*/
        maps.put("competitionName", competitionName);
        maps.put("cclass", cclass);
        maps.put("cclassify", cclassify);
        int pageNo = 0;
        int pageSize = 20;
        if (req.getParameter("pageNo") != null
                && StringUtil.isNumeric(req.getParameter("pageNo"))) {
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        if (pageNo < 1) {
            pageNo = 1;
        }
        Page<ContestVideo> pages = null;
        try {
            pages = contestVideoService.searchContestVideoList(maps, pageNo, pageSize);
        } catch (Exception e) {
            pages = new Page<ContestVideo>();
            pages.setResult(new ArrayList<ContestVideo>());
            log.error("首页查询竞赛视频list出错:", e);
        }
        model.addAttribute("pages", pages);
        model.addAttribute("competitionName", maps.get("competitionName"));
        model.addAttribute("cclass", maps.get("cclass"));
        model.addAttribute("cclassify", maps.get("cclassify"));
        if (user != null) {
            model.addAttribute("user", user);
        }
    }

    /**
     * pdf下载
     */
    @RequestMapping("/download/file")
    public void downLoadCVPDF(HttpServletRequest req, HttpServletResponse response, HttpSession session, String url) {
        try {
            //下载调用
            TxtUtil.downFile(req, response, url, uploadAbsolutePath);
        } catch (Exception e) {
            log.info("pdf下载出错:", e);
        }
    }

    /**
     * 修改视频播放次数
     */
    @PostMapping("/changeVideoPlayTime")
    @ResponseBody
    public ResultBean changePlayCount(HttpServletRequest req, HttpServletResponse response, HttpSession session, String vid, Integer vclassify) {
        int result = 0;
        ResultBean resultBean = new ResultBean();
        if (vclassify == 1) {//竞赛
            result = contestVideoService.updatePlayCount(vid);
        } else {//常规
            result = generalVideoService.updatePlayCount(vid);
        }
        if (result > 0) {
            resultBean.setCode(ResultBean.SUCCESS);
            resultBean.setMsg("update success");
        } else {
            resultBean.setCode(ResultBean.UNKNOWN_EXCEPTION);
            resultBean.setMsg("update failed");
        }
        return resultBean;
    }
}
