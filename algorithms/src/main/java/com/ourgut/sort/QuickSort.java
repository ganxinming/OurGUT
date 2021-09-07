package com.ourgut.sort;

/**
 * @author ganxinming
 * @createDate 2021/9/6
 * @description 快排：冒泡排序的升级 O(nlogn) 不稳当
 * 感觉和归并排序相反
 * 1.取第一个值作为分割界限，将数组分为两边
 * 2.取分界值进行递归
 */
public class QuickSort {

	public static void main(String[] args) {
		int [] a={88,79,92,15,64,31,54,96,71};
		//因为这里我传入的都是位置，所以length-1
		quick(a,0,a.length-1);
		traverse(a);
	}

	public static int panation(int[] arry,int start,int end){

		//作为分界的元素值，将由此值将数组分为两边，先临时记录下来，用来放最后确定下来的位置
		int val=arry[start];
		while(start < end){
			//注意：end > start
			while(end > start && arry[end] > val){
				end--;
			}
			//因为start位置的值已经记录，所以这个位置可以直接放，如果start放了值，也就意味着end位置也可以随意放值了
			arry[start]=arry[end];
			while(end > start && arry[start] < val){
				start++;
			}
			//因为之前的end元素已经被放了，所以现在可以直接放了
			arry[end]=arry[start];
		}
		//最后的位置是start或者end都可以，用来放分界的元素
		arry[start]=val;
		return start;
	}
	//递归调用
	public static void quick(int[] arry,int start,int end){
		if(start >= end){
			return;
		}
		int p = panation(arry, start, end);
		quick(arry,start,p-1);
		quick(arry,p+1,end);
	}

	//遍历数组
	public static void traverse(int[] arry){
		for (int a : arry){
			System.out.println(a);
		}
	}
}