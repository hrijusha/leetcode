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

## 💡 Approach: Brute Force

```java
//Brute force
class Solution {
    public String longestPalindrome(String s) {
        int maxLen = 0;
        String longestPalindrome = "";
        if (s == null || s.length() < 1)
            return "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String s1 = s.substring(i, j + 1);
                if (checkPalindrome(s1)) {
                    if (s1.length() > maxLen) {
                        longestPalindrome = s1;
                        maxLen = s1.length();
                    }
                }
            }
        }
        return longestPalindrome;
    }

    private boolean checkPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
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

### Time Complexity: $\mathcal{O}(n^3)$
The time complexity is cubic due to the nested loops combined with string operations:
* **Generating Substrings:** The two outer `for` loops define the start (`i`) and end (`j`) boundaries for every possible substring. This results in generating $\frac{n(n+1)}{2}$ total substrings, which takes $\mathcal{O}(n^2)$ iterations.
* **Substring Creation & Palindrome Check:** Inside the inner loop, two operations depend on the length of the substring (which can be up to $n$ characters long):
  1. `s.substring(i, j + 1)`: In modern Java, this creates a completely new copy of the character array, taking $\mathcal{O}(n)$ time.
  2. `checkPalindrome(s1)`: This helper method iterates through half of the substring's characters, taking an additional $\mathcal{O}(n)$ time.
* **Total Operations:** Multiplying the $\mathcal{O}(n^2)$ loop iterations by the $\mathcal{O}(n)$ inner operations gives a final time complexity of $\mathcal{O}(n^2) \times \mathcal{O}(n) = \mathcal{O}(n^3)$. *(Note: This approach will almost certainly result in a "Time Limit Exceeded" error on large inputs).*

### Space Complexity: $\mathcal{O}(n)$
* **Auxiliary Space:** Inside the inner loop, `String s1 = s.substring(...)` creates a new string object in memory for every iteration. The maximum length of this string is $n$. Additionally, the variable `longestPalindrome` stores a string of up to length $n$. Therefore, the space complexity required to store these copied strings is linearly proportional to the length of the input string, resulting in $\mathcal{O}(n)$ space.

## 💡 Approach: Expand Around Center (Optimized)

Instead of generating every possible substring (which is inefficient), this optimized approach iterates through the string and treats each character as the **center** of a potential palindrome. From this center, we expand outwards to the left and right as long as the characters match.

Since palindromes can be of odd or even lengths, we must check two different centers for every index:
1. **Odd Length:** The center is a single character (e.g., `a` in `aba`). We expand starting at `(i, i)`.
2. **Even Length:** The center is *between* two characters (e.g., the center of `abba`). We expand starting at `(i, i + 1)`.

By keeping track of the maximum length found and doing a little math to calculate the starting index (`i - (maxLen - 1) / 2`), we avoid creating expensive substrings during the loop and only extract the final result at the very end.

---

## ⏱️ Complexity Analysis

### Time Complexity: $\mathcal{O}(n^2)$
The time complexity is quadratic, which is a massive improvement over the $\mathcal{O}(n^3)$ brute-force method:
* **Outer Loop:** Iterates through each of the $n$ characters in the string, contributing $\mathcal{O}(n)$.
* **Center Expansion (`findLargestPalindromeLength`):** For each character, we expand outwards. In the worst-case scenario (e.g., a string of entirely identical characters like `"aaaaa"`), this expansion takes up to $n/2$ steps, which is an $\mathcal{O}(n)$ operation.
* **Total Operations:** Multiplying the outer loop by the inner expansion gives $\mathcal{O}(n) \times \mathcal{O}(n) = \mathcal{O}(n^2)$. 

### Space Complexity: $\mathcal{O}(1)$
* **Auxiliary Space:** This algorithm only uses a few primitive integer variables (`globalMax`, `start`, `left`, `right`, `oddLen`, `evenLen`) to keep track of indices and lengths. 
* **String Allocation:** Unlike the brute-force approach, no new substrings are created inside the loops. The only string allocation happens at the very end during the final `s.substring()` call. Therefore, the auxiliary space used to run the algorithm is strictly constant $\mathcal{O}(1)$.
