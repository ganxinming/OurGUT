package com.ourspring.springSecuritys.controller;

import java.util.Objects;

import com.ourspring.springSecuritys.entity.jpa.User;
import com.ourspring.springSecuritys.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/3/3
 * @description
 */

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@RequestMapping("/doRegister")
	public String doRegister(User user){
		System.out.println(user.getUsername() + "  " + user.getPassword());
		userService.addUser(user);
		return "注册成功";
	}

	@RequestMapping("/doLogin")
	public String doLogin(User user){
		UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
		if(Objects.nonNull(userDetails)){
			return "登录成功";
		}
		return "登录失败";
	}

}
