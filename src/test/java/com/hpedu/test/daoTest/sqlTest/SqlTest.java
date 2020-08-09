package com.hpedu.test.daoTest.sqlTest;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hpedu.util.codeUtil.UUIDUtil;
import com.hpedu.web.core.sqlTest.dao.CourseDao;
import com.hpedu.web.core.sqlTest.dao.StuCourseDao;
import com.hpedu.web.core.sqlTest.dao.StudentDao;
import com.hpedu.web.core.sqlTest.pojo.Course;
import com.hpedu.web.core.sqlTest.pojo.StuCourse;
import com.hpedu.web.core.sqlTest.pojo.Student;
import com.hpedu.web.core.sqlTest.service.StuCourseService;
import com.hpedu.web.core.sqlTest.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SqlTest {

/*赵钱孙李，周吴郑王。
冯陈褚卫，蒋沈韩杨。
朱秦尤许，何吕施张。
孔曹严华，金魏陶姜。*/
    static String[] firstName = {"张","王","李","赵","钱","孙","陈","周","吴","郑","冯","褚","卫","蒋","沈","韩","杨"} ;
    static String[] secondName = {"小","大","二","一","亚","胜","若"};
    static String[] lastName = {"刚","力","强","宝","龙","贵","琼","飞","翔","菊","锋"};

    
    /**
     * 四、怎么真正的批量执行
     * Mabatis的xml中写了
     * 
     * insert('a','b','c') values ('1','1','1'),('2','2','2');
     * 
     * 其实是一条插入语句，是批量执行。update，和remove就不支持这种语法了。如果想让update，remove也批量，需要配置mysql连接参数。
     * 如果想做到真正批量执行，需要调用prepareStatement的executeBatchedInserts()或者executePreparedBatchAsMultiStatement()方法，
     * 这就需要设置rewriteBatchedStatements为true。
     */

    @Autowired
    StuCourseDao stuCourseDao ;
    @Autowired
    StuCourseService stuCourseService ;
    @Test
    public void insertStuCourse(){
        /*随机的人 列表*/
        List<Student> studentList = stuDao.selectList(new QueryWrapper<Student>().select("id"));

        /*随机的课程列表*/
        List<Course> courseList = courseDao.selectList(new QueryWrapper<Course>().select("c_id"));
        
        /*insert stuCourse*/
        List<StuCourse> list = new ArrayList<>() ;
        StuCourse sc = null ;
        for (int i = 0; i < 700000; i++) {
            sc = new StuCourse() ;
            sc.setScId(i+1);
            sc.setCId(courseList.get(createSequence(100)).getCId());
            sc.setSId( studentList.get(createSequence(60000)).getId() );
            sc.setScore(createSequence(100));
//            stuCourseDao.insert(sc);
            list.add(sc) ;
        }
        stuCourseService.saveBatch(list) ;
        
    }
    
    
    @Autowired
    CourseDao courseDao ;
    @Test
    public void insertCourse(){
        Course course = null ;
        for (int i = 0; i < 100; i++) {
            course = new Course() ;
            course.setCId(i + 1);
            course.setName( UUIDUtil.getUUID().substring(0,10));
            courseDao.insert(course) ;
        }
        
    }
    
    @Autowired
    StudentDao stuDao ; 
    @Autowired
    StudentService studentService ;
    @Test
    public void insertStu (){
        Student stu = null ;
        List<Student > list = new ArrayList<>() ;
        /*填装学生*/
        for (int i = 1001; i < 70000 ; i++) {
            stu = new Student();
            stu.setName(createName());
            stu.setId(i+1);
            /*store to database*/
//            stuDao.insert(stu) ;
            list.add(stu) ;
        }
        studentService.saveBatch(list) ;
    }
    
    
    private int createSequence(int number){
        int num =  (int)Math.floor(Math.random() * number) ; ;
        return num ;
    }
    
    
    private String createName(){
        return new StringBuilder()
                .append(firstName[ createSequence(17)] )
                .append(secondName[ createSequence(7)])
                .append(lastName[ createSequence(11)]).toString() ;
    }

    
    
    
}

