//package com.ourspring.data;
//
//import com.ourspring.data.jpa.dao.messqge.MessageRepository;
//import com.ourspring.data.jpa.dao.user.UserRepository;
//import com.ourspring.data.jpa.entity.second.Message;
//import com.ourspring.data.jpa.entity.primary.User;
//import com.ourspring.data.mybatisPlus.mapper.UserMapper;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
///**
// * @author ganxinming
// * @createDate 2021/6/11
// * @description
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest()
//public class ApplicationTests {
//
//	@Autowired
//	@Qualifier("primaryJdbcTemplate")
//	protected JdbcTemplate jdbcTemplate1;
//
//	@Autowired
//	@Qualifier("secondJdbcTemplate")
//	protected JdbcTemplate jdbcTemplate2;
//
//
//	@Autowired
//	private UserRepository userRepository;
//	@Autowired
//	private MessageRepository messageRepository;
//
//
////	@Autowired
////	private UserService userService;
//
//
//	@Autowired
//	@Qualifier("userMapper")
//	private UserMapper userMapper;
//
//	@Before
//	public void setUp() {
//		jdbcTemplate1.update("DELETE  FROM  user ");
//		jdbcTemplate2.update("DELETE  FROM  user1 ");
//	}
//
//	@Test
//	public void test() throws Exception {
//
//		// 往第一个数据源中插入两条数据
//		jdbcTemplate1.update("insert into user(id,name,age) values(?, ?, ?)", 1, "aaa", 20);
//		jdbcTemplate1.update("insert into user(id,name,age) values(?, ?, ?)", 2, "bbb", 30);
//
//		// 往第二个数据源中插入一条数据，若插入的是第一个数据源，则会主键冲突报错
//		jdbcTemplate2.update("insert into user1(id,name,age) values(?, ?, ?)", 1, "aaa", 20);
//
//		// 查一下第一个数据源中是否有两条数据，验证插入是否成功
//		Assert.assertEquals("2", jdbcTemplate1.queryForObject("select count(1) from user", String.class));
//
//		// 查一下第一个数据源中是否有两条数据，验证插入是否成功
//		Assert.assertEquals("1", jdbcTemplate2.queryForObject("select count(1) from user1", String.class));
//
//	}
//
//	@Test
//	public void test1() throws Exception {
//
//		userRepository.save(new User("aaa", 10));
//		userRepository.save(new User("bbb", 20));
//		userRepository.save(new User("ccc", 30));
//		userRepository.save(new User("ddd", 40));
//		userRepository.save(new User("eee", 50));
//
//		Assert.assertEquals(5, userRepository.findAll().size());
//
//		messageRepository.save(new Message("o1", "aaaaaaaaaa"));
//		messageRepository.save(new Message("o2", "bbbbbbbbbb"));
//		messageRepository.save(new Message("o3", "cccccccccc"));
//
//		Assert.assertEquals(3, messageRepository.findAll().size());
//
//	}
//
//	@Test
//	public void testMybatisPlus(){
//		com.ourspring.data.mybatisPlus.entity.User user=new com.ourspring.data.mybatisPlus.entity.User();
//		user.setId(666);
//		user.setAge(10);
//		user.setName("name");
//		userMapper.insert(user);
//	}
//
//}