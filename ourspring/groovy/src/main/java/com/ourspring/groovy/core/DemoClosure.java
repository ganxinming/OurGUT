package com.ourspring.groovy.core;

import java.util.List;
import java.util.stream.Collectors;

import groovy.lang.Binding;
import groovy.lang.Closure;
import groovy.lang.GroovyShell;

/**
 * @author ganxinming
 * @createDate 2021/8/2
 * @description groovy 有一个闭包的概念，类似 java 中的 lambda 。
 */
public class DemoClosure {

	public static class Func {

		// 过滤函数，第2个参数是闭包
		public List<Integer> filter(List<Integer> list, Closure<Boolean> closure) {
			return list.stream().filter(x -> {
				return closure.call(x);
			}).collect(Collectors.toList());
		}

	}

	public static void main(String[] args) {
		Binding binding = new Binding();
		binding.setVariable("func", new Func());

		GroovyShell shell = new GroovyShell(binding);

		Object value = shell.evaluate("return func.filter([-1,2,3,4], {x -> x > 0} )");

		System.out.printf("执行结果：%s, 类型：%s\n", value, value.getClass().getCanonicalName());
	}
}
