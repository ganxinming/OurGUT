package com.ourgut.likou.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ganxinming
 * @createDate 2021/8/30
 * @description 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：[[1]]
 */
public class Test1 {

	List<List<Integer>> listAll=new ArrayList<>();
	public List<List<Integer>> permute(int[] nums) {

		DFS(nums,new ArrayList<Integer>());
		return listAll;
	}
	public void DFS(int[] nums ,List<Integer> list){

		//如果满足加入结果且退出
		if(list.size() == nums.length){
			listAll.add(new ArrayList(list));
			return;
		}
		//做选择
		for(int i=0;i<nums.length;i++){
			//排除不合法的情况
			if(isValid(nums[i],list)){
				list.add(nums[i]);
				//进行新一轮遍历
				DFS(nums,list);
				//退出这次遍历，退出最后元素
				list.remove(list.size()-1);
			}
		}

	}

	public boolean isValid(int val,List<Integer> list){
		for(Integer i : list){
			if(i == val){
				return false;
			}
		}
		return true;
	}
}
