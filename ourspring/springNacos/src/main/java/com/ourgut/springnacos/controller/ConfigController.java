package com.ourgut.springnacos.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import com.alibaba.nacos.api.config.annotation.NacosValue;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ganxinming
 * @createDate 2021/7/5
 * @description
 */
@Controller
@RequestMapping("config")
public class ConfigController {

	@NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
	private boolean useLocalCache;

	@RequestMapping(value = "/get", method = GET)
	@ResponseBody
	public boolean get() {
		return useLocalCache;
	}
}
