package com.ourgut.likou.tree;

import lombok.Data;

/**
 * @author ganxinming
 * @createDate 2021/8/29
 * @description
 */
@Data
public class TreeNode {
	int val;
	public TreeNode left;
	public TreeNode right;
	TreeNode() {}
	TreeNode(int val) { this.val = val; }
	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}
