package com.ourspring.data.mong.service;

import java.util.List;

import com.ourspring.data.mong.entity.Student;

/**
 * @author ganxinming
 * @createDate 2021/6/10
 * @description
 */
public interface StudentService {
	Student addStudent(Student student) throws Exception;

	void deleteStudent(String id);

	Student updateStudent(Student student);

	Student findStudentById(String id);

	List<Student> findAllStudent();
}
