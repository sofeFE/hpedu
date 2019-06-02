package com.hpedu.util.mybatis;

import com.hpedu.util.codeUtil.UUIDUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

@Component
public class MyBatisBase {
    
    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate;
    @Value("${uploadAbsolutePath}")
    private String uploadAbsolutePath;
    
    /*public <T> T getMapper(Class<T> clazz) {
        return sqlSessionTemplate.getMapper(clazz);
    }*/

    /**
     * 上传文件
     * @param relativePath 相对路径/""/
     * @param file multipart文件
     * @param obj 要更新的对象
     * @param ObjectType 对象对应的类
     * @param methodName 更新对象属性 需要调用的方法名
     * @param ParameterType  方法的参数类型.
     * @throws Exception 
     */
    public void upload(String relativePath, MultipartFile file, Object obj ,
                          Class<?> ObjectType, String methodName , Class<?>... ParameterType) throws Exception{

        if (file.getSize() > 0) {
            Method method = ObjectType.getMethod(methodName, ParameterType);
            String rootPath = uploadAbsolutePath;
            String realPath = rootPath + relativePath ;
            String fileName = UUIDUtil.getUUID();
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            file.transferTo(new File(realPath, fileName + "." + suffix));
            method.invoke(obj ,relativePath + fileName + "." + suffix ) ;
        } 
    }

    

    /**
     * 通过条件获取分页记录
     *
     * @param sqlId Mybatis的XML中的SQL定义id， 命名空间 + id
     * @param param 封装条件
     * @param skip  第多少条
     * @param limit 每页显示多少条数
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> query(String sqlId, Object param, int skip, int limit) {
        try { 
            /*if (limit == 0) {
                return (List<T>) this.sqlSessionTemplate.selectList(sqlId, param);
            }*/
            return (List<T>) this.sqlSessionTemplate.selectList(sqlId, param, new RowBounds(skip, limit));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过分页条件获取Page
     *
     * @param sqlListId  获取集合数据的的SQL语句
     * @param sqlCountId 获取总数的SQL语句
     * @param param      查询条件
     * @param page       分页参数封装, 只用传递第几页和每页条数, 如第一页,则其pageNo为1, 第二页则为2
     */
    public <T> Page<T> queryPage(String sqlListId, String sqlCountId, Object param, Page<T> page) {
        try {
            if (page == null) {
                return page;
            }
            int skip = (page.getPageNo() - 1) * page.getPageSize();
            int limit = page.getPageSize();
            List<T> results = query(sqlListId, param, skip, limit);
            long count = getCount(sqlCountId, param);
            page.result(results).countTotal(count);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return page;
    }

    /**
     * 获取分页总数
     *
     * @param sqlId Mybatis的XML中的SQL定义id， 命名空间 + id
     * @param param 封装条件
     */
    public long getCount(String sqlId, Object param) {
        try {
            Integer count = this.sqlSessionTemplate.selectOne(sqlId, param);
            if (count == null) {
                count = 0;
            }
            return Long.valueOf(count);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过分页条件获取Page
     *
     * @param sqlListId  获取集合数据的的SQL语句
     * @param sqlCountId 获取总数的SQL语句
     * @param param      查询条件
     * @param pageNo     第几页, 如果是第一页, 则值为1, 如果是第二页, 则值为2
     * @param pageSize   每页显示多少条数
     */
    public <T> Page<T> queryPage(String sqlListId, String sqlCountId, Object param, int pageNo, int pageSize) {
        Page<T> page = new Page<T>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        return queryPage(sqlListId, sqlCountId, param, page);
    }

    public String sqlId(String id) {
        return getNamespace().append(".").append(id).toString();
    }

    private StringBuffer getNamespace() {
        String prefix = null;
        Class<?>[] classes = this.getClass().getInterfaces();
        if (ArrayUtils.isNotEmpty(classes)) {
            prefix = classes[0].getName();
        } else {
            throw new IllegalArgumentException("请检查是否实现接口");
        }
        return new StringBuffer(prefix);
    }
}
