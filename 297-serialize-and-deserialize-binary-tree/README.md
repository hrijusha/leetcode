<h2><a href="https://leetcode.com/problems/serialize-and-deserialize-binary-tree">Serialize and Deserialize Binary Tree</a></h2> <img src='https://img.shields.io/badge/Difficulty-Hard-red' alt='Difficulty: Hard' /><hr><p>Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.</p>

<p>Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.</p>

<p><strong>Clarification:</strong> The input/output format is the same as <a href="https://support.leetcode.com/hc/en-us/articles/32442719377939-How-to-create-test-cases-on-LeetCode#h_01J5EGREAW3NAEJ14XC07GRW1A" target="_blank">how LeetCode serializes a binary tree</a>. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/09/15/serdeser.jpg" style="width: 442px; height: 324px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,null,null,4,5]
<strong>Output:</strong> [1,2,3,null,null,4,5]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = []
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[0, 10<sup>4</sup>]</code>.</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
</ul>

## Approach: Breadth-First Search (Level-Order Traversal)

This solution uses a Queue-based Breadth-First Search (BFS) to both serialize the tree into a string and deserialize the string back into a tree. We process the nodes level by level.

### 1. Serialization (Tree to String)
*   **Initialization:** We use a `Queue` for our level-order traversal and a `StringBuilder` to construct the serialized sequence. If the tree is empty, we return an empty string.
*   **Traversal:** We start by adding the `root` to the queue. While the queue is not empty, we poll the front node.
*   **Encoding:** 
    *   If the node is `null`, we append `"N,"` to represent a missing child. We do not add anything to the queue.
    *   If the node is not `null`, we append its integer value followed by a comma (e.g., `"5,"`). Then, we push both its `left` and `right` children into the queue (even if they are `null`, so we can accurately record the tree's structure on the next level).
*   **Result:** This produces a comma-separated string representing the tree level by level, including placeholders for nulls.

### 2. Deserialization (String to Tree)
*   **Initialization:** If the input string is empty, we return `null`. Otherwise, we split the string by commas into an array of strings called `values`. We create the `root` node using the first value and add it to a `Queue`.
*   **Reconstruction:** We use an index `i` (starting at $1$) to iterate through the `values` array.
*   **Parent-Child Mapping:** While the queue is not empty, we poll a `parent` node. 
    *   We check the current string at index `i`. If it's not `"N"`, we create a new `TreeNode` for the left child, attach it to `parent.left`, and add it to the queue. We increment `i`.
    *   We check the next string at index `i`. If it's not `"N"`, we create a new `TreeNode` for the right child, attach it to `parent.right`, and add it to the queue. We increment `i`.
*   **Result:** The queue ensures that we attach children to the correct parents in the exact same level-order sequence they were serialized.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the total number of nodes in the binary tree. 
  * In `serialize`, we visit each node exactly once. Appending to a `StringBuilder` is an $O(1)$ operation, leading to $O(N)$ time.
  * In `deserialize`, string splitting takes $O(N)$ time. We then iterate through the array of values exactly once, doing constant time operations to create nodes and assign pointers, which also takes $O(N)$ time.
* **Space Complexity:** $O(N)$
  * In `serialize`, the `Queue` will hold at most all the nodes at the deepest level of the tree. In the worst case (a perfectly balanced tree), the bottom level holds roughly $N/2$ nodes, requiring $O(N)$ space. The `StringBuilder` also requires $O(N)$ space to hold the output string.
  * In `deserialize`, creating the `values` array by splitting the string takes $O(N)$ space. The `Queue` used for reconstruction also takes up to $O(N)$ space.
