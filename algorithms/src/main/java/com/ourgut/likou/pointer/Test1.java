package com.ourgut.likou.pointer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ganxinming
 * @createDate 2021/9/12
 * @description 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：
 *
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *  
 *
 * 示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 *
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 示例 3:
 *
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 *
 * 解法：
 * 一、定义两个map：
 * 1.需要比较的字符map的key和value(次数)
 * 2.记录下当前所需窗口的map
 *
 * 二、构建初始化need的数组
 *
 * 三、len记录最小的串长度，记录最小串的起始
 *
 * 四、
 */
public class Test1 {
	public String minWindow(String s, String t) {
		Map<Character,Integer> window = new HashMap();  // 用来记录窗口中的有效字符和数量
		Map<Character,Integer> need = new HashMap();  // 需要凑齐的字符和数量  一般是需要匹配的那串字符
		// 构建need字符集
		for (int i = 0; i < t.length(); i++) {
			char needChar = t.charAt(i);
			need.put(needChar,need.getOrDefault(needChar,0)+1);
		}
		int left=0;
		int right=0;
		int start=0;
		int len=Integer.MAX_VALUE;//
		int valid=0;//need中有效key

		//一般都是right < s.length()
		while(right < s.length()){
			char c=s.charAt(right);
			right+=1;
			//符合条件的字符加入到当前的window窗口
			if(need.containsKey(c)){
				int count=window.getOrDefault(c,0)+1;
				window.put(c,count);
				//如果window中已经和need相同了，有效+1
				if(count==need.get(c)){
					valid++;
				}
			}
			//这个达到需要移动左边界的条件 需要视情况而定
			while(need.size() == valid){
				//记录下最小的字符串的起始和长度
				if((right-left) < len){
					len=right-left;
					start=left;
				}
				//left开始移动
				char cc=s.charAt(left);
				left++;
				//只有对于有效的字符，才需要从window窗口移除
				if(need.containsKey(cc)){
					//如果已经不满足条件了，有效--
					if(window.get(cc).equals(need.get(cc))){
						valid--;
					}
					window.put(cc,window.get(cc)-1);
				}
			}
		}
		return len==Integer.MAX_VALUE ? "" : s.substring(start,start+len);
	}

}
