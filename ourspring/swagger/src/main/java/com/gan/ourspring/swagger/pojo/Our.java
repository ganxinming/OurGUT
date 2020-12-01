package com.gan.ourspring.swagger.pojo;


import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author ganxinming
 * @createDate 2020/11/26
 * @description @ApiModel一般用在实体类上
 */

@ApiModel(value = "名称：大家类", description = "我们")
@Data
public class Our {
	private String name;
	private Integer id;
}
