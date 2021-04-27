package com.ourGut.log;

import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;

/**
 * @author ganxinming
 * @createDate 2021/4/8
 * @description
 */
//public class TraceHolderTest {
//	public static void main(String[] args) {
//		TraceWatch traceWatch = new TraceWatch();
//
//		TraceHolder.run(traceWatch, "function1", i -> {
//			try {
//				TimeUnit.SECONDS.sleep(1); // 模拟业务代码
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		});
//
//		String result = TraceHolder.run(traceWatch, "function2", () -> {
//			try {
//				TimeUnit.SECONDS.sleep(1); // 模拟业务代码
//				return "YES";
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//				return "NO";
//			}
//		});
//
//		TraceHolder.run(traceWatch, "function1", i -> {
//			try {
//				TimeUnit.SECONDS.sleep(1); // 模拟业务代码
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		});
//
//		System.out.println(JSON.toJSONString(traceWatch.getTaskMap()));
//	}
//}
//
///* output:
//{"function2":[{"data":1004,"taskName":"function2"}],"function1":[{"data":1001,"taskName":"function1"},{"data":1002,"taskName":"function1"}]}
//*/
//public class TraceHolder {
//	/**
//	 * 有返回值调用
//	 */
//	public static <T> T run(TraceWatch traceWatch, String taskName, Supplier<T> supplier) {
//		try {
//			traceWatch.start(taskName);
//
//			return supplier.get();
//		} finally {
//			traceWatch.stop();
//		}
//	}
//
//	/**
//	 * 无返回值调用
//	 */
//	public static void run(TraceWatch traceWatch, String taskName, IntConsumer function) {
//		try {
//			traceWatch.start(taskName);
//
//			function.accept(0);
//		} finally {
//			traceWatch.stop();
//		}
//	}
//}
