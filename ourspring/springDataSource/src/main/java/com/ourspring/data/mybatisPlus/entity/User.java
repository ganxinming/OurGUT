package com.ourspring.data.mybatisPlus.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ganxinming
 * @createDate 2021/6/11
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Integer id;
	private String name;
	private Integer age;
}
