package com.gan.ourspring.swagger.controller;

import com.gan.ourspring.swagger.annotation.NoIncludeSwagger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2020/11/26
 * @description
 * @Api作用在类上
 * 1.tags 类名,可以多值 ，就是UI界面上最大的标题
 * 2.value 接口描述
 * 3.description 已废弃
 */
@RestController
@Api(tags = {"helloController","hello"},value = "hello的入口",description = "DemoController描述")
public class HelloController {

	/**@ApiOperation 修饰方法
	 * value:接口描述 notes:提示
	 * @return
	 */
	@ApiOperation(value = "hello的路径" , notes="注意get请求")
	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	public String hello(){
		return "hello";
	}


	/**
	 * @ApiParam 修饰参数
	 * name：参数名称，value：参数描述，required：是否是必须
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "接口描述", notes = "提示信息")
	@RequestMapping(value = "/swaggerMethodTest",method = RequestMethod.GET)
	public String swaggerMethodTest(Long id, @ApiParam(name="userName", value = "姓名 这个是参数描述", required = true) String userName){
		return "hello";
	}

	/**
	 *
	 * @param id
	 * @param userName
	 * @return
	 */
//	@ApiOperation(value = "接口描述1", notes = "提示信息1")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "id",value = "用户id",required = true),
//			@ApiImplicitParam(dataType = "string",name = "userName",value = "用户名称",required = true)
//	})
//	@RequestMapping(value = "/swaggerMethodTest",method = RequestMethod.GET)
//	public String swaggerMethodTest1(Long id, String userName){
//		return "hello";
//	}

	/**
	 * @ApiIgnore 表示这个方法或类被忽略，其实和自定义注解一样@NoIncludeSwagger
	 * @return
	 */
	@ApiIgnore
	@RequestMapping("/ignoreMethod")
	public String ignoreMethod(){
		return "ignore";
	}

	/**
	 * 自定义忽略注解
	 * @return
	 */
	@NoIncludeSwagger
	@RequestMapping("/myIgnoreMethod")
	public String myIgnoreMethod(){
		return "ignore";
	}

}
