package com.ourspring.springSecuritys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/1/26
 * @description
 */

@RestController
public class LoginController {

	@RequestMapping("/login")
	public String login(){
		return "登录了";
	}

}
