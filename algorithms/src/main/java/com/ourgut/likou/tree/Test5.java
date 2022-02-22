package com.ourgut.likou.tree;

/**
 * @author ganxinming
 * @createDate 2021/9/15
 * @description
 */
public class Test5 {
	public static TreeNode head =new TreeNode();
	public static TreeNode curr=head;
	public void flatten(TreeNode root) {

		build(root);
		root=curr.right;
	}

	public void build(TreeNode root){
		if(root == null){
			return;
		}
		TreeNode right=head.right;
		TreeNode left=head.left;
		root.right=
		head.right=new TreeNode(root.val);
		head.left=null;
		head=head.right;
		build(right);
		build(left);
	}
}
