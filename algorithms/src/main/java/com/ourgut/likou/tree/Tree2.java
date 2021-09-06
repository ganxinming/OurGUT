package com.ourgut.likou.tree;

/**
 * @author ganxinming
 * @createDate 2021/8/29
 * @description 124. 二叉树中的最大路径和
 *
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * 路径和 是路径中各节点值的总和。
 *
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 *
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 *  
 *
 * 提示：
 *
 * 树中节点数目范围是 [1, 3 * 104]
 * -1000 <= Node.val <= 1000
 *
 * 1.本质上就是将左右子节点进行比较，谁大取谁，然后加上自己节点，形成这颗树的最大值
 * 2.但是树的最大值不代表路径的最大值，因为可能出现负数，所以最大值一定会出现先遍历的过程，记录下遍历中最大值就行了
 */
public class Tree2 {
	public int max=Integer.MIN_VALUE;

	public  int maxPathSum(TreeNode root) {
		if(root == null){
			return 0;
		}
		side(root);
		return max;
	}

	public  int side(TreeNode root){
		if(root == null){
			return 0;
		}
		//因为可能是负数，所以如果是负数，还不如不要这个节点的值，为0
		int leftV=Math.max(0,side(root.left));
		int rightV=Math.max(0,side(root.right));
		//将路径中每次左中右路径的值加起来进行一次判断，这样就保存了遍历中最大的值
		max=Math.max(max,leftV+rightV+root.val);
		return Math.max(leftV,rightV)+root.val;
	}
}
