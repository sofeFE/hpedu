package com.hpedu.web.core.evaluation.service;

import java.util.List;



import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.evaluation.pojo.Evaluation;


/**
 * 评论Service
 * */
public interface EvaluationService extends IService<Evaluation> {

	/**
	 * 查询该视频所有评论 
	 * */
	List<Evaluation> findAllEvaluationByEid(String eid);
	/**
	 * 查看所有评论(分页)
	 * 
	 * */
	//Page<Evaluation>  findEvaluationListByPage(Map<String, String> map,int pageNo,int pageSize) ;
	List<Evaluation>  findEvaluationListByPage(int pageno,int pagesize) ;
	int findEvaluationListCount();
	/**
	 * 根据ID删除该评论
	 * */
	int deleteEvaluationById(String eid);
	/**
	 * 查询该视频前20条评论 
	 * */
	List<Evaluation> findTop20EvaluationByEid(String vid);
	/**
	 * 新增评论 
	 * */
	int insertEvaluation(Evaluation e);
}
