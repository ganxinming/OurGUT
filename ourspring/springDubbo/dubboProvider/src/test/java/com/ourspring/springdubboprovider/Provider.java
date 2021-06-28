package com.ourspring.springdubboprovider;

import java.io.IOException;

import com.ourspring.springdubboprovider.service.impl.GenelServiceImpl;
import com.springDubbo.dubboService.generic.GenelService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

/**
 * @author ganxinming
 * @createDate 2021/3/18
 * @description
 */
public class Provider {

	/**
	 * 泛化调用
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		ServiceConfig<GenelService> serviceConfig = new ServiceConfig<GenelService>();
		serviceConfig.setApplication(new ApplicationConfig("first-dubbo-provider"));
		serviceConfig.setRegistry(registry());
		serviceConfig.setInterface(GenelService.class);
		serviceConfig.setRef(new GenelServiceImpl());
		serviceConfig.export();
		System.in.read();
	}

	public static RegistryConfig registry() {
		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setAddress("115.159.202.204:2181");
		registryConfig.setProtocol("zookeeper");
		return registryConfig;
	}
}
