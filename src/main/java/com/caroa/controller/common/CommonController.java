package com.caroa.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CommonController {
	
	@RequestMapping("/list")
	public String list(){
		return "/index";
	}
}
