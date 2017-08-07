package com.caroa.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caroa.controller.BaseController;

@Controller
public class IndexController extends BaseController{
	
	@RequestMapping("/index")
	public String index(){
		return "/login/login";
	}
	
	@RequestMapping("/toerror")
	public String error(){
		return "/common/error";
	}
}
