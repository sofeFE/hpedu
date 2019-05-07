package com.hpedu.web.core.evaluation.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hpedu.web.core.evaluation.pojo.Evaluation;
import org.apache.ibatis.annotations.Mapper;


/**
 * 评论 dao
 * */
@Mapper
public interface EvaluationMapper extends BaseMapper<Evaluation> {

	/**
	 * 查询该视频所有评论 
	 * */
	List<Evaluation> findAllEvaluationByEid(String vid);
	/**
	 * 查看所有评论(分页)
	 * 
	 * */
	List<Evaluation>  findEvaluationListByPage(int skip,int limit) ;
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
