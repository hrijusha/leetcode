<h2><a href="https://leetcode.com/problems/climbing-stairs">Climbing Stairs</a></h2> <img src='https://img.shields.io/badge/Difficulty-Easy-brightgreen' alt='Difficulty: Easy' /><hr><p>You are climbing a staircase. It takes <code>n</code> steps to reach the top.</p>

<p>Each time you can either climb <code>1</code> or <code>2</code> steps. In how many distinct ways can you climb to the top?</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 45</code></li>
</ul>

## Approach: Top-Down Dynamic Programming (Memoization)

This solution uses a recursive approach combined with memoization (Top-Down DP) to find the number of distinct ways to climb to the top of a staircase with `n` steps.

1. **State Definition:** 
   We define a recursive helper function `climb(n)` that returns the total number of unique ways to reach step `n`. We use a 1D array `dp` of size `n + 1` to cache our calculated results.
2. **Base Cases:** 
   * If `n < 0`, it means we took a step that bypassed the bottom of the stairs. This is an invalid path, so we return 0.
   * If `n == 0`, it means we are exactly at the starting point. There is exactly 1 way to be at the start (by taking zero steps), so we return 1.
3. **Memoization (Caching):** 
   Before making any recursive calls, we check if `dp[n] > 0`. If so, we have already computed the number of ways to reach step `n`, and we return the cached value directly to avoid redundant calculations.
4. **State Transition:** 
   Because you can only climb 1 or 2 steps at a time, the number of ways to reach step `n` is the sum of the ways to reach the step right below it (`n - 1`) and the step two levels below it (`n - 2`).
   The recurrence relation is:
   $$dp[n] = \text{climb}(n - 1) + \text{climb}(n - 2)$$

## Complexity Analysis

* **Time Complexity:** O(n)
  Because we cache the results in the `dp` array, the number of ways to reach each step from $1$ to $n$ is calculated exactly once. The recursion tree is pruned, reducing the time complexity from an exponential $O(2^n)$ down to linear time.
* **Space Complexity:** O(n)
  We use an extra 1D array `dp` of size `n + 1` which takes O(n) space. Additionally, the maximum depth of the recursive call stack will be `n` before hitting the base cases, which also requires O(n) space. The overall space complexity is O(n).
