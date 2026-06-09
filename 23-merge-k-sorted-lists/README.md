<h2><a href="https://leetcode.com/problems/merge-k-sorted-lists">Merge k Sorted Lists</a></h2> <img src='https://img.shields.io/badge/Difficulty-Hard-red' alt='Difficulty: Hard' /><hr><p>You are given an array of <code>k</code> linked-lists <code>lists</code>, each linked-list is sorted in ascending order.</p>

<p><em>Merge all the linked-lists into one sorted linked-list and return it.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> lists = [[1,4,5],[1,3,4],[2,6]]
<strong>Output:</strong> [1,1,2,3,4,4,5,6]
<strong>Explanation:</strong> The linked-lists are:
[
  1-&gt;4-&gt;5,
  1-&gt;3-&gt;4,
  2-&gt;6
]
merging them into one sorted linked list:
1-&gt;1-&gt;2-&gt;3-&gt;4-&gt;4-&gt;5-&gt;6
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> lists = []
<strong>Output:</strong> []
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> lists = [[]]
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>k == lists.length</code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= lists[i].length &lt;= 500</code></li>
	<li><code>-10<sup>4</sup> &lt;= lists[i][j] &lt;= 10<sup>4</sup></code></li>
	<li><code>lists[i]</code> is sorted in <strong>ascending order</strong>.</li>
	<li>The sum of <code>lists[i].length</code> will not exceed <code>10<sup>4</sup></code>.</li>
</ul>

## Approach: Min-Heap (Priority Queue)
When merging two lists, a simple two-pointer approach works. However, when merging `k` lists, comparing the front of every list to find the absolute minimum would be too slow. To optimize this, we use a Min-Heap (Priority Queue) to always keep the smallest available nodes at the top, ready to be picked.

**Core Logic:**
1. **Initialize the Heap:** We create a `PriorityQueue` and provide a custom comparator `(a, b) -> a.val - b.val` so it organizes the nodes from smallest to largest value.
2. **Seed the Heap:** We iterate through our array of `k` lists and push the `head` node of every non-empty list into our Priority Queue. Now, the heap contains the smallest nodes from each individual list.
3. **The Dummy Node:** Just like merging two lists, we create a `dummy` node to act as the safe starting point for building our new merged list, and a `list` tail pointer to build it out.
4. **The Merge Loop:** While the Priority Queue is not empty:
   * We `poll()` (remove) the smallest node from the top of the heap.
   * We attach this minimum node to our newly building list.
   * **The Magic Step:** If the node we just pulled out has a next node in its original list, we push that `next` node into the Priority Queue. This ensures that the heap always contains the current "front" of every active list.
5. **Return:** Once the heap is empty, all nodes have been sorted. We return `dummy.next` to skip the placeholder value.

## Complexity Analysis
* **Time Complexity:** **`O(N log K)`** Where `N` is the total number of nodes across all lists, and `K` is the number of linked lists. Every single node is added to and removed from the Priority Queue exactly once. Insertion and deletion in a heap of size `K` takes `O(log K)` time.
* **Space Complexity:** **`O(K)`** The Priority Queue never holds more than `K` elements at any given time (one node from each list). Creating the `dummy` pointer requires `O(1)` space, making the overall auxiliary space bounded by `K`.
