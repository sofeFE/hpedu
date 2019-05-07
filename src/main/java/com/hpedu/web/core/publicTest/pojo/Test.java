package com.hpedu.web.core.publicTest.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
@TableName("test")
@Data
public class Test {
	@TableId
	private String id;
	private String testPointId;// 考点id
	private Date createTime;// 创建时间
	private String testTitle;// 题目内容
	private int score;// 每题分数
	private String testAnswer;// 题目答案
	private String testDetail; // 详解
	private int testType; // 测验题目类型：0/null：选择题；1：简答题
	private int isMoreChoose;// 是否多选题
	
	@TableField(exist = false)
	private TestPoint testPoint ;
	
	
	
	

}
