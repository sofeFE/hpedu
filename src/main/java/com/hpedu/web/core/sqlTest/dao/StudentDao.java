package com.hpedu.web.core.sqlTest.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hpedu.web.core.sqlTest.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentDao extends BaseMapper<Student> {
    
    
}
