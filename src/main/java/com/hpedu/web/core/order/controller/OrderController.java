package com.hpedu.web.core.order.controller;


import com.alibaba.fastjson.JSONObject;
import com.hpedu.util.codeUtil.BaseUtil;
import com.hpedu.util.codeUtil.PrintHelper;
import com.hpedu.util.codeUtil.StringUtil;
import com.hpedu.util.codeUtil.UUIDUtil;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.order.pojo.ArticleImg;
import com.hpedu.web.core.order.pojo.Banner;
import com.hpedu.web.core.order.pojo.Order;
import com.hpedu.web.core.order.service.OrderService;
import com.hpedu.web.core.user.pojo.User;
import com.hpedu.web.core.video.pojo.ContestVideo;
import com.hpedu.web.core.video.pojo.GeneralVideo;
import com.hpedu.web.core.video.service.ContestVideoService;
import com.hpedu.web.core.video.service.GeneralVideoService;
import com.hpedu.web.core.wxpay.service.WxPayService;
import com.hpedu.web.core.wxpay.util.WechatPayUtil;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping("/")
public class OrderController {
    @Resource
    OrderService orderService;
    @Resource
    ContestVideoService contestVideoService;
    @Resource
    GeneralVideoService generalVideoService;
    @Resource
    WxPayService WxPayService;
    private Logger log = BaseUtil.getLogger(OrderController.class);

