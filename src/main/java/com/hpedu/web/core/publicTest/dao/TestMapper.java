package com.hpedu.web.core.publicTest.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.publicTest.pojo.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface TestMapper extends BaseMapper<Test> {
	/**
	 * 添加测试题
	 * @param test
	 */
	void insertTest(Test test);
	/**
	 * 查询 测试题- 分页
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Page<Test> getTest_Page(Map<String, String> map, int pageNo, int pageSize);
	
	/**
	 * 删除单个测试题
	 * @param id
	 */
	void deleteOntTest(String id);
	/**
	 * 根据id获得一条测试数据
	 * @param id
	 * @return
	 */
	Test getOneTest(String id);
}
