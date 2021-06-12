package com.ourspring.data.jpa.dao.user;

import com.ourspring.data.jpa.entity.primary.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ganxinming
 * @createDate 2021/6/11
 * @description
 */
public interface UserRepository extends JpaRepository<User, Integer> {

}
