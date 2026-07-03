<h2><a href="https://leetcode.com/problems/invert-binary-tree">Invert Binary Tree</a></h2> <img src='https://img.shields.io/badge/Difficulty-Easy-brightgreen' alt='Difficulty: Easy' /><hr><p>Given the <code>root</code> of a binary tree, invert the tree, and return <em>its root</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/14/invert1-tree.jpg" style="width: 500px; height: 165px;" />
<pre>
<strong>Input:</strong> root = [4,2,7,1,3,6,9]
<strong>Output:</strong> [4,7,2,9,6,3,1]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/14/invert2-tree.jpg" style="width: 500px; height: 120px;" />
<pre>
<strong>Input:</strong> root = [2,1,3]
<strong>Output:</strong> [2,3,1]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> root = []
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[0, 100]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
</ul>

---

## Approach

This solution uses a straightforward **Top-Down Recursive Approach** (Depth-First Search). 

The core idea of inverting a binary tree is that for every node, its left child and right child need to be swapped. Because trees are recursive data structures, if we swap the children at the current node and then ask the children to swap their own subtrees, the entire tree will be mirrored.

1.  **Base Case:** If the current `root` is `null`, there is nothing to invert, so we simply return `null`.
2.  **Swap Children:** We temporarily store the left child in a `temp` variable. Then, we overwrite the left child with the right child, and assign the stored left child to the right child.
3.  **Recurse:** We recursively call `invertTree` on the newly swapped `root.left` and `root.right` subtrees. 
4.  **Return:** Finally, we return the original `root` node, which now represents the top of the completely inverted tree.

## Complexity Analysis

* **Time Complexity:** `O(N)` where `N` is the total number of nodes in the tree. We must visit every single node exactly once to swap its children.
* **Space Complexity:** `O(H)` where `H` is the height of the tree. This accounts for the memory used by the recursive call stack.
    * *Best/Average Case:* `O(log N)` for a well-balanced binary tree.
    * *Worst Case:* `O(N)` for a completely unbalanced (skewed) tree where the recursive depth equals the total number of nodes.

---

## Code

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        // Base case: if the tree is empty, return null
        if (root == null) {
            return null;
        }
        
        // Swap the left and right children
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // Recursively invert the left and right subtrees
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
