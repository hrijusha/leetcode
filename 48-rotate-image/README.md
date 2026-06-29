<h2><a href="https://leetcode.com/problems/rotate-image">Rotate Image</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>You are given an <code>n x n</code> 2D <code>matrix</code> representing an image, rotate the image by <strong>90</strong> degrees (clockwise).</p>

<p>You have to rotate the image <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank"><strong>in-place</strong></a>, which means you have to modify the input 2D matrix directly. <strong>DO NOT</strong> allocate another 2D matrix and do the rotation.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/08/28/mat1.jpg" style="width: 500px; height: 188px;" />
<pre>
<strong>Input:</strong> matrix = [[1,2,3],[4,5,6],[7,8,9]]
<strong>Output:</strong> [[7,4,1],[8,5,2],[9,6,3]]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/08/28/mat2.jpg" style="width: 500px; height: 201px;" />
<pre>
<strong>Input:</strong> matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
<strong>Output:</strong> [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == matrix.length == matrix[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 20</code></li>
	<li><code>-1000 &lt;= matrix[i][j] &lt;= 1000</code></li>
</ul>

## Approach

To rotate an $N \times N$ matrix by 90 degrees clockwise **in-place** (without allocating a new matrix), we can use a highly elegant two-step mathematical trick:

1. **Transpose the Matrix:** We convert all rows into columns (and vice versa) by swapping `matrix[i][j]` with `matrix[j][i]`. 
   *Note:* The inner loop starts at `j = i` (the diagonal) to ensure we only swap elements in the upper triangle with the lower triangle. Starting at `j = 0` would cause a double-swap, reverting the matrix to its original state.
2. **Reverse Each Row:** Once the matrix is transposed, the elements are in the correct rows but in the reverse order. We iterate through each row and swap elements from the left side with the corresponding elements on the right side (`matrix[i][j]` with `matrix[i][n - 1 - j]`), stopping at the middle column.

## Complexity Analysis

* **Time Complexity:** $O(N^2)$
  * Transposing the matrix touches roughly half the elements, taking $O(N^2)$ time.
  * Reversing the rows also touches half the elements, taking $O(N^2)$ time.
  * Dropping the constants, the total time complexity scales linearly with the total number of cells in the matrix (where total cells $= N \times N$).
* **Space Complexity:** $O(1)$
  * The operation is performed strictly in-place. No secondary matrices or dynamically sized data structures are allocated. The only extra space used is for a few primitive integer variables (`temp`, `i`, `j`, `n`).
