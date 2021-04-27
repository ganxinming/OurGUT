package com.ourspring.excel.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.exception.ExcelAnalysisException;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.ourspring.excel.entity.DemoData;
import com.ourspring.excel.entity.DownloadModule;
import com.ourspring.excel.listener.DemoDataListener;
import org.apache.xmlbeans.XmlException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
		System.out.println(123);
//		// 写法2
//		fileName = "/Users/ganxinming/Desktop/risk1.xlsx";
//		// 这里 需要指定写用哪个class去写
//		ExcelWriter excelWriter = null;
//		try {
//			excelWriter = EasyExcel.write(fileName, DemoData.class).build();
//			WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
//			excelWriter.write(data(), writeSheet);
//		} finally {
//			// 千万别忘记finish 会帮忙关闭流
//			if (excelWriter != null) {
//				excelWriter.finish();
//			}
//		}
		return "write";
	}



	@RequestMapping("/webRead")
	public String webRead(MultipartFile file,HttpServletResponse response) throws Exception {

		List<Object> objects = null;
		try {
			objects = EasyExcel.read(file.getInputStream()).head(DownloadModule.class).sheet().doReadSync();
		} catch (ExcelAnalysisException e) {
			System.out.println("上传正确文件");
		}
		System.out.println(objects);
		return "";
	}

	@RequestMapping("/webWrite")
	public String webWrite(HttpServletResponse response) throws Exception {
		// 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding("utf-8");
		// 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
		String fileName = URLEncoder.encode("测试", "UTF-8");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
		EasyExcel.write(response.getOutputStream(), DemoData.class).sheet("模板").doWrite(data());
		return "";
	}


	@RequestMapping("/webWriteModule")
	public String webWriteModule(HttpServletResponse response) throws Exception {
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding("utf-8");
		// 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
		String fileName = URLEncoder.encode("测试", "UTF-8");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
		EasyExcel.write(response.getOutputStream(), DownloadModule.class).useDefaultStyle(false).sheet("模板").doWrite(null);
		return "";
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

	@RequestMapping("/thread")
	public void test213(){
		Thread thread = new Thread() {
			@Override
			public void run() {
				while(true){
					try {
						sleep(500);
						System.out.println(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		};
		thread.start();
	}
}
