package com.ourspring.data.jpa.entity.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author ganxinming
 * @createDate 2021/6/11
 * @description
 */

@Entity(name = "user")
@Data
public class User {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer age;

	public User(){}

	public User(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
}
