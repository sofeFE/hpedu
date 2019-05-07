package com.hpedu.web.core.trophy.controller;

import com.hpedu.util.ResultBean;
import com.hpedu.util.codeUtil.BaseUtil;
import com.hpedu.util.codeUtil.PrintHelper;
import com.hpedu.util.codeUtil.StringUtil;
import com.hpedu.util.mybatis.MyBatisBase;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.trophy.pojo.StuImg;
import com.hpedu.web.core.trophy.pojo.Trophy;
import com.hpedu.web.core.trophy.service.TrophyService;
import com.hpedu.web.core.user.pojo.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/")
public class TrophyController {
    @Resource
    TrophyService trophyService;
    @Value("${uploadAbsolutePath}")
    private String uploadAbsolutePath;
    @Autowired
    private MyBatisBase base;


    private Logger log = BaseUtil.getLogger(TrophyController.class);

    @RequestMapping("/back/trophy.html")
    public void searchAllTrophy(HttpServletRequest req, HttpSession session, Model model) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Trophy> tlist = trophyService.findAllTrophy(map);
            model.addAttribute("tlist", tlist);
        } catch (Exception e) {
            log.error("查询学生后台出错：", e);
            model.addAttribute("tlist", new ArrayList<Trophy>());
        }
    }

    /**
     * 增加奖杯
     */
    @RequestMapping("/back/toAddTrophy.html")
    public void toAddTrophy(HttpServletRequest req, HttpSession session, Model mode) {
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
    }

    @RequestMapping(value = "/back-authc/addTrophy", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean<String> addTrophy(HttpServletRequest req, HttpSession session, @RequestParam(value = "pimgUrl1", required = false) MultipartFile file, Trophy trophy, @RequestParam(value = "file1", required = false) MultipartFile[] files) {
        ResultBean<String> result = new ResultBean<>();
        try {
            trophyService.addTrophy(trophy, file, files);
            result.setCode(ResultBean.SUCCESS);
            return result;
        } catch (Exception e) {
            log.error("新增奖杯信息出错了:", e);
            result.setCode(ResultBean.UNKNOWN_EXCEPTION);
            result.setMsg("something wrong");
        }

        return result;

    }


    /**
     * 根据id删除奖杯
     */
    @RequestMapping("back-authc/deleteTrophy")
    public ModelAndView deleteTrophy(HttpServletRequest req, HttpSession session, String id) {
        User user = (User) session.getAttribute("backuser");
        if (user != null) {
            try {
                trophyService.deleteTrophy(id);
            } catch (Exception e) {
                log.error("删除奖杯信息【id:" + id + "】出错了:", e);
            }
            return new ModelAndView("redirect:/back/trophy.html");
        }
        return new ModelAndView("redirect:/back/backlogin.html");
    }

    /**
     * 修改奖杯信息
     */
    @RequestMapping("/back/toUpdateTrophy.html")
    public void toUpdateTrophy(HttpServletRequest req, HttpSession session, String id, Model mode) {
        try {
            Trophy trophy = trophyService.findTrophyById(id);
            trophy.setStuList(trophyService.selectStuImgByTpid(id));
            mode.addAttribute("trophy", trophy);
        } catch (Exception e) {
            log.error("修改奖杯信息【id:" + id + "】出错了:", e);
            mode.addAttribute("error", "出错了:" + e.getMessage());
        }

        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
    }

    @RequestMapping(value = "/back-authc/updatTrophy", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean<String> updatTrophy(  @RequestParam(value = "pimgUrl1", required = false) MultipartFile file, 
                                    Trophy trophy, 
                                    @RequestParam(value = "file1", required = false) MultipartFile[] files,
                                      String[] delImhid) {
        ResultBean<String> result = new ResultBean<>();
            try {
               
                trophyService.updateTrophy(trophy,file,files,delImhid);
                result.setCode(ResultBean.SUCCESS);
                return result;
            } catch (Exception e) {
                log.error("修改学员信息【id:" + trophy.getPid() + "】失败：", e);
                result.setCode(ResultBean.UNKNOWN_EXCEPTION);
                result.setMsg("something wrong");
                
            }
        return result;
    }

    /**
     * 上移、下移
     */
    @RequestMapping("/back-authc/updatStuSort")
    @ResponseBody
    public ResultBean updatStuSort( String id1, String sort1, String id2, String sort2) {
        ResultBean result = new ResultBean();
        try {
            trophyService.updateTrophySort(id1, sort1,id2, sort2);
            
            result.setCode(ResultBean.SUCCESS);
        } catch (Exception e) {
//            throw new RuntimeException(e);
            result.setCode(ResultBean.UNKNOWN_EXCEPTION);
        }
return result ;
    }
    //查看学员信息
    @RequestMapping("/order/checkStu.html")
    public ModelAndView checkStu(HttpServletRequest req, HttpServletResponse response, String id) {
        ModelAndView mode = new ModelAndView();
        Trophy stu = null;
        try {
            stu = trophyService.findTrophyById(id);
            stu.setStuList(trophyService.selectStuImgByTpid(id));
            String info = stu.getInfo();
            String pintro = stu.getPintro();
            if (info != null) {
                info = info.replace("<pre class=\"js_message_plain ng-binding\">", "<pre class=\"js_message_plain ng-binding\" style=\"border:0px;background:white;\">");
                stu.setInfo(info);
            }
            if (pintro != null) {
                pintro = pintro.replace("<pre class=\"js_message_plain ng-binding\">", "<p>").replace("</pre>", "</p>");
                stu.setPintro(pintro);
            }

        } catch (Exception e) {
            stu = new Trophy();
            stu.setStuList(new ArrayList<StuImg>());
            log.error("查询学员【id:" + id + "】失败:", e);
        }
        mode.setViewName("order/checkStu");
        mode.addObject("stu", stu);
        return mode;
    }

    //分页查看所有学员信息
    @RequestMapping("/order/stuList.html")
    public ModelAndView stuList(HttpServletRequest req, HttpServletResponse response) {

        ModelAndView mode = new ModelAndView();
        Map<String, String> map = new HashMap<String, String>();
        Page<Trophy> pages = null;
        try {
            int pageNo = 0;
            int pageSize = 4;
            if (req.getParameter("pageNo") != null && !req.getParameter("pageNo").equals("")
                    && StringUtil.isNumeric(req.getParameter("pageNo"))) {
                pageNo = Integer.parseInt(req.getParameter("pageNo"));
            }
            if (pageNo < 1) {
                pageNo = 1;
            }
            pages = trophyService.searchTrophyList(map, pageNo, pageSize);
        } catch (Exception e) {
            log.error("查询学员失败:", e);
            pages = new Page<Trophy>();
            pages.setResult(new ArrayList<Trophy>());

        }
        mode.addObject("pages", pages);
        mode.setViewName("order/stuList");
        return mode;

    }

   

}
