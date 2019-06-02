package com.hpedu.web.core.publicTest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hpedu.util.mybatis.MyBatisBase;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.publicTest.dao.TestPointMapper;
import com.hpedu.web.core.publicTest.pojo.TestPoint;
import com.hpedu.web.core.publicTest.service.TestPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class TestPointServiceImpl extends ServiceImpl<TestPointMapper,TestPoint> implements TestPointService{

	@Autowired
	private TestPointMapper tpMapper ;
	@Autowired
	private MyBatisBase myBatisBase ;
	@Override
	public void insertTestPoint(Map<String, String> map)  {
		tpMapper.insertTestPoint(map);
	}

	
	@Override
	public  Page<TestPoint> getTestPoint(Map<String, String> map,int pageNo,int pageSize)  {
		Page<TestPoint> page = myBatisBase.queryPage("com.hpedu.web.core.publicTest.dao.TestPointMapper.selectTestPoint",
				 "com.hpedu.web.core.publicTest.dao.TestPointMapper.TestPointCount", map,  pageNo, pageSize);
		return page;
	}


	@Override
	public TestPoint getOneTestPoint(String id) {
		return tpMapper.getOneTestPoint(id);
	}


	@Override
	public void updateTestPoint(TestPoint tp) {
		tpMapper.updateTestPoint(tp);
	}


	@Override
	public void deleteOneTestPoint(String id) {
		tpMapper.deleteOneTestPoint(id);
	}


	@Override
	public List<String> getAllGrade() {
		return tpMapper.getAllGrade();
	}


	@Override
	public List<Map<String, String>> getTestPointByGrade(String grade) {
		List<Map<String, String>> list = tpMapper.getTestPointByGrade(grade);
		return list;
	}

	
}
