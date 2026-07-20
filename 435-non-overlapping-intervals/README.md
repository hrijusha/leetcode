<h2><a href="https://leetcode.com/problems/non-overlapping-intervals">Non-overlapping Intervals</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>Given an array of intervals <code>intervals</code> where <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code>, return <em>the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping</em>.</p>

<p><strong>Note</strong> that intervals which only touch at a point are <strong>non-overlapping</strong>. For example, <code>[1, 2]</code> and <code>[2, 3]</code> are non-overlapping.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,2],[2,3],[3,4],[1,3]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> [1,3] can be removed and the rest of the intervals are non-overlapping.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,2],[1,2],[1,2]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> You need to remove two [1,2] to make the rest of the intervals non-overlapping.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,2],[2,3]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> You don&#39;t need to remove any of the intervals since they&#39;re already non-overlapping.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= intervals.length &lt;= 10<sup>5</sup></code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>-5 * 10<sup>4</sup> &lt;= start<sub>i</sub> &lt; end<sub>i</sub> &lt;= 5 * 10<sup>4</sup></code></li>
</ul>

## Approach: Greedy Algorithm

This solution uses a Greedy approach to find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping. The core idea is to always keep the interval that ends the earliest, as it leaves the most room for subsequent intervals.

1. **Sorting (The Greedy Choice):** 
   We sort the `intervals` array based on their **end times** in ascending order. By picking intervals that end as early as possible, we maximize the available space for future intervals (which minimizes the number of removals needed).
2. **Initialization:** 
   We keep track of the end time of the last safely kept interval using `prevEnd` (initialized to the end time of the very first interval). We also initialize a `count` to $0$ to track how many intervals we must erase.
3. **Iteration and Overlap Detection:** 
   We iterate through the remaining intervals starting from index $1$. For each interval, we compare its start time (`currStart`) with the end time of the last kept interval (`prevEnd`).
   * **Overlap:** If `currStart < prevEnd`, the current interval overlaps with the previously kept one. Because we sorted by end times, the current interval ends after (or at the same time as) `prevEnd`. To minimize removals, we keep the previous interval and "erase" the current one by incrementing `count`.
   * **No Overlap:** If `currStart >= prevEnd`, there is no overlap. We can safely keep this current interval. We update `prevEnd` to be the current interval's end time (`currEnd`) and move forward. 
4. **Final Result:** 
   After evaluating all intervals, `count` will hold the minimum number of overlapping intervals we had to skip/erase.

## Complexity Analysis

* **Time Complexity:** $O(N \log N)$
  Where $N$ is the number of intervals. Sorting the 2D array dominates the time complexity, taking $O(N \log N)$ time. The subsequent iteration through the array requires a single pass, which takes $O(N)$ time.
* **Space Complexity:** $O(\log N)$ to $O(N)$
  We do not allocate any additional data structures that scale with the input size. However, the space complexity depends on the underlying sorting algorithm used by the language (in Java, `Arrays.sort()` on objects/2D arrays uses TimSort, which can require up to $O(N)$ auxiliary space in the worst case, though average-case space overhead for general sorting is often considered $O(\log N)$).
