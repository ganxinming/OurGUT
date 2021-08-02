package com.ourspring.sendHttpRequest.controller;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/7/8
 * @description
 */
@RestController
@Slf4j
public class TestController {

	public static int n=1;
	public static int m=1;

	@RequestMapping("/test")
	public String test(HttpServletRequest request){
		String requestURI = request.getRequestURI();
		log.info("第{}次",n++);
		return requestURI;
	}

	@RequestMapping("/test1")
	public String test1(HttpServletRequest request){
		String requestURI = request.getRequestURI();
		log.info("第{}次",m++);
		return requestURI;
	}
}
