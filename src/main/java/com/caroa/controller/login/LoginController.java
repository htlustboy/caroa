package com.caroa.controller.login;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.caroa.common.TaskStatus;
import com.caroa.common.TaskStatus.Result;
import com.caroa.controller.BaseController;
import com.caroa.model.User;
import com.caroa.service.user.UserService;
import com.caroa.util.BaseUtil;
import com.caroa.util.EmailUtil;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	
	@Resource
	UserService userService;
	
	//首页
	@RequestMapping("/index")
	public String index(Model model){
		return R.view("login/login");
	}
	
	//登陆
	@RequestMapping("/login")
	public String login(Model model){
		String username = this.getRequest().getParameter("username");
		String password = this.getRequest().getParameter("password");
		String isRemember = this.getRequest().getParameter("remember");
		TaskStatus status = userService.doLogin(username, password, isRemember);
		if(status.getResult().equals(Result.NG)){
			model.addAttribute("message", status.getMessage());
			return R.view("/login/login");
		}
		return R.view("/index");
	}
	
	//注册
	@FormData(save=true)
	@RequestMapping("/regisiter")
	public String regisiter(){
		return R.view("regisiter/regisiter");
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
		return R.view("/common/success");
	}
	
	//找回密码页面
	@RequestMapping("/findPwd")
	public String findPassword(){
		return R.view("/password/password");
	}
	
	//注销
	@RequestMapping("/logout")
	public String logout(){
		//清空session
		this.getRequest().getSession().invalidate();
		BaseUtil.logout();
		return R.view("/index", true);
	}
	
	//修改密码发送email
	@RequestMapping(value="/sendEmail",method=RequestMethod.GET)
	public String sendEmail(Model model){
		String username = this.getRequest().getParameter("username");
		String email = this.getRequest().getParameter("email");
		String code = BaseUtil.getRamdomUUID(6);
		//1.数据库保存激活码
		User u = userService.queryUserByName(username);
		u.setIntergral(code);
		userService.updateUserCode(username,code);
		//2.发送邮件
		EmailUtil.sendEmail(email, username, code);
		String message = "尊敬的"+username+": ，密码找回已发送到您的邮箱，请打开 "+
						 "<a href=/login/openEmail?username="+
						 username+">"+email+
						 "</a>"+" 邮箱，输入激活码，完成密码修改！"; 
		model.addAttribute("message", message);
		return R.view("/common/success");
	}
	
	//跳转修改密码页面
	@RequestMapping(value="/openEmail",method=RequestMethod.GET)
	public String openEmail(Model model){
		String username = this.getRequest().getParameter("username");
		model.addAttribute("username", username);
		return R.view("/password/update");
	}
	
	//修改密码事件
	@RequestMapping("/doUpdatePwd")
	public String doUpdatePwd(Model model){
		String username = this.getRequest().getParameter("username");
		String password = this.getRequest().getParameter("password");
		//加密
		password = BaseUtil.password2Hex(username, password);
		String code = this.getRequest().getParameter("code");
		boolean result = userService.updatePwd(username,password,code);
		if(result){
			model.addAttribute("message", "修改密码成功");
			return R.view("/common/success");
		}else{
			model.addAttribute("message", "修改失败");
			return R.view("/common/error");
		}
	}
}
