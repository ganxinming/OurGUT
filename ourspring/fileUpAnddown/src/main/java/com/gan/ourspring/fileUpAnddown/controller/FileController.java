package com.gan.ourspring.fileUpAnddown.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ganxinming
 * @createDate 2021/2/19
 * @description
 */
@RestController
public class FileController {

	@RequestMapping("/downFile")
	public String downFile(HttpServletResponse response) {
		try {
			Map<String, List<String>> map = new HashMap<>();

			map.put("name", Lists.newArrayList("123", "456","汉字","论文"));
			map.put("age", Lists.newArrayList("123", "456","English","Any"));
			String jsonString = JSONObject.toJSONString(map);

			response.setContentType("application/force-download");
			// 设置下载后的文件名以及header
			response.setHeader("Content-Disposition", "attachment;filename="
					.concat(String.valueOf(URLEncoder.encode("第一个json.txt", "UTF-8"))));
			response.setCharacterEncoding("utf-8");
			// 创建输出对象
			OutputStream os = response.getOutputStream();

			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os));
			bufferedWriter.write(jsonString);
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "123";
	}

	@RequestMapping("/upload")
	public String upload(HttpServletRequest request,
			@RequestParam("file") MultipartFile file) throws IOException {

		// 测试MultipartFile接口的各个方法
		System.out.println("文件类型ContentType=" + file.getContentType());
		System.out.println("文件组件名称Name=" + file.getName());
		System.out.println("文件原名称OriginalFileName=" + file.getOriginalFilename());
		System.out.println("文件大小Size=" + file.getSize() / 1024 + "KB");
		// 如果文件不为空，写入上传路径，进行文件上传
		if (!file.isEmpty()) {
			InputStream inputStream = file.getInputStream();
			StringBuffer json = new StringBuffer();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String s = bufferedReader.readLine();
			Object parse = JSONObject.parse(s);
			System.out.println(parse.toString());
		} else {
		}
		return "123";
	}

}
