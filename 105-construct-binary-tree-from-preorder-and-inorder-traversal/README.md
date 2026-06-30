<h2><a href="https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal">Construct Binary Tree from Preorder and Inorder Traversal</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>Given two integer arrays <code>preorder</code> and <code>inorder</code> where <code>preorder</code> is the preorder traversal of a binary tree and <code>inorder</code> is the inorder traversal of the same tree, construct and return <em>the binary tree</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/tree.jpg" style="width: 277px; height: 302px;" />
<pre>
<strong>Input:</strong> preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
<strong>Output:</strong> [3,9,20,null,null,15,7]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> preorder = [-1], inorder = [-1]
<strong>Output:</strong> [-1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= preorder.length &lt;= 3000</code></li>
	<li><code>inorder.length == preorder.length</code></li>
	<li><code>-3000 &lt;= preorder[i], inorder[i] &lt;= 3000</code></li>
	<li><code>preorder</code> and <code>inorder</code> consist of <strong>unique</strong> values.</li>
	<li>Each value of <code>inorder</code> also appears in <code>preorder</code>.</li>
	<li><code>preorder</code> is <strong>guaranteed</strong> to be the preorder traversal of the tree.</li>
	<li><code>inorder</code> is <strong>guaranteed</strong> to be the inorder traversal of the tree.</li>
</ul>

## Approach: Divide and Conquer (Recursive)

The solution uses a recursive approach to build the tree by leveraging the properties of preorder and inorder traversals:

1. **Identify the Root:** In a `preorder` traversal (Root -> Left -> Right), the first element is always the root of the current tree (or subtree). An instance variable `preorderIndex` is used to sequentially pick the next root node.
2. **Locate in Inorder:** Once the root is identified and instantiated, the code performs a linear search in the `inorder` array (Left -> Root -> Right) to find the index of this root value.
3. **Divide:** Finding the root in the `inorder` array logically divides the remaining elements into two halves:
   - Everything to the left of the `inOrderIndex` belongs to the left subtree.
   - Everything to the right of the `inOrderIndex` belongs to the right subtree.
4. **Conquer (Recurse):** The function recursively calls itself to build the `left` and `right` subtrees, updating the `left` and `right` boundaries for the `inorder` array segment accordingly.

### The Code
```java
class Solution {
    int preorderIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int left, int right) {
        if (left > right) {
            return null;
        }
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        int inOrderIndex = left;
        for (int i = left; i < inorder.length; i++) {
            if (inorder[inOrderIndex] == rootValue) {
                break;
            } else {
                inOrderIndex++;
            }
        }

        root.left = buildTree(preorder, inorder, left, inOrderIndex - 1);
        root.right = buildTree(preorder, inorder, inOrderIndex + 1, right);

        return root;
    }
}
```

## Complexity Analysis

### Time Complexity: $\mathcal{O}(N^2)$ (Worst Case)
- **Worst-case:** The dominant operation is the linear `for` loop used to find the `rootValue` inside the `inorder` array. In the worst-case scenario (e.g., a completely skewed tree), the recursion will process $N$ levels, and at each level, it scans the remaining array. This results in $N + (N-1) + (N-2) + \dots + 1$, which simplifies to **$\mathcal{O}(N^2)$**.
- **Average-case:** For a balanced tree, the root is typically found near the middle of the current segment, leading to a time complexity closer to **$\mathcal{O}(N \log N)$**.

*(Note: The time complexity can be optimized to $\mathcal{O}(N)$ overall if a `HashMap` is used to cache the indices of the `inorder` elements beforehand, allowing $\mathcal{O}(1)$ lookups instead of scanning.)*

### Space Complexity: $\mathcal{O}(N)$ (Worst Case)
- The space complexity is dictated by the call stack during the recursive calls. 
- **Worst-case:** In a completely skewed tree, the recursion stack will grow as deep as the number of nodes, requiring **$\mathcal{O}(N)$** extra space.
- **Average-case:** For a balanced binary tree, the height of the tree is bounded by $\log N$, resulting in **$\mathcal{O}(\log N)$** space for the recursion stack. No extra data structures are dynamically allocated proportional to the input size, other than the output tree itself.
