package com.ourspring.data.jpa.entity.second;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ganxinming
 * @createDate 2021/6/11
 * @description
 */
@Entity(name = "message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
	@Id
	@GeneratedValue
	private Long id;

	//为null不插入
	@Column(nullable = false)
	private String name;

	//重复不插入
	@Column(unique=true)
	private String content;


	public Message(String name, String content) {
		this.name = name;
		this.content = content;
	}

}
