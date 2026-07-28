<h2><a href="https://leetcode.com/problems/house-robber">House Robber</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and <b>it will automatically contact the police if two adjacent houses were broken into on the same night</b>.</p>

<p>Given an integer array <code>nums</code> representing the amount of money of each house, return <em>the maximum amount of money you can rob tonight <b>without alerting the police</b></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,7,9,3,1]
<strong>Output:</strong> 12
<strong>Explanation:</strong> Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 400</code></li>
</ul>

## Approach: Top-Down Dynamic Programming (Memoization)

This solution uses a recursive Depth-First Search (DFS) approach with memoization to solve the classic "House Robber" problem. The core logic relies on deciding whether to rob or skip the current house to maximize the total haul without triggering the adjacent-house alarm.

1. **State Definition:** 
   We define a recursive helper function `maxSum(nums, index, dp)` that returns the maximum amount of money you can rob from the first house up to the house at `index`. We use a 1D array `dp` of size `n + 1` to cache our calculated results, initialized with `-1`.
2. **Base Cases:** 
   * If `index < 0`, there are no houses left to consider, so the profit is $0$.
   * If `index == 0`, we are at the very first house. With no previous houses to worry about, the maximum profit is simply to rob this house, so we return `nums[0]`.
3. **Memoization (Caching):** 
   Before performing any recursive branching, we check if `dp[index]` is not `-1`. If it has a value, it means we have already computed the maximum profit up to this specific house, and we can return the cached value immediately to avoid redundant calculations.
4. **State Transition:** 
   At every house (represented by `index`), we have exactly two choices:
   * **Pick (Rob it):** We take the money from the current house (`nums[index]`). Because we triggered this house's alarm, we cannot rob the immediately preceding house, so we must add the max profit from two houses back (`index - 2`).
   * **Not Pick (Skip it):** We skip the current house, meaning our maximum profit is just whatever the maximum profit was up to the previous house (`index - 1`).
   
   The recurrence relation is:
   $$dp[index] = \max(nums[index] + \text{maxSum}(index - 2), \text{maxSum}(index - 1))$$
   We store this maximum value in `dp[index]` and return it.

## Complexity Analysis

* **Time Complexity:** $O(n)$
  Where $n$ is the length of the `nums` array. Because we cache the results in the `dp` array, the maximum profit for each index from $0$ to $n-1$ is calculated exactly once. This prunes the recursion tree, reducing the time complexity from an exponential $O(2^n)$ down to linear time.
* **Space Complexity:** $O(n)$
  We allocate a 1D `dp` array of size $n$, which takes $O(n)$ space. Additionally, the maximum depth of the recursive call stack will be $n$ frames before hitting the base cases, requiring another $O(n)$ space. Overall auxiliary space complexity is $O(n)$.
