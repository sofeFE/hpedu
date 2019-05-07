package com.hpedu.web.core.exam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hpedu.web.core.exam.pojo.Exam;
import com.hpedu.web.core.exam.pojo.ExamImg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface ExamMapper  extends BaseMapper<Exam> {
	
	/**
	 * 导航栏检索小测验(分页)
	 * 
	 * */

	List<Exam> findExamListByMap(Map<String,String> map);
	int findExamListByMapCount(Map<String,String> map);
	/**
	 * 点击查看测验
	 * */
	Exam findExamByEtid(String etid) ;
	/**
	 * 根据ID删除测试
	 * */
	void deleteExamById(String etId);
	/**
	 * 修改测试
	 * */
	void updateExam(Exam exam);
	/**
	 * 新增测验
	 * */
	void addExam(Exam exam);
	
	/**
	 * 批量新增测验题和答案
	 * 
	 * */
	int  insertExamImgs(List<ExamImg> list);
	/**
	 * 批量删除测验题和答案
	 * 
	 * */
	int  deleteExamImgs(String[] list);
	
	List<ExamImg> selectExamImgByExid(String ExamImg);
	
	 /**
	 * 修改小测验学习人数
	 * */
	void updateLearnCount(String etid);
}
