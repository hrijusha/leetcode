<h2><a href="https://leetcode.com/problems/longest-consecutive-sequence">Longest Consecutive Sequence</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>Given an unsorted array of integers <code>nums</code>, return <em>the length of the longest consecutive elements sequence.</em></p>

<p>You must write an algorithm that runs in&nbsp;<code>O(n)</code>&nbsp;time.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [100,4,200,1,3,2]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest consecutive elements sequence is <code>[1, 2, 3, 4]</code>. Therefore its length is 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,3,7,2,5,8,4,6,0,1]
<strong>Output:</strong> 9
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,0,1,2]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 🧠 Approach
Sorting an array is out of question because the solution has to be in O(n) time. Hashset was used because searching time complexity is O(1). 

### The Core Idea: Identify the "Start" of a Sequence
**Only start counting when we are at the absolute beginning of a sequence.** * A number `x` is the start of a sequence *only if* the number immediately preceding it (`x - 1`) does **not** exist in our HashSet.
Once you have identified the start, then start iterating through the set to find longest sequence.

## ⏱️ Complexity Analysis

### Time Complexity: $\mathcal{O}(n)$
Although there is an inner `while` loop nested inside an outer `for` loop, the exact time complexity is strictly bounded by $\mathcal{O}(n)$ (linear time). 
* **HashSet Insertion:** Inserting all elements from the array into the set takes amortized $\mathcal{O}(n)$ time.
* **Outer Loop Checks:** The outer loop iterates exactly $n$ times. The check to see if `num - 1` exists is an $\mathcal{O}(1)$ operation.
* **Inner Loop Executions:** The `while` loop *only* runs when a number is the start of a sequence. Because of this safeguard, the inner loop will visit each number in a valid sequence exactly once across the entire execution of the program. Thus, the inner loop runs at most $n$ times *in total*.
* **Total Operations:** $O(n) + O(n) + O(n) = \mathcal{O}(n)$.

### Space Complexity: $\mathcal{O}(n)$
* **Worst-Case Upper Bound:** To achieve $\mathcal{O}(1)$ instant lookups, the values must be copied into a `HashSet`. If the input array contains entirely unique numbers, the `HashSet` will need to store all $n$ integers, requiring $\mathcal{O}(n)$ auxiliary space.
* **Best-Case (For Context):** If the array consists entirely of duplicate values (e.g., `[5, 5, 5, 5]`), the `HashSet` stores only a single element, taking $\mathcal{O}(1)$ space. However, since complexity measures the upper bound, we officially classify the space complexity as $\mathcal{O}(n)$.
