package com.gan.ourspring.filter;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/1/10
 * @description
 */
@Slf4j
@RestController
public class UserController {

	@RequestMapping("/testUser")
	public String testUser(@RequestBody @Valid User user, BindingResult bindingResult){
		//参数校验的数据存入BindingResult中
		for (ObjectError error : bindingResult.getAllErrors()){
			return error.getDefaultMessage();
		}
		System.out.println(user);
		return "success";
	}

}
