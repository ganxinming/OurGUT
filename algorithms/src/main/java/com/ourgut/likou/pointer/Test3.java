package com.ourgut.likou.pointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ganxinming
 * @createDate 2021/9/13
 * @description 438. 找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 异位词 指字母相同，但排列不同的字符串。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *  示例 2:
 *
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *
 * 其实和Test2一模一样
 */
public class Test3 {
	public List<Integer> findAnagrams(String s2, String s1) {
		/**
		 * 即记录你需要匹配的字符串和对应的次数
		 */
		Map<Character,Integer> need=new HashMap<>();
		/**
		 * 记录你窗口中此时的字符和对应的数字
		 */
		Map<Character,Integer> window=new HashMap<>();
		//初始化need,即记录你需要匹配的字符串和对应的次数
		for (int i=0;i<s1.length();i++){
			need.put(s1.charAt(i),need.getOrDefault(s1.charAt(i),0)+1);
		}
		//双向滑动
		int left=0,right=0,valid=0;
		List<Integer> list=new ArrayList<>();
		while(right < s2.length()){
			char c=s2.charAt(right);
			right++;
			//判断是否在need里
			if(need.containsKey(c)){
				int count=window.getOrDefault(c,0)+1;
				window.put(c,count);
				if(need.get(c).equals(count)){
					valid++;
				}
			}

			while((right - left) >= s1.length()){
				//如果已经满足相应配对则left移动
				if(valid == need.size()){
					list.add(left);
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
		return list;
	}
}
