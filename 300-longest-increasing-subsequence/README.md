<h2><a href="https://leetcode.com/problems/longest-increasing-subsequence">Longest Increasing Subsequence</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>Given an integer array <code>nums</code>, return <em>the length of the longest <strong>strictly increasing </strong></em><span data-keyword="subsequence-array"><em><strong>subsequence</strong></em></span>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,9,2,5,3,7,101,18]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,0,3,2,3]
<strong>Output:</strong> 4
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [7,7,7,7,7,7,7]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2500</code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><b>Follow up:</b>&nbsp;Can you come up with an algorithm that runs in&nbsp;<code>O(n log(n))</code> time complexity?</p>

## Approach: Dynamic Programming

This solution uses a Bottom-Up Dynamic Programming approach to find the length of the longest strictly increasing subsequence.

1. **State Definition:** 
   We use an array `dp` of the same length as the input array `nums`. The value at `dp[i]` represents the length of the longest increasing subsequence that strictly *ends* at index `i`.
2. **Initialization:** 
   Every single element is intrinsically a valid increasing subsequence of length $1$ (itself). Therefore, we initialize the entire `dp` array with $1$. We also maintain a `maxLength` variable, initialized to $1$, to track the global maximum length found.
3. **State Transition:** 
   We use two nested loops. The outer loop `i` iterates through the array from the second element to the end. The inner loop `j` looks back at all previous elements (from index $0$ to $i-1$).
   * If we find a previous element that is strictly smaller than the current element (`nums[i] > nums[j]`), it means we can extend the subsequence ending at `j` by appending `nums[i]`.
   * The recurrence relation is:
     $$dp[i] = \max(dp[i], dp[j] + 1)$$
4. **Final Result:** 
   As we build up the `dp` array, we continuously update our `maxLength` variable with the highest value seen so far. Finally, we return `maxLength`, which holds the length of the longest increasing subsequence in the entire array.

## Complexity Analysis

* **Time Complexity:** $O(n^2)$
  Where $n$ is the length of the `nums` array. We have two nested loops: the outer loop iterates $n$ times, and for each iteration $i$, the inner loop iterates $i$ times. This results in $1 + 2 + 3 + \dots + (n-1)$ operations, which simplifies to $O(n^2)$.
* **Space Complexity:** $O(n)$
  Where $n$ is the length of the `nums` array. We require an additional 1D array `dp` of size $n$ to store the length of the longest increasing subsequence up to each index.
