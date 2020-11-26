package com.gan.java8.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.gan.java8.thread.pojo.User;
import org.junit.Test;

/**
 * @author ganxinming
 * @createDate 2020/11/25
 * @description  创建线程
 *
 */
public class TheadCreate {



	/**
	 * 通过Executors创建线程池，有四种方式。但是其核心都是通过改变
	 * 核心线程，最大线程，队列等来达到下面四种线程池的目的
	 */
	@Test
	public void executorsCreate(){
		/**
		 * 单例
		 ExecutorService executorService = Executors.newSingleThreadExecutor();
		 缓存线程池，没有核心线程，所以所有线程都有keepalive
		 ExecutorService executorService = Executors.newCachedThreadPool();
		 定时任务线程
		 ExecutorService executorService = Executors.newScheduledThreadPool(10);
		 */
		ExecutorService executorService = Executors.newFixedThreadPool(6);
		/**
		 * 线程池的执行任务，目的就是就把任务交给线程池处理，先处理后面的代码
		 */
		for(int j=0;j<10;j++){
			executorService.execute(()->{
				for(int i=0;i<10;i++){
					System.out.println(Thread.currentThread().getName()+"i:"+i);
				}
			});
		}
		System.out.println("甘新明");
	}

	/**
	 * 手动创建线程池
	 *核心线程，最大线程，keepaliveTime，阻塞队列
	 *CallerRunsPolicy策略，执行不完时，主线程帮忙执行，其实非常不好
	 * 如果任务太多了，主线程就完蛋了，一直在帮忙执行任务，后面的程序没法跑了。还不如在队列达到一定数量时，主线程进行sleep。
	 * 这样主线程也不会往池子丢任务，线程池子任务也可以继续执行减少
	 */
	@Test
	public void headCreate() throws InterruptedException {
		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 5,
				0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(10), new ThreadPoolExecutor.CallerRunsPolicy());
		/**
		 * 循环将任务加入线程池，让他执行
		 */
		for(int i=0;i<10;i++){
			poolExecutor.execute(()->{
				for(int j=0;j<100;j++){
					System.out.println(Thread.currentThread().getName()+"j"+j);
				}
			});
		}
		Thread.sleep(10);
		System.out.println("睡眠");
	}

	/**
	 * 测试不同种类的阻塞队列(多线程下：队列满了当前加入消息的线程阻塞，队列为空取消息的线程阻塞，)
	 * ArrayBlockingQueue 有界阻塞队列
	 */
	@Test
	public void testBlockQuenue(){

		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1,2,10,TimeUnit.MILLISECONDS
				,new ArrayBlockingQueue<>(2));

		for (int i = 0; i < 10; i++) {
			int k=i;
			poolExecutor.execute(()->{
				for (int j = 0; j <100 ; j++) {
					System.out.println(Thread.currentThread().getName()+"k:"+k+"j:"+j);
				}
			});
		}
	}

}
