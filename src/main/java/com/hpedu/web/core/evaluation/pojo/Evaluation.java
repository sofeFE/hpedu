package com.hpedu.web.core.evaluation.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 评论 
 * */
@TableName("evaluation")
public class Evaluation {
	@TableId
	private String eid;
	private String uname;
	private String evaluation;
	private Date ecreatTime;
	private String vid;//视频ID
	private String vclassify;//视频所属分类
	private String eisShow;//是否显示
	
	//关联显示的字段
	@TableField(exist = false)
	private String gname;//常规视频名称
	@TableField(exist = false)
	private String cname;//竞赛视频名称
	
	
	
	
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	public String getEcreatTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(ecreatTime);
	}
	public void setEcreatTime(Date ecreatTime) {
		this.ecreatTime = ecreatTime;
	}
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getVclassify() {
		return vclassify;
	}
	public void setVclassify(String vclassify) {
		this.vclassify = vclassify;
	}
	public String getEisShow() {
		return eisShow;
	}
	public void setEisShow(String eisShow) {
		this.eisShow = eisShow;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
}
