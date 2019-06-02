package com.hpedu.web.core.user.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

//视频章节测试题选项
@TableName("unittest_choose")
public class UnitTest_Choose  implements Serializable {
	@TableId
private String	csid ;
private String	 utid ;// 关联单元测试题id
private String	 tcontent;//选择题内容
private Long	 sort ;// 顺序
private String tanswer;//选项的值
	
	
public String getCsid() {
	return csid;
}
public void setCsid(String csid) {
	this.csid = csid;
}
public String getUtid() {
	return utid;
}
public void setUtid(String utid) {
	this.utid = utid;
}
public String getTcontent() {
	return tcontent;
}
public void setTcontent(String tcontent) {
	this.tcontent = tcontent;
}

public String getTanswer() {
	return tanswer;
}
public void setTanswer(String tanswer) {
	this.tanswer = tanswer;
}
public Long getSort() {
	return sort;
}
public void setSort(Long sort) {
	this.sort = sort;
}



}
