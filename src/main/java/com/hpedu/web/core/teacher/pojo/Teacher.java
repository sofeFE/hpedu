package com.hpedu.web.core.teacher.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hpedu.util.codeUtil.HanyuPinyinHelper;
import lombok.Data;

@Data
@TableName("teacher")
public class Teacher {
	@TableId
	private String tid;
	@TableField("tname")
	private String tname;
	private String tintro;
	@TableField("timgUrl")
	private String timgUrl;
	@TableField("backImg")
	private String backImg;
	
	private long sort;//顺序-时间戳
	private String subject;//教授科目
@TableField("isShow")
	private Integer isShow;//是否显示
	
	@TableField(exist = false)
	private int nums;//未批改试卷数
	@TableField(exist = false)
	private String tnamePinyin;//教师名称全拼
	
	
	
}
