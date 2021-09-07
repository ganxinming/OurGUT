package com.ourgut.sort;

/**
 * @author ganxinming
 * @createDate 2021/9/7
 * @description 希尔排序只不过是插入排序的升级 O(n^1.5) 不稳定
 * 1.分组进行插入排序，初始为length/2，后面不断/2，直到 < 1
 * 2.代码和插入排序基本一致，只不过i不从0开始了，从group开始
 */
public class ShellSort {
	public static void main(String[] args) {
		int[] arr={5,8,4,3,9};
		shell(arr);
		traverse(arr);
	}

	public static void shell(int [] arrays) {
		int group=arrays.length/2;
		//确定开始分组为长度的一半
		while (group >=1){
			//为啥要从i=group开始呢？
			//这样也能排到后面不是一个完整的分组的数。
			// 想一下插入排序，他不也是从后开始吗，然后依次比较，如果后面小于前面，前面就往后挪，直到大于，在将开始的值赋值到该位置
			for (int i=group;i<arrays.length;i++){
				int key=arrays[i];
				int j=i;
				while (j-group >= 0 && key < arrays[j-group]){
					arrays[j]=arrays[j-group];
					j=j-group;
				}
				arrays[j]=key;
			}
			//每次缩减一半
			group=group/2;
		}

	}
	//遍历数组
	public static void traverse(int[] arry){
		for (int a : arry){
			System.out.println(a);
		}
	}
}
