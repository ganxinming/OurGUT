package com.ourgut.likou.BFS;

import java.util.LinkedList;
import java.util.Queue;

import com.ourgut.likou.tree.TreeNode;

/**
 * @author ganxinming
 * @createDate 2021/8/31
 * @description 111. 二叉树的最小深度
 *
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明：叶子节点是指没有子节点的节点。
 */
public class Test1 {


	//一种递归解法,缺点在为一条线时时间复杂度为n

	/**
	 * 这种递归需要判断单链的情况，所以对于左为空，或右为空的情况，需要单独判断
	 * @param root
	 * @return
	 */
	public int minDepth(TreeNode root) {
		if(root == null){
			return 0;
		}
		if(root.left == null && root.right == null){
			return 1;
		}
		if(root.left == null){
			return minDepth(root.right)+1;
		}
		if(root.right == null){
			return minDepth(root.left)+1;
		}
		int left=minDepth(root.left);
		int right=minDepth(root.right);
		return Math.min(left,right)+1;
	}

	/**
	 * 广度遍历(推荐解法)
	 * 核心思想：从顶进行遍历，每遍历完一层节点就深度+1，结束条件是有节点没有子节点了，那么就是最小的深度
	 * 队列的使用方式 Queue<TreeNode> queue=new LinkedList<>();
	 * queue.offer(node);入元素
	 * quue.poll();出元素
	 * @param root
	 * @return
	 */
	int minDepth1(TreeNode root) {
		if (root == null) return 0;
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		// root 本身就是一层，depth 初始化为 1
		int depth = 1;

		while (!q.isEmpty()) {
			int sz = q.size();
			/* 将当前队列中的所有节点向四周扩散 */
			for (int i = 0; i < sz; i++) {
				TreeNode cur = q.poll();
				/* 判断是否到达终点 */
				if (cur.left == null && cur.right == null)
					return depth;
				/* 将 cur 的相邻节点加入队列 */
				if (cur.left != null)
					q.offer(cur.left);
				if (cur.right != null)
					q.offer(cur.right);
			}
			/* 这里增加步数 */
			depth++;
		}
		return depth;
	}

}
