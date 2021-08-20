package com.gan.guava;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

/**
 * @author ganxinming
 * @createDate 2020/11/4
 * @description
 */
public class TestCache {

	/**
	 * 自定义，过期自定义重新加载缓存
	 */
	private static LoadingCache<String, Optional<Map>> INDICATOR_CACHE = CacheBuilder.newBuilder()
			.maximumSize(10000).expireAfterAccess(5, TimeUnit.MINUTES)
			.build(new CacheLoader<String, Optional<Map>>() {
				@Override
				public Optional<Map> load(String code) {
					return createCache(code);
				}
			});
	private static Optional<Map> createCache(String code) {
		if (code != null) {
			return Optional.of(Maps.newHashMap());
		}
		return Optional.empty();
	}

	public static void main(String[] args) throws InterruptedException {
		 Cache<Object, Object> cache = CacheBuilder.newBuilder()
				.maximumSize(10) //设置最大容量
				.expireAfterWrite(60, TimeUnit.SECONDS) //缓存一段时间失效
				.concurrencyLevel(10) //并发级别
				.recordStats()
				.build();
		//放入缓存
		Set set=new HashSet<>();
		for (int i=0;i<20;i++){
			cache.put(i,i);
			set.add(i);
		}
		Thread.sleep(997L);
		ImmutableMap<Object, Object> allPresent = cache.getAllPresent(set);
		for (Map.Entry<Object, Object> entry :allPresent.entrySet()){
			System.out.println("key"+entry.getKey());
			System.out.println("value"+entry.getValue());
		}
		System.out.println("缓存是:"+cache.getIfPresent("key"));
	}
}
