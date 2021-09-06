package com.ourgut.likou.twoSplit;

/**
 * @author ganxinming
 * @createDate 2021/9/2
 * @description 剑指 Offer 11. 旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，
 * 输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 */
public class Test2 {
	public int minArray(int[] numbers) {
		if(numbers.length == 0){
			return 0;
		}
		int start=0;
		int end=numbers.length-1;

		while(start < end){
			int mid=(start +end)  >>1;
			if(numbers[mid] > numbers[end]){
				start=mid+1;
			}
			else
			if(numbers[mid] < numbers[end]){
				end=mid;
			}else{
				//不断向前试探，适用于2,2,2,1,2的情况
				--end;
			}

		}
		return numbers[start];

	}
}
