<h2><a href="https://leetcode.com/problems/binary-tree-maximum-path-sum">Binary Tree Maximum Path Sum</a></h2> <img src='https://img.shields.io/badge/Difficulty-Hard-red' alt='Difficulty: Hard' /><hr><p>A <strong>path</strong> in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence <strong>at most once</strong>. Note that the path does not need to pass through the root.</p>

<p>The <strong>path sum</strong> of a path is the sum of the node&#39;s values in the path.</p>

<p>Given the <code>root</code> of a binary tree, return <em>the maximum <strong>path sum</strong> of any <strong>non-empty</strong> path</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/13/exx1.jpg" style="width: 322px; height: 182px;" />
<pre>
<strong>Input:</strong> root = [1,2,3]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The optimal path is 2 -&gt; 1 -&gt; 3 with a path sum of 2 + 1 + 3 = 6.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/13/exx2.jpg" />
<pre>
<strong>Input:</strong> root = [-10,9,20,null,null,15,7]
<strong>Output:</strong> 42
<strong>Explanation:</strong> The optimal path is 15 -&gt; 20 -&gt; 7 with a path sum of 15 + 20 + 7 = 42.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 3 * 10<sup>4</sup>]</code>.</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
</ul>

## Approach: Depth-First Search (Post-Order Traversal)

This solution uses a recursive Depth-First Search (DFS) approach to find the maximum path sum. The core challenge is that a path can go up through a parent and down into another child (forming an "arch"), but when returning a value to a parent node, we can only return a single downward branch. 

1. **Global Tracker:** 
   We maintain a global/class-level variable `maxSum` initialized to `Integer.MIN_VALUE`. This will keep track of the absolute highest path sum we encounter anywhere in the tree during our traversal.
2. **Recursive DFS (Bottom-Up):** 
   We define a `dfs` helper function that computes the maximum path sum extending downwards from a given node. The base case is a `null` node, which contributes a sum of $0$.
3. **Pruning Negative Paths:** 
   We recursively call `dfs` for the left and right children. Crucially, we use `Math.max(0, dfs(child))`. If a subtree's maximum path sum is negative, including it would only decrease our total sum. By capping the minimum return value at $0$, we effectively choose to ignore detrimental paths.
4. **Evaluating the "Arch" (Local Maximum):** 
   At any given `root`, the maximum path that treats this `root` as the highest node in the path (the "arch") is `root.val + left + right`. We calculate this `pathSum` and update our global `maxSum` if it is strictly greater than the previously recorded maximum.
5. **Returning the Branch (For the Parent):** 
   When returning the path sum to the parent of the current `root`, we cannot return the full "arch" because a valid path cannot branch in three directions. We can only return the current node's value plus the best single downward branch. Therefore, we return `root.val + Math.max(left, right)`.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the total number of nodes in the binary tree. We perform a post-order traversal, visiting every single node in the tree exactly once. The work done at each node (additions and comparisons) takes $O(1)$ time.
* **Space Complexity:** $O(H)$
  Where $H$ is the height of the binary tree. We do not use any auxiliary data structures, but the recursive call stack will grow as deep as the height of the tree. In the worst-case scenario (a completely skewed/unbalanced tree), the height is $N$, resulting in $O(N)$ space. In the best-case scenario (a perfectly balanced tree), the height is $\log N$, resulting in $O(\log N)$ space.
