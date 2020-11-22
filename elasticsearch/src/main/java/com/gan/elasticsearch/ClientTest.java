package com.gan.elasticsearch;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author ganxinming
 * @createDate 2020/11/18
 * @description
 */
public class ClientTest {

	static RestHighLevelClient client = new RestHighLevelClient(
			RestClient.builder(
					new HttpHost("115.159.202.204", 9200, "http"),
					new HttpHost("115.159.202.204", 9201, "http")));

}
