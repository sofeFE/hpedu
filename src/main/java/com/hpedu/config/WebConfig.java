package com.hpedu.config;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * 需要配置 1.视图解析器 2.组件扫描器 3.静态资源映射.
 */

//@EnableWebMvc
/**
 * @EnableWebMvc
 * 如果添加该注释,需要更多的自定义配置
 *  1. tomcat 的dispatcherServlet处理器 处理 视图映射. 这是不可能成功的.
 *  Could not resolve view with name 'classindex' in servlet with name 'dispatcherServlet'
 *  2. 静态资源映射/** 需要重新定义,
 *  3. 很多过滤器也 不会自动添加了.
 *  4. /webjars/** 目录映射也没有了.
 *
 * */

//@Configuration
	@Deprecated //暂时不用自定义配置
public class WebConfig  implements WebMvcConfigurer {


	/*@Autowired
	private BaseInterceptor baseInterceptor ;*/


	/**
	 * 静态资源请求 交给 默认控制器处理.
	 * @param configurer
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		 configurer.enable();
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry viewresolverregistry) {
		/* * 配置尝试1*/
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);//该属性使用 有待商议
		resolver.setViewClass(JstlView.class);//

		/*配置尝试2*/
//		viewresolverregistry.jsp().prefix("/WEB-INF/jsp/").suffix(".jsp");

		/*尝试3*/
//		viewresolverregistry.jsp("/WEB-INF/jsp/",".jsp").viewClass(InternalResourceView.class);
//		viewresolverregistry.viewResolver(new InternalResourceViewResolver());

		/*原方法*/
//		WebMvcConfigurer.super.configureViewResolvers(viewresolverregistry);
	}

	/**配置自定义 静态资源路径 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/**")
								.addResourceLocations("classpath:/META-INF/resources/")
								.addResourceLocations("classpath:/resources/")
								.addResourceLocations("classpath:/static/")
								.addResourceLocations("classpath:/public/");
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}

}
