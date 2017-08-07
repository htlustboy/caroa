package com.caroa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.caroa.interceptor.FormDataHandlerInterceptor;

@Configuration
public class MyInterceptorConfig extends WebMvcConfigurerAdapter{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(new com.caroa.interceptor.BaseHandlerInterceptor()).addPathPatterns("/*/**");
		registry.addInterceptor(new FormDataHandlerInterceptor()).addPathPatterns("/*/**");
	}
}
