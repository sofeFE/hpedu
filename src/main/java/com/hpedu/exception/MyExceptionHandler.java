package com.hpedu.exception;

import com.hpedu.util.ResultBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
    
    RequestMappingHandlerMapping handlerMapping ;
    
    @ExceptionHandler(value = {RuntimeException.class} )
    @ResponseBody
    protected ResultBean handleForJson(RuntimeException ex, WebRequest request, Model model) throws Exception{
        log.error("错误信息:{},stacktrace[0]:{}" , ex.getMessage(),ex.getStackTrace()[0]) ;
        return ResultBean.failed( ex.getMessage());
    }
    @ExceptionHandler(value = {Exception.class} )
    @RequestMapping(produces = {"text/html"})//;charset=utf-8
    protected String handleForHtml(Exception ex,
                                   WebRequest request, Model model) throws Exception{
        StringBuilder bodyOfResponse = new StringBuilder();
        ResponseEntity<Object> entity =  handleException(ex, request);
        int statusCodeValue = entity.getStatusCodeValue();
        String url = "" ;
        if(statusCodeValue >= 400 && statusCodeValue< 500){
            url =  "404" ;
        }else if( statusCodeValue >= 500){
            url = "500" ;
        }

        log.error("错误信息:{}" , StringUtils.isBlank(entity.toString()) ? entity.toString() : bodyOfResponse.toString());
        model.addAttribute("errMsg", StringUtils.isBlank(bodyOfResponse.toString()) ? entity.toString() : bodyOfResponse.toString() ) ;
        return "error/" + url ;

    }

    /*@ModelAttribute("ExceptionResponseEntity") ResponseEntity entity
     * 重定向控制器, 获取参数值 :RequestContextUtils.getInputFlashMap(request).get("key")
     * */


    /**
     * 该注解作用对象为方法，并且在运行时有效，value()可以指定异常类。由该注解注释的方法可以具有灵活的输入参数（详细参见Spring API）：
     *
     * 异常参数：包括一般的异常或特定的异常（即自定义异常），如果注解没有指定异常类，会默认进行映射。
     * 请求或响应对象 (Servlet API or Portlet API)： 你可以选择不同的类型，如ServletRequest/HttpServletRequest或PortleRequest/ActionRequest/RenderRequest。
     * Session对象(Servlet API or Portlet API)： HttpSession或PortletSession。
     * WebRequest或NativeWebRequest 
     * Locale
     * InputStream/Reader 
     * OutputStream/Writer 
     * Model
     * 
     * 
     * 方法返回值可以为：
     *
     * ModelAndView对象
     * Model对象
     * Map对象
     * View对象
     * String对象
     * 还有@ResponseBody、HttpEntity<?>或ResponseEntity<?>，以及void
     */
}
