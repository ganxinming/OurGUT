package com.ourspring.groovy.core;

import cn.hutool.core.lang.func.Func;
import com.ourspring.groovy.common.CommonUtil;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

/**
 * @author ganxinming
 * @createDate 2021/8/2
 * @description 执行java定义函数，直接调用封装的对象的方法
 */
public class DemoUseJavaFun {
	public static void main(String[] args) {
		Binding binding = new Binding();
		binding.setVariable("CommonUtil", new CommonUtil());
		GroovyShell shell = new GroovyShell(binding);
		Object value = shell.evaluate("return CommonUtil.testGroovy()");
		System.out.printf("执行结果：%s, 类型：%s\n", value, value.getClass().getCanonicalName());
	}
}
