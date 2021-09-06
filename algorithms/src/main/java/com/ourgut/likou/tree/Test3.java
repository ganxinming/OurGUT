package com.ourgut.likou.tree;

import java.util.ArrayList;

/**
 * @author ganxinming
 * @createDate 2021/8/29
 * @description 剑指 Offer 34. 二叉树中和为某一值的路径
 *
 * 输入一颗二叉树的根节点和一个整数，按字典序打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * 示例1
 * 输入：
 * {10,5,12,4,7},22
 * 返回值：
 * [[10,5,7],[10,12]]
 * 示例2
 * 输入：
 * {10,5,12,4,7},15
 * 返回值：
 * []
 */
public class Test3 {

	public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
		ArrayList<ArrayList<Integer>> listAll=new ArrayList<ArrayList<Integer>>();
		if (root == null)
			return listAll;
		ArrayList<Integer> list=new ArrayList<Integer>();
		//这里就是利用了，方法可以改变list对象的原理，所以递归完，就是答案了。
		//listAll用来存路径
		//list类似于栈的结构，回溯遍历。先遍历到底层，然后回退一个遍历。
		backTrace(listAll,list,root,target);
		return listAll;
	}
	public void  backTrace(ArrayList<ArrayList<Integer>> listAll,ArrayList<Integer> list,TreeNode root,int target){
		//只有当target值比节点值大时才能进行，如果小于，则说明此路肯定不通了，那就不做处理
		//为什么不用处理？是因为我们这还是比较阶段，如果不满足，就不满足呗，反正这个节点又没存。
		if (target >= root.val){
			target-=root.val;
			//将路径值添加进去
			list.add(root.val);
			//如果为空，且为叶子节点，则将这条路径加入进去。
			//为什么要new ArrayList<Integer>(list)，因为list要用来遍历和回退节点。
			//很奇怪，为什么list可以遍历使用，就不怕出错吗？因为传入的list，是个引用啊，方法里面改变值，是会改变list本身的。
			if (target == 0 && root.right == null && root.left == null){
				listAll.add(new ArrayList<Integer>(list));
			}
			//如果左子树为不为空，就递归调用
			//不要怕递归，可以想象，他递归遍历，就是先遍历了最左边所有节点，然后回退一个节点，遍历右节点
			if (root.left != null){
				backTrace(listAll,list,root.left,target);
			}
			//如果右子树为不为空，就递归调用
			if (root.right !=null){
				backTrace(listAll,list,root.right,target);
			}
			//如果左右都没有，说明到了叶子节点，但是还是不满足，则回退一个节点。
			list.remove(list.size()-1);
		}
	}
}
