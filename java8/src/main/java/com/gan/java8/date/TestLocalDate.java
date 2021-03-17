package com.gan.java8.date;

import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.Test;

/**
 * @author ganxinming
 * @createDate 2021/3/2
 * @description
 */
public class TestLocalDate {

	@Test
	public void testLocalDate(){
		LocalDate localDate=LocalDate.now();
		System.out.println(localDate);
		LocalDate localDate1 = localDate.minusMonths(3);
		System.out.println(localDate1);
		long l = localDate1.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
		System.out.println(l);
	}
}
