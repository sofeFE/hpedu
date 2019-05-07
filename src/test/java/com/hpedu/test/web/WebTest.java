package com.hpedu.test.web;

import org.junit.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.* ;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.* ;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.* ;

import org.springframework.test.web.servlet.MockMvc;

@SuppressWarnings("unused")
public class WebTest {
	@Test
	public void test() throws Exception{
		HomeController controller = new HomeController();
		MockMvc mock = standaloneSetup(controller).build() ;
				mock.perform(get("/")).andExpect(view().name("forward:index.jsp")) ;
	}
	
	
	
	
	
	
	
}
