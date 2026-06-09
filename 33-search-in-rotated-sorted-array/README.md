<h2><a href="https://leetcode.com/problems/search-in-rotated-sorted-array">Search in Rotated Sorted Array</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>There is an integer array <code>nums</code> sorted in ascending order (with <strong>distinct</strong> values).</p>

<p>Prior to being passed to your function, <code>nums</code> is <strong>possibly left rotated</strong> at an unknown index <code>k</code> (<code>1 &lt;= k &lt; nums.length</code>) such that the resulting array is <code>[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]</code> (<strong>0-indexed</strong>). For example, <code>[0,1,2,4,5,6,7]</code> might be left rotated by&nbsp;<code>3</code>&nbsp;indices and become <code>[4,5,6,7,0,1,2]</code>.</p>

<p>Given the array <code>nums</code> <strong>after</strong> the possible rotation and an integer <code>target</code>, return <em>the index of </em><code>target</code><em> if it is in </em><code>nums</code><em>, or </em><code>-1</code><em> if it is not in </em><code>nums</code>.</p>

<p>You must write an algorithm with <code>O(log n)</code> runtime complexity.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [4,5,6,7,0,1,2], target = 0
<strong>Output:</strong> 4
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [4,5,6,7,0,1,2], target = 3
<strong>Output:</strong> -1
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> nums = [1], target = 0
<strong>Output:</strong> -1
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5000</code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li>All values of <code>nums</code> are <strong>unique</strong>.</li>
	<li><code>nums</code> is an ascending array that is possibly rotated.</li>
	<li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li>
</ul>

## Approach: Modified Binary Search
Normally, Binary Search requires a perfectly sorted array so you can confidently eliminate half the search space at every step. In a rotated sorted array, splitting the array in half guarantees that at least *one* of the halves will be perfectly sorted. We can leverage this to figure out where our target is.

**Core Logic:**
1. **Initialize Pointers:** Set `left` to the start of the array and `right` to the end.
2. **Find the Midpoint:** Calculate `mid` and check if `nums[mid]` is the target. If so, return `mid`.
3. **Identify the Sorted Half:** At every step, we must determine which side of `mid` is perfectly sorted:
   * **Left half is sorted (`nums[left] <= nums[mid]`):**
     We check if the `target` falls within the strict boundaries of this sorted left half (`target >= nums[left]` and `target < nums[mid]`).
     * If it does, we discard the right half (`right = mid - 1`).
     * If it doesn't, the target *must* be in the unsorted right half (`left = mid + 1`).
   * **Right half is sorted (the `else` block):**
     We check if the `target` falls within the strict boundaries of this sorted right half (`target > nums[mid]` and `target <= nums[right]`).
     * If it does, we discard the left half (`left = mid + 1`).
     * If it doesn't, the target *must* be in the unsorted left half (`right = mid - 1`).
4. **Not Found:** If the loop finishes and the pointers cross without finding the target, we return `-1`.

## Complexity Analysis
* **Time Complexity:** **`O(log N)`** Where `N` is the length of the array. Even though the array is rotated, we are still successfully discarding half of the remaining search space during every iteration of the `while` loop.
* **Space Complexity:** **`O(1)`** We only maintain a few integer variables (`left`, `right`, `mid`) to track our indices, strictly requiring constant extra memory.
