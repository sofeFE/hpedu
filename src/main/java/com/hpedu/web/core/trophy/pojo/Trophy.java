package com.hpedu.web.core.trophy.pojo;

import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hpedu.util.codeUtil.HanyuPinyinHelper;
@TableName("trophy")
public class Trophy {
	@TableId
	private String pid;
	private String pname;
	private String pintro;
	private String pimgUrl;
	
	private String heartContent;
	private String title;
	private String info;
	private String pintrotitle;
	private String sort;
	private String common;//学员简介

	@TableField(exist = false)
	List<StuImg>  stuList;

	@TableField(exist = false)
	private String pnamePinyin;//学生名称全拼
	
	public String getPid() {
		return pid;
	}
	public Trophy setPid(String pid) {
		this.pid = pid;
		return this ;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPintro() {
		return pintro;
	}
	public void setPintro(String pintro) {
		this.pintro = pintro;
	}
	public String getPimgUrl() {
		return pimgUrl;
	}
	public void setPimgUrl(String pimgUrl) {
		this.pimgUrl = pimgUrl;
	}
	public String getHeartContent() {
		return heartContent;
	}
	public void setHeartContent(String heartContent) {
		this.heartContent = heartContent;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getPintrotitle() {
		return pintrotitle;
	}
	public void setPintrotitle(String pintrotitle) {
		this.pintrotitle = pintrotitle;
	}
	public String getSort() {
		return sort;
	}
	public Trophy setSort(String sort) {
		this.sort = sort;
		return this;
	}
	public List<StuImg> getStuList() {
		return stuList;
	}
	public void setStuList(List<StuImg> stuList) {
		this.stuList = stuList;
	}
	public String getCommon() {
		return common;
	}
	public void setCommon(String common) {
		this.common = common;
	}
	public String getPnamePinyin() {
		if(pname!=null){
			return HanyuPinyinHelper.toHanyuPinyin(pname);
		}
		return pnamePinyin;
	}
	public void setPnamePinyin(String pnamePinyin) {
		this.pnamePinyin = pnamePinyin;
	}
	

}
