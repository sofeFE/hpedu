package com.hpedu.web.core.user.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

//视频章节测试题
@TableName("unittest")
public class UnitTest implements Serializable {
	@TableId
private String	utid ;
private String	  vid ;//关联视频id
private int	  utype ;// 视频类型：0：单个常规；1：单个竞赛；2：多集常规；3：多集竞赛；4：科目小测验
private String	  ucreateTime ;//创建时间
private int	  score ;// 每题分数
private String	  ucontent ;// 题目内容
private String	  answer ;// 题目答案
private int isMoreChoose;//是否多选题
private String utimg;//题目图片
private String ponit;//考点
private String detail;//详解
private int testType;//测验题目类型：0/null：选择题；1：简答题
	
	
	
	
public String getUtid() {
	return utid;
}
public void setUtid(String utid) {
	this.utid = utid;
}
public String getVid() {
	return vid;
}
public void setVid(String vid) {
	this.vid = vid;
}
public int getUtype() {
	return utype;
}
public void setUtype(int utype) {
	this.utype = utype;
}
public String getUcreateTime() {
	return ucreateTime;
}
public void setUcreateTime(String ucreateTime) {
	this.ucreateTime = ucreateTime;
}
public int getScore() {
	return score;
}
public void setScore(int score) {
	this.score = score;
}
public String getUcontent() {
	return ucontent;
}
public void setUcontent(String ucontent) {
	this.ucontent = ucontent;
}
public String getAnswer() {
	return answer;
}
public void setAnswer(String answer) {
	this.answer = answer;
}
public int getIsMoreChoose() {
	return isMoreChoose;
}
public void setIsMoreChoose(int isMoreChoose) {
	this.isMoreChoose = isMoreChoose;
}
public String getUtimg() {
	return utimg;
}
public void setUtimg(String utimg) {
	this.utimg = utimg;
}
public String getPonit() {
	return ponit;
}
public void setPonit(String ponit) {
	this.ponit = ponit;
}
public String getDetail() {
	return detail;
}
public void setDetail(String detail) {
	this.detail = detail;
}
public int getTestType() {
	return testType;
}
public void setTestType(int testType) {
	this.testType = testType;
}



}
