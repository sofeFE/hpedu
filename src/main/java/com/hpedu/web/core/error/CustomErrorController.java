package com.hpedu.web.core.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 重写错误控制器.
 *
 * @author 郭迪
 * @Time 2019年4月7日14:45:22
 */
//@Controller
//@RequestMapping("/error/")
public class CustomErrorController extends BasicErrorController { // 

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomErrorController.class);

    @Autowired
    private ErrorAttributes errorAttributes;

    public CustomErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes, new ErrorProperties());
        this.errorAttributes = errorAttributes;
    }

    /**
     * 想自定义 错误页 . 404 500  之类视图
     * 页面加载 错误信息.
     */
   /* @RequestMapping(value = "/{page}")//SystemConstant.ERROR_NO_AUTH_PATH
    public String noAuth(@PathVariable Integer page) {
        System.out.println("进入了 自定义 错误 控制器 .");
        if(page >=400 && page < 500){
            page = 404 ;
        }else if(page >=500){
            page = 500 ;
        }
        return "/error/"+ page;
    }*/

    /*@RequestMapping(value = SystemConstant.URL_NO_EXISTS)
    public Result noExists() {
        return Result.urlNoExists();
    }

    @RequestMapping(value = SystemConstant.SERVER_ERROR)
    public Result serverError() {
        return Result.serverError();
    }

    @ExceptionHandler(ApplicationException.class)
    public Result handlerException(Exception e) {
        LOGGER.error("控制器增强处理异常");
        return Result.serverError();
    }*/


}
