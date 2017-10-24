package com.dms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dms.utility.GlobalFunction;

@Controller
@RequestMapping("/admin")
public class AdminController {

	static int seq = 0;
	private GlobalFunction globalfunciton;

	public AdminController() {
		// TODO Auto-generated constructor stub
		globalfunciton = new GlobalFunction();
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String adminHome() {
		
		return "/admin/home";

	}
}
