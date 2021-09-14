package com.ourgut.likou.pointer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ganxinming
 * @createDate 2021/9/13
 * @description 567. 字符串的排列
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 *
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false

 */
public class Test2 {
	public boolean checkInclusion(String s1, String s2) {
		Map<Character,Integer> need=new HashMap<>();
		Map<Character,Integer> window=new HashMap<>();
		//初始化need
		for (int i=0;i<s1.length();i++){
			need.put(s1.charAt(i),need.getOrDefault(s1.charAt(i),0)+1);
		}
		//双向滑动
		int left=0,right=0,start=0,valid=0;
		int len=Integer.MAX_VALUE;
		while(right < s2.length()){
			char c=s2.charAt(right);
			right++;
			//判断是否在need里
			if(need.containsKey(c)){
				int count=window.getOrDefault(c,0)+1;
				window.put(c,count);
				if(need.get(c) == count){
					valid++;
				}
			}
			//这个条件就和Test1不一样，此条件为达到 需要left移动的条件时，此项目中当字符串长度
			while((right - left) >= s1.length()){
				//如果已经满足相应配对则left移动
				if(valid == need.size()){
					return true;
				}
				//回退
				char ch = s2.charAt(left);
				left++;
				if(need.containsKey(ch)){
					if(window.get(ch).equals(need.get(ch))){
						valid--;
					}
					window.put(ch,window.get(ch)-1);
				}

			}
		}
		return false;

	}
}
