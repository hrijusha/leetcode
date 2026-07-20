<h2><a href="https://leetcode.com/problems/unique-paths">Unique Paths</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>There is a robot on an <code>m x n</code> grid. The robot is initially located at the <strong>top-left corner</strong> (i.e., <code>grid[0][0]</code>). The robot tries to move to the <strong>bottom-right corner</strong> (i.e., <code>grid[m - 1][n - 1]</code>). The robot can only move either down or right at any point in time.</p>

<p>Given the two integers <code>m</code> and <code>n</code>, return <em>the number of possible unique paths that the robot can take to reach the bottom-right corner</em>.</p>

<p>The test cases are generated so that the answer will be less than or equal to <code>2 * 10<sup>9</sup></code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img src="https://assets.leetcode.com/uploads/2018/10/22/robot_maze.png" style="width: 400px; height: 183px;" />
<pre>
<strong>Input:</strong> m = 3, n = 7
<strong>Output:</strong> 28
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> m = 3, n = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -&gt; Down -&gt; Down
2. Down -&gt; Down -&gt; Right
3. Down -&gt; Right -&gt; Down
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
</ul>

## Approach: Top-Down Dynamic Programming (Memoization)

This solution uses a recursive Depth-First Search (DFS) approach with memoization (Top-Down DP) to find the number of unique paths from the top-left to the bottom-right of an $m \times n$ grid.

1. **State Definition:** 
   We define a recursive function `findPaths(m, n)` that returns the total number of unique paths for a grid of size $m \times n$. We use a 2D array `dp` of size `(m+1) x (n+1)` to cache our computed results.
2. **Base Case:** 
   If either `m == 1` or `n == 1`, it means the grid has been reduced to a single row or a single column. There is only $1$ possible path to traverse a straight line, so we return $1$.
3. **Memoization (Caching):** 
   Before performing any recursive calculations, we check if `dp[m][n]` is greater than $0$. If it is, this subproblem has already been solved, and we can return the cached value immediately in order to eliminate redundant recursive calls.
4. **State Transition:** 
   To reach the end of an $m \times n$ grid, you must come from either the cell directly above it or the cell directly to its left. Therefore, the total unique paths for the current grid size is the sum of the paths for a grid with one less row plus a grid with one less column. 
   The recurrence relation is:
   $$dp[m][n] = \text{findPaths}(m - 1, n) + \text{findPaths}(m, n - 1)$$

## Complexity Analysis

* **Time Complexity:** $O(m \cdot n)$
  Because we cache the results in our `dp` array, each subproblem (or grid state) is evaluated exactly once. There are $m \times n$ possible states, so the time complexity drops from an exponential $O(2^{m+n})$ (standard recursion) to linear with respect to the grid area.
* **Space Complexity:** $O(m \cdot n)$
  We allocate a 2D array `dp` of size $(m+1) \times (n+1)$, which takes $O(m \cdot n)$ extra space. Additionally, the maximum depth of the recursion stack will be $m + n$ before hitting the base cases, which takes $O(m + n)$ space. The dominant term is the 2D array, yielding an overall space complexity of $O(m \cdot n)$.
