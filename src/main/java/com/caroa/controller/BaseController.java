package com.caroa.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseController implements EnvironmentAware{

	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected Environment environment;
	
	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.METHOD})
	public @interface FormData{
		boolean save() default false;
		boolean remove() default false;
	}
	
	   /** 取得HttpServletRequest */
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /** 取得HttpServletResponse */
    public HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
    
    protected static class R{
    	public static final String LAYOUT_PAGE = "layout";
    	public static final String REDIRECT_PAGE = "redirect";
    	
    	public static String view(String viewPath){
    		return viewPath;
    	}
    	
	    public static String view(String viewPath, boolean isRediredt) {
           return String.format("%s%s.html", isRediredt ? "redirect:/" : "", viewPath);
        }

    }
}
