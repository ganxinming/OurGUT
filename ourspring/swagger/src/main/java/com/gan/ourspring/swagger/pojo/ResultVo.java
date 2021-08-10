package com.gan.ourspring.swagger.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ganxinming
 * @createDate 2020/11/26
 * @description @ApiModel一般用在实体类上
 */

@ApiModel(value = "VO对象", description = "我们")
@Data
public class ResultVo {

	@ApiModelProperty(value = "响应消息")
	private String msg;
	@ApiModelProperty(value = "响应状态码")
	private int code;
	@ApiModelProperty(value = "响应数据")
	private Object data;
}
