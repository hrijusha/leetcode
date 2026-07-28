<h2><a href="https://leetcode.com/problems/house-robber-ii">House Robber II</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are <strong>arranged in a circle.</strong> That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and&nbsp;<b>it will automatically contact the police if two adjacent houses were broken into on the same night</b>.</p>

<p>Given an integer array <code>nums</code> representing the amount of money of each house, return <em>the maximum amount of money you can rob tonight <strong>without alerting the police</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong> You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## Approach: Top-Down Dynamic Programming (Memoization) with Problem Decomposition

This solution solves the "House Robber II" problem, where houses are arranged in a circle (meaning the first and last houses are adjacent). Because you cannot rob both the first and the last house simultaneously, we can break this circular problem down into two separate linear problems.

1. **Problem Decomposition:** 
   We evaluate two distinct scenarios and take the maximum of the two:
   * **Scenario 1:** We rob houses from index `0` to `n - 2` (completely ignoring the last house).
   * **Scenario 2:** We rob houses from index `1` to `n - 1` (completely ignoring the first house).
   * *(Edge Case)*: If there is only $1$ house, the circular constraint doesn't matter, so we just return the value of that single house.
2. **State Definition:** 
   We use a recursive helper function `maxSum` to solve the linear version of the problem. We pass in a `dp` array to cache results, a `start` index representing our lower bound, and an `index` representing the house we are currently evaluating (moving backwards from the end of our range down to `start`).
3. **Base Cases:** 
   * If `index < start`, we've run out of houses in our designated range, so the profit is $0$.
   * If `index == start`, we are at the very first house in our range, so the maximum profit from here is just `nums[start]`.
4. **Memoization & State Transition:** 
   Before computing, we check if `dp[index]` is not `-1` (meaning we've already calculated the max profit up to this house). If not, we make a choice at the current `index`:
   * **Skip it:** Take the maximum profit from the previous house (`index - 1`).
   * **Rob it:** Take the money from the current house (`nums[index]`) plus the max profit from the house two steps back (`index - 2`) to avoid triggering an alarm.
   We store the maximum of these two choices in `dp[index]` and return it.

## Complexity Analysis

* **Time Complexity:** $O(n)$
  Where $n$ is the number of houses. We are effectively solving the standard linear House Robber problem twice. Thanks to memoization, each house's state in both ranges is calculated exactly once. Therefore, the time complexity is $O(n) + O(n)$, which simplifies to $O(n)$.
* **Space Complexity:** $O(n)$
  Where $n$ is the number of houses. We allocate two separate `dp` arrays (`dp0` and `dp1`), each of size $n + 1$, taking $O(n)$ space. Additionally, the recursion call stack will go as deep as $n$ frames before hitting the base cases, requiring another $O(n)$ space. Overall auxiliary space is linear.
