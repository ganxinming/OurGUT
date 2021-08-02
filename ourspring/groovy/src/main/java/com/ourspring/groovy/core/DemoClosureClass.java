package com.ourspring.groovy.core;

import groovy.lang.Binding;
import groovy.lang.Closure;
import groovy.lang.GroovyShell;

/**
 * @author ganxinming
 * @createDate 2021/8/2
 * @description 在 Java 中定义一个 Groovy 闭包类
 */
public class DemoClosureClass {

	// 这是一个 groovy 闭包类
	public static class Add extends Closure<Long> {

		public Add() {
			super(null, null);
		}

		@Override
		public Long call(Object... args) {
			long result = 0;
			for (Object obj : args) {
				if (obj instanceof Integer) {
					result = result + (Integer) obj;
					continue;
				}

				if (obj instanceof Long) {
					result = result + (Long) obj;
					continue;
				}
			}
			return result;
		}
	}

	public static void main(String[] args) {
		Binding binding = new Binding();

		binding.setVariable("add", new Add());

		GroovyShell shell = new GroovyShell(binding);

		Object value = shell.evaluate("return add(1,2,3)");

		System.out.printf("执行结果：%s, 类型：%s\n", value, value.getClass().getCanonicalName());
	}

}
