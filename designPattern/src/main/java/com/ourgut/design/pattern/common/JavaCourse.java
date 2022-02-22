package com.ourgut.design.pattern.common;

import lombok.Data;

/**
 * @author ganxinming
 * @createDate 2022/1/8
 * @description
 */
@Data
public class JavaCourse implements ICourse {
	@Override
	public void record() {
		System.out.println("录制Java课程");
	}
}
