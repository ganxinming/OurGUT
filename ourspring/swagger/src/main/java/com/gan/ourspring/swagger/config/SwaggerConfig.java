package com.gan.ourspring.swagger.config;

import static com.google.common.base.Predicates.*;
import static springfox.documentation.builders.PathSelectors.*;
import static springfox.documentation.builders.RequestHandlerSelectors.*;

import com.gan.ourspring.swagger.annotation.NoIncludeSwagger;
import com.google.common.base.Predicate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ganxinming
 * @createDate 2020/11/26
 * @description 开启swaggerUi
 * 访问http://localhost:8080/swagger-ui.html/
 */
@Configuration
@EnableSwagger2//开启Swagger2
public class SwaggerConfig {

	/**
	 * 创建API应用
	 * apiInfo() 增加API相关信息
	 * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
	 * 本例采用指定扫描的包路径来定义指定要建立API的目录。
	 * Docket：摘要对象，通过对象配置描述文件的信息
	 * apiInfo:设置描述文件中 info
	 * @return
	 */
	@Bean
	public Docket swaggerSpringMvcPlugin() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				//扫描需要的包
				.apis(RequestHandlerSelectors.basePackage("com.gan.ourspring.swagger.controller"))
				//对于不想生成接口文档的方法，使用注解排除
				.apis(not(withMethodAnnotation(NoIncludeSwagger.class)))
				//哪些匹配的路径生成文档，可以使用正则匹配，默认全部生成
				.paths(PathSelectors.any())
//				.paths(allowPath())
				.build();
	}

	//配置API的基本信息（会在http://项目实际地址/swagger-ui.html页面显示）

	private ApiInfo apiInfo() {

		return new ApiInfoBuilder()
				.title("Spring Boot中使用Swagger2构建RESTful APIs")
				.description("测试api接口文档描述")
				.termsOfServiceUrl("http://www.baidu.com")
				.version("1.0")
				.build();
	}
	private Predicate<String> allowPath(){
		return or(regex("/demo/.*"));
	}
}
