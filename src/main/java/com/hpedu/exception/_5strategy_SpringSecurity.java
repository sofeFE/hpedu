package com.hpedu.exception;

public class _5strategy_SpringSecurity {
/*	解决办法5:  Handle the Access Denied in Spring Security
	The Access Denied occurs when an authenticated user tries to access resources 
		that he doesn’t have enough authorities to access.
	6.1. MVC – Custom Error Page
	First, let’s look at the MVC style of the solution and 
		see how to customize an error page for Access Denied:
	The XML configuration:
	
	<http>
	    <intercept-url pattern="/admin/*" access="hasAnyRole('ROLE_ADMIN')"/>   
	    ... 
	    <access-denied-handler error-page="/my-error-page" />
	</http>
	And the Java configuration:
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
	        .antMatchers("/admin/*").hasAnyRole("ROLE_ADMIN")
	        ...
	        .and()
	        .exceptionHandling().accessDeniedPage("/my-error-page");
	}
	When users try to access a resource without having enough authorities, they will be redirected to “/my-error-page“.
	6.2. Custom AccessDeniedHandler
	Next, let’s see how to write our custom AccessDeniedHandler:
	
	@Component
	public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	 
	    @Override
	    public void handle
	      (HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) 
	      throws IOException, ServletException {
	        response.sendRedirect("/my-error-page");
	    }
	}
	And now let’s configure it using XML Configuration:
	
	<http>
	    <intercept-url pattern="/admin/*" access="hasAnyRole('ROLE_ADMIN')"/> 
	    ...
	    <access-denied-handler ref="customAccessDeniedHandler" />
	</http>
	Or using Java Configuration:
	
	@Autowired
	private CustomAccessDeniedHandler accessDeniedHandler;
	 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
	        .antMatchers("/admin/*").hasAnyRole("ROLE_ADMIN")
	        ...
	        .and()
	        .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
	}
	Note how – in our CustomAccessDeniedHandler, we can customize the response as we wish by redirecting or display a custom error message.
	6.3. REST and Method Level Security
	Finally, let’s see how to handle method level security @PreAuthorize, @PostAuthorize, and @SecureAccess Denied.
	We’ll, of course, use the global exception handling mechanism that we discussed earlier to handle the AccessDeniedException as well:
	
	@ControllerAdvice
	public class RestResponseEntityExceptionHandler 
	  extends ResponseEntityExceptionHandler {
	 
	    @ExceptionHandler({ AccessDeniedException.class })
	    public ResponseEntity<Object> handleAccessDeniedException(
	      Exception ex, WebRequest request) {
	        return new ResponseEntity<Object>(
	          "Access denied message here", new HttpHeaders(), HttpStatus.FORBIDDEN);
	    }
	     
	    ...
	}
*/
}
