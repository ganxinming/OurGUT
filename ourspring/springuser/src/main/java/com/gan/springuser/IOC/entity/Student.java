package com.gan.springuser.IOC.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ganxinming
 * @createDate 2021/2/4
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	private String name;
}
