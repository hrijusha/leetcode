<h2><a href="https://leetcode.com/problems/insert-interval">Insert Interval</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>You are given an array of non-overlapping intervals <code>intervals</code> where <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> represent the start and the end of the <code>i<sup>th</sup></code> interval and <code>intervals</code> is sorted in ascending order by <code>start<sub>i</sub></code>. You are also given an interval <code>newInterval = [start, end]</code> that represents the start and end of another interval.</p>

<p>Insert <code>newInterval</code> into <code>intervals</code> such that <code>intervals</code> is still sorted in ascending order by <code>start<sub>i</sub></code> and <code>intervals</code> still does not have any overlapping intervals (merge overlapping intervals if necessary).</p>

<p>Return <code>intervals</code><em> after the insertion</em>.</p>

<p><strong>Note</strong> that you don&#39;t need to modify <code>intervals</code> in-place. You can make a new array and return it.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,3],[6,9]], newInterval = [2,5]
<strong>Output:</strong> [[1,5],[6,9]]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
<strong>Output:</strong> [[1,2],[3,10],[12,16]]
<strong>Explanation:</strong> Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= intervals.length &lt;= 10<sup>4</sup></code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>intervals</code> is sorted by <code>start<sub>i</sub></code> in <strong>ascending</strong> order.</li>
	<li><code>newInterval.length == 2</code></li>
	<li><code>0 &lt;= start &lt;= end &lt;= 10<sup>5</sup></code></li>
</ul>

## Approach

Since the provided `intervals` array is already sorted and non-overlapping, this solution uses a highly efficient **Three-Phase Linear Scan** to insert and merge the new interval:

1. **Phase 1: Add Non-overlapping Left Intervals**
   We iterate through the array and add all intervals that end strictly *before* the `newInterval` starts. Because the array is sorted, these are guaranteed not to overlap.
2. **Phase 2: Merge Overlapping Intervals**
   As long as the current interval starts *before or exactly when* the `newInterval` ends, an overlap exists. We continuously expand our `newInterval` to encompass these overlaps by updating its start to the minimum start value and its end to the maximum end value. Once the overlaps stop, we add this fully merged `newInterval` to our result list.
3. **Phase 3: Add Non-overlapping Right Intervals**
   We take all remaining intervals in the array (which are guaranteed to start *after* our merged interval ends) and append them directly to the result list.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  * We process the `intervals` array in a single pass. Even though there are three separate `while` loops, the index `i` is only incremented sequentially from $0$ to $N$, meaning every interval is visited exactly once.
* **Space Complexity:** $O(N)$
  * We use an `ArrayList` to store the newly constructed intervals. In the worst-case scenario (e.g., no overlaps occur), this list will contain $N + 1$ elements, scaling linearly with the input size.
