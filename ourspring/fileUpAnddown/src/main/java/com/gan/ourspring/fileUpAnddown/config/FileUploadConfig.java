package com.gan.ourspring.fileUpAnddown.config;

import java.io.File;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ganxinming
 * @createDate 2021/4/13
 * @description
 */
@Configuration
public class FileUploadConfig {

	private String filePath="/tmp/upload";
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		File file =new File(filePath);
		if(!file .exists()  && !file .isDirectory()){
			file.mkdir();
		}
		factory.setLocation(filePath);
		return factory.createMultipartConfig();
	}
}
