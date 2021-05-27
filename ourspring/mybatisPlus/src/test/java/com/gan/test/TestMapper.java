package com.gan.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ourgut.ourspring.mybatisplus.MybatisPlusApplication;
import com.ourgut.ourspring.mybatisplus.entity.User;
import com.ourgut.ourspring.mybatisplus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ganxinming
 * @createDate 2021/5/27
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={MybatisPlusApplication.class})
public class TestMapper {

	@Autowired UserMapper userMapper;

	/**
	 * 如果实体类id没有指定id生成规则，且没有设置id时，会默认设置个id
	 * 可以设置雪花算法id  @TableId(type = IdType.ID_WORKER)
	 */
	@Test
	public void testInsert(){
		User user = new User();
		user.setName("codewei");
		user.setAge(21);
		user.setEmail("634498594@qq.com");
		int result = userMapper.insert(user);
		System.out.println(result);   // 受影响的行数
	}

	@Test
	public void testUpdate(){
		User user = new User();
		// 通过条件自动拼接动态sql
		user.setId(1397789098713792513L);
		user.setName("asdasd");
		int reslut = userMapper.updateById(user);   // 参数是一个对象！！
		System.out.println(reslut);
	}

	// 测试乐观锁成功
	/**
	 * 乐观锁其实就是先进行select查出当前待更新数据的version，
	 * 然后 update user set name = "codewei",version = version +1where id =2 and version = 1
	 * 进行update操作，成功后version成2。
	 * 但是这个操作多线程下，肯定是有部分操作成功，有部分操作失败。
	 * 比如：先查询version=1，但之后version可能已经变为2了，这时候就失败了
	 * 可以使用自旋锁来尝试多次提交
	 */
	@Test
	public void optimisticLockerTest(){
		// 查询用户的信息
		User user = userMapper.selectById(1397797276826443777L);
		// 修改用户信息
		user.setName("jjjk");
		user.setEmail("156748115@qq.com");
		// 执行更新操作
		userMapper.updateById(user);
	}


	// 测试分页查询
	@Test
	public void pageSelectTest(){
		// 参数1：当前页   参数二：页面显示条数
		Page<User> page = new Page<>(1,2);
		QueryWrapper<User> queryWrapper=new QueryWrapper();
		queryWrapper.orderByDesc("id");
		userMapper.selectPage(page, queryWrapper);
		List<User> users = page.getRecords();
		for (User user : users) {
			System.out.println(user);
		}
	}

	// 条件查询,map封装条件
	@Test
	public void testSelectByBatchIds(){
		Map<String,Object> map = new HashMap<>();
		// 自定义要查询
		map.put("name","唐嫣");  // 条件  where name = '唐嫣'
		List<User> users = userMapper.selectByMap(map);
		for (User user : users) {
			System.out.println(user);
		}
	}


	/**
	 * 配置了逻辑删除，则不删除实体，但是查询时则查不到了
	 */
	@Test
	public void deleteDelete(){
		userMapper.deleteById(1397804185906184194L);  // 删除ID为1的用户
	}

	/**
	 * 查询所有，可以用于测试性能插件，如果超出查询时间则报错
	 */
	@Test
	public void selectAll(){
		List<User> users = userMapper.selectList(null);
		for (User user : users) {
			System.out.println(user);
		}
	}

}
