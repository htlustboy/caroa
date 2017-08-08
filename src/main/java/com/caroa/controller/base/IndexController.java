package com.caroa.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caroa.controller.BaseController;

@Controller
public class IndexController extends BaseController{
	
	//首页
	@RequestMapping("/index")
	public String index(){
		return "/login/login";
	}
	
	//错误页面
	@RequestMapping("/toerror")
	public String error(){
		return "/common/error";
	}
	
	//没有权限
	@RequestMapping("/unauthor")
	public String unauthor(){
		return "/common/unauthor";
	}
}
