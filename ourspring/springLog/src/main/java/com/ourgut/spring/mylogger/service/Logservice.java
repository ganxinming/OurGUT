package com.ourgut.spring.mylogger.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

/**
 * @author ganxinming
 * @createDate 2021/6/29
 * @description
 */
@Service
@Slf4j
public class Logservice {

	public void testTrace(){
		log.info("链路追踪，看下和controller是否一致");
	}
}
