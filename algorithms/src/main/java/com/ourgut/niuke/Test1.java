package com.ourgut.niuke;

/**
 * @author ganxinming
 * @createDate 2021/8/10
 * @description
 *
 * 在一个二维数组array中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * [
 * [1,2,8,9],
 * [2,4,9,12],
 * [4,7,10,13],
 * [6,8,11,15]
 * ]
 * 给定 target = 7，返回 true。
 *
 * 给定 target = 3，返回 false。
 *
 * 0 <= array.length <= 500
 * 0 <= array[0].length <= 500
 *
 *
 * 解题思路：从左下角开始
 */
public class Test1 {
	public class Solution {
		public boolean Find(int target, int [][] array) {
			if(array.length == 0 || array[0].length == 0){
				return false;
			}
			int y=array.length-1;
			int x=0;
			while(y>=0 && x< array[0].length){
				if(target < array[y][x] ){
					--y;
				}
				else
					if(target > array[y][x]){
					++x;
					}
					else{
					return true;
					}
			}
			return false;
		}
	}
}
