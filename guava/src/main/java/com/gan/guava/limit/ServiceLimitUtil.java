package com.gan.guava.limit;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

/**
 * @author ganxinming
 * @createDate 2021/6/29
 * @description 在任何需要限流的地方，调用前加上
 * 优点：1、所有地方限流 2、可扩展，针对不同的类型限流大小不一样
 */
public class ServiceLimitUtil {

	private static Map<String,Double> map= Maps.newHashMap();
	static{
		map.put("ServiceLimitUtil_"+"order_sync",1d);
	}

	//动态获取限流配置大小是否变更
	private static RateLimiter getRateLimiter(LimitType type) {
		String key="ServiceLimitUtil_"+type.getName();
		RateLimiter rateLimiter=type.getRateLimiter();
		double v = map.get(key);
		if (rateLimiter == null || rateLimiter.getRate() != v) {
			rateLimiter = RateLimiter.create(v);
			System.out.println("create rateLimiter size is:"+v);
		}
		return rateLimiter;
	}

	public static void limit(LimitType type ){
		Boolean isOpen = true;
		try {
			if(isOpen){
				if(getRateLimiter(type).tryAcquire()){
					return;
				}
				else{
					double acquire = getRateLimiter(type).acquire();
					System.out.println("rateLimiter size is:"+type.getRateLimiter().getRate());
					System.out.println(map.get("ServiceLimitUtil_"+"order_sync"));
				}
			}

		} catch (Throwable throwable) {
		}
	}

	public enum LimitType{
		ORDER_SYNC("order_sync",1d);

		private String name;
		private Double limitNum;
		private RateLimiter rateLimiter;
		LimitType(String name,Double limitNum){
			this.name=name;
			this.limitNum=limitNum;
			this.rateLimiter=RateLimiter.create(limitNum);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Double getLimitNum() {
			return limitNum;
		}

		public void setLimitNum(Double limitNum) {
			this.limitNum = limitNum;
		}

		public RateLimiter getRateLimiter() {
			return rateLimiter;
		}

		public void setRateLimiter(RateLimiter rateLimiter) {
			this.rateLimiter = rateLimiter;
		}
	}

	@Test
	public void test(){

		ExecutorService executorService = Executors.newFixedThreadPool(5);
		while (true){
			executorService.submit(()->{
				ServiceLimitUtil.limit(LimitType.ORDER_SYNC);
				System.out.println("成功");
			});
			if(LimitType.ORDER_SYNC.getLimitNum()<4){
				LimitType.ORDER_SYNC.setLimitNum(LimitType.ORDER_SYNC.getLimitNum()+1);
				map.put("ServiceLimitUtil_"+"order_sync",LimitType.ORDER_SYNC.getLimitNum());
			}
			System.out.println("size:"+map.get("ServiceLimitUtil_"+"order_sync"));
		}
	}


}
