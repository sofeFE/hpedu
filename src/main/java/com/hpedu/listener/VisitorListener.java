package com.hpedu.listener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.hpedu.util.redisUtil.RedisUtil;
import com.hpedu.web.core.index.task.VisitorTask;
import com.hpedu.web.core.user.service.UserService;
//从数据库取出数据--不愿意用jdbc连接,就搞成了这个样子.
/**
 * 自定义 listener
 *
 */
/**注册监听器的方法之一:注解*/
//@WebListener
//@Component
public class VisitorListener implements ServletContextListener,ApplicationListener<ContextRefreshedEvent> {
	
	
	@Autowired
	private UserService userService ;
	@Autowired
	private RedisUtil redisUtil ;
	@Autowired
	private VisitorTask visitorT ;
	
	private ServletContext context ;
	
	private ScheduledExecutorService scheduExec = Executors.newScheduledThreadPool(3);// ;
	
	
	
	/**spring容器加载完毕后执行该方法
	 * 1.将访问量从数据库取出,放入上下文
	 * */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null ){
			try {
				//取出数据
				int num = userService.getVisitorNum() ;
				//存入上下文,或者,内存,2选1
				method1(num);
				
				//执行定时更新到数据库,延迟10秒,间隔60秒 执行.
				scheduExec.scheduleAtFixedRate(visitorT , 1000*10l , 1000*60*10l,TimeUnit.MILLISECONDS);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	@SuppressWarnings("unused")
	private void method1(int num){
		//放入上下文对象中.--可以替换为内存.
		context.setAttribute("visitorNum", num);//1 TODO delete one 
	}
	@SuppressWarnings("unused")
	/**存入内存,也可以直接用注解,
	 * 经百般努力,此方法太过费事, 遂弃置 
	 * 
	 * */
	private void method2(int num){
		redisUtil.set("visitorNum", num);//2 TODO delete
	}
	
	
	/**web容器初始化后,执行该方法
	 * 据观测,此方法早于onApplicationEcent()执行.
	 * */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		context = sce.getServletContext();
		/**
		 * 系统启动的时候,查询数据库,获取访问人数.
		 */
//		servletContext.setAttribute("visitorNum", num);//放入ServletContext中.全局可以获取?
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed");
		userService.updateVisitorNum( ((AtomicInteger)sce.getServletContext().getAttribute("visitorNum")).get() );
	}
}