package com.hpedu.config.security;

//@Component
public class UserService/* implements UserDetailsService*/{
	
	/*@Autowired
	private UserMapper userMapper ;	
	
	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		User user = userMapper.getUserByUserName(arg0);
		if(user!=null) {
			List<GrantedAuthority> list = new ArrayList<>();
			list.add(new SimpleGrantedAuthority("roles")) ;
			return new User(user.getUserName(),user.getPassWord(),list);
		}
		
		
		throw new UsernameNotFoundException("");
	}
*/
}
