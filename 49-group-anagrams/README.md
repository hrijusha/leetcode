<h2><a href="https://leetcode.com/problems/group-anagrams">Group Anagrams</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>Given an array of strings <code>strs</code>, group the <span data-keyword="anagram">anagrams</span> together. You can return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">strs = [&quot;eat&quot;,&quot;tea&quot;,&quot;tan&quot;,&quot;ate&quot;,&quot;nat&quot;,&quot;bat&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[&quot;bat&quot;],[&quot;nat&quot;,&quot;tan&quot;],[&quot;ate&quot;,&quot;eat&quot;,&quot;tea&quot;]]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>There is no string in strs that can be rearranged to form <code>&quot;bat&quot;</code>.</li>
	<li>The strings <code>&quot;nat&quot;</code> and <code>&quot;tan&quot;</code> are anagrams as they can be rearranged to form each other.</li>
	<li>The strings <code>&quot;ate&quot;</code>, <code>&quot;eat&quot;</code>, and <code>&quot;tea&quot;</code> are anagrams as they can be rearranged to form each other.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">strs = [&quot;&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[&quot;&quot;]]</span></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">strs = [&quot;a&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[&quot;a&quot;]]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= strs[i].length &lt;= 100</code></li>
	<li><code>strs[i]</code> consists of lowercase English letters.</li>
</ul>

## 🧠 Approach: Categorization by Sorting

The core property of anagrams is that they are composed of the exact same characters, just in a different order. Therefore, if we take two anagrams (like `"eat"` and `"tea"`) and sort their characters alphabetically, they will both result in the exact same string (`"aet"`).

We can use this sorted string as a unique "signature" or "key". By using a HashMap, we can group all the original strings that share the same sorted signature together in a list.

### Algorithm
1. Initialize a `HashMap` where the key is a `String` (the sorted signature) and the value is a `List<String>` (the grouped anagrams).
2. Iterate through each string `str` in the input array `strs`.
3. Convert the current string into a character array, sort it, and convert it back to a string (`sortedString`).
4. Check if `sortedString` already exists in the map:
   - If it does, retrieve the corresponding list and add the original `str` to it.
   - If it doesn't, create a new list, add the original `str`, and put the new key-value pair into the map.
5. Extract all the grouped lists from the map using `map.values()` and return them as a `List<List<String>>`.

> **💡 Java Pro-Tip:** In modern Java (Java 8+), your entire `if/else` block for checking and updating the map can be condensed into a single line using `computeIfAbsent`:
> `map.computeIfAbsent(sortedString, k -> new ArrayList<>()).add(str);`

## ⏱️ Complexity Analysis

* **Time Complexity:** `O(N * K log K)`
  * Where `N` is the total number of strings in the input array, and `K` is the maximum length of a string.
  * We iterate through the array of `N` strings once. For each string, sorting the character array takes `O(K log K)` time. 
* **Space Complexity:** `O(N * K)`
  * Where `N` is the number of strings and `K` is the maximum length of a string.
  * In the worst-case scenario, the HashMap will store every single string inside its lists, and the sorted string keys will also take up space in memory.
