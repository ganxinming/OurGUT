package com.ourspring.springdubboprovider.service.impl;

import com.springDubbo.dubboService.generic.GenelService;

import org.springframework.stereotype.Service;

/**
 * @author ganxinming
 * @createDate 2021/3/18
 * @description
 */
@Service("genelService")
public class GenelServiceImpl implements GenelService {

	@Override
	public String testInvoke() {
		return "GenelService";
	}
}
