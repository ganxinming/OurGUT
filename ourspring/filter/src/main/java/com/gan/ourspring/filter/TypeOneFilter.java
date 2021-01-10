package com.gan.ourspring.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author ganxinming
 * @createDate 2021/1/10
 * @description
 */
@Order(1)
@Component
public class TypeOneFilter implements UniversalFilter{

	@Override
	public boolean isFilter(TestDomain domain) {
		System.out.println("进行TypeOneFilter逻辑判断");
		return false;
	}

}
