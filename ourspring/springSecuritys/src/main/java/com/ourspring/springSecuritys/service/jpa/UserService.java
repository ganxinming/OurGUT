package com.ourspring.springSecuritys.service.jpa;

import com.ourspring.springSecuritys.dao.UserDao;
import com.ourspring.springSecuritys.entity.jpa.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

/**
 * @author ganxinming
 * @createDate 2021/2/1
 * @description
 */
@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserDao userDao;

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
	protected void getUserData(){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
		//WebAuthenticationDetails@fffc7f0c: RemoteIpAddress: 127.0.0.1; SessionId: 303C7F254DF8B86667A2B20AA0667160
		System.out.println(details);
	}

}
