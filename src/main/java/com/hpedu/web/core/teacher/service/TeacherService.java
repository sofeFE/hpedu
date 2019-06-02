package com.hpedu.web.core.teacher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hpedu.util.ResultBean;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.teacher.pojo.Teacher;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface TeacherService extends IService<Teacher> {//

	/**
	 * 查询所有教师
	 * */
	List<Teacher> findAllTeacher(Map<String,Object> param);
	
	/**
	 * 根据ID查询教师
	 * */
	Teacher findTeacherById(String id);
	 /**
	 * 根据name查询教师
	 * */
  Teacher findTeacherByName(String tname);
	/**
	 * 修改教师信息
	 * */
	ResultBean<?> updateTeacher(Teacher teacher, MultipartFile file, MultipartFile file1) throws IOException, NoSuchMethodException, Exception;
	/**
	 * 删除教师信息
	 * */
	ResultBean<?> deleteTeacherById(String id);
	/**
	 * 新增教师
	 * */
	void addTeacher(Teacher teacher);
	/**
   	 *  修改教师顺序
   	 * 
   	 * */
 int updateTeacherSort(String tid,String sort);

	/**
	 * 更改两个教师的排序.
	 * @param tid1
	 * @param sort1
	 * @param tid2
	 * @param sort2
	 * @return
	 */
 ResultBean<String> updateTeacherSort(String tid1, long sort1, String tid2, long sort2) ;



 Page<Teacher> searchTeacherList(Map<String, Object> map, int pageNo, int pageSize) ;
 
 com.github.pagehelper.PageInfo<Teacher> searchTeacherList2(Map<String, Object> map, int pageNo, int pageSize) ;

	List<Teacher> searchTeacherList3(Map<String, Object> map, int pageNo,
									 int pageSize) ;
 
	/**
	 * 获取所有 有效的教师
	 * @param limit
	 * @return
	 */
	List<Teacher> findTeacherByIsShow(Map<String, Object> limit);

	ResultBean forbiddenTeacherById(String id);
	
}
