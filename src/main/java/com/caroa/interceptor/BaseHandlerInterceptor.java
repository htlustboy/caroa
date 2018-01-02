package com.caroa.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.caroa.util.BaseUtil;

/**
 * 路径拦截器
 * @author ht
 *
 */
public class BaseHandlerInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getServletPath();
		if(url.equals("/index")){
			if(!BaseUtil.isLogin()){
				response.sendRedirect(request.getContextPath()+"/login/index");
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String method = request.getServletPath();
		if(method.endsWith(".json")){
			return;
		}
		//添加路径变量
		String scheme = request.getScheme();
		String serverName = request.getServerName();
		int port = request.getServerPort();
		String path = request.getContextPath();
		String basePath = scheme + "://" + serverName + ":" + port + path;
		modelAndView.addObject("base", path);
		modelAndView.addObject("basePath", basePath);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