    @RequestMapping("/order/checkOrdersByUid.html")
    public void searchAllOrder(HttpServletRequest req, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Order> olist = null;
            try {
                olist = orderService.findAllOrderByUserId(user.getUid());
            } catch (Exception e) {
                log.info("查看用户订单信息出错:", e);
                olist = new ArrayList<Order>();
            }
            model.addAttribute("olist", olist);
        } else {
            model.addAttribute("msg", "您还没有登录！");
        }
    }

    //订单查看
    @RequestMapping("/back/orderCheck.html")
    public void orderCheck(HttpServletRequest req, HttpSession session, Model model) {
        int pageNo = 0;
        int pageSize = 20;
        if (req.getParameter("pageNo") != null
                && StringUtil.isNumeric(req.getParameter("pageNo"))) {
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        if (pageNo < 1) {
            pageNo = 1;
        }
        //Map<String,String>maps = new HashMap<String,String>();
        //Page<Order>pages = orderService.findOrderListByPage(maps, pageNo, pageSize);
        List<Order> list = null;
        int totalCount = 0;
        try {
            list = orderService.findOrderListByPage(pageNo, pageSize);
            totalCount = orderService.findOrderListCount();
        } catch (Exception e) {
            log.info("分页查看用户订单信息出错:", e);
            list = new ArrayList<Order>();
        }
        Page pages = new Page();
        pages.setResult(list);
        pages.setPageNo(pageNo);
        pages.setPageSize(pageSize);
        pages.setTotalCount(totalCount);
        model.addAttribute("pages", pages);

    }

    //视频检索
    @RequestMapping("/order/searchVideo.html")
    public void searchAllOrder(HttpServletRequest req, Model model, String keyword) {
        StringBuffer error = new StringBuffer();
        List<GeneralVideo> glist = null;
        List<ContestVideo> clist = null;
        try {
            glist = generalVideoService.searchGeneralVideoListByGname(keyword);
        } catch (Exception e) {
            error.append("常规视频查询失败：" + e.getMessage() + ";");
            glist = new ArrayList<GeneralVideo>();
        }
        try {
            clist = contestVideoService.searchContestVideoListByCname(keyword);
        } catch (Exception e) {
            error.append("竞赛视频查询失败：" + e.getMessage() + ";");
            clist = new ArrayList<ContestVideo>();
        }
        if (error.length() > 0) {
            log.error("首页视频关键词检索出错：" + error.toString());
        }
        model.addAttribute("glist", glist);
        model.addAttribute("clist", clist);

    }

    //分页查询更多视频
    @RequestMapping("/order/showAllVideo.html")
    public void showAllVideo(HttpServletRequest req, Model model, Integer type) {
        Map<String, String> maps = new HashMap<>();
        int pageNo = 0;
        int pageSize = 8;
        if (req.getParameter("pageNo") != null
                && StringUtil.isNumeric(req.getParameter("pageNo"))) {
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (type == 0) {//分页查常规
            Page<GeneralVideo> pages = null;
            try {
                pages = generalVideoService.searchGeneralVideoList(maps, pageNo, pageSize);
            } catch (Exception e) {
                pages = new Page<GeneralVideo>();
                pages.setResult(new ArrayList<GeneralVideo>());
                log.error("分页查询常规课程失败了", e);
            }
            model.addAttribute("pages", pages);

        } else if (type == 1) {//分页查竞赛
            Page<ContestVideo> pages = null;
            try {
                pages = contestVideoService.searchContestVideoList(maps, pageNo, pageSize);
            } catch (Exception e) {
                pages = new Page<ContestVideo>();
                pages.setResult(new ArrayList<ContestVideo>());
                log.error("分页查询常规课程失败了", e);
            }
            model.addAttribute("pages", pages);
        }
        model.addAttribute("type", type);
    }

    //生成订单号和二维码
    @PostMapping(value = "/order/getErweimaImg", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String, Object> getErweimaImg(String vid, String vclassify, String uid) /*throws IOException*/ {
        
        Map<String, Object> resMap = orderService.getWePayCodeImgMap(vid, vclassify, uid);

        return resMap;
    }

    
    //获取活动截止时间
    @RequestMapping("/order/getEndKillTime")
    @ResponseBody
    public Map<String, Object> getEndKillTime(HttpServletRequest req, HttpServletResponse response,
                                              String vid, String vclassify) throws IOException {

        String killStartTime = "";//秒杀开始时间
        String killEndTime = "";//秒杀截止时间
        if (vclassify.equals("0")) {//常规
            GeneralVideo g = generalVideoService.findGeneralVideoByVid(vid);
            killStartTime = g.getKillStartTime();
            killEndTime = g.getKillEndTime();
        } else {
            ContestVideo c = contestVideoService.findContestVideoById(vid);
            killStartTime = c.getKillStartTime();
            killEndTime = c.getKillEndTime();
        }
        return BaseUtil.getKillInfo(killStartTime, killEndTime);
    }

    

    //微信支付后回调方法
    @RequestMapping("/order/callBackAfterPay")
    @ResponseBody
    public String callBackAfterPay(HttpServletRequest req, HttpServletResponse response) throws IOException {
        Map<String, String> backDataMap = parseReturnXmlInfoFromWePay(req);//解析微信返回的数据
        String sendMsg = "";
        if (backDataMap != null) {
            log.info("支付回调信息：" + JSONObject.toJSON(backDataMap).toString());
            Map<String, String> map = new HashMap<String, String>();
            String return_msg = "";
            String return_code = backDataMap.get("return_code");//返回状态码 [SUCCESS/FAIL]
            
            if (return_code.equals("SUCCESS")) {
                
                if (backDataMap.get("result_code").equals("SUCCESS")) {
                    
                    if (WechatPayUtil.checkBackSign(backDataMap)) {//对于支付结果通知的内容做签名验证
                        String out_trade_no = backDataMap.get("out_trade_no");//订单号
                        String total_fee = backDataMap.get("total_fee");//实付金额（单位：分）
                        //校验返回的订单金额是否与商户的订单金额一致
                        Order order = orderService.findOrderByOrderNo(out_trade_no);
                        String omoney = BaseUtil.getWpayMOney(order.getOmoney());
                        String oispay = order.getOisPay();
                        if (omoney.equals(total_fee)) {
                            if (oispay.equals("0")) {
                                //修改订单状态
                                try {
                                    orderService.updateOrderPayStatus(out_trade_no);
                                } catch (Exception e) {
                                    log.info("修改定订单状态【订单号:" + out_trade_no + "】出错：", e);
                                    /*系统出错, 通知微信端 ,是否会退还 用户资金?*/
                                    return_code = "FAIL";
                                    return_msg = "修改订单状态异常";
                                }
                            } else {
                                return_code = "FAIL";
                                return_msg = "重复订单";
                            }
                        } else {
                            return_code = "FAIL";
                            return_msg = "订单金额不一致";
                        }
                    } else {
                        return_code = "FAIL";
                        return_msg = "签名错误";
                    }
                }
            } else {
                return_msg = backDataMap.get("return_msg");
            }
            map.put("return_msg", return_msg);
            map.put("return_code", return_code);
            sendMsg = WechatPayUtil.switchMap2Xml(map);
        } else {
            sendMsg = "解析返回数据异常!";
        }
        return sendMsg;
    }

    /**
     * 如果微信 未 回调接口, 需要手动调用 订单api,查询支付结果.
     * @param request
     * @return
     */
    
    
    //解析微信回调返回的值
    public Map<String, String> parseReturnXmlInfoFromWePay(HttpServletRequest request) {
        Map<String, String> map = null;
        try {
            // 解析结果存储在HashMap
            map = new HashMap<String, String>();
            InputStream inputStream = request.getInputStream();
            // 读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            // 得到xml根元素
            org.dom4j.Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();
            // 遍历所有子节点
            for (Element e : elementList)
                map.put(e.getName(), e.getText());
            // 释放资源
            inputStream.close();
            inputStream = null;
        } catch (Exception e) {
            log.info("解析微信回调返回的值出错：", e);
        }
        return map;
    }

    //检查订单是否支付
    @RequestMapping("/order/checkOrderIsBuy")
    public void checkOrderIsBuy(HttpServletRequest req, HttpServletResponse response,
                                String vid, String vclassify, String uid) throws IOException {
        List<Order> olist = null;
        try {
            olist = orderService.findOrderByParams(uid, vid, vclassify, "1");
        } catch (Exception e) {
            log.info("检查订单是否支付出错：", e);
        }
        String str = "error";
        if (olist != null && olist.size() > 0) {
            if (olist.get(0).getOisPay().equals("1")) {
                str = "ok";
            }
        }
        PrintHelper.sendJsonString(response, str);
    }

    //查看文章内容
    @RequestMapping("/banner/checkArticle")
    public ModelAndView checkArticle(HttpServletRequest req, HttpServletResponse response, String bid) {
        Banner b = null;
        ModelAndView mode = new ModelAndView();
        try {
            b = orderService.findBannerByBid(bid);
            try {
                List<ArticleImg> alist = orderService.selectArticleImgByBid(bid);
                mode.addObject("alist", alist);
            } catch (Exception e) {

            }
        } catch (Exception e) {
            log.info("查看文章内容出错【id:" + bid + "】:", e);
            b = new Banner();
        }

        mode.addObject("banner", b);
        mode.setViewName("order/checkArticle");
        return mode;

    }
}
