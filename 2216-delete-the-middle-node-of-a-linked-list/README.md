<h2><a href="https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list">Delete the Middle Node of a Linked List</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>You are given the <code>head</code> of a linked list. <strong>Delete</strong> the <strong>middle node</strong>, and return <em>the</em> <code>head</code> <em>of the modified linked list</em>.</p>

<p>The <strong>middle node</strong> of a linked list of size <code>n</code> is the <code>&lfloor;n / 2&rfloor;<sup>th</sup></code> node from the <b>start</b> using <strong>0-based indexing</strong>, where <code>&lfloor;x&rfloor;</code> denotes the largest integer less than or equal to <code>x</code>.</p>

<ul>
	<li>For <code>n</code> = <code>1</code>, <code>2</code>, <code>3</code>, <code>4</code>, and <code>5</code>, the middle nodes are <code>0</code>, <code>1</code>, <code>1</code>, <code>2</code>, and <code>2</code>, respectively.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/11/16/eg1drawio.png" style="width: 500px; height: 77px;" />
<pre>
<strong>Input:</strong> head = [1,3,4,7,1,2,6]
<strong>Output:</strong> [1,3,4,1,2,6]
<strong>Explanation:</strong>
The above figure represents the given linked list. The indices of the nodes are written below.
Since n = 7, node 3 with value 7 is the middle node, which is marked in red.
We return the new list after removing this node. 
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/11/16/eg2drawio.png" style="width: 250px; height: 43px;" />
<pre>
<strong>Input:</strong> head = [1,2,3,4]
<strong>Output:</strong> [1,2,4]
<strong>Explanation:</strong>
The above figure represents the given linked list.
For n = 4, node 2 with value 3 is the middle node, which is marked in red.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/11/16/eg3drawio.png" style="width: 150px; height: 58px;" />
<pre>
<strong>Input:</strong> head = [2,1]
<strong>Output:</strong> [2]
<strong>Explanation:</strong>
The above figure represents the given linked list.
For n = 2, node 1 with value 1 is the middle node, which is marked in red.
Node 0 with value 2 is the only node remaining after removing node 1.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is in the range <code>[1, 10<sup>5</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

## Approach: Fast and Slow Pointers (Tortoise and Hare)

This solution uses the classic two-pointer technique to find and remove the middle node of a singly linked list in a single pass. 

1. **Base Case Handling:** 
   If the linked list has only one node (`head.next == null`), there is no middle node to leave behind, so the list becomes empty. We simply return `null`.
2. **Pointer Initialization (The Trick):** 
   Normally, to find the exact middle of a list, both `slow` and `fast` start at the `head`. However, to *delete* a node in a singly linked list, we need our pointer to stop at the node **just before** the one we want to delete. 
   To achieve this, we give the `fast` pointer a head start by initializing it to `head.next.next`, while `slow` starts at `head`. This artificial lag ensures that when `fast` reaches the end of the list, `slow` will be pointing to the predecessor of the middle node.
3. **Traversal:** 
   We iterate through the list, moving the `fast` pointer two steps at a time (`fast = fast.next.next`) and the `slow` pointer one step at a time (`slow = slow.next`). We continue this until `fast` either reaches the last node or drops off the end of the list (`null`).
4. **Deletion:** 
   Once the loop terminates, `slow` is sitting exactly one node before the middle node. We delete the middle node by bypassing it: `slow.next = slow.next.next`. 
5. **Final Result:** 
   We return the original `head` of the modified list.

## Complexity Analysis

* **Time Complexity:** $O(n)$
  Where $n$ is the number of nodes in the linked list. The `fast` pointer traverses the list by jumping two nodes at a time, meaning the loop runs approximately $n/2$ times. This simplifies to a linear time complexity of $O(n)$.
* **Space Complexity:** $O(1)$
  We only use two extra pointers (`slow` and `fast`) to traverse the list. The deletion is done entirely in-place by rearranging pointers, requiring constant auxiliary space.
