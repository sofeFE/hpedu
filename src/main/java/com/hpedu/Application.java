package com.hpedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@ServletComponentScan/** 为了自动扫描 注解 @WebServlet @WebFilter @WebListener*/
//@EnableCaching//可以使用缓存.
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(value="com.hpedu.web.core.*.dao")
@EnableCaching
public class Application extends SpringBootServletInitializer { //extends SpringBootServletInitializer
	
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(Application.class);
//	}


	//implements ServletContextInitializer
	/**实现此接口为注册过滤器,监视器*/
	/*@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		*//**
		 * 添加监听器
		 *//*
		servletContext.addListener(VisitorListener.class);
		servletContext.addFilter("customFilter",VisitorFilter.class)
				.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/classindex.jsp");
	}*/
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args) ;
	}

/**
 *  配置视图解析器
 */
	/*@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		resolver.setViewClass(JstlView.class);
		return resolver;
	}*/
}
