package com.gan.ourspring.aop.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ganxinming
 * @createDate 2021/9/16
 * @description
 */
@Data
@AllArgsConstructor
public class Family implements Serializable {
	private String name;
}
