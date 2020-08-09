package com.hpedu.web.core.back;

import com.alibaba.fastjson.JSONArray;
import com.hpedu.util.ResultBean;
import com.hpedu.util.codeUtil.BaseUtil;
import com.hpedu.util.codeUtil.PrintHelper;
import com.hpedu.web.core.exam.service.ExamService;
import com.hpedu.web.core.order.service.OrderService;
import com.hpedu.web.core.shiro.pojo.SysUserEntity;
import com.hpedu.web.core.teacher.service.TeacherService;
import com.hpedu.web.core.user.pojo.UnitTest;
import com.hpedu.web.core.user.pojo.UnitTest_Choose;
import com.hpedu.web.core.user.pojo.User;
import com.hpedu.web.core.user.pojo.UserScore;
import com.hpedu.web.core.user.service.UserService;
import com.hpedu.web.core.video.service.ContestVideoService;
import com.hpedu.web.core.video.service.GeneralVideoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
     * 根据 试题和试题选项  得到-->
     *
     * @param list
     * @return
     */
    private Map<String, Object> getUnitTests(List<Map> list) {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, List<UnitTest_Choose>> chooseMap = new HashMap<String, List<UnitTest_Choose>>();//题目 对应的 选项集合 映射
        List<UnitTest> utlist = new ArrayList<UnitTest>();//试题集合

        Map<String, String> uniqueMap = new HashMap<String, String>();

        for (Map map : list) {//编历得到每一个试题

            if (map != null) {
                String utid = String.valueOf(map.get("utid"));
                List<UnitTest_Choose> chooseList = new ArrayList<UnitTest_Choose>();//选项集合

                if (!uniqueMap.containsKey(utid)) {//包含这个utid,就跳过添加步骤.
                    UnitTest unitTest = new UnitTest();//试题对象
                    //包装对象
                    unitTest.setUtid(utid);
                    unitTest.setAnswer(String.valueOf(map.get("answer")));
                    unitTest.setScore(Integer.parseInt(String.valueOf(map.get("score"))));
                    unitTest.setUcontent(String.valueOf(map.get("ucontent")));
                    unitTest.setTestType(map.get("testType") == null ? 0 : Integer.parseInt(String.valueOf(map.get("testType"))));
                    unitTest.setIsMoreChoose(map.get("isMoreChoose") == null ? null
                            : Integer.parseInt(String.valueOf(map.get("isMoreChoose"))));
                    if (map.get("utimg") != null) {//题目图片
                        unitTest.setUtimg(String.valueOf(map.get("utimg")));
                    }
                    if (map.get("ponit") != null) {//考点
                        unitTest.setPonit(String.valueOf(map.get("ponit")));
                    }
                    if (map.get("detail") != null) {//详解
                        unitTest.setDetail(String.valueOf(map.get("detail")));
                    }
                    utlist.add(unitTest);

                    //添加到集合中.防止重复添加.
                    uniqueMap.put(utid, null);
                }

                if (map.get("csid") != null) {
                    UnitTest_Choose uc = new UnitTest_Choose();
                    uc.setCsid(String.valueOf(map.get("csid")));
                    uc.setTcontent(map.get("tcontent") == null ? "" : String.valueOf(map.get("tcontent")));
                    uc.setTanswer(map.get("tanswer") == null ? "" : String.valueOf(map.get("tanswer")));
                    uc.setUtid(utid);

                    if (chooseMap.get(utid) != null) {
                        List<UnitTest_Choose> list2 = chooseMap.get(utid);
                        chooseList.addAll(list2);
                    }
                    chooseList.add(uc);
                    chooseMap.put(utid, chooseList);
                }
            }

        }
        result.put("ucData", chooseMap);
        result.put("utlist", utlist);
        return result;
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
            Map<String, Object> resMap = getUnitTests(userService.selectVidoeTestAll(map));
            List<UnitTest> unitTestList = (List<UnitTest>) resMap.get("utlist");//单元测试题

            if (unitTestList.size() > 0) {
                /*选择题*/
                Map<String, List<UnitTest_Choose>> chooseMap = (Map<String, List<UnitTest_Choose>>) resMap.get("ucData");

                // 拼接前端测试题html
                StringBuilder unitTestPage = new StringBuilder();
                if (isRedo == 0) {// 初始化测验题
                    /*获得某种试题 总体 数量,分值,*/
                    List<Map> mapList = userService.getTotalUnitTests(vid, Integer.parseInt(utype));
                    if (mapList.size() > 0) {
                        Map mmap = mapList.get(0);/*选择题*/
                        String nums_show = String.valueOf(mmap.get("nums"));
                        int choose_num = Integer.parseInt(StringUtils.isNotBlank(nums_show) ? nums_show : "0");
                        String scores_show = String.valueOf(mmap.get("scores"));
                        int choose_score = Integer.parseInt(StringUtils.isNotBlank(scores_show) ? scores_show : "0");

                        int shortAnswer_num = 0;
                        int shortAnswer_score = 0;

                        if (mapList.size() > 1) {
                            Map mmap2 = mapList.get(1);/*简答题*/
                            String nums2 = String.valueOf(mmap2.get("nums"));
                            shortAnswer_num = Integer.parseInt(StringUtils.isNotBlank(nums2) ? nums2 : "0");
                            String scores2 = String.valueOf(mmap2.get("scores"));
                            shortAnswer_score = Integer.parseInt(StringUtils.isNotBlank(scores2) ? scores2 : "0");

                            nums_show = (choose_score + shortAnswer_num) + "(选择题" + choose_num + "道，简答题" + shortAnswer_num + "道)";
                            scores_show = (choose_score + shortAnswer_score) + "(选择题" + choose_score + "分，简答题" + shortAnswer_score + "分)";
                        }

                        int isHasJDT = 0;// 只有选择题
                        if (mapList.size() == 1 && String.valueOf(mmap.get("testType")).equals("1")) {// 只有简答题
                            isHasJDT = 1;
                        } else if (mapList.size() > 1) {// 二者皆有
                            isHasJDT = 2;
                        }

                        unitTestPage.append(
                                "<div style=\"width:100%;background:#BDBDBD;font-weight:bold;padding:10px;text-align:left;\">"
                                        + "<ul>"
                                        + "<li>测试项目：" + vname + ",试题：" + nums_show + ",分值：" + scores_show + "</li>"
                                        + "<li>&nbsp;</li>"
                                        + "<li>说明："
                                        + "<input type=\"hidden\" id=\"isHasJDT\" value=\"" + isHasJDT + "\">"
                                        + "<input type=\"hidden\" id=\"nums_hide\" value=\"" + (choose_num + shortAnswer_num) + "\">"
                                        + "<input type=\"hidden\" id=\"scores_hide\" value=\"" + (choose_score + shortAnswer_score) + "\">"
                                        + "</li>"
                                        + "<li style=\"color:#CE0221;\">提示：选择题选项顺序为随机排列，若要核对答案，请以选项内容为准</li>"
                                        + "</ul>"
                                        + "</div>");
                    }
                    unitTestPage.append("<div id=\"unit_testAll\">");
                }

                int size = unitTestList.size();//本章试题数
                for (int i = 0; i < size; i++) {
                    UnitTest ut = unitTestList.get(i);
                    List<UnitTest_Choose> ulist = chooseMap.get(ut.getUtid());
                    if (i == 2) {// 第3题开始 -- 隐藏试题
                        unitTestPage.append("<div style=\"display:" + (isRedo == 0 ? "none" : "block") + ";text-align:left;\" id=\"more_div\">");
                    }
                    /*填装试题内容*/
                    unitTestPage = pushUnitTest(unitTestPage, ut, ulist, i + 1);

                    if (i == size - 1) {// 最后一道题的打分按钮
                        unitTestPage.append(" <div style=\"width:100%;padding:10px;text-align:left;\">"
                                + "<input type=\"button\" class=\"btn btn-primary\"  value=\"提交打分\"  id=\"submitAnswer_btn\" onclick=\"submitAnswer()\">"
                                + " <input type=\"button\" class=\"btn btn-primary\" value=\"重新做题\" onclick=\"redo()\">"
                                + " <h4 style=\"display:none;\" id=\"result_tests\">答题结果：</h4>"
                                + "</div>");
                        if (size > 2) {
                            unitTestPage.append("</div>");
                        }
                    }

                }
                if (size > 2) {
                    unitTestPage.append("<input type=\"button\" class=\"btn btn-primary\" value=\""
                            + (isRedo == 0 ? "展开全部" : "隐藏") + "\" onclick=\"slideDown_div2(this)\">");
                }
                if (isRedo == 0) {
                    unitTestPage.append("</div>");
                }
                data = unitTestPage.toString();
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

    private StringBuilder pushUnitTest(StringBuilder unitTestPage, UnitTest ut, List<UnitTest_Choose> unitTestChooseList, int unitTestNum) {
        String answer = ut.getAnswer();
        StringBuilder show_answer = new StringBuilder();
        StringBuilder answer_ids = new StringBuilder();
        StringBuilder thisUnitTest = new StringBuilder();

        /*选择题渲染*/
        if (ut.getTestType() == 0) {//测验题目类型：0/null：选择题；1：简答题
            String[] answer_a = new String[]{"A", "B", "C", "D", "E", "F"};
            for (int j = 0; j < unitTestChooseList.size(); j++) {
                UnitTest_Choose thisChoose = unitTestChooseList.get(j);

                if (answer.indexOf(thisChoose.getTanswer()) > -1) {//选项为正确选项之一
                    if (answer_ids.length() > 0) {
                        answer_ids.append(",");
                        show_answer.append(",");
                    }
                    answer_ids.append(thisChoose.getCsid());
                    show_answer.append(answer_a[j]);
                }
                /*填装选项*/
                thisUnitTest.append("<input type=\"checkbox\" value=\"" + thisChoose.getCsid() + "\" onclick=\"checkBox(this,'hide_" + ut.getUtid() + "')\"> "
                        + answer_a[j] + ". " + thisChoose.getTcontent() + "<br>");
            }
            /*隐藏答案, 自动对比 打分*/
            thisUnitTest.append("   <input type=\"hidden\" name=\"right_answer\" value=\"" + answer_ids.toString() + "\"> "
                    + "<input type=\"hidden\" name=\"user_answer\" id=\"hide_" + ut.getUtid() + "\">"
                    + "<input type=\"hidden\" name=\"score\" value=\"" + ut.getScore() + "\">"
                    + "</div>");
        }

        //简答题渲染
        else {
            thisUnitTest.append("<textarea class=\"input\" name=\"JDT_answer\" id=\"JDT_" + ut.getUtid()
                    + "\" style=\"width:600px;height:120px;\" placeholder=\"答题区\">" +
                    "</textarea>" +
                    "</div>");
        }


        String testType = "简答题";
        StringBuilder rightAnswer = new StringBuilder("");
        if (ut.getTestType() == 0) {
            testType = ut.getIsMoreChoose() == 0 ? "单选题" : "多选题";
            /*选择题 答案 (隐藏)*/
            rightAnswer.append("<span id=\"sp_" + ut.getUtid() + "\" style=\"font-size:16px;color:#CE0221;font-weight:bold;display:none;\">✘"
                    + "<span style=\"font-size:16px;font-weight:bold;\">正确答案：(" + (show_answer.length() == 0 ? "无" : show_answer.toString()) + ")</span>" +
                    "</span>");
        }
        /*插入 题目详情:*/
        thisUnitTest.insert(0,
                "<div style=\"width:100%;background:#EEEEEE;word-break:break-all;margin-top:10px;padding:5px;text-align:left;\">"
                        + "<font style=\"font-weight:bold;\"> " + unitTestNum + ".【" + testType + "】（" + ut.getScore() + "分）：</font>"
                        + "<h4>" + ut.getUcontent() + "," + rightAnswer.toString() + "</h4>"
                        + "</div>"
                        + (StringUtils.isBlank(ut.getUtimg()) ? "" :
                        "<div style=\"width:100%;margin-top:5px;\"><img src=\"" + ut.getUtimg() + "\"></div>")

                        + "<div style=\"width:100%;margin-top:5px;display:none;\" id=\"hideDiv_" + ut.getUtid() + "\">"
                        + (StringUtils.isBlank(ut.getPonit()) ? ""
                        : "<p style=\"width:100%;word-break:break-all;font-weight:bold;font-size:16px;\">【考点】：" + ut.getPonit() + "</p>")
                        + (StringUtils.isBlank(ut.getDetail()) ? ""
                        : "<p style=\"font-size:16px;width:100%;word-break:break-all;\"><b>【详解】：</b>" + ut.getDetail() + "</p>")
                        + "</div>"
                        + "<div style=\"width:100%;word-break:break-all;margin-top:5px;\">");

        unitTestPage.append(thisUnitTest);
        return unitTestPage;
    }


    //记录学生答题成绩
    @RequestMapping("/back/addUserScore")
    @ResponseBody
    public ResultBean addUserScore(HttpServletRequest req, HttpSession session, UserScore stuScore) {
        List<Map> JDTAnswerList = JSONArray.parseArray(req.getParameter("JDT_answer"), Map.class);
        User user = (User) session.getAttribute("user");
        return userService.addUserScore(user, stuScore, JDTAnswerList);
    }




}
