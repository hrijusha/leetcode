<h2><a href="https://leetcode.com/problems/kth-smallest-element-in-a-bst">Kth Smallest Element in a BST</a></h2> 
<img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' />
<hr>

<p>Given the <code>root</code> of a binary search tree, and an integer <code>k</code>, return <em>the</em> <code>k<sup>th</sup></code> <em>smallest value (<strong>1-indexed</strong>) of all the values of the nodes in the tree</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/28/kthtree1.jpg" style="width: 212px; height: 301px;" />
<pre>
<strong>Input:</strong> root = [3,1,4,null,2], k = 1
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/28/kthtree2.jpg" style="width: 382px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [5,3,6,2,4,null,null,1], k = 3
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is <code>n</code>.</li>
	<li><code>1 &lt;= k &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?</p>

---

## Approach

This solution utilizes a fundamental property of Binary Search Trees (BST): **an in-order traversal (Left $\rightarrow$ Root $\rightarrow$ Right) visits the nodes in strictly ascending order.**

1.  **State Management:** We use two class-level variables: `count` (initialized to $k$) to track how many nodes we still need to visit, and `result` to store the final answer.
2.  **Recursive Traversal:** The `findElement` helper function performs the in-order traversal. 
    * It recursively traverses as far left as possible to reach the smallest elements.
    * As it returns from the left subtree, it "visits" the current root node by decrementing `count`.
    * If `count` hits `0`, it means the current node is exactly the $k$-th smallest element. We save `node.val` to `result` and return.
    * If the target hasn't been found, the traversal continues down the right subtree.

## Complexity Analysis

* **Time Complexity:** $O(H + k)$ where $H$ is the height of the tree. The algorithm first traverses down to the leftmost leaf, taking $O(H)$ time. From there, it processes $k$ nodes. 
    * *Best/Average Case:* In a balanced tree, the height is $\log N$, making the time complexity $O(\log N + k)$. 
    * *Worst Case:* In a completely unbalanced (skewed) tree, it may take $O(N)$ time.
* **Space Complexity:** $O(H)$ to maintain the recursive call stack. 
    * *Best/Average Case:* $O(\log N)$ for a balanced tree.
    * *Worst Case:* $O(N)$ for a skewed tree where the recursion goes $N$ levels deep.

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
    int result = 0;
    int count = 0;

    public int kthSmallest(TreeNode root, int k) {
        count = k;
        findElement(root);
        return result;
    }

    private void findElement(TreeNode node) {
        if (node == null) {
            return;
        } else {
            // Visit Left
            findElement(node.left);

            // Process Node
            count--;
            if (count == 0) {
                result = node.val;
                return;
            }
            
            // Visit Right
            findElement(node.right);
        }
    }
}
