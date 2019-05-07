package com.hpedu.config.security;

//@Configuration
//@EnableWebSecurity
public class AppSecutiryConfiguration/* extends WebSecurityConfigurerAdapter*/{

	/*@Autowired
	private DataSource dataSource ;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		*//**
		 * 基于内存配置认证
		 *//*
		// example
		auth.inMemoryAuthentication()
			.withUser("gd").password("root").roles("USER").and()
			.withUser("admin").password("admin").roles("USER","ADMIN") ;
		
		*//**
		 * 基于数据库及 jdbc 配置认证
		 *//*
		auth.jdbcAuthentication()
			.dataSource(dataSource) 
			.usersByUsernameQuery("sql语句")//查用户
			.authoritiesByUsernameQuery("sql语句")//查权限
			.passwordEncoder(new StandardPasswordEncoder("53cr3t")) ;//密码 密文转码
			
		*//**
		 * 
		 *//*
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		super.configure(http);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}*/
	
}
