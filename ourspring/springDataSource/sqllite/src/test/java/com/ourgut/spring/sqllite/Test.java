package com.ourgut.spring.sqllite;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import com.ourgut.spring.sqlite.BootStrapApplication;
//import com.ourgut.spring.sqlite.dao.UserDao;
import com.ourgut.spring.sqlite.entity.UserDO;
import org.junit.runner.RunWith;
import org.sqlite.SQLiteConnection;
import org.sqlite.core.DB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ganxinming
 * @createDate 2021/8/27
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootStrapApplication.class)
public class Test {
//	@Autowired
//	private UserDao userDao;

	@Autowired
	private JdbcTemplate template;

	@org.junit.Test
	public void testInsert() {
//		UserDO user = new UserDO();
//		user.setUsername("123");
//		user.setPassword("123");
//		userDao.insert(user);
//		System.out.println(user);
//		UserDO userDO = userDao.selectById(1);
//		System.out.println(userDO);
		Map<String, Object> map = template.queryForMap("SELECT id, username, password, create_time FROM users WHERE id = 1");
		System.out.println(map);
	}

	@org.junit.Test
	public void testSqlLite() {

		List<Map<String, Object>> list = template.queryForList("SELECT * FROM users ");
		System.out.println(list);
	}


	@org.junit.Test
	public void testSqlCreate() {


	}
	public void testInsertSql() {
		int update = template.update("INSERT INTO COMPANY1 (ID,NAME,AGE,ADDRESS,SALARY) " +
				"VALUES (1, 'Paul', 32, 'California', 20000.00 );");
		System.out.println(update);
	}

	@org.junit.Test
	public void testQuery() {
		testSqlCreate();
		testInsertSql();
		List<Map<String, Object>> list = template.queryForList("SELECT * FROM COMPANY1 ");
		System.out.println(list);
	}
}
