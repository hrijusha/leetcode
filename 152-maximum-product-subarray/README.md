<h2><a href="https://leetcode.com/problems/maximum-product-subarray">Maximum Product Subarray</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>Given an integer array <code>nums</code>, find a <span data-keyword="subarray-nonempty">subarray</span> that has the largest product, and return <em>the product</em>.</p>

<p>The test cases are generated so that the answer will fit in a <strong>32-bit</strong> integer.</p>

<p><strong>Note</strong> that the product of an array with a single element is the value of that element.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,-2,4]
<strong>Output:</strong> 6
<strong>Explanation:</strong> [2,3] has the largest product 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-2,0,-1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The result cannot be 2, because [-2,-1] is not a subarray.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
	<li>The product of any subarray of <code>nums</code> is <strong>guaranteed</strong> to fit in a <strong>32-bit</strong> integer.</li>
</ul>

## **Core Logic:**
1. **Initialization:** We initialize our `max`, `min`, and `maxP` (global max) state variables with the first element of the array.
2. **Iteration:** We traverse the array starting from the second element.
3. **State Evaluation:** At each step, the new local maximum and local minimum can originate from three possibilities:
   * The current number itself (abandoning the previous sequence to start a new subarray).
   * The current number multiplied by the previous local maximum.
   * The current number multiplied by the previous local minimum (multiplying a highly negative minimum by a negative current number yields a high positive number).
4. **Updating:** We calculate the new local maximum and minimum from these three choices. A temporary variable is used to hold the previous maximum to prevent state overwriting. We then update our `maxP` if the new local maximum is strictly greater.

## Complexity Analysis
* **Time Complexity:** **`O(N)`** We iterate through the given array of length *N* exactly once. All operations inside the loop (comparisons and basic arithmetic) execute in constant time `O(1)`.
* **Space Complexity:** **`O(1)`** We optimize the space complexity by only tracking a few integer variables (`max`, `min`, `tempMax`, and `maxP`) at each step rather than maintaining an entire dynamic programming array. This requires strictly constant extra space.
