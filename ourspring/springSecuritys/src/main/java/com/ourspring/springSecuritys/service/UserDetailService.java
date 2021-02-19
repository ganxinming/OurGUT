package com.ourspring.springSecuritys.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author ganxinming
 * @createDate 2021/1/27
 * @description
 * UserDetailsService 包含用户的所有信息，可以查看他的不同实现
 * 使用 JdbcUserDetailsManager 可以让我们通过 JDBC 的方式将数据库和 Spring Security 连接起来
 */
public class UserDetailService implements UserDetailsService {

	//根据用户名查询用户
	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return null;
	}

	public void saveUser(){
		UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken("test", "test");

		/**
		 * 获得token后存入用户信息，内部采用ThreadLocal
		 */
		SecurityContextHolder.getContext().setAuthentication(token);
	}
}
