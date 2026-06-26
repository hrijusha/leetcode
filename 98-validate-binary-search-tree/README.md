<h2><a href="https://leetcode.com/problems/validate-binary-search-tree">Validate Binary Search Tree</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>Given the <code>root</code> of a binary tree, <em>determine if it is a valid binary search tree (BST)</em>.</p>

<p>A <strong>valid BST</strong> is defined as follows:</p>

<ul>
	<li>The left <span data-keyword="subtree">subtree</span> of a node contains only nodes with keys&nbsp;<strong>strictly less than</strong> the node&#39;s key.</li>
	<li>The right subtree of a node contains only nodes with keys <strong>strictly greater than</strong> the node&#39;s key.</li>
	<li>Both the left and right subtrees must also be binary search trees.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/12/01/tree1.jpg" style="width: 302px; height: 182px;" />
<pre>
<strong>Input:</strong> root = [2,1,3]
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/12/01/tree2.jpg" style="width: 422px; height: 292px;" />
<pre>
<strong>Input:</strong> root = [5,1,4,null,null,3,6]
<strong>Output:</strong> false
<strong>Explanation:</strong> The root node&#39;s value is 5 but its right child&#39;s value is 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-2<sup>31</sup> &lt;= Node.val &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## 🧠 Approach: Recursive Inorder Traversal

The core property of a Binary Search Tree (BST) is that an **Inorder Traversal** (Left $\rightarrow$ Root $\rightarrow$ Right) will always visit the nodes in strictly ascending order. 

Instead of passing maximum and minimum boundaries down the tree, we can leverage this property by traversing the tree in-order and simply keeping track of the previously visited node. 

### Algorithm
1. Perform a standard Inorder DFS traversal.
2. Maintain a `prev` reference to store the node we just visited.
3. Before traversing to the right child, compare the current node's value with `prev.val`.
4. If `prev` is not null and the current value is **less than or equal to** the previous value, the ascending order is broken, and the tree is invalid.
5. If the entire tree is traversed without breaking this rule, it is a valid BST.

*Note: The `prev` state is reset to `null` at the start of the public method to ensure thread safety and prevent cross-contamination if the method is called multiple times on the same instance.*

## ⏱️ Complexity Analysis

* **Time Complexity:** `O(N)`
  * Where `N` is the total number of nodes in the tree. In the worst-case scenario (a valid BST), we must visit every single node exactly once to confirm its validity.
  * *Best Case:* `O(1)` if the root is null, or it short-circuits early on the left side of the tree.
* **Space Complexity:** `O(H)`
  * Where `H` is the height of the tree, which represents the maximum depth of the recursive call stack. 
  * *Worst Case:* `O(N)` for a completely skewed (unbalanced) tree, resembling a linked list.
  * *Best Case:* `O(log N)` for a perfectly balanced binary search tree.
