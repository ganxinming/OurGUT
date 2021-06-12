package com.ourspring.data.mong.entity;

import javax.validation.constraints.NotNull;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ganxinming
 * @createDate 2021/6/10
 * @description
 */
@Data
@Document//类似于hibernate的entity注解，表示由mongo来维护该表。
public class Student {

	@Id
	private String id;//如果自己不设置@Id主键，mongo会自动生成一个唯一主键，并且插入时效率远高于自己设置主键。

	@NotNull
	private String studentId;

	private Integer age;

	private String name;

	private String gender;

	@Transient
	private String example;//@Transient，被该注解标注的，将不会被录入到数据库中。只作为普通的javaBean属性

}
