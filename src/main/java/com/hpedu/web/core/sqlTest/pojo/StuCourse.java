package com.hpedu.web.core.sqlTest.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sc")
public class StuCourse implements Serializable {
    @TableId(type= IdType.INPUT)
    private int scId ;
    private int sId ;
    private int cId ;
    private int score ;
    
    
}
