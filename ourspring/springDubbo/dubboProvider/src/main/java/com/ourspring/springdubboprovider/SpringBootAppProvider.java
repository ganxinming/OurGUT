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
@ImportResource(locations= {"classpath:provider.xml"})
public class SpringBootAppProvider {

	@Autowired
	GenelService genelService;

	//使用API的形式对外发布Service
	@PostConstruct
	public void init(){
		ServiceConfig<GenelService> serviceConfig = new ServiceConfig<GenelService>();
		serviceConfig.setApplication(new ApplicationConfig("first-dubbo-provider"));
		serviceConfig.setRegistry(registry());
		serviceConfig.setInterface(GenelService.class);
		serviceConfig.setRef(genelService);
		serviceConfig.export();
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
