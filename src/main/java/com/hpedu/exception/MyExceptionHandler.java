package com.hpedu.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ControllerAdvice是controller的一个辅助类，最常用的就是作为全局异常处理的切面类
 * @ControllerAdvice可以指定扫描范围
 * @ControllerAdvice约定了几种可行的返回值，如果是直接返回model类的话，需要使用@ResponseBody进行json转换
 *
 * 返回String，表示跳到某个view
 * 返回modelAndView
 * 返回model + @ResponseBody
 */
@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(MyExceptionHandler.class);


    @ExceptionHandler(RuntimeException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        //传入我们自己的错误状态码 4xx 5xx，否则就不会进入定制错误页面的解析流程
        /**
         * Integer statusCode = (Integer) request
         .getAttribute("javax.servlet.error.status_code");
         */
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","RuntimeException");
        map.put("message",e.getMessage());
//转发到/error
        return "forward:/error";
    }




}
