package com.ourgut.devSpecifica.response;

import java.io.Serializable;

/**
 * @author ganxinming
 * @createDate 2021/8/20
 * @description
 */
public class Response implements Serializable {

	private static final long serialVersionUID = 2360867989280235575L;

	private Result result;

	private Object data;

	public Result getResult() {
		if (this.result == null) {
			this.result = new Result();
		}
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
