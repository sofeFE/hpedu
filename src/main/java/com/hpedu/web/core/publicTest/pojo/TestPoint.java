package com.hpedu.web.core.publicTest.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 测试的知识点
 * @author Administrator
 *
 */
@TableName("testpoint")
@Data
public class TestPoint  implements Serializable {
	@TableId
	private String id;
	private String grade;// 年级
	private String pointName;//测试知识点名字
	
	
	
	
	
	
	
}
