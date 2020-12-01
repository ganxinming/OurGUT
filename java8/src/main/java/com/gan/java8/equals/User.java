package com.gan.java8.equals;

import java.util.Objects;

import lombok.Data;

/**
 * @author ganxinming
 * @createDate 2020/11/30
 * @description 优雅判断两个对象是否相等
 */
@Data
public class User {

	private String id;
	private String name;

	@Override
	public int hashCode() {

		return Objects.hash(id,name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		User user=(User)obj;

		return Objects.equals(id,user.getId()) && Objects.equals(name,user.getName());
	}
}
