package com.ourspring.springdubboprovider.service.impl;

import com.springDubbo.dubboService.TestService;

import org.springframework.stereotype.Service;

/**
 * @author ganxinming
 * @createDate 2021/3/17
 * @description
 */
@Service("testService")
public class TestServiceImpl implements TestService {
	@Override
	public String startInvoke() {
		return "startInvoke success";
	}
}
