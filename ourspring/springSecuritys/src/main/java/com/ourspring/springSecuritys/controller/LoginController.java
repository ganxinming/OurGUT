package com.ourspring.springSecuritys.controller;

import com.ourspring.springSecuritys.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/1/26
 * @description
 */

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final UserService userService;

	@RequestMapping("/login")
	public String login(){
		Authentication userData = userService.getUserData();
		System.out.println(userData.toString());
		return "login";
	}

	@RequestMapping("/index")
	public String index(){
		return "index";
	}

	@RequestMapping("/register")
	public String register(){
		return "register";
	}

}
