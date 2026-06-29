<h2><a href="https://leetcode.com/problems/merge-intervals">Merge Intervals</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>Given an array&nbsp;of <code>intervals</code>&nbsp;where <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code>, merge all overlapping intervals, and return <em>an array of the non-overlapping intervals that cover all the intervals in the input</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,3],[2,6],[8,10],[15,18]]
<strong>Output:</strong> [[1,6],[8,10],[15,18]]
<strong>Explanation:</strong> Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,4],[4,5]]
<strong>Output:</strong> [[1,5]]
<strong>Explanation:</strong> Intervals [1,4] and [4,5] are considered overlapping.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[4,7],[1,4]]
<strong>Output:</strong> [[1,7]]
<strong>Explanation:</strong> Intervals [1,4] and [4,7] are considered overlapping.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= intervals.length &lt;= 10<sup>4</sup></code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
</ul>

## Approach
This solution uses a **Sorting** approach combined with a linear scan to efficiently merge overlaps:

1. **Sort the Intervals:** The array is first sorted in ascending order based on the starting point of each interval. This guarantees that any overlapping intervals will be adjacent to each other in our sorted list.
2. **Track the Current Interval:** We initialize `prevStart` and `prevEnd` with the start and end values of the very first interval. We also create an empty `ArrayList` to store our final merged intervals.
3. **Iterate and Merge:** We loop through the rest of the intervals one by one:
   * **If they overlap** (current `start` $\le$ `prevEnd`): We merge them by updating `prevEnd` to be the maximum of the current `prevEnd` and the current interval's `end`.
   * **If they don't overlap:** The previous interval is fully merged and finalized. We add `[prevStart, prevEnd]` to our result list, and reset `prevStart` and `prevEnd` to match the new, current interval.
4. **Finalize:** Once the loop completes, we add the final tracked interval to the list and convert the `ArrayList` back into a 2D array to return it.

## Complexity Analysis

* **Time Complexity:** $O(N \log N)$
  * Sorting the array of intervals takes $O(N \log N)$ time, where $N$ is the total number of intervals. 
  * The linear scan to merge the intervals takes $O(N)$ time. 
  * Because $O(N \log N)$ grows faster than $O(N)$, the sorting step dominates the overall time complexity.

* **Space Complexity:** $O(N)$
  * We use an `ArrayList` to store the merged intervals before converting them back to an array. In the worst-case scenario (where absolutely no intervals overlap), this list will store all $N$ intervals, requiring $O(N)$ extra space.
  * *(Note: The sorting algorithm used by `Arrays.sort()` may also consume up to $O(N)$ auxiliary space depending on the data type and underlying implementation).*
