package com.gan.util.date;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.junit.Test;

/**
 * @author ganxinming
 * @createDate 2021/3/19
 * @description
 */
public class testJson {

	@Test
	public void testJsonArray(){
//		List<Object> objects= Lists.newArrayList("R0001","R002");
//		String s = JSONObject.toJSONString(objects);
//		System.out.println(s);
//		Object parse = JSONObject.parse("[\"R0001\",\"R002\"]");
//		if (parse instanceof List){
//			System.out.println(1);
//		}

		List<Object> objectList=Lists.newArrayList("123","abc",1234);
//		String a="abc";
		int a=1234;
		if (objectList.contains(a)){
			System.out.println(a);
		}
	}
}

