package com.hpedu.web.core.video.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 竞赛课
 * */
@TableName("contestvideo")
public class ContestVideo {
	@TableId
	private String cid;
	private String cname;
	private String cisVip;
	private String cmoney;
	private int cplayNo;
	private String competitionName;//竞赛名称
	private String cclass;//班级
	private String cclassify;//班级下分类
	private String cintro;//简介
	private String cvideoUrl;//路径 
	private Date ccreatTime;//路径 
	private String cvimg;//封面图片路径 
	private String cvpdf;//pdf文件路径
	private Integer isMore;//是否多关联
	
	private String killMoney;
	private String killName;
	private String killStartTime;
	private String killEndTime;
	private Integer isKill;
	
	
	private String teacherName;
	
	private List<VideoPdf> pdflist;//关联pdf
	
	private int clen;//简介长度
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCisVip() {
		return cisVip;
	}
	public void setCisVip(String cisVip) {
		this.cisVip = cisVip;
	}
	public String getCmoney() {
		return cmoney;
	}
	public void setCmoney(String cmoney) {
		this.cmoney = cmoney;
	}
	public int getCplayNo() {
		return cplayNo;
	}
	public void setCplayNo(int cplayNo) {
		this.cplayNo = cplayNo;
	}
	public String getCompetitionName() {
		return competitionName;
	}
	public void setCompetitionName(String competitionName) {
		this.competitionName = competitionName;
	}
	public String getCclass() {
		return cclass;
	}
	public void setCclass(String cclass) {
		this.cclass = cclass;
	}
	public String getCclassify() {
		return cclassify;
	}
	public void setCclassify(String cclassify) {
		this.cclassify = cclassify;
	}
	public String getCintro() {
		return cintro;
	}
	public void setCintro(String cintro) {
		this.cintro = cintro;
	}
	public String getCvideoUrl() {
		return cvideoUrl;
	}
	public void setCvideoUrl(String cvideoUrl) {
		this.cvideoUrl = cvideoUrl;
	}
	public String getCcreatTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(ccreatTime);
	}
	public void setCcreatTime(Date ccreatTime) {
		this.ccreatTime = ccreatTime;
	}
	public String getCvimg() {
		return cvimg;
	}
	public void setCvimg(String cvimg) {
		this.cvimg = cvimg;
	}
	public String getCvpdf() {
		return cvpdf;
	}
	public void setCvpdf(String cvpdf) {
		this.cvpdf = cvpdf;
	}
	public int getClen() {
		return clen;
	}
	public void setClen(int clen) {
		this.clen = clen;
	}
	public Integer getIsMore() {
		return isMore;
	}
	public void setIsMore(Integer isMore) {
		this.isMore = isMore;
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
	

}
