package com.hpedu.web.core.publicTest.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 测试题-选择题 的选项及内容
 * @author Administrator
 *
 */
@Data
@TableName("testoption")
public class TestOption {
	@TableId
	private String id;
	private String testId;// 关联测试题id
	private String option;// 选项的值
	private String optionContent;// 选择题内容
	
	
	
}
