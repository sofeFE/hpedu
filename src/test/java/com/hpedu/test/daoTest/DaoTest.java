package com.hpedu.test.daoTest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hpedu.util.codeUtil.UUIDUtil;
import com.hpedu.web.core.shiro.dao.SysMenuDao;
import com.hpedu.web.core.shiro.pojo.SysMenuEntity;
import com.hpedu.web.core.teacher.dao.TeacherMapper;
import com.hpedu.web.core.teacher.pojo.Teacher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration

//@MybatisTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Import(SysMenuDao.class)
public class DaoTest {
    
    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate;
    SysMenuDao menuDao ;
    TeacherMapper teacherDao ; 
    @Before
    public void before(){
        menuDao = sqlSessionTemplate.getMapper(SysMenuDao.class) ;
        teacherDao = sqlSessionTemplate.getMapper(TeacherMapper.class) ;
        
    }
    
    
    @Test
    public void test(){
        List<SysMenuEntity> menuList = menuDao.getMenuList(null);
        Assert.assertNotNull( menuList);
        for (SysMenuEntity entity: menuList  ) {
            System.out.println(entity);
        }
    }
    
    
    @Test
    public void updateTest(){
        Teacher teacher = teacherDao.selectOne(new QueryWrapper<Teacher>().eq("tname", "测试数据"));
        teacher.setTintro(null);
        teacherDao.updateById( teacher );
        /*条件更新*/
//        teacherDao.update( teacher, new QueryWrapper<Teacher>().eq("tname", "测试数据") );//
        
    }
    @Test
    public void insert(){
        Teacher teacher = new Teacher();
        teacher.setSort(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli() );
        teacher.setTid(UUIDUtil.getUUID());
        teacher.setBackImg("/teacherImg/2dd9d1c685db4f90b262cf83625affd9.jpeg");
        teacher.setTimgUrl("/teacherImg/2dd9d1c685db4f90b262cf83625affd9.jpeg");
        teacher.setIsShow(0);
        teacher.setTname("测试数据" );
        teacherDao.addTeacher(teacher);
    }
    
    
    
}
