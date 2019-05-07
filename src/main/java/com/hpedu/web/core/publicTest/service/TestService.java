package com.hpedu.web.core.publicTest.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.publicTest.pojo.Test;

/**
 * 测试题 业务层.
 * @author Administrator
 *
 */

public interface TestService  extends IService<Test> {
	/**
	 * 添加测试题
	 * @param test
	 * @param option
	 * @param optionContent
	 * @
	 */
	void insertTest(Test test, String[] option, String[] optionContent)  ;

	/**
	 * 查询测试题 - 分页.
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Page<Test> getTest_Page(Map<String, String> map, int pageNo, int pageSize);

	/**
	 * 删除单个测试题
	 * @param id
	 * @param testType 
	 */
	void deleteOneTest(String id, Integer testType);

	/**
	 * 根据id获得一个测试数据
	 * @param id
	 * @return
	 */
	Test getOneTest(String id);

	/**
	 * 更新测试题
	 * @param test
	 */
	void updateTest(Test test);
	
}
