package com.gan.java8.thread.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ganxinming
 * @createDate 2020/11/26
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private int id;
	private String name;
}
