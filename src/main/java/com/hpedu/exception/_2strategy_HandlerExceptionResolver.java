package com.hpedu.exception;

public class _2strategy_HandlerExceptionResolver {
//	第二种解决方案是定义HandlerExceptionResolver - 
//		这将解决应用程序抛出的任何异常。它还允许我们在REST API中实现统一的异常处理机制。
	
//	3.1。ExceptionHandlerExceptionResolver
//	此解析程序是在Spring 3.1中引入的，默认情况下在DispatcherServlet中启用。
//		也是@ExceptionHandler机制如何工作的核心组件。
//
//	3.2。DefaultHandlerExceptionResolver
//	这个解析器是在Spring 3.0中引入的，默认情况下它在DispatcherServlet中启用。
//		它用于将标准Spring异常解析为其相应的HTTP状态代码，即客户端错误 - 4xx和服务器错误 - 5xx状态代码。
//		以下是它处理的Spring Exceptions 的完整列表，以及它们如何映射到状态代码。
//	虽然它确实正确设置了响应的状态代码，但一个限制是  它没有为响应主体设置任何内容。
//		对于REST API - 状态代码实际上不足以呈现给客户端 - 
//			响应也必须具有正文，以允许应用程序提供有关失败的其他信息。
//		这可以通过配置 视图解析 和 通过ModelAndView呈现错误内容来解决，
//		但解决方案显然不是最佳的。这就是为什么Spring 3.2引入了一个更好的选项，我们将在后面的部分讨论
//	
//	3.3。ResponseStatusExceptionResolver
//	此解析程序也在Spring 3.0中引入，默认情况下在DispatcherServlet中启用。
//		它的主要职责是 使用自定义异常时可用的@ResponseStatus注释，并将这些异常映射到HTTP状态代码。
//	这样的自定义异常可能如下所示：
//	@ResponseStatus(value = HttpStatus.NOT_FOUND)
//	public class ResourceNotFoundException extends RuntimeException {
//	    public ResourceNotFoundException() {
//	        super();
//	    }
//	    public ResourceNotFoundException(String message) {
//	        super(message);
//	    }
//	    public ResourceNotFoundException(Throwable cause) {
//	        super(cause);
//	    }
//	    public ResourceNotFoundException(String message, Throwable cause) {
//	        super(message, cause);
//	    }
//	}
//	与DefaultHandlerExceptionResolver相同，
//		此解析器在处理响应主体的方式上受到限制 - 它确实在响应上映射状态代码，但正文仍为空。
//	
//	3.4
//	SimpleMappingExceptionResolver and AnnotationMethodHandlerExceptionResolver
//	SimpleMappingExceptionResolver 不适合 Restful服务, 
//	AnnotationMethodHandlerExceptionResolver 为处理@ExceptionHandler ,但已经被3.2 废弃
	
//	3.5
//	DefaultHandlerExceptionResolver和ResponseStatusExceptionResolver的组合
//	在为Spring RESTful Service提供良好的错误处理机制方面有很长的路要走。
//	缺点是 - 如前所述 - 无法控制响应的主体。
//	理想情况下，我们希望能够输出JSON或XML，具体取决于客户端要求的格式（通过Accept标头）。
	
//	仅这一点就足以证明创建一个新的自定义异常解析器是正确的:
//	@Component
//	public class ResponseStatusExceptionResolver_Example extends AbstractHandlerExceptionResolver {
//	 
//	    @Override
//	    protected ModelAndView doResolveException (
//	    		HttpServletRequest request, 
//	    		HttpServletResponse response, 
//	    		Object handler, 
//	    		Exception ex) {
//	        try {
				/**添加 异常判断?*/
//	            if (ex instanceof IllegalArgumentException) {
//	                return handleIllegalArgument((IllegalArgumentException) ex,request ,response, handler);
//	            }
////	            ...
//	        } catch (Exception handlerException) {
//	            logger.warn("Handling of [" + ex.getClass().getName() + "] resulted in Exception", 
//								handlerException);
//	        }
//	        return null;
//	    }
//	 	/**处理异常的方法*/
//	    private ModelAndView handleIllegalArgument(IllegalArgumentException ex,
//											    		HttpServletRequest request,
//											    		HttpServletResponse response,
//											    		Object handler) throws IOException {
//	        response.sendError(HttpServletResponse.SC_CONFLICT);
//	        //application/json 获取请求头 中的 参数.
//	        String accept = request.getHeader(HttpHeaders.ACCEPT);
//	        ...
//	        return new ModelAndView();
//	    }
	/**
	这里要注意的一个细节是我们可以访问请求本身，因此我们可以考虑客户端发送的Accept标头的值。
	例如，如果客户端请求application/json，
			那么在出现错误情况时，我们要确保返回一个用application/json编码的响应体。

	另一个重要的实现细节是我们返回一个  ModelAndView  - 
			这是响应的主体，它将允许我们设置它所需的任何内容。

	这种方法是一种一致且易于配置的机制，用于Spring REST服务的错误处理。
	
	但它确实有局限性：
		它与低级HtttpServletResponse进行交互，
			并且它适用于使用ModelAndView的旧MVC模型- 因此仍有改进的余地。
*/
}
