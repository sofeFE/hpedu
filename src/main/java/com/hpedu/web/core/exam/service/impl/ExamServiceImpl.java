package com.hpedu.web.core.exam.service.impl;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hpedu.util.mybatis.MyBatisBase;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.exam.dao.ExamMapper;
import com.hpedu.web.core.exam.pojo.Exam;
import com.hpedu.web.core.exam.pojo.ExamImg;
import com.hpedu.web.core.exam.service.ExamService;

@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper,Exam> implements ExamService{
	@Autowired
	private MyBatisBase myBatisBase ;
	@Override
	public Page<Exam> findExamListByMap(Map<String, String> map,int pageNo,int pageSize)  {
		
		Page<Exam> pages = myBatisBase.queryPage("com.hpedu.web.core.exam.dao.ExamMapper.findExamListByMap",
			"com.hpedu.web.core.exam.dao.ExamMapper.findExamListByMapCount", map, pageNo, pageSize);
		return pages;
	}

	@Override
	public Exam findExamByEtid(String etid)  {
		
		Exam exam = baseMapper.findExamByEtid(etid);
		return exam;
	}

	@Override
	public void deleteExamById(String etId)  {
		
		baseMapper.deleteExamById(etId);
	}

	@Override
	public void updateExam(Exam exam)  {
		
		baseMapper.updateExam(exam);
	}

	@Override
	public void addExam(Exam exam)  {
		
		baseMapper.addExam(exam);
	}

	@Override
	public int insertExamImgs(List<ExamImg> list) {
		
		return baseMapper.insertExamImgs(list);
	}

	@Override
	public int deleteExamImgs(String[] list) {
		
		return baseMapper.deleteExamImgs(list);
	}

	@Override
	public List<ExamImg> selectExamImgByExid(String ExamImg) {
		
		return baseMapper.selectExamImgByExid(ExamImg);
	}

	@Override
	public void updateLearnCount(String etid) {
		
		baseMapper.updateLearnCount(etid);
	}
}
