package com.ourGut.ourSpring.springmvc.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/4/13
 * @description
 */
@RestController
public class TestResponse {

	@RequestMapping("/getResponse")
	public HttpServletResponse getResponse(HttpServletResponse response) throws IOException {
		 setResponseErrorJson(response,"错误");
		 return null;
	}

	private HttpServletResponse setResponseErrorJson(HttpServletResponse response,String msg) throws IOException {
		response.reset();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		Map<String, Object> map = Maps.newHashMap();
		map.put("is_success", false);
		map.put("code", -1);
		map.put("msg", msg);
		response.getWriter().println(JSON.toJSONString(map));
		response.getWriter().flush();
		return response;
	}
}
