package com.hpedu.web.core.back.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hpedu.util.codeUtil.*;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.exam.pojo.Exam;
import com.hpedu.web.core.exam.pojo.ExamImg;
import com.hpedu.web.core.exam.service.ExamService;
import com.hpedu.web.core.order.pojo.ArticleImg;
import com.hpedu.web.core.order.pojo.Banner;
import com.hpedu.web.core.order.service.OrderService;
import com.hpedu.web.core.shiro.pojo.SysUserEntity;
import com.hpedu.web.core.teacher.pojo.Teacher;
import com.hpedu.web.core.teacher.service.TeacherService;
import com.hpedu.util.CloudSMSUtil;
import com.hpedu.web.core.user.pojo.*;
import com.hpedu.web.core.user.service.UserService;
import com.hpedu.web.core.video.pojo.ContestVideo;
import com.hpedu.web.core.video.pojo.GeneralVideo;
import com.hpedu.web.core.video.pojo.VideoChild;
import com.hpedu.web.core.video.pojo.VideoPdf;
import com.hpedu.web.core.video.service.ContestVideoService;
import com.hpedu.web.core.video.service.GeneralVideoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/")
public class BackController {

    @Resource
    TeacherService teacherService;
    @Resource
    GeneralVideoService generalVideoService;
    @Resource
    ContestVideoService contestVideoService;
    @Resource
    ExamService examService;
    @Resource
    UserService userService;
    @Resource
    OrderService orderService;

    //	@Value("${uploadAbsolutePath}")
    @Value("${uploadAbsolutePath}")
    private String uploadAbsolutePath;


    private Logger log = BaseUtil.getLogger(BackController.class);


    

    @RequestMapping("/back/info.html")
    public void toInfo(HttpServletRequest req, HttpSession session, Model model) {
        SysUserEntity user = (SysUserEntity) session.getAttribute("backuser");
        if (user != null) {
            model.addAttribute("backuser", user);
        }

    }

    

    /**
     * 常规课程管理
     */
    @RequestMapping("/back/backGeneral/chinese/generalChineseList.html")
    public void searchAllGeneralChinese(HttpServletRequest req, HttpSession session, Model model) {
        Map<String, String> maps = new HashMap<String, String>();
        Page<GeneralVideo> pages = null;
        try {
            int pageNo = 0;
            int pageSize = 20;
            if (req.getParameter("pageNo") != null && StringUtil.isNumeric(req.getParameter("pageNo"))) {
                pageNo = Integer.parseInt(req.getParameter("pageNo"));
            }
            if (pageNo < 1) {
                pageNo = 1;
            }
            String gclass = req.getParameter("gclass");
            String gclassify = req.getParameter("gclassify");

            maps.put("gsbuject", "语文");
            if (!"".equals(gclass) && null != gclass) {
                // maps.put("gclass",new
                // String(gclass.getBytes("iso8859-1"),"utf-8") );
                maps.put("gclass", gclass);
            }
            // 分类 如：阅读
            if (!"".equals(gclassify) && null != gclassify) {
                // maps.put("gclassify",new
                // String(gclassify.getBytes("iso8859-1"),"utf-8"));
                maps.put("gclassify", gclassify);
            }
            pages = generalVideoService.searchGeneralVideoList(maps, pageNo, pageSize);

        } catch (Exception e) {
            pages = new Page<GeneralVideo>();
            pages.setResult(new ArrayList<GeneralVideo>());

            log.error("查询语文课程失败了", e);
        }
        model.addAttribute("pages", pages);
        model.addAttribute("map", maps);
    }

    /**
     * 常规课程视频删除
     */
    @RequestMapping("/back/deleteGeneralByid")
    public ModelAndView deleteGeneralByid(HttpServletRequest req, HttpSession session, String gid, Integer isMore) {
        User user = (User) session.getAttribute("backuser");
        try {
            if (null != gid && !"".equals(gid)) {
                if (null != user) {
                    generalVideoService.deleteGeneralById(gid);
                    if (isMore != null && isMore == 1) {
                        generalVideoService.deleteVideoChildById(gid);
                    }
                    return new ModelAndView("redirect:/back/backGeneral/chinese/generalChineseList.html");
                }
            }
        } catch (Exception e) {
            log.error("常规课程删除出错了【id:" + gid + "】：", e);

        }
        return new ModelAndView("redirect:/back/backlogin.html");
    }

    /**
     * 新增常规课程
     */
    @RequestMapping("/back/backGeneral/chinese/addChineseGeneral.html")
    public void toaddChineseGeneralVideo(HttpServletRequest req, HttpSession session, Model mode) {
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
        List<Teacher> tclist = teacherService.findAllTeacher(null);
        mode.addAttribute("tclist", tclist);

    }

    /**
     * 新增语文课程视频
     */
    @RequestMapping(value = "/back/addChineseGeneralVideo", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addChineseGeneralVideo(HttpServletRequest req, HttpSession session,
                                               @RequestParam(value = "file1", required = false) MultipartFile[] files, GeneralVideo generalVideo) {
        try {
            if (null != generalVideo) {
                generalVideo.setIsMore(0);
                generalVideo = this.Get_saveGeneralVideo(req, files, generalVideo, "语文");
                generalVideoService.addChineseGeneralVideo(generalVideo);

                List<VideoPdf> pdflist = generalVideo.getPdflist();
                if (pdflist.size() > 0) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("list", pdflist);
                    map.put("vid", generalVideo.getGid());
                    generalVideoService.insertVideoPdf(map);
                }
                return new ModelAndView("redirect:/back/backGeneral/chinese/generalChineseList.html");
            }
        } catch (Exception e) {
            session.setAttribute("error", "出错了：" + e.getMessage());
            log.error("新增语文课程视频出错了：", e);

        }

        return new ModelAndView("redirect:/back/backGeneral/chinese/addChineseGeneral.html");
    }


    /**
     * 修改语文课程常规视频
     */
    @RequestMapping(value = "/back/chinese/updateGeneralVideo", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView updateGeneralVideo(HttpServletRequest req, HttpSession session,
                                           @RequestParam(value = "file1", required = false) MultipartFile[] files, GeneralVideo generalVideo) {
        try {
            generalVideo = this.Get_updateGeneralVideo(req, files, generalVideo);
            generalVideoService.updateChineseGeneralVideo(generalVideo);
            List<VideoPdf> pdflist = generalVideo.getPdflist();
            if (pdflist.size() > 0) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("list", pdflist);
                map.put("vid", generalVideo.getGid());
                generalVideoService.insertVideoPdf(map);
            }

            String[] delpdfid = req.getParameterValues("delpdfid");
            if (delpdfid != null && delpdfid.length > 0) {
                generalVideoService.deleteVideoPdf(delpdfid);
            }

            return new ModelAndView("redirect:/back/backGeneral/chinese/generalChineseList.html");
        } catch (Exception e) {
            session.setAttribute("error", "出错了：" + e.getMessage());
            session.setAttribute("gid", generalVideo.getGid());
            log.error("修改语文课程视频【id:" + generalVideo.getGid() + "】出错了：", e);
        }
        return new ModelAndView(
                "redirect:/back/backGeneral/chinese/updateChineseGeneralVideo.html?gid=" + generalVideo.getGid());
    }

    /**
     * 常规课程数学
     */
    @RequestMapping("/back/backGeneral/math/searchAllMathGeneralList.html")
    public void searchAllMathGeneralVideo(HttpServletRequest req, HttpSession session, Model model) {
        Map<String, String> maps = new HashMap<String, String>();
        Page<GeneralVideo> pages = null;
        try {
            int pageNo = 0;
            int pageSize = 20;
            if (req.getParameter("pageNo") != null && StringUtil.isNumeric(req.getParameter("pageNo"))) {
                pageNo = Integer.parseInt(req.getParameter("pageNo"));
            }
            if (pageNo < 1) {
                pageNo = 1;
            }
            String gclass = req.getParameter("gclass");
            String gclassify = req.getParameter("gclassify");

            maps.put("gsbuject", "数学");
            if (!"".equals(gclass) && null != gclass) {
                // maps.put("gclass",new
                // String(gclass.getBytes("iso8859-1"),"utf-8"));
                maps.put("gclass", gclass);
            }
            // 分类 如：专题课 同步班
            if (!"".equals(gclassify) && null != gclassify) {
                // maps.put("gclassify",new
                // String(gclassify.getBytes("iso8859-1"),"utf-8"));
                maps.put("gclassify", gclassify);
            }
            pages = generalVideoService.searchGeneralVideoList(maps, pageNo, pageSize);
        } catch (Exception e) {
            log.error("查询常规数学课出错了:", e);
            pages = new Page<GeneralVideo>();
            pages.setResult(new ArrayList<GeneralVideo>());
        }
        model.addAttribute("pages", pages);
        model.addAttribute("map", maps);

    }

    /**
     * 常规课程数学
     */
    @RequestMapping("/back/backGeneral/science/searchScienceGeneral")
    public void searchScienceGeneralVideo(HttpServletRequest req, HttpSession session, Model model) {
        Map<String, String> maps = new HashMap<String, String>();
        Page<GeneralVideo> pages = null;
        try {
            int pageNo = 0;
            int pageSize = 20;
            if (req.getParameter("pageNo") != null && StringUtil.isNumeric(req.getParameter("pageNo"))) {
                pageNo = Integer.parseInt(req.getParameter("pageNo"));
            }
            if (pageNo < 1) {
                pageNo = 1;
            }
            String gclass = req.getParameter("gclass");
            String gclassify = req.getParameter("gclassify");

            maps.put("gsbuject", "科学");
            if (!"".equals(gclass) && null != gclass) {
                maps.put("gclass", gclass);
            }
            // 分类 如：专题课 同步班
            if (!"".equals(gclassify) && null != gclassify) {
                maps.put("gclassify", gclassify);
            }
            pages = generalVideoService.searchGeneralVideoList(maps, pageNo, pageSize);
        } catch (Exception e) {
            log.error("查询常规数学课出错了:", e);
            pages = new Page<GeneralVideo>();
            pages.setResult(new ArrayList<GeneralVideo>());
        }
        model.addAttribute("pages", pages);
        model.addAttribute("map", maps);

    }

    /**
     * 添加 常规数学课程
     */
    @RequestMapping("/back/backGeneral/math/addMathGeneral.html")
    public void toAddMathGeneral(HttpServletRequest req, HttpSession session, Model mode) {
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
        List<Teacher> tclist = teacherService.findAllTeacher(null);
        mode.addAttribute("tclist", tclist);
    }

    /**
     * 跳转 常规科学课程 添加页
     */
    @RequestMapping("/back/backGeneral/science/addScienceGeneral")
    public void addScienceGeneral(HttpServletRequest req, HttpSession session, Model mode) {
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
        List<Teacher> tclist = teacherService.findAllTeacher(null);
        mode.addAttribute("tclist", tclist);
    }

    /**
     * 添加 常规专题
     */
    @RequestMapping("/back/backGeneral/addVideoMore.html")
    public void addVideoMore(HttpServletRequest req, HttpSession session, Model mode, Integer type, String gsbuject) {
        String subject = "";
        if (gsbuject != null && gsbuject.length() > 0) {
            subject = gsbuject;
        } else {
            if (type == 0) {
                subject = "语文";
            } else if (type == 1) {
                subject = "数学";
            } else if (type == 2) {
                subject = "英语";
            } else if (type == 3) {
                subject = "科学";
            }
        }
        List<Teacher> tclist = teacherService.findAllTeacher(null);
        mode.addAttribute("tclist", tclist);

        mode.addAttribute("subject", subject);
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
    }

    /**
     * 新增常规数学课程
     */

    @RequestMapping(value = "/back/addMathGeneral", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addMathGeneral(HttpServletRequest req, HttpSession session,
                                       @RequestParam(value = "file1", required = false) MultipartFile[] files, GeneralVideo generalVideo) {
        try {
            if (null != generalVideo) {
                generalVideo.setIsMore(0);
                generalVideo = this.Get_saveGeneralVideo(req, files, generalVideo, "数学");
                generalVideoService.addChineseGeneralVideo(generalVideo);

                List<VideoPdf> pdflist = generalVideo.getPdflist();
                if (pdflist.size() > 0) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("list", pdflist);
                    map.put("vid", generalVideo.getGid());
                    generalVideoService.insertVideoPdf(map);
                }

                return new ModelAndView("redirect:/back/backGeneral/math/searchAllMathGeneralList.html");
            }
        } catch (Exception e) {
            session.setAttribute("error", "出错了：" + e.getMessage());
            log.error("新增数学课程视频出错了：", e);
        }

        return new ModelAndView("redirect:/back/backGeneral/math/addMathGeneral.html");
    }

    /**
     * 执行新增 常规科学课程
     */
    @RequestMapping(value = "/back/insertScienceGeneral", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addScienceGeneral(HttpServletRequest req, HttpSession session,
                                          @RequestParam(value = "file1", required = false) MultipartFile[] files, GeneralVideo generalVideo) {
        try {
            if (null != generalVideo) {
                generalVideo.setIsMore(0);
                generalVideo = this.Get_saveGeneralVideo(req, files, generalVideo, "科学");
                generalVideoService.addChineseGeneralVideo(generalVideo);

                List<VideoPdf> pdflist = generalVideo.getPdflist();
                if (pdflist.size() > 0) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("list", pdflist);
                    map.put("vid", generalVideo.getGid());
                    generalVideoService.insertVideoPdf(map);
                }
                return new ModelAndView("redirect:/back/backGeneral/science/searchScienceGeneral");//成功跳转 搜索页
            }
        } catch (Exception e) {
            session.setAttribute("error", "出错了：" + e.getMessage());
            log.error("新增数学课程视频出错了：", e);
        }

        return new ModelAndView("redirect:/back/backGeneral/science/addScienceGeneral");//失败跳转 添加页
    }

