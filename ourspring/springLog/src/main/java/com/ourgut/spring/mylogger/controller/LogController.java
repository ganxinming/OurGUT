package com.ourgut.spring.mylogger.controller;

import com.ourgut.spring.mylogger.service.Logservice;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/6/28
 * @description
 */
@RestController
@Slf4j
public class LogController {

	@Autowired Logservice logservice;
	@RequestMapping("/test")
	public String test(){
		log.info("request success");
		logservice.testTrace();
		return "test";
	}
}
