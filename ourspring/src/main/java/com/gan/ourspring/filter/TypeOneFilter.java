package com.gan.ourspring.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author ganxinming
 * @createDate 2020/11/4
 * @description @Order注入顺序，多个实例注入，可以用List<UniversalFilter>
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
