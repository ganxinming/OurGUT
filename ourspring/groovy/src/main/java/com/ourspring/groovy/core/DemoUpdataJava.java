package com.ourspring.groovy.core;

import java.util.Arrays;
import java.util.List;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

/**
 * @author ganxinming
 * @createDate 2021/8/2
 * @description 修改java变量
 */
public class DemoUpdataJava {

	public static class Person {
		public String name;
		public int age;
		public List<String> books;

		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return "Person{" +
					"name='" + name + '\'' +
					", age=" + age +
					", books=" + books +
					'}';
		}
	}

	public static void main(String[] args) {
		Binding binding = new Binding();

		Person person = new Person();
		person.name = "lt";
		person.age = 10;
		person.books = Arrays.asList("book0", "book1");

		System.out.println("修改前: " + person);

		binding.setVariable("person", person);

		GroovyShell shell = new GroovyShell(binding);

		Object value = shell.evaluate("println 'in groovy books : ' + person.books; person.books[1] = 'new book1';return person.name");

		System.out.printf("执行结果：%s, 类型：%s\n", value, value.getClass().getCanonicalName());

		System.out.println("修改后: " + person);
	}

}
