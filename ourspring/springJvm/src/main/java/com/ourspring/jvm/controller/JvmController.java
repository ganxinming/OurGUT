package com.ourspring.jvm.controller;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.ourspring.jvm.entity.JvmEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/6/15
 * @description
 *
 * 常用JVM配置：
 *
 * 堆设置
 * -Xms:初始堆大小
 * -Xmx:最大堆大小
 * -Xmn:新生代大小
 * -XX:NewRatio:设置新生代和老年代的比值。如：为3，表示年轻代与老年代比值为1：3
 * -XX:SurvivorRatio:新生代中Eden区与两个Survivor区的比值。注意Survivor区有两个。如：为3，表示Eden：Survivor=3：2，一个Survivor区占整个新生代的1/5
 * -XX:MaxTenuringThreshold:设置转入老年代的存活次数。如果是0，则直接跳过新生代进入老年代，默认为15。
 * -XX:PermSize、-XX:MaxPermSize:分别设置永久代最小大小与最大大小（Java8以前）
 * -XX:MetaspaceSize、-XX:MaxMetaspaceSize:分别设置元空间最小大小与最大大小（Java8以后）
 * 收集器设置
 * Serial收集器
 * -XX:+UseSerialGC->指定年轻代为Serial收集器
 * -XX:+UseSerialOldGC->指定老年代为Serial收集器
 * ParNew收集器
 * -XX:+UseParNewG->指定年轻代为ParNew收集器
 * Parallel Scavenge收集器
 * -XX:+UseParallelGC->指定年轻代为Parallel收集器
 * -XX:+UseParallelOldGC->指定老年代为Parallel收集器
 * -XX:ParallelGCThreads->指定GC工作的线程数量
 * CMS收集器
 * -XX:+UseConcMarkSweepGC->指定指定老年代为CMS收集器
 * -XX:ConcGCThreads->并发的GC线程数
 * -XX:+UseCMSCompactAtFullCollection->FullGC之后是否做压缩整理(减少碎片)
 * -XX:CMSFullGCsBeforeCompaction->多少次FullGC之后压缩一次，默认是0，代表每次FullGC后都会压缩一次，比如-XX:CMSFullGCsBeforeCompaction=0
 * -XX:CMSInitiatingOccupancyFraction->当老年代使用达到该比例时会触发FullGC(默认是92，这是百分比)，比如-XX:CMSInitiatingOccupancyFaction=92
 * -XX:+UseCMSInitiatingOccupancyOnly->只使用设定的回收阈值(-XX:CMSInitiatingOccupancyFraction设定的值)，如果不指定，JVM仅在第一次使用设定值，后续则会自动调整
 * -XX:+CMSScavengeBeforeRemark->在CMSGC前启动一次minor gc，目的在于减少老年代对年轻代的引用，降低CMS GC的标记阶段时的开销，一般CMS的GC耗时80%都在 remark阶段
 * G1收集器
 * -XX:+UseG1GC->开启G1收集器
 * -XX:G1HeapRegionSize->指定分区大小(1MB~32MB，且必须是2的幂)，默认将整堆划分为2048个分区
 * -XX:MaxGCPauseMillis->目标暂停时间(默认200ms)
 * -XX:G1NewSizePercent->新生代内存初始空间(默认整堆5%)
 * -XX:G1MaxNewSizePercent->新生代内存最大空间
 *
 * 垃圾回收统计信息
 * -XX:+PrintGC
 * -XX:+PrintGCDetails
 * -XX:+PrintGCTimeStamps
 * -Xloggc:filename
 * 并行收集器设置
 * -XX:ParallelGCThreads=n:设置并行收集器收集时使用的CPU数。并行收集线程数。
 * -XX:MaxGCPauseMillis=n:设置并行收集最大暂停时间
 * -XX:GCTimeRatio=n:设置垃圾回收时间占程序运行时间的百分比。公式为1/(1+n)
 * 并发收集器设置
 * -XX:+CMSIncrementalMode:设置为增量模式。适用于单CPU情况。
 */
@RestController
public class JvmController {

	@RequestMapping("/test")
	public String test(){
		return "jvm";
	}

	@RequestMapping("/test1")
	public String test1() throws Exception{
		ExecutorService executorService = Executors.newFixedThreadPool(50);
		executorService.submit(()->{
			int i=0;
			while(true){

				JvmEntity entity=JvmEntity.builder().name(UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString())
						.name2(UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString())
						.name1(UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString())
						.name3(UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString())
						.name4(UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString())
						.name5(UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString())
						.name6(UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()).build();
//				System.out.println(Thread.currentThread().getName()+"运行次数:"+i+"对象为："+entity.toString());
				Thread.sleep(100);
			}
		});
		return "jvm";
	}


	@RequestMapping("/test2")
	public String test2() throws Exception{

		while(true){
			System.out.println("锁住了");
			Thread.sleep(10000);
		}
	}
}
