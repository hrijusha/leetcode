<h2><a href="https://leetcode.com/problems/reorder-list">Reorder List</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>You are given the head of a singly linked-list. The list can be represented as:</p>

<pre>
L<sub>0</sub> &rarr; L<sub>1</sub> &rarr; &hellip; &rarr; L<sub>n - 1</sub> &rarr; L<sub>n</sub>
</pre>

<p><em>Reorder the list to be on the following form:</em></p>

<pre>
L<sub>0</sub> &rarr; L<sub>n</sub> &rarr; L<sub>1</sub> &rarr; L<sub>n - 1</sub> &rarr; L<sub>2</sub> &rarr; L<sub>n - 2</sub> &rarr; &hellip;
</pre>

<p>You may not modify the values in the list&#39;s nodes. Only nodes themselves may be changed.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/04/reorder1linked-list.jpg" style="width: 422px; height: 222px;" />
<pre>
<strong>Input:</strong> head = [1,2,3,4]
<strong>Output:</strong> [1,4,2,3]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/09/reorder2-linked-list.jpg" style="width: 542px; height: 222px;" />
<pre>
<strong>Input:</strong> head = [1,2,3,4,5]
<strong>Output:</strong> [1,5,2,4,3]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is in the range <code>[1, 5 * 10<sup>4</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 1000</code></li>
</ul>

**Core Logic:**
1. **Find the Middle (Tortoise and Hare):** * We use a `slow` pointer (moves 1 step) and a `fast` pointer (moves 2 steps). 
   * By the time the `fast` pointer reaches the end, the `slow` pointer will be exactly at the middle of the list. We then split the list into two halves.
2. **Reverse the Second Half:**
   * We take the second half of the list (starting from `slow.next`) and reverse the direction of its pointers in place using `prev`, `curr`, and `temp` variables.
3. **Merge Alternately:**
   * Finally, we use two pointers (`p1` at the head of the first half, `p2` at the head of the reversed second half).
   * We weave the two lists together by alternating connections: linking a node from the first half to a node from the second half, and repeating until the second half is exhausted.

## Complexity Analysis
* **Time Complexity:** **`O(N)`** Where `N` is the number of nodes in the linked list. 
  * Finding the middle takes `O(N/2)` time.
  * Reversing the second half takes `O(N/2)` time.
  * Merging the two halves takes `O(N/2)` time.
  * Overall time complexity simplifies to linear `O(N)`.
* **Space Complexity:** **`O(1)`** We strictly manipulate existing pointers (`fast`, `slow`, `prev`, `curr`, etc.) without allocating any new nodes or external data structures, resulting in strictly constant extra space.
