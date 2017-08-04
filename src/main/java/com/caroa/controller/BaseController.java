package com.caroa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/base")
public class BaseController {
	
	@RequestMapping("/index")
	public String list(){
		return "login";
	}
}
