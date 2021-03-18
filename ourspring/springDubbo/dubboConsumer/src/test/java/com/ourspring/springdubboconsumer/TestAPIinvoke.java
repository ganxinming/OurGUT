package com.ourspring.springdubboconsumer;

import com.springDubbo.dubboService.TestService;
import com.springDubbo.dubboService.generic.GenelService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.junit.jupiter.api.Test;

/**
 * @author ganxinming
 * @createDate 2021/3/18
 * @description
 */

public class TestAPIinvoke {

	@Test
	public void testAPI(){
		// 当前应用配置
		ApplicationConfig application = new ApplicationConfig();
		application.setName("demo-consumer-test");
// 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接

		ReferenceConfig<GenelService> referenceConfig = new ReferenceConfig<GenelService>();
		referenceConfig.setApplication(new ApplicationConfig("first-dubbo-consumer"));
		referenceConfig.setRegistry(new RegistryConfig("zookeeper://115.159.202.204:2181"));
		referenceConfig.setInterface(GenelService.class);
		GenelService greetingService = referenceConfig.get();
		System.out.println(greetingService.testInvoke());
	}
}
