package com.itcast.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itcast.entity.User;
import com.itcast.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("register")
	public String register(User user) {
		try {
			userService.register(user);
		}catch (Exception e) {
			e.printStackTrace();
			return "redirect:/register";
		}
		return "redirect:/login";
	}
	
	@RequestMapping("loginview")
	public String login() {
		return "login";
	}

	@GetMapping("logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();	
		return "login";
	}

	@RequestMapping("login")
	public String login(String username, String password) {

		try {

			Subject subject = SecurityUtils.getSubject();
			subject.login(new UsernamePasswordToken(username, password));
			return "redirect:/index";

		} catch (UnknownAccountException e) {
			e.printStackTrace();
			System.out.println("用户名错误！");
		} catch (IncorrectCredentialsException e) {
			e.printStackTrace();
			System.out.println("密码错误！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/user/loginview";
	}
}
