package com.caroa.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

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
	
	@ResponseBody
	@RequestMapping(value="/validUserName",method=RequestMethod.POST)
	public Map<String, Boolean> validUserName(){
		String username = this.getRequest().getParameter("username");
		User u = userService.queryUserByName(username);
		boolean result = u==null?true:false;
		Map<String, Boolean> map = new HashMap<String,Boolean>();
		map.put("valid", result);
		return map;
	}
}
