<h2><a href="https://leetcode.com/problems/find-median-from-data-stream">Find Median from Data Stream</a></h2> <img src='https://img.shields.io/badge/Difficulty-Hard-red' alt='Difficulty: Hard' /><hr><p>The <strong>median</strong> is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.</p>

<ul>
	<li>For example, for <code>arr = [2,3,4]</code>, the median is <code>3</code>.</li>
	<li>For example, for <code>arr = [2,3]</code>, the median is <code>(2 + 3) / 2 = 2.5</code>.</li>
</ul>

<p>Implement the MedianFinder class:</p>

<ul>
	<li><code>MedianFinder()</code> initializes the <code>MedianFinder</code> object.</li>
	<li><code>void addNum(int num)</code> adds the integer <code>num</code> from the data stream to the data structure.</li>
	<li><code>double findMedian()</code> returns the median of all elements so far. Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;MedianFinder&quot;, &quot;addNum&quot;, &quot;addNum&quot;, &quot;findMedian&quot;, &quot;addNum&quot;, &quot;findMedian&quot;]
[[], [1], [2], [], [3], []]
<strong>Output</strong>
[null, null, null, 1.5, null, 2.0]

<strong>Explanation</strong>
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-10<sup>5</sup> &lt;= num &lt;= 10<sup>5</sup></code></li>
	<li>There will be at least one element in the data structure before calling <code>findMedian</code>.</li>
	<li>At most <code>5 * 10<sup>4</sup></code> calls will be made to <code>addNum</code> and <code>findMedian</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong></p>

<ul>
	<li>If all integer numbers from the stream are in the range <code>[0, 100]</code>, how would you optimize your solution?</li>
	<li>If <code>99%</code> of all integer numbers from the stream are in the range <code>[0, 100]</code>, how would you optimize your solution?</li>
</ul>

## Approach: Two Heaps (Min-Heap and Max-Heap)

This solution uses two priority queues to maintain a running median. The core idea is to split the incoming data stream into two halves: a lower half and an upper half. 

1. **State Definition:** 
   * `leftHeap` (Max-Heap): Stores the smaller half of the numbers. The root (peek) will always be the largest number in this lower half.
   * `rightHeap` (Min-Heap): Stores the larger half of the numbers. The root (peek) will always be the smallest number in this upper half.
2. **Adding a Number (`addNum`):** 
   To maintain the correct order and balance, inserting a number is a three-step process:
   * **Insert:** We first add the new number to the `leftHeap` (the smaller half).
   * **Rebalance Values:** To ensure that all numbers in the `leftHeap` are truly smaller than or equal to the numbers in the `rightHeap`, we immediately remove the largest element from `leftHeap` and push it into the `rightHeap`.
   * **Rebalance Sizes:** We want to enforce a size constraint where `leftHeap` is always either equal in size to `rightHeap`, or exactly $1$ element larger. If the `rightHeap` becomes larger than the `leftHeap` during the previous step, we pop the smallest element from `rightHeap` and move it back to `leftHeap`.
3. **Finding the Median (`findMedian`):** 
   * If the total amount of numbers is odd, `leftHeap` will have exactly one more element than `rightHeap` due to our size constraint. The median is simply the top element of `leftHeap`.
   * If the total amount of numbers is even, both heaps are of equal size. The median is the average of the two middle values, which are readily available at the top of `leftHeap` and the top of `rightHeap`.

## Complexity Analysis

* **Time Complexity:** 
  * `addNum(int num)`: $O(\log n)$
    Where $n$ is the total number of elements added so far. In the worst case, we perform three heap operations (insertions and extractions). Adding to or extracting from a Priority Queue takes logarithmic time $O(\log n)$.
  * `findMedian()`: $O(1)$
    Retrieving the median only requires peeking at the root of one or both heaps, which is a constant time operation.
* **Space Complexity:** $O(n)$
  Where $n$ is the total number of elements added from the data stream. All elements are stored across the two priority queues, taking linear auxiliary space.
