package com.caroa.controller.login;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caroa.common.TaskStatus;
import com.caroa.common.TaskStatus.Result;
import com.caroa.controller.BaseController;
import com.caroa.service.user.UserService;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	
	@Resource
	UserService userService;
	
	//首页
	@FormData(save=true)
	@RequestMapping("/index")
	public String index(Model model){
		return R.view("/login/login");
	}
	
	//登陆
	@FormData(remove=true)
	@RequestMapping("/login")
	public String login(Model model){
		String username = this.getRequest().getParameter("username");
		String password = this.getRequest().getParameter("password");
		String isRemember = this.getRequest().getParameter("remember");
		TaskStatus status = userService.doLogin(username, password, isRemember);
		if(status.getResult().equals(Result.NG)){
			model.addAttribute("message", "未找到该用户");
			return R.view("/login/login");
		}
		return "/index/index";
	}
	
	//注册
	@FormData(save=true)
	@RequestMapping("/regisiter")
	public String regisiter(){
		return "regisiter/regisiter";
	}
	
	//注册事件
	@FormData(remove=true)
	@RequestMapping("/doregisiter")
	public String doregisiter(Model model){
		String username = this.getRequest().getParameter("username");
		String password = this.getRequest().getParameter("password");
		String displayName = this.getRequest().getParameter("displayName");
		String email = this.getRequest().getParameter("email");
		String address = this.getRequest().getParameter("address");
		String phoneNum = this.getRequest().getParameter("phoneNum");
		String memo = this.getRequest().getParameter("memo");
		userService.save(username,password,displayName,email,address,phoneNum,memo);
		model.addAttribute("message" ,"注册成功！");
		return "/common/success";
	}
}
