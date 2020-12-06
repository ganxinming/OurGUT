package com.gan.ourspring.async;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ganxinming
 * @createDate 2020/12/5
 * @description
 */
@SpringBootTest
public class AnsycTest {

	@Autowired
	AnsycService ansycService;

	@Test
	public void test() throws InterruptedException, ExecutionException {
		System.out.println("异步开始");
		Future<Map<Long, List<String>>> mapFuture = ansycService.startAnsyc();
		System.out.println("异步进行中，可以做其他事");
		mapFuture.get().get(123L).stream().forEach(System.out::println);
		System.out.println("异步结束");
	}

	@Test
	public void test2() throws ExecutionException, InterruptedException {
		ansycService.startSynchronize();
	}

	@Test
	public void test3() throws ExecutionException, InterruptedException {
		ansycService.startAnsycDiff();
	}
}
