<h2><a href="https://leetcode.com/problems/longest-repeating-character-replacement">Longest Repeating Character Replacement</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>You are given a string <code>s</code> and an integer <code>k</code>. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most <code>k</code> times.</p>

<p>Return <em>the length of the longest substring containing the same letter you can get after performing the above operations</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ABAB&quot;, k = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> Replace the two &#39;A&#39;s with two &#39;B&#39;s or vice versa.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;AABABBA&quot;, k = 1
<strong>Output:</strong> 4
<strong>Explanation:</strong> Replace the one &#39;A&#39; in the middle with &#39;B&#39; and form &quot;AABBBBA&quot;.
The substring &quot;BBBB&quot; has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only uppercase English letters.</li>
	<li><code>0 &lt;= k &lt;= s.length</code></li>
</ul>

## Approach: Sliding Window

This solution uses a Sliding Window (or Two Pointers) approach combined with a frequency map. The core idea is to find the longest window where the number of characters we need to replace to make all characters identical is less than or equal to `k`.

1. **State Definition:** 
   We maintain a window defined by a `left` and `right` pointer. We use a `HashMap` (`countMap`) to track the frequencies of the characters currently inside this window. We also maintain `maxCount`, which represents the frequency of the most abundant character in our current window.
2. **Window Expansion:** 
   We iterate the `right` pointer through the string, adding each new character to our `countMap` and updating `maxCount` if this character's frequency becomes the new highest.
3. **Validity Check:** 
   For any given window, the number of characters we *have* to replace is the total number of characters in the window minus the frequency of the most common character (`windowSize - maxCount`). 
   * If `windowSize - maxCount <= k`, the window is valid. We can achieve a uniform string with at most `k` replacements. We update our `maxSize`.
4. **Window Contraction:** 
   If `windowSize - maxCount > k`, the window is invalid (we need too many replacements). To fix this, we shrink the window from the left by decrementing the frequency of the character at the `left` pointer in our map and moving the `left` pointer one step forward. 
   *(Note: We don't need to decrement `maxCount` when the left pointer moves. We are only interested in finding a strictly longer valid window, which would require a historically higher `maxCount` anyway).*

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the length of the string `s`. Both the `right` and `left` pointers only move forward, meaning each character is processed at most twice (once when added to the window, once when removed). Updating and fetching from the HashMap takes $O(1)$ time.
* **Space Complexity:** $O(1)$ auxiliary space
  Although we use a `HashMap`, the problem constraints usually specify that the string consists only of uppercase English letters. Therefore, the map will store a maximum of $26$ key-value pairs. Since this is a constant upper bound regardless of the input string's length, the space complexity is $O(1)$.
