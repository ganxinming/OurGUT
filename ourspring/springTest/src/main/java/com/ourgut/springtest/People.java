package com.ourgut.springtest;

import java.util.Objects;

import lombok.AllArgsConstructor;

/**
 * @author ganxinming
 * @createDate 2021/6/30
 * @description
 */
@AllArgsConstructor
public class People {
	String name;
	Integer age;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof People)) return false;
		People people = (People) o;
		return name.equals(people.name) &&
				age.equals(people.age);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, age);
	}
}