    // 常规子视频异步上传
    @RequestMapping("/file/upload")
    @ResponseBody
    public String upload(Long orgId, MultipartFile[] file, Integer type)
            throws IOException {
        String filePath = "";
        String status = "ok";
        try {
            boolean isVideo = type == null ? true : false;
            String dir = isVideo ? "video" : "examImg";
            filePath = upload(file, dir, isVideo);
        } catch (Exception e) {
            status = "异步上传子视频或测验题图片出错:" + e.getMessage();
            log.info("上传子视频出错:", e);
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("vcid", filePath);
        map.put("status", status);
        return JSONObject.toJSON(map).toString();
    }

    /**
     * @param dir     存放视频的文件夹名字
     * @param isVideo
     * @return filePath 文件存放的 最终路径和名字 的字符串
     * @
     */
    private String upload(MultipartFile[] fileList, String dir, boolean isVideo) {
        String filePathOrVcid = "";
        // 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
        /*CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());*/
        // 检查form中是否有enctype="multipart/form-data"
//        if (multipartResolver.isMultipart(request)) {
        if (fileList.length > 0) {
            // 将request变成多部分request
//            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            String rootPath = uploadAbsolutePath + "/" + dir;
//            Iterator iter = multiRequest.getFileNames();
//            while (iter.hasNext()) {
            // 一次遍历所有文件
//                MultipartFile file = multiRequest.getFile(iter.next().toString());
            for (int i = 0; i < fileList.length; i++) {
                MultipartFile file = fileList[i] ;
                if (file != null ) {
                    String suffix = file.getOriginalFilename()
                            .substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                    String name = UUIDUtil.getUUID() + "." + suffix;
                    File gfile = new File(rootPath + "/" + name);
                    /*if (!gfile.exists()) {
                        gfile.mkdirs();
                    }*/
                    // 上传
                    try {
                        file.transferTo(gfile);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if (!gfile.exists()) {
                       throw new RuntimeException("文件未找到,上传失败");
                    }
                    
                    //添加新视频
                    if (isVideo) {
                        VideoChild vc = new VideoChild();
                        vc.setVcid(UUIDUtil.getUUID());
                        vc.setVcideoUrl("/" + dir + "/" + name);
                        vc.setEditDate(new Date());
                        vc.setVctype(0);
                        try {
                            generalVideoService.insertOneVideoChild(vc);
                            filePathOrVcid = vc.getVcid();
                        } catch (Exception e) {
                            log.info("异步上传异常：", e);
                        }
                    } else {
                        filePathOrVcid = "/" + dir + "/" + name;
                    }

                }
            }
        }
        return filePathOrVcid;
    }

    /**
     * 新增常规专题课
     *
     * @param req
     * @param session
     * @param files1       封面
     * @param imgFile      视频对应图片
     * @param files2       子视频id?
     * @param generalVideo
     * @return
     * @
     */
    @RequestMapping(value = "/back/addGeneralMore", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addGeneralMore(HttpServletRequest req, HttpSession session,
                                       @RequestParam(value = "file1", required = false) MultipartFile[] files1,
                                       @RequestParam(value = "imgFile", required = false) MultipartFile[] imgFile,
                                       String[] files2,
                                       GeneralVideo generalVideo) {

        String gsbuject = generalVideo.getGsbuject();
        try {
            if (null != generalVideo) {
                // 父视频添加
                generalVideo.setIsMore(1);
                generalVideo = this.Get_saveGeneralVideoMore(req, files1, generalVideo);
                generalVideo.setGvideoUrl("");
                generalVideoService.addChineseGeneralVideo(generalVideo);

                String[] sortArr = req.getParameterValues("sort");
                /**
                 * 上传视频图片,将路径设置进子视频数据库表中.
                 */
                UploadVideoImg_GeneralVideoMore(req, imgFile, files2, sortArr, generalVideo);

                List<VideoPdf> pdflist = generalVideo.getPdflist();
                if (pdflist.size() > 0) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("list", pdflist);
                    map.put("vid", generalVideo.getGid());
                    generalVideoService.insertVideoPdf(map);
                }

                if (gsbuject.equals("数学")) {
                    return new ModelAndView("redirect:/back/backGeneral/math/searchAllMathGeneralList.html");
                } else if (gsbuject.equals("语文")) {
                    return new ModelAndView("redirect:/back/backGeneral/chinese/generalChineseList.html");
                } else if (gsbuject.equals("英语")) {
                    return new ModelAndView("redirect:/back/backGeneral/english/searchAllEnglishGeneral.html");
                } else if (gsbuject.equals("科学")) {
                    return new ModelAndView("redirect:/back/backGeneral/science/searchScienceGeneral");
                }

            }
        } catch (Exception e) {
            session.setAttribute("error", "出错了:" + e.getMessage());
            log.error("新增常规专题课程视频出错了：", e);
        }
        return new ModelAndView("redirect:/back/backGeneral/addGeneralMore.html?gsbuject=" + gsbuject);
    }

    /**
     * 提交内容更新 进入的控制器.
     *
     * @param req
     * @param session
     * @param files1       新添加的封面图/pdf 文件
     * @param files2       新添加的视频 文件的id
     * @param imgFile      新添加的视频 图片文件
     * @param generalVideo
     * @return
     * @
     */
    @RequestMapping(value = "/back/updateVideoMore", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView updateVideoMore(HttpServletRequest req, HttpSession session,
                                        @RequestParam(value = "file1", required = false) MultipartFile[] files1,
                                        @RequestParam(value = "imgFile", required = false) MultipartFile[] imgFile,
                                        @RequestParam(value = "files2", required = false) String[] files2,
                                        GeneralVideo generalVideo) {
        try {
            //
            generalVideo = this.Get_updateVideoMore(req, files1, generalVideo);/**封面图/pdf文件 数据库设置.*/
            generalVideo.setGvideoUrl("");
            generalVideoService.updateChineseGeneralVideo(generalVideo);
            /**
             * 上传视频图片,将路径设置进子视频数据库表中.
             */
            UploadVideoImg_GeneralVideoMore(req, imgFile, files2, null, generalVideo);

            // 子视频删除
            String[] delVcid = req.getParameterValues("delVcid");
            if (delVcid != null && delVcid.length > 0) {
                generalVideoService.deleteVideoChild(delVcid);
            }
            // 子视频名称修改
            String[] change_vcid = req.getParameterValues("change_vcid");
            if (change_vcid != null) {
                String[] change_vcname = req.getParameterValues("change_vcname");
                List<VideoChild> vclist = new ArrayList<VideoChild>();
                for (int i = 0; i < change_vcid.length; i++) {
                    VideoChild vc = new VideoChild();
                    vc.setVcid(change_vcid[i]);
                    vc.setVcname(change_vcname[i]);
                    vclist.add(vc);
                }
                generalVideoService.updateBatchVideoChildName(vclist);
            }
            //子视频 排序 修改
            String[] change_id = req.getParameterValues("change_id");
            if (change_id != null) {
                String[] change_sort = req.getParameterValues("change_sort");
                List<VideoChild> vclist = new ArrayList<VideoChild>();
                for (int i = 0; i < change_id.length; i++) {
                    VideoChild vc = new VideoChild();
                    vc.setVcid(change_id[i]);
                    vc.setSort(change_sort[i]);
                    vclist.add(vc);
                }
                generalVideoService.updateBatchVideoChildSort(vclist);
            }


            // pdf列表添加
            List<VideoPdf> pdflist = generalVideo.getPdflist();
            if (pdflist.size() > 0) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("list", pdflist);
                map.put("vid", generalVideo.getGid());
                generalVideoService.insertVideoPdf(map);
            }
            // pdf列表删除
            String[] delpdfid = req.getParameterValues("delpdfid");
            if (delpdfid != null && delpdfid.length > 0) {
                generalVideoService.deleteVideoPdf(delpdfid);
            }

            String gsbuject = generalVideo.getGsbuject();
            if (gsbuject.equals("数学")) {
                return new ModelAndView("redirect:/back/backGeneral/math/searchAllMathGeneralList.html");
            } else if (gsbuject.equals("语文")) {
                return new ModelAndView("redirect:/back/backGeneral/chinese/generalChineseList.html");
            } else if (gsbuject.equals("英语")) {
                return new ModelAndView("redirect:/back/backGeneral/english/searchAllEnglishGeneral.html");
            } else if (gsbuject.equals("科学")) {
                return new ModelAndView("redirect:/back/backGeneral/science/searchScienceGeneral");
            }
        } catch (Exception e) {
            session.setAttribute("error", "出错了：" + e.getMessage());
            log.error("修改专题课程视频【id:" + generalVideo.getGid() + "】出错了：", e);
        }
        return new ModelAndView("redirect:/back/backGeneral/updateVideoMore.html?gid=" + generalVideo.getGid());
    }

    // 获取新增的常规专题对象
    private GeneralVideo Get_saveGeneralVideoMore(HttpServletRequest req, MultipartFile[] files2,
                                                  GeneralVideo generalVideo) throws IOException {
        generalVideo.setGid(UUIDUtil.getUUID());
        generalVideo = this.setUploadFiles_GeneralVideoMore(req, files2, generalVideo);
        generalVideo.setGcreatTime(new Date());
        generalVideo.setGplayNo(10);
        String money = req.getParameter("gmoney");
        if (!"".equals(money) && null != money) {
            generalVideo.setGmoney(money);
        } else {
            generalVideo.setGmoney("0");
        }
        String isKill = req.getParameter("isKill");
        if (isKill != null && !"".equals(isKill)) {
            generalVideo.setIsKill(Integer.parseInt(isKill));
        } else {
            generalVideo.setIsKill(0);
        }

        String gsbuject = generalVideo.getGsbuject();
        // 语文数学设置多个年级值
        if (gsbuject.equals("语文") || gsbuject.equals("数学")) {
            String[] gclass = req.getParameterValues("gclass");
            generalVideo.setGclass(BaseUtil.arrayToStr(gclass));
        }
        return generalVideo;
    }

    // 获取编辑的常规对象
    private GeneralVideo Get_updateVideoMore(HttpServletRequest req, MultipartFile[] files1, GeneralVideo generalVideo)
            throws Exception {
        /**
         * 先进行 封面图像,pdf文件上传.
         */
        generalVideo = this.setUploadFiles_GeneralVideoMore(req, files1, generalVideo);

        String videoUrl = generalVideo.getGvideoUrl();
        String fmImgUrl = generalVideo.getGvimg();

        if (fmImgUrl.length() == 0) {
            GeneralVideo generalVideo2 = generalVideoService.findGeneralVideoByVid(generalVideo.getGid());
//			if (fmImgUrl.length() == 0) {/**什么鬼操作???2018年4月17日10:49:32*/
//				generalVideo.setGvimg(generalVideo2.getGvimg());
//			}
            generalVideo.setGvimg(generalVideo2.getGvimg());
            generalVideo.setGplayNo(videoUrl == null || videoUrl.length() == 0 ? generalVideo2.getGplayNo() : 10);
        } else {
            generalVideo.setGplayNo(10);// 设置默认播放次数
        }

        String money = req.getParameter("gmoney");
        if (!"".equals(money) && null != money) {
            generalVideo.setGmoney(money);
        } else {
            generalVideo.setGmoney("0");
        }

        String isKill = req.getParameter("isKill");
        if (isKill != null && !"".equals(isKill)) {
            generalVideo.setIsKill(Integer.parseInt(isKill));
        } else {
            generalVideo.setIsKill(0);
        }

        String gsbuject = generalVideo.getGsbuject();
        // 语文数学设置多个年级值
        if (gsbuject.equals("语文") || gsbuject.equals("数学")) {
            String[] gclass = req.getParameterValues("gclass");
            generalVideo.setGclass(BaseUtil.arrayToStr(gclass));
        }
        generalVideo.setGcreatTime(new Date());
        return generalVideo;
    }

    // 常规专题文件批量上传（封面、pdf）
    private GeneralVideo setUploadFiles_GeneralVideoMore(HttpServletRequest req, MultipartFile[] files,
                                                         GeneralVideo generalVideo) throws IOException {
        List<VideoPdf> pdfList = new ArrayList<VideoPdf>();
        if (files != null && files.length > 0) {
//			String realPath = uploadAbsolutePath;
//			String path = req.getContextPath().substring(1);
            String[] file_dir = new String[]{"fmImg", "pdf"};
            Long sort = new Date().getTime();
            for (int i = 0; i < files.length; i++) {
                String fileName = UUIDUtil.getUUID();
                MultipartFile file = files[i];
                String url = "";
                String name = "";
                if (file.getSize() > 0) {
                    String suffix = file.getOriginalFilename()
                            .substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                    /**此处应该根据后缀名来判断 要上传到的文件夹, 而不是 i的大小,2018年4月17日11:46:30 gd*/
                    name = judge(suffix) ? file.getOriginalFilename() : fileName + "." + suffix;
                    String dir = judge(suffix) ? file_dir[1] : file_dir[0];

                    String realPath_c = uploadAbsolutePath + "/" + dir;
                    file.transferTo(new File(realPath_c, name));
                    url = "/" + dir + "/" + name;
                }
                if (i == 0) {// 封面图片
                    generalVideo.setGvimg(url);
                } else if (i > 0 && url.length() > 0) {// pdf文件 / 或者 是视频图?
                    if (file.getSize() > 0) {//true 为pdf ,false 为 png之类.
                        VideoPdf pdf = new VideoPdf();
                        pdf.setPdfid(UUIDUtil.getUUID());
                        pdf.setPdfUrl(url);
                        pdf.setSort(String.valueOf(sort + i));
                        pdf.setVctype(0);
                        pdfList.add(pdf);
                    }
                }
            }
        }
        generalVideo.setPdflist(pdfList);
        return generalVideo;
    }

    // 常规专题文件批量上传（封面、pdf）
    private void UploadVideoImg_GeneralVideoMore(HttpServletRequest req, MultipartFile[] imgFile, String[] files2, String[] sortArr,
                                                 GeneralVideo generalVideo) throws IOException {
        // 子视频添加
        String[] vcname = req.getParameterValues("vcname");
        String pid = generalVideo.getGid();
        // 子视频批量修改
        Long sort = new Date().getTime();
        List<String> videoImgPath = getVideoImgPath(req, imgFile);
        if (vcname != null && files2 != null && files2.length > 0) {
            List<VideoChild> vclist = new ArrayList<VideoChild>();
            for (int i = 0; i < files2.length; i++) {
                VideoChild vc = new VideoChild();
                vc.setVcid(files2[i]);
                vc.setEditDate(new Date());
                vc.setPid(pid);
                vc.setSort(sortArr == null ? String.valueOf(sort) : sortArr[i]);
//					vc.setSort(String.valueOf(sort + i));
                vc.setVcname(vcname[i]);
                vc.setcImgUrl(i < videoImgPath.size() ? videoImgPath.get(i) : null);
                vclist.add(vc);
            }
            try {
                // 更新.
                generalVideoService.updateBatchVideoChild(vclist);
            } catch (Exception e) {
                log.info("修改专题--批量修改子视频出错：", e);
            }
        }
    }

    private List<String> getVideoImgPath(HttpServletRequest req, MultipartFile[] imgFile) throws IOException {
        List<String> videoImgPath = new LinkedList<>();
        if (imgFile != null && imgFile.length > 0) {
            String file_dir = "fmImg";
            for (int i = 0; i < imgFile.length; i++) {
                String fileName = UUIDUtil.getUUID();
                MultipartFile file = imgFile[i];
                String url = "";
                String name = "";
                if (file.getSize() > 0) {
                    String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                    name = fileName + "." + suffix;
                    String realPath_c = uploadAbsolutePath + "/" + file_dir;
                    file.transferTo(new File(realPath_c, name));
                    url = "/" + file_dir + "/" + name;
                    videoImgPath.add(url);
                }
            }
        }
        return videoImgPath;
    }


    private boolean judge(String suffix) {
        // png ,jpg,jpeg
        if (StringUtils.containsIgnoreCase(suffix, "png") || StringUtils.containsIgnoreCase(suffix, "jpg")
                || StringUtils.containsIgnoreCase(suffix, "jpeg")) {
            return false;
        } else if (StringUtils.containsIgnoreCase(suffix, "pdf")) {
            return true;
        }

        return true;
    }

    /**
     * 删除常规数学
     */
    @RequestMapping("/back/deleteMathGeneralByid")
    public ModelAndView deleteMathGeneralByid(HttpServletRequest req, HttpSession session, String gid, Integer isMore) {
        User user = (User) session.getAttribute("backuser");
        if (null != gid && !"".equals(gid)) {
            try {
                if (null != user) {
                    generalVideoService.deleteGeneralById(gid);
                    if (isMore != null && isMore == 1) {
                        generalVideoService.deleteVideoChildById(gid);
                    }
                    return new ModelAndView("redirect:/back/backGeneral/math/searchAllMathGeneralList.html");
                }
            } catch (Exception e) {
                log.error("删除数学程视频【id:" + gid + "】出错了：", e);
            }
        }
        return new ModelAndView("redirect:/back/backlogin.html");
    }

    /**
     * 删除 常规 科学
     */
    @RequestMapping("/back/deleteScienceGeneralByid")
    public ModelAndView deleteScienceGeneralByid(HttpServletRequest req, HttpSession session, String gid, Integer isMore) {
        User user = (User) session.getAttribute("backuser");
        if (null != gid && !"".equals(gid)) {
            try {
                if (null != user) {
                    generalVideoService.deleteGeneralById(gid);
                    if (isMore != null && isMore == 1) {
                        generalVideoService.deleteVideoChildById(gid);
                    }
                }
            } catch (Exception e) {
                log.error("删除数学程视频【id:" + gid + "】出错了：", e);
            }
        }
        return new ModelAndView("redirect:/back/backGeneral/science/searchScienceGeneral");
    }

    // 修改常规数学课程
    @RequestMapping(value = "/back/updateMathGeneral", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView updateMathGeneral(HttpServletRequest req, HttpSession session,
                                          @RequestParam(value = "file1", required = false) MultipartFile[] files, GeneralVideo generalVideo) {
        try {
            generalVideo = this.Get_updateGeneralVideo(req, files, generalVideo);
            generalVideoService.updateChineseGeneralVideo(generalVideo);

            List<VideoPdf> pdflist = generalVideo.getPdflist();
            if (pdflist.size() > 0) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("list", pdflist);
                map.put("vid", generalVideo.getGid());
                generalVideoService.insertVideoPdf(map);
            }

            String[] delpdfid = req.getParameterValues("delpdfid");
            if (delpdfid != null && delpdfid.length > 0) {
                generalVideoService.deleteVideoPdf(delpdfid);
            }
            return new ModelAndView("redirect:/back/backGeneral/math/searchAllMathGeneralList.html");
        } catch (Exception e) {
            session.setAttribute("error", "出错了：" + e.getMessage());
            log.error("修改数学课程视频【id:" + generalVideo.getGid() + "】出错了：", e);
        }
        return new ModelAndView("redirect:/back/backGeneral/math/updateMathGeneral.html?gid=" + generalVideo.getGid());
    }

    // 执行修改 -常规科学课程
    @RequestMapping(value = "/back/updateScienceGeneral", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView updateScienceGeneral(HttpServletRequest req, HttpSession session,
                                             @RequestParam(value = "file1", required = false) MultipartFile[] files, GeneralVideo generalVideo) {
        try {
            generalVideo = this.Get_updateGeneralVideo(req, files, generalVideo);
            generalVideoService.updateChineseGeneralVideo(generalVideo);

            List<VideoPdf> pdflist = generalVideo.getPdflist();
            if (pdflist.size() > 0) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("list", pdflist);
                map.put("vid", generalVideo.getGid());
                generalVideoService.insertVideoPdf(map);
            }

            String[] delpdfid = req.getParameterValues("delpdfid");
            if (delpdfid != null && delpdfid.length > 0) {
                generalVideoService.deleteVideoPdf(delpdfid);
            }
            return new ModelAndView("redirect:/back/backGeneral/science/searchScienceGeneral");
        } catch (Exception e) {
            session.setAttribute("error", "出错了：" + e.getMessage());
            log.error("修改数学课程视频【id:" + generalVideo.getGid() + "】出错了：", e);
        }
        return new ModelAndView("redirect:/back/backGeneral/science/updateScienceGeneral?gid=" + generalVideo.getGid());
    }


    /**
     * 常规课程 英语
     */
    @RequestMapping("/back/backGeneral/english/searchAllEnglishGeneral.html")
    public void searchAllEnglishGeneral(HttpServletRequest req, HttpSession session, Model model) {
        Map<String, String> maps = new HashMap<String, String>();
        Page<GeneralVideo> pages = null;
        try {
            int pageNo = 0;
            int pageSize = 20;
            if (req.getParameter("pageNo") != null && StringUtil.isNumeric(req.getParameter("pageNo"))) {
                pageNo = Integer.parseInt(req.getParameter("pageNo"));
            }
            if (pageNo < 1) {
                pageNo = 1;
            }
            String gclass = req.getParameter("gclass");
            String gclassify = req.getParameter("gclassify");

            maps.put("gsbuject", "英语");
            if (!"".equals(gclass) && null != gclass) {
                // maps.put("gclass",new
                // String(gclass.getBytes("iso8859-1"),"utf-8"));
                maps.put("gclass", gclass);
            }
            // 分类 如：第一册 第二册
            if (!"".equals(gclassify) && null != gclassify) {
                // maps.put("gclassify",new
                // String(gclassify.getBytes("iso8859-1"),"utf-8"));
                maps.put("gclassify", gclassify);
            }
            pages = generalVideoService.searchGeneralVideoList(maps, pageNo, pageSize);
        } catch (Exception e) {
            log.error("查询常规英语出错了：", e);
            pages = new Page<GeneralVideo>();
            pages.setResult(new ArrayList<GeneralVideo>());
        }
        model.addAttribute("pages", pages);
        model.addAttribute("map", maps);
    }

    /**
     * 新增常规英语课程
     */
    @RequestMapping("/back/backGeneral/english/addEnglishGeneral.html")
    public void toAddEnglishGeneral(HttpServletRequest req, HttpSession session, Model mode) {
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
        List<Teacher> tclist = teacherService.findAllTeacher(null);
        mode.addAttribute("tclist", tclist);
    }

    // 新增常规英语课程
    @RequestMapping(value = "/back/english/insertEnglishGeneral", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addEnglishGeneral(HttpServletRequest req, HttpSession session,
                                          @RequestParam(value = "file1", required = false) MultipartFile[] files, GeneralVideo generalVideo) {
        try {
            if (null != generalVideo) {
                generalVideo.setIsMore(0);
                generalVideo = this.Get_saveGeneralVideo(req, files, generalVideo, "英语");
                generalVideoService.addChineseGeneralVideo(generalVideo);

                List<VideoPdf> pdflist = generalVideo.getPdflist();
                if (pdflist.size() > 0) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("list", pdflist);
                    map.put("vid", generalVideo.getGid());
                    generalVideoService.insertVideoPdf(map);
                }
                return new ModelAndView("redirect:/back/backGeneral/english/searchAllEnglishGeneral.html");
            }
        } catch (Exception e) {
            session.setAttribute("error", "出错了：" + e.getMessage());
            log.error("新增英语课程视频出错了：", e);
        }
        return new ModelAndView("redirect:/back/backGeneral/english/addEnglishGeneral.html");
    }

    /**
     * to修改语文课程视频页面
     */
    @RequestMapping("/back/backGeneral/chinese/updateChineseGeneralVideo.html")
    public void toUpdateChineseGeneralVideo(HttpServletRequest req, HttpSession session, String gid, Model mode) {
        GeneralVideo generalVideo = null;
        if (gid != null && !"".equals(gid)) {
            try {
                generalVideo = generalVideoService.findGeneralVideoByVid(gid);
                try {
                    generalVideo.setPdflist(contestVideoService.selectPdfByVid(gid, "0"));
                } catch (Exception e) {
                    session.setAttribute("error", "查询pdf数据出错了");
                    log.error("修改跳转语文课程视频pdf集合查询【id:" + gid + "】出错了：", e);
                }

            } catch (Exception e) {
                session.setAttribute("error", "查询数据出错了");
                log.error("修改跳转语文课程视频查询【id:" + gid + "】出错了：", e);
            }
        } else {
            generalVideo = new GeneralVideo();
            generalVideo.setPdflist(new ArrayList<VideoPdf>());
        }
        List<Teacher> tclist = teacherService.findAllTeacher(null);
        mode.addAttribute("tclist", tclist);
        mode.addAttribute("generalVideo", generalVideo);
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
    }

    /**
     * 修改常规专题课程
     */
    @RequestMapping("/back/backGeneral/updateVideoMore.html")
    public void updateVideoMore(HttpServletRequest req, HttpSession session, String gid, Model mode) {
        GeneralVideo generalVideo = null;
        if (gid != null && !"".equals(gid)) {
            try {
                generalVideo = generalVideoService.findGeneralVideoByVid(gid);
                try {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("pid", gid);
                    generalVideo.setVclist(generalVideoService.selectAllChildVideo(map));
                    generalVideo.setPdflist(contestVideoService.selectPdfByVid(gid, "0"));
                } catch (Exception e) {
                    log.error("修改专题课程视频pdf或子视频集合查询【id:" + gid + "】出错了：", e);
                }
                if (!StringUtils.isEmpty(generalVideo.getWeekVal())) {
                    String[] arr = generalVideo.getWeekVal().split("-");
                    mode.addAttribute("week", arr[0]);
                    String[] numArr = arr[1].split("");
                    mode.addAttribute("timeNum", numArr[0].equals("0") ? numArr[1] : arr[1]);
                }
            } catch (Exception e) {
                log.error("修改专题课程视频查询【id:" + gid + "】出错了：", e);
            }

        } else {
            generalVideo = new GeneralVideo();
            generalVideo.setPdflist(new ArrayList<VideoPdf>());
            generalVideo.setVclist(new ArrayList<VideoChild>());
        }
        List<Teacher> tclist = teacherService.findAllTeacher(null);
        mode.addAttribute("tclist", tclist);
        mode.addAttribute("generalVideo", generalVideo);
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
    }

    /**
     * 跳转 修改常规数学课程 页面
     */
    @RequestMapping("/back/backGeneral/math/updateMathGeneral.html")
    public void toUpdateMathGeneral(HttpServletRequest req, HttpSession session, String gid, Model mode) {
        GeneralVideo generalVideo = null;
        if (gid != null && !"".equals(gid)) {
            try {
                generalVideo = generalVideoService.findGeneralVideoByVid(gid);
                try {
                    generalVideo.setPdflist(contestVideoService.selectPdfByVid(gid, "0"));
                } catch (Exception e) {
                    log.error("查询常规数学课pdf集合出错：", e);
                }
            } catch (Exception e) {
                log.error("查询常规数学课出错：", e);
            }
        } else {
            generalVideo = new GeneralVideo();
            generalVideo.setPdflist(new ArrayList<VideoPdf>());
        }
        List<Teacher> tclist = teacherService.findAllTeacher(null);
        mode.addAttribute("tclist", tclist);
        mode.addAttribute("generalVideo", generalVideo);
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
    }

    /**
     * 跳转 修改 常规 科学 页面
     */
    @RequestMapping("/back/backGeneral/science/updateScienceGeneral")
    public void toPage_UpdateScienceGeneral(HttpServletRequest req, HttpSession session, String gid, Model mode) {
        GeneralVideo generalVideo = null;
        if (gid != null && !"".equals(gid)) {
            try {
                generalVideo = generalVideoService.findGeneralVideoByVid(gid);
                try {
                    generalVideo.setPdflist(contestVideoService.selectPdfByVid(gid, "0"));
                } catch (Exception e) {
                    log.error("查询常规数学课pdf集合出错：", e);
                }
            } catch (Exception e) {
                log.error("查询常规数学课出错：", e);
            }
        } else {
            generalVideo = new GeneralVideo();
            generalVideo.setPdflist(new ArrayList<VideoPdf>());
        }
        List<Teacher> tclist = teacherService.findAllTeacher(null);
        mode.addAttribute("tclist", tclist);
        mode.addAttribute("generalVideo", generalVideo);
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
    }

    /**
     * 修改常规英语课程
     */
    @RequestMapping("/back/backGeneral/english/upEnglishGeneral.html")
    public void toUpdateEnglishGeneral(HttpServletRequest req, HttpSession session, String gid, Model mode) {
        GeneralVideo generalVideo = null;
        if (gid != null && !"".equals(gid)) {
            try {
                generalVideo = generalVideoService.findGeneralVideoByVid(gid);
                try {
                    generalVideo.setPdflist(contestVideoService.selectPdfByVid(gid, "0"));
                } catch (Exception e) {
                    log.error("修改常规英语课程查询pdf出错了：", e);
                }
            } catch (Exception e) {
                log.error("修改常规英语课程查询出错了：", e);
            }
        } else {
            generalVideo = new GeneralVideo();
            generalVideo.setPdflist(new ArrayList<VideoPdf>());
        }
        List<Teacher> tclist = teacherService.findAllTeacher(null);
        mode.addAttribute("tclist", tclist);
        mode.addAttribute("generalVideo", generalVideo);
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
    }

    @RequestMapping(value = "/back/updateEnglishGeneral", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView updateEnglishGeneral(HttpServletRequest req, HttpSession session,
                                             @RequestParam(value = "file1", required = false) MultipartFile[] files, GeneralVideo generalVideo) {
        try {
            generalVideo = this.Get_updateGeneralVideo(req, files, generalVideo);
            generalVideoService.updateChineseGeneralVideo(generalVideo);

            List<VideoPdf> pdflist = generalVideo.getPdflist();
            if (pdflist.size() > 0) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("list", pdflist);
                map.put("vid", generalVideo.getGid());
                generalVideoService.insertVideoPdf(map);
            }

            String[] delpdfid = req.getParameterValues("delpdfid");
            if (delpdfid != null && delpdfid.length > 0) {
                generalVideoService.deleteVideoPdf(delpdfid);
            }

            return new ModelAndView("redirect:/back/backGeneral/english/searchAllEnglishGeneral.html");
        } catch (Exception e) {
            session.setAttribute("error", "出错了：" + e.getMessage());
            log.error("修改英语课程视频【id:" + generalVideo.getGid() + "】出错了：", e);
        }
        return new ModelAndView(
                "redirect:/back/backGeneral/english/upEnglishGeneral.html?gid=" + generalVideo.getGid());
    }


    @RequestMapping("/back/deleteEnglishGeneral")
    public ModelAndView deleteEnglishGeneralByid(HttpServletRequest req, HttpSession session, String gid,
                                                 Integer isMore) {
        User user = (User) session.getAttribute("backuser");
        if (null != gid && !"".equals(gid)) {
            if (null != user) {
                try {
                    generalVideoService.deleteGeneralById(gid);
                    if (isMore != null && isMore == 1) {
                        generalVideoService.deleteVideoChildById(gid);
                    }
                    return new ModelAndView("redirect:/back/backGeneral/english/searchAllEnglishGeneral.html");
                } catch (Exception e) {
                    log.error("删除英语课程视频【id:" + gid + "】出错了：", e);
                }
            }
        }
        return new ModelAndView("redirect:/back/backlogin.html");
    }

    /**
     * 美国大联盟杯
     */
    @RequestMapping("/back/backContest/america/backUSAclass.html")
    public void tobackUsaClass(HttpServletRequest req, HttpSession session, Model model) {
        Page<ContestVideo> pages = null;
        Map<String, String> maps = new HashMap<String, String>();
        try {
            int pageNo = 0;
            int pageSize = 20;
            if (req.getParameter("pageNo") != null && StringUtil.isNumeric(req.getParameter("pageNo"))) {
                pageNo = Integer.parseInt(req.getParameter("pageNo"));
            }
            if (pageNo < 1) {
                pageNo = 1;
            }
            String cclass = req.getParameter("cclass");
            String cclassify = req.getParameter("cclassify");

            maps.put("competitionName", "美国大联盟杯");
            if (!"".equals(cclass) && null != cclass) {
                // maps.put("cclass",new
                // String(cclass.getBytes("iso8859-1"),"utf-8"));
                maps.put("cclass", cclass);
            }
            // 班级下分类 如：专题课 真题课
            if (!"".equals(cclassify) && null != cclassify) {
                // maps.put("cclassify",new
                // String(cclassify.getBytes("iso8859-1"),"utf-8"));
                maps.put("cclassify", cclassify);
            }
            pages = contestVideoService.searchContestVideoList(maps, pageNo, pageSize);
        } catch (Exception e) {
            log.error("查询 美国大联盟杯出错了：", e);
            pages = new Page<ContestVideo>();
            pages.setResult(new ArrayList<ContestVideo>());
        }
        model.addAttribute("pages", pages);
        model.addAttribute("map", maps);
    }

    /**
     * 新增美国大联盟杯课程
     */
    @RequestMapping("/back/backContest/america/toAddUSAClass.html")
    public void toAddUSAClass(HttpServletRequest req, HttpSession session, Model mode) {
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
        List<Teacher> tclist = teacherService.findAllTeacher(null);
        mode.addAttribute("tclist", tclist);
    }

    @RequestMapping(value = "/back/america/addUSAClass", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addUSAClass(HttpServletRequest req, HttpSession session,
                                    @RequestParam(value = "file1", required = false) MultipartFile[] files, ContestVideo contestVideo) {
        try {
            if (null != contestVideo) {
                contestVideo.setIsMore(0);
                contestVideo = this.Get_saveContestVideo(req, files, contestVideo, "美国大联盟杯");
                contestVideoService.addUSAClass(contestVideo);

                List<VideoPdf> pdflist = contestVideo.getPdflist();
                if (pdflist.size() > 0) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("list", pdflist);
                    map.put("vid", contestVideo.getCid());
                    generalVideoService.insertVideoPdf(map);
                }
                return new ModelAndView("redirect:/back/backContest/america/backUSAclass.html");
            }
        } catch (Exception e) {
            session.setAttribute("error", "出错了：" + e.getMessage());
            log.error("新增美国大联盟杯出错了：", e);
        }
        return new ModelAndView("redirect:/back/backContest/america/toAddUSAClass.html");
    }

    /**
     * 修改美国大联盟杯
     */
    @RequestMapping("/back/backContest/america/toUpdateUSAClass.html")
    public void toUpdateUSAClass(HttpServletRequest req, HttpSession session, Model mode, String cid) {
        ContestVideo contestVideo = null;
        if (cid != null && !"".equals(cid)) {
            try {
                contestVideo = contestVideoService.findContestVideoById(cid);
                try {
                    contestVideo.setPdflist(contestVideoService.selectPdfByVid(cid, "1"));
                } catch (Exception e) {
                    log.error("修改美国大联盟杯查询pdfg对象【id:" + cid + "】出错了：", e);
                }
            } catch (Exception e) {
                log.error("修改美国大联盟杯查询对象【id:" + cid + "】出错了：", e);
            }
        } else {
            contestVideo = new ContestVideo();
            contestVideo.setPdflist(new ArrayList<VideoPdf>());
        }
        List<Teacher> tclist = teacherService.findAllTeacher(null);
        mode.addAttribute("tclist", tclist);
        mode.addAttribute("contestVideo", contestVideo);
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
    }

    @RequestMapping(value = "/back/updateUSAClass", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView updateUSAClass(HttpServletRequest req, HttpSession session,
                                       @RequestParam(value = "file1", required = false) MultipartFile[] files, ContestVideo contestVideo) {
        try {
            contestVideo = this.Get_updateContestVideo(req, files, contestVideo);
            contestVideoService.updateUSAClass(contestVideo);
            List<VideoPdf> pdflist = contestVideo.getPdflist();
            if (pdflist.size() > 0) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("list", pdflist);
                map.put("vid", contestVideo.getCid());
                generalVideoService.insertVideoPdf(map);
            }

            String[] delpdfid = req.getParameterValues("delpdfid");
            if (delpdfid != null && delpdfid.length > 0) {
                generalVideoService.deleteVideoPdf(delpdfid);
            }

            return new ModelAndView("redirect:/back/backContest/america/backUSAclass.html");
        } catch (Exception e) {
            session.setAttribute("error", "出错了：" + e.getMessage());
            log.error("修改美国大联盟杯【id:" + contestVideo.getCid() + "】出错了：", e);

        }
        return new ModelAndView(
                "redirect:/back/backContest/america/toUpdateUSAClass.html?cid=" + contestVideo.getCid());
    }


    /**
     * 删除竞赛课程
     */
    @RequestMapping("/back/deleteUSAClass")
    public ModelAndView deleteUSACLass(HttpServletRequest req, HttpSession session, String cid) {
        User user = (User) session.getAttribute("backuser");
        if (cid != null && !"".equals(cid)) {
            if (null != user) {
                try {
                    contestVideoService.deleteUSAClass(cid);
                } catch (Exception e) {
                    log.error("删除美国大联盟杯【id:" + cid + "】出错了：", e);
                }
                return new ModelAndView("redirect:/back/backContest/america/backUSAclass.html");
            }
        }
        return new ModelAndView("redirect:/back/backlogin.html");
    }

    /**
     * 迎春杯
     */
    @RequestMapping("/back/backContest/backSpring/backSpringClass.html")
    public void tobackSpringClass(HttpServletRequest req, HttpSession session, Model model) {
        Page<ContestVideo> pages = null;
        Map<String, String> maps = new HashMap<String, String>();
        try {
            int pageNo = 0;
            int pageSize = 20;
            if (req.getParameter("pageNo") != null && StringUtil.isNumeric(req.getParameter("pageNo"))) {
                pageNo = Integer.parseInt(req.getParameter("pageNo"));
            }
            if (pageNo < 1) {
                pageNo = 1;
            }
            String cclass = req.getParameter("cclass");
            String cclassify = req.getParameter("cclassify");

            maps.put("competitionName", "迎春杯");
            if (!"".equals(cclass) && null != cclass) {
                // maps.put("cclass",new
                // String(cclass.getBytes("iso8859-1"),"utf-8"));
                maps.put("cclass", cclass);
            }
            // 班级下分类 如：专题课 真题课
            if (!"".equals(cclassify) && null != cclassify) {
                // maps.put("cclassify",new
                // String(cclassify.getBytes("iso8859-1"),"utf-8"));
                maps.put("cclassify", cclassify);
            }
            pages = contestVideoService.searchContestVideoList(maps, pageNo, pageSize);
        } catch (Exception e) {
            log.error("查询 迎春杯出错了：", e);
            pages = new Page<ContestVideo>();
            pages.setResult(new ArrayList<ContestVideo>());
        }
        model.addAttribute("pages", pages);
        model.addAttribute("map", maps);
    }

    /**
     * 删除迎春杯课程
     */
    @RequestMapping("/back/deleteSpringCLass")
    public ModelAndView deleteSpringCLass(HttpServletRequest req, HttpSession session, String cid) {
        User user = (User) session.getAttribute("backuser");
        if (cid != null && !"".equals(cid)) {
            if (null != user) {
                try {
                    contestVideoService.deleteUSAClass(cid);
                } catch (Exception e) {
                    log.error("删除迎春杯【id:" + cid + "】出错了：", e);
                }
                return new ModelAndView("redirect:/back/backContest/backSpring/backSpringClass.html");
            }
        }
        return new ModelAndView("redirect:/back/backlogin.html");
    }

    /**
     * 新增迎春杯课程
     */
    @RequestMapping("/back/backContest/backSpring/toAddSpringClass.html")
    public void toAddSpringClass(HttpServletRequest req, HttpSession session, Model mode) {

        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
        List<Teacher> tclist = teacherService.findAllTeacher(null);
        mode.addAttribute("tclist", tclist);
    }

    @RequestMapping(value = "/back/backSpring/addSpringClass", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addSpringClass(HttpServletRequest req, HttpSession session,
                                       @RequestParam(value = "file1", required = false) MultipartFile[] files, ContestVideo contestVideo) {
        try {
            if (null != contestVideo) {
                contestVideo.setIsMore(0);
                contestVideo = this.Get_saveContestVideo(req, files, contestVideo, "迎春杯");
                contestVideoService.addUSAClass(contestVideo);

                List<VideoPdf> pdflist = contestVideo.getPdflist();
                if (pdflist.size() > 0) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("list", pdflist);
                    map.put("vid", contestVideo.getCid());
                    generalVideoService.insertVideoPdf(map);
                }

                return new ModelAndView("redirect:/back/backContest/backSpring/backSpringClass.html");
            }
        } catch (Exception e) {
            session.setAttribute("error", "出错了：" + e.getMessage());
            log.error("新增迎春杯出错了：", e);
        }
        return new ModelAndView("redirect:/back/backContest/backSpring/toAddSpringClass.html");
    }


    /**
     * 修改迎春杯
     */
    @RequestMapping("/back/backContest/backSpring/toUpdateSpringClass.html")
    public void toUpdateSpringClass(HttpServletRequest req, HttpSession session, Model mode, String cid) {
        ContestVideo contestVideo = null;
        if (cid != null && !"".equals(cid)) {
            try {
                contestVideo = contestVideoService.findContestVideoById(cid);
                try {
                    contestVideo.setPdflist(contestVideoService.selectPdfByVid(cid, "1"));
                } catch (Exception e) {
                    log.error("修改迎春杯查询对象pdf【id:" + cid + "】出错了：", e);
                }
            } catch (Exception e) {
                log.error("修改迎春杯查询对象【id:" + cid + "】出错了：", e);
            }
        } else {
            contestVideo = new ContestVideo();
            contestVideo.setPdflist(new ArrayList<VideoPdf>());
        }
        List<Teacher> tclist = teacherService.findAllTeacher(null);
        mode.addAttribute("tclist", tclist);
        mode.addAttribute("contestVideo", contestVideo);
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
    }

    @RequestMapping(value = "/back/updateSpringClass", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView updateSpringClass(HttpServletRequest req, HttpSession session,
                                          @RequestParam(value = "file1", required = false) MultipartFile[] files, ContestVideo contestVideo) {
        try {
            contestVideo = this.Get_updateContestVideo(req, files, contestVideo);
            contestVideoService.updateUSAClass(contestVideo);

            List<VideoPdf> pdflist = contestVideo.getPdflist();
            if (pdflist.size() > 0) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("list", pdflist);
                map.put("vid", contestVideo.getCid());
                generalVideoService.insertVideoPdf(map);
            }

            String[] delpdfid = req.getParameterValues("delpdfid");
            if (delpdfid != null && delpdfid.length > 0) {
                generalVideoService.deleteVideoPdf(delpdfid);
            }

            return new ModelAndView("redirect:/back/backContest/backSpring/backSpringClass.html");
        } catch (Exception e) {
            session.setAttribute("error", "出错了：" + e.getMessage());
            log.error("修改迎春杯【id:" + contestVideo.getCid() + "】出错了：", e);
        }
        return new ModelAndView(
                "redirect:/back/backContest/backSpring/toUpdateSpringClass.html?cid=" + contestVideo.getCid());
    }


    /**
     * 华杯
     */
    @RequestMapping("/back/backContest/hua/backHuaClass.html")
    public void tobackHuaClass(HttpServletRequest req, HttpSession session, Model model) {
        Page<ContestVideo> pages = null;
        Map<String, String> maps = new HashMap<String, String>();
        try {
            int pageNo = 0;
            int pageSize = 20;
            if (req.getParameter("pageNo") != null && StringUtil.isNumeric(req.getParameter("pageNo"))) {
                pageNo = Integer.parseInt(req.getParameter("pageNo"));
            }
            if (pageNo < 1) {
                pageNo = 1;
            }
            String cclass = req.getParameter("cclass");
            String cclassify = req.getParameter("cclassify");

            maps.put("competitionName", "华杯");
            if (!"".equals(cclass) && null != cclass) {
                // maps.put("cclass",new
                // String(cclass.getBytes("iso8859-1"),"utf-8"));
                maps.put("cclass", cclass);
            }
            // 班级下分类 如：专题课 真题课
            if (!"".equals(cclassify) && null != cclassify) {
                // maps.put("cclassify",new
                // String(cclassify.getBytes("iso8859-1"),"utf-8"));
                maps.put("cclassify", cclassify);
            }
            pages = contestVideoService.searchContestVideoList(maps, pageNo, pageSize);
        } catch (Exception e) {
            pages = new Page<ContestVideo>();
            pages.setResult(new ArrayList<ContestVideo>());
            log.error("查询华杯出错了：", e);
        }
        model.addAttribute("pages", pages);
        model.addAttribute("map", maps);
    }

    /**
     * 删除华杯课程
     */
    @RequestMapping("/back/deleteHuaCLass")
    public ModelAndView deleteHuaCLass(HttpServletRequest req, HttpSession session, String cid) {
        User user = (User) session.getAttribute("backuser");
        if (!"".equals(cid) && null != cid) {
            if (null != user) {
                try {
                    contestVideoService.deleteUSAClass(cid);
                } catch (Exception e) {
                    log.error("删除 华杯【id:" + cid + "】出错了：", e);
                }
                return new ModelAndView("redirect:/back/backContest/hua/backHuaClass.html");
            }
        }
        return new ModelAndView("redirect:/back/backlogin.html");
    }

    /**
     * 新增华杯课程
     */
    @RequestMapping("/back/backContest/hua/toAddHuaClass.html")
    public void toAddHuaClass(HttpServletRequest req, HttpSession session, Model mode) {
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
        List<Teacher> tclist = teacherService.findAllTeacher(null);
        mode.addAttribute("tclist", tclist);
    }

    @RequestMapping(value = "/back/hua/addHuaClass", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addHuaClass(HttpServletRequest req, HttpSession session,
                                    @RequestParam(value = "file1", required = false) MultipartFile[] files, ContestVideo contestVideo) {
        try {
            if (null != contestVideo) {
                contestVideo.setIsMore(0);
                contestVideo = this.Get_saveContestVideo(req, files, contestVideo, "华杯");
                contestVideoService.addUSAClass(contestVideo);

                List<VideoPdf> pdflist = contestVideo.getPdflist();
                if (pdflist.size() > 0) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("list", pdflist);
                    map.put("vid", contestVideo.getCid());
                    generalVideoService.insertVideoPdf(map);
                }
                return new ModelAndView("redirect:/back/backContest/hua/backHuaClass.html");
            }
        } catch (Exception e) {
            session.setAttribute("error", "出错了：" + e.getMessage());
            log.error("新增华杯出错了：", e);
        }
        return new ModelAndView("redirect:/back/backContest/hua/toAddHuaClass.html");
    }


    /**
     * 修改华杯
     */
    @RequestMapping("/back/backContest/hua/toUpdateHuaClass.html")
    public void toUpdateHuaClass(HttpServletRequest req, HttpSession session, Model mode, String cid) {
        ContestVideo contestVideo = null;
        if (cid != null && !"".equals(cid)) {
            try {
                contestVideo = contestVideoService.findContestVideoById(cid);
                try {
                    contestVideo.setPdflist(contestVideoService.selectPdfByVid(cid, "1"));
                } catch (Exception e) {
                    log.error("修改华杯查询对象pdf【id:" + cid + "】出错了：", e);
                }
            } catch (Exception e) {
                log.error("修改华杯查询对象【id:" + cid + "】出错了：", e);
            }
        } else {
            contestVideo = new ContestVideo();
            contestVideo.setPdflist(new ArrayList<VideoPdf>());
        }
        mode.addAttribute("contestVideo", contestVideo);
        List<Teacher> tclist = teacherService.findAllTeacher(null);
        mode.addAttribute("tclist", tclist);
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
    }

    @RequestMapping(value = "/back/updateHuaClass", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView updateHuaClass(HttpServletRequest req, HttpSession session,
                                       @RequestParam(value = "file1", required = false) MultipartFile[] files, ContestVideo contestVideo) {
        try {
            contestVideo = this.Get_updateContestVideo(req, files, contestVideo);
            contestVideoService.updateUSAClass(contestVideo);

            List<VideoPdf> pdflist = contestVideo.getPdflist();
            if (pdflist.size() > 0) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("list", pdflist);
                map.put("vid", contestVideo.getCid());
                generalVideoService.insertVideoPdf(map);
            }

            String[] delpdfid = req.getParameterValues("delpdfid");
            if (delpdfid != null && delpdfid.length > 0) {
                generalVideoService.deleteVideoPdf(delpdfid);
            }

            return new ModelAndView("redirect:/back/backContest/hua/backHuaClass.html");
        } catch (Exception e) {
            session.setAttribute("error", "出错了：" + e.getMessage());
            log.error("修改华杯【id:" + contestVideo.getCid() + "】出错了：", e);
        }
        return new ModelAndView("redirect:/back/backContest/hua/toUpdateHuaClass.html?cid=" + contestVideo.getCid());
    }


    /**
     * 语文测验
     */
    @RequestMapping("/back/exam/chinese/chineseExam.html")
    public void backExam(HttpServletRequest req, HttpSession session, Model model) {
        Page<Exam> pages = null;
        Map<String, String> maps = new HashMap<String, String>();
        try {
            int pageNo = 0;
            int pageSize = 20;
            if (req.getParameter("pageNo") != null && StringUtil.isNumeric(req.getParameter("pageNo"))) {
                pageNo = Integer.parseInt(req.getParameter("pageNo"));
            }
            if (pageNo < 1) {
                pageNo = 1;
            }
            String etclass = req.getParameter("etclass");

            String etclassify = req.getParameter("etclassify");

            maps.put("etsubject", "语文测验");
            if (!"".equals(etclass) && null != etclass) {
                // maps.put("etclass",new
                // String(etclass.getBytes("iso8859-1"),"utf-8"));
                maps.put("etclass", etclass);
            }
            // 班级下分类 如：专题课 真题课
            if (!"".equals(etclassify) && null != etclassify) {
                // maps.put("etclassify",new
                // String(etclassify.getBytes("iso8859-1"),"utf-8"));
                maps.put("etclassify", etclassify);
            }
            pages = examService.findExamListByMap(maps, pageNo, pageSize);
        } catch (Exception e) {
            log.error("查询 语文测验出错了：", e);
            pages = new Page<Exam>();
            pages.setResult(new ArrayList<Exam>());
        }
        model.addAttribute("pages", pages);
        model.addAttribute("map", maps);
    }

    // -------------------------------------综合语数外测验--start

    /**
     * 新增测验（语数外）
     */
    @RequestMapping("/back/exam/toAddExam.html")
    public void toAddExamAll(HttpServletRequest req, HttpSession session, Model mode, String subject) {
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
        List<Teacher> tclist = teacherService.findAllTeacher(null);
        mode.addAttribute("tclist", tclist);
        mode.addAttribute("subject", subject);
    }

    // 新增测验
    @RequestMapping("/back/addExamClass")
    public ModelAndView addExamClass(HttpServletRequest req, HttpSession session,
                                     @RequestParam(value = "fm", required = false) MultipartFile fm, Exam exam) {
        String subject = exam.getEtsubject();
        try {
            // 1.新增测验题
            exam = getexam2(req, exam, fm);
            examService.addExam(exam);
            // 3.跳转回列表页面
            if (exam.getEtsubject().equals("语文测验")) {
                return new ModelAndView("redirect:/back/exam/chinese/chineseExam.html");
            } else if (exam.getEtsubject().equals("数学测验")) {
                return new ModelAndView("redirect:/back/exam/math/mathExam.html");
            } else {
                return new ModelAndView("redirect:/back/exam/english/englishExam.html");
            }
        } catch (Exception e) {
            session.setAttribute("error", "出错了：" + e.getMessage());
            log.error("新增" + subject + "出错了：", e);
            return new ModelAndView("redirect:/back/exam/chinese/toAddExam.html?subject=" + subject);
        }

    }

    // 修改测验
    @RequestMapping(value = "/back/updateExamClass", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView updateExamClass(HttpServletRequest req, HttpSession session,
                                        @RequestParam(value = "fm", required = false) MultipartFile fm, Exam exam) {
        try {
            // 1.修改测验题
            exam = getexam2(req, exam, fm);
            examService.updateExam(exam);
            // 3.跳转回列表页面
            if (exam.getEtsubject().equals("语文测验")) {
                return new ModelAndView("redirect:/back/exam/chinese/chineseExam.html");
            } else if (exam.getEtsubject().equals("数学测验")) {
                return new ModelAndView("redirect:/back/exam/math/mathExam.html");
            } else {
                return new ModelAndView("redirect:/back/exam/english/englishExam.html");
            }
        } catch (Exception e) {
            session.setAttribute("error", "出错了：" + e.getMessage());
            log.error("修改" + exam.getEtsubject() + "【id:" + exam.getEtid() + "】出错了：", e);
            return new ModelAndView("redirect:/back/exam/updateExam.html?gid=" + exam.getEtid());
        }

    }

    /**
     * 修改测验跳转
     */
    @RequestMapping("/back/exam/updateExam.html")
    public void updateExamAll(HttpServletRequest req, HttpSession session, Model mode, String gid) {
        if (gid != null && !"".equals(gid)) {
            try {
                Exam exam = examService.findExamByEtid(gid);
                mode.addAttribute("exam", exam);
            } catch (Exception e) {
                log.error("修改测验查询对象【id:" + gid + "】出错了：", e);
            }
        }
        List<Teacher> tclist = teacherService.findAllTeacher(null);
        mode.addAttribute("tclist", tclist);
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
    }

    private Exam getexam2(HttpServletRequest req, Exam exam, MultipartFile fm) throws IOException {
        String realPath = uploadAbsolutePath;
        String path = req.getContextPath().substring(1);
        realPath = uploadAbsolutePath + "/" + "examImg";
//		realPath = realPath.replace(path, "examImg");
        String id = exam.getEtid() == null || exam.getEtid().length() == 0 ? UUIDUtil.getUUID() : exam.getEtid();
        if (fm != null) {
            if (fm.getSize() > 0) {
                String fileName = UUIDUtil.getUUID();
                String suffix = fm.getOriginalFilename().substring(fm.getOriginalFilename().lastIndexOf(".") + 1);
                fm.transferTo(new File(realPath, fileName + "." + suffix));
                exam.setFmImg("/examImg/" + fileName + "." + suffix);
            }
        }
        if (exam.getEtsubject().equals("英语测验") && exam.getEtclassify() != null && exam.getEtclassify().length() > 0) {
            exam.setEtclass("三年级,四年级,五年级,六年级");
        }
        exam.setEtestNo(exam.getEtid() == null ? "10" : exam.getEtestNo());
        exam.setEtid(id);
        exam.setEtcreatTime(new Date());
        return exam;
    }

    // -------------------------------------综合语数外测验--end

    /**
     * 删除测试
     */
    @RequestMapping("/back/deleteExamById")
    public ModelAndView deleteExamById(HttpServletRequest req, HttpSession session, String gid) {
        User user = (User) session.getAttribute("backuser");
        if (gid != null && !"".equals(gid)) {
            if (null != user) {
                try {
                    examService.deleteExamById(gid);
                } catch (Exception e) {
                    log.error("删除语文测验【id:" + gid + "】出错了：", e);
                }
                return new ModelAndView("redirect:/back/exam/chinese/chineseExam.html");
            }
        }
        return new ModelAndView("redirect:/back/backlogin.html");
    }

    /**
     * 修改语文测验
     */
    @RequestMapping("/back/exam/chinese/updateChinexeExam.html")
    public void toUpdateExamById(HttpServletRequest req, HttpSession session, Model mode, String gid) {
        if (gid != null && !"".equals(gid)) {
            try {
                Exam exam = examService.findExamByEtid(gid);
                // exam.setImgList(examService.selectExamImgByExid(gid));
                mode.addAttribute("exam", exam);
            } catch (Exception e) {
                log.error("修改语文测验查询对象【id:" + gid + "】出错了：", e);
            }
        }
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
    }

    // 修改语文测验
    @RequestMapping(value = "/back/updateExam", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView updateExam(HttpServletRequest req, HttpSession session,
                                   @RequestParam(value = "fm", required = false) MultipartFile[] fm,
                                   @RequestParam(value = "file1", required = false) MultipartFile[] files,
                                   @RequestParam(value = "file1a", required = false) MultipartFile[] afiles, Exam exam, String[] delImhid) {
        User user = (User) session.getAttribute("backuser");
        try {
            if (user != null) {
                Exam ex = getexam(req, exam, fm, files, afiles);
                examService.updateExam(ex);
                /*
                 * if(ex.getImgList().size()>0){
                 * examService.insertExamImgs(ex.getImgList()); }
                 */
                if (delImhid != null && delImhid.length > 0) {
                    examService.deleteExamImgs(delImhid);
                }
                return new ModelAndView("redirect:/back/exam/chinese/chineseExam.html");
            }
        } catch (Exception e) {
            session.setAttribute("error", "出错了：" + e.getMessage());
            log.error("修改语文测验【id:" + exam.getEtid() + "】出错了：", e);
            return new ModelAndView("redirect:/back/exam/chinese/updateChinexeExam.html?gid=" + exam.getEtid());
        }
        return new ModelAndView("redirect:/back/backlogin.html");
    }

    /**
     * 新增语文测验
     */
    @RequestMapping("/back/exam/chinese/toAddChineseExam.html")
    public void toAddExam(HttpServletRequest req, HttpSession session, Model mode) {
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
    }

    // 新增语文测验
    @RequestMapping("/back/addChineseExam")
    public ModelAndView addExam(HttpServletRequest req, HttpSession session,
                                @RequestParam(value = "fm", required = false) MultipartFile[] fm,
                                @RequestParam(value = "file1", required = false) MultipartFile[] files,
                                @RequestParam(value = "file1a", required = false) MultipartFile[] afiles, Exam exam) {
        User user = (User) session.getAttribute("backuser");
        if (user != null) {
            try {
                exam.setEtsubject("语文测验");
                Exam ex = getexam(req, exam, fm, files, afiles);
                examService.addExam(ex);
                /*
                 * if(ex.getImgList().size()>0){
                 * examService.insertExamImgs(ex.getImgList()); }
                 */
                return new ModelAndView("redirect:/back/exam/chinese/chineseExam.html");
            } catch (Exception e) {
                session.setAttribute("error", "出错了：" + e.getMessage());
                log.error("新增语文测验出错了：", e);
                return new ModelAndView("redirect:/back/exam/chinese/toAddChineseExam.html");
            }
        }
        return new ModelAndView("redirect:/back/backlogin.html");
    }

    private Exam getexam(HttpServletRequest req, Exam exam, MultipartFile[] fm, MultipartFile[] files,
                         MultipartFile[] afiles) throws IOException {
        String id = exam.getEtid() == null || exam.getEtid().length() == 0 ? UUIDUtil.getUUID() : exam.getEtid();
        exam.setEtid(id);
        exam.setEtcreatTime(new Date());
        exam.setEtestNo("10");
        // exam=this.getImgList(req,exam,fm,files,afiles);
        return exam;
    }

    private Exam getImgList(HttpServletRequest req, Exam exam, MultipartFile[] fm, MultipartFile[] files,
                            MultipartFile[] afiles) throws IOException {
        String realPath = uploadAbsolutePath;
        String path = req.getContextPath().substring(1);
//		realPath = realPath.replace(path, "examImg");
        realPath = uploadAbsolutePath + "/" + "examImg";
        List<ExamImg> imgList = new ArrayList<ExamImg>();
        if (fm != null) {
            for (int i = 0; i < fm.length; i++) {// 测验试卷题、答案pdf文件
                MultipartFile file = fm[i];
                if (file.getSize() > 0) {
                    String fileName = i == 0 ? UUIDUtil.getUUID() : file.getOriginalFilename();
                    String suffix = file.getOriginalFilename()
                            .substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                    file.transferTo(new File(realPath, i == 0 ? fileName + "." + suffix : fileName));
                    if (i == 0) {// 题pdf
                        exam.setPdf("/examImg/" + fileName);
                    } else if (i == 1) {// 答案pdf
                        exam.setAnswerPdf("/examImg/" + fileName);
                    }
                }
            }
        }

        if (files != null) {
            Long sort = new Date().getTime();
            int i = 0;
            for (MultipartFile file : files) {// 测验题
                if (file.getSize() > 0) {
                    String fileName = UUIDUtil.getUUID();
                    String suffix = file.getOriginalFilename()
                            .substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                    file.transferTo(new File(realPath, fileName + "." + suffix));
                    ExamImg img = new ExamImg();
                    img.setId(UUIDUtil.getUUID());
                    img.setSort(String.valueOf(sort + i));
                    img.setType(0);
                    img.setExid(exam.getEtid());
                    img.setExamUrl("/examImg/" + fileName + "." + suffix);
                    img.setAnswerUrl("");
                    imgList.add(img);
                }
                ++i;
            }
        }
        if (afiles != null) {
            Long sort = new Date().getTime();
            int i = 0;
            for (MultipartFile file : afiles) {// 测验答案
                if (file.getSize() > 0) {
                    String fileName = UUIDUtil.getUUID();
                    String suffix = file.getOriginalFilename()
                            .substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                    file.transferTo(new File(realPath, fileName + "." + suffix));
                    ExamImg img = new ExamImg();
                    img.setId(UUIDUtil.getUUID());
                    img.setSort(String.valueOf(sort + i));
                    img.setType(1);
                    img.setExid(exam.getEtid());
                    img.setExamUrl("");
                    img.setAnswerUrl("/examImg/" + fileName + "." + suffix);
                    imgList.add(img);
                }
                ++i;
            }
        }
        exam.setImgList(imgList);
        return exam;
    }

    /**
     * 数学测验
     */
    @RequestMapping("/back/exam/math/mathExam.html")
    public void backMathExam(HttpServletRequest req, HttpSession session, Model model) {
        Page<Exam> pages = null;
        Map<String, String> maps = new HashMap<String, String>();
        try {
            int pageNo = 0;
            int pageSize = 20;
            if (req.getParameter("pageNo") != null && StringUtil.isNumeric(req.getParameter("pageNo"))) {
                pageNo = Integer.parseInt(req.getParameter("pageNo"));
            }
            if (pageNo < 1) {
                pageNo = 1;
            }
            String etclass = req.getParameter("etclass");
            String etclassify = req.getParameter("etclassify");

            maps.put("etsubject", "数学测验");
            if (!"".equals(etclass) && null != etclass) {
                // maps.put("etclass",new
                // String(etclass.getBytes("iso8859-1"),"utf-8"));
                maps.put("etclass", etclass);
            }

            // 班级下分类 如：专题课 真题课
            if (!"".equals(etclassify) && null != etclassify) {
                // maps.put("etclassify",new
                // String(etclassify.getBytes("iso8859-1"),"utf-8"));
                maps.put("etclassify", etclassify);
            }
            pages = examService.findExamListByMap(maps, pageNo, pageSize);
        } catch (Exception e) {
            pages = new Page<Exam>();
            pages.setResult(new ArrayList<Exam>());
            log.error("查询数学测验出错了：", e);
        }
        model.addAttribute("pages", pages);
        model.addAttribute("map", maps);
    }

    /**
     * 删除测试
     */
    @RequestMapping("/back/deleteMathExamById")
    public ModelAndView deleteMathExamById(HttpServletRequest req, HttpSession session, String gid) {
        User user = (User) session.getAttribute("backuser");
        if (gid != null && !"".equals(gid)) {
            if (null != user) {
                try {
                    examService.deleteExamById(gid);
                } catch (Exception e) {
                    log.error("删除数学测验【id:" + gid + "】出错了：", e);
                }
                return new ModelAndView("redirect:/back/exam/math/mathExam.html");
            }
        }
        return new ModelAndView("redirect:/back/backlogin.html");
    }

    /**
     * 修改数学测验
     */
    @RequestMapping("/back/exam/math/updateMathExam.html")
    public void toUpdateMathExamById(HttpServletRequest req, HttpSession session, Model mode, String gid) {
        if (gid != null && !"".equals(gid)) {
            try {
                Exam exam = examService.findExamByEtid(gid);
                // exam.setImgList(examService.selectExamImgByExid(gid));
                mode.addAttribute("exam", exam);
            } catch (Exception e) {
                log.error("修改数学测验查询对象【id:" + gid + "】出错了：", e);
            }
        }
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
    }

    @RequestMapping(value = "/back/updateMathExam", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView upMathdateExam(HttpServletRequest req,
                                       @RequestParam(value = "fm", required = false) MultipartFile[] fm, HttpSession session,
                                       @RequestParam(value = "file1", required = false) MultipartFile[] files,
                                       @RequestParam(value = "file1a", required = false) MultipartFile[] afiles, Exam exam, String[] delImhid) {
        User user = (User) session.getAttribute("backuser");
        if (user != null) {
            try {
                Exam ex = getexam(req, exam, fm, files, afiles);
                examService.updateExam(ex);
                /*
                 * if(ex.getImgList().size()>0){
                 * examService.insertExamImgs(ex.getImgList()); }
                 */
                if (delImhid != null && delImhid.length > 0) {
                    examService.deleteExamImgs(delImhid);
                }
                return new ModelAndView("redirect:/back/exam/math/mathExam.html");
            } catch (Exception e) {
                log.error("修改数学测验【id:" + exam.getEtid() + "】出错了：", e);
                session.setAttribute("error", "出错了：" + e.getMessage());
                return new ModelAndView("redirect:/back/exam/math/updateMathExam.html?gid=" + exam.getEtid());
            }
        }
        return new ModelAndView("redirect:/back/backlogin.html");
    }

    /**
     * 新增数学测验
     */
    @RequestMapping("/back/exam/math/toAddMathExam.html")
    public void toAddMathExam(HttpServletRequest req, HttpSession session, Model mode) {
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
    }

    @RequestMapping("/back/addMathExam")
    public ModelAndView addMathExam(HttpServletRequest req, HttpSession session,
                                    @RequestParam(value = "fm", required = false) MultipartFile[] fm,
                                    @RequestParam(value = "file1", required = false) MultipartFile[] files,
                                    @RequestParam(value = "file1a", required = false) MultipartFile[] afiles, Exam exam) {
        User user = (User) session.getAttribute("backuser");
        if (user != null) {
            try {
                exam.setEtsubject("数学测验");
                Exam ex = getexam(req, exam, fm, files, afiles);
                examService.addExam(ex);
                /*
                 * if(ex.getImgList().size()>0){
                 * examService.insertExamImgs(ex.getImgList()); }
                 */
                return new ModelAndView("redirect:/back/exam/math/mathExam.html");
            } catch (Exception e) {
                session.setAttribute("error", "出错了：" + e.getMessage());
                log.error("新增数学测验出错了：", e);
                return new ModelAndView("redirect:/back/exam/math/toAddMathExam.html");
            }
        }
        return new ModelAndView("redirect:/back/backlogin.html");
    }

    /**
     * 英语测验
     */
    @RequestMapping("/back/exam/english/englishExam.html")
    public void backEnglishExam(HttpServletRequest req, HttpSession session, Model model) {
        Page<Exam> pages = null;
        Map<String, String> maps = new HashMap<String, String>();
        try {
            int pageNo = 0;
            int pageSize = 20;
            if (req.getParameter("pageNo") != null && StringUtil.isNumeric(req.getParameter("pageNo"))) {
                pageNo = Integer.parseInt(req.getParameter("pageNo"));
            }
            if (pageNo < 1) {
                pageNo = 1;
            }
            String etclass = req.getParameter("etclass");
            String etclassify = req.getParameter("etclassify");

            maps.put("etsubject", "英语测验");
            if (!"".equals(etclass) && null != etclass) {
                // maps.put("etclass",new
                // String(etclass.getBytes("iso8859-1"),"utf-8"));
                maps.put("etclass", etclass);
            }

            // 班级下分类 如：专题课 真题课
            if (!"".equals(etclassify) && null != etclassify) {
                // maps.put("etclassify",new
                // String(etclassify.getBytes("iso8859-1"),"utf-8"));
                maps.put("etclassify", etclassify);
            }
            pages = examService.findExamListByMap(maps, pageNo, pageSize);
        } catch (Exception e) {
            log.error("查询英语测验出错了：", e);
            pages = new Page<Exam>();
            pages.setResult(new ArrayList<Exam>());
        }
        model.addAttribute("pages", pages);
        model.addAttribute("map", maps);
    }

    /**
     * 删除英语测试
     */
    @RequestMapping("/back/deleteEnglishExamById")
    public ModelAndView deleteEnglishExamById(HttpServletRequest req, HttpSession session, String gid) {
        User user = (User) session.getAttribute("backuser");
        if (gid != null && !"".equals(gid)) {
            if (null != user) {
                try {
                    examService.deleteExamById(gid);
                } catch (Exception e) {
                    log.error("删除英语测验【id:" + gid + "】出错了：", e);
                }
                return new ModelAndView("redirect:/back/exam/english/englishExam.html");
            }
        }
        return new ModelAndView("redirect:/back/backlogin.html");
    }

    /**
     * 修改英语测验
     */
    @RequestMapping("/back/exam/english/updateEnglishExam.html")
    public void toUpdateEnglishExamById(HttpServletRequest req, HttpSession session, Model mode, String gid) {
        if (gid != null && !"".equals(gid)) {
            try {
                Exam exam = examService.findExamByEtid(gid);
                // exam.setImgList(examService.selectExamImgByExid(gid));
                mode.addAttribute("exam", exam);
            } catch (Exception e) {
                log.error("修改英语测验查询对象【id:" + gid + "】出错了：", e);
            }
        }

        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
    }

    @RequestMapping(value = "/back/updateEnglishExam", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView updateEnglishExam(HttpServletRequest req,
                                          @RequestParam(value = "fm", required = false) MultipartFile[] fm, HttpSession session,
                                          @RequestParam(value = "file1", required = false) MultipartFile[] files,
                                          @RequestParam(value = "file1a", required = false) MultipartFile[] afiles, Exam exam, String[] delImhid) {
        User user = (User) session.getAttribute("backuser");
        if (user != null) {
            try {
                if (exam.getEtclassify() != null && exam.getEtclassify().length() > 0) {
                    exam.setEtclass("三年级,四年级,五年级,六年级");
                }
                Exam ex = getexam(req, exam, fm, files, afiles);
                examService.updateExam(ex);
                /*
                 * if(ex.getImgList().size()>0){
                 * examService.insertExamImgs(ex.getImgList()); }
                 */
                if (delImhid != null && delImhid.length > 0) {
                    examService.deleteExamImgs(delImhid);
                }
                return new ModelAndView("redirect:/back/exam/english/englishExam.html");
            } catch (Exception e) {
                session.setAttribute("error", "出错了：" + e.getMessage());
                log.error("修改英语测验【id:" + exam.getEtid() + "】出错了：", e);
                return new ModelAndView("redirect:/back/exam/english/updateEnglishExam.html?gid=" + exam.getEtid());
            }
        }
        return new ModelAndView("redirect:/back/backlogin.html");
    }

    /**
     * 新增英语测验
     */
    @RequestMapping("/back/exam/english/toAddEnglishExam.html")
    public void toAddEnglishExam(HttpServletRequest req, HttpSession session, Model mode) {

        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
    }

    @RequestMapping("/back/addEnglishExam")
    public ModelAndView addEnglishExam(HttpServletRequest req,
                                       @RequestParam(value = "fm", required = false) MultipartFile[] fm, HttpSession session,
                                       @RequestParam(value = "file1", required = false) MultipartFile[] files,
                                       @RequestParam(value = "file1a", required = false) MultipartFile[] afiles, Exam exam, String[] delImhid) {
        User user = (User) session.getAttribute("backuser");
        if (user != null) {
            try {
                exam.setEtsubject("英语测验");
                if (exam.getEtclassify() != null && exam.getEtclassify().length() > 0) {
                    exam.setEtclass("三年级,四年级,五年级,六年级");
                }
                Exam ex = getexam(req, exam, fm, files, afiles);
                examService.addExam(ex);
                /*
                 * if(ex.getImgList().size()>0){
                 * examService.insertExamImgs(ex.getImgList()); }
                 */
                return new ModelAndView("redirect:/back/exam/english/englishExam.html");
            } catch (Exception e) {
                session.setAttribute("error", "出错了：" + e.getMessage());
                log.error("新增英语测验出错了：", e);
                return new ModelAndView("redirect:/back/exam/english/toAddEnglishExam.html");
            }

        }
        return new ModelAndView("redirect:/back/backlogin.html");
    }

    // -------------------------20170326新增
    // --------------------------常规相关
    // 获取新增的常规对象
    private GeneralVideo Get_saveGeneralVideo(HttpServletRequest req, MultipartFile[] files, GeneralVideo generalVideo,
                                              String gsbuject) throws IOException {
        generalVideo = this.setUploadFiles_GeneralVideo(req, files, generalVideo);
        generalVideo.setGid(UUIDUtil.getUUID());
        generalVideo.setGsbuject(gsbuject);
        generalVideo.setGcreatTime(new Date());
        generalVideo.setGplayNo(10);
        // 语文数学设置多个年级值
        if (gsbuject.equals("语文") || gsbuject.equals("数学")) {
            String[] gclass = req.getParameterValues("gclass");
            generalVideo.setGclass(BaseUtil.arrayToStr(gclass));
        }
        String money = req.getParameter("gmoney");
        if (!"".equals(money) && null != money) {
            generalVideo.setGmoney(money);
        } else {
            generalVideo.setGmoney("0");
        }
        String isKill = req.getParameter("isKill");
        if (isKill != null && !"".equals(isKill)) {
            generalVideo.setIsKill(Integer.parseInt(isKill));
        } else {
            generalVideo.setIsKill(0);
        }

        String killMoney = generalVideo.getKillMoney();
        if (!"".equals(killMoney) && null != killMoney) {
            generalVideo.setKillMoney(killMoney);
        } else {
            generalVideo.setKillMoney("0");
        }
        return generalVideo;
    }

    // 获取编辑的常规对象
    private GeneralVideo Get_updateGeneralVideo(HttpServletRequest req, MultipartFile[] files,
                                                GeneralVideo generalVideo) throws Exception {
        generalVideo = this.setUploadFiles_GeneralVideo(req, files, generalVideo);
        String videoUrl = generalVideo.getGvideoUrl();
        String fmImgUrl = generalVideo.getGvimg();
        if (videoUrl.length() == 0 || fmImgUrl.length() == 0) {
            GeneralVideo generalVideo2 = generalVideoService.findGeneralVideoByVid(generalVideo.getGid());
            if (videoUrl.length() == 0) {
                generalVideo.setGvideoUrl(generalVideo2.getGvideoUrl());
            }
            if (fmImgUrl.length() == 0) {
                generalVideo.setGvimg(generalVideo2.getGvimg());
            }
            generalVideo.setGplayNo(videoUrl.length() == 0 ? generalVideo2.getGplayNo() : 10);
        } else {
            generalVideo.setGplayNo(10);// 设置默认播放次数
        }
        String money = req.getParameter("gmoney");
        if (!"".equals(money) && null != money) {
            generalVideo.setGmoney(money);
        } else {
            generalVideo.setGmoney("0");
        }
        String isKill = req.getParameter("isKill");
        if (isKill != null && !"".equals(isKill)) {
            generalVideo.setIsKill(Integer.parseInt(isKill));
        } else {
            generalVideo.setIsKill(0);
        }

        String killMoney = generalVideo.getKillMoney();
        if (!"".equals(killMoney) && null != killMoney) {
            generalVideo.setKillMoney(killMoney);
        } else {
            generalVideo.setKillMoney("0");
        }

        String gsbuject = generalVideo.getGsbuject();
        // 语文数学设置多个年级值
        if (gsbuject.equals("语文") || gsbuject.equals("数学")) {
            String[] gclass = req.getParameterValues("gclass");
            generalVideo.setGclass(BaseUtil.arrayToStr(gclass));
        }
        generalVideo.setGcreatTime(new Date());
        return generalVideo;
    }

    // 常规文件批量上传（视频、封面、pdf）
    private GeneralVideo setUploadFiles_GeneralVideo(HttpServletRequest req, MultipartFile[] files,
                                                     GeneralVideo generalVideo) throws IOException {
        List<VideoPdf> pdfList = new ArrayList<VideoPdf>();
        if (files != null && files.length > 0) {
            /**使用nginx,不用需要使用项目目录了.*/
            String[] file_dir = new String[]{"video", "fmImg", "pdf"};
            String fileName = UUIDUtil.getUUID();
            String name = "";
            Long sort = new Date().getTime();
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                String url = "";
                if (file.getSize() > 0) {
                    String suffix = file.getOriginalFilename()
                            .substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                    name = i >= 2 ? file.getOriginalFilename() : fileName + "." + suffix;
                    String dir = i >= 2 ? file_dir[2] : file_dir[i];//文件夹名称 例如fmImg
                    /**真实路径, 上传用. -->更换为其他任意非项目下(webapps)路径*/
                    String realPath_c = uploadAbsolutePath + "/" + dir;

                    file.transferTo(new File(realPath_c, name));
                    url = "/" + dir + "/" + name;
                }
                if (i == 0) {// 视频
                    generalVideo.setGvideoUrl(url);
                } else if (i == 1) {// 封面图片
                    generalVideo.setGvimg(url);
                } else if (i >= 2 && url.length() > 0) {// pdf文件
                    if (file.getSize() > 0) {
                        VideoPdf pdf = new VideoPdf();
                        pdf.setPdfid(UUIDUtil.getUUID());
                        pdf.setPdfUrl(url);
                        pdf.setSort(String.valueOf(sort + i));
                        pdf.setVctype(0);
                        pdfList.add(pdf);
                    }
                }
            }
        }
        generalVideo.setPdflist(pdfList);
        return generalVideo;
    }

    // -------------------------------竞赛相关
    // 获取新增的竞赛对象
    private ContestVideo Get_saveContestVideo(HttpServletRequest req, MultipartFile[] files, ContestVideo contestVideo,
                                              String competitionName) throws IOException {
        contestVideo = this.setUploadFiles_ContestVideo(req, files, contestVideo);
        contestVideo.setCid(UUIDUtil.getUUID());
        contestVideo.setCompetitionName(competitionName);
        contestVideo.setCcreatTime(new Date());
        contestVideo.setCplayNo(20);
        // 设置多选年级
        String[] cclass = req.getParameterValues("cclass");
        contestVideo.setCclass(BaseUtil.arrayToStr(cclass));
        String money = req.getParameter("cmoney");
        if (!"".equals(money) && null != money) {
            contestVideo.setCmoney(money);
        } else {
            contestVideo.setCmoney("0");
        }
        String killMoney = contestVideo.getKillMoney();
        if (!"".equals(killMoney) && null != killMoney) {
            contestVideo.setKillMoney(killMoney);
        } else {
            contestVideo.setKillMoney("0");
        }
        String isKill = req.getParameter("isKill");
        if (isKill != null && !"".equals(isKill)) {
            contestVideo.setIsKill(Integer.parseInt(isKill));
        } else {
            contestVideo.setIsKill(0);
        }
        return contestVideo;
    }

    // 获取编辑的竞赛对象
    private ContestVideo Get_updateContestVideo(HttpServletRequest req, MultipartFile[] files,
                                                ContestVideo contestVideo) throws Exception {
        contestVideo = this.setUploadFiles_ContestVideo(req, files, contestVideo);
        String videoUrl = contestVideo.getCvideoUrl();
        String fmImgUrl = contestVideo.getCvimg();
        if (videoUrl.length() == 0 || fmImgUrl.length() == 0) {
            ContestVideo contestVideo2 = contestVideoService.findContestVideoById(contestVideo.getCid());
            if (videoUrl.length() == 0) {
                contestVideo.setCvideoUrl(contestVideo2.getCvideoUrl());
            }
            if (fmImgUrl.length() == 0) {
                contestVideo.setCvimg(contestVideo2.getCvimg());
            }
            contestVideo.setCplayNo(videoUrl.length() == 0 ? contestVideo2.getCplayNo() : 20);
        } else {
            contestVideo.setCplayNo(20);// 设置默认播放次数
        }
        String money = req.getParameter("cmoney");
        if (!"".equals(money) && null != money) {
            contestVideo.setCmoney(money);
        } else {
            contestVideo.setCmoney("0");
        }
        String killMoney = contestVideo.getKillMoney();
        if (!"".equals(killMoney) && null != killMoney) {
            contestVideo.setKillMoney(killMoney);
        } else {
            contestVideo.setKillMoney("0");
        }
        String isKill = req.getParameter("isKill");
        if (isKill != null && !"".equals(isKill)) {
            contestVideo.setIsKill(Integer.parseInt(isKill));
        } else {
            contestVideo.setIsKill(0);
        }
        // 设置多选年级
        String[] cclass = req.getParameterValues("cclass");
        contestVideo.setCclass(BaseUtil.arrayToStr(cclass));
        contestVideo.setCcreatTime(new Date());
        return contestVideo;
    }

    // 竞赛文件批量上传（视频、封面、pdf）
    private ContestVideo setUploadFiles_ContestVideo(HttpServletRequest req, MultipartFile[] files,
                                                     ContestVideo contestVideo) throws IOException {
        List<VideoPdf> pdfList = new ArrayList<VideoPdf>();
        if (files != null && files.length > 0) {
            String realPath = uploadAbsolutePath;
            String path = req.getContextPath().substring(1);
            String[] file_dir = new String[]{"cvideo", "fmImg", "pdf"};
            String fileName = UUIDUtil.getUUID();
            Long sort = new Date().getTime();
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                String url = "";
                String name = "";
                if (file.getSize() > 0) {
                    String suffix = file.getOriginalFilename()
                            .substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                    name = i >= 2 ? file.getOriginalFilename() : fileName + "." + suffix;
                    String dir = i >= 2 ? file_dir[2] : file_dir[i];
                    String realPath_c = uploadAbsolutePath + "/" + dir;
                    file.transferTo(new File(realPath_c, name));
                    url = "/" + dir + "/" + name;
                }
                if (i == 0) {// 视频
                    contestVideo.setCvideoUrl(url);
                } else if (i == 1) {// 封面图片
                    contestVideo.setCvimg(url);
                } else if (i >= 2 && url.length() > 0) {// pdf文件
                    if (file.getSize() > 0) {
                        // contestVideo.setCvpdf(url);
                        VideoPdf pdf = new VideoPdf();
                        pdf.setPdfid(UUIDUtil.getUUID());
                        pdf.setPdfUrl(url);
                        pdf.setSort(String.valueOf(sort + i));
                        pdf.setVctype(1);
                        pdfList.add(pdf);
                    }
                }
            }
        }
        contestVideo.setPdflist(pdfList);
        return contestVideo;
    }

    /**
     * 首页轮播图片管理
     */
    @RequestMapping("/back/bannerMgr.html")
    public void bannerMgr(HttpServletRequest req, HttpSession session, Model model) {
        try {
            List<Banner> blist = orderService.selectAllBanner();
            model.addAttribute("blist", blist);
        } catch (Exception e) {
            log.error("查询首页轮播图出错了：", e);
        }
    }

    /**
     * 删除轮播图
     */
    @RequestMapping("/back/deleteBanner")
    public ModelAndView deleteBanner(HttpServletRequest req, HttpSession session, String bid) {
        try {
            orderService.deleteBanner(bid);
        } catch (Exception e) {
            log.error("删除轮播图【id:" + bid + "】出错了:", e);
        }
        return new ModelAndView("redirect:/back/bannerMgr.html");
    }

    /**
     * 修改轮播图
     */
    @RequestMapping("/back/bannerMgr/updateBanner.html")
    public void updateBanner(HttpServletRequest req, HttpSession session, String bid, Model mode) {
        try {
            Banner banner = orderService.findBannerByBid(bid);
            mode.addAttribute("banner", banner);
            try {
                List<ArticleImg> alist = orderService.selectArticleImgByBid(bid);
                mode.addAttribute("alist", alist);
            } catch (Exception e) {

            }
            if (session.getAttribute("error") != null) {
                mode.addAttribute("error", session.getAttribute("error"));
                session.removeAttribute("error");
            }
        } catch (Exception e) {
            mode.addAttribute("error", "修改轮播图查询出错了:" + e.getMessage());
            log.error("修改轮播图查询【id:" + bid + "】出错了：", e);
        }

    }

    @RequestMapping(value = "/back/updateBannerClass", method = RequestMethod.POST)
    public ModelAndView updateBannerClass(HttpServletRequest req, HttpSession session, Banner banner,
                                          @RequestParam(value = "file1", required = false) MultipartFile file,
                                          @RequestParam(value = "file2", required = false) MultipartFile[] file2, String[] delImhid) {
        try {
            String realPath = uploadAbsolutePath;
            String path = req.getContextPath().substring(1);
            realPath = uploadAbsolutePath + "/" + "fmImg";
            if (file.getSize() > 0) {
                String fileName = UUIDUtil.getUUID();
                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                file.transferTo(new File(realPath, fileName + "." + suffix));
                banner.setImgUrl("/fmImg/" + fileName + "." + suffix);
            }
            orderService.updateBanner(banner);
            if (file2.length > 0) {// 轮播图文章图片
                List<ArticleImg> alist = new ArrayList<ArticleImg>();
                for (MultipartFile f : file2) {
                    if (f.getSize() > 0) {
                        String fileName = UUIDUtil.getUUID();
                        String suffix = f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf(".") + 1);
                        file.transferTo(new File(realPath, fileName + "." + suffix));
                        ArticleImg img = new ArticleImg();
                        img.setAtid(fileName);
                        img.setAtUrl("/fmImg/" + fileName + "." + suffix);
                        img.setBid(banner.getBid());
                        alist.add(img);
                    }
                }
                try {
                    orderService.insertArticleImgs(alist);
                    if (delImhid != null && delImhid.length > 0) {
                        orderService.deleteArticleImgs(delImhid);
                    }
                } catch (Exception e) {
                    log.info("批量新增轮播图文章失败：", e);
                }

            }
            return new ModelAndView("redirect:/back/bannerMgr.html");
        } catch (Exception e) {
            session.setAttribute("error", "出错了：" + e.getMessage());
            log.error("修改轮播图查询【id:" + banner.getBid() + "】出错了：", e);
        }
        return new ModelAndView("redirect:/back/bannerMgr/updateBanner.html?bid=" + banner.getBid());
    }

    /**
     * 上移、下移
     */
    @RequestMapping("/back/updateBannerSort")
    public void updateBannerSort(HttpServletRequest req, HttpServletResponse response, String id1, String sort1,
                                 String id2, String sort2) throws Exception {
        String res = "ok";
        try {
            orderService.updateBannerSort(id1, sort1);
            orderService.updateBannerSort(id2, sort2);
        } catch (Exception e) {
            res = "出错了：" + e.getMessage();
        }
        PrintHelper.sendJsonString(response, res);
    }

    /**
     * 新增轮播图
     */
    @RequestMapping("/back/bannerMgr/toAddBanner.html")
    public void toAddBanner(HttpServletRequest req, HttpSession session, Model mode) {

        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
    }

    @RequestMapping(value = "/back/addBannerClass", method = RequestMethod.POST)
    public ModelAndView addBannerClass(HttpServletRequest req, HttpSession session, Banner banner,
                                       @RequestParam(value = "file1", required = false) MultipartFile file,
                                       @RequestParam(value = "file2", required = false) MultipartFile[] file2) {
        try {
            banner.setBid(UUIDUtil.getUUID());
            banner.setSort(String.valueOf(new Date().getTime()));
            String realPath = uploadAbsolutePath;
            realPath = uploadAbsolutePath + "/" + "fmImg";
            if (file.getSize() > 0) {
                String fileName = UUIDUtil.getUUID();
                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

                file.transferTo(new File(realPath, fileName + "." + suffix));
                banner.setImgUrl("/fmImg/" + fileName + "." + suffix);
            }
            orderService.insertBanner(banner);
            /**
             * 如果是网站添加轮播图,执行以下代码
             */
            if (banner.getBelong() == 0) {
                if (file2.length > 0) {// 轮播图文章图片
                    List<ArticleImg> alist = new ArrayList<ArticleImg>();
                    for (MultipartFile f : file2) {
                        if (f.getSize() > 0) {
                            String fileName = UUIDUtil.getUUID();
                            String suffix = f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf(".") + 1);
                            file.transferTo(new File(realPath, fileName + "." + suffix));
                            ArticleImg img = new ArticleImg();
                            img.setAtid(fileName);
                            img.setAtUrl("/fmImg/" + fileName + "." + suffix);
                            img.setBid(banner.getBid());
                            alist.add(img);
                        }
                    }
                    try {
                        orderService.insertArticleImgs(alist);
                    } catch (Exception e) {
                        log.info("批量新增轮播图文章失败：", e);
                    }
                }
            }


            return new ModelAndView("redirect:/back/bannerMgr.html");
        } catch (Exception e) {
            session.setAttribute("error", "出错了：" + e.getMessage());
            log.error("新增轮播图查询出错了：", e);

        }
        return new ModelAndView("redirect:/back/bannerMgr/toAddBanner.html");
    }

    /**
     * 学生家长管理
     */
    @RequestMapping("/back/userMgr.html")
    public void userMgr(HttpServletRequest req, HttpSession session, Model model) {
        Map<String, String> maps = new HashMap<String, String>();

        maps.put("type", "1");
        int pageNo = 0;
        int pageSize = 20;
        if (req.getParameter("pageNo") != null && StringUtil.isNumeric(req.getParameter("pageNo"))) {
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        if (pageNo < 1) {
            pageNo = 1;
        }
        Page<User> pages = null;
        try {
            pages = userService.searchUserList(maps, pageNo, pageSize);
            model.addAttribute("pages", pages);
        } catch (Exception e) {
            pages = new Page<User>();
            pages.setResult(new ArrayList<User>());
            log.error("查询学生家长出错了：", e);
        }
    }

    /**
     * 普通用户管理
     */
    @RequestMapping("/back/userMgrComm.html")
    public void userMgrComm(HttpServletRequest req, HttpSession session, Model model) {
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("type", "0");
        int pageNo = 0;
        int pageSize = 20;
        if (req.getParameter("pageNo") != null && StringUtil.isNumeric(req.getParameter("pageNo"))) {
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        if (pageNo < 1) {
            pageNo = 1;
        }
        Page<User> pages = null;
        try {
            pages = userService.searchUserList(maps, pageNo, pageSize);
        } catch (Exception e) {
            pages = new Page<User>();
            pages.setResult(new ArrayList<User>());
            log.error("查询普通用户出错了：", e);
        }
        model.addAttribute("pages", pages);
    }

    /**
     * 删除学生家长
     */
    @RequestMapping("/back/deleteCommUser")
    public ModelAndView deleteCommUser(HttpServletRequest req, HttpSession session, String uid) {
        try {
            userService.deleteUserById(uid);
        } catch (Exception e) {
            log.error("删除学生家长【id:" + uid + "】失败：", e);
        }
        return new ModelAndView("redirect:/back/userMgrComm.html");
    }

    /**
     * 删除普通用户
     */
    @RequestMapping("/back/deleteUser")
    public ModelAndView deleteUser(HttpServletRequest req, HttpSession session, String uid) {
        try {
            userService.deleteUserById(uid);
        } catch (Exception e) {
            log.error("删除普通用户【id:" + uid + "】失败：", e);
        }
        return new ModelAndView("redirect:/back/userMgr.html");
    }

    /**
     * 审核学生家长
     */
    @RequestMapping("/back/updateUser.html")
    public void updateUser(HttpServletRequest req, HttpSession session, String uid, int userType, Model model) {
        try {
            if (uid == null) {
                uid = (String) session.getAttribute("shUid");
                if (uid != null) {
                    session.removeAttribute("shUid");
                    String shError = (String) session.getAttribute("shError");
                    if (shError != null) {
                        model.addAttribute("shError", shError);
                        session.removeAttribute("shError");
                    }
                }
            }
            User user = userService.findUserByUid(uid);
            model.addAttribute("user", user);
            String json = JSONArray.toJSON(userService.selectRightMenu("")).toString();
            model.addAttribute("menus", json);
            model.addAttribute("userType", userType);
        } catch (Exception e) {
            log.error("审核学生家长【id:" + uid + "】出错了:", e);
        }
    }

    @RequestMapping(value = "/back/updateUserClass", method = RequestMethod.POST)
    public ModelAndView updateUserClass(HttpServletRequest req, HttpSession session, User user, String endTime2,
                                        Integer isSend, int userType) {
        try {
            user.setStatus(1);
            Date endDate = null;
            String res = "";
            if (endTime2 != null && endTime2.length() > 0) {
                try {
                    endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endTime2);
                } catch (Exception e) {
                }
            }
            user.setEndTime(endDate);
            try {
                userService.updateUserNews(user);
                if (isSend == 1) {
                    // 发短信提醒用户审核通过
                    res = CloudSMSUtil.sendSMS(user.getPhoneNo(), session, false, userType);// userType:1：重审短信；0：注册审核短信
                    // System.out.println("发短信。。。");
                } else {
                    res = "1";
                    // System.out.println("不发短信。。。");
                }

                if (res.equals("1")) {
                    return new ModelAndView("redirect:/back/userMgr.html");
                } else {
                    res = "审核短信发送失败，请查看短信账号是否已到期！";
                }
            } catch (Exception e) {
                res = "审核失败，请检查服务器或网络是否异常！";
            }
            if (res.length() > 0) {
                session.setAttribute("shError", res);
                session.setAttribute("shUid", user.getUid());
            }

        } catch (Exception e) {
            log.error("审核学生家长信息【id:" + user.getUid() + "】发送短信操作失败：", e);
        }
        return new ModelAndView("redirect:/back/updateUser.html");
    }

    /**
     * 管理员管理
     */
    @RequestMapping("/back/adminMgr.html")
    public void adminMgr(HttpServletRequest req, HttpSession session, Model model) {
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("isVip", "2");
        try {
            List<User> alist = userService.searchUserList(maps);
            model.addAttribute("alist", alist);
        } catch (Exception e) {
            log.error("查询管理员出错：", e);
        }
    }

    /**
     * 学习等级管理
     */
    @RequestMapping("/back/userLevel.html")
    public void userLevel(HttpServletRequest req, HttpSession session, Model model) {
        try {
            List<UserLevel> ulist = userService.selectAllLevel();
            model.addAttribute("ulist", ulist);
        } catch (Exception e) {
            log.error("查询学习等级出错：", e);
        }
    }

    /**
     * 删除学习等级
     */
    @RequestMapping("/back/deleteUserLevel")
    public ModelAndView deleteUserLevel(HttpServletRequest req, HttpSession session, String ulid) {
        try {
            userService.deleteLevel(ulid);
        } catch (Exception e) {
            log.error("删除学习等级【id:" + ulid + "】失败：", e);
        }
        return new ModelAndView("redirect:/back/userLevel.html");
    }

    /**
     * 新增学习等级页面
     */
    @RequestMapping("/back/userLevelAdd.html")
    public void userLevelAdd(HttpServletRequest req, HttpSession session, Model model) {
        String addError = (String) session.getAttribute("error");
        if (addError != null) {
            model.addAttribute("error", addError);
            session.removeAttribute("error");
        }
    }

    @RequestMapping(value = "/back/userLevelAddClass", method = RequestMethod.POST)
    public ModelAndView userLevelAddClass(HttpServletRequest req, HttpSession session, UserLevel user, String level,
                                          String minNum) {
        try {
            user.setUlid(UUIDUtil.getUUID());
            if (level.length() == 0) {
                user.setLevel(0);
            }
            if (minNum.length() == 0) {
                user.setMinNum(0l);
            }
            /*
             * if(maxNum.length()==0){ user.setMaxNum(0l); }
             */
            String res = "";
            try {
                userService.insertLevel(user);
                return new ModelAndView("redirect:/back/userLevel.html");
            } catch (Exception e) {
                res = "新增学习等级失败，请检查服务器或网络是否异常！";
            }
            if (res.length() > 0) {
                session.setAttribute("error", res);
            }
        } catch (Exception e) {
            log.error("新增学习等级出错：", e);
        }
        return new ModelAndView("redirect:/back/userLevelAdd.html");
    }

    // 修改学习等级
    @RequestMapping("/back/userLevelUp.html")
    public void userLevelUp(HttpServletRequest req, HttpSession session, String ulid, Model mode) {
        try {
            UserLevel ul = userService.selectLevelById(ulid);
            if (ul != null) {
                mode.addAttribute("ulevel", ul);
            }
        } catch (Exception e) {
            log.error("修改学习等级查询对象出错：", e);
            mode.addAttribute("error", "错误：" + e.getMessage());
        }
        if (session.getAttribute("error") != null) {
            mode.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }
    }

    /**
     * 修改学习等级
     */
    @RequestMapping(value = "/back/userLevelUpClass", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView userLevelUpClass(HttpServletRequest req, HttpSession session, UserLevel ul, String level,
                                         String minNum) {
        try {
            if (level.length() == 0) {
                ul.setLevel(0);
            }
            if (minNum.length() == 0) {
                ul.setMinNum(0l);
            }
            /*
             * if(maxNum.length()==0){ ul.setMaxNum(0l); }
             */
            userService.updateLevel(ul);
            return new ModelAndView("redirect:/back/userLevel.html");
        } catch (Exception e) {
            session.setAttribute("error", "出错：" + e.getMessage());
            log.error("修改学习等级【id:" + ul.getUlid() + "】出错：", e);
        }
        return new ModelAndView("redirect:/back/userLevelUp.html?ulid=" + ul.getUlid());
    }

    /**
     * 删除管理员
     */
    @RequestMapping("/back/deleteAdmin")
    public ModelAndView deleteAdmin(HttpServletRequest req, HttpSession session, String uid) {
        try {
            userService.deleteUserById(uid);
        } catch (Exception e) {
            log.error("删除管理员【id:" + uid + "】失败：", e);
        }
        return new ModelAndView("redirect:/back/adminMgr.html");
    }

    /**
     * 新增管理员页面
     */
    @RequestMapping("/back/toAddAdmin.html")
    public void toAddAdmin(HttpServletRequest req, HttpSession session, Model model) {
        String addError = (String) session.getAttribute("addError");
        if (addError != null) {
            model.addAttribute("addError", addError);
            session.removeAttribute("addError");
        }
    }

    @RequestMapping(value = "/back/addAdminClass", method = RequestMethod.POST)
    public ModelAndView addAdminClass(HttpServletRequest req, HttpSession session, User user) {
        try {
            user.setUid(UUIDUtil.getUUID());
            user.setPassWord(new MD5(user.getPassWord()).compute_upper());
            user.setStatus(1);
            user.setIsVip("2");
            user.setRegTime(new Date());
            String res = "";
            try {
                userService.addUser(user);
                return new ModelAndView("redirect:/back/adminMgr.html");
            } catch (Exception e) {
                res = "新增管理员失败，请检查服务器或网络是否异常！";
            }
            if (res.length() > 0) {
                session.setAttribute("addError", res);
            }
        } catch (Exception e) {
            log.error("新增管理员出错：", e);
        }
        return new ModelAndView("redirect:/back/toAddAdmin.html");
    }

    /**
     * 检查管理员是否已存在
     */
    @RequestMapping("/back/checkAdminIsExit")
    public void checkAdminIsExit(HttpServletRequest req, HttpServletResponse response, String name) throws Exception {
        int count = 1;
        try {
            count = userService.getIsExitsByName(name);
        } catch (Exception e) {
            log.error("检查管理员【name:" + name + "】是否存在失败：", e);
        }
        PrintHelper.sendJsonString(response, count);
    }

    /**
     * 单个视频添加测试题跳转
     */
    @RequestMapping("/back/backGeneral/addUnitTest.html")
    public void addUnitTest(HttpServletRequest req, HttpSession session, Model mode, String vid, String vname,
                            String type, String typeName) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("vid", vid);
            map.put("utype", type);
            List<Map> list = userService.selectVidoeTestAll(map);
            Map<String, Object> resMap = getUnitTests(list);
            mode.addAttribute("ucData", resMap.get("ucData"));
            mode.addAttribute("utlist", resMap.get("utlist"));
        } catch (Exception e) {
            mode.addAttribute("error", "查询章节测试题出错：" + e.getMessage());
            log.error("查询章节测试题【vid:" + vid + "】出错：", e);
            List<UnitTest> utlist = new ArrayList<UnitTest>();
            mode.addAttribute("utlist", utlist);
        }
        mode.addAttribute("vid", vid);// 章节视频id
        mode.addAttribute("vname", vname);// 视频名称
        mode.addAttribute("type", type);// 视频类型：0：常规；1：竞赛；4：科目小测验
        mode.addAttribute("typeName", typeName);// 视频类别：语文、数学、华杯、语文测验等
        mode.addAttribute("isMore", 0);// 是否多集视频

    }

    /**
     * 多集视频添加测试题跳转
     */
    @RequestMapping("/back/backGeneral/addUnitTestMore.html")
    public void addUnitTestMore(HttpServletRequest req, HttpSession session, Model mode, String vid, String vname,
                                String type, String typeName) {
        String vcId = "";
        List<VideoChild> vlist = null;
        try {
            Map<String, String> map2 = new HashMap<String, String>();
            map2.put("pid", vid);
            vlist = generalVideoService.selectAllChildVideo(map2);// 视频多集关联子视频
            if (vlist.size() > 0) {
                VideoChild v = vlist.get(0);
                vcId = v.getVcid();

                String ctype = type.equals("0") ? "2" : "3";
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("vid", vcId);
                map.put("utype", ctype);
                List<Map> list = userService.selectVidoeTestAll(map);
                Map<String, Object> resMap = getUnitTests(list);
                mode.addAttribute("ucData", resMap.get("ucData"));
                mode.addAttribute("utlist", resMap.get("utlist"));

            } else {
                List<UnitTest> utlist = new ArrayList<UnitTest>();
                mode.addAttribute("utlist", utlist);
            }
        } catch (Exception e) {
            mode.addAttribute("error", "查询多集测试题出错：" + e.getMessage());
            log.error("查询多集测试题【vid:" + vid + "】【子视频vcId:" + vcId + "】出错：", e);
            vlist = new ArrayList<VideoChild>();
            List<UnitTest> utlist = new ArrayList<UnitTest>();
            mode.addAttribute("utlist", utlist);
        }
        mode.addAttribute("vid", vid);// 章节视频id
        mode.addAttribute("vname", vname);// 视频名称
        mode.addAttribute("type", type);// 视频类型：0：常规；1：竞赛
        mode.addAttribute("typeName", typeName);// 视频类别：语文、数学、华杯等
        mode.addAttribute("isMore", 1);// 是否多集视频

        mode.addAttribute("vlist", vlist);// 视频多集关联子视频
        mode.addAttribute("vcId", vcId);// 当前测试题子视频id
    }

    // 新增测验题
    @RequestMapping("/back/addUnitTestClass")
    public void addUnitTestClass(HttpServletRequest req, HttpServletResponse response,
                                 String[] answer, UnitTest u,
                                 String vid, int type, int isMore,
                                 int index, String vcId) throws Exception {
        Map<String, String> res = new HashMap<String, String>();
        String status = "ok";
        String data = "";
        u.setAnswer(BaseUtil.arrayToStr(answer));
        String utid = UUIDUtil.getUUID();
        u.setUtid(utid);
        if (isMore == 1) {// 专题子视频测试题
            type = type == 0 ? 2 : 3;
            vid = vcId;
        }
        u.setUtype(type);// 视频类型：0：单个常规；1：单个竞赛；2：多集常规；3：多集竞赛
        u.setVid(vid);

        List<UnitTest_Choose> uclist = new ArrayList<UnitTest_Choose>();
        try {
            userService.insertUnitTest(u);// 新增测验题
            String tcHtml = "";
            if (u.getTestType() == 0) {
                String[] tcontentArr = req.getParameterValues("tcontent");
                String[] tanswerArr = req.getParameterValues("tanswer");
                if (tcontentArr != null) {
                    Long sort = new Date().getTime();
                    for (int i = 0; i < tcontentArr.length; i++) {
                        String tcontent = tcontentArr[i];
                        String tanswer = tanswerArr[i];
                        UnitTest_Choose uc = new UnitTest_Choose();
                        uc.setCsid(UUIDUtil.getUUID());
                        uc.setTcontent(tcontent);
                        uc.setUtid(utid);
                        uc.setTanswer(tanswer);
                        uc.setSort(sort + i);
                        uclist.add(uc);
                        tcHtml += tanswer + "." + tcontent + "<br>";
                    }
                    // 新增测验题选项
                    userService.insertUnitTest_Choose(uclist);
                }
            }
            String ucontent = u.getUcontent() == null ? "" : u.getUcontent();
            data = "<tr id=\"tr_" + utid + "\"><td>" + index + "</td><td>"
                    + (ucontent.length() > 30 ? ucontent.substring(0, 30) + "..." : ucontent) + "</td><td>"
                    + (u.getUtimg() == null || u.getUtimg().length() == 0 ? ""
                    : "<img src=\"" + u.getUtimg() + "\" style=\"width:40px;height:40px;\">")
                    + "</td><td>" + (u.getTestType() == 0 ? "选择题" : "简答题") + "</td>";
            data += "<td>" + (u.getTestType() != 1 ? (u.getIsMoreChoose() == 1 ? "是" : "否") : "") + "</td><td>"
                    + BaseUtil.arrayToStr(answer) + "</td><td style=\"text-align:left;color:#00CC99;\">" + tcHtml
                    + "</td>";
            data += "<td>" + u.getScore()
                    + "</td><td><div class=\"button-group\"><a class=\"button border-main border-up\" onclick=\"toEdit('"
                    + utid + "')\"  ><span class=\"icon-edit\"></span> 修改</a>"
                    + "<a class=\"button border-red\" onclick=\"del('" + utid
                    + "')\" ><span class=\"icon-trash-o\"></span> 删除</a></div> </td></tr>";
        } catch (Exception e) {
            status = "error";
            data = "新增测验题失败：" + e.getMessage();
            log.error("新增测验题异常", e);
        }

        res.put("status", status);
        res.put("data", data);
        PrintHelper.sendJsonObject(response, res);
    }

    /**
     * 删除测验题
     */
    @RequestMapping("/back/delUnitTestClass")
    public void delUnitTestClass(HttpServletRequest req, HttpServletResponse response, String utid) throws Exception {
        String res = "ok";
        try {
            userService.delUnitTest(utid);
        } catch (Exception e) {
            log.error("删除测验题异常：", e);
            res = "删除测验题出错：" + e.getMessage();
        }
        PrintHelper.sendJsonString(response, res);
    }

    /**
     * 章节测试题修改页面跳转
     */
    @RequestMapping("/back/backGeneral/updateUnitTest.html")
    public void updateUnitTest(HttpServletRequest req, HttpSession session, Model model, String utid, String vcId) {
        List<Map> list = userService.selectVidoeTestByUtid(utid);
        Map<String, Object> resMap = getUnitTests(list);
        model.addAttribute("ucData", resMap.get("ucData"));
        model.addAttribute("utlist", resMap.get("utlist"));
        model.addAttribute("vcId", vcId);
    }

    /**
     * 根据 试题和试题选项  得到-->
     *
     * @param list
     * @return
     */
    private Map<String, Object> getUnitTests(List<Map> list) {
        Map<String, Object> mapRes = new HashMap<String, Object>();
        Map<String, List<UnitTest_Choose>> ucData = new HashMap<String, List<UnitTest_Choose>>();//题目 对应的 选项集合 映射
        List<UnitTest> utlist = new ArrayList<UnitTest>();//试题集合

        Map<String, String> uniqueMap = new HashMap<String, String>();

        for (Map map : list) {//编历得到每一个试题

            String utid = String.valueOf(map.get("utid"));
            List<UnitTest_Choose> clist = new ArrayList<UnitTest_Choose>();//选项集合

            if (!uniqueMap.containsKey(utid)) {//包含这个utid,就跳过添加步骤.
                UnitTest u = new UnitTest();//试题对象
                //包装对象
                u.setUtid(utid);
                u.setAnswer(String.valueOf(map.get("answer")));
                u.setScore(Integer.parseInt(String.valueOf(map.get("score"))));
                u.setUcontent(String.valueOf(map.get("ucontent")));
                u.setTestType(map.get("testType") == null ? 0 : Integer.parseInt(String.valueOf(map.get("testType"))));
                u.setIsMoreChoose(map.get("isMoreChoose") == null ? null
                        : Integer.parseInt(String.valueOf(map.get("isMoreChoose"))));
                if (map.get("utimg") != null) {//题目图片
                    u.setUtimg(String.valueOf(map.get("utimg")));
                }
                if (map.get("ponit") != null) {//考点
                    u.setPonit(String.valueOf(map.get("ponit")));
                }
                if (map.get("detail") != null) {//详解
                    u.setDetail(String.valueOf(map.get("detail")));
                }
                utlist.add(u);

                //添加到集合中.防止重复添加.
                uniqueMap.put(utid, null);
            }

            if (map.get("csid") != null) {
                UnitTest_Choose uc = new UnitTest_Choose();
                uc.setCsid(String.valueOf(map.get("csid")));
                uc.setTcontent(map.get("tcontent") == null ? "" : String.valueOf(map.get("tcontent")));
                uc.setTanswer(map.get("tanswer") == null ? "" : String.valueOf(map.get("tanswer")));
                uc.setUtid(utid);

                if (ucData.get(utid) != null) {
                    List<UnitTest_Choose> list2 = ucData.get(utid);
                    clist.addAll(list2);
                }
                clist.add(uc);
                ucData.put(utid, clist);
            }

        }
        mapRes.put("ucData", ucData);
        mapRes.put("utlist", utlist);
        return mapRes;
    }

    // 修改测验题
    @RequestMapping("/back/updateUnitTestClass")
    public void updateUnitTestClass(HttpServletRequest req, HttpServletResponse response, UnitTest u, String[] answer)
            throws Exception {
        String res = "ok";
        List<UnitTest_Choose> uclist_add = new ArrayList<UnitTest_Choose>();
        List<UnitTest_Choose> uclist_edit = new ArrayList<UnitTest_Choose>();
        try {
            u.setAnswer(BaseUtil.arrayToStr(answer));
            userService.updateUnitTest(u);// 修改测验题
            if (u.getTestType() == 0) {
                // 新增选项
                String[] tcontentArr = req.getParameterValues("tcontent");
                if (tcontentArr != null) {
                    String[] tanswerArr = req.getParameterValues("tanswer");
                    Long sort = new Date().getTime();
                    for (int i = 0; i < tcontentArr.length; i++) {
                        UnitTest_Choose uc = new UnitTest_Choose();
                        uc.setCsid(UUIDUtil.getUUID());
                        uc.setTcontent(tcontentArr[i]);
                        uc.setTanswer(tanswerArr[i]);
                        uc.setUtid(u.getUtid());
                        uc.setSort(sort + i);
                        uclist_add.add(uc);
                    }
                    userService.insertUnitTest_Choose(uclist_add);
                }

                // 修改选项
                String[] editIdArr = req.getParameterValues("editId");
                if (editIdArr != null) {
                    String[] cArr = req.getParameterValues("editContent");
                    String[] aArr = req.getParameterValues("editAnswer");
                    for (int i = 0; i < editIdArr.length; i++) {
                        UnitTest_Choose uc = new UnitTest_Choose();
                        uc.setCsid(editIdArr[i]);
                        uc.setTcontent(cArr[i]);
                        uc.setTanswer(aArr[i]);
                        uclist_edit.add(uc);
                    }
                    userService.updateUnitTest_Choose(uclist_edit);
                }

                // 删除选项
                String[] delIdArr = req.getParameterValues("delId");
                if (delIdArr != null) {
                    userService.delUnitTest_Choose(delIdArr);
                }
            }
        } catch (Exception e) {
            res = "修改测验题失败：" + e.getMessage();
            log.error("修改测验题【utid:" + u.getUtid() + "】异常：", e);
        }

        PrintHelper.sendJsonString(response, res);
    }

    /**
     * 拼接章节测验题列表
     */
    @RequestMapping("/back/getUnitTestHtml")
    public void getUnitTestHtml(HttpServletRequest req, HttpServletResponse response, String utid, String utype)
            throws Exception {
        Map<String, String> res = new HashMap<String, String>();
        String status = "ok";
        String data = "";
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("vid", utid);
            map.put("utype", utype);
            List<Map> list = userService.selectVidoeTestAll(map);//得到 选择题 和 选项
            Map<String, Object> resMap = getUnitTests(list);//得到试题和选项 映射 集合.

            List<UnitTest> utlist = (List<UnitTest>) resMap.get("utlist");
            Map<String, List<UnitTest_Choose>> ucData = (Map<String, List<UnitTest_Choose>>) resMap.get("ucData");

            StringBuffer sb = new StringBuffer();
            sb.append(
                    "<tr>" +
                            "<th>序号</th>" +
                            "<th>题目</th>" +
                            "<th>题目图片</th>" +
                            "<th>题目类型</th>" +
                            "<th>是否多选</th>" +
                            "<th>答案</th>" +
                            "<th>选项</th>" +
                            "<th>分数</th>" +
                            "<th width=\"20%\">操作</th>" +
                            "</tr>");

            for (int i = 0; i < utlist.size(); i++) {//遍历试题集合
                UnitTest ut = utlist.get(i);
                String ucontent = ut.getUcontent();
                sb.append("<tr id=\"tr_" + ut.getUtid() + "\">"
                        + "<td>" + (i + 1) + "</td>"
                        + "<td>" + (ucontent.length() > 30 ? ucontent.substring(0, 30) + "..." : ucontent) + "</td>"
                        + "<td>" + (ut.getUtimg() == null || ut.getUtimg().length() == 0 ? ""
                        : "<img src=\"" + ut.getUtimg() + "\" style=\"width:40px;height:40px;\">")
                        + "</td>"
                        + "<td>" + (ut.getTestType() == 1 ? "简答题" : "选择题") + "</td>"
                        + "<td>" + (ut.getTestType() != 1 ? (ut.getIsMoreChoose() == 1 ? "是" : "否") : "") + "</td>"
                        + "<td>" + ut.getAnswer() + "</td>"
                        + "<td style=\"text-align:left;color:#00CC99;\">");

                List<UnitTest_Choose> uclist = ucData.get(ut.getUtid());// 选项
                if (uclist != null) {
                    for (UnitTest_Choose us : uclist) {
                        sb.append(us.getTanswer() + "." + us.getTcontent() + "<br>");
                    }
                }
                sb.append("</td>");
                sb.append("<td>" + ut.getScore() + "</td><td><div class=\"button-group\">");
                sb.append("<a class=\"button border-main border-up\" onclick=\"toEdit('" + ut.getUtid()
                        + "')\" ><span class=\"icon-edit\"></span> 修改</a>");
                sb.append("<a class=\"button border-red\"  onclick=\"del('" + ut.getUtid()
                        + "')\" ><span class=\"icon-trash-o\"></span>删除</a></div> </td></tr>");
            }
            data = sb.toString();
        } catch (Exception e) {
            log.error(" 拼接章节测验题列表异常：", e);
            status = "error";
            data = "拼接章节测验题列表出错：" + e.getMessage();
        }
        res.put("status", status);
        res.put("data", data);
        PrintHelper.sendJsonObject(response, res);
    }

    // 根据视频id和类型查询所有测试题 并拼接前端html
    @RequestMapping("/back/getUnitTestsHtmlByVid")
    public void getUnitTestsHtmlByVid(HttpServletRequest req, HttpServletResponse response, HttpSession session,
                                      String vid, String utype, int isRedo, String vname) throws Exception {
        String status = "ok";
        String data = "";

        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("vid", vid);
            map.put("utype", utype);
            map.put("rand", "true");// 是否随机
            List<Map> list = userService.selectVidoeTestAll(map);

            Map<String, Object> resMap = getUnitTests(list);
            List<UnitTest> utlist = (List<UnitTest>) resMap.get("utlist");

            if (utlist.size() > 0) {
                Map<String, List<UnitTest_Choose>> ucData = (Map<String, List<UnitTest_Choose>>) resMap.get("ucData");
                // 拼接前端测试题html
                StringBuffer sb_html = new StringBuffer();
                if (isRedo == 0) {// 初始化测验题
                    List<Map> mapList = userService.getTotalUnitTests(vid, Integer.parseInt(utype));
                    if (mapList.size() > 0) {
                        Map mmap = mapList.get(0);
                        String nums = String.valueOf(mmap.get("nums"));
                        String scores = String.valueOf(mmap.get("scores"));

                        if (mapList.size() > 1) {
                            Map mmap2 = mapList.get(1);
                            String nums2 = String.valueOf(mmap2.get("nums"));
                            String scores2 = String.valueOf(mmap2.get("scores"));
                            nums = (Integer.parseInt(nums) + Integer.parseInt(nums2)) + "(选择题" + nums + "道，简答题" + nums2
                                    + "道)";
                            scores = (Integer.parseInt(scores) + Integer.parseInt(scores2)) + "(选择题" + scores + "分，简答题"
                                    + scores2 + "分)";
                        }
                        int isHasJDT = 0;// 只有选择题
                        if (mapList.size() == 1 && String.valueOf(mmap.get("testType")).equals("1")) {// 只有简答题
                            isHasJDT = 1;
                        } else if (mapList.size() > 1) {// 二者皆有
                            isHasJDT = 2;
                        }

                        sb_html.append(
                                "<div style=\"width:100%;background:#BDBDBD;font-weight:bold;padding:10px;text-align:left;\">"
                                        + "<ul>"
                                        + "<li>名称：" + vname + "&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;题目数：" + nums + " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总分：" + scores + "</li>"
                                        + "<li>&nbsp;</li>"
                                        + "<li>说明："
                                        + "<input type=\"hidden\" id=\"isHasJDT\" value=\"" + isHasJDT + "\">"
                                        + "<input type=\"hidden\" id=\"nums_hide\" value=\"" + nums + "\">"
                                        + "<input type=\"hidden\" id=\"scores_hide\" value=\"" + scores + "\">"
                                        + "</li>"
                                        + "<li style=\"color:#CE0221;\">提示：选择题选项顺序为随机排列，若要核对答案，请以选项内容为准</li>"
                                        + "</ul>"
                                        + "</div>");
                    }
                    sb_html.append("<div id=\"unit_testAll\">");
                }

                int size = utlist.size();//本章试题数
                for (int i = 0; i < size; i++) {
                    UnitTest ut = utlist.get(i);
                    List<UnitTest_Choose> ulist = ucData.get(ut.getUtid());
                    if (i == 2) {// 第3题开始 -- 隐藏试题
                        sb_html.append("<div style=\"display:" + (isRedo == 0 ? "none" : "block") + ";text-align:left;\" id=\"more_div\">");
                    }
                    sb_html = getHtml(sb_html, ut, ulist, i + 1);

                    if (i == size - 1) {// 最后一道题的打分按钮
                        sb_html.append(" <div style=\"width:100%;padding:10px;text-align:left;\">"
                                + "<input type=\"button\" class=\"btn btn-primary\"  value=\"提交打分\"  id=\"submitAnswer_btn\" onclick=\"submitAnswer()\">"
                                + " <input type=\"button\" class=\"btn btn-primary\" value=\"重新做题\" onclick=\"redo()\">"
                                + " <h4 style=\"display:none;\" id=\"result_tests\">答题结果：</h4>"
                                + "</div>");
                        if (size > 2) {
                            sb_html.append("</div>");
                        }
                    }

                }
                if (size > 2) {
                    sb_html.append("<input type=\"button\" class=\"btn btn-primary\" value=\""
                            + (isRedo == 0 ? "展开全部" : "隐藏") + "\" onclick=\"slideDown_div2(this)\">");
                }
                if (isRedo == 0) {
                    sb_html.append("</div>");
                }
                data = sb_html.toString();
            }

        } catch (Exception e) {
            log.error("根据视频id和类型查询所有测试题并拼接前端html【vid:" + vid + "】异常：", e);
            status = "error";
        }
        Map<String, String> res = new HashMap<String, String>();
        res.put("status", status);
        res.put("data", data);
        PrintHelper.sendJsonObject(response, res);
    }

