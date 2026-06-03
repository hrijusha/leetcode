<h2><a href="https://leetcode.com/problems/longest-substring-without-repeating-characters">Longest Substring Without Repeating Characters</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>Given a string <code>s</code>, find the length of the <strong>longest</strong> <span data-keyword="substring-nonempty"><strong>substring</strong></span> without duplicate characters.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcabcbb&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The answer is &quot;abc&quot;, with the length of 3. Note that <code>&quot;bca&quot;</code> and <code>&quot;cab&quot;</code> are also correct answers.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bbbbb&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> The answer is &quot;b&quot;, with the length of 1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;pwwkew&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The answer is &quot;wke&quot;, with the length of 3.
Notice that the answer must be a substring, &quot;pwke&quot; is a subsequence and not a substring.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code> consists of English letters, digits, symbols and spaces.</li>
</ul>

## 💡 Approach: Brute force
The Algorithm
Generate all substrings: Use two nested loops. The outer loop pointer i represents the starting index of the substring, and the inner loop pointer j represents the ending index.

Extract and verify: For every combination of i and j, isolate the substring s[i...j].

Check for uniqueness: Pass that specific substring into a helper function that iterates through it, using a Hash Set to track seen characters. If a character is already in the set, the substring is invalid.

Update the maximum: If the helper function confirms all characters are unique, compare the substring's length against your current maximum length and update it if it is larger.

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isUnique(s, i, j)) {
                    // Update maxLength if this valid substring is the longest we've seen
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        
        return maxLength;
    }
    
    // Helper function to verify if characters in s[start...end] are unique
    private boolean isUnique(String s, int start, int end) {
        Set<Character> seen = new HashSet<>();
        
        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            if (seen.contains(c)) {
                return false; // Duplicate found
            }
            seen.add(c);
        }
        
        return true; // All characters are unique
    }
}
```

## 💡 Approach: Sliding Window using HashSet

This approach uses the **Sliding Window** technique with two pointers (`left` and `right`) and a `HashSet`. 

We expand our window by moving the `right` pointer and adding characters to the set. If we encounter a character that is already in the set, we shrink the window from the `left` by removing characters from the set until the duplicate is gone. We continuously track the maximum window size.

### ⏱️ Complexity Analysis
* **Time Complexity:** `O(N)` in average cases (technically `O(2N)` worst case as each character might be visited by both pointers).
* **Space Complexity:** `O(K)` where `K` is the size of the character set (e.g., 26 for English letters or 128 for ASCII).
