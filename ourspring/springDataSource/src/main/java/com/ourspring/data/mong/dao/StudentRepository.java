package com.ourspring.data.mong.dao;

import com.ourspring.data.mong.entity.Student;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ganxinming
 * @createDate 2021/6/10
 * @description
 */
@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

}
