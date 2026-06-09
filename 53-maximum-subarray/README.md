<h2><a href="https://leetcode.com/problems/maximum-subarray">Maximum Subarray</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>Given an integer array <code>nums</code>, find the <span data-keyword="subarray-nonempty">subarray</span> with the largest sum, and return <em>its sum</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-2,1,-3,4,-1,2,1,-5,4]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The subarray [4,-1,2,1] has the largest sum 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The subarray [1] has the largest sum 1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,4,-1,7,8]
<strong>Output:</strong> 23
<strong>Explanation:</strong> The subarray [5,4,-1,7,8] has the largest sum 23.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> If you have figured out the <code>O(n)</code> solution, try coding another solution using the <strong>divide and conquer</strong> approach, which is more subtle.</p>

## Approach
The brute force way to solve this is to calculate the sum of every possible subarray, which takes `O(N^2)` time. 
**Core Logic:**
1. **The Choice:** As we iterate through the array, for every number `nums[i]`, we have to make a choice:
   * Do we add this number to our existing running subarray?
   * Or do we abandon the previous subarray and start a brand new one starting exactly at `nums[i]`?
2. **The Rule:** We only start a new subarray if the previous running sum is **negative**. A negative running sum will only ever drag down the value of the current number. We represent this mathematically with `Math.max(nums[i], currentSum + nums[i])`.
3. **State Tracking:** * `currentSum` keeps track of the best possible sum ending at the current index.
   * `maxSum` keeps track of the absolute highest `currentSum` we have ever seen across the entire iteration.
4. **Updating:** At each step, we update our `currentSum` using the rule above, and then immediately check if it beats our historical `maxSum`.

## Complexity Analysis
* **Time Complexity:** **`O(N)`** Where `N` is the number of elements in the array. We process every element in the array exactly once in a single `for` loop.
* **Space Complexity:** **`O(1)`** We optimize our space footprint by only maintaining two integer variables (`currentSum` and `maxSum`). We do not need an entirely new array to store previous computations, keeping the extra space strictly constant.
