<h2><a href="https://leetcode.com/problems/set-matrix-zeroes">Set Matrix Zeroes</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>Given an <code>m x n</code> integer matrix <code>matrix</code>, if an element is <code>0</code>, set its entire row and column to <code>0</code>&#39;s.</p>

<p>You must do it <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank">in place</a>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/08/17/mat1.jpg" style="width: 450px; height: 169px;" />
<pre>
<strong>Input:</strong> matrix = [[1,1,1],[1,0,1],[1,1,1]]
<strong>Output:</strong> [[1,0,1],[0,0,0],[1,0,1]]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/08/17/mat2.jpg" style="width: 450px; height: 137px;" />
<pre>
<strong>Input:</strong> matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
<strong>Output:</strong> [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[0].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>-2<sup>31</sup> &lt;= matrix[i][j] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong></p>

<ul>
	<li>A straightforward solution using <code>O(mn)</code> space is probably a bad idea.</li>
	<li>A simple improvement uses <code>O(m + n)</code> space, but still not the best solution.</li>
	<li>Could you devise a constant space solution?</li>
</ul>

## Approach: Two-Pass Array Tracking

This solution uses a two-pass approach with auxiliary arrays to track which rows and columns need to be updated. It safely modifies the matrix in place without cascading zeroes (where setting a zero in the first pass causes unintended zeroes later in the same pass).

1. **State Definition:** 
   We initialize two boolean arrays: `rowTracker` of size `m` (number of rows) and `colTracker` of size `n` (number of columns). These will flag which specific rows and columns contain at least one `0`.
2. **First Pass (Detection):** 
   We iterate through every cell in the $m \times n$ matrix. Whenever we encounter a `0` at `matrix[i][j]`, we flag both the $i$-th row and the $j$-th column by setting `rowTracker[i] = true` and `colTracker[j] = true`.
3. **Second Pass (Modification):** 
   We iterate through the entire matrix one more time. For each cell at `(i, j)`, we check our tracker arrays. If either `rowTracker[i]` or `colTracker[j]` is `true`, we overwrite the current cell's value with `0`.

## Complexity Analysis

* **Time Complexity:** $O(m \cdot n)$
  Where $m$ is the number of rows and $n$ is the number of columns. We traverse the entire matrix exactly twice. The total number of operations is roughly $2 \cdot m \cdot n$, which simplifies asymptotically to $O(m \cdot n)$.
* **Space Complexity:** $O(m + n)$
  We allocate two separate boolean arrays to keep track of the rows and columns that need to be zeroed out. The sizes of these arrays are $m$ and $n$ respectively, resulting in a space complexity of $O(m + n)$. *(Note: This can be further optimized to $O(1)$ space by using the first row and first column of the matrix itself as the trackers, but this approach is much easier to read and reason about).*
