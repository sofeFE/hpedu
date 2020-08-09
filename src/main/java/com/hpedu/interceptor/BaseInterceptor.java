package com.hpedu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//@Component
@Deprecated
public class BaseInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI() ;
		
		//HandlerInterceptor.super.preHandle(request, response, handler);
		return true ;
	}

}
