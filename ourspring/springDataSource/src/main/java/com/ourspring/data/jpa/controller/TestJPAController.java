package com.ourspring.data.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ganxinming
 * @createDate 2021/6/11
 * @description
 */
@Controller
@RequestMapping("/jpa")
public class TestJPAController {

	@Autowired
	@Qualifier("primaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate1;

	@Autowired
	@Qualifier("secondJdbcTemplate")
	protected JdbcTemplate jdbcTemplate2;


}
