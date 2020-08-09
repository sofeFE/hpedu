package com.hpedu.web.core.sqlTest.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hpedu.web.core.sqlTest.pojo.Course;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseDao extends BaseMapper<Course> {
    
    
}
