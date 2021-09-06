package com.ourgut.spring.sqlite.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/8/30
 * @description
 */
@RestController
public class TestConroller {
	@Autowired
	private JdbcTemplate template;

	@RequestMapping("/test1")
	public void test1() throws SQLException {
		testSqlCreate();
		testInsertSql();
		query();
	}
	@RequestMapping("/test2")
	public void test2(){
		query();
	}



	public void testSqlCreate() {

		template.execute( "CREATE TABLE COMPANY1 " +
				"(ID INT PRIMARY KEY     NOT NULL," +
				" NAME           TEXT    NOT NULL, " +
				" AGE            INT     NOT NULL, " +
				" ADDRESS        CHAR(50), " +
				" SALARY         REAL)");

	}

	public void testInsertSql() {
		int update = template.update("INSERT INTO COMPANY1 (ID,NAME,AGE,ADDRESS,SALARY) " +
				"VALUES (1, 'Paul', 32, 'California', 20000.00 );");
		System.out.println(update);
	}

	public void query(){
		List<Map<String, Object>> list = template.queryForList("SELECT * FROM COMPANY1 ");
		System.out.println(list);
	}
}
