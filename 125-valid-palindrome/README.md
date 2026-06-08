<h2><a href="https://leetcode.com/problems/valid-palindrome">Valid Palindrome</a></h2> <img src='https://img.shields.io/badge/Difficulty-Easy-brightgreen' alt='Difficulty: Easy' /><hr><p>A phrase is a <strong>palindrome</strong> if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.</p>

<p>Given a string <code>s</code>, return <code>true</code><em> if it is a <strong>palindrome</strong>, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;A man, a plan, a canal: Panama&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> &quot;amanaplanacanalpanama&quot; is a palindrome.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;race a car&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> &quot;raceacar&quot; is not a palindrome.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot; &quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> s is an empty string &quot;&quot; after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of printable ASCII characters.</li>
</ul>

**Core Logic:**
1. **String Sanitization:** * We iterate through the original string, ignoring spaces and punctuation.
   * Using a `StringBuilder`, we extract only the alphanumeric characters and convert them to lowercase.
   * This guarantees we are only comparing valid characters.
2. **Two-Pointer Comparison:**
   * If the sanitized string is empty, it is technically a palindrome, so we return `true`.
   * We place one pointer at the beginning (`left`) and one at the end (`right`) of the cleaned string.
   * We move the pointers toward the center, comparing the characters at each step.
   * If any characters mismatch, it is not a palindrome. If the pointers meet in the middle without any mismatches, it is a valid palindrome.

## Complexity Analysis
* **Time Complexity:** **`O(N)`** Where `N` is the length of the string. We iterate through the string once to clean it `O(N)`, and then we iterate through at most half of the cleaned string to check for the palindrome `O(N/2)`. This simplifies to linear time, `O(N)`.
* **Space Complexity:** **`O(N)`**
  Creating the intermediate cleaned string using `StringBuilder` and `toCharArray()` requires allocating new memory proportional to the size of the original string.
