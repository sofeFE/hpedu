package com.hpedu.web.core.publicTest.controller;

import com.alibaba.fastjson.JSON;
import com.hpedu.util.codeUtil.StringUtil;
import com.hpedu.util.codeUtil.UUIDUtil;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.publicTest.pojo.TestPoint;
import com.hpedu.web.core.publicTest.service.TestPointService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试知识点 控制器
 * @author Administrator
 *
 */
@Controller
public class TestPointController {
	@Autowired
	private TestPointService tpService ;
	
	/**
	 * 插入测试知识点
	 * @param request
	 * @param tp
	 * @return
	 */
	@RequestMapping(value="/addTestPoint",method=RequestMethod.POST)
	public ModelAndView addTestPoint(HttpServletRequest request ,TestPoint tp){
		Map<String ,String> map = new HashMap<>();
		map.put("id", UUIDUtil.getUUID()) ;
		map.put("grade", tp.getGrade()) ;
		map.put("pointName", tp.getPointName());
		try {
			tpService.insertTestPoint(map);
		} catch (Exception e) {
			System.out.println("插入测试知识点 异常~~~"+e.getMessage());
//			e.printStackTrace();
		}
//		return new ModelAndView("/back/test/controlTestPoint");
		return new ModelAndView("redirect:/back/test/controlTestPoint");//添加完成后,直接展示.
	}
	
	/**
	 * 展示 测试知识点.
	 * @return
	 */
	@RequestMapping(value="/back/test/controlTestPoint")
	public ModelAndView showTestPoint(HttpServletRequest request ,
			@RequestParam(value="grade",required=false) String grade){
		Map<String ,String> map = new HashMap<>();
		if(!StringUtils.isBlank(grade)){
			map.put("grade",grade) ;
		}
		ModelAndView mv = new ModelAndView("/back/test/controlTestPoint");
		int pageNo = 0;
		int pageSize = 4;
		if (request.getParameter("pageNo") != null
				&& StringUtil.isNumeric(request.getParameter("pageNo"))) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		if (pageNo < 1) {
			pageNo = 1;
		}
		Page<TestPoint> page = null ;
		try {
			page = tpService.getTestPoint(map,pageNo,pageSize);
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("查询知识点出错-->"+e.getMessage());
		}
		
		mv.addObject("pages", page);
		return mv;
	}
	/**
	 * 展示 测试知识点.-json
	 * @return
	 */
	@RequestMapping(value="/back/test/controlTestPoint_JSON")
	@ResponseBody
	public String showTestPoint_JSON(HttpServletRequest request ,
			@RequestParam(value="grade",required=false) String grade,
			@RequestParam(value="num",required=false) String num){
		Map<String ,String> map = new HashMap<>();
		if(!StringUtils.isBlank(grade)){
			map.put("grade",grade) ;
		}
		int pageNo = 0;
		int pageSize = 4;
		if (num != null
				&& StringUtil.isNumeric(num)) {
			pageNo = Integer.parseInt(num);
		}
		if (pageNo < 1) {
			pageNo = 1;
		}
		Page<TestPoint> page = null ;
		try {
			page = tpService.getTestPoint(map,pageNo,pageSize);
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("查询知识点出错-->"+e.getMessage());
		}
		return JSON.toJSONString(page);
	}
	
	@RequestMapping("/back/test/addTestPoint")
	public ModelAndView toView_AddTestPoint(){
		ModelAndView mv = new ModelAndView("/back/test/addTestPoint");
		return mv ;
	}
	
	
	@RequestMapping("/back/test/updateTestPoint")
	public ModelAndView toView_UpdateTestPoint(String id){
		
		ModelAndView mv = new ModelAndView("/back/test/addTestPoint");
		TestPoint tp = tpService.getOneTestPoint(id);
		mv.addObject("testPoint", tp);
		return mv ;
	}
	@RequestMapping("/updateTestPoint")
	public ModelAndView updateTestPoint(TestPoint tp){
		ModelAndView mv = new ModelAndView("redirect:/back/test/controlTestPoint");
		
		tpService.updateTestPoint(tp);
		
		return mv ;
	}
	
	@RequestMapping("/deleteTestPoint")
	public ModelAndView deleteTestPoint(String id){
		ModelAndView mv = new ModelAndView("redirect:/back/test/controlTestPoint");
		
		tpService.deleteOneTestPoint(id);
		
		return mv ;
	}
	
	@RequestMapping("/getAllGrade")
	@ResponseBody
	public String getAllGrade(){
		
		List<String> list = tpService.getAllGrade();
		
		return JSON.toJSONString(list);
	}
	@RequestMapping("/getTestPointByGrade")
	@ResponseBody
	public String getTestPointByGrade(String grade){
		
		List<Map<String,String>> list = tpService.getTestPointByGrade(grade);
		
		return JSON.toJSONString(list);
	}
	
	
}
