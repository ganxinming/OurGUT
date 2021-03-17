package com.ourspring.springSecuritys.service.impl;

import java.util.Objects;

import com.ourspring.springSecuritys.dao.UserDao;
import com.ourspring.springSecuritys.entity.jpa.User;
import com.ourspring.springSecuritys.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

/**
 * @author ganxinming
 * @createDate 2021/2/1
 * @description
 * loadUserByUsername 通过用户名来加载用户 。这个方法主要用于从系统数据中查询并加载具体的用户到
 * Spring Security中。
 * 使用：简单的登录特别容易实现，实现UserService接口后
 * 在WebSecurityConfig文件中设置下使用该service就行了，会自动调用loadUserByUsername方法读取用户
 * 然后进行密码，是否有效等认证
 */
@Service
public class UserServiceImpl implements  UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		return user;
	}

	/**
	 * 获取用户详细信息
	 * Authentication 基本可以代表一个user所有信息
	 * ip，sessionId等
	 */
	@Override
	public Authentication getUserData(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
		System.out.println(details);
		return authentication;
	}

	@Override
	public void addUser(User user) {

		if (Objects.nonNull(user.getUsername())  && Objects.nonNull(user.getPassword())){
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userDao.save(user);
		}
		return ;
	}
}
