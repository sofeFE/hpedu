package com.hpedu;

import com.hpedu.filter.VisitorFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

//@ServletComponentScan/** 为了自动扫描 注解 @WebServlet @WebFilter @WebListener*/
//@EnableCaching//可以使用缓存.
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(value="com.hpedu.web.core.*.dao")
@EnableCaching
public class Application  {
	//extends SpringBootServletInitializer  //使用外部容器的时候，需要继承此类，

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


	@Bean
	public FilterRegistrationBean myFilter(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new VisitorFilter());
		registrationBean.setUrlPatterns(Arrays.asList("/classindex.html","/index.html"));
		return registrationBean;
	}

//	@Bean
//	public ServletListenerRegistrationBean myListener(){//VisitorListener
//		ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
//
//		servletListenerRegistrationBean.setListener(new ContainerDestoryListener());
//		return servletListenerRegistrationBean ;
//	}

}
