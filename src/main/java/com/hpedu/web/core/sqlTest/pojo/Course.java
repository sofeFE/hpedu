package com.hpedu.web.core.sqlTest.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("course")
public class Course implements Serializable {
    @TableId(type = IdType.INPUT)
    private int cId ;
    private String name ;
    
    
}
