package com.hpedu.test.daoTest.mybatisBatchUpdate;

import com.hpedu.util.codeUtil.UUIDUtil;
import com.hpedu.util.mybatis.MyBatisBase;
import com.hpedu.web.core.teacher.dao.TeacherMapper;
import com.hpedu.web.core.teacher.pojo.Teacher;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BatchUpdate {

    final ThreadLocal local = new ThreadLocal();
    final Date date = new Date();
    @Autowired
    private SqlSessionTemplate template;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private MyBatisBase myBatisBase ;
    @Autowired
    TeacherMapper mapper ;
    
    public <T> T getmapper(Class<T> clazz) {
        return template.getMapper(clazz);
    }


    /*批量更新方法
    updateTeacherSort_1 updateTeacherSort*/
    @Test
    public void batchUpdate() {
//        TeacherMapper dao = myBatisBase.getMapper(TeacherMapper.class) ;
        Map<String,Object> map = new HashMap<>() ;
        map.put("limit",10000) ;
        map.put("isShow",1) ;
        
        List<Teacher> list = getList(map);
        start();
        Function<List<?>,Integer> function = (x) -> mapper.updateTeacherSort(x) ;
        int result = makeBatch(  function,list,1000);
        System.out.println("更新数据完成-------,result= " + result);
        end();
    }
    

    /*获取数据,作为更新的参数 */
    public List<Teacher> getList(Map<String,Object> param) {
//        TeacherMapper dao = myBatisBase.getMapper(TeacherMapper.class) ;
        List<Teacher> teacherByIsShow = mapper.getTeacherByIsShow(param);
        for (Teacher t : teacherByIsShow) {
            t.setSort(t.getSort() + 1);
        }
        System.out.println("获取数据完成--------");
        return teacherByIsShow;
    }

    /**
     * 批量添加1  10000条记录,分段添加,每次50 , 用时 4s 956ms
     * 每次 1000, 用时2s , 9.084
     * 每次5000, 用时3s
     * 每次10000 ,我曹 ,3.1, 3.5, 5.1,6.1,9.257
     * <p>
     * 随着数据库数据量的增大, 每次添加的时间都会增长.
     */
    @Test
    public void batchInsert() {
//        TeacherMapper dao = myBatisBase.getMapper(TeacherMapper.class) ;
        List<Teacher> list = getTeachersToInsert(10000);
        start();
        Function<List<?>,Integer> function = (x) -> mapper.batchInsert(x) ;
        int result = makeBatch(  function,list,1000);
        System.out.println("插入数据完成,result= " + result);
        end();
    }
    /**
     * 分段方法
     * @param members
     * @param EachBatchNumber 每批commit的个数
     * @return
     */
    public int makeBatch(Function<List<?>,Integer> function ,List<?> members,int EachBatchNumber) {
        TeacherMapper mapper = template.getMapper(TeacherMapper.class);
        List<?> result = null;
        Integer count = 0 ;
        int to = EachBatchNumber ;// 每批最后一个的下标
        for (int from = 0; from < members.size(); from = to,to = from + (EachBatchNumber - 1)) {
            if (to >= members.size()) {
                to = members.size();
                result = members.subList(from,to);

                count += function.apply(result) ;
                
                break;// 数据插入完毕，退出循环
            }
                result = members.subList(from,to);
                count += function.apply(result) ;
            
        }
        return count;
    }
    /*
    public Integer execute(Function<List<?>,Integer> function,List<?> members){
        return function.apply(members);
    }*/
    /**
     * 批量删除,10000条 耗时630ms
     */
    @Test
    public void BatchDelete() {
//        TeacherMapper teacherMapper = myBatisBase.getMapper(TeacherMapper.class);
        start();
        int result = mapper.batchDeleteByName("%测试数据%");
        System.out.println("result= " + result);
        end();

    }

    /**
     * 批量添加2 , mybatis ExceutorType.BATCH , 内置批量操作.
     * 耗时:3.064 ,9.206 ,8.768
     */
    @Test
    public void batchInsert_2() {
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            TeacherMapper mapper = session.getMapper(TeacherMapper.class);
            List<Teacher> records = getTeachersToInsert(10000); // not shown
            /*这是什么鬼, 没弄清楚*/
//            BatchInsert<Teacher> batchInsert = dao.batchInsert(records)
//                    .into(teacher)
//                    .map(id).toProperty("id")
//                    .map(firstName).toProperty("firstName")
//                    .map(lastName).toProperty("lastName")
//                    .map(birthDate).toProperty("birthDate")
//                    .map(employed).toProperty("employed")
//                    .map(occupation).toProperty("occupation")
//                    .build()
//                    .render(RenderingStrategy.MYBATIS3);
//
//            batchInsert.insertStatements().stream().forEach(dao::insert);
            start();
            int i = mapper.batchInsert(records);
            session.commit();
            end(); 
        } finally {
            session.close();
        }
    }

    public List<Teacher> getTeachersToInsert(int limit) {
        List<Teacher> list = new ArrayList<>();
        start();
        for (int i = 0; i < limit; i++) {
            Teacher teacher = new Teacher();
            teacher.setSort(getTime());
            teacher.setTid(UUIDUtil.getUUID());
            teacher.setBackImg("/teacherImg/2dd9d1c685db4f90b262cf83625affd9.jpeg");
            teacher.setTimgUrl("/teacherImg/2dd9d1c685db4f90b262cf83625affd9.jpeg");
            teacher.setIsShow(1);
            teacher.setTname("测试数据" + i);
            list.add(teacher);
        }
        System.out.println("组装10000条数据完毕.");
        end();
        return list;
    }

    public Long getTime() {
        return date.getTime();
    }


    public void start() {
        local.set(System.currentTimeMillis());
    }

    /**
     * plus 加 
     * minus 减
     * mutiply 乘
     * division 除
     * 
     */
    public void end() {
        BigDecimal bigDecimal = new BigDecimal((System.currentTimeMillis() - (Long) local.get()) );
        bigDecimal.setScale(5,BigDecimal.ROUND_HALF_UP);
        BigDecimal divide = bigDecimal.divide(new BigDecimal(1000));
        System.out.println("耗时(s) :" + divide.toString());
    }

}
