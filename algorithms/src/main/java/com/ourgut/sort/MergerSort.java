package com.ourgut.sort;

/**
 * @author ganxinming
 * @createDate 2021/9/7
 * @description
 * 归并排序:思路不断将排好的序列合并。 O(nlogn)稳定
 * 难点：递归合并数组，有点难想象。咱们就这样想，加订左右边序列已经排好序，合并左边和右边序列。递归下去.
 * 第二个：就是这个边界的判断有点难，还得好好想想.
 * 1.基于递归，取中间值，分为左右
 * 2.从[start,mid],[mid+1-end]按顺序加入到临时数组，最后将临时数组复制到原数组
 */
public class MergerSort {

	public static void main(String[] args) {
		int [] a={1,9,7,4,2,7,8,3,11,22,88,55,66,34,567,78,23,36,798,123};
		sort(a,0,a.length-1);
		traverse(a);
	}
	//排序
	public static void sort(int[] a,int start,int end){
		if (start >= end) {
			return;
		}

		int mid=(end+start)>>1;
		//递归将左边的排好序
		sort(a,start,mid);
		//递归将右边排好序
		sort(a,mid+1,end);
		//将左右两边排好的序合并
		mergerSort(a,start,mid,end);
	}
	//用来合并左右序列
	public static void mergerSort(int [] a,int start,int mid,int end){
		//定义一个临时数组，用来存储排序后的结果
		int[] arry=new int [end+1];
		//临时数组索引
		int key=start;
		//右边序列的开始
		int center=mid+1;
		//这个索引后面用来遍历临时数组，复制到原数组
		int left=start;
		//左开头小于中间，右开头小于最后end
		while ( start<=mid && center<=end){
			//如果左边小，临时数组就加左边数据
			//如果右边小，临时数组就加右边数据
			arry[key++]=a[start]>a[center]?a[center++]:a[start++];
		}
		//如果左边数据还有多，直接加上去
		while (start<=mid){
			arry[key++]=a[start++];
		}
		//如果右边数据还有多，直接加上去
		while (center<=end){
			arry[key++]=a[center++];
		}
		//复制到原数组上
		for (int index=left;index<=end;index++){
			a[index]=arry[index];
		}
	}



	//遍历数组
	public static void traverse(int[] arry){
		for (int a : arry){
			System.out.println(a);
		}
	}
}
