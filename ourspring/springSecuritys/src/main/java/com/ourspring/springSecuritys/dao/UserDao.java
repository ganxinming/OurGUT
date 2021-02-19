package com.ourspring.springSecuritys.dao;

import com.ourspring.springSecuritys.entity.jpa.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ganxinming
 * @createDate 2021/2/1
 * @description
 */
public interface UserDao extends JpaRepository<User,Long> {
	User findUserByUsername(String username);
}
