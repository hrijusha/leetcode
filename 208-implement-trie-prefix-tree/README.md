<h2><a href="https://leetcode.com/problems/implement-trie-prefix-tree">Implement Trie (Prefix Tree)</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>A <a href="https://en.wikipedia.org/wiki/Trie" target="_blank"><strong>trie</strong></a> (pronounced as &quot;try&quot;) or <strong>prefix tree</strong> is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.</p>

<p>Implement the Trie class:</p>

<ul>
	<li><code>Trie()</code> Initializes the trie object.</li>
	<li><code>void insert(String word)</code> Inserts the string <code>word</code> into the trie.</li>
	<li><code>boolean search(String word)</code> Returns <code>true</code> if the string <code>word</code> is in the trie (i.e., was inserted before), and <code>false</code> otherwise.</li>
	<li><code>boolean startsWith(String prefix)</code> Returns <code>true</code> if there is a previously inserted string <code>word</code> that has the prefix <code>prefix</code>, and <code>false</code> otherwise.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;Trie&quot;, &quot;insert&quot;, &quot;search&quot;, &quot;search&quot;, &quot;startsWith&quot;, &quot;insert&quot;, &quot;search&quot;]
[[], [&quot;apple&quot;], [&quot;apple&quot;], [&quot;app&quot;], [&quot;app&quot;], [&quot;app&quot;], [&quot;app&quot;]]
<strong>Output</strong>
[null, null, true, false, true, null, true]

<strong>Explanation</strong>
Trie trie = new Trie();
trie.insert(&quot;apple&quot;);
trie.search(&quot;apple&quot;);   // return True
trie.search(&quot;app&quot;);     // return False
trie.startsWith(&quot;app&quot;); // return True
trie.insert(&quot;app&quot;);
trie.search(&quot;app&quot;);     // return True
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length, prefix.length &lt;= 2000</code></li>
	<li><code>word</code> and <code>prefix</code> consist only of lowercase English letters.</li>
	<li>At most <code>3 * 10<sup>4</sup></code> calls <strong>in total</strong> will be made to <code>insert</code>, <code>search</code>, and <code>startsWith</code>.</li>
</ul>

## Approach: Prefix Tree (Trie)

This solution implements a Trie (Prefix Tree), a specialized tree data structure used to efficiently store and retrieve keys in a dataset of strings. It is particularly highly optimized for prefix-matching operations.

1. **Node Structure (`TrieNode`):** 
   Each node contains a `HashMap` called `children` to store links to subsequent characters, and a boolean flag `isEndOfWord`. 
   * Using a `HashMap` instead of a fixed-size array (like `TrieNode[26]`) makes this implementation flexible enough to handle any Unicode character while saving space on sparse nodes (nodes with few branches). 
   * The `isEndOfWord` flag marks the end of a valid, inserted word, distinguishing it from a path that is merely a prefix for a longer word.
2. **Insertion (`insert`):** 
   We start at the `root` and iterate through each character of the input `word`. For each character, we check if it exists in the current node's `children` map. If it doesn't, we create a new `TrieNode` and add it to the map. We then move our pointer to this child node. After processing all characters, we set the final node's `isEndOfWord` to `true`.
3. **Searching for a Word (`search`):** 
   We traverse the tree character by character starting from the `root`. If at any point the next character is missing from the `children` map, the word does not exist, and we return `false`. If we successfully traverse the entire word, we return the value of `curr.isEndOfWord`. This ensures we don't accidentally return `true` for a string that is only present as a prefix of a longer word.
4. **Searching for a Prefix (`startsWith`):** 
   The traversal logic is identical to `search`. However, if we successfully traverse every character in the prefix without hitting a `null` child, we immediately return `true`. We do not need to check `isEndOfWord` because any valid path that consumes the entire prefix string constitutes a successful prefix match.

## Complexity Analysis

*Let $L$ be the length of the word or prefix being processed.*

* **Time Complexity:** 
  * `insert(String word)`: $O(L)$
    We iterate through the $L$ characters of the word exactly once. HashMap lookups and insertions take $O(1)$ average time.
  * `search(String word)`: $O(L)$
    We perform at most $L$ iterations, with $O(1)$ HashMap lookups at each step.
  * `startsWith(String prefix)`: $O(L)$
    Identical to `search`, taking at most $L$ steps.
* **Space Complexity:** 
  * `insert(String word)`: $O(L)$ auxiliary space per word.
    In the worst case (where the word shares no common prefix with any currently inserted word), we will create $L$ new `TrieNode` objects. Over $N$ total words, the global space complexity is $O(N \cdot L)$ in the worst case, though it is significantly less in practice due to shared prefixes.
  * `search(String word)` and `startsWith(String prefix)`: $O(1)$ auxiliary space.
    Both operations only require a single `curr` pointer to traverse the existing tree, allocating no additional data structures.
