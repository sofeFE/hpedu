/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.hpedu.Aspect;

import com.google.gson.Gson;
import com.hpedu.annotation.SysLog;
import com.hpedu.util.HttpContextUtils;
import com.hpedu.util.IPUtils;
import com.hpedu.web.core.shiro.pojo.SysLogEntity;
import com.hpedu.web.core.shiro.pojo.SysUserEntity;
import com.hpedu.web.core.shiro.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志，切面处理类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {
	@Autowired
	private SysLogService sysLogService;
	
	@Pointcut("@annotation(com.hpedu.annotation.SysLog)")
	public void logPointCut() { 
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

		//保存日志
//		saveSysLog(point, time);
		printLog(point, time);
		return result;
	}

	private void printLog(ProceedingJoinPoint joinPoint, long time) {
		log.info("业务日志打印:[{}]", createSysLog(joinPoint, time).toString());
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		SysLogEntity logEntity = createSysLog(joinPoint, time);
		
		//请求的参数
		Object[] args = joinPoint.getArgs();
		try{/*为什么只获取第一个参数?*/
			String params = new Gson().toJson(args[0]);
			logEntity.setParams(params);
		}catch (Exception e){

		}
		//用户名
		String username = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
		logEntity.setUsername(username);

		
		//保存系统日志
		sysLogService.save(logEntity);
	}

	private SysLogEntity createSysLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		SysLogEntity logEntity = new SysLogEntity();
		SysLog logAnnotation = method.getAnnotation(SysLog.class);
		if(logAnnotation != null){
			//注解上的描述
			logEntity.setOperation(logAnnotation.value());
		}

		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		logEntity.setMethod(className + "." + methodName + "()");

		

		//获取request
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		//设置IP地址
		logEntity.setIp(IPUtils.getIpAddr(request));
		logEntity.setTime(time);
		logEntity.setCreateDate(new Date());
		return logEntity;
	}
}
