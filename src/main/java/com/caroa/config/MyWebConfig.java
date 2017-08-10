package com.caroa.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.caroa.common.RedisClient;
import com.caroa.servlet.ValidServlet;

/**
 * @author ht
 * @data 2017年8月4日10:52:47
 */
@Configuration
//指定Mapper接口所在的包
@MapperScan(basePackages="com.caroa.dao")
public class MyWebConfig {
	
	private static Logger logger = LoggerFactory.getLogger(MyWebConfig.class);
	
	@Resource
	private Environment environment;
	
	
	/**
	 * 创建数据源
	 * @return
	 */
	@Bean
	public DataSource getDataSource(){
		Properties properties = new Properties();
		properties.put("driverClassName", environment.getProperty("jdbc.driverClassName"));
		properties.put("url", environment.getProperty("jdbc.url"));
		properties.put("username", environment.getProperty("jdbc.username"));
		properties.put("passowrd", environment.getProperty("jdbc.password"));
		DataSource ds = null;
		try {
			ds = DruidDataSourceFactory.createDataSource(properties);
			logger.info("使用数据源1-----------------------------------------------------------------------------------");
		} catch (Exception e) {
			properties.put("driverClassName", environment.getProperty("jdbc.driverClassName2"));
			properties.put("url", environment.getProperty("jdbc.url2"));
			properties.put("username", environment.getProperty("jdbc.username2"));
			properties.put("passowrd", environment.getProperty("jdbc.password2"));
			try {
				ds = DruidDataSourceFactory.createDataSource(properties);
				logger.info("使用数据源2-------------------------------------------------------------------------------");
			} catch (Exception e2) {
				logger.error("数据源创建失败！");
			}
		}
		return ds;
	}
	
	/**
	 * 创建SqlSessionFactory
	 * @param ds
	 * @return
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource ds){
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(ds);
		//加载全局配置文件
		sqlSessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(environment.getProperty("mybatis.config")));
		//下边的配置仅仅用于*.xml文件，如果不需要用到xml文件，则不需要配置
		sqlSessionFactoryBean.setTypeAliasesPackage(environment.getProperty("mybatis.typeAliasesPackage"));
		try {
			sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(environment.getProperty("mybatis.mapperLocations")));
			logger.info("sqlSessionFactory创建成功---------------------------------------------------------------------");
			return sqlSessionFactoryBean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("sqlSessionFactory创建失败:"+e.getMessage());
		}
		return null;
	}
	
	/**
	 * 配置redis缓存,获取缓存池
	 * @return
	 */
	@Bean(name="jedisPool")
	public JedisPool getJedisPool(){
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(Integer.parseInt(environment.getProperty("redis.maxtotal")));
		config.setMaxIdle(Integer.parseInt(environment.getProperty("redis.maxidle")));
		config.setMaxWaitMillis(Long.parseLong(environment.getProperty("redis.maxwaitmillis")));
		String host = environment.getProperty("redis.address");
		int port = Integer.parseInt(environment.getProperty("redis.port"));
		logger.info("获取redis缓存池-----------------------------------------------------------------------------------");
		return new JedisPool(config, host, port,3000);
	}
	
	/**
	 * 初始化redis
	 * @param jedisPool
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(RedisClient.class)
	public RedisClient getRedisUtil(JedisPool jedisPool){
		RedisClient redClient = new RedisClient();
		redClient.setJedisPool(jedisPool);
		logger.info("初始化redis--------------------------------------------------------------------------------------");
		return redClient;
	}
	
	/**
	 * 注册自己的Servlet
	 * @return
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean(){
		logger.info("初始化Servlet-------------------------------------------------------------------------------------");
		return new ServletRegistrationBean(new ValidServlet(), "/valid/*");
	}
	
}
