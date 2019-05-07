package com.hpedu.web.core.evaluation.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hpedu.util.mybatis.MyBatisBase;
import com.hpedu.web.core.evaluation.dao.EvaluationMapper;
import com.hpedu.web.core.evaluation.pojo.Evaluation;
import com.hpedu.web.core.evaluation.service.EvaluationService;

@Service
public class EvaluationServiceImpl extends ServiceImpl<EvaluationMapper,Evaluation> implements EvaluationService {

	@Autowired
	private MyBatisBase myBatisBase ;
	@Override
	public List<Evaluation> findAllEvaluationByEid(String eid)  {
		List<Evaluation> elist = baseMapper.findAllEvaluationByEid(eid);
		return elist;
	}


	@Override
	public List<Evaluation> findEvaluationListByPage(int pageno, int pagesize)
			 {
		int skip=(pageno-1)*pagesize;
		return baseMapper.findEvaluationListByPage(skip, pagesize);
	}
	@Override
	public int findEvaluationListCount()  {
		return baseMapper.findEvaluationListCount();
	}

	@Override
	public int deleteEvaluationById(String eid)  {
		return baseMapper.deleteEvaluationById(eid);
	}

	@Override
	public List<Evaluation> findTop20EvaluationByEid(String vid)
			 {
		return baseMapper.findTop20EvaluationByEid(vid);
	}

	@Override
	public int insertEvaluation(Evaluation e)  {
		return baseMapper.insertEvaluation(e);
	}

}
