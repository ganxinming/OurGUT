package com.ourgut.likou.pointer;

/**
 * @author ganxinming
 * @createDate 2021/9/15
 * @description
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * 什么是回文串？
 * 正读和反读都一样，如aa，aba，ccacc,aabbaa
 * 思路：判断一个回文，字符串从中心点出发，left，right向两边扩张，始终left==right的值
 * 1.奇数时，left和right都等于i
 * 2.偶数时，left==i，right=i+1
 * 3.不断遍历i的位置
 */
public class Test4 {
	public String longestPalindrome(String s) {
		String res="";
		for(int i=0;i<s.length();i++){
			res=getSonString(s,i,i).length() > res.length()?getSonString(s,i,i):res;
			res=getSonString(s,i,i+1).length() > res.length()?getSonString(s,i,i+1):res;
		}
		return res;
	}

	/**
	 * 判断从left和right出发是否为回文
	 * @param s
	 * @param left
	 * @param right
	 * @return
	 */
	public String getSonString(String s,int left,int right){
		while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right) ){
			left--;
			right++;
		}
		return s.substring(left+1,right);

	}
}
