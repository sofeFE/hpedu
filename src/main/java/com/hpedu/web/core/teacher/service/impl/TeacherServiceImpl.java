package com.hpedu.web.core.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hpedu.util.ResultBean;
import com.hpedu.util.mybatis.MyBatisBase;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.teacher.dao.TeacherMapper;
import com.hpedu.web.core.teacher.pojo.Teacher;
import com.hpedu.web.core.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService{
@Autowired
private MyBatisBase myBatisBase ;
	
	@Transactional(readOnly = false)
	@Override
	public ResultBean<String> updateTeacherSort(String tid1, long sort1, String tid2, long sort2) {
		Teacher t1= new Teacher();
		Teacher t2= new Teacher();
		t1.setSort(sort1);
		t1.setTid(tid1);
		t2.setTid(tid2);
		t2.setSort(sort2);
		List<Teacher> list = new ArrayList<>() ;
		list.add(t1);list.add(t2) ;
		int i = baseMapper.updateTeacherSort(list);
		ResultBean<String>  resultBean = new ResultBean<>();
		resultBean.setMsg("success");
		resultBean.setCode(resultBean.SUCCESS);
		if(i <=0){
			resultBean.setMsg("sorting failure");
			resultBean.setCode(resultBean.UNKNOWN_EXCEPTION);
		}
		return resultBean;
	}



	@Override
	public List<Teacher> findAllTeacher(Map<String,Object> param)  {
		TeacherMapper mapper = baseMapper;
		return mapper.findAllTeacher(param);
	}

	@Override
	public Teacher findTeacherById(String id)  {
		TeacherMapper mapper = baseMapper;
		Teacher teacher = mapper.findTeacherById(id);
		return teacher;
	}

	/**
	 * 修改教师详情
	 * @param teacher
	 * @param file
	 * @param file1
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	@Override
	public ResultBean<?> updateTeacher(Teacher teacher, MultipartFile file,MultipartFile file1) throws Exception{
		
		String relativePath = "/teacherImg/" ;
		if(file != null && file.getSize() > 0) {
			myBatisBase.upload(relativePath, file,teacher,Teacher.class,"setTimgUrl",String.class) ;
		}if(file1 != null && file1.getSize() > 0) {
			myBatisBase.upload(relativePath,file1,teacher,Teacher.class,"setBackImg",String.class);
		}
		
		TeacherMapper mapper = baseMapper;
		int i = mapper.updateById( teacher );
		ResultBean<?> resultBean = new ResultBean<>();
		if(i > 0){
			resultBean.setCode(ResultBean.SUCCESS);
			resultBean.setMsg("修改成功");
		}else {
			resultBean.setCode(ResultBean.UNKNOWN_EXCEPTION);
			resultBean.setMsg("修改失败");
		}

		return resultBean ;
	}
	
	@Transactional(readOnly = false )
	@Override
	public ResultBean<?> deleteTeacherById(String id)  {
		TeacherMapper mapper = baseMapper;
		if(mapper.deleteTeacherById(id) > 0){
			return ResultBean.ok();
		}
		return ResultBean.failed("删除失败") ;
	}
	@Transactional(readOnly = false )
	@Override
	public void addTeacher(Teacher teacher)  {
		TeacherMapper mapper = baseMapper;
		mapper.addTeacher(teacher);
	}

	@Deprecated
	@Transactional(readOnly = false )
	@Override
	public int updateTeacherSort(String tid, String sort) {
		/*TeacherMapper mapper = baseMapper;
		return mapper.updateTeacherSort(tid, sort);*/
		return 0 ;
	}

	@Override
	public Teacher findTeacherByName(String tname)  {
		TeacherMapper mapper = baseMapper;
		return mapper.findTeacherByName(tname);
	}

	
	@Override
	public Page<Teacher> searchTeacherList(Map<String, Object> map, int pageNo,
			int pageSize)  {
		
		/*方法*/
		Page<Teacher> page = myBatisBase.queryPage("com.hpedu.web.core.teacher.dao.TeacherMapper.findTeacherByPage",
				"com.hpedu.web.core.teacher.dao.TeacherMapper.searchTeacherListCount", map, pageNo, pageSize);
		return page;
		
	}
	@Override
	public com.github.pagehelper.PageInfo<Teacher> searchTeacherList2(Map<String, Object> map, int pageNo,
			int pageSize)  {
		
		PageHelper.startPage(pageNo	,pageSize) ;
		TeacherMapper mapper = baseMapper;
		List<Teacher> list = mapper.findAllTeacher(map);
		com.github.pagehelper.Page<Teacher> page = new com.github.pagehelper.Page<>();
		
		PageInfo info = new PageInfo(list);
//		List list1 = info.getList();

		return info;
		
	}
	
	public List<Teacher> searchTeacherList3(Map<String, Object> map, int pageNo,  int pageSize)  {

		TeacherMapper mapper = baseMapper;
		List<Teacher> list = mapper.findAllTeacher(map);

		return list;

	}
	
	@Override
	@Cacheable(value="welcomePage"/*,key="teacherList_show"*/)/*value 为缓存块的名字 , key 为缓存中存储的 键值对 的key*/
	public List<Teacher> findTeacherByIsShow(Map<String, Object> limit) {
//		Collection<Teacher> teachers = listByMap(limit);
		List<Teacher> list = list(new QueryWrapper<Teacher>().eq("isShow", limit.get("isShow")).last("limit "+ limit.get("limit"))  );
		return list ;
//		return baseMapper.getTeacherByIsShow(limit);
	}

	@Override
	public ResultBean forbiddenTeacherById(String id) {
		TeacherMapper mapper = baseMapper;
		
		if(mapper.forbiddenTeacherById(id) > 0){
			return ResultBean.ok();
		}
		return ResultBean.failed("更新教师状态失败") ;
	}

}
