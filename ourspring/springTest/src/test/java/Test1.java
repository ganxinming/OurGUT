import static org.junit.jupiter.api.Assertions.*;

import com.ourgut.springtest.Compute;
import com.ourgut.springtest.People;
import com.sun.tools.javac.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author ganxinming
 * @createDate 2021/6/30
 * @description  junit5 注意导包
 * 直接执行main方法
 */
@Slf4j
public class Test1 {
	private Compute compute=null;

	/**
	 * 会“环绕”在每个@Test方法前
	 */
	@BeforeEach
	public void before(){
		this.compute=new Compute();
		log.info("创建对象");
	}

	/**
	 * 执行一次，最先执行
	 */
	@BeforeAll
	public static void beforeClass(){
		log.info("最先执行只执行一次");
	}


	/**
	 * 会“环绕”在每个@Test方法后
	 */
	@AfterEach
	public void after(){
		log.info("清除对象");
	}

	/**
	 * 执行一次，最先执行
	 */
	@AfterAll
	public static void afterClass(){
		log.info("最先执行只执行一次");
	}



	/**
	 * assertTrue(): 期待结果为true
	 * assertFalse(): 期待结果为false
	 * assertNotNull(): 期待结果为非null
	 * assertArrayEquals(): 期待结果为数组并与期望数组每个元素的值均相等
	 */
	@Test
	public void test(){
		int max = compute.getMax(1, 2);
		assertEquals(2,compute.getMax(1, 2));
		System.out.println(max);
	}

	@Test
	public void test2(){
		int max = (int) compute.add(1);
		assertEquals(1,max);
		System.out.println(max);
	}


	/**
	 * 只是暂时不运行
	 */
	@Disabled
	@Test
	public void test3(){
		int max = (int) compute.add(1);
		assertEquals(1,max);
		System.out.println(max);
	}


	/**
	 * 自定义参数,单感觉只能定义简单值
	 * @param x
	 * 值得注意的是，@ValueSource不允许传入Null值和Empty值。从JUnit 5.4开始，
	 * 我们可以使用@NullSource、@EmptySource 和 @NullAndEmptySource 注解可以分别将单个null值、单个Empty和 Null和Empty
	 * 传递给参数化测试方法。
	 * 可以使用多个不同的
	 * chars bytes shorts ints longs floats doucles booleans strings classes
	 */
	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 5, 100,Integer.MAX_VALUE })
	public void test4(int x){
		int max = (int) compute.add(x);
//		assertEquals(1,max);
		System.out.println(max);
	}

	/***
	 * @MethodSource 传入构建的参数，名称一样，默认找当前test类，或者指定包名
	 */

	@ParameterizedTest
	@MethodSource
	void testCapitalize(String input, String result) {
		assertEquals(result, StringUtils.capitalize(input));
	}

	static List<Arguments> testCapitalize() {
		return List.of( // arguments:
				Arguments.arguments("abc", "Abc"));
	}


	/**
	 * 构建自定义参数
	 * @param people
	 */
	@ParameterizedTest
	@MethodSource
	void testPeople(People people) {
		compute.combination(people);
	}

	static java.util.List<Arguments> testPeople() {
		return List.of( // arguments:
				Arguments.arguments(new People("gxm",24)),
				Arguments.arguments(new People("mxg",24)));
	}

}
