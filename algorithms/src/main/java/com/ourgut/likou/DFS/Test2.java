package com.ourgut.likou.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ganxinming
 * @createDate 2021/8/30
 * @description 51. N 皇后
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 *
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
输入：n = 4
输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
解释：如上图所示，4 皇后问题存在两个不同的解法。
 */
public class Test2 {

	List<List<String>> listAll=new ArrayList<>();

	public void solveNQueens(int n) {
		//初始化棋盘
		List<char[]> listc=new ArrayList<>();
		for(int i=0;i<n;i++){
			char[] ch=new char[n];
			for(int j=0;j<n;j++){
				ch[j]='.';
			}
			listc.add(ch);
		}
		//开始遍历
		DFS(n,listc,0);
		//转成字符串
		for(List<String> list: listAll){
			for(String ch:list){
				System.out.println(ch);
			}
			System.out.println("=====");
		}
	}

	/**
	 * 进行深度遍历
	 * @param n
	 * @param list
	 * @param p
	 */
	public void DFS(int n ,List<char[]> list,int p){
		//符合条件添加
		if(p == n){
			List<String> lists=new ArrayList<>();
			for(char[] c:list){
				lists.add(String.valueOf(c));
			}
			listAll.add(lists);
			return;
		}
		//遍历排
		for(int i=0;i<n;i++){
			//判断是否有效
			if(isVaild(list,i,p)){
				//获取当前排
				char[] ch= list.get(p);
				//设置Q
				ch[i]='Q';
				//设置下一个Q
				DFS(n,list,p+1);
				//恢复
				ch[i]='.';
			}
		}


	}

	/**
	 * 判断是否有效，只需判断同列，左右上角是否有Q即可
	 * 因为本身就是按排来进行遍历的，所以不需判断排有Q
	 * @param list 整个棋盘，用row表示到第几排放Q了
	 * @param col 遍历列的位置
	 * @param row 当前第几排，需要放Q的那排，但是还没放
	 * @return
	 */
	public boolean isVaild(List<char[]> list,int col,int row){

		//判断列不存在Q
		for(int i=0;i<row;i++){
			char[] ch= list.get(i);
			if(ch[col] == 'Q'){
				return false;
			}
		}
		//判断左上角不存在Q
		int tmp1=1;
		for(int i=row-1;i>=0;i--){
			char[] ch= list.get(i);
			if((col-tmp1) >= 0){
				if(ch[col-tmp1] == 'Q'){
					return false;
				}
				tmp1++;
			}else{
				break;
			}
		}
		//判断右上角不存在Q
		int tmp2=1;
		for(int i=row-1;i>=0;i--){
			char[] ch= list.get(i);
			if((col+tmp2) < ch.length){
				if(ch[col+tmp2] == 'Q'){
					return false;
				}
				tmp2++;
			}else{
				break;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Test2 test2=new Test2();
		test2.solveNQueens(4);
	}
}
