package com.hpedu.web.core.sqlTest.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("student")
public class Student implements Serializable {
    @TableId(type= IdType.INPUT)
    private int id ;
    private String name ;
    
    
}
