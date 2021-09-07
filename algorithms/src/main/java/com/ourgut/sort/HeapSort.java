package com.ourgut.sort;

/**
 * @author ganxinming
 * @createDate 2021/9/7
 * @description 堆排序：选择排序的升级版 O(nlogn) 不稳定
 * 其实这个堆排序就是对所有父节点开始排序。选出子节点最大值与父节点比较。
 * 如果子节点大于父节点则替换，将替换后的子节点作为父节点继续
 * 和他的子节点比较.
 * 1.将数组看成一个完成二叉树，第一个叶子节点的父节点为 (length-1/)2,这里的length是arry.length-1
 * 2.从这个父节点开始，得到子节点 k*2+1得到第一个叶子节点，判断两个叶子节点，取最大，然后和父节点进行比较
 * 3.比较替换子父节点后，被替换的节点位置有变动，所以需要继续看他的叶子节点 while(k*2+1 <= length)
 */
public class HeapSort {
	public static void main(String[] args) {
		int [] a={19,82,38,47,57,10,94,88,45,23,45,23,1,2,5,6,9,3};
		HeapSort s=new HeapSort();
		s.heapSort(a);
		traverse(a);
	}

	public void heapSort(int [] arry){

		for (int i=0;i<arry.length;i++){
			//调整0-arry.length-1-i的堆，使其变成最大堆
			adjustHeap(arry,arry.length-1-i);
			//然后将arr[0]最大值和末尾元素交换，然后继续循环调整
			swap(arry,0,arry.length-1-i);
		}
	}
	public void adjustHeap(int [] arry,int length){
		//这里的i,表示的是最小节点的父节点，i自减，则自动遍历下一个父节点了。（这应该是按完全二叉树排的，从左至右排）
		for (int i=(length-1)/2;i>=0;i--){
			//记录下父节点，因为在进行一次调整后，k的值可能会变成被调整的下标
			int k=i;
			//k*2+1，这是判断左子节点是否存在。（因为根节点是0开始，所以可以用k*2+1这个公式代，实在不行自己代进去试）
			//为啥要用while,因为调整完一个节点，必定使得下面的子树大小发送发生变化，所以才把k单独拿出来
			while (k*2+1 <= length){
				//假设左子节点是左右子节点的最大值
				int bigger=k*2+1;
				//如果这样bigger < length，说明最后一个是右子节点，则右子节点存在,选出左右节点最大的值
				if (bigger < length){
					if (arry[bigger] < arry[bigger+1]){
						bigger++;
					}
				}
				//将左右节点最大值和父节点比较
				if (arry[k] < arry[bigger]){
					swap(arry,k,bigger);
					//比较后，此时父节点是最大值，那么替换后的另一个节点因为值发生了改变，可能导致他的树需要重新排序。
					//将替换节点作为父节点继续循环遍历，使他的树有序。
					k=bigger;
				}
				//不比父节点大则返回不调整
				else {
					break;
				}
			}
		}
	}
	public void swap(int [] arry,int start,int end){
		arry[start]=arry[start]^arry[end];
		arry[end]=arry[start]^arry[end];
		arry[start]=arry[start]^arry[end];
	}

	//遍历数组
	public static void traverse(int[] arry){
		for (int a : arry){
			System.out.println(a);
		}
	}
}
