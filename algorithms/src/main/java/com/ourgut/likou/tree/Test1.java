package com.ourgut.likou.tree;

import java.util.Arrays;


/**
 * @author ganxinming
 * @createDate 2021/8/29
 * @description 从前序与中序遍历序列构造二叉树
 *
 * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
 * 示例 1:
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 *
 * 1.只要牢记Arrays.copyOfRange就没大问题
 * 2.在inorder[i] == preorder[0]里面进行左右节点连接
	 */
public class Test1 {

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if(preorder.length == 0 || inorder.length == 0){
			return null;
		}
		TreeNode node=new TreeNode(preorder[0]);
		for(int i=0;i<inorder.length;i++){
			if(inorder[i] == preorder[0]){
				node.left=buildTree(Arrays.copyOfRange(preorder,1,i+1),Arrays.copyOfRange(inorder,0,i));
				node.right=buildTree(Arrays.copyOfRange(preorder,i+1,preorder.length),Arrays.copyOfRange(inorder,i+1,inorder.length));
				break;
			}
		}
		return node;
	}


}
