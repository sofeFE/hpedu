package com.hpedu.web.core.teacher.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hpedu.web.core.teacher.pojo.Teacher;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
//import org.mybatis.dynamic.sql.util.SqlProviderAdapter;


@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {//
	/**
	 * 查询所有老师
	 * */
	List<Teacher> findAllTeacher(Map<String,Object> map);
	/**
	 * 根据ID 查询教师
	 * */
	 Teacher findTeacherById(String id);
	 /**
		 * 根据name查询教师
		 * */
	  Teacher findTeacherByName(String tname);
	 /**
	  * 
	  *更新教师信息 
	  * */
	 int updateTeacher(Teacher teacher);
	 /**
	  * 根据ID删除教师信息
	  * */
	 int deleteTeacherById(String id);
	 /**
	  * 新增教师信息
	  * */
	 int addTeacher(Teacher teacher);

	 /**
	   	 *  修改教师顺序
	   	 * */
	 int updateTeacherSort_1(List<Teacher> teacher);

	int updateTeacherSort(List<?> teacher) ;

	int searchTeacherListCount(Map<String,String> map);
	 List<Teacher> findTeacherByPage(Map<String,Object> map);//分页查询教师

	/**
	 * 批量插入
	 * @param param
	 * @return
	 */
	int batchInsert(List<?> param) ;

//	@InsertProvider(type=Teacher.class, method="insert")
//	@Options(useGeneratedKeys=true, keyProperty="record.fullName")
//	int insert(List<Teacher> insertStatement);

	int batchDeleteByName(String tname);


	/**
	 * 获取所有激活中的老师
	 * @return
	 * @param limit
	 */
//	List<Teacher> findActiveTeacher(Map<String, Object> limit);

	/**
	 * 根据激活状态获取教师集合
	 * @param limit
	 * @return
	 */
	List<Teacher> getTeacherByIsShow(Map<String, Object> limit);
}
