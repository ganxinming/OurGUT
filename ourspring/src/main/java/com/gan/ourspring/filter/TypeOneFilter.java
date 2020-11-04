package com.gan.ourspring.filter;

/**
 * @author ganxinming
 * @createDate 2020/11/4
 * @description
 */
public class TypeOneFilter implements UniversalFilter{

	@Override
	public boolean isFilter(TestDomain domain) {
		return false;
	}
	
}