    private StringBuffer getHtml(StringBuffer sb_html, UnitTest ut, List<UnitTest_Choose> ulist, int i) {
        String answer = ut.getAnswer();
        StringBuffer show_answer = new StringBuffer();
        StringBuffer answer_ids = new StringBuffer();
        StringBuffer html_sb = new StringBuffer();
        if (ut.getTestType() == 0) {//测验题目类型：0/null：选择题；1：简答题
            String[] answer_a = new String[]{"A", "B", "C", "D", "E", "F"};
            for (int j = 0; j < ulist.size(); j++) {
                UnitTest_Choose u = ulist.get(j);
                if (answer.indexOf(u.getTanswer()) > -1) {
                    if (answer_ids.length() > 0) {
                        answer_ids.append(",");
                        show_answer.append(",");
                    }
                    answer_ids.append(u.getCsid());
                    show_answer.append(answer_a[j]);
                }
                html_sb.append("<input type=\"checkbox\" value=\"" + u.getCsid() + "\" onclick=\"checkBox(this,'hide_" + ut.getUtid() + "')\"> "
                        + answer_a[j] + ". " + u.getTcontent() + "<br>");
            }

            html_sb.append("<input type=\"hidden\" name=\"right_answer\" value=\"" + answer_ids.toString() + "\"> "
                    + "<input type=\"hidden\" name=\"user_answer\" id=\"hide_" + ut.getUtid() + "\">"
                    + "<input type=\"hidden\" name=\"score\" value=\"" + ut.getScore() + "\">"
                    + "</div>");
        }
        //简答题 格式
        else {
            html_sb.append("<textarea class=\"input\" name=\"JDT_answer\" id=\"JDT_" + ut.getUtid()
                    + "\" style=\"width:600px;height:120px;\" placeholder=\"答题区\"></textarea></div>");
        }

        String testType = "简答题";
        String answerHtml = "";
        if (ut.getTestType() == 0) {
            testType = ut.getIsMoreChoose() == 0 ? "单选题" : "多选题";
            answerHtml = "<span id=\"sp_" + ut.getUtid() + "\" style=\"font-size:16px;color:#CE0221;font-weight:bold;display:none;\">✘" + "&nbsp;&nbsp;"
                    + "<span style=\"font-size:16px;font-weight:bold;\">正确答案：(" + (show_answer.length() == 0 ? "无" : show_answer.toString()) + ")</span></span>";
        }
        html_sb.insert(0,
                "<div style=\"width:100%;background:#EEEEEE;word-break:break-all;margin-top:10px;padding:5px;text-align:left;\">"
                        + "<font style=\"font-weight:bold;\"> " + i + ".【" + testType + "】（" + ut.getScore() + "分）：</font>"
                        + "<h4>&nbsp;&nbsp;&nbsp;" + ut.getUcontent() + "&nbsp;&nbsp;" + answerHtml + "</h4>"
                        + "</div>"
                        + (ut.getUtimg() != null && ut.getUtimg().length() > 0 ?
                        "<div style=\"width:100%;margin-top:5px;\"><img src=\"" + ut.getUtimg() + "\"></div>" : "")
                        + "<div style=\"width:100%;margin-top:5px;display:none;\" id=\"hideDiv_" + ut.getUtid() + "\">"
                        + (ut.getPonit() == null || ut.getPonit().length() == 0 ? ""
                        : "<p style=\"width:100%;word-break:break-all;font-weight:bold;font-size:16px;\">【考点】：" + ut.getPonit() + "</p>")
                        + (ut.getDetail() == null || ut.getDetail().length() == 0 ? ""
                        : "<p style=\"font-size:16px;width:100%;word-break:break-all;\"><b>【详解】：</b>" + ut.getDetail() + "</p>")
                        + "</div>"
                        + "<div style=\"width:100%;word-break:break-all;margin-top:5px;\">");

        sb_html.append(html_sb);
        return sb_html;
    }

