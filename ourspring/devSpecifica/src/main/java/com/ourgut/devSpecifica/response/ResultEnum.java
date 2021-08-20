package com.ourgut.devSpecifica.response;

/**
 * @author ganxinming
 * @createDate 2021/8/20
 * @description
 */
public enum  ResultEnum {
	success(200,"正常"),
	error(500,"服务器异常");
	private Integer code;
	private String message;

	ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}


	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
