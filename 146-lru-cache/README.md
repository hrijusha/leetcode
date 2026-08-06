<h2><a href="https://leetcode.com/problems/lru-cache">LRU Cache</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>Design a data structure that follows the constraints of a <strong><a href="https://en.wikipedia.org/wiki/Cache_replacement_policies#LRU" target="_blank">Least Recently Used (LRU) cache</a></strong>.</p>

<p>Implement the <code>LRUCache</code> class:</p>

<ul>
	<li><code>LRUCache(int capacity)</code> Initialize the LRU cache with <strong>positive</strong> size <code>capacity</code>.</li>
	<li><code>int get(int key)</code> Return the value of the <code>key</code> if the key exists, otherwise return <code>-1</code>.</li>
	<li><code>void put(int key, int value)</code> Update the value of the <code>key</code> if the <code>key</code> exists. Otherwise, add the <code>key-value</code> pair to the cache. If the number of keys exceeds the <code>capacity</code> from this operation, <strong>evict</strong> the least recently used key.</li>
</ul>

<p>The functions <code>get</code> and <code>put</code> must each run in <code>O(1)</code> average time complexity.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;LRUCache&quot;, &quot;put&quot;, &quot;put&quot;, &quot;get&quot;, &quot;put&quot;, &quot;get&quot;, &quot;put&quot;, &quot;get&quot;, &quot;get&quot;, &quot;get&quot;]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
<strong>Output</strong>
[null, null, null, 1, null, -1, null, -1, 3, 4]

<strong>Explanation</strong>
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= capacity &lt;= 3000</code></li>
	<li><code>0 &lt;= key &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= value &lt;= 10<sup>5</sup></code></li>
	<li>At most <code>2 * 10<sup>5</sup></code> calls will be made to <code>get</code> and <code>put</code>.</li>
</ul>

## Approach: HashMap + Doubly Linked List

This solution implements a Least Recently Used (LRU) Cache by combining a **HashMap** (for fast lookups) and a custom **Doubly Linked List** (for fast updates to the access order).

1. **State Definition & Initialization:** 
   * **HashMap (`cache`):** Stores keys mapped to their corresponding Linked List nodes, providing $O(1)$ access time.
   * **Doubly Linked List (DLL):** Maintains the access order. The front (right after the head) represents the Most Recently Used (MRU) item, while the back (right before the tail) represents the Least Recently Used (LRU) item.
   * **Dummy Nodes:** We initialize dummy `head` and `tail` nodes linked to each other. This is a classic DLL trick that eliminates edge cases (like inserting into an empty list or deleting the last node), ensuring pointer manipulations never hit `NullPointerException`.
2. **Read Operation (`get`):** 
   If the key exists in the HashMap, we retrieve the corresponding node. Because accessing the data counts as "using" it, we must update its rank. We `remove()` the node from its current position in the DLL and `insert()` it right behind the dummy `head` (marking it as the MRU). Then, we return its value. If the key doesn't exist, we return `-1`.
3. **Write Operation (`put`):** 
   * **Update Existing:** If the key already exists, we fetch the node, update its `value`, and move it to the MRU position (head) using the same `remove()` and `insert()` logic.
   * **Insert New:** If the key is new, we create a new `Node`, add it to the HashMap, and `insert()` it at the MRU position (head).
4. **Eviction Policy:** 
   After inserting a new node, we check if the `cache.size()` exceeds our allowed `capacity`. If it does, we must evict the LRU item. Because of our DLL structure, the LRU item is always guaranteed to be the node immediately preceding the dummy tail (`tail.prev`). We remove this node from the DLL and delete its key from the HashMap.

## Complexity Analysis

* **Time Complexity:** 
  * `get(int key)`: $O(1)$ average time. HashMap lookups take $O(1)$, and DLL pointer updates (removing and inserting nodes) strictly take $O(1)$ time.
  * `put(int key, int value)`: $O(1)$ average time. HashMap insertions and DLL pointer updates are both constant time operations. Even during an eviction, accessing `tail.prev` and removing it from both structures takes $O(1)$ time.
* **Space Complexity:** $O(C)$
  Where $C$ is the `capacity` of the cache. At any given time, the HashMap holds at most $C$ key-value pairs, and the Doubly Linked List holds at most $C + 2$ nodes (including the dummy `head` and `tail`).
