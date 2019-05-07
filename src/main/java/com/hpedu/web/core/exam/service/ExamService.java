package com.hpedu.web.core.exam.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.exam.pojo.Exam;
import com.hpedu.web.core.exam.pojo.ExamImg;

public interface ExamService  extends IService<Exam> {
	

	/**
	 * 导航栏检索小测验(分页)
	 * 
	 * */

	Page<Exam> findExamListByMap(Map<String,String> map,int pageNo,int pageSize);
	/**
	 * 点击某个测验查看
	 * */
	Exam findExamByEtid(String etid);
	/**
	 * 根据ID删除测试
	 * */
	void deleteExamById(String etId);
	/**
	 * 修改测试
	 * */
	void updateExam(Exam exam);
	/**
	 * 新增测试
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
