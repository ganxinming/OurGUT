package com.gan.ourspring.fileUpAnddown.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ganxinming
 * @createDate 2021/2/19
 * @description
 */
@Controller
public class IndexController {

	@RequestMapping("/gotoUpload")
	public String gotoUpload(){
		return "/gotoUpload";
	}
}
