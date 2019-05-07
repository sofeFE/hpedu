package com.hpedu.web.core.user.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hpedu.util.codeUtil.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
 @TableName("user")
public class User /*implements UserDetails*/{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@TableId
	private String uid;
	private String userName;
	private String passWord;
	private String phoneNo;
	/**是否vip：0：普通用户；1：vip用户（学生家长视频分时段免费）；2：后台管理员；3：后台超级管理员*/
	private String isVip;
	private String email;/**/
	private String headImgUrl;/*头像地址url*/
	private Date regTime; 
	private Date lastLoginTime; /**/
	private Long learnTime;//学习时长
	
	
	//新增字段
	private String salt ;/*密码盐*/
	/**sb
	 * 登录人身份类型：0：普通用户；1：学生家长*/ 
	private Integer type;
	private Integer status;//状态：0：未审核  1：已审核；
	private String freeType;//免费类型:半年、一年
	private Date endTime;//免费到期时间?

@TableField(exist = false)
	 private Integer isused;//使用状态（0：未审核或普通用户；1：使用中；2：已过期）--专用于学生家长审核

	 private String yqCode;//自己的邀请码内容
	 private String yqCodeUrl;//自己的邀请码图片（用于分享）
	 private String usedCode;//当前用户注册使用的邀请码

	 @Deprecated
	 @TableField(exist = false)
	 private String rightContent;//权限内容
//	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getIsVip() {
		return isVip;
	}
	public void setIsVip(String isVip) {
		this.isVip = isVip;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public String getRegTime() {
		return regTime==null?null: DateUtil.To_yyyy_MM_dd(regTime);
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public String getLastLoginTime() {
		return lastLoginTime==null?null:DateUtil.To_yyyy_MM_dd(lastLoginTime);
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String  getEndTime() {
		return endTime==null?null:DateUtil.To_yyyy_MM_dd(endTime);
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getFreeType() {
		return freeType;
	}
	public void setFreeType(String freeType) {
		this.freeType = freeType;
	}
	public Long getLearnTime() {
		return learnTime;
	}
	public void setLearnTime(Long learnTime) {
		this.learnTime = learnTime;
	}
	public Integer getIsused() {
		return isused;
	}
	public void setIsused(Integer isused) {
		this.isused = isused;
	}
	public String getYqCode() {
		return yqCode;
	}
	public void setYqCode(String yqCode) {
		this.yqCode = yqCode;
	}
	public String getYqCodeUrl() {
		return yqCodeUrl;
	}
	public void setYqCodeUrl(String yqCodeUrl) {
		this.yqCodeUrl = yqCodeUrl;
	}
	public String getUsedCode() {
		return usedCode;
	}
	public void setUsedCode(String usedCode) {
		this.usedCode = usedCode;
	}
	
	
	/*@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	public User(String userName2, String passWord2, List<GrantedAuthority> list) {
		// TODO Auto-generated constructor stub
	}
	*/
	
}
