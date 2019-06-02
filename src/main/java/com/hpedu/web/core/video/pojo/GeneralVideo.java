package com.hpedu.web.core.video.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hpedu.util.codeUtil.DateUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 常规课
 * */
@TableName("generalvideo")
public class GeneralVideo implements Serializable {
	@TableId
	private String gid;
	private String gname;
	private String gisVip;
	private String gmoney;
	private int gplayNo;
	private String gsbuject;//学科 语数外
	private String gclass;//班级 三 四 五 小升初 新概念 其他  
	private String gclassify;//阅读 写作 。。。。。 
	private String gintro;//简介
	private String gvideoUrl;//路径
	private Date gcreatTime;//路径
	private String gvimg;//封面图片路径
	private String gvpdf;//pdf文件路径
	private Integer isMore;//是否多关联
	private int glen;//简介长度
	private String gclassify2;//子分类（暑假班、寒假班）
	
	private String killMoney;
	private String killName;
	private String killStartTime;
	private String killEndTime;
	private Integer isKill;
	
	
	private String teacherName;
	private String weekVal;//每周更新时间
	
	@TableField(exist = false)
	private List<VideoPdf> pdflist;//关联pdf
	@TableField(exist = false)
	private List<VideoChild> vclist;//关联子视频(多集专题子视频)
	
	
	
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getGisVip() {
		return gisVip;
	}
	public void setGisVip(String gisVip) {
		this.gisVip = gisVip;
	}
	public String getGmoney() {
		return gmoney;
	}
	public void setGmoney(String gmoney) {
		this.gmoney = gmoney;
	}
	public int getGplayNo() {
		return gplayNo;
	}
	public void setGplayNo(int gplayNo) {
		this.gplayNo = gplayNo;
	}
	
	public String getGsbuject() {
		return gsbuject;
	}
	public void setGsbuject(String gsbuject) {
		this.gsbuject = gsbuject;
	}
	public String getGclass() {
		return gclass;
	}
	public void setGclass(String gclass) {
		this.gclass = gclass;
	}
	public String getGclassify() {
		return gclassify;
	}
	public void setGclassify(String gclassify) {
		this.gclassify = gclassify;
	}
	public String getGintro() {
		return gintro;
	}
	public void setGintro(String gintro) {
		this.gintro = gintro;
	}
	public String getGvideoUrl() {
		return gvideoUrl;
	}
	public void setGvideoUrl(String gvideoUrl) {
		this.gvideoUrl = gvideoUrl;
	}
	public String getGcreatTime() {
		return DateUtil.getYMD(gcreatTime);
	}
	public void setGcreatTime(Date gcreatTime) {
		this.gcreatTime = gcreatTime;
	}
	public String getGvimg() {
		return gvimg;
	}
	public void setGvimg(String gvimg) {
		this.gvimg = gvimg;
	}
	public String getGvpdf() {
		return gvpdf;
	}
	public void setGvpdf(String gvpdf) {
		this.gvpdf = gvpdf;
	}
	public int getGlen() {
		return glen;
	}
	public void setGlen(int glen) {
		this.glen = glen;
	}
	public Integer getIsMore() {
		return isMore;
	}
	public void setIsMore(Integer isMore) {
		this.isMore = isMore;
	}
	public String getGclassify2() {
		return gclassify2;
	}
	public void setGclassify2(String gclassify2) {
		this.gclassify2 = gclassify2;
	}
	public List<VideoPdf> getPdflist() {
		return pdflist;
	}
	public void setPdflist(List<VideoPdf> pdflist) {
		this.pdflist = pdflist;
	}
	public String getKillMoney() {
		return killMoney;
	}
	public void setKillMoney(String killMoney) {
		this.killMoney = killMoney;
	}
	public String getKillName() {
		return killName;
	}
	public void setKillName(String killName) {
		this.killName = killName;
	}
	public String getKillStartTime() {
		return killStartTime;
	}
	public void setKillStartTime(String killStartTime) {
		this.killStartTime = killStartTime;
	}
	public String getKillEndTime() {
		return killEndTime;
	}
	public void setKillEndTime(String killEndTime) {
		this.killEndTime = killEndTime;
	}
	public Integer getIsKill() {
		return isKill;
	}
	public void setIsKill(Integer isKill) {
		this.isKill = isKill;
	}
	
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public List<VideoChild> getVclist() {
		return vclist;
	}
	public void setVclist(List<VideoChild> vclist) {
		this.vclist = vclist;
	}
	public String getWeekVal() {
		return weekVal;
	}
	public void setWeekVal(String weekVal) {
		this.weekVal = weekVal;
	}
	
	
}
