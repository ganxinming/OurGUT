package com.ourspring.sendHttpRequest.entity;

import java.util.List;

/**
 * @author ganxinming
 * @createDate 2021/1/18
 * @description
 */
public class ResponseMessage {
	private Object message;
	private int status;
	private List<DataBean> data;

	public class DataBean{
		private int noticeId;
		private String noticeTitle;
		private Object noticeImg;
		private long noticeCreateTime;
		private long noticeUpdateTime;
		private String noticeContent;
	}
}
