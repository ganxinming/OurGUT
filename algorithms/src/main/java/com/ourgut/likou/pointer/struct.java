package com.ourgut.likou.pointer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ganxinming
 * @createDate 2021/9/13
 * @description 双指针二分法框架
 *
 */
public class struct {
	public String minWindow(String s, String t) {
		Map<Character,Integer> window = new HashMap();  // 用来记录窗口中有效字符和数量(即能匹配need的)
		Map<Character,Integer> need = new HashMap();  // 需要凑齐的字符和数量  一般是需要匹配的那串字符
		// 构建need字符集
		for (int i = 0; i < t.length(); i++) {
			char needChar = t.charAt(i);
			need.put(needChar,need.getOrDefault(needChar,0)+1);
		}
		int left=0;
		int right=0;
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
			/**
			 * 注意此时[left,right]的字符串，才是真的的字符串，window里的字符串只是记录的需要的字符串
			 */
			//这个达到需要移动左边界的条件 需要视情况而定，这里是Test1的条件，如果已经满足了vaild值就可以移动了
			while(need.size() == valid){

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
		return "";
	}
}
