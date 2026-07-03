<h2><a href="https://leetcode.com/problems/design-add-and-search-words-data-structure">Design Add and Search Words Data Structure</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>Design a data structure that supports adding new words and finding if a string matches any previously added string.</p>

<p>Implement the <code>WordDictionary</code> class:</p>

<ul>
	<li><code>WordDictionary()</code>&nbsp;Initializes the object.</li>
	<li><code>void addWord(word)</code> Adds <code>word</code> to the data structure, it can be matched later.</li>
	<li><code>bool search(word)</code>&nbsp;Returns <code>true</code> if there is any string in the data structure that matches <code>word</code>&nbsp;or <code>false</code> otherwise. <code>word</code> may contain dots <code>&#39;.&#39;</code> where dots can be matched with any letter.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<pre>
<strong>Input</strong>
[&quot;WordDictionary&quot;,&quot;addWord&quot;,&quot;addWord&quot;,&quot;addWord&quot;,&quot;search&quot;,&quot;search&quot;,&quot;search&quot;,&quot;search&quot;]
[[],[&quot;bad&quot;],[&quot;dad&quot;],[&quot;mad&quot;],[&quot;pad&quot;],[&quot;bad&quot;],[&quot;.ad&quot;],[&quot;b..&quot;]]
<strong>Output</strong>
[null,null,null,null,false,true,true,true]

<strong>Explanation</strong>
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord(&quot;bad&quot;);
wordDictionary.addWord(&quot;dad&quot;);
wordDictionary.addWord(&quot;mad&quot;);
wordDictionary.search(&quot;pad&quot;); // return False
wordDictionary.search(&quot;bad&quot;); // return True
wordDictionary.search(&quot;.ad&quot;); // return True
wordDictionary.search(&quot;b..&quot;); // return True
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 25</code></li>
	<li><code>word</code> in <code>addWord</code> consists of lowercase English letters.</li>
	<li><code>word</code> in <code>search</code> consist of <code>&#39;.&#39;</code> or lowercase English letters.</li>
	<li>There will be at most <code>2</code> dots in <code>word</code> for <code>search</code> queries.</li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>addWord</code> and <code>search</code>.</li>
</ul>

## Approach 1: Brute Force (HashSet + Regex)

This is a straightforward, logical approach that relies on Java's built-in collections and regular expressions. 

1.  **Data Structure:** We store all inserted words in a standard `HashSet`.
2.  **Adding Words (`addWord`):** We simply add the string to the Set. 
3.  **Searching Words (`search`):** * If the word has no wildcards (`.`), we do a highly efficient $O(1)$ lookup using `set.contains(word)`.
    * If the word contains a `.`, we iterate through **every single word** in the `HashSet` and use Java's `String.matches()` method to check if the regex pattern fits.

### Complexity Analysis
Let $N$ be the total number of words in the dictionary, and $L$ be the length of the word to search.
* **Time Complexity:** * `addWord(word)`: $O(L)$ due to the string hashing process.
    * `search(word)`: $O(L)$ for standard words. However, for wildcard words, it is $O(N \cdot L)$. Iterating through every word and compiling the regex pattern via `.matches()` on every check is very slow and will likely cause a **Time Limit Exceeded (TLE)** on large datasets.
* **Space Complexity:** $O(N \cdot L)$ to store all words independently in the HashSet. No memory is shared between words with common prefixes.

---

## Approach 2: Optimized (Trie + DFS)

To optimize the wildcard search and memory footprint, we use a **Trie (Prefix Tree)** combined with a **Depth-First Search (DFS)**.

1.  **Data Structure:** A `TrieNode` class where each node contains an array of 26 children (for 'a'-'z') and a boolean `isWord` flag. Prefix sharing drastically reduces memory usage.
2.  **Adding Words (`addWord`):** We iterate character by character, creating new nodes when paths don't exist, and mark the final node as a valid word.
3.  **Searching Words (`search`):** * **Iterative Fast-Path:** If the word has no `.`, we perform a standard, highly efficient iterative Trie traversal.
    * **Recursive Wildcard Search:** If the word contains a `.`, we delegate to a recursive Depth-First Search (`searchHelper`). When the DFS encounters a `.`, it iterates through all 26 possible child nodes and recursively explores any path that exists.

### Complexity Analysis
* **Time Complexity:**
    * `addWord(word)`: $O(L)$.
    * `search(word)`: $O(L)$ for standard words. For wildcards, the worst-case is $O(26^L)$, but practically it is significantly faster than Approach 1 because it instantly prunes invalid paths (it only explores branches where characters actually exist).
* **Space Complexity:** $O(M)$ overall, where $M$ is the total length of all characters. Shared prefixes save massive amounts of memory compared to a HashSet.

---

## Code Implementations

### Solution 1: Brute Force (HashSet)
```java
class WordDictionary {
    Set<String> set;

    public WordDictionary() {
        set = new HashSet<>();
    }

    public void addWord(String word) {
        set.add(word);
    }

    public boolean search(String word) {
        if (word.contains(".")) {
            for (String s : set) {
                if (check(s, word)) {
                    return true;
                }
            }
            return false;
        } else {
            return set.contains(word);
        }
    }

    private boolean check(String s, String pattern) {
        if (s.matches(pattern)) {
            return true;
        }
        return false;
    }
}
```


```java
class WordDictionary {
    class TrieNode {
        // Array to hold links to the next possible characters (a-z)
        TrieNode[] children;
        boolean isWord;

        public TrieNode() {
            children = new TrieNode[26];
            isWord = false;
        }
    }

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            // If the path doesn't exist, create a new node
            if (current.children[index] == null) {
                // store the character and attach a new TrieNode;
                current.children[index] = new TrieNode();
            }
            // move down to the new node
            current = current.children[index];
        }
        current.isWord = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        
        // OPTIMIZATION: Iterative search for standard words
        if (!word.contains(".")) {
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                // If the path doesn't exist, return false
                if (current.children[index] == null) {
                    return false;
                }
                current = current.children[index];
            }
            return current.isWord;
        } else {
            // Fallback to DFS for wildcards
            return searchHelper(word, 0, root);
        }
    }

    private boolean searchHelper(String word, int index, TrieNode current) {
        if (index == word.length()) {
            return current.isWord;
        }

        char c = word.charAt(index);

        if (c == '.') {
            for (TrieNode child : current.children) {
                if (child != null && searchHelper(word, index + 1, child)) {
                    return true;
                }
            }
            return false;
        } else {
            int charIndex = c - 'a';
            if (current.children[charIndex] == null) {
                return false;
            }
            return searchHelper(word, index + 1, current.children[charIndex]);
        }
    }
}
```
