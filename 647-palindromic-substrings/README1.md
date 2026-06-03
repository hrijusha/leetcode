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

## 💡 Approach: Brute Force (Check All Substrings)

This is the most straightforward, naive way to solve the problem. The core idea is to generate every single possible substring from the given string, and then individually verify if each one is a palindrome.

1. **Generate Substrings:** We use two nested loops (`i` and `j`) to define the starting and ending indices of every possible substring. 
2. **Verify:** For each substring generated, we pass it to a helper function `checkPalindrome`.
3. **Check Logic:** The helper function creates a physical copy of the substring, places a pointer at the beginning (`left`) and the end (`right`), and moves them towards the center. If the characters at the pointers ever mismatch, it's not a palindrome. 
4. **Count:** Every time the helper function returns `true`, we increment our running counter.

---

## 💻 Java Implementation

```java
class Solution {
    public int countSubstrings(String s) {
        int count = 0;
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (checkPalindrome(s, i, j)) {
                    count++;
                }
            }
        }
        
        return count;
    }

    private boolean checkPalindrome(String s, int i, int j) {
        String sub = s.substring(i, j + 1);
        
        int left = 0;
        int right = sub.length() - 1;
        
        while (left < right) {
            if (sub.charAt(left) != sub.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
}
```
## ⏱️ Complexity Analysis

**Approach:** Brute Force (Check All Substrings)

### Time Complexity: $\mathcal{O}(n^3)$
The time complexity is cubic due to three nested operations:
* **Generating Substrings:** The two outer `for` loops (`i` and `j`) define the start and end boundaries for every possible substring. This generates exactly $\frac{n(n+1)}{2}$ substrings, which takes $\mathcal{O}(n^2)$ iterations.
* **Substring Creation:** Inside the inner loop, `s.substring(i, j + 1)` creates a brand new copy of the characters in memory. Copying a string of up to length $n$ takes $\mathcal{O}(n)$ time.
* **Palindrome Verification:** The `while` loop inside the `checkPalindrome` helper method iterates through half of the new substring, taking up to $\mathcal{O}(n)$ time.
* **Total Operations:** Multiplying the $\mathcal{O}(n^2)$ loop iterations by the $\mathcal{O}(n)$ inner operations (substring creation + verification) gives an overall time complexity of $\mathcal{O}(n^3)$.

### Space Complexity: $\mathcal{O}(n)$
* **Auxiliary Space:** Inside the `checkPalindrome` method, the line `String sub = s.substring(i, j + 1);` allocates a new string object in memory during every single check. The maximum length of this newly created string is $n$ (when the substring spans the entire word). Therefore, the extra space required scales linearly with the input size, resulting in $\mathcal{O}(n)$ space complexity.
