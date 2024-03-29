## 动态规划
动态规划问题的一般形式就是求最值，求解动态规划的核心问题是穷举。
因为要求最值，肯定要把所有可行的答案穷举出来，然后在其中找最值呗。

1.首先，动态规划的穷举有点特别，因为这类问题存在「重叠子问题」，
如果暴力穷举的话效率会极其低下，
所以需要「备忘录」或者「DP table」来优化穷举过程，避免不必要的计算。

2.动态规划问题一定会具备「最优子结构」，才能通过子问题的最值得到原问题的最值

3.虽然动态规划的核心思想就是穷举求最值，但是问题可以千变万化，
只有列出正确的「状态转移方程」，才能正确地穷举

4.明确 base case -> 明确「状态」-> 明确「选择」 -> 定义 dp 数组/函数的含义。
# 初始化 base case (即初始化DPTable的一些初始值)
dp[0][0][...] = base
# 进行状态转移 (进行for循环，然后选择最大值的公式，取最大值)
for 状态1 in 状态1的所有取值：
    for 状态2 in 状态2的所有取值：
        for ...
            # 选择
            dp[状态1][状态2][...] = 求最值(选择1，选择2...)
            
## DP一般都可以用以下三张方式替换
f(20)=f(19)+f(18)
1.暴力递归(f18等有重复计算)
2.带备忘录的递归解法(一般使用一个数组充当这个「备忘录」记录下计算的值防止重复计算，
当然你也可以使用哈希表（字典），思想都是一样的。)
3、dp 数组的迭代解法
(基于备忘录变化来的)

## 状态转移方程

f(n)={1 , n=1,2
     {f(n-1)+f(n-2) , n >2
为啥叫「状态转移方程」？其实就是为了听起来高端。
你把 f(n) 想做一个状态 n，这个状态 n 是由状态 n - 1 和状态 n - 2 相加转移而来，
这就叫状态转移，仅此而已。
你会发现，上面的几种解法中的所有操作，
例如 return f(n - 1) + f(n - 2)，dp[i] = dp[i - 1] + dp[i - 2]

## 状态压缩
根据斐波那契数列的状态转移方程，当前状态只和之前的两个状态有关，
其实并不需要那么长的一个 DP table 来存储所有的状态，只要想办法存储之前的两个状态就行了。
这个技巧就是所谓的「状态压缩」，如果我们发现每次状态转移只需要 DP table 中的一部分，
那么可以尝试用状态压缩来缩小 DP table 的大小，只记录必要的数据，
上述例子就相当于把DP table 的大小从 n 缩小到 2。