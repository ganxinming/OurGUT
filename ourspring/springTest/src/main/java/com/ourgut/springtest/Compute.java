package com.ourgut.springtest;


/**
 * @author ganxinming
 * @createDate 2021/6/30
 * @description
 */
public class Compute {

	public int getMax(int a,int b){
		int max = Math.max(a, b);
		return max;
	}

	private long n = 0;
	public long add(long x) {
		n = n + x;
		return n;
	}
	public long sub(long x) {
		n = n - x;
		return n;
	}

	public void combination(People people){
		System.out.println(people.toString());
	}
}
