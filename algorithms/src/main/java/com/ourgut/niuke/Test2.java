package com.ourgut.niuke;

import java.util.ArrayList;

/**
 * @author ganxinming
 * @createDate 2021/8/10
 * @description 从尾到头打印链表
 * 输入一个链表的头节点，按链表从尾到头的顺序返回每个节点的值（用数组返回）。
 *
 * 如输入{1,2,3}的链表如下图:
 *
 * 返回一个数组为[3,2,1]
 *
 * 0 <= 链表长度 <= 1000
 *
 * 解法：递归遍历
 */
public class Test2 {
	ArrayList<Integer> list=new ArrayList<Integer>();


	public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
		if(listNode == null){
			return list;
		}
		printListFromTailToHead(listNode.next);
		list.add(listNode.val);
		return list;
	}



	class ListNode{
		int val;
		ListNode next = null;
		ListNode(int val) {
			this.val = val;
		}
	}
}
