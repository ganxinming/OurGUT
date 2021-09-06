package com.ourgut.likou.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ganxinming
 * @createDate 2021/9/1
 * @description 剑指 Offer 37. 序列化二叉树
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 *
 * 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 */
public class Test4 {

	/**
	 * 前序遍历即可，保证空节点也有值替代(为后面反序列)
	 * @param root
	 * @return
	 */
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if(root == null){
			return "#!";
		}
		String a=root.val+"!";
		a+=serialize(root.left);
		a+=serialize(root.right);
		return a;
	}

	/**
	 * 反序列化
	 * 问题：为啥只有前序遍历就可以解析成一颗树了
	 * 为啥之前需要前序和中序才能解析成一颗树呢？因为他们的叶子节点无法判断到头了，这里可以有#代表到尽头了
	 * 之所以要两个顺序解析树，不也是为了确定边界吗？
	 * @param data
	 * @return
	 */
	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		String[] a= data.split("!");
		Queue<String> queue=new LinkedList<>();
		for(int i=0;i<a.length;i++){
			queue.offer(a[i]);
		}
		return bulidNode(queue);
	}

	/**
	 * 将前序遍历转换成队列，每次取队列的第一个值，按顺序构建树就好了。重点是用了队列
	 * @param queue
	 * @return
	 */
	public TreeNode bulidNode(Queue<String> queue){
		String s=queue.poll();
		if("#".equals(s)){
			return null;
		}
		TreeNode node=new TreeNode(Integer.valueOf(s));
		node.left=bulidNode(queue);
		node.right=bulidNode(queue);
		return node;
	}
}
