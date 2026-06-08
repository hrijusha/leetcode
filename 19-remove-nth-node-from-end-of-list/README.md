<h2><a href="https://leetcode.com/problems/remove-nth-node-from-end-of-list">Remove Nth Node From End of List</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>Given the <code>head</code> of a linked list, remove the <code>n<sup>th</sup></code> node from the end of the list and return its head.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/remove_ex1.jpg" style="width: 542px; height: 222px;" />
<pre>
<strong>Input:</strong> head = [1,2,3,4,5], n = 2
<strong>Output:</strong> [1,2,3,5]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> head = [1], n = 1
<strong>Output:</strong> []
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> head = [1,2], n = 1
<strong>Output:</strong> [1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is <code>sz</code>.</li>
	<li><code>1 &lt;= sz &lt;= 30</code></li>
	<li><code>0 &lt;= Node.val &lt;= 100</code></li>
	<li><code>1 &lt;= n &lt;= sz</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you do this in one pass?</p>

## Approach: One-Pass Two-Pointer (Fast and Slow)
To find the `n`th node from the end without knowing the total length of the list beforehand, we can maintain a specific "gap" between two pointers. By the time the front pointer reaches the end, the trailing pointer will be exactly where we need it.

**Core Logic:**
1. **Dummy Node:** We create a `dummy` node that points to the `head`. This is crucial for simplifying edge cases, such as when the target node to remove is the `head` itself. We initialize both a `fast` and `slow` pointer at this dummy node.
2. **Create the Gap:** We advance the `fast` pointer exactly `n` steps forward. This creates a gap of `n` nodes between the `fast` and `slow` pointers.
3. **Traverse to the End:** We then move both pointers forward one step at a time simultaneously. Because the gap is fixed at `n` nodes, when the `fast` pointer hits the last node in the list (`fast.next == null`), the `slow` pointer will be positioned exactly at the node *immediately before* the one we want to delete.
4. **Delete and Return:** We bypass the target node by updating the pointer: `slow.next = slow.next.next`. Finally, we return `dummy.next` as the new head of the list.

## Complexity Analysis
* **Time Complexity:** **`O(N)`** Where `N` is the total number of nodes in the linked list. We process the list in exactly one pass.
* **Space Complexity:** **`O(1)`** We only use a few distinct pointers (`dummy`, `fast`, `slow`), requiring strictly constant extra space.


## Approach 2: Brute Force (Two-Pass)
The most straightforward way to find a node from the *end* of a list is to first figure out exactly how long the list is. Once we know the total length, we can calculate exactly which node it is from the *beginning* and just walk there.

**Core Logic:**
1. **First Pass (Find Length):** Traverse the entire linked list from the head to the end just to count the total number of nodes (let's call this length `L`).
2. **Calculate Target:** The `n`th node from the end is exactly the `(L - n)`th node from the beginning.
3. **Second Pass (Remove Node):** * We use a `dummy` node at the start to handle edge cases (like if we need to remove the very first node).
   * We traverse the list a second time, stopping exactly one node *before* our target.
   * We skip the target node by updating the pointer: `curr.next = curr.next.next`.

### Complexity Analysis (Brute Force)
* **Time Complexity:** **`O(N)`** Where `N` is the total number of nodes in the linked list. Even though we iterate through the list twice (once to find the length, once to find the target), `O(2N)` simplifies to `O(N)` in Big O notation.
* **Space Complexity:** **`O(1)`** We only use a few variables to keep track of the length and our current position, requiring strictly constant extra space.

## Solution (Java)

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Dummy node to handle edge cases like removing the head
        ListNode dummy = new ListNode(0, head);
        
        // Step 1: Find the total length of the list
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        
        // Step 2: Calculate the target index from the beginning
        int targetIndex = length - n;
        
        // Step 3: Traverse again to reach the node just before the target
        curr = dummy;
        for (int i = 0; i < targetIndex; i++) {
            curr = curr.next;
        }
        
        // Skip the target node
        curr.next = curr.next.next;
        
        return dummy.next;
    }
}
