<h2><a href="https://leetcode.com/problems/word-search-ii">Word Search II</a></h2> <img src='https://img.shields.io/badge/Difficulty-Hard-red' alt='Difficulty: Hard' /><hr><p>Given an <code>m x n</code> <code>board</code>&nbsp;of characters and a list of strings <code>words</code>, return <em>all words on the board</em>.</p>

<p>Each word must be constructed from letters of sequentially adjacent cells, where <strong>adjacent cells</strong> are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/07/search1.jpg" style="width: 322px; height: 322px;" />
<pre>
<strong>Input:</strong> board = [[&quot;o&quot;,&quot;a&quot;,&quot;a&quot;,&quot;n&quot;],[&quot;e&quot;,&quot;t&quot;,&quot;a&quot;,&quot;e&quot;],[&quot;i&quot;,&quot;h&quot;,&quot;k&quot;,&quot;r&quot;],[&quot;i&quot;,&quot;f&quot;,&quot;l&quot;,&quot;v&quot;]], words = [&quot;oath&quot;,&quot;pea&quot;,&quot;eat&quot;,&quot;rain&quot;]
<strong>Output:</strong> [&quot;eat&quot;,&quot;oath&quot;]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/07/search2.jpg" style="width: 162px; height: 162px;" />
<pre>
<strong>Input:</strong> board = [[&quot;a&quot;,&quot;b&quot;],[&quot;c&quot;,&quot;d&quot;]], words = [&quot;abcb&quot;]
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n == board[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 12</code></li>
	<li><code>board[i][j]</code> is a lowercase English letter.</li>
	<li><code>1 &lt;= words.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
	<li>All the strings of <code>words</code> are unique.</li>
</ul>

## Approach: Prefix Tree (Trie) + DFS with Backtracking

This solution solves the "Word Search II" problem by combining a Trie (Prefix Tree) with Depth-First Search (DFS) and Backtracking. Instead of searching the grid for every single word individually (which would be extremely slow), we build a Trie of all words and search the grid once, using the Trie to instantly prune invalid paths.

1. **Trie Construction:** 
   We first insert all target `words` into a Trie. This data structure allows us to efficiently verify if a sequence of characters on the board is a valid prefix for *any* of the words we are looking for.
2. **Grid Traversal:** 
   We iterate through every cell in the $M \times N$ board. We treat every cell as a potential starting character and launch a DFS from it, passing the `root` of our Trie.
3. **DFS and Prefix Pruning (The Core Optimization):** 
   During the DFS, before exploring further, we check if the current cell's character exists as a child in our current Trie node. 
   * If it doesn't, it means no word in our dictionary starts with the path we've traced so far. We immediately prune this branch and `return`, saving massive amounts of computation.
4. **Word Discovery and Deduplication:** 
   If we land on a Trie node where `isEndOfWord` is `true`, we have successfully found a complete word. We add the constructed `currWord` to our result list. 
   * **Crucial Step:** We immediately set `nextNode.isEndOfWord = false`. This guarantees that even if we find the exact same word via a different winding path on the board, we won't add it to our result list twice.
5. **Backtracking (Mark and Sweep):** 
   To ensure we don't reuse the same cell in a single word path, we temporarily mark the current cell as visited by overwriting it with `'#'`. We then recursively explore all 4 adjacent directions. Once all directions are explored, we backtrack by restoring the original character, freeing it up for other potential overlapping paths.

## Complexity Analysis

*Variables:* Let $W$ be the number of words, $L$ be the maximum length of a word, $M$ be the number of rows, and $N$ be the number of columns.

* **Time Complexity:** $O(W \cdot L + M \cdot N \cdot 3^L)$
  * **Trie Construction:** Inserting all words takes $O(W \cdot L)$ time.
  * **DFS Traversal:** We initiate a search from $M \cdot N$ cells. In the worst case, from any starting cell, we explore 4 directions initially. Because we mark the cell we just came from as visited, we only have 3 valid choices for every subsequent step. The recursion goes as deep as the longest word $L$. Therefore, the DFS exploration space is tightly bounded by $O(M \cdot N \cdot 3^L)$. 
  *(Note: String concatenation `currWord + ch` creates a new string at each step taking $O(L)$ time, which adds overhead, but the branching factor dominates the asymptotic complexity).*
* **Space Complexity:** $O(W \cdot L)$
  * **Trie:** In the absolute worst case (where no words share any common prefixes), the Trie will contain $W \cdot L$ nodes, taking $O(W \cdot L)$ auxiliary space.
  * **Call Stack:** The recursive DFS call stack will grow at most to the length of the longest word, taking $O(L)$ space. 
  * The dominant factor is the Trie memory, resulting in an overall space complexity of $O(W \cdot L)$.
