package com.hpedu.exception;

public class _1strategy_Exceptionhandler {
//	解决方案1 ​​ - 控制器级别 @ExceptionHandler
//	第一个解决方案在@Controller级别工作 - 我们将定义一个处理异常的方法，并使用@ExceptionHandler注释：
	
//	public class FooController{
//	     
//	    //...
//	    @ExceptionHandler({ CustomException1.class, CustomException2.class })
//	    public void handleException() {
//	        //
//	    }
//	}
//	这种方法具有一个主要缺点- 在@ExceptionHandler注释的方法仅适用于特定的控制器活性，不是全局为整个应用程序。当然，将其添加到每个控制器使其不适合一般的异常处理机制。
//
//	我们可以通过让所有控制器扩展基本控制器类来解决这个限制- 但是，对于无论何种原因，这是不可能的应用程序，这可能是一个问题。例如，控制器可能已经从另一个基类扩展，该基类可能在另一个罐中或不能直接修改，或者它们本身可能不能直接修改。
//
//	接下来，我们将看另一种解决异常处理问题的方法 - 一种是全局的，不包括对现有工件（如控制器）的任何更改。
}
