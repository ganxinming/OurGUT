package com.ourgut.springtest;

import java.net.URI;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/7/8
 * @description
 */
@RestController
public class TestController {

	@RequestMapping("test")
	public String test(HttpRequest request){
		URI uri = request.getURI();
		return String.valueOf(uri.getPort());
	}

}
