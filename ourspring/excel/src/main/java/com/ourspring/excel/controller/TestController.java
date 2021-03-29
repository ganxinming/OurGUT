package com.ourspring.excel.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.ourspring.excel.entity.DemoData;
import com.ourspring.excel.listener.DemoDataListener;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/3/24
 * @description
 */

@RestController
public class TestController {

	@RequestMapping("/read")
	public String read(){
		// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
		// 写法1：
		String fileName = "/Users/ganxinming/Desktop/risk.xlsx";
		// 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
		EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();

		// 写法2：
		fileName = "/Users/ganxinming/Desktop/risk.xlsx";
		ExcelReader excelReader = null;
		try {
			excelReader = EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).build();
			ReadSheet readSheet = EasyExcel.readSheet(0).build();
			excelReader.read(readSheet);
		} finally {
			if (excelReader != null) {
				// 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
				excelReader.finish();
			}
		}
		return "read";
	}

	@RequestMapping("/write")
	public String write(){
		// 写法1
		String fileName = "/Users/ganxinming/Desktop/risk1.xlsx";
		// 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
		// 如果这里想使用03 则 传入excelType参数即可
		EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());

		// 写法2
		fileName = "/Users/ganxinming/Desktop/risk1.xlsx";
		// 这里 需要指定写用哪个class去写
		ExcelWriter excelWriter = null;
		try {
			excelWriter = EasyExcel.write(fileName, DemoData.class).build();
			WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
			excelWriter.write(data(), writeSheet);
		} finally {
			// 千万别忘记finish 会帮忙关闭流
			if (excelWriter != null) {
				excelWriter.finish();
			}
		}
		return "write";
	}


	private List<DemoData> data() {
		List<DemoData> list = new ArrayList<DemoData>();
		for (int i = 0; i < 10; i++) {
			DemoData data = new DemoData();
			data.setId((long)i);
			data.setHitTime(new Date());
			data.setUserId("userID"+i);
			data.setSence("疯狂");
			data.setPhone("123213");
			list.add(data);
		}
		return list;
	}


}
