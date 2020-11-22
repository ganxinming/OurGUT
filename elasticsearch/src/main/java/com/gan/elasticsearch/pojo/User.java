package com.gan.elasticsearch.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ganxinming
 * @createDate 2020/11/22
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private String id;
	private Integer age;
	private String name;
}
