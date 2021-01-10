package com.gan.ourspring.async;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

/**
 * @author ganxinming
 * @createDate 2020/12/5
 * @description
 * 使用：在测试里类里
 * 作用：代码异步执行
 * 注意：
 * 1.@Async也可以打在类上,这样类下面的所有方法都是异步的
 * 2.不能在同一类下调用@Async注解的方法,比如A类下有a和b方法,b方法有@Async注解,不能直接这样a调用b,要把b放到其他类中
 * 3.必须要加@EnableAsync注解
 */
@Service
public class AnsycServiceIpl implements AnsycService{

	@Autowired
	AnsycUtil ansycUtil;

	/**
	 * 注意：@Async注解必须是注入类的直系方法
	 * @return
	 * @throws InterruptedException
	 */
	@Override
	@Async("taskExecutor")//使用自定义线程池，不配置可以使用默认线程池
	public Future<Map<Long, List<String>>> startAnsyc() throws InterruptedException {
		return new AsyncResult<>(getMap());
	}

	/**
	 * 像这种就不是直系方法，是在普通方法里调@Async方法，这种不可取
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Override
	public void startSynchronize() throws InterruptedException, ExecutionException {
		System.out.println("开始异步");
		Future<Map<Long, List<String>>> result = getResult();
		System.out.println("异步进行中");
		result.get().get(123L).stream().forEach(System.out::println);
		System.out.println("异步结束");
		return;
	}

	/**
	 * 或者在用另一个注入类使用@Async方法也可
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Override
	public void startAnsycDiff() throws InterruptedException, ExecutionException {
		System.out.println("开始异步");
		Future<Map<Long, List<String>>> result = ansycUtil.result();
		System.out.println("异步进行中");
		result.get().get(123L).stream().forEach(System.out::println);
		System.out.println("异步结束");
	}

	@Async("taskExecutor")
	public Future<Map<Long, List<String>>> getResult() throws InterruptedException {
		Map<Long, List<String>> map=Maps.newHashMap();
		List<String> list= Lists.newArrayList();
		list.add("abc");
		Thread.sleep(1000L);
		map.put(123L,list);
		return new AsyncResult<>(map);
	}
	private Map<Long, List<String>> getMap() throws InterruptedException {
		Map<Long, List<String>> map=Maps.newHashMap();
		List<String> list= Lists.newArrayList();
		list.add("abc");
		Thread.sleep(1000L);
		map.put(123L,list);
		return map;
	}
}
