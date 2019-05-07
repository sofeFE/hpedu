package com.hpedu.config.mybatis;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.hpedu.interceptor.PageHelper;

//@Configuration
public class MyBatisConfig {
    
    /**
     * @ConfigurationProperties(prefix = "spring.datasource")
     * 自动将 application.proerties中的 以 pring,datasource 开头的 属性 配置进 datasource 中.*/

    @Bean/*(name="dataSource", destroyMethod = "close")  */
    @ConfigurationProperties(prefix = "spring.datasource")  
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();  
    }

    @Bean(name = "sqlSessionFactory") //3
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean Factorybean = new SqlSessionFactoryBean();
        Factorybean.setDataSource(dataSource());
        Factorybean.setTypeAliasesPackage("com.hpedu.web.core.*.pojo");

        //分页插件 //4
       /* PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        //添加插件
        Factorybean.setPlugins(new Interceptor[]{(Interceptor) pageHelper});*/

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
			Factorybean.setMapperLocations( 
					resolver.getResources("classpath:/com/hpedu/web/core/*/mapper/*Mapper.xml"));
			return Factorybean.getObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    
    
    
}
