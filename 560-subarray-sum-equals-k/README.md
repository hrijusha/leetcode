<h2><a href="https://leetcode.com/problems/subarray-sum-equals-k">Subarray Sum Equals K</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>Given an array of integers <code>nums</code> and an integer <code>k</code>, return <em>the total number of subarrays whose sum equals to</em> <code>k</code>.</p>

<p>A subarray is a contiguous <strong>non-empty</strong> sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,1,1], k = 2
<strong>Output:</strong> 2
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [1,2,3], k = 3
<strong>Output:</strong> 2
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>-10<sup>7</sup> &lt;= k &lt;= 10<sup>7</sup></code></li>
</ul>

## Approach: Prefix Sum Array

This solution uses a Prefix Sum array combined with a nested loop to evaluate the sum of all possible subarrays. While it avoids the $O(n^3)$ complexity of a pure brute-force approach, it checks every subarray explicitly.

1. **Prefix Sum Construction:** 
   We create a `prefix` array of size `n + 1`, where `prefix[i]` stores the cumulative sum of all elements in the input array `nums` from index $0$ up to $i - 1$. We initialize `prefix[0] = 0` to easily handle subarrays that start at the very first index.
2. **O(1) Range Sum Queries:** 
   The core mathematical trick of a prefix sum array is that the sum of any continuous subarray from index `i` to `j` can be calculated in constant time using the formula:
   $$\text{Sum}(i, j) = \text{prefix}[j + 1] - \text{prefix}[i]$$
3. **Evaluating All Subarrays:** 
   We use two nested loops to define the boundaries of every possible subarray. The outer loop `i` represents the starting index, and the inner loop `j` represents the ending index. 
4. **Counting Matches:** 
   For each subarray bounded by `i` and `j`, we calculate its sum using our prefix array. If the sum exactly equals `k`, we increment our `count`. After evaluating all combinations, we return the total count.

## Complexity Analysis

* **Time Complexity:** $O(n^2)$
  Where $n$ is the length of the `nums` array. Building the prefix sum array takes $O(n)$ time. However, iterating through all possible subarrays using the nested `i` and `j` loops requires $1 + 2 + 3 + \dots + n \approx \frac{n^2}{2}$ iterations, resulting in an $O(n^2)$ time complexity. *(Note: An optimal $O(n)$ solution exists for this problem using a HashMap to track prefix sum frequencies, but this approach correctly demonstrates the foundational prefix sum technique).*
* **Space Complexity:** $O(n)$
  We allocate an auxiliary `prefix` array of size $n + 1$ to store the cumulative sums, requiring linear extra space.

  ## Approach: Prefix Sum + HashMap (Optimal)

This solution optimizes the prefix sum approach by using a HashMap to keep track of the frequencies of prefix sums we have seen so far. It calculates the answer in a single pass, eliminating the need for nested loops.

1. **The Core Logic (Math Trick):** 
   If the cumulative sum up to index `i` is `currentSum`, and we want to find a continuous subarray ending at `i` that sums to `k`, we are essentially looking for a previous point in the array (say, index `j`) where the cumulative sum was exactly `currentSum - k`. 
   Mathematically: `Sum(0 to i) - Sum(0 to j) = k`. 
2. **Initialization:** 
   We initialize a HashMap `prefixSumMap` to store the prefix sums and their frequencies. Crucially, we insert `(0, 1)` into the map right at the start. This handles the base case where a valid subarray starts from the very first element (index $0$) and perfectly sums to `k`.
3. **Single Pass Iteration:** 
   We iterate through the `nums` array exactly once, keeping a running tally of the total sum in `currentSum`.
4. **Counting Matches:** 
   At each step, we check our map to see if the required complement (`currentSum - k`) has occurred previously. If it has, we add the frequency of that previous sum to our `count`. 
5. **Updating the Map:** 
   Before moving to the next element, we record our newly calculated `currentSum` into the map (or increment its frequency if we've seen this exact sum before).

## Complexity Analysis

* **Time Complexity:** $O(n)$
  Where $n$ is the length of the `nums` array. We iterate through the array exactly once. For each element, checking for the existence of a key and updating the HashMap takes $O(1)$ average time. This drastically reduces the time complexity from $O(n^2)$ down to linear time.
* **Space Complexity:** $O(n)$
  Where $n$ is the length of the `nums` array. In the worst-case scenario (e.g., all elements are positive numbers), every single prefix sum will be uniquely different, meaning we will insert $n$ distinct key-value pairs into the HashMap, requiring $O(n)$ auxiliary space.

  ## Solution: Prefix Sum + HashMap (Optimal)

```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        // Base case: to handle subarrays that start from the 0th index
        prefixSumMap.put(0, 1);
        int currentSum = 0;
        int count = 0;
        
        for (int i = 0; i < nums.length; i++) {
            currentSum = currentSum + nums[i];
            
            // If (currentSum - k) exists, it means we found a subarray summing to k
            if (prefixSumMap.containsKey(currentSum - k)) {
                count += prefixSumMap.get(currentSum - k);
            }
            
            // Record the current prefix sum into the map
            prefixSumMap.put(currentSum, prefixSumMap.getOrDefault(currentSum, 0) + 1);
        }
        
        return count;
    }
}
