package com.ourgut.likou.DP;

/**
 * @author ganxinming
 * @createDate 2021/9/15
 * @description 188. 买卖股票的最佳时机 IV
 *
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *示例 1：
 *
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2：
 *
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *
 *
 * 思考过程：
1.状态转移方程：i表示天数，k表示交易次数(每次买入，k才-1，卖出k值是不变的)，0，1表示手上是否持有股票  最终dp[i][k][0]表示手上的钱

dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])//因为是卖出股票，所以回血prices[i]
dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])//因为是买入，所以需要付出 - prices[i]

2.初始化base
 因为会出现 i-1=-1的情况，对于这两种情况

dp[i][j][0]=0;//手上还没有股票，所以是0元
dp[i][j][1]=-prices[i];//此时持有了股票，所以负股票数
 */
public class Test2 {
	public int maxProfit(int k, int[] prices) {
		if(prices.length  == 0){
			return 0;
		}
		int[][][] dp=new int[prices.length][k+1][2];
		for(int i=0;i<prices.length;i++){
			for(int j=k;j>=1;j--){
				if(i-1 == -1){
					dp[i][j][0]=0;//手上还没有股票，所以是0元
					dp[i][j][1]=-prices[i];//此时持有了股票，所以负股票数
					continue;
				}
				dp[i][j][0]=max(dp[i-1][j][1]+prices[i],dp[i-1][j][0]);
				dp[i][j][1]=max(dp[i-1][j][1],dp[i-1][j-1][0]-prices[i]);
			}
		}
		return dp[prices.length-1][k][0];
	}
	public int max(int a,int b){
		return Math.max(a,b);
	}
}
