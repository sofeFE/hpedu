package com.hpedu.web.core.order.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

//首页轮播图
@Data
@TableName("banner")
public class Banner implements Serializable {
    @TableId
    private String bid;
    private String title;// 标题
    private String content;// 图片说明
    private String imgUrl;// 图片路径
    private String sort;//顺序-时间戳
    private String article;//文章

    //公众号首页图 添加属性
    private String category;//跟页面跳转相关的属性 - 课名
    private String subject;//跟页面跳转相关的属性 - 科目
    private String grade;//跟页面跳转相关的属性 - 科目
    private String classify;//跟页面跳转相关的属性 - 科目

    private Integer belong;//轮播图所属的项目


}
