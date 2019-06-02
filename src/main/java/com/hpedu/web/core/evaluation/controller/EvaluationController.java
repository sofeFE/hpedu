package com.hpedu.web.core.evaluation.controller;

import com.hpedu.util.codeUtil.BaseUtil;
import com.hpedu.util.codeUtil.PrintHelper;
import com.hpedu.util.codeUtil.StringUtil;
import com.hpedu.util.codeUtil.UUIDUtil;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.evaluation.pojo.Evaluation;
import com.hpedu.web.core.evaluation.service.EvaluationService;
import com.hpedu.web.core.user.pojo.User;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
@RequestMapping("/")
public class EvaluationController {
	@Resource
	EvaluationService evaluationService;
	private Logger log=BaseUtil.getLogger(EvaluationController.class);
	/**
	 * 评论管理
	 * */
	@RequestMapping("/back/evalMgr.html")
	public void evalMgr(HttpServletRequest req,HttpSession session,Model model){
		
		int pageNo = 0;
		int pageSize = 20;
		if (req.getParameter("pageNo") != null
				&& StringUtil.isNumeric(req.getParameter("pageNo"))) {
			pageNo = Integer.parseInt(req.getParameter("pageNo"));
		}
		if (pageNo < 1) {
			pageNo = 1;
		}
		List<Evaluation> list=null;
		int totalCount=0;
		try{
		 list=evaluationService.findEvaluationListByPage(pageNo, pageSize);
		 totalCount=evaluationService.findEvaluationListCount();
		}catch(Exception e){
			list=new ArrayList<Evaluation>();
			log.error("查询评论失败：",e);
		}
		Page pages=new Page();
		pages.setResult(list);
		pages.setPageNo(pageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		model.addAttribute("pages", pages);
		
	}
	/**
	 * 评论删除
	 * 
	 * */
	@RequestMapping("/back/deleteEvaluationByid")
	public ModelAndView deleteEvaluationByid(HttpServletRequest req,HttpSession session,String eid){
		User user = (User)session.getAttribute("backuser");
		if(!"".equals(eid)&&null!=eid){
			if(null!=user){
				try{
				evaluationService.deleteEvaluationById(eid);
				}catch(Exception e){
					log.error("删除评论【id:"+eid+"】失败：",e);
				}
				return new ModelAndView("redirect:/back/evalMgr.html");
			}
		}
		return new ModelAndView("redirect:/back/backlogin.html");
		
	}
	/**
	 * 评论新增
	 * 
	 * */
	@RequestMapping("/eval/addEvaluation")
	public void addEvaluation(HttpServletRequest req,HttpServletResponse response){
		String uname=req.getParameter("uname");
		String evaluation=req.getParameter("evaluation");
		String vid=req.getParameter("vid");
		String vclassify=req.getParameter("vclassify");
		Evaluation evaluation1=new Evaluation();
		evaluation1.setEid(UUIDUtil.getUUID());
		evaluation1.setEvaluation(evaluation);
		evaluation1.setUname(uname);
		evaluation1.setVclassify(vclassify);
		evaluation1.setVid(vid);
		evaluation1.setEcreatTime(new Date());
		String res="ok";
		try{
			int ii=evaluationService.insertEvaluation(evaluation1);
			if(ii>0){
				res+="_"+evaluation1.getEcreatTime();
			}else{
				res="评论新增失败，请联系管理员！";
			}
		}catch(Exception ex){
			res="评论新增失败，请联系管理员！";
			log.error("评论新增失败：",ex);
		} finally {
			try {
				PrintHelper.sendJsonString(response, res);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
