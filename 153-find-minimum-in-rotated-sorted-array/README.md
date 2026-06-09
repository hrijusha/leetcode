<h2><a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array">Find Minimum in Rotated Sorted Array</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>Suppose an array of length <code>n</code> sorted in ascending order is <strong>rotated</strong> between <code>1</code> and <code>n</code> times. For example, the array <code>nums = [0,1,2,4,5,6,7]</code> might become:</p>

<ul>
	<li><code>[4,5,6,7,0,1,2]</code> if it was rotated <code>4</code> times.</li>
	<li><code>[0,1,2,4,5,6,7]</code> if it was rotated <code>7</code> times.</li>
</ul>

<p>Notice that <strong>rotating</strong> an array <code>[a[0], a[1], a[2], ..., a[n-1]]</code> 1 time results in the array <code>[a[n-1], a[0], a[1], a[2], ..., a[n-2]]</code>.</p>

<p>Given the sorted rotated array <code>nums</code> of <strong>unique</strong> elements, return <em>the minimum element of this array</em>.</p>

<p>You must write an algorithm that runs in&nbsp;<code>O(log n) time</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,5,1,2]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The original array was [1,2,3,4,5] rotated 3 times.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,5,6,7,0,1,2]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [11,13,15,17]
<strong>Output:</strong> 11
<strong>Explanation:</strong> The original array was [11,13,15,17] and it was rotated 4 times. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
	<li><code>-5000 &lt;= nums[i] &lt;= 5000</code></li>
	<li>All the integers of <code>nums</code> are <strong>unique</strong>.</li>
	<li><code>nums</code> is sorted and rotated between <code>1</code> and <code>n</code> times.</li>
</ul>

## Approach: Modified Binary Search
Because the array was originally sorted, finding the minimum element is equivalent to finding the "pivot" point where the rotation occurred (the point where the numbers drop from high to low). Since the required time complexity is `O(log n)`, we must use Binary Search.

**Core Logic:**
1. **Pointers:** Initialize a `left` pointer at the start of the array and a `right` pointer at the end.
2. **The Condition (`left < right`):** We use `left < right` instead of `left <= right` because we are narrowing down a range to find a single element. When `left` equals `right`, we have narrowed the search space down to one item, which must be our minimum.
3. **The Comparison:** At each step, we calculate the `mid` index and compare `nums[mid]` against `nums[right]`. 
   * **Case 1: `nums[mid] > nums[right]`**
     This implies the right half of the array is unsorted (it contains the pivot point). The minimum *must* be to the right of `mid`. We update `left = mid + 1`.
   * **Case 2: `nums[mid] <= nums[right]`**
     This implies the right half of the array is perfectly sorted. Therefore, the minimum must be in the left half. However, `mid` itself could be the minimum, so we cannot skip it. We update `right = mid`.

## Complexity Analysis
* **Time Complexity:** **`O(log N)`** Where `N` is the number of elements in the array. In each iteration of the `while` loop, we cut the search space in half.
* **Space Complexity:** **`O(1)`** We only allocate a few integer variables (`left`, `right`, `mid`), strictly requiring constant extra memory.
