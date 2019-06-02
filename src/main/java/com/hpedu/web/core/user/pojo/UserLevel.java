package com.hpedu.web.core.user.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

//用户等级表
@TableName("userlevel")
@Data
public class UserLevel  implements Serializable {
    @TableId
    private String ulid;
    private Integer level;// 等级
    private String des;//等级描述
    private Long minNum;// 下限
    private Long maxNum;// 上限


}
