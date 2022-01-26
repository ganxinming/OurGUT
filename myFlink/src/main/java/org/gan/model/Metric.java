package org.gan.model;

/**
 * @author ganxinming
 * @createDate 2022/1/21
 * @description
 */
public class Metric {
	public String name;
	public long timestamp;

	public Metric(String name, long timestamp) {
		this.name = name;
		this.timestamp = timestamp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
