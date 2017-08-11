package com.caroa.controller.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caroa.controller.BaseController;
import com.caroa.service.file.FileService;

@Controller
public class IndexController extends BaseController{
	
	@Resource
	FileService fileService;
	
	//首页
	@RequestMapping("/index")
	public String index(Model model){
		System.out.println(new Date());
		List<Map<String, Object>> newFile = fileService.findNewFile(); //最新文件
		List<Map<String, Object>> hotFile = fileService.findHotFile();  //热门文件
		model.addAttribute("newfile", newFile);
		model.addAttribute("hotfile", hotFile);
		return R.view("/index");
	}
	
	//错误页面
	@RequestMapping("/toerror")
	public String error(){
		return R.view("/common/error");
	}
	
	//没有权限
	@RequestMapping("/unauthor")
	public String unauthor(){
		return R.view("/common/unauthor");
	}
}
