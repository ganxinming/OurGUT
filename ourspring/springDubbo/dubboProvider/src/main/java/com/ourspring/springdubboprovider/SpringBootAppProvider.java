package com.ourspring.springdubboprovider;

import javax.annotation.PostConstruct;

import com.ourspring.springdubboprovider.service.impl.GenelServiceImpl;
import com.springDubbo.dubboService.generic.GenelService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author ganxinming
 * @createDate 2021/3/17
 * @description
 */

@SpringBootApplication
/**
 * 在META-INF/spring下建文件，无需注解导入
 */
@ImportResource(locations= {"classpath:provider.xml"})
public class SpringBootAppProvider {

	@Autowired
	GenelService genelService;

	//使用API的形式对外发布Service

	/**
	 * 所有xml能做的，api都能做
	 */
	@PostConstruct
	public void init(){
		// 当前应用配置
//		ApplicationConfig application = new ApplicationConfig();
//		application.setName("provider");
//		application.setOwner("ganxinming");
//
//		// 连接注册中心配置
//		RegistryConfig registry =registry();
//
//		// 服务提供者协议配置
//		ProtocolConfig protocol = new ProtocolConfig();
//		protocol.setName("dubbo");
//		protocol.setPort(20980);
//
//		ServiceConfig<GenelService> serviceConfig = new ServiceConfig<GenelService>();
//		serviceConfig.setApplication(application);
//		serviceConfig.setRegistry(registry);
//		serviceConfig.setProtocol(protocol);
//		serviceConfig.setInterface(GenelService.class);
//		serviceConfig.setRef(genelService);
//		serviceConfig.export();

	}

	public static RegistryConfig registry() {
		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setAddress("115.159.202.204:2181");
		registryConfig.setProtocol("zookeeper");
		return registryConfig;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAppProvider.class,args);
	}

}
