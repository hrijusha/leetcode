<h2><a href="https://leetcode.com/problems/combination-sum">Combination Sum</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>Given an array of <strong>distinct</strong> integers <code>candidates</code> and a target integer <code>target</code>, return <em>a list of all <strong>unique combinations</strong> of </em><code>candidates</code><em> where the chosen numbers sum to </em><code>target</code><em>.</em> You may return the combinations in <strong>any order</strong>.</p>

<p>The <strong>same</strong> number may be chosen from <code>candidates</code> an <strong>unlimited number of times</strong>. Two combinations are unique if the <span data-keyword="frequency-array">frequency</span> of at least one of the chosen numbers is different.</p>

<p>The test cases are generated such that the number of unique combinations that sum up to <code>target</code> is less than <code>150</code> combinations for the given input.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> candidates = [2,3,6,7], target = 7
<strong>Output:</strong> [[2,2,3],[7]]
<strong>Explanation:</strong>
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> candidates = [2,3,5], target = 8
<strong>Output:</strong> [[2,2,2,2],[2,3,3],[3,5]]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> candidates = [2], target = 1
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= candidates.length &lt;= 30</code></li>
	<li><code>2 &lt;= candidates[i] &lt;= 40</code></li>
	<li>All elements of <code>candidates</code> are <strong>distinct</strong>.</li>
	<li><code>1 &lt;= target &lt;= 40</code></li>
</ul>

## Approach

This solution uses a **Backtracking (Depth-First Search)** approach to explore all possible combinations of numbers that sum up to the target. At every step in our recursive tree, we have two primary choices for the current candidate number: **Pick it** or **Skip it**.

1. **Base Cases:** * If `target == 0`, we have found a valid combination. We create a copy of our `current` list and add it to our `result`.
   * If `index == candidates.length` (we ran out of numbers) or `target < 0`, the current path is invalid, and we simply return to backtrack.
2. **Choice 1: Pick the Current Number:** * As long as the current candidate is less than or equal to the remaining target, we add it to our `current` list. 
   * We then recursively call our function with the *reduced* target (`target - candidates[index]`). 
   * *Crucial Step:* Notice that we pass the **same** `index` into the recursive call, not `index + 1`. This allows us to use the same number an unlimited number of times.
   * After the recursive call finishes, we **backtrack** by removing the last added number from our `current` list so we can explore other paths.
3. **Choice 2: Skip the Current Number:** * We recursively call our function but increment the index (`index + 1`) to move on to the next candidate, keeping the target exactly the same.

## Complexity Analysis

* **Time Complexity:** $O(N^{\frac{T}{M}})$
  * Let $N$ be the number of candidates, $T$ be the target value, and $M$ be the minimum value among the candidates.
  * In the worst-case scenario, the execution tree can go as deep as $\frac{T}{M}$ (if we repeatedly pick the smallest element). At each node, the tree can branch out up to $N$ times. This makes the loose upper bound exponential. However, the exact time depends heavily on the input data and how quickly the target reaches zero.
* **Space Complexity:** $O(\frac{T}{M})$
  * The space complexity is dictated by the maximum depth of the recursion stack. The longest possible path is when we keep adding the smallest element $M$ to reach target $T$. 
  * Therefore, the recursion stack and the `current` list will take at most $O(\frac{T}{M})$ auxiliary space.
