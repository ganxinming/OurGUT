package com.springDubbo.dubboService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ganxinming
 * @createDate 2021/7/1
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transport {
	private String traceId;
	private String message;
}
