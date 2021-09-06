//package com.ourspring.data.mong.service;
//
//import java.util.List;
//
//import com.ourspring.data.mong.dao.StudentRepository;
//import com.ourspring.data.mong.entity.Student;
//import lombok.extern.slf4j.Slf4j;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * @author ganxinming
// * @createDate 2021/6/10
// * @description
// */
//@Service
//@Slf4j
//public class StudentServiceImpl implements StudentService {
//
//	@Autowired
//	private StudentRepository studentRepository;
//
//	/**
//	 * 添加学生信息
//	 * @param student
//	 * @return mongodb在4.0的版本之后，增加了事务处理机制，但依赖于mongodb的副本集
//	 * 所以需要搭建副本集才能生效，否则无效
//	 */
//	@Override
//	@Transactional(rollbackFor = Exception.class)
//	public Student addStudent(Student student) throws Exception {
//		Student student1=new Student();
//		try {
//			studentRepository.save(student);
//			int a=1/0;
//		} catch (Exception e){
//			log.info("插入出错");
//			throw new Exception();
//		}
//		return student1;
//	}
//
//	/**
//	 * 根据 id 删除学生信息
//	 * @param id
//	 */
//	@Override
//	public void deleteStudent(String id) {
//		studentRepository.deleteById(id);
//	}
//
//	@Override
//	public Student updateStudent(Student student) {
//		return null;
//	}
//
//	@Override
//	public Student findStudentById(String id) {
//		return null;
//	}
//
//	@Override
//	public List<Student> findAllStudent() {
//		return null;
//	}
//}
//
