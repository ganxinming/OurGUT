package com.OurGUT.jsonTest;

import com.OurGUT.jsonTest.bean.LoginMessage;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * @author ganxinming
 * @createDate 2021/4/7
 * @description
 */
public class TestFastJson {

	@Test
	public void testJsonTransform(){
		String ss="{\"androidId\":\"and\",\"cityCode\":\"0571\",\"clientType\":2,\"currentLg\":123.1,\"currentLt\":321.1,\"customerNo\":1234567,\"deviceId\":\"deviceId\",\"imei\":\"imei\",\"ip\":\"127.0.0.1\",\"loginCount\":1,\"method\":1,\"mpType\":1,\"phone\":\"15170276788\",\"randomId\":\"random\",\"requestTimeMillis\":12312312132,\"smdid\":\"smdid123\",\"source\":2,\"timestamp\":12312312,\"type\":2,\"version\":\"5.0.0\"}"
		;
		LoginMessage loginMessage = LoginMessage.builder().androidId("and").cityCode("0571")
				.clientType(2).currentLg(123.1).currentLt(321.1).customerNo(1234567L)
				.deviceId("deviceId").imei("imei").ip("127.0.0.1").loginCount(1)
				.method(1).mpType(1).phone("15170276788").randomId("random").requestTimeMillis(12312312132L)
				.smdid("smdid123").source(2).timestamp(12312312L).type(2).version("5.0.0").build();
		System.out.println(JSON.toJSONString(loginMessage));
	}
}
