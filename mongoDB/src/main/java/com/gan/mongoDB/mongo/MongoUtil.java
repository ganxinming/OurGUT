package com.gan.mongoDB.mongo;

import static java.util.regex.Pattern.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.junit.Test;

/**
 * @author ganxinming
 * @createDate 2020/10/11
 * @description 新版本springboot不支持以下整和，所以将@Value注释
 */
public class MongoUtil {

//	@Value("${mongo.username}")
	private String user;

//	@Value("${mongo.password}")
	private String pass;

//	@Value("${mongo.database}")
	private String database;

//	@Value("${mongo.host}")
	private String serverAddr;

//	@Value("${mongo.port}")
	private int port;

	/**
	 * 建立连接，获取database
	 * @return
	 */
	public MongoDatabase getMongoDatabase(){
		MongoClient client = new MongoClient("localhost",27017);
		MongoDatabase mongoDatabase = client.getDatabase("myMonGoDB");
		return  mongoDatabase;
	}

	/**
	 * 建立个带有身份认证的MongoDatabase
	 * @return
	 */
	public MongoDatabase getCompleteMongoDatabase(){
		ServerAddress serverAddress = new ServerAddress(serverAddr, port);
		List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
		MongoCredential mc = MongoCredential.createScramSha1Credential(user,database,pass.toCharArray());
		credentialsList.add(mc);
		MongoClientOptions options = MongoClientOptions.builder()
				//设置连接超时时间为10s
				.connectTimeout(1000*10)
				//设置最长等待时间为10s
				.maxWaitTime(1000*10)
				.threadsAllowedToBlockForConnectionMultiplier(10)
				//避免主从延迟
				.readPreference(ReadPreference.primary())
				.build();
		//对于多个ip地址的mongo，可以封装成list传入
		MongoClient client = new MongoClient(serverAddress,credentialsList,options);
		MongoDatabase sang = client.getDatabase("sang");
		return  sang;
	}

	/**
	 * 通过database，获取相应的collection
	 * @param name
	 * @return
	 */
	public MongoCollection getCollection(String name){
		MongoDatabase mongoDatabase=getMongoDatabase();
		MongoCollection<Document> collection = mongoDatabase.getCollection(name);
		return collection;
	}

	/**
	 * 创建collection
	 * @param name
	 */
	public void creatCollection(String name){
		MongoDatabase mongoDatabase=getMongoDatabase();
		mongoDatabase.createCollection(name);
	}

	/**
	 * 插入新数据（如果collection，将会自动创建个新的collection）
	 */
	public void insert(){
		MongoCollection collection = getCollection("first");
		Document d1 = new Document();
		//document就像是两个{},中间通过append增加内容（平级元素），并且document中还可以加document(子集元素)
		d1.append("name", "三国演义").append("author", "罗贯中");
		d1.append("name", new Document("book","book"));
		collection.insertOne(d1);
	}


	/**
	 * 根据属性==值进行删除
	 */
	public void delete(){
		MongoCollection collection = getCollection("first");
		//可以删除查到的一条数据
		collection.deleteOne(Filters.eq("author", "罗贯中"));
		//删除所有值相等的数据
//		collection.deleteOne(Filters.eq("author", "罗贯中"));
	}

	/**
	 * 改
	 */
	public void update(){
		MongoCollection collection = getCollection("first");

		collection.updateOne(Filters.eq("author", "罗贯中"),
				new Document("$set", new Document("name", "三国演义123")));

	}

	/**
	 * 查询所有
	 */
	@Test
	public void findAll(){
		MongoCollection collection = getCollection("first");
		FindIterable findIterable = collection.find();
		MongoCursor iterator = findIterable.iterator();
		while (iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}

	/**
	 * 简单查询
	 */
	public void findBy(){
		MongoCollection collection = getCollection("first");
		//多条件查询
		FindIterable findIterable = collection.find(Filters.and(Filters.eq("name", "三国演义123"),
				Filters.gt("age", 18), Filters.gt("money", 20000)));
		//单条件查询
//		collection.find(Filters.eq("_id",12312312));
		MongoCursor iterator = findIterable.iterator();
		while (iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}

	/**
	 * 聚合查询
	 */
	public  void aggregateQuery(){
		MongoCollection collection = getCollection("first");
		BasicDBObject query = new BasicDBObject();
		//按时间区域查询
		query.put("createTime",new BasicDBObject("$gte", "2020-10-02 12:20:00").append("$lte","2018-07-04 10:02:46"));
		//模糊查询
		Pattern pattern = compile("^.*三.*$", CASE_INSENSITIVE);
		query.put("userName", pattern);
		//精确查询
		query.put("_id", "123");
		//分页查询
		MongoCursor mongoCursor = collection.find(query)
				//可按多属性排序
				.sort(Sorts.orderBy(Sorts.descending("username", "age")))
				//分页查询，从第0条开始，每页10条
				.skip(0).limit(10).iterator();
		while (mongoCursor.hasNext()){
			System.out.println(mongoCursor.next());
		}
		/**************************************************************************************/
		//第二种纯聚合方式
		BasicDBObject dbObject = new BasicDBObject("name", "三国");
		dbObject.append("age",new BasicDBObject("$gt",10));

		AggregateIterable<Document> aggregate = collection.aggregate(
				Arrays.asList(
						//匹配查询的条件
						Aggregates.match(dbObject),
						//分组
						Aggregates.group("name"),
						//排序
						Aggregates.sort(Sorts.orderBy(Sorts.descending("username", "age"))),
						//分页，从0开始
						Aggregates.skip(0),
						//限制每页100
						Aggregates.limit(100)
				)
		);
		MongoCursor iterator = aggregate.iterator();
		while (iterator.hasNext()){
			System.out.println(iterator.next());
		}

	}


	@Test
	public void testSetCollection(){
		MongoDatabase mongoDatabase = getMongoDatabase();
		MongoIterable<String> strings = mongoDatabase.listCollectionNames();
		MongoCursor<String> iterator = strings.iterator();
		while (iterator.hasNext()){
			String next = iterator.next();
			if("f".equals(next)){
				System.out.println(next);
			}
		}
	}

	@Test
	public void createCollection(){
		MongoDatabase mongoDatabase = getMongoDatabase();
		mongoDatabase.createCollection("third");
	}

	/**
	 * 插入json字符串对象,需要进行一次转换Document.parse
	 */
	@Test
	public void insertObject(){
		User user=new User() ;
		user.setName("gan");
		user.setUser("xin");
		MongoCollection collection = getCollection("first");
		String jsonString=JSONObject.toJSONString(user);
		Document document = Document.parse(jsonString);
		collection.insertOne(document);
	}

	class User{
		private String user;

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		private String name;
	}


}
