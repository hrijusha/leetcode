<h2><a href="https://leetcode.com/problems/decode-ways">Decode Ways</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>You have intercepted a secret message encoded as a string of numbers. The message is <strong>decoded</strong> via the following mapping:</p>

<p><code>&quot;1&quot; -&gt; &#39;A&#39;<br />
&quot;2&quot; -&gt; &#39;B&#39;<br />
...<br />
&quot;25&quot; -&gt; &#39;Y&#39;<br />
&quot;26&quot; -&gt; &#39;Z&#39;</code></p>

<p>However, while decoding the message, you realize that there are many different ways you can decode the message because some codes are contained in other codes (<code>&quot;2&quot;</code> and <code>&quot;5&quot;</code> vs <code>&quot;25&quot;</code>).</p>

<p>For example, <code>&quot;11106&quot;</code> can be decoded into:</p>

<ul>
	<li><code>&quot;AAJF&quot;</code> with the grouping <code>(1, 1, 10, 6)</code></li>
	<li><code>&quot;KJF&quot;</code> with the grouping <code>(11, 10, 6)</code></li>
	<li>The grouping <code>(1, 11, 06)</code> is invalid because <code>&quot;06&quot;</code> is not a valid code (only <code>&quot;6&quot;</code> is valid).</li>
</ul>

<p>Note: there may be strings that are impossible to decode.<br />
<br />
Given a string s containing only digits, return the <strong>number of ways</strong> to <strong>decode</strong> it. If the entire string cannot be decoded in any valid way, return <code>0</code>.</p>

<p>The test cases are generated so that the answer fits in a <strong>32-bit</strong> integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;12&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>&quot;12&quot; could be decoded as &quot;AB&quot; (1 2) or &quot;L&quot; (12).</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;226&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>&quot;226&quot; could be decoded as &quot;BZ&quot; (2 26), &quot;VF&quot; (22 6), or &quot;BBF&quot; (2 2 6).</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;06&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>&quot;06&quot; cannot be mapped to &quot;F&quot; because of the leading zero (&quot;6&quot; is different from &quot;06&quot;). In this case, the string is not a valid encoding, so return 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> contains only digits and may contain leading zero(s).</li>
</ul>

## Approach: Top-Down Dynamic Programming (Memoization)

This solution uses a recursive Depth-First Search (DFS) approach with memoization to count all possible valid decodings of a digit string. At each step, we explore building a decoding by taking either one or two digits, much like a decision tree, while caching the results to avoid redundant work.

1. **State Definition:** 
   We define a recursive helper function `decode(s, i, dp)` that returns the number of valid ways to decode the substring starting at index `i`. We use a 1D array `dp` of the same length as the string to cache our results, initialized with `-1`.
2. **Base Cases:** 
   * **Success:** If `i == s.length()`, we have successfully reached the end of the string, meaning our current sequence of choices forms a valid decoding path. We return $1$ to count this path.
   * **Failure:** If `s.charAt(i) == '0'`, we hit a dead end. In the problem's mapping (1-26), no encoding starts with a `'0'` (e.g., "06" is not valid for 'F'). We return $0$ because this branch cannot yield a valid decoding.
3. **Memoization (Caching):** 
   Before branching, we check if `dp[i]` is not `-1`. If it has a cached value, we've already computed the number of ways to decode the remaining string from this index, and we return it immediately.
4. **State Transition (Branching):** 
   From any valid index, we can potentially take two paths:
   * **Single-digit decode:** Since we already verified the current character isn't `'0'`, it must be a valid single digit (1-9). We add the number of ways to decode the rest of the string (`decode(s, i + 1, dp)`).
   * **Two-digit decode:** We check if there is a next character (`i + 1 < s.length()`) AND if the two-digit combination falls between 10 and 26. This is true if the current digit is `'1'`, or if it's `'2'` and the next digit is between `'0'` and `'6'`. If valid, we add the ways to decode the remainder (`decode(s, i + 2, dp)`).
   
   The total paths from index `i` is the sum of these two branches. We store this result in `dp[i]` and return it.

## Complexity Analysis

* **Time Complexity:** $O(n)$
  Where $n$ is the length of the string `s`. Because of our `dp` array, the number of ways to decode the suffix starting at each index `i` is computed exactly once. The recursion tree is pruned, reducing an otherwise exponential $O(2^n)$ time complexity down to linear time.
* **Space Complexity:** $O(n)$
  We allocate a 1D `dp` array of size $n$, which takes $O(n)$ space. Additionally, the maximum depth of the recursive call stack will be $n$ frames (in the case where we only take single-digit branches) before hitting the base case. The overall auxiliary space complexity is $O(n)$.
