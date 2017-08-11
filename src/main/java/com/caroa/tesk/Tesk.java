package com.caroa.tesk;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

import com.caroa.common.RedisClient;
import com.caroa.constant.Constant;
import com.caroa.service.file.FileService;
import com.caroa.service.user.UserService;
import com.caroa.util.BaseUtil;
import com.caroa.util.JsonUtil;

/**
 * 每隔15分钟清空user表中的激活码
 * @author ht
 *
 */
@Component
public class Tesk {
	
	private static final Logger logger = LoggerFactory.getLogger(Tesk.class);
	
	@Resource
	private UserService userService;
	
	@Resource
	private FileService fileService;
	
	@Resource
	private RedisClient redisClient;
	
	//每隔一小时运行一次,更新首页文件
	@Scheduled(cron="0 0 0/1 * * ?")
	public void print(){
		Jedis jedis = redisClient.getJedis();
		List<Map<String, Object>> newfile = fileService.findNewFile();
		List<Map<String, Object>> hotfile = fileService.findHotFile();
		jedis.set(Constant.File_TYPE_NEWFILE, JsonUtil.obj2json(newfile));
		jedis.set(Constant.File_TYPE_HOTFILE, JsonUtil.obj2json(hotfile));
		redisClient.returnReource(jedis);
		logger.info("定时任务:更新首页文件信息....." + BaseUtil.date2String(new Date()));
	}
}
