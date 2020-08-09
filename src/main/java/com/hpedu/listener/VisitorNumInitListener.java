package com.hpedu.listener;

import com.hpedu.util.RedisUtil;
import com.hpedu.web.core.index.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



/**
 * 该类的主要作用：
 * 1 .初始化 访问数量 到上下文属性中。
 * 2. 容器销毁的时候更新访问量到数据库 。
 *
 */
@Component
public class VisitorNumInitListener implements  ApplicationListener<ContextRefreshedEvent> {//ServletContextListener,ApplicationListener<ContextRefreshedEvent>

//	@Autowired
//	private UserService userService ;
	@Autowired
	private RedisUtil redisUtil ;
	@Autowired
	private VisitorService visitorService ;
	@Autowired
	private ServletContext context ;
	
	private ScheduledExecutorService scheduExec = Executors.newScheduledThreadPool(3);// ;
	
	
	
	/**spring容器加载完毕后执行该方法
	 * 1.将访问量从数据库取出,放入上下文
	 * */
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if(event.getApplicationContext().getParent() == null ){
			try {
				//取出数据
//				int num = userService.getVisitorNum() ;
				int num = redisUtil.get("springbootEdu:other:updateVisitorNum")==null ? 0 : (int)redisUtil.get("springbootEdu:other:updateVisitorNum") ;
				context.setAttribute("visitorNum",num );
				//执行定时更新到数据库,延迟10秒,间隔60秒 执行.
				scheduExec.scheduleAtFixedRate(()->{
				    int visitorNum = (int)context.getAttribute("visitorNum") ;
					visitorService.updateVisitorNum(visitorNum) ;
				}, 1000*10l , 1000*60l,TimeUnit.MILLISECONDS);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
//	private void store2Redis(){
//		redisUtil.set("visitorNum",(int)context.getAttribute("visitorNum")) ;
//	}

}