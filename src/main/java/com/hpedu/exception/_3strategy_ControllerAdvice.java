package com.hpedu.exception;

//解决方案3 - @ControllerAdvice
//Spring 3.2 通过  @ControllerAdvice注释为全局@ExceptionHandler提供支持。
//这使得机制能够脱离旧的MVC模型，并使用ResponseEntity以及@ExceptionHandler的类型安全性和灵活性

public class _3strategy_ControllerAdvice {

//		@ControllerAdvice
//		public class ResponseEntityExceptionHandlerExample extends ResponseEntityExceptionHandler {
//		
//			@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
//			protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
//				/**
//				 * 此处应该 应该 怎么做呢?
//				 */
//				String bodyOfResponse = "This should be application specific exception message";
//				return handleExceptionInternal(ex, bodyOfResponse, 
//												new HttpHeaders(), HttpStatus.CONFLICT, request);
//				
//			}
//		}
	/**
	@Controlleradvice注释允许我们将之前的多个分散的@ExceptionHandlers合并到一个全局错误处理组件中。。
	实际机制非常简单，但也非常灵活。它给了我们：
	
			1.完全控制响应的主体 以及 状态代码
			2.将几个 异常 映射到同一个方法，一起处理，
			3.它充分利用了较新的RESTful ResposeEntity响应
	但是:
		这里需要记住的一件事是，将用@ExceptionHandler声明的异常与用作方法参数的异常匹配起来。
		如果这些不匹配，编译器就不会报错——没有理由应该报错，Spring也不会报错.
		然而，当异常在运行时被抛出时，异常解析机制将会失败:

		java.lang.IllegalStateException: No suitable resolver for argument [0] [type=...]
		HandlerMethod details: ...
 */
}
