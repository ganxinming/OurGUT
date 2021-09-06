package com.ourgut.common;

import lombok.Data;

/**
 * @author ganxinming
 * @createDate 2021/9/1
 * @description
 */
@Data
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

