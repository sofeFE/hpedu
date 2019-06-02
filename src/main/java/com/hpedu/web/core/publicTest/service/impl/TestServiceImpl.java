package com.hpedu.web.core.publicTest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hpedu.util.codeUtil.UUIDUtil;
import com.hpedu.util.mybatis.MyBatisBase;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.publicTest.dao.TestMapper;
import com.hpedu.web.core.publicTest.dao.TestOptionMapper;
import com.hpedu.web.core.publicTest.pojo.Test;
import com.hpedu.web.core.publicTest.pojo.TestOption;
import com.hpedu.web.core.publicTest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper,Test> implements TestService{

	@Autowired
	private TestMapper testMapper ;
	@Autowired
	private TestOptionMapper testOptionMapper ;

	@Autowired
	private MyBatisBase myBatisBase ;
	
	
	@Override
	public void insertTest(Test test, String[] option, String[] optionContent)  {
		List<TestOption> optionList = new ArrayList<TestOption>();
		testMapper.insertTest(test);// 新增测验题
		
		if (test.getTestType() == 0) {
			if (optionContent != null) {
				for (int i = 0; i < option.length; i++) {
					String option_content = optionContent[i];
					String option_ = option[i];
					TestOption testOption = new TestOption();
					testOption.setId(UUIDUtil.getUUID());
					testOption.setTestId(test.getId());
					testOption.setOptionContent(option_content);
					testOption.setOption(option_);
					
					optionList.add(testOption);
				}
				// 新增测验题选项
				testOptionMapper.insertTestOption(optionList);
			}
		}
		
	}

	@Override
	public Page<Test> getTest_Page(Map<String, String> map, int pageNo, int pageSize) {
		return myBatisBase.queryPage("com.hpedu.web.core.publicTest.dao.TestMapper.getTest_Page",
				 "com.hpedu.web.core.publicTest.dao.TestMapper.getTest_Page_count", map,  pageNo, pageSize);
	}

	@Override
	public void deleteOneTest(String id,Integer testType) {
		//删除测试题 表数据
		testMapper.deleteOntTest(id);
		//如果是选择题, 删除相应的选项.
		if(testType==0){// 0是选择
			testOptionMapper.deleteOptionByTestId(id);
		}
	}

	@Override
	public Test getOneTest(String id) {
		return testMapper.getOneTest(id);
	}

	@Override
	public void updateTest(Test test) {
		/**
		 * 更新测试题,
		 * 1.知识点
		 * 2.测试题实体更新,
		 * 3.选项更新? --> 1.禁止选择变简答 2.单选变多选, 3.选项内容变换?
		 * 
		 * 另一种思路, 直接删除原有的, 从新添加一个?
		 */
		
	}
	
	
	
}
