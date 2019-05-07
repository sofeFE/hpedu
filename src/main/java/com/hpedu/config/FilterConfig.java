package com.hpedu.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Arrays;

//@Configuration
public class FilterConfig {


    @Bean
    public FilterRegistrationBean shiroFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        
        //该值缺省为false，表示生命周期由SpringApplicationContext管理，true则表示由ServletContainer管理
        registration.addInitParameter("targetFilterLifecycle", "true");
        registration.setEnabled(true);
        registration.setOrder(Integer.MAX_VALUE - 1);
//        registration.addUrlPatterns();
        registration.setUrlPatterns(Arrays.asList("/back-authc/**"));

//        DispatcherServlet
        return registration;
    }

}
