<h2><a href="https://leetcode.com/problems/subtree-of-another-tree">Subtree of Another Tree</a></h2> <img src='https://img.shields.io/badge/Difficulty-Easy-brightgreen' alt='Difficulty: Easy' /><hr><p>Given the roots of two binary trees <code>root</code> and <code>subRoot</code>, return <code>true</code> if there is a subtree of <code>root</code> with the same structure and node values of<code> subRoot</code> and <code>false</code> otherwise.</p>

<p>A subtree of a binary tree <code>tree</code> is a tree that consists of a node in <code>tree</code> and all of this node&#39;s descendants. The tree <code>tree</code> could also be considered as a subtree of itself.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/04/28/subtree1-tree.jpg" style="width: 532px; height: 400px;" />
<pre>
<strong>Input:</strong> root = [3,4,5,1,2], subRoot = [4,1,2]
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/04/28/subtree2-tree.jpg" style="width: 502px; height: 458px;" />
<pre>
<strong>Input:</strong> root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the <code>root</code> tree is in the range <code>[1, 2000]</code>.</li>
	<li>The number of nodes in the <code>subRoot</code> tree is in the range <code>[1, 1000]</code>.</li>
	<li><code>-10<sup>4</sup> &lt;= root.val &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= subRoot.val &lt;= 10<sup>4</sup></code></li>
</ul>

## 🧠 Approach: Double Depth-First Search (DFS)

To determine if `subRoot` is a subtree of `root`, we can treat every single node in the main `root` tree as a potential starting point for a match. 

This approach uses two separate recursive functions:
1. **The Traversal Function (`isSubtree`)**: This acts as our outer loop. It traverses through the main `root` tree. For every node it visits, it asks: *"Are the trees identical starting from here?"* If not, it moves on to check the left and right children.
2. **The Matching Function (`isSameTree`)**: This acts as our inner loop. Given two nodes, it recursively verifies if both the structure and the values of the trees originating from those nodes are completely identical.

### Algorithm
1. Check base cases in `isSubtree`: 
   - If the main `root` becomes `null`, we've reached the end without a match, so return `false`.
   - If `subRoot` is `null`, an empty tree is technically a subtree of any tree, so return `true`.
2. Check if the current `root` and `subRoot` are identical using the `isSameTree` helper.
3. If they are not identical, recursively call `isSubtree` on the left and right children of `root`. If *either* side finds a match, return `true`.

## ⏱️ Complexity Analysis

* **Time Complexity:** `O(N * M)`
  * Where `N` is the number of nodes in the `root` tree, and `M` is the number of nodes in the `subRoot` tree.
  * In the worst-case scenario (e.g., all nodes have the same value but the structure only mismatches at the very bottom), we have to call `isSameTree` for every single node in `root`. Each `isSameTree` call compares up to `M` nodes. 
* **Space Complexity:** `O(H_R)`
  * Where `H_R` is the height of the `root` tree. 
  * This represents the maximum depth of the recursive call stack. In the worst case (a completely skewed tree), the space complexity would be `O(N)`. In a balanced tree, it would be `O(log N)`. 
  * *(Note: `isSameTree` adds up to `O(H_S)` space to the stack, but since `H_R >= H_S` for a valid subtree, the overall space complexity is bounded by the height of the main tree).*
