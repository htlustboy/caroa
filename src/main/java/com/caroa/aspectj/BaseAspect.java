package com.caroa.aspectj;


import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.caroa.util.BaseUtil;

@Component
@Aspect
public class BaseAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseAspect.class);
	
	@Pointcut("execution(public * com.caroa.controller.common.CommonController.*(..))")
	public void basePointCut(){ }
	
	@Around("basePointCut()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Exception{
		if(!BaseUtil.isLogin()){
			logger.info("用户未登陆");
			throw new Exception("用户未登陆");
		}else{
			//已经登陆
			Subject cuurentUser = SecurityUtils.getSubject();
			if(cuurentUser.hasRole("admin2")){
				try {
					return proceedingJoinPoint.proceed();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}else{
				logger.info("没有权限");
				throw new Exception("没有权限");
			}
		}
		return null;
	}
	
	@AfterThrowing(value="basePointCut()",throwing="e")
	public void afterThrowing(Throwable e){
		logger.warn(e.getMessage()+":"+new Date());
	}
	
}
