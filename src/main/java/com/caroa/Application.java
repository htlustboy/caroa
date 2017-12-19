package com.caroa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//初始化spring定时任务
@EnableScheduling 
public class Application {
	
	private static Logger logger = LoggerFactory.getLogger(Application.class);
	
	//程序入口
	public static void main(String[] args) {
		logger.info("程序启动>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		SpringApplication.run(Application.class, args);
		logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<启动完成");
		logger.info("访问地址:localhost:8090/index");
	}
}
