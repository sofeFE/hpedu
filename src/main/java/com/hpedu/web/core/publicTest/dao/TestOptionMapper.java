package com.hpedu.web.core.publicTest.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hpedu.web.core.publicTest.pojo.TestOption;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestOptionMapper extends BaseMapper<TestOption> {
	

	void insertTestOption(List<TestOption> optionList);

	void deleteOptionByTestId(String id);
}