    // 新增学生成绩
    @RequestMapping("/back/addUserScore")
    public void addUserScore(HttpServletRequest req, HttpServletResponse response, HttpSession session, UserScore uc) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            try {
                uc.setUsid(UUIDUtil.getUUID());
                uc.setUid(user.getUid());
                if (uc.getIsHasJDT() == null || uc.getIsHasJDT() == 0) {
                    uc.setGotScore(uc.getScore());
                }
                userService.insertUserScore(uc);
                if (uc.getIsHasJDT() != 0) {
                    try {
                        // 批量新增学生简答题答案
                        List<Map> JDT_answer = JSONArray.parseArray(req.getParameter("JDT_answer"), Map.class);
                        List<ErrorExam> eelist = new ArrayList<ErrorExam>();
                        if (JDT_answer.size() > 0) {
                            for (Map<String, String> m : JDT_answer) {
                                String jdt_answer = m.get("jdt_answer");
                                String jdt_id = m.get("jdt_id");
                                ErrorExam ee = new ErrorExam();
                                ee.setEeid(UUIDUtil.getUUID());
                                ee.setSort(new Date().getTime());
                                ee.setStuAnswer(jdt_answer);
                                ee.setUsid(uc.getUsid());
                                ee.setUtid(jdt_id);
                                eelist.add(ee);
                            }

                            userService.insertErrorExam(eelist);
                        }

                    } catch (Exception e) {
                        log.error("批量新增学生简答题答案【usid：" + uc.getUsid() + "】异常：", e);
                    }
                }
                if (uc.getUtype() == 4) {// 科目小测验--修改小测验学习人数
                    try {
                        examService.updateLearnCount(uc.getVid());
                    } catch (Exception e) {
                        log.error("修改小测验学习人数异常：", e);
                    }

                }
            } catch (Exception e) {
                log.error("新增学生成绩异常：", e);
            }
        }
    }

    /**
     * 教师批改试卷页面跳转
     */
    @RequestMapping("/back/correctIndex.html")
    public void correctIndex(HttpServletRequest req, HttpSession session, Model mode) {

        String tname = (String) session.getAttribute("tname");
        int pageNo = 0;
        int pageSize = 15;
        if (req.getParameter("pageNo") != null && StringUtil.isNumeric(req.getParameter("pageNo"))) {
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        if (pageNo < 1) {
            pageNo = 1;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tname", tname);
        try {
            mode.addAttribute("pages", userService.searchUnitTestList(map, pageNo, pageSize));
        } catch (Exception e) {
            log.error("教师批改试卷页面跳转异常：", e);
        }
    }

    /**
     * 教师批改单个试卷的简答题
     */
    @RequestMapping("/back/goToCorrectUnitTest.html")
    public void goToCorrectUnitTest(HttpServletRequest req, HttpSession session, Model model, String usid, String gname) {
        List<Map> jdt_list = null;
        try {
            jdt_list = userService.getJDTScoresDetail(usid);
        } catch (Exception e) {
            log.info("查看简答题详情出错:", e);
        }
        if (session.getAttribute("error") != null) {
            model.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
            gname = (String) session.getAttribute("gname");
            usid = (String) session.getAttribute("usid");
            session.removeAttribute("gname");
            session.removeAttribute("usid");
            session.removeAttribute("tname");
        }
        model.addAttribute("jdt_list", jdt_list);
        model.addAttribute("gname", gname);
        model.addAttribute("usid", usid);
    }

    // 批量修改简答题得分
    @RequestMapping(value = "/back/addUnitTestScoresClass", method = RequestMethod.POST)
    public ModelAndView addUnitTestScoresClass(HttpServletRequest req, HttpSession session, String usid, String gname) {
        String res = "";
        try {
            String[] eeid = req.getParameterValues("eeid");
            int totalScore = 0;
            try {
                if (eeid != null && eeid.length > 0) {
                    String[] stuGotScore = req.getParameterValues("stuGotScore");
                    String[] coment = req.getParameterValues("coment");
                    List<ErrorExam> elist = new ArrayList<ErrorExam>();
                    for (int i = 0; i < eeid.length; i++) {
                        ErrorExam ee = new ErrorExam();
                        ee.setEeid(eeid[i]);
                        int score = stuGotScore[i].length() == 0 ? 0 : Integer.parseInt(stuGotScore[i]);
                        ee.setStuGotScore(score);
                        ee.setComent(coment[i]);
                        elist.add(ee);
                        totalScore += score;
                    }
                    userService.updateErrorExam(elist);
                }

            } catch (Exception e) {
                res = "批量修改简答题得分出错，请联系管理员！";
                log.error("批量修改简答题得分出错：", e);
            }
            UserScore us = new UserScore();
            us.setUsid(usid);
            us.setJDTscore(totalScore);
            userService.updateUserScore(us);
            if (res.length() == 0) {
                return new ModelAndView("redirect:/back/correctIndex.html");
            }
        } catch (Exception e) {
            res = "修改简答题总得分出错，请联系管理员！";
            log.error("修改简答题总得分出错：", e);
        }
        if (res.length() > 0) {
            session.setAttribute("error", res);
            session.setAttribute("usid", usid);
            session.setAttribute("gname", gname);
        }
        return new ModelAndView("redirect:/back/goToCorrectUnitTest.html");
    }

    /**
     * 添加小测验试卷页面跳转
     */
    @RequestMapping("/back/exam/addExams.html")
    public void addExams(HttpServletRequest req, HttpSession session, Model mode, String etid) {
        try {
            Exam exam = examService.findExamByEtid(etid);
            mode.addAttribute("exam", exam);// 测验题
            if (StringUtils.isNotEmpty(exam.getEtimg()) && exam.getEtimg().equals("1")) {
                List<ExamImg> imgList = examService.selectExamImgByExid(etid);
                mode.addAttribute("imgList", imgList);// 测验题图片和答案
            }
        } catch (Exception e) {
            mode.addAttribute("error", "查询小测验试卷出错：" + e.getMessage());
            log.error("查询小测验试卷【vid:" + etid + "】出错：", e);
        }
    }

    /**
     * 添加小测验试卷
     */
    @RequestMapping(value = "/back/updateExams", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView updateExams(HttpServletRequest req, HttpSession session,
                                    @RequestParam(value = "fm", required = false) MultipartFile[] fm,
                                    @RequestParam(value = "file1", required = false) MultipartFile[] files,
                                    @RequestParam(value = "file1a", required = false) MultipartFile[] afiles, Exam exam, String[] delImhid) {
        try {
            exam = this.getImgList(req, exam, fm, files, afiles);
            examService.updateExam(exam);
            if (delImhid != null && delImhid.length > 0) {
                examService.deleteExamImgs(delImhid);
            }
            if (exam.getImgList().size() > 0) {
                examService.insertExamImgs(exam.getImgList());
            }
            // 3.跳转回列表页面
            if (exam.getEtsubject().equals("语文测验")) {
                return new ModelAndView("redirect:/back/exam/chinese/chineseExam.html");
            } else if (exam.getEtsubject().equals("数学测验")) {
                return new ModelAndView("redirect:/back/exam/math/mathExam.html");
            } else {
                return new ModelAndView("redirect:/back/exam/english/englishExam.html");
            }
        } catch (Exception e) {
            session.setAttribute("error", "出错了：" + e.getMessage());
            log.error("修改添加小测验试卷【id:" + exam.getEtid() + "】出错了：", e);
            return new ModelAndView("redirect:/back/exam/addExams.html?etid=" + exam.getEtid());
        }

    }
}
