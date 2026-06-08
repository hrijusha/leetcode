<h2><a href="https://leetcode.com/problems/linked-list-cycle">Linked List Cycle</a></h2> <img src='https://img.shields.io/badge/Difficulty-Easy-brightgreen' alt='Difficulty: Easy' /><hr><p>Given <code>head</code>, the head of a linked list, determine if the linked list has a cycle in it.</p>

<p>There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the&nbsp;<code>next</code>&nbsp;pointer. Internally, <code>pos</code>&nbsp;is used to denote the index of the node that&nbsp;tail&#39;s&nbsp;<code>next</code>&nbsp;pointer is connected to.&nbsp;<strong>Note that&nbsp;<code>pos</code>&nbsp;is not passed as a parameter</strong>.</p>

<p>Return&nbsp;<code>true</code><em> if there is a cycle in the linked list</em>. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png" style="width: 300px; height: 97px; margin-top: 8px; margin-bottom: 8px;" />
<pre>
<strong>Input:</strong> head = [3,2,0,-4], pos = 1
<strong>Output:</strong> true
<strong>Explanation:</strong> There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test2.png" style="width: 141px; height: 74px;" />
<pre>
<strong>Input:</strong> head = [1,2], pos = 0
<strong>Output:</strong> true
<strong>Explanation:</strong> There is a cycle in the linked list, where the tail connects to the 0th node.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test3.png" style="width: 45px; height: 45px;" />
<pre>
<strong>Input:</strong> head = [1], pos = -1
<strong>Output:</strong> false
<strong>Explanation:</strong> There is no cycle in the linked list.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of the nodes in the list is in the range <code>[0, 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
	<li><code>pos</code> is <code>-1</code> or a <strong>valid index</strong> in the linked-list.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Can you solve it using <code>O(1)</code> (i.e. constant) memory?</p>

## Approach: Hash Set (Visited Tracking)
The simplest way to detect a cycle is to keep track of every node we have visited. As we traverse the linked list, we can drop each node into a "visited" collection. If we ever encounter a node that is already in our collection, we know we've been running in a circle.

## Core Logic
1. **Initialization:** Create a `HashSet` to store the references of the nodes we visit. We use a Set because it provides constant time `O(1)` lookups.
2. **Traversal:** Iterate through the linked list starting from the `head` using a `curr` pointer.
3. **Detection:**
   * At each step, check if the `curr` node is already inside the `seen` set.
   * If it is, a cycle is detected! Return `true`.
   * If it is not, add the `curr` node to the `seen` set and move to the `next` node.
4. **Termination:** If the `curr` pointer eventually reaches `null`, it means we reached the end of the list without looping. Return `false`.

## Complexity Analysis
* **Time Complexity:** **`O(N)`** Where `N` is the number of nodes in the linked list. We visit each node at most once. Adding a node to a HashSet and checking for its existence both take `O(1)` time on average.
* **Space Complexity:** **`O(N)`** In the worst-case scenario (a linked list with no cycle), we will end up storing every single node in the `HashSet`, requiring memory proportional to the size of the list.
