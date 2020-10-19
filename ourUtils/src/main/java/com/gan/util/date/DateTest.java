package com.gan.util.date;

import java.util.Date;

/**
 * @author ganxinming
 * @createDate 2020/10/11
 * @description
 */
public class DateTest {
	public static void main(String[] args) {
		Date date=new Date();
		String aa= DateFormatUtils.formatDate(date,DateFormatUtils.DATE_Month_FORMAT);
		System.out.println(aa);
	}
}
