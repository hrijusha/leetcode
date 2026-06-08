<h2><a href="https://leetcode.com/problems/3sum">3Sum</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>Given an integer array nums, return all the triplets <code>[nums[i], nums[j], nums[k]]</code> such that <code>i != j</code>, <code>i != k</code>, and <code>j != k</code>, and <code>nums[i] + nums[j] + nums[k] == 0</code>.</p>

<p>Notice that the solution set must not contain duplicate triplets.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,0,1,2,-1,-4]
<strong>Output:</strong> [[-1,-1,2],[-1,0,1]]
<strong>Explanation:</strong> 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,1]
<strong>Output:</strong> []
<strong>Explanation:</strong> The only possible triplet does not sum up to 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,0]
<strong>Output:</strong> [[0,0,0]]
<strong>Explanation:</strong> The only possible triplet sums up to 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 3000</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Approach 1: Optimized Two-Pointer (Recommended)
This approach significantly reduces the time complexity by sorting the array first and using a two-pointer technique to find the matching pairs.

**Core Logic:**
1. **Sort the Array:** Sorting allows us to easily skip duplicates and strategically move our pointers based on whether our sum is too large or too small.
2. **Anchor and Search:** Iterate through the array. For each number `nums[i]` (the anchor):
   * If the anchor is greater than `0`, we can stop searching. Because the array is sorted, no numbers after it can be negative, making a sum of `0` impossible.
   * Set a `left` pointer just after the anchor and a `right` pointer at the end of the array.
3. **Two-Pointer Traversal:** * Calculate the `sum`.
   * If `sum == 0`, we found a valid triplet! Add it to our `HashSet` to naturally prevent duplicate lists. Move both pointers inward.
   * If `sum < 0`, the total is too small. Move the `left` pointer to the right to increase the sum.
   * If `sum > 0`, the total is too large. Move the `right` pointer to the left to decrease the sum.

### Complexity Analysis (Optimized)
* **Time Complexity:** **`O(N^2)`** Sorting the array takes `O(N log N)`. The outer loop runs `N` times, and the inner `while` loop runs at most `N` times. Thus, the overall time complexity is dominated by the nested loops: `O(N^2)`.
* **Space Complexity:** **`O(N)`** The `HashSet` is used to store unique triplets, which scales with the number of valid outputs. Sorting also generally takes some auxiliary space depending on the language's implementation.

---

## Approach 2: Brute Force 
The most basic way to solve this is to generate every possible combination of three numbers and check if they add up to zero.

**Core Logic:**
1. Use three nested loops to iterate through every possible triplet combination `(i, j, k)`.
2. Check if the three numbers sum to `0`.
3. If they do, sort the three numbers so the triplet is in a standard format, and add it to a `HashSet` to ensure we don't return duplicate triplets.

### Complexity Analysis (Brute Force)
* **Time Complexity:** **`O(N^3)`** Three nested loops mean we are checking every combination, resulting in cubic time complexity. This will result in a Time Limit Exceeded (TLE) error for large inputs.
* **Space Complexity:** **`O(N)`** For the `HashSet` to store the unique results.

---

```java
//Brute force
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    check(nums, i, j, k, set);
                }
            }
        }
        return new ArrayList<>(set);
    }

    private void check(int[] nums, int i, int j, int k, Set<List<Integer>> set) {
        if (i != j || j != k || k!=i) {
            if (nums[i] + nums[j] + nums[k] == 0) {
                List<Integer> list = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k]));
                Collections.sort(list);
                set.add(list);
            }
        }
    }
}
```
