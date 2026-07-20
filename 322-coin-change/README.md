<h2><a href="https://leetcode.com/problems/coin-change">Coin Change</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>You are given an integer array <code>coins</code> representing coins of different denominations and an integer <code>amount</code> representing a total amount of money.</p>

<p>Return <em>the fewest number of coins that you need to make up that amount</em>. If that amount of money cannot be made up by any combination of the coins, return <code>-1</code>.</p>

<p>You may assume that you have an infinite number of each kind of coin.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> coins = [1,2,5], amount = 11
<strong>Output:</strong> 3
<strong>Explanation:</strong> 11 = 5 + 5 + 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> coins = [2], amount = 3
<strong>Output:</strong> -1
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> coins = [1], amount = 0
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= coins.length &lt;= 12</code></li>
	<li><code>1 &lt;= coins[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>0 &lt;= amount &lt;= 10<sup>4</sup></code></li>
</ul>

## Approach: Dynamic Programming (Bottom-Up)

This solution uses a 1D Dynamic Programming approach to find the minimum number of coins needed to make up the target amount. 

1. **State Definition:** 
   We use an array `dp` of size `amount + 1`. The value at `dp[i]` represents the minimum number of coins required to make up the amount `i`.
2. **Initialization:** 
   We initialize the array with `amount + 1`, which acts as our "infinity" because the maximum possible number of coins needed (if we only had 1-cent coins) is `amount`. Therefore, `amount + 1` is safely out of bounds. We set `dp[0] = 0` because it takes zero coins to make the amount $0$.
3. **State Transition:** 
   We iterate through each `coin` in the given `coins` array. For each coin, we update our `dp` array for all amounts from `coin` up to `amount`. 
   The recurrence relation is: 
   $$dp[i] = \min(dp[i], dp[i - coin] + 1)$$
   This means for any amount `i`, the minimum coins needed is either what we already calculated for `i`, or the number of coins needed for `i - coin` plus $1$ (representing the current coin).
4. **Final Result:** 
   After evaluating all coins and amounts, if `dp[amount]` is still `amount + 1`, it means the target amount cannot be made up by any combination of the coins, so we return `-1`. Otherwise, we return `dp[amount]`.

## Complexity Analysis

* **Time Complexity:** $O(S \cdot n)$
  Where $S$ is the `amount` and $n$ is the number of `coins`. We have a nested loop: the outer loop runs $n$ times (once for each coin), and the inner loop runs up to $S$ times (updating the `dp` array for each amount). 
* **Space Complexity:** $O(S)$
  Where $S$ is the `amount`. We use an extra 1D array `dp` of size `amount + 1` to store the minimum coin counts for each sub-problem.
