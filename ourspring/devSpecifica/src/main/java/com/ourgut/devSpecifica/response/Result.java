package com.ourgut.devSpecifica.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author ganxinming
 * @createDate 2021/8/20
 * @description
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Result", propOrder = { "resultCode", "resultMsg" })
public class Result implements Serializable {
	private static final long serialVersionUID = 10L;
	protected int resultCode;
	protected String resultMsg;

	public int getResultCode() {
		return this.resultCode;
	}

	public void setResultCode(int value) {
		this.resultCode = value;
	}

	public String getResultMsg() {
		return this.resultMsg;
	}

	public void setResultMsg(String value) {
		this.resultMsg = value;
	}
}
