<h2><a href="https://leetcode.com/problems/koko-eating-bananas">Koko Eating Bananas</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>Koko loves to eat bananas. There are <code>n</code> piles of bananas, the <code>i<sup>th</sup></code> pile has <code>piles[i]</code> bananas. The guards have gone and will come back in <code>h</code> hours.</p>

<p>Koko can decide her bananas-per-hour eating speed of <code>k</code>. Each hour, she chooses some pile of bananas and eats <code>k</code> bananas from that pile. If the pile has less than <code>k</code> bananas, she eats all of them instead and will not eat any more bananas during this hour.</p>

<p>Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.</p>

<p>Return <em>the minimum integer</em> <code>k</code> <em>such that she can eat all the bananas within</em> <code>h</code> <em>hours</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> piles = [3,6,7,11], h = 8
<strong>Output:</strong> 4
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> piles = [30,11,23,4,20], h = 5
<strong>Output:</strong> 30
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> piles = [30,11,23,4,20], h = 6
<strong>Output:</strong> 23
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= piles.length &lt;= 10<sup>4</sup></code></li>
	<li><code>piles.length &lt;= h &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= piles[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Approach: Binary Search on Answer Space

This solution utilizes the "Binary Search on Answer" technique. Instead of searching for an element within an array, we are searching for a valid integer (the eating speed, $k$) within a continuous range of possible answers.

1. **Define the Search Space:** 
   * **Minimum speed (`left`):** $1$ banana per hour (the slowest possible speed).
   * **Maximum speed (`right`):** The largest pile size in the array. Eating any faster than the largest pile doesn't save any time, because Koko can only eat at most one pile per hour anyway.
2. **Binary Search:** 
   We calculate a middle speed (`mid`) and simulate how long it would take Koko to eat all the bananas at that specific speed.
3. **Simulate Eating Time:** 
   For a given speed `mid`, the hours required to finish a pile of size `n` is $\lceil n / mid \rceil$. In Java, using `Math.ceil()` requires floating-point division which is slow and can cause precision issues. We use integer math instead: `(n + mid - 1) / mid` safely achieves the exact same ceiling division. We sum these hours up to get the total time (`sum`).
4. **Adjust the Boundaries:** 
   * If `sum <= h`, Koko finishes within the time limit. This speed is valid, but we want to find if there is a *slower* valid speed. We shrink our search space to the left by setting `right = mid`.
   * If `sum > h`, Koko is eating too slowly and runs out of time. We must increase the minimum speed by setting `left = mid + 1`.
5. **Final Result:** 
   The loop continues narrowing the range and terminates when `left == right`, which will land exactly on the absolute minimum valid eating speed.

## Complexity Analysis

* **Time Complexity:** $O(N \log M)$
  Where $N$ is the number of piles (length of the array) and $M$ is the maximum number of bananas in a single pile. Finding the maximum pile size takes $O(N)$ time. The binary search operates over a range of size $M$, so it takes $O(\log M)$ iterations. During each iteration, we loop through all $N$ piles to calculate the total hours, which takes $O(N)$ time. Combining these gives $O(N) + O(N \log M)$, which simplifies to $O(N \log M)$.
* **Space Complexity:** $O(1)$ auxiliary space
  We only allocate a few primitive integer variables (`max`, `left`, `right`, `mid`, `sum`) to keep track of the boundaries and the running total. No auxiliary arrays or data structures are used, resulting in constant space complexity.
