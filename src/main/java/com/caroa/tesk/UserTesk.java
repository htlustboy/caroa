package com.caroa.tesk;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.caroa.service.user.UserService;

/**
 * 每隔15分钟清空user表中的激活码
 * @author ht
 *
 */
@Component
public class UserTesk {
	
	private static final Logger logger = LoggerFactory.getLogger(UserTesk.class);
	
	@Resource
	private UserService userService;
	
	@Scheduled(cron="* 30 * * * ?")
	public void print(){
//		userService.clearCode();
		logger.info("定时任务执行完毕");
	}
}
