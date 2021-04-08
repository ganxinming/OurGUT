package com.ourspring.excel.entity;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

/**
 * @author ganxinming
 * @createDate 2021/4/6
 * @description
 */
@Data
public class DownloadModule {

	@ExcelProperty("用户ID")
	private Long id;

}
