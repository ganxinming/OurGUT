package com.ourspring.data.mong.controller;

import com.ourspring.data.mong.entity.Student;
import com.ourspring.data.mong.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/6/10
 * @description
 *
 * 可以使用mongoTemplate和MongoRepository两种方式
 * 使用MongoRepository可以支持事务，且符合springData规范
 * 使用mongoTemplate，简单，方便
 */
@RestController
public class TestMongo {

	@Autowired MongoTemplate mongoTemplate;

	@Autowired StudentService studentService;

	@RequestMapping("/testMongo")
	public String testMongo(){
		Student student=new Student();
		student.setId("123");
		student.setAge(100);
		student.setExample("无关紧要");
		mongoTemplate.insert(student);
		return "123";
	}


	@RequestMapping("/testMongoRepository")
	public String testMongoRepository(String id) throws Exception {
		Student student=new Student();
		student.setId(id);
		student.setAge(100);
		student.setExample("无关紧要");
		studentService.addStudent(student);
		return "123";
	}
}
