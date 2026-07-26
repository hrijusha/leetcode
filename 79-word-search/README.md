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

This solution uses Depth-First Search (DFS) to explore all possible paths in the grid, combined with Backtracking to ensure we don't use the same cell more than once in a single path.

1. **Grid Traversal (Finding the Start):** 
   We iterate through every single cell in the $m \times n$ matrix. When we find a cell that matches the first character of our target `word`, we launch a DFS search from that cell to try and construct the rest of the word.
2. **DFS and Base Cases:** 
   Our recursive `dfs` function checks two main base cases:
   * **Success:** If our current `index` equals the length of the `word`, we have successfully matched every character. We return `true`.
   * **Failure:** If we go out of bounds of the matrix, OR if the current cell does not match the character we are currently looking for (`word.charAt(index)`), we hit a dead end and return `false`.
3. **Backtracking (Mark and Sweep):** 
   To prevent reusing the same cell within the same word construction, we temporarily mark the current cell as visited by changing its value to a special character (`'*'`). 
   We then recursively call `dfs` for all four adjacent directions (up, down, left, right). 
   If none of the paths yield a successful word, we **backtrack** by restoring the cell's original character (`temp`) so it can be potentially used by a different starting path.
4. **Final Result:** 
   If any DFS path returns `true`, that `true` propagates all the way up, and the main function returns `true`. If we check every possible starting position and none work, we return `false`.

## Complexity Analysis

* **Time Complexity:** $O(m \cdot n \cdot 3^L)$
  Where $m$ is the number of rows, $n$ is the number of columns, and $L$ is the length of the `word`. We potentially initiate a DFS from every cell ($m \cdot n$ times). During the DFS, from each cell we can explore up to 4 directions. However, because we mark the cell we just came from as visited, we effectively only have 3 choices for every subsequent step. This gives a recursive branching factor of $3^L$. Thus, the loose upper bound is $O(m \cdot n \cdot 4^L)$, but $O(m \cdot n \cdot 3^L)$ is a tighter and more accurate worst-case time complexity.
* **Space Complexity:** $O(L)$
  Where $L$ is the length of the `word`. We modify the matrix in-place, so we don't need an extra `visited` matrix. The only extra space used is the recursive call stack. The maximum depth of the recursion stack will be exactly $L$ before it either finds the word or backtracks.
