package com.gan.ourspring.filter;

import java.util.concurrent.atomic.AtomicInteger;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ganxinming
 * @createDate 2021/1/10
 * @description
 */
@Setter
@Getter
public class WindowLimitFilter extends AbstractMatcherFilter<ILoggingEvent> {
	Long window;

	Integer limit;

	Level level;

	FixedWindow fixedWindow = null;

	@Override
	public FilterReply decide(ILoggingEvent event) {
		if (!isStarted()) {
			return FilterReply.NEUTRAL;
		}
		if (event.getLevel().equals(level)) {
			if (fixedWindow == null) {
				fixedWindow = new FixedWindow(window, limit);
			} else {
				if (fixedWindow.tryAcquire()) {
					return onMatch;
				} else {
					return onMismatch;
				}
			}
		}
		return onMismatch;
	}

	@Override
	public void start() {
		if (this.window != null && this.limit != null) {
			super.start();
		}
	}

	class FixedWindow {
		private Long time = System.currentTimeMillis();

		private AtomicInteger count = new AtomicInteger(0);

		/**
		 * 限流数量
		 */
		private Integer limit;

		/**
		 * 限流窗口大小(ms)
		 */
		private Long window;

		public FixedWindow(Long window, Integer limit) {
			this.window = window;
			this.limit = limit;
		}

		public boolean tryAcquire() {
			long nowTime = System.currentTimeMillis();
			if (nowTime < time + window) {
				return limit >= count.incrementAndGet();
			} else {
				time = nowTime;
				count.compareAndSet(count.get(), 0);
				return true;
			}
		}
	}
}

