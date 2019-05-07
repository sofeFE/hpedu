package com.hpedu.web.core.order.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 订单 
 * */
@TableName("orders")
@Data
public class Order {
	@TableId
	private String oid;//主键
	private String omoney;//支付金额
	private long ocreatTime;//创建时间
	private long opayTime;// 支付时间
	private String oisPay;//是否支付
	private String orderNo;//订单编号
	private String uid;//购买者ID
	private String vid;//视频id
	private String vclassify;//视频所属分类 0常规 1竞赛 
	private int payStyle;//支付方式
	private Integer isKill;//是否秒杀支付
	
	//关联显示的字段
	@TableField(exist = false)
	private String userName;//购买者
	@TableField(exist = false)
	private String gname;//常规视频名称
	@TableField(exist = false)
	private String cname;//竞赛视频名称
	@TableField(exist = false)
	private Integer isUsed;//是否有效：1：有效；0：过期
	
	
	
}
