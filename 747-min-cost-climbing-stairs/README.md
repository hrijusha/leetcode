<h2><a href="https://leetcode.com/problems/min-cost-climbing-stairs">Min Cost Climbing Stairs</a></h2> <img src='https://img.shields.io/badge/Difficulty-Easy-brightgreen' alt='Difficulty: Easy' /><hr><p>You are given an integer array <code>cost</code> where <code>cost[i]</code> is the cost of <code>i<sup>th</sup></code> step on a staircase. Once you pay the cost, you can either climb one or two steps.</p>

<p>You can either start from the step with index <code>0</code>, or the step with index <code>1</code>.</p>

<p>Return <em>the minimum cost to reach the top of the floor</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> cost = [10,<u>15</u>,20]
<strong>Output:</strong> 15
<strong>Explanation:</strong> You will start at index 1.
- Pay 15 and climb two steps to reach the top.
The total cost is 15.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> cost = [<u>1</u>,100,<u>1</u>,1,<u>1</u>,100,<u>1</u>,<u>1</u>,100,<u>1</u>]
<strong>Output:</strong> 6
<strong>Explanation:</strong> You will start at index 0.
- Pay 1 and climb two steps to reach index 2.
- Pay 1 and climb two steps to reach index 4.
- Pay 1 and climb two steps to reach index 6.
- Pay 1 and climb one step to reach index 7.
- Pay 1 and climb two steps to reach index 9.
- Pay 1 and climb one step to reach the top.
The total cost is 6.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= cost.length &lt;= 1000</code></li>
	<li><code>0 &lt;= cost[i] &lt;= 999</code></li>
</ul>

## Approach: Bottom-Up Dynamic Programming (In-Place)

This solution uses a highly space-optimized Dynamic Programming approach. Instead of creating a separate `dp` array, it modifies the input `cost` array in place to store the cumulative minimum cost to reach each step.

1. **State Definition:** 
   We repurpose the given `cost` array. After processing, the value at `cost[i]` will represent the total minimum cost required to reach the $i$-th step and then jump from it.
2. **Base Cases:** 
   The problem allows starting from either index $0$ or index $1$. Therefore, the initial values at `cost[0]` and `cost[1]` already represent the minimum cumulative cost to jump from those starting steps. We start our loop from index $2$.
3. **State Transition:** 
   To step off of stair `i`, you must first reach it. You can arrive at stair `i` from either one step below (`i - 1`) or two steps below (`i - 2`). To minimize the total cost, we add the current step's cost to the minimum of the two preceding cumulative costs.
   The recurrence relation applied in-place is:
   $$cost[i] = cost[i] + \min(cost[i - 1], cost[i - 2])$$
4. **Final Result:** 
   The target "top of the floor" is conceptually one step beyond the end of the array (at index `n`). You can reach the top by jumping from either the last step (`n - 1`) or the second-to-last step (`n - 2`). We return the minimum of these two final cumulative costs.

## Complexity Analysis

* **Time Complexity:** $O(n)$
  Where $n$ is the length of the `cost` array. We iterate through the array exactly once in a single loop from index $2$ to $n - 1$.
* **Space Complexity:** $O(1)$ auxiliary space
  We do not allocate any extra arrays or use a recursion stack. By modifying the input `cost` array in-place, we achieve constant extra space complexity. *(Note: While highly optimal for algorithmic challenges, mutating input parameters is something to be mindful of in production environments where the original data might still be needed).*

```java
//Backward iteration java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[n] = 0;
        dp[n - 1] = cost[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            dp[i] = cost[i] + Math.min(dp[i + 1], dp[i + 2]);
        }
        return Math.min(dp[0], dp[1]);
    }
}

//Recursive solution
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        return Math.min(climb(cost, 0), climb(cost, 1));
    }

    private int climb(int[] cost, int i) {
        if (i >= cost.length) return 0;
        
        return cost[i] + Math.min(climb(cost, i + 1), climb(cost, i + 2));
    }
}
```
### Solution 1: Dynamic Programming (Backward Iteration)

This solution uses a Bottom-Up Dynamic Programming approach, but instead of calculating the cost from the bottom up to the top, it calculates the cost from the top down to the bottom.

1. **State Definition:** 
   We create a `dp` array of size `n + 1`. The value at `dp[i]` represents the minimum cost required to reach the very top of the stairs *starting from* step `i`.
2. **Base Cases:** 
   * `dp[n] = 0`: This is the top of the floor. If you are already here, the cost to reach it is $0$.
   * `dp[n - 1] = cost[n - 1]`: From the last step, your only option to reach the top is to jump $1$ step, which costs `cost[n - 1]`.
3. **State Transition:** 
   We iterate backwards from the second-to-last step (`n - 2`) down to $0$. From any step `i`, you must pay the cost of the current step, and then you can choose to jump either $1$ step (to `i + 1`) or $2$ steps (to `i + 2`). We take the path that costs less.
   The recurrence relation is:
   $$dp[i] = cost[i] + \min(dp[i + 1], dp[i + 2])$$
4. **Final Result:** 
   Since we can start our climb from either step $0$ or step $1$, the minimum total cost will be the minimum of `dp[0]` and `dp[1]`.

#### Complexity Analysis (Backward Iteration)
* **Time Complexity:** $O(n)$
  Where $n$ is the length of the `cost` array. We iterate through the array exactly once in reverse order.
* **Space Complexity:** $O(n)$
  We allocate a new 1D array `dp` of size `n + 1` to store the computed costs for each step.

---

### Solution 2: Pure Recursion (Brute Force)

This solution uses a basic Depth-First Search (DFS) recursive approach to explore all possible paths to the top of the stairs. 

1. **State Definition:** 
   We define a recursive helper function `climb(cost, i)` that calculates the total cost to reach the top of the floor starting from index `i`.
2. **Base Case:** 
   If our current index `i` is greater than or equal to the length of the array (`i >= cost.length`), we have successfully reached or cleared the top of the floor. No further steps are needed, so the cost from here is $0$.
3. **State Transition:** 
   If we are on a valid step, we must pay `cost[i]`. From there, we branch into two separate recursive calls: one representing a $1$-step jump to `i + 1` and another representing a $2$-step jump to `i + 2`. We add the minimum of these two recursive calls to our current cost.
4. **Final Result:** 
   Because we are allowed to start at either step $0$ or step $1$, we call our recursive function for both starting points and return the minimum of the two results.

#### Complexity Analysis (Pure Recursion)
* **Time Complexity:** $O(2^n)$
  Where $n$ is the length of the `cost` array. At each step, the recursion branches into two separate paths. Because we do not use memoization to cache results, we end up recalculating the exact same sub-problems repeatedly, leading to an exponential time complexity. *(Note: This solution will likely result in a Time Limit Exceeded (TLE) error on LeetCode for larger inputs).*
* **Space Complexity:** $O(n)$
  While no auxiliary arrays are created, the recursive call stack will go as deep as $n$ frames before hitting the base case, requiring linear space.
