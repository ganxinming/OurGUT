package com.ourspring.excel.entity;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

/**
 * @author ganxinming
 * @createDate 2021/3/24
 * @description
 */

@Data
@ContentRowHeight(10)
@HeadRowHeight(20)
@ColumnWidth(25)
public class DemoData {

	@ExcelProperty("id")
	private Long id;

	@ExcelProperty("用户ID")
	private String userId;

	@ExcelProperty("手机号")
	private String phone;

	@ExcelProperty("场景")
	private String sence;

	@ExcelProperty("时间")
	private Date hitTime;

}
