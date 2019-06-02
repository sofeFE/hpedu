package com.hpedu.exception;

import com.hpedu.web.core.user.pojo.User;
import com.hpedu.web.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
/**
 * 解决方案4 - ResponseStatusException（Spring 5及以上版本）
 * 
 * Spring 5引入了ResponseStatusException类。
 * 我们可以创建一个它的实例，提供一个HttpStatus,一个可选的理由 和 异常实例:
 *
 */
//@Controller
public class _4strategy_ResponseStateException {
	@Autowired
	private UserService userService ;
	@GetMapping(value = "/{id}")
	public User findById(@PathVariable("id") Long id, HttpServletResponse response) {
	    try {
	    	User resourceById = userService.findUserByUid(id + "") ;
	        return resourceById;
	     }
	    catch (Exception exc) {// 此处为特定异常.
	         throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Foo Not Found", exc);
	         /**  不用自定义很多特定异常类.*/
	    }
	}
	
	/**
	 * 使用ResponseStatusException有什么好处？

		1.非常适合原型设计：我们可以非常快速地实施基本解决方案
		2.一种类型，多种状态代码：一种异常类型可能导致多种不同的响应。
		3.与@ExceptionHandler相比，这减少了紧耦合,我们不必创建任意数量的自定义异常类,
			可以通过编程方式创建异常，从而更好地控制异常处理
		
		
		那么怎么权衡呢？
		
		1.没有统一的异常处理方法:与提供全局方法的@ControllerAdvice相反，执行一些应用程序范围的约定更加困难
		2.代码复制:我们可能会发现自己在多个控制器中复制代码
		
		
	We should also note that it’s possible to combine(结合) different approaches(处理) within one application.
	我们还应该注意，可以在一个应用程序中组合不同的方法。
	For example, we can implement a @ControllerAdvice globally, but also ResponseStatusExceptions locally.
	例如，我们可以全局实现@ControllerAdvice，但也可以在本地实现ResponseStatusException。
	 However, we need to be careful: 
	 但是一定要小心:
	 	If the same exception can be handled in multiple ways, we may notice some surprising behavior. 
	 A possible convention(约定) is to handle one specific kind of exception always in one way.
	 如果同一个异常可以用多种方式处理，我们可能会看到一些令人惊讶的行为。
	 而一般的约定 是 始终以一种方式处理一种特定类型的异常。
	 */
}
