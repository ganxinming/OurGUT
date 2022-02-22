package com.ourgut.likou.DP;

/**
 * @author ganxinming
 * @createDate 2021/9/15
 * @description 122. 买卖股票的最佳时机 II
 * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: prices = [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 *
 * 输入: prices = [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 *
 * 解析：
 * 因为k接近无限，那么，k-1和k可以直接去除了
 */
public class Test3 {
	public int maxProfit(int[] prices) {
		int[][] dp=new int[prices.length][2];
		for(int i=0;i<prices.length;i++){
			if(i -1 ==  -1){
				dp[i][0]=0;
				dp[i][1]=-prices[i];
				continue;
			}
			dp[i][0]=max(dp[i-1][0],dp[i-1][1]+prices[i]);
			dp[i][1]=max(dp[i-1][1],dp[i-2][0]-prices[i]);
		}
		return dp[prices.length-1][0];
	}
	public int max(int a,int b){
		return Math.max(a,b);
	}
}
