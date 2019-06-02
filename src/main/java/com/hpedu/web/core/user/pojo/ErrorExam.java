package com.hpedu.web.core.user.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

//学生章节错误测验题记录表
@TableName("errorexam")
public class ErrorExam  implements Serializable {
	@TableId
private String	eeid ;
private String	  usid;// 关联分数记录id[userScore]
private String	  utid ;// 测试题id[unitTest]
private Long	  sort ;//顺序
private String stuAnswer;//学生答案
private Integer stuGotScore;//学生单个题目得分
private String coment;//批注
	
	
	
	
public String getEeid() {
	return eeid;
}
public void setEeid(String eeid) {
	this.eeid = eeid;
}
public String getUsid() {
	return usid;
}
public void setUsid(String usid) {
	this.usid = usid;
}
public String getUtid() {
	return utid;
}
public void setUtid(String utid) {
	this.utid = utid;
}
public Long getSort() {
	return sort;
}
public void setSort(Long sort) {
	this.sort = sort;
}
public String getStuAnswer() {
	return stuAnswer;
}
public void setStuAnswer(String stuAnswer) {
	this.stuAnswer = stuAnswer;
}
public Integer getStuGotScore() {
	return stuGotScore;
}
public void setStuGotScore(Integer stuGotScore) {
	this.stuGotScore = stuGotScore;
}
public String getComent() {
	return coment;
}
public void setComent(String coment) {
	this.coment = coment;
}



}
