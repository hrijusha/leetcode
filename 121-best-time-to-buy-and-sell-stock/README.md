<h2><a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock">Best Time to Buy and Sell Stock</a></h2> <img src='https://img.shields.io/badge/Difficulty-Easy-brightgreen' alt='Difficulty: Easy' /><hr><p>You are given an array <code>prices</code> where <code>prices[i]</code> is the price of a given stock on the <code>i<sup>th</sup></code> day.</p>

<p>You want to maximize your profit by choosing a <strong>single day</strong> to buy one stock and choosing a <strong>different day in the future</strong> to sell that stock.</p>

<p>Return <em>the maximum profit you can achieve from this transaction</em>. If you cannot achieve any profit, return <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> prices = [7,1,5,3,6,4]
<strong>Output:</strong> 5
<strong>Explanation:</strong> Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> prices = [7,6,4,3,1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> In this case, no transactions are done and the max profit = 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= prices[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Core Logic
1. **Initialization:** Track the `minPrice` seen so far (initialized to the first day's price) and the `maxProfit` we can make (initialized to `0`).
2. **Iteration:** Traverse through the array of prices day by day.
3. **State Evaluation:** For each `price`:
   * Update `minPrice` if the current `price` is strictly lower than the lowest price we've seen on previous days.
   * Calculate the `profit` we would make if we sold on this current day (current `price` - `minPrice`).
4. **Updating:** Update the `maxProfit` if this newly calculated profit is greater than our previous `maxProfit`.

## Complexity Analysis
* **Time Complexity:** **`O(N)`** We iterate through the given `prices` array of length *N* exactly once. All operations inside the loop are basic comparisons and arithmetic, which run in constant time.
* **Space Complexity:** **`O(1)`** We optimize space by only maintaining two integer variables (`minPrice` and `maxProfit`). We do not use any additional data structures, so the memory footprint is strictly constant.
