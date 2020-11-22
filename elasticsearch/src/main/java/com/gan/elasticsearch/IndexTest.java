package com.gan.elasticsearch;

import java.io.IOException;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

/**
 * @author ganxinming
 * @createDate 2020/11/19
 * @description 所有操作都可同步或异步，一般异步都需要实现监听器
 */
public class IndexTest {
	public static void main(String[] args) throws IOException {
		RestHighLevelClient client = ClientTest.client;
		CreateIndexRequest request = new CreateIndexRequest("GAN");
		CreateIndexResponse createIndexResponse = client.indices().create(request,RequestOptions.DEFAULT);
		System.out.println(createIndexResponse);
	}

	/**
	 * 创建索引
	 * @throws IOException
	 */
	@Test
	public void creatIndex() throws IOException {
		RestHighLevelClient client = ClientTest.client;
		//索引，后面的设置都可省略
		CreateIndexRequest request = new CreateIndexRequest("xin");
		//设置超时
		request.setTimeout(TimeValue.timeValueMinutes(2));
		//对索引设置，3个分片，2个副本
		request.settings(Settings.builder()
				.put("index.number_of_shards", 1)
				.put("index.number_of_replicas", 1)
		);
		//设置结构,类型
		request.mapping(
				"{\n" +
						"  \"properties\": {\n" +
						"    \"message\": {\n" +
						"      \"type\": \"text\"\n" +
						"    }\n" +
						"  }\n" +
						"}",
				XContentType.JSON);
		//同步创建，异步的也可，看文档
		CreateIndexResponse createIndexResponse = client.indices().create(request,RequestOptions.DEFAULT);
		System.out.println(createIndexResponse);
	}

	/**
	 * 删除索引
	 * @throws IOException
	 */
	@Test
	public void deleteIndex() throws IOException {
		RestHighLevelClient client = ClientTest.client;
		DeleteIndexRequest request = new DeleteIndexRequest("123");

		//等待所有节点知道删除了索引
		request.timeout(TimeValue.timeValueMinutes(2));
		//主节点超时时间
		request.masterNodeTimeout(TimeValue.timeValueMinutes(1));

		//同步
		AcknowledgedResponse deleteIndexResponse = client.indices().delete(request, RequestOptions.DEFAULT);
		//client.indices().deleteAsync(request, RequestOptions.DEFAULT, listener);
		boolean acknowledged = deleteIndexResponse.isAcknowledged();
	}


	/**
	 * 查看索引是否存在
	 * @throws IOException
	 */
	@Test
	public void findIndex() throws IOException {
		RestHighLevelClient client = ClientTest.client;

		GetIndexRequest request = new GetIndexRequest("twitter");

		//是否返回一下额外信息
		request.local(false);
		//是否便于阅读
		request.humanReadable(true);
		//是否返回所有默认配置
		request.includeDefaults(false);
		request.indicesOptions(IndicesOptions.LENIENT_EXPAND_OPEN);
		boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
		System.out.println(exists);
	}
}
