package com.hpedu.exception;

public class _6strategy_Springboot {
//	
//. Spring Boot支持
	
	/*
	 * Spring Boot提供了一个  ErrorController实现，以合理的方式处理错误。

	简而言之，它为浏览器（也称为Whitelabel错误页面）提供了回退错误页面，并为RESTful 及 非HTML请求提供了JSON响应：

	{
	    "timestamp": "2019-01-17T16:12:45.977+0000",
	    "status": 500,
	    "error": "Internal Server Error",
	    "message": "Error processing the request!",
	    "path": "/my-endpoint-with-exceptions"
	}
	像往常一样，Spring Boot允许使用以下属性配置这些功能：

	server.error.whitelabel.enabled：  可用于禁用Whitelabel错误页面 并依赖servlet容器提供HTML错误消息
	server.error.include-stacktrace： 默认使用always ，它可以包含HTML和JSON默认响应中的stacktrace*/
	
	/*
	
	除了这些属性，
	1.我们可以为 /error 提供我们自己的视图解析器映射， 覆盖Whitelabel页面。

	
	2.我们还可以通过在上下文中包含的ErrorAttributes bean 来自定义我们想要在响应中显示的属性  。
	
	我们可以扩展Spring Boot提供的  DefaultErrorAttributes 类，以简化操作：

	@Component
	public class MyCustomErrorAttributes extends DefaultErrorAttributes {
	 
	    @Override
	    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
	        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
	        errorAttributes.put("locale", webRequest.getLocale().toString());
	        errorAttributes.remove("error");
	 
	        //...
	 
	        return errorAttributes;
	    }
	}*/
	
	
	
/*	3.
	如果我们想进一步定义（或覆盖）应用程序如何处理特定内容类型的错误，我们可以注册一个  ErrorController  bean。

	同样，我们可以使用Spring Boot提供的默认  BasicErrorController 来帮助我们。
	例如，假设我们想要自定义应用程序处理XML端点中触发的错误的方式,
		我们所要做的就是使用@RequestMapping定义一个公共方法，  并声明它生成  application/xml 媒体类型：

	@Component
	public class MyErrorController extends BasicErrorController {
	 
	    public MyErrorController(ErrorAttributes errorAttributes) {
	        super(errorAttributes, new ErrorProperties());
	    }
	 
	    @RequestMapping(produces = MediaType.APPLICATION_XML_VALUE)
	    public ResponseEntity<Map<String, Object>> xmlError(HttpServletRequest request) {
	         
	    // ...
	 
	    }
	}*/
	
	
}
