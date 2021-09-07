package com.ourgut.sort;

/**
 * @author ganxinming
 * @createDate 2021/9/6
 * @description 插入排序 O(n2) 稳定
 * 1.一次for循环，每次循环得到的值就是当要插入的值
 * 2.通过while循环，从后往前 寻找插入位置
 */
public class InsertSort {
	public static void main(String[] args) {
		int[] a={1,9,5,6,7,4,2,3,8,0};
		sort(a);
		traverse(a);
	}
	public static void sort(int [] arry){
		for(int i=0;i<arry.length;i++){
			//将需要排序的值赋给temp，
			int temp=arry[i];
			int j=i;
			//因为前面都是排好序的，所以将temp跟前面的比较，从后向前比较
			//如果小于，就把值向后移，因为arry[i]，这个位置是空的，值已经交给temp了
			while (j >0 && temp < arry[j-1]){
				//这个时候arry[i]有值了，那么arry[j-1]这个位置就空，用于下一次赋值
				arry[j]=arry[j-1];
				j--;
			}
			//只要一有发现不在满足temp<arry[j-1]，就直接退出了，因为我们前面那个序列是有序的，只有一不满足就可以退出，也正好插在这个位置
			arry[j]=temp;
		}
	}
	//遍历数组
	public static void traverse(int[] arry){
		for (int a : arry){
			System.out.println(a);
		}
	}
}

