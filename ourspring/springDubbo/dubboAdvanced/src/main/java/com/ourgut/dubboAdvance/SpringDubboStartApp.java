package com.ourgut.dubboAdvance;

import java.net.InetAddress;
import java.net.UnknownHostException;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;

/**
 * @author ganxinming
 * @createDate 2021/6/30
 * @description
 */
@SpringBootApplication
@ImportResource( {"classpath:springProviderDubbo.xml"})
@Slf4j
public class SpringDubboStartApp {

	public static void main(String[] args) throws UnknownHostException {
		ConfigurableApplicationContext application = SpringApplication.run(SpringDubboStartApp.class, args);
		log.info("..######..##.....##..######...######..########..######...######.\n" +
				".##....##.##.....##.##....##.##....##.##.......##....##.##....##\n" +
				".##.......##.....##.##.......##.......##.......##.......##......\n" +
				"..######..##.....##.##.......##.......######....######...######.\n" +
				".......##.##.....##.##.......##.......##.............##.......##\n" +
				".##....##.##.....##.##....##.##....##.##.......##....##.##....##\n" +
				"..######...#######...######...######..########..######...######.");
		Environment env = application.getEnvironment();
		String ip = InetAddress.getLocalHost().getHostAddress();
		String port = env.getProperty("server.port");
		String path = env.getProperty("server.servlet.context-path");
		if (StringUtils.isEmpty(path)) {
			path = "";
		}
		log.info("\n----------------------------------------------------------\n\t" +
				"Application  is running! Access URLs:\n\t" +
				"Local访问网址: \t\thttp://localhost:" + port + path + "\n\t" +
				"External访问网址: \thttp://" + ip + ":" + port + path + "\n\t" +
				"----------------------------------------------------------");
	}
}
