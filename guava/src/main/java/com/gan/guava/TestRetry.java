package com.gan.guava;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.BlockStrategies;
import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.RetryListener;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicates;

/**
 * @author ganxinming
 * @createDate 2020/10/23
 * @description 重试组件，当超出重试次数报异常
 */
public class TestRetry {
	public static void main(String[] args) {
		Retryer<Boolean> retryer = RetryerBuilder.<Boolean> newBuilder()
				//有异常重试
				.retryIfException()
				//返回对象为空重试
				.retryIfResult(Predicates.isNull())
				//任务阻塞策略
				.withBlockStrategy(BlockStrategies.threadSleepStrategy())
				//停止重试策略,重试3次
				.withStopStrategy(StopStrategies.stopAfterAttempt(3))
				//重试间隔时间
				.withWaitStrategy(WaitStrategies.fixedWait(2, TimeUnit.MILLISECONDS))
				.build();

		boolean withRetry = delayRetry(retryer);
		System.out.println("[RETRY-RESULT]: " + withRetry);
	}
	public static boolean delayRetry(Retryer<Boolean> retryer){
		boolean result = Boolean.TRUE;
		try {
			result = retryer.call(()->{
				System.out.println("[TIME-STAMP]:" + System.currentTimeMillis());
				return Boolean.FALSE;
			});
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (RetryException e) {
			e.printStackTrace();
		}

		return result;
	}

}
