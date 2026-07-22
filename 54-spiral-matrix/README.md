<h2><a href="https://leetcode.com/problems/spiral-matrix">Spiral Matrix</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>Given an <code>m x n</code> <code>matrix</code>, return <em>all elements of the</em> <code>matrix</code> <em>in spiral order</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/spiral1.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>Input:</strong> matrix = [[1,2,3],[4,5,6],[7,8,9]]
<strong>Output:</strong> [1,2,3,6,9,8,7,4,5]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/spiral.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Input:</strong> matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
<strong>Output:</strong> [1,2,3,4,8,12,11,10,9,5,6,7]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10</code></li>
	<li><code>-100 &lt;= matrix[i][j] &lt;= 100</code></li>
</ul>

## Approach: Boundary Shrinking (Simulation)

This solution simulates the spiral traversal by maintaining four boundary pointers (`top`, `bottom`, `left`, `right`) that define the current outermost layer of the matrix. As we traverse the perimeter of the current layer, we "shrink" the boundaries inward.

1. **Boundary Initialization:** 
   We define four pointers to represent the boundaries of the unvisited portion of the matrix:
   * `top = 0` (top row)
   * `bottom = m - 1` (bottom row)
   * `left = 0` (left column)
   * `right = n - 1` (right column)
2. **Spiral Traversal (Layer by Layer):** 
   We use a `while` loop that continues as long as our boundaries haven't crossed (`top <= bottom` and `left <= right`). Inside the loop, we perform four sequential traversals to peel off the current outer layer:
   * **Traverse Right:** Move from `left` to `right` along the `top` row. Once done, we increment `top` to shrink the top boundary downward.
   * **Traverse Down:** Move from `top` to `bottom` along the `right` column. Once done, we decrement `right` to shrink the right boundary leftward.
3. **Preventing Duplicate Traversal:** 
   Before proceeding with the final two directions, we must check if our boundaries have crossed during the first two steps. This is crucial for rectangular matrices (where $m \neq n$) to avoid traversing the same row or column twice.
   * **Traverse Left:** If `top <= bottom`, we move from `right` to `left` along the `bottom` row, then decrement `bottom`.
   * **Traverse Up:** If `left <= right`, we move from `bottom` to `top` along the `left` column, then increment `left`.
4. **Final Result:** 
   Once the boundaries cross, all elements have been added to the `list` in spiral order, and we return it.

## Complexity Analysis

* **Time Complexity:** $O(m \cdot n)$
  Where $m$ is the number of rows and $n$ is the number of columns. We visit every single element in the $m \times n$ matrix exactly once, doing $O(1)$ work per element.
* **Space Complexity:** $O(1)$ auxiliary space
  We only use four integer pointers (`top`, `bottom`, `left`, `right`) to keep track of our position, requiring constant extra space. *(Note: The space required for the output `List` to store the result is $O(m \cdot n)$, but output space is typically excluded from auxiliary space complexity calculations).*
