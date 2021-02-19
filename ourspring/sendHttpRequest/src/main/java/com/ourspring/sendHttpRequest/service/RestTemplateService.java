package com.ourspring.sendHttpRequest.service;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;
import com.ourspring.sendHttpRequest.entity.ResponseMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author ganxinming
 * @createDate 2021/1/18
 * @description
 */

/**
 * 对应操作
 * Delete   delete
 * Get		getForObject , getForEntity
 * Post		postForObject , postForEntity
 * HEAD		headForHeaders
 * any		exchange,execute
 */

/**
 * 在内部，RestTemplate默认使用HttpMessageConverter实例将HTTP消息转换成POJO或者从POJO转换成HTTP消息
 */
@Service
public class RestTemplateService {

	@Autowired
	RestTemplate restTemplate;


	/**
	 * 不带参数get请求
	 * @param url
	 */
	public void getRequest(String url){
		/**
		 * getForEntity 可获得封装载体之外的一些http的信息
		 */
		ResponseEntity<ResponseMessage> forEntity = restTemplate.getForEntity(url, ResponseMessage.class);
		ResponseMessage body = forEntity.getBody();
		System.out.println(body);
		/**
		 * getForObject 只可获得封装载体
		 */
		ResponseMessage forObject = restTemplate.getForObject(url, ResponseMessage.class);
		System.out.println(forObject);
	}

	/**
	 * 带参数get请求
	 * @param url
	 */
	public void getRequestWithOptions(String url){
		/**
		 * 请求拼接替换参数
		 */
		ResponseEntity<ResponseMessage> forEntity = restTemplate.getForEntity(url+"/{1}/{2}",
				ResponseMessage.class,"参数1","参数2");
		ResponseMessage body = forEntity.getBody();
		System.out.println(body);

		/**
		 * 封装成keyv-value参数map
		 */
		HashMap<Object, Object> map = Maps.newHashMap();
		map.put("name","参数1");
		map.put("age","参2");
		ResponseEntity<ResponseMessage> forEntity2 = restTemplate.getForEntity(url,
				ResponseMessage.class,map);
		HttpStatus statusCode = forEntity2.getStatusCode();
		if (statusCode.is2xxSuccessful()){
			System.out.println("成功，状态码"+statusCode.value());
		}

	}

	/**
	 * post请求
	 * @param url
	 */
	public void postRequest(String url){
		/**
		 * 自行设置请求头
		 */
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		/**
		 * 设置请求值，是个一键多值
		 */
		MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
		map.add("email", "844072586@qq.com");

		/**
		 * 封装成request
		 */
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
		System.out.println(response.getBody());
	}

	/**
	 * exchange请求
	 * excute用法和exchange差不多，只不过返回值是指定的类型不包括http参数
	 * @param url
	 */
	public void exchange请求Request(String url) throws JSONException {
		/**
		 * 自行设置请求头
		 */
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


		JSONObject jsonObj = new JSONObject();
		jsonObj.put("start",1);
		jsonObj.put("page",5);

		HttpEntity<String> entity = new HttpEntity<>(jsonObj.toString(), headers);
		ResponseEntity<JSONObject> exchange = restTemplate.exchange(url,
				HttpMethod.GET, entity, JSONObject.class);

		System.out.println(exchange.getBody());

	}
}
