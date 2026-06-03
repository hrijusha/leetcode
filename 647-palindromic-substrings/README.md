<h2><a href="https://leetcode.com/problems/palindromic-substrings">Palindromic Substrings</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>Given a string <code>s</code>, return <em>the number of <strong>palindromic substrings</strong> in it</em>.</p>

<p>A string is a <strong>palindrome</strong> when it reads the same backward as forward.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within the string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abc&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> Three palindromic strings: &quot;a&quot;, &quot;b&quot;, &quot;c&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaa&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> Six palindromic strings: &quot;a&quot;, &quot;a&quot;, &quot;a&quot;, &quot;aa&quot;, &quot;aa&quot;, &quot;aaa&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

---

## Approach: Expand Around Center

The most space-efficient way to solve this is the **Expand Around Center** approach. Every palindrome is symmetrical, mirroring around its center. Therefore, we can treat every character (and every space between characters) as a potential center and expand outwards to find all valid palindromes.

### Intuition
For a string of length $N$, there are $2N - 1$ possible centers:
1.  **Odd length palindromes:** The center is a single character (e.g., the `'b'` in `"aba"`).
2.  **Even length palindromes:** The center is the space between two characters (e.g., between the `'b'`s in `"abba"`).

We iterate through all possible centers. For each center, we expand our `left` and `right` pointers outwards as long as the characters match and the pointers stay within the bounds of the string.

---

## Complexity Analysis

* **Time Complexity:** $O(N^2)$
    * $N$ is the length of the string. We iterate through $2N - 1$ centers. In the worst-case scenario (a string of all identical characters), expanding outwards takes $N/2$ steps per center. 
* **Space Complexity:** $O(1)$
    * We only use a few integer variables for our pointers and counters, requiring no extra memory proportional to the string size.

---
