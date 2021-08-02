package com.ourspring.groovy.core;

import com.ourspring.groovy.common.CommonUtil;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

/**
 * @author ganxinming
 * @createDate 2021/8/2
 * @description
 */
public class DemoUseStatic {
	public static void main(String[] args) {
		Binding binding=new Binding();
		binding.setVariable("CommonUtil", CommonUtil.class);
		GroovyShell groovyShell = new GroovyShell(binding);
		Object evaluate = groovyShell.evaluate("return CommonUtil.testSataicGroovy()");
		System.out.printf("执行结果：%s, 类型：%s\n", evaluate, evaluate.getClass().getCanonicalName());
	}
}
