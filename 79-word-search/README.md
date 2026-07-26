<h2><a href="https://leetcode.com/problems/word-search">Word Search</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>Given an <code>m x n</code> grid of characters <code>board</code> and a string <code>word</code>, return <code>true</code> <em>if</em> <code>word</code> <em>exists in the grid</em>.</p>

<p>The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/04/word2.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Input:</strong> board = [[&quot;A&quot;,&quot;B&quot;,&quot;C&quot;,&quot;E&quot;],[&quot;S&quot;,&quot;F&quot;,&quot;C&quot;,&quot;S&quot;],[&quot;A&quot;,&quot;D&quot;,&quot;E&quot;,&quot;E&quot;]], word = &quot;ABCCED&quot;
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/04/word-1.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Input:</strong> board = [[&quot;A&quot;,&quot;B&quot;,&quot;C&quot;,&quot;E&quot;],[&quot;S&quot;,&quot;F&quot;,&quot;C&quot;,&quot;S&quot;],[&quot;A&quot;,&quot;D&quot;,&quot;E&quot;,&quot;E&quot;]], word = &quot;SEE&quot;
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/15/word3.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Input:</strong> board = [[&quot;A&quot;,&quot;B&quot;,&quot;C&quot;,&quot;E&quot;],[&quot;S&quot;,&quot;F&quot;,&quot;C&quot;,&quot;S&quot;],[&quot;A&quot;,&quot;D&quot;,&quot;E&quot;,&quot;E&quot;]], word = &quot;ABCB&quot;
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n = board[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 6</code></li>
	<li><code>1 &lt;= word.length &lt;= 15</code></li>
	<li><code>board</code> and <code>word</code> consists of only lowercase and uppercase English letters.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you use search pruning to make your solution faster with a larger <code>board</code>?</p>

## Approach: Depth-First Search (DFS) with Backtracking

This solution uses a Depth-First Search (DFS) to explore potential paths in the grid, coupled with Backtracking to ensure that we do not use the same cell more than once during a single word construction.

1. **Grid Traversal (Finding the Start):** 
   We iterate through every cell in the $m \times n$ matrix. Whenever we encounter a cell that matches the very first character of our target `word`, we initiate a DFS search from that position.
2. **DFS and Base Cases:** 
   Our recursive `dfs` helper function handles the pathfinding. It checks two primary base cases:
   * **Success:** If our current `index` matches the length of the `word`, we have successfully found all characters in sequence. We return `true`.
   * **Failure:** If we move out of the matrix bounds, or if the current cell's character does not match the character we need (`word.charAt(index)`), this path is invalid, so we return `false`.
3. **Backtracking (Mark and Sweep):** 
   To prevent reusing the same matrix cell within the same path, we temporarily alter the current cell's character to a special marker (`'#'`). 
   We then recursively call `dfs` for all four adjacent directions (down, up, right, left) using a logical `||` (OR) operator. This operator short-circuits, meaning if any direction returns `true`, it immediately stops exploring the others and returns `true`.
   After the recursive calls, regardless of success or failure, we **backtrack** by restoring the cell's original character (`temp`) so it remains available for other potential paths starting elsewhere.
4. **Final Result:** 
   If any of the initial DFS calls from the main loops return `true`, we immediately return `true`. If all loops finish and no valid path was found, we return `false`.

## Complexity Analysis

* **Time Complexity:** $O(m \cdot n \cdot 3^L)$
  Where $m$ is the number of rows, $n$ is the number of columns, and $L$ is the length of the `word`. The outer loops force us to potentially start a DFS from every single cell ($m \cdot n$ times). Within the DFS, we explore up to 4 directions initially. However, because we mark the cell we just came from, we only have 3 valid directions to explore for each subsequent step. This creates a recursive branching factor of roughly $3^L$. Therefore, the worst-case time complexity is $O(m \cdot n \cdot 3^L)$.
* **Space Complexity:** $O(L)$
  Where $L$ is the length of the `word`. We are modifying the grid in-place rather than allocating a separate `visited` boolean matrix. The only extra memory consumed is by the recursive call stack, which will grow as deep as the length of the word before hitting a base case.
