package com.gan.ourspring.filter;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ganxinming
 * @createDate 2020/11/26
 * @description
 */
@Controller
public class FilterController {

	@Autowired
	List<UniversalFilter> filters;

	@RequestMapping("/testFilter")
	public void testFilter(){
		List<TestDomain> domains= Lists.newArrayList();
		for (int i = 0; i <10 ; i++) {
			TestDomain dom=new TestDomain();
			domains.add(dom);
		}
		List<TestDomain> collect = domains.stream().filter(domain -> IsFilter(domain)).collect(Collectors.toList());
		System.out.println(collect);
	}

	public boolean IsFilter(TestDomain domain){

		for (UniversalFilter filter : filters){
			if(filter.isFilter(domain)){
				//如果被过滤了，进行一些数据库打标操作(打上过滤标志，且过滤器是哪个)，或者日志操作
				return false;
			}
		}
		return true;
	}
}
