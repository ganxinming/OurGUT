package com.gan.elasticsearch;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONObject;
import com.gan.elasticsearch.pojo.User;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;

/**
 * @author ganxinming
 * @createDate 2020/11/20
 * @description
 */
public class DocumentTest {

	/**
	 * 插入一条数据，插入的写法有多种
	 * @throws IOException
	 */
	@Test
	public void insert() throws IOException {
		RestHighLevelClient client = ClientTest.client;
		//指定索引，如果不存在则创建
		IndexRequest request = new IndexRequest("xin");
		//如果不指定，默认分配随机id
		request.id("2");
		//存入的数据结构
		User user=new User("123",24,"ganxinm");
		//source
		request.source(JSONObject.toJSONString(user), XContentType.JSON);
		/**
		 *  map写法构造数据结构
		 */
//		Map<String, Object> jsonMap = new HashMap<>();
//		jsonMap.put("user", "kimchy");
//		jsonMap.put("postDate", new Date());
//		jsonMap.put("message", "trying out Elasticsearch");
//		IndexRequest indexRequest = new IndexRequest("posts")
//				.id("1").source(jsonMap);

		/**
		 *  build写法构造
		 */
//		XContentBuilder builder = XContentFactory.jsonBuilder();
//		builder.startObject();
//		{
//			builder.field("user", "kimchy");
//			builder.timeField("postDate", new Date());
//			builder.field("message", "trying out Elasticsearch");
//		}
//		builder.endObject();
//		IndexRequest indexRequest = new IndexRequest("posts")
//				.id("1").source(builder);

		//等待主分片时间
		request.timeout(TimeValue.timeValueSeconds(1));
		//刷新策略
		request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
		//设置操作类型，默认create
		request.opType(DocWriteRequest.OpType.CREATE);

		IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
		System.out.println(indexResponse);
	}

	@Test
	public void delete() throws IOException {
		RestHighLevelClient client = ClientTest.client;

		DeleteRequest request = new DeleteRequest(
				"twitter",
				"2");
//		request.routing("routing");
//		request.version(2);
		request.timeout(TimeValue.timeValueMinutes(2));
		request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);

		DeleteResponse deleteResponse = client.delete(
				request, RequestOptions.DEFAULT);
		System.out.println(deleteResponse);
	}

	/**
	 * 每次对某个文档操作完都会_version+1
	 * @throws IOException
	 */
	@Test
	public void update() throws IOException {
		RestHighLevelClient client = ClientTest.client;

		UpdateRequest request = new UpdateRequest(
				"twitter",
				"1");
		String jsonString = "{" +
				"\"updated\":\"2018-01-01\"," +
				"\"reason\":\"daily update\"" +
				"}";
		request.doc(jsonString, XContentType.JSON);
		request.timeout(TimeValue.timeValueSeconds(1));
		request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
		request.retryOnConflict(3);
		UpdateResponse updateResponse = client.update(
				request, RequestOptions.DEFAULT);
		System.out.println(updateResponse);
	}

	/**
	 * 根据id查询
	 * @throws IOException
	 */
	@Test
	public void find() throws IOException {
		RestHighLevelClient client = ClientTest.client;

		GetRequest request = new GetRequest(
				"posts",
				"1");

		//设置获取是否获取_source上下文，和需要获取的字段等
		String[] includes = Strings.EMPTY_ARRAY;
		//需要排除的字段
		String[] excludes = new String[]{"message"};
		FetchSourceContext fetchSourceContext =
				new FetchSourceContext(true, includes, excludes);
		request.fetchSourceContext(fetchSourceContext);
//		是否存在
//		boolean exists = client.exists(request, RequestOptions.DEFAULT);
		GetResponse getResponse = client.get(request, RequestOptions.DEFAULT);
		System.out.println(getResponse.getSourceAsString());
//		String message = getResponse.getField("user").getValue();
	}

	/**
	 * 高级查询
	 */
	@Test
	public void search() throws IOException {
		RestHighLevelClient client = ClientTest.client;

		SearchRequest searchRequest = new SearchRequest("twitter");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//		searchSourceBuilder.query(QueryBuilders.termQuery("user", "kimchy"));
		/**
		 * QueryBuilders 提供了好多查询
		 */
		//match，分词器查询
		QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("user", "kimchy")
				.fuzziness(Fuzziness.AUTO)
				//分词的的长度
				.prefixLength(3)
				.maxExpansions(10);

		searchSourceBuilder.query(matchQueryBuilder);
		//分页查询
		searchSourceBuilder.from(0);
		searchSourceBuilder.size(5);

		//按分数排
		searchSourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
		//根据确定的域排序
		searchSourceBuilder.sort(new FieldSortBuilder("id").order(SortOrder.ASC));

		//高亮
		HighlightBuilder highlightBuilder = new HighlightBuilder();
		HighlightBuilder.Field highlightTitle =
				new HighlightBuilder.Field("title");
		highlightTitle.highlighterType("unified");
		highlightBuilder.field(highlightTitle);
		searchSourceBuilder.highlighter(highlightBuilder);

		//聚合查询
		TermsAggregationBuilder aggregation = AggregationBuilders.terms("by_company")
				.field("company.keyword");
		aggregation.subAggregation(AggregationBuilders.avg("average_age")
				.field("age"));
		searchSourceBuilder.aggregation(aggregation);

		searchRequest.source(searchSourceBuilder);

		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

		RestStatus status = searchResponse.status();
		TimeValue took = searchResponse.getTook();
		Boolean terminatedEarly = searchResponse.isTerminatedEarly();
		boolean timedOut = searchResponse.isTimedOut();
		int totalShards = searchResponse.getTotalShards();

		int successfulShards = searchResponse.getSuccessfulShards();
		int failedShards = searchResponse.getFailedShards();
		for (ShardSearchFailure failure : searchResponse.getShardFailures()) {
			// failures should be handled here
		}


	}

}
