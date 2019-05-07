package com.hpedu.web.core.user.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

//用户学习时间表
@TableName("userlearn")
@Data
public class UserLearn {
    @TableId
    private String ulid;
    private String userid;//用户id
    private String vid;// 视频id
    private Integer vctype;// 视频类型:0:常规；1：竞赛
    private Long learnTime;// 学习时长
    private String learnDate;// 学习日期



}
