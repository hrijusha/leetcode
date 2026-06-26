<h2><a href="https://leetcode.com/problems/counting-bits">Counting Bits</a></h2> <img src='https://img.shields.io/badge/Difficulty-Easy-brightgreen' alt='Difficulty: Easy' /><hr><p>Given an integer <code>n</code>, return <em>an array </em><code>ans</code><em> of length </em><code>n + 1</code><em> such that for each </em><code>i</code><em> </em>(<code>0 &lt;= i &lt;= n</code>)<em>, </em><code>ans[i]</code><em> is the <strong>number of </strong></em><code>1</code><em><strong>&#39;s</strong> in the binary representation of </em><code>i</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> [0,1,1]
<strong>Explanation:</strong>
0 --&gt; 0
1 --&gt; 1
2 --&gt; 10
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> [0,1,1,2,1,2]
<strong>Explanation:</strong>
0 --&gt; 0
1 --&gt; 1
2 --&gt; 10
3 --&gt; 11
4 --&gt; 100
5 --&gt; 101
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong></p>

<ul>
	<li>It is very easy to come up with a solution with a runtime of <code>O(n log n)</code>. Can you do it in linear time <code>O(n)</code> and possibly in a single pass?</li>
	<li>Can you do it without using any built-in function (i.e., like <code>__builtin_popcount</code> in C++)?</li>
</ul>

## 💡 Solution Approach

This solution determines the number of set bits (1s) for each number from `0` to `n` by leveraging **Java's String manipulation** and **binary conversion** built-in methods. 

Here is the step-by-step breakdown of the algorithm:
1. **Initialization:** Create a `result` integer array of size $n + 1$ to hold the bit counts for every number from $0$ up to $n$.
2. **Iteration & Binary Conversion:** Iterate through each number `i` from $0$ to $n$. For each number, convert it into a binary string representation using `Integer.toBinaryString(i)`.
3. **Counting the Bits (String Manipulation):** To find the number of `1`s, take the total length of the binary string and subtract the length of that same string after removing all the `1`s (done via `.replace("1", "")`). The difference gives the exact count of `1`s.
4. **Storage:** Store this calculated count into the `result` array at the corresponding index `i`.

## 📊 Complexity Analysis

* **Time Complexity:** $O(n \log n)$
  * The `for` loop iterates $n + 1$ times, which gives $O(n)$.
  * Inside the loop, `Integer.toBinaryString(i)` and the `String.replace()` operations iterate over the characters of the binary string. The length of a binary string for a number $i$ is proportional to $\log i$. 
  * Therefore, performing $O(\log n)$ string operations $n$ times results in an overall time complexity of $O(n \log n)$.
* **Space Complexity:** $O(n)$
  * **Output Space:** The `result` array requires $O(n)$ space to store the answers.
  * **Auxiliary Space:** $O(\log n)$ space is temporarily used during each iteration to store the binary string representations before they are garbage collected. Overall space complexity is dominated by the output array, making it $O(n)$.
