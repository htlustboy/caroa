package com.caroa.config;

import java.util.LinkedHashMap;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.caroa.common.JdbcRealm;
import com.caroa.util.BaseUtil;

@Configuration
public class MyShiroConfig {
	
	private static Logger logger = LoggerFactory.getLogger(MyShiroConfig.class);
	/**
	 * 加载ehCache缓存配置
	 * @return
	 */
	@Bean
	public EhCacheManager getEhCacheManager(){
		EhCacheManager ehManager = new EhCacheManager();
		ehManager.setCacheManagerConfigFile("classpath:ehcache.xml");
		logger.info("加载ehCache配置文件--------------------------------------------------------------------------------");
		return ehManager;
	}
	
	/**
	 * 配置shiroFilter(shiro关键)
	 * @param manager
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager manager){
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(manager);
		//配置最基本的url访问路径
		bean.setLoginUrl("/index");
		bean.setSuccessUrl("/success");
		bean.setUnauthorizedUrl("/unauthor");
		//设置url访问过滤器
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/index", "anon"); //首页
		filterChainDefinitionMap.put("/toerror", "anon"); //错误页面
		filterChainDefinitionMap.put("/login/**", "anon"); //登陆页面
		filterChainDefinitionMap.put("/valid/**", "anon");//验证码
		filterChainDefinitionMap.put("/file/**", "anon");//文件状态
		filterChainDefinitionMap.put("/resource/**", "anon");//静态资源
		filterChainDefinitionMap.put("/*/**","authc");
		filterChainDefinitionMap.put("/common/**", "authc[admin]");
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		logger.info("shiro加载完毕-------------------------------------------------------------------------------------");
		return bean;
	}

	/**
	 * 配置自定义的权限登陆管理器
	 * @param jdbcRealm
	 * @return
	 */
	@Bean
	public JdbcRealm jdbcRealm(@Qualifier("credentialsMatcher")CredentialsMatcher credentialsMatcher){
		JdbcRealm jdbcRealm = new JdbcRealm();
		jdbcRealm.setCredentialsMatcher(credentialsMatcher);
		jdbcRealm.setCacheManager(getEhCacheManager());
		logger.info("shiro权限登陆管理器加载完毕--------------------------------------------------------------------------");
		return jdbcRealm;
	}
	
	/**
	 * 配置核心的事务安全管理器
	 * @param jdbcRealm
	 * @return
	 */
	@Bean
	public SecurityManager securityManager(com.caroa.common.JdbcRealm jdbcRealm){
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(jdbcRealm);
		logger.info("shiro核心事务安全管理加载完毕-------------------------------------------------------------------------");
		return manager;
	}
	
	/**
	 * 配置自定义的密码比较
	 * @return
	 */
	@Bean
	public CredentialsMatcher credentialsMatcher(){
		logger.info("shiro自定义的密码加载完毕----------------------------------------------------------------------------");
		return new CredentialsMatcher();
	}
	
	/**
	 * 撇脂生命周期进程
	 * @return
	 */
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
		logger.info("shiro生命周期进程加载完毕----------------------------------------------------------------------------");
		return new LifecycleBeanPostProcessor();
	}
	
	private class CredentialsMatcher extends SimpleCredentialsMatcher{
		@Override
		public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
			UsernamePasswordToken utoken = (UsernamePasswordToken) token;
			//获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
	        String inPassword = BaseUtil.password2Hex(utoken.getUsername(),new String(utoken.getPassword()));
	        //获得数据库中的密码
	        String dbPassword=(String) info.getCredentials();
	        //进行密码的比对
	        return this.equals(inPassword, dbPassword);
		}
	}
}
