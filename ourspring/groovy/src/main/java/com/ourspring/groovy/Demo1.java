package com.ourspring.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

/**
 * @author ganxinming
 * @createDate 2021/8/2
 * @description
 */
public class Demo1 {
	public static void main(String[] args) {
		Binding binding = new Binding();

		// 绑定变量到 groovy 执行环境
		binding.setVariable("foo", new Integer(2));

		// 构建 groovy shell
		GroovyShell shell = new GroovyShell(binding);

		// 执行脚本
		Object value = shell.evaluate("println 'Hello World!'; x = 123; return foo * 10");

		// 执行结果
		System.out.printf("执行结果：%s, 类型: %s\n", value, value.getClass().getCanonicalName());

		// 获取执行过程中的变量
		System.out.printf("执行过程中的x变量：%s, 类型: %s\n", binding.getVariable("x"), binding.getVariable("x").getClass().getCanonicalName());
	}

}
