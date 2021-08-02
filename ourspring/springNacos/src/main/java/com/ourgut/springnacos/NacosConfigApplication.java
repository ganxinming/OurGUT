package com.ourgut.springnacos;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ganxinming
 * @createDate 2021/7/5
 * @description
 */
@SpringBootApplication
//使用 @NacosPropertySource 加载 dataId 为 example 的配置源，并开启自动更新：
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class NacosConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosConfigApplication.class, args);
	}
}
