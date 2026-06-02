<h2><a href="https://leetcode.com/problems/longest-palindromic-substring">Longest Palindromic Substring</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>Given a string <code>s</code>, return <em>the longest</em> <span data-keyword="palindromic-string"><em>palindromic</em></span> <span data-keyword="substring-nonempty"><em>substring</em></span> in <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;babad&quot;
<strong>Output:</strong> &quot;bab&quot;
<strong>Explanation:</strong> &quot;aba&quot; is also a valid answer.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cbbd&quot;
<strong>Output:</strong> &quot;bb&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consist of only digits and English letters.</li>
</ul>

```java
class Solution {
    public String longestPalindrome(String s) {
        int globalMax = 0;
        int start = 0;
        
        if (s == null || s.length() < 1)
            return "";
            
        for (int i = 0; i < s.length(); i++) {
            int oddLen = findLargestPalindromeLength(s, i, i);
            int evenLen = findLargestPalindromeLength(s, i, i + 1);
            int maxLen = Math.max(oddLen, evenLen);
            if (maxLen > globalMax) {
                globalMax = maxLen;
                start = i - (maxLen - 1) / 2; 
            }
        }
        
        return s.substring(start, start + globalMax);
    }

    private int findLargestPalindromeLength(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;   
    }
}
```
