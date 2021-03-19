package com.ourspring.springdubboconsumer;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.springDubbo.dubboService.TestService;
import com.springDubbo.dubboService.generic.GenelService;
import lombok.SneakyThrows;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.junit.jupiter.api.Test;

/**
 * @author ganxinming
 * @createDate 2021/3/18
 * @description
 */

public class TestAPIinvoke {

	/**
	 * API的 形式调用dubbo
	 */
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


	/**
	 * 泛化调用
	 *	只需要知道 zk地址，接口全限定名，方法名，参数
	 */
	@SneakyThrows
	@Test
	public void testGenAPI(){
		ApplicationConfig application = new ApplicationConfig();
		application.setName("api-generic-consumer");

		RegistryConfig registry = new RegistryConfig();
		registry.setAddress( "zookeeper://115.159.202.204:2181");
		application.setRegistry(registry);

		ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
		// 弱类型接口名
		String interfaceName="com.springDubbo.dubboService.generic.GenelService";
		reference.setInterface(interfaceName);
		// 声明为泛化接口
		reference.setGeneric(true);

		reference.setApplication(application);

		// 用com.alibaba.dubbo.rpc.service.GenericService可以替代所有接口引用
		GenericService genericService = reference.get();
		/**
		 * GenericService 这个接口只有一个方法，名为 $invoke，它接受三个参数，分别为方法名、方法参数类型数组和参数值数组；
		 * 对于方法参数类型数组 如果是基本类型，如 int 或 long，可以使用 int.class.getName()获取其类型； 如果是基本类型数组，如
		 * int[]，则可以使用 int[].class.getName()； 如果是 POJO，则直接使用全类名，如
		 * com.alibaba.dubbo.samples.generic.api.Params。
		 */
		Object testInvoke = genericService.$invoke("testInvoke", new String[0], new Object[0]);

		System.out.println(testInvoke);
	}
}
