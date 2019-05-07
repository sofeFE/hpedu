package com.hpedu.web.core.publicTest.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hpedu.web.core.publicTest.pojo.TestOption;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestOptionMapper extends BaseMapper<TestOption> {
	

	void insertTestOption(List<TestOption> optionList);

	void deleteOptionByTestId(String id);
}
