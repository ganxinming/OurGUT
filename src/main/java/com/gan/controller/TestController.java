package com.gan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2020/10/18
 * @description
 */
@RestController
public class TestController {

	@RequestMapping("/test")
	public String testBody(){
		return "Hello SpringBoot";
	}
}
