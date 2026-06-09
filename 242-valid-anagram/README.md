<h2><a href="https://leetcode.com/problems/valid-anagram">Valid Anagram</a></h2> <img src='https://img.shields.io/badge/Difficulty-Easy-brightgreen' alt='Difficulty: Easy' /><hr><p>Given two strings <code>s</code> and <code>t</code>, return <code>true</code> if <code>t</code> is an <span data-keyword="anagram">anagram</span> of <code>s</code>, and <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;anagram&quot;, t = &quot;nagaram&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;rat&quot;, t = &quot;car&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code> and <code>t</code> consist of lowercase English letters.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> What if the inputs contain Unicode characters? How would you adapt your solution to such a case?</p>

## Approach: Frequency Counting (Hash Maps)
To determine if two strings are anagrams, we don't need to actually rearrange them. We just need to verify that both strings contain the exact same characters in the exact same quantities.

**Core Logic:**
1. **Early Exit:** If the lengths of string `s` and string `t` are different, it is mathematically impossible for them to be anagrams. We immediately return `false` to save processing time.
2. **State Tracking:** We create two `HashMap`s (`map1` and `map2`) to track the frequency of each character in both strings.
3. **Simultaneous Counting:** Because we know both strings are the same length at this point, we can iterate through them both in a single `for` loop. For every index `i`:
   * We extract the character from `s` and increment its count in `map1`.
   * We extract the character from `t` and increment its count in `map2`.
   * *Note: `getOrDefault(char, 0) + 1` is a clean way to handle characters we haven't seen yet.*
4. **Final Verification:** We simply return the result of `map1.equals(map2)`. In Java, this built-in method automatically checks if both maps have the same size, the same keys, and the exact same values for those keys.

## Complexity Analysis
* **Time Complexity:** **`O(N)`** Where `N` is the length of the strings. We iterate through the characters exactly once. Hash Map insertions and lookups operate in `O(1)` constant time. The final `.equals()` check also takes `O(N)` time in the worst case, keeping the overall time linear.
* **Space Complexity:** **`O(N)`** In the worst-case scenario (where all characters in the string are unique), our Hash Maps will grow proportionally to the size of the input strings. *(Note: If the input is restricted to just lowercase English letters, the space complexity is technically `O(1)` because the maps will never hold more than 26 key-value pairs, regardless of how long the strings are).*
