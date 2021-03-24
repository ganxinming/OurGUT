package com.ourspring.springSecuritys.controller;

import com.ourspring.springSecuritys.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	/**
	 * 实现UserDetailsService，将密码认证的过程交给springSecurity处理(一般做法)
	 * @return
	 */
	@RequestMapping("/login")
	public String login(){
		Authentication userData = userService.getUserData();
		System.out.println(userData.toString());
		return "login";
	}

	/**
	 * 自定义认证过程
	 * 其实和拦截器里的差不多
	 * 1.生成UsernamePasswordAuthenticationToken
	 * 2.交给provider进行认证token，authenticate
	 * 3.得到Authentication用户信息
	 * 4.保存在SecurityContextHolder中
	 * @return
	 */
	@RequestMapping("/login2")
	public String login2(){

		/**
		 *  对验证码进行校验
		 */

		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken("username", "password");
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		/**
		 * 认证通过，生成token
		 */

		System.out.println(authentication);
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
