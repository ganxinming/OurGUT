package com.ourspring.io.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ganxinming
 * @createDate 2021/8/6
 * @description
 */
@Data
@AllArgsConstructor
public class People implements Serializable {
	private static final long serialVersionUID = 9177387073012897668L;

	private String name;
	private Integer age;
	private Integer mon;
}
