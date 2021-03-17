package com.ourspring.springSecuritys.service;

import com.ourspring.springSecuritys.entity.jpa.User;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author ganxinming
 * @createDate 2021/3/3
 * @description
 */
public interface UserService extends UserDetailsService {
	void addUser(User user);
	Authentication getUserData();
}
