package com.hpedu.web.core.publicTest.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hpedu.web.core.publicTest.pojo.TestPoint;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestPointMapper extends BaseMapper<TestPoint> {
	
	/**
	 * 插入 知识点 
	 * */
	void insertTestPoint(Map<String,String> map);
	
	List<TestPoint> selectTestPoint(Map<String,String> map);

	TestPoint getOneTestPoint(String id);

	void updateTestPoint(TestPoint tp);

	void deleteOneTestPoint(String id);

	List<String> getAllGrade();

	List<Map<String, String>> getTestPointByGrade(String grade);
}
