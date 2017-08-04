package com.caroa.common;

import redis.clients.jedis.JedisPool;

/**
 * redisPool配置类
 * @author ht
 * @data 2017年8月4日11:14:36
 */
public class RedisClient {
	
	private JedisPool jedisPool;
	
	//获取缓存池
	public JedisPool getJedisPool(){
		return jedisPool;
	}
	
	//设置缓存池
	public void setJedisPool(JedisPool jedisPool){
		this.jedisPool = jedisPool;
	}
}
