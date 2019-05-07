package com.hpedu.web.core.index.task;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hpedu.util.redisUtil.RedisUtil;
import com.hpedu.web.core.index.service.VisitorService;

/**
 * 定时任务类
 * 定期将人数更新到数据库.
 * @author Administrator
 */
//@Component
public class VisitorTask implements Runnable{
	
	@Autowired
	private ServletContext context ;
	@Autowired
	private RedisUtil redisUtil ;
	@Autowired
	private VisitorService visitorService ;

	@Override
	public void run() {
		function1();
		function2();
	}

	/**从内存取出*/
	private void function2() {
		// 获取数据 
		AtomicInteger attribute = (AtomicInteger)redisUtil.get("visitorNum");
		//调用方法更新数据
		visitorService.updateVisitorNum(attribute.intValue());
	}

	/**从上下文取出,*/
	private void function1() {
		// 获取数据 
		AtomicInteger attribute = (AtomicInteger)context.getAttribute("visitorNum");
		//调用方法更新数据
		visitorService.updateVisitorNum(attribute.intValue());
	}
	
}
