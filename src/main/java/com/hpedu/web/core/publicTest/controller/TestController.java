package com.hpedu.web.core.publicTest.controller;

import com.alibaba.fastjson.JSON;
import com.hpedu.util.codeUtil.BaseUtil;
import com.hpedu.util.codeUtil.PrintHelper;
import com.hpedu.util.codeUtil.StringUtil;
import com.hpedu.util.codeUtil.UUIDUtil;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.publicTest.pojo.Test;
import com.hpedu.web.core.publicTest.service.TestService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 测试 控制器
 * @author Administrator
 *
 */
@Controller
public class TestController {
	@Autowired
	private TestService testService ;
	
	
	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/back/test/addTest")
	public ModelAndView addTest(HttpServletRequest request){
		
		return new ModelAndView("/back/test/addTest");
	}
	
	/**
	 * 新增测验题 
	 * @param req
	 * @param response
	 * @param test
	 * @
	 */
	@RequestMapping(value="/addTest",method=RequestMethod.POST)
	public void addUnitTestClass(HttpServletRequest req, HttpServletResponse response, 
			String[] testAnswer,String[] option,String[] optionContent, Test test ) throws IOException {
		
		Map<String, String> res = new HashMap<String, String>();
		String status = "ok";
		String data = "";
		
		test.setTestAnswer(BaseUtil.arrayToStr(testAnswer));
		String TestId = UUIDUtil.getUUID();
		test.setId(TestId);
		test.setCreateTime(new Date());
		try {
			testService.insertTest(test,option,optionContent);
			
		/*	String ucontent = test.getUcontent() == null ? "" : test.getUcontent();
			data = "<tr id=\"tr_" + TestId + "\">"
					+ "<td>" + index + "</td>"
					+ "<td>" + (ucontent.length() > 30 ? ucontent.substring(0, 30) + "..." : ucontent) + "</td>"
					+ "<td>" + (test.getUtimg() == null || test.getUtimg().length() == 0 ? "" : "<img src=\"" + test.getUtimg() + "\" style=\"width:40px;height:40px;\">")+ "</td>"
					+ "<td>" + (test.getTestType() == 0 ? "选择题" : "简答题") + "</td>"
					+ "<td>" + (test.getTestType() != 1 ? (test.getIsMoreChoose() == 1 ? "是" : "否") : "") + "</td>"
					+ "<td>" + BaseUtil.arrayToStr(testAnswer) + "</td>"
					+ "<td style=\"text-align:left;color:#00CC99;\">" + tcHtml + "</td>"
					+ "<td>" + test.getScore() + "</td>"
					+ "<td>"
						+ "<div class=\"button-group\">"
							+ "<a class=\"button border-main border-up\" onclick=\"toEdit('" + TestId + "')\"  >" + "<span class=\"icon-edit\"></span> 修改</a>"
							+ "<a class=\"button border-red\" onclick=\"del('" + TestId + "')\" >" + "<span class=\"icon-trash-o\"></span> 删除</a>"
						+ "</div> "
					+ "</td>"
				+ "</tr>";
			*/
			
		} catch (Exception e) {
			System.out.println("新增测验题失败：" + e.getMessage());
			data = "新增测验题失败：" + e.getMessage() ;
			status = "error";
//			log.error("新增测验题异常", e);
		}

		res.put("status", status);
		res.put("data", data);
		PrintHelper.sendJsonObject(response, res);
	}

	/**
	 * 展示 测试.
	 * @return
	 */
	@RequestMapping(value="/back/test/controlTest")
	public ModelAndView showTest(HttpServletRequest request){
		Map<String ,String> map = new HashMap<>();
		ModelAndView mv = new ModelAndView("/back/test/controlTest");
		int pageNo = 0;
		int pageSize = 4;
		if (request.getParameter("pageNo") != null
				&& StringUtil.isNumeric(request.getParameter("pageNo"))) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		if (pageNo < 1) {
			pageNo = 1;
		}
		Page<Test> page = null ;
		try {
			page = testService.getTest_Page(map,pageNo,pageSize);
		} catch (Exception e) {
			System.out.println("查询知识点出错-->"+e.getMessage());
		}
		
		mv.addObject("pages", page);
		return mv;
	}
	/**
	 * 展示 测试.
	 * @return
	 */
	@RequestMapping(value="/back/test/controlTest_JSON")
	@ResponseBody
	public String showTest_JSON(HttpServletRequest request,String grade,String testPointId,Integer num){
		Map<String ,String> map = new HashMap<>();
		if(!StringUtils.isBlank(grade)){
			map.put("grade", grade);
		}
		if(!StringUtils.isBlank(testPointId)){
			map.put("testPointId", testPointId);
		}
		int pageNo = 0;
		int pageSize = 4;
		if (num!=null) {
			pageNo = num;
		}
		if (pageNo < 1) {
			pageNo = 1;
		}
		Page<Test> page = null ;
		try {
			page = testService.getTest_Page(map,pageNo,pageSize);
		} catch (Exception e) {
			System.out.println("查询知识点出错-->"+e.getMessage());
		}
		return JSON.toJSONString(page);
	}
	
	
	@RequestMapping("/deleteTest")
	public ModelAndView deleteTest(String id ,Integer testType){
		ModelAndView mv = new ModelAndView("redirect:/back/test/controlTest");
		testService.deleteOneTest(id,testType);
		return mv ;
	}
	
	@RequestMapping("/back/test/updateTest")
	public ModelAndView toView_UpdateTest(String id){
		ModelAndView mv = new ModelAndView("/back/test/updateTest");
		Test test = testService.getOneTest(id);
		mv.addObject("Test", test);
		return mv ;
	}
	@RequestMapping("/updateTest")
	public ModelAndView updateTest(Test test){
		ModelAndView mv = new ModelAndView("redirect:/back/test/controlTest");
		testService.updateTest(test);
		return mv ;
	}
	
	@RequestMapping("/goTest")
	public ModelAndView goTest(String grade){
		ModelAndView mv = new ModelAndView("public/test");
		/**
		 * 根据年级,选择试题
		 * .8个大知识点 10道题(从8个知识点中随机抽取两个问答题.)
		 *  试题按类型排序. 
		 */
		List<Test> TestList = new ArrayList<Test>();

		
		
		return mv;
	}
	
	
	
	
	/*
	
	@RequestMapping("/getAllGrade")
	@ResponseBody
	public String getAllGrade(){
		
		List<String> list = testService.getAllGrade();
		
		return JSON.toJSONString(list);
	}
	*/
	
}
