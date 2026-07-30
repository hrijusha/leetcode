<h2><a href="https://leetcode.com/problems/minimum-window-substring">Minimum Window Substring</a></h2> <img src='https://img.shields.io/badge/Difficulty-Hard-red' alt='Difficulty: Hard' /><hr><p>Given two strings <code>s</code> and <code>t</code> of lengths <code>m</code> and <code>n</code> respectively, return <em>the <strong>minimum window</strong></em> <span data-keyword="substring-nonempty"><strong><em>substring</em></strong></span><em> of </em><code>s</code><em> such that every character in </em><code>t</code><em> (<strong>including duplicates</strong>) is included in the window</em>. If there is no such substring, return <em>the empty string </em><code>&quot;&quot;</code>.</p>

<p>The testcases will be generated such that the answer is <strong>unique</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ADOBECODEBANC&quot;, t = &quot;ABC&quot;
<strong>Output:</strong> &quot;BANC&quot;
<strong>Explanation:</strong> The minimum window substring &quot;BANC&quot; includes &#39;A&#39;, &#39;B&#39;, and &#39;C&#39; from string t.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a&quot;, t = &quot;a&quot;
<strong>Output:</strong> &quot;a&quot;
<strong>Explanation:</strong> The entire string s is the minimum window.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a&quot;, t = &quot;aa&quot;
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> Both &#39;a&#39;s from t must be included in the window.
Since the largest window of s only has one &#39;a&#39;, return empty string.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == s.length</code></li>
	<li><code>n == t.length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> and <code>t</code> consist of uppercase and lowercase English letters.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you find an algorithm that runs in <code>O(m + n)</code> time?</p>

## Approach: Sliding Window

This solution uses the Sliding Window technique with two frequency maps to find the shortest substring in `s` that contains all the characters (including duplicates) required by `t`.

1. **State Definition:** 
   * `tmap`: A frequency map storing the exact count of each character required by string `t`.
   * `windowMap`: A frequency map tracking the characters currently inside our sliding window in `s`.
   * `matchCount`: An integer tracking how many unique characters from `t` currently have their required frequency satisfied inside the window.
2. **Window Expansion (Finding a Valid Window):** 
   We iterate a `right` pointer across the string `s`. For each character, we add it to our `windowMap`. If this character is part of `t` and its frequency in our window exactly matches its required frequency in `tmap`, we increment our `matchCount`.
3. **Window Contraction (Optimizing the Window):** 
   Whenever `matchCount == tmap.size()`, our window contains all necessary characters and is considered "valid". Now, we want to make it as small as possible:
   * We record the current window size if it's strictly smaller than our historical minimum (`minLen`).
   * We shrink the window from the left by decrementing the frequency of the character at the `left` pointer in our `windowMap` and moving `left` forward.
   * If the removed character was part of `t` and its frequency in the window drops *below* the required frequency, we decrement `matchCount`. This breaks the inner loop, forcing the outer loop to expand the `right` pointer again to find a new valid window.
4. **Final Result:** 
   After traversing the entire string, we extract the substring using our recorded `minLeft` and `minLen`. If `minLen` was never updated (remains `Integer.MAX_VALUE`), no valid window was found, so we return an empty string.

## Complexity Analysis

* **Time Complexity:** $O(|S| + |T|)$
  Where $|S|$ and $|T|$ are the lengths of strings `s` and `t`. Populating the `tmap` takes $O(|T|)$ time. In the main algorithm, both the `left` and `right` pointers only move forward. In the worst-case scenario, each character in `s` is visited exactly twice (once by `right` to add it, and once by `left` to remove it). Hash map operations (put/get) operate in $O(1)$ average time, yielding a linear overall time complexity.
* **Space Complexity:** $O(U_S + U_T)$
  Where $U_S$ and $U_T$ are the number of unique characters in `s` and `t`. We use two HashMaps to store character frequencies. If the character set is bounded (e.g., standard ASCII), the maximum number of keys is 128 or 256, meaning the space complexity simplifies to $O(1)$ auxiliary space.
