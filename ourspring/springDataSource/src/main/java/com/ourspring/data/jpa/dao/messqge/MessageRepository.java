package com.ourspring.data.jpa.dao.messqge;

import com.ourspring.data.jpa.entity.second.Message;


import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ganxinming
 * @createDate 2021/6/11
 * @description
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
