package com.OurGUT.jsonTest.bean;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * @author ganxinming
 * @createDate 2021/4/8
 * @description
 */
@Builder
@Data
public class LoginMessage implements Serializable {

	private static final long serialVersionUID = 2443783348364370481L;

	private Long customerNo;
	private String smdid;
	private Integer method;
	private Long requestTimeMillis;
	private String cityCode;
	private Double currentLg;
	private String ip;
	private Integer source;
	private Integer type;
	private String deviceId;
	private String version;
	private Integer loginCount;
	private Integer mpType;
	private String randomId;
	private Integer clientType;
	private Double currentLt;
	private String phone;
	private String imei;
	private String androidId;
	private Long timestamp;
}
