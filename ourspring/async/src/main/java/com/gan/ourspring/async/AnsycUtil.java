package com.gan.ourspring.async;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

/**
 * @author ganxinming
 * @createDate 2020/12/6
 * @description
 */
@Service
public class AnsycUtil {

	@Async("taskExecutor")
	public  Future<Map<Long, List<String>>> result() throws InterruptedException {
		Map<Long, List<String>> map= Maps.newHashMap();
		List<String> list= Lists.newArrayList();
		list.add("abc");
		Thread.sleep(1000L);
		map.put(123L,list);
		return new AsyncResult<>(map);
	}
}
