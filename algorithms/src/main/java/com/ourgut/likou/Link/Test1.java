package com.ourgut.likou.Link;

import com.ourgut.common.ListNode;

/**
 * @author ganxinming
 * @createDate 2021/9/1
 * @description 21. 合并两个有序链表
 *
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class Test1 {

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode head=new ListNode();
		ListNode treuNode=head;
		while(l1 != null && l2!=null){
			if(l1.val > l2.val){
				ListNode node= new ListNode(l2.val);
				head.next= node;
				head=head.next;
				l2=l2.next;
			}
			else{
				ListNode node= new ListNode(l1.val);
				head.next= node;
				head=head.next;
				l1=l1.next;
			}
		}
		if(l1 != null){
			head.next=l1;
		}
		if(l2 != null){
			head.next=l2;
		}
		return treuNode.next;
	}
}
