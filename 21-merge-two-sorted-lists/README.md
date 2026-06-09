<h2><a href="https://leetcode.com/problems/merge-two-sorted-lists">Merge Two Sorted Lists</a></h2> <img src='https://img.shields.io/badge/Difficulty-Easy-brightgreen' alt='Difficulty: Easy' /><hr><p>You are given the heads of two sorted linked lists <code>list1</code> and <code>list2</code>.</p>

<p>Merge the two lists into one <strong>sorted</strong> list. The list should be made by splicing together the nodes of the first two lists.</p>

<p>Return <em>the head of the merged linked list</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/merge_ex1.jpg" style="width: 662px; height: 302px;" />
<pre>
<strong>Input:</strong> list1 = [1,2,4], list2 = [1,3,4]
<strong>Output:</strong> [1,1,2,3,4,4]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> list1 = [], list2 = []
<strong>Output:</strong> []
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> list1 = [], list2 = [0]
<strong>Output:</strong> [0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in both lists is in the range <code>[0, 50]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
	<li>Both <code>list1</code> and <code>list2</code> are sorted in <strong>non-decreasing</strong> order.</li>
</ul>

## Approach: Two-Pointer with a Dummy Node
Because both input lists are already sorted, we can build the merged list by comparing the front nodes of each list one by one, picking the smaller of the two, and appending it to our new list. 

**Core Logic:**
1. **The Dummy Node:** We create a `dummy` node (initialized with a dummy value like `-1`). This gives us a convenient starting point to attach our merged nodes to without worrying about whether the new list is empty yet. We also create a `sortedList` pointer to act as the "tail" that builds out the new list.
2. **The Comparison Loop:** While neither `list1` nor `list2` is empty:
   * We compare the current values of `list1` and `list2`.
   * We point `sortedList.next` to the node with the strictly smaller value.
   * We advance the pointer of the list we just pulled from.
   * We advance our `sortedList` tail pointer.
3. **The Remainder:** Eventually, one list will run out of nodes before the other. Because the lists are already sorted, the remaining nodes in the unfinished list are guaranteed to be larger than everything processed so far. We can simply attach the entire remainder of that list to the end of our `sortedList`.
4. **Return:** We return `dummy.next`, which points to the actual start of our beautifully merged list.

## Complexity Analysis
* **Time Complexity:** **`O(N + M)`** Where `N` and `M` are the lengths of `list1` and `list2` respectively. We iterate through the lists at most once. In the worst-case scenario, we traverse all nodes in both lists before one runs out.
* **Space Complexity:** **`O(1)`** We are strictly manipulating the existing pointers of the input lists. Aside from the single `dummy` node, no new memory is allocated, making the extra space required perfectly constant.
