package com.itcast.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("order")
public class OrderController {

	@RequestMapping("save")
	@RequiresRoles(value = { "user", "admin" }, logical = Logical.OR)
	public String save() {
		System.out.println("可使用該權錢");
		return "redirect:/index";
	}

	@ExceptionHandler(Exception.class)
	public String exception(Exception e) {
		System.out.println("沒有該全縣");
		return "redirect:/index";
	}

}
