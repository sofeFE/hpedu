package com.hpedu.filter;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

//@Component
//@ConditionalOnBean(VisitorListener.class)
public class VisitorFilter implements Filter {
	
	private ServletContext context ;
	
	private  AtomicInteger ai = new AtomicInteger(0);
	
	private  ScheduledExecutorService scheduExec = Executors.newScheduledThreadPool(10);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//nullpointexception
		context = filterConfig.getServletContext() ;
		System.out.println("context= "+ context);
		int attribute =(int) context.getAttribute("visitorNum");
		ai.set(attribute);
	}
	/**
	 * 访问特定网页 增加访客数量 
	 * 保证原子操作,线程安全.
	 * 
	 * 应该改为异步操作, 保证访问流畅
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;

		if (req.getRequestURI().contains("classindex.jsp")) {
			asynTask(req);
		}
		chain.doFilter(request, response);

	}
	
	/**
	 * 异步 更新上下文对象中的访问量.
	 * TODO delete one 
	 * @param request
	 */
	public void asynTask(ServletRequest request)  {
		scheduExec.submit(new Runnable() {
			@Override
			public void run() {
				boolean flag = false;
				do {
					//
					int value = ai.intValue();
					flag = ai.compareAndSet(value, value + 1);

				} while (!flag);

				context.setAttribute("visitorNum", ai.get());
			}
		});

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
