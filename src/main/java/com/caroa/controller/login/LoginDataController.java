package com.caroa.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.caroa.controller.BaseController;
import com.caroa.model.User;
import com.caroa.service.user.UserService;

@RestController
@RequestMapping("/login")
public class LoginDataController extends BaseController{
	
	@Resource
	private UserService userService;
	
	//验证用户是否存在
	@ResponseBody
	@RequestMapping(value="/ajaxValidUserName",method=RequestMethod.POST)
	public Map<String, Boolean> validUserName(){
		String username = this.getRequest().getParameter("username");
		User u = userService.queryUserByName(username);
		boolean result = u==null?true:false;
		Map<String, Boolean> map = new HashMap<String,Boolean>();
		map.put("valid", result);
		return map;
	}
	
	//找回密码事件
	@ResponseBody
	@RequestMapping(value="/ajaxFindPwd",method=RequestMethod.POST)
	public Map<String, Object> doFindPassword(Model model){
		Map<String, Object> map = new HashMap<String,Object>();
		String message = "";
		String username = this.getRequest().getParameter("username").trim();
		String code = this.getRequest().getParameter("code").trim(); //验证码
		String sessionCode = (String) this.getRequest().getSession().getAttribute("codeValidate");
		if(!code.toUpperCase().equals(sessionCode.toUpperCase())){
			message = "验证码错误";
			map.put("message", message);
			return map;
		}
		User u = userService.queryUserByName(username);
		if(u==null){
			message = "用户不存在";
			map.put("message", message);
			return map;
		}
		message = "OK";
		map.put("message", message);
		return map;
	}
		
}
