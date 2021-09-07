package com.ourgut.sort;

/**
 * @author ganxinming
 * @createDate 2021/9/6
 * @description 选择排序：每次选出最小的放在最前面 O(n2) 不稳定
 * 1.第一次for循环，挑一个位置的数用来放最小的数
 * 2.进行比较选出最小的数
 * 3.将第一层for的位置放最小的数
 */
public class SelectSort {
	public static void main(String[] args) {
		int[] a={1,9,3,8,6,4,2,8,0,1,43,6};
		seleSort(a);
		traverse(a);
	}
	public static void seleSort(int arry[]){
		for(int i=0;i<arry.length;i++){
			int k=i;
			for(int j=i+1;j<arry.length;j++){
				if(arry[j] < arry[k]){
					k=j;
				}
			}
			int tmp=arry[k];
			arry[k]=arry[i];
			arry[i]=tmp;
		}
	}

	//遍历数组
	public static void traverse(int[] arry){
		for (int a : arry){
			System.out.println(a);
		}
	}
}
