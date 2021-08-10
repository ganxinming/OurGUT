package com.ourspring.springSecuritys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ourspring.springSecuritys.entity.jpa.User;
import com.ourspring.springSecuritys.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class UserController {

	private static String tokenKey="dasdaszxvgwsaS";

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



	/**
	 * 生成jwt token
	 * @return
	 */
	@RequestMapping("/loginToken")
	public String loginToken(){
		//登录成功后
		User user=new User();
		user.setUsername("gan");
		user.setPassword("123");
		user.setId(123456L);
		//封装角色信息
		HashMap<String, Object> map = Maps.newHashMap();
		map.put("role","角色");
		//验证完用户真实有效后，发放token
		String token = Jwts.builder()
				.setSubject(user.getUsername())//设置subject，它将首先确保作为 JWT 主体存在 Claims 实例，然后使用指定的值设置 Claims
				.setClaims(map) //可以存放一些其他信息，如：角色，权限,但是其实这里其他的所有信息都会封装到claims中
				.setId(user.getId().toString())//设置ID
				.setIssuedAt(new Date())//jwt创建时间
				.setExpiration(new Date(System.currentTimeMillis() + 3600000))//设置过期时间
				.signWith(SignatureAlgorithm.HS256, tokenKey)//设置加密方式
				.compact();
		return token;
	}


	/**
	 * 解析token
	 * eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoi6KeS6ImyIiwiZXhwIjoxNjI4NDA4NTA3LCJpYXQiOjE2Mjg0MDQ5MDcsImp0aSI6IjEyMzQ1NiJ9.XICvCsmcTgD5BEx90vejVfgrzdtEmzwgGDxvA6LhB5Y
	 * @return
	 */
	@RequestMapping("/loginDeToken")
	public String loginDeToken(String token){
		deToken(token);
		return "success";
	}

	public static void deToken(String token){
		JwtParser parser = Jwts.parser();
		parser.setSigningKey(tokenKey);//设置解析密码

		//token密码正确，且在有效期内则能正常解析，某则抛异常
		Jws<Claims> claimsJws = parser.parseClaimsJws(token);
		Claims jwsBody = claimsJws.getBody();
		log.info("subject:{}", jwsBody.getSubject());
		log.info("Id:{}", jwsBody.getId());
		log.info("Expiration:{}",jwsBody.getExpiration());
		log.info("IssuedAt:{}",jwsBody.getIssuedAt());
		log.info("map:{}",jwsBody.get("role"));
	}

}
