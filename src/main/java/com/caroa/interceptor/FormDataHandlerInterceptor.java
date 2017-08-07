package com.caroa.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.caroa.controller.BaseController.FormData;

/**
 * 表单重复验证的拦截器
 * @author ht
 *
 */
public class FormDataHandlerInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		if(handler instanceof HandlerMethod){
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			FormData annotation = method.getAnnotation(FormData.class);
			if(annotation!=null){
				boolean need2SaveSession = annotation.save();
				if(need2SaveSession){
					//添加token
					request.getSession().setAttribute("token", UUID.randomUUID().toString().replaceAll("-", ""));
				}
				boolean need2RemoveSession = annotation.remove();
				if (need2RemoveSession) {
					if(isRepearSubmit(request)){
						request.getSession().setAttribute("error", "请不要重复提交!");
						response.sendRedirect("/toerror");
						return false;
					}
					request.getSession().removeAttribute("token");
				}
			}
			return true;
		}else{
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	
	//是否重复提交
	private boolean isRepearSubmit(HttpServletRequest request){
		String token = (String) request.getSession(false).getAttribute("token");
		if(token==null){
			return true;
		}
		String clientToken = request.getParameter("token");
		if(clientToken==null){
			return true;
		}
		if(!token.equals(clientToken)){
			return true;
		}
		return false;
	}

}
