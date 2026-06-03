<h2><a href="https://leetcode.com/problems/container-with-most-water">Container With Most Water</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>You are given an integer array <code>height</code> of length <code>n</code>. There are <code>n</code> vertical lines drawn such that the two endpoints of the <code>i<sup>th</sup></code> line are <code>(i, 0)</code> and <code>(i, height[i])</code>.</p>

<p>Find two lines that together with the x-axis form a container, such that the container contains the most water.</p>

<p>Return <em>the maximum amount of water a container can store</em>.</p>

<p><strong>Notice</strong> that you may not slant the container.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://s3-lc-upload.s3.amazonaws.com/uploads/2018/07/17/question_11.jpg" style="width: 600px; height: 287px;" />
<pre>
<strong>Input:</strong> height = [1,8,6,2,5,4,8,3,7]
<strong>Output:</strong> 49
<strong>Explanation:</strong> The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> height = [1,1]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == height.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= height[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Container With Most Water

### Approach: Brute Force
This solution uses a straightforward brute-force approach to find the maximum amount of water a container can store. 

The algorithm evaluates every possible pair of lines (representing the container's edges) using two nested loops:
1. The outer loop `i` selects the starting line.
2. The inner loop `j` selects the ending line (which is always ahead of `i`).
3. For each pair, it calculates the area by multiplying the distance between the lines `(j - i)` by the height of the shorter line `Math.min(height[i], height[j])`.
4. It keeps track of the maximum area found across all combinations and returns it.

### Complexity
* **Time Complexity:** `O(n^2)` 
  Because we are using two nested loops to check every possible pair in an array of size `n`, the time it takes grows quadratically. 
* **Space Complexity:** `O(1)`
  The algorithm only requires a few integer variables (`maxArea`, `i`, `j`, `area`) to maintain state, meaning it uses a constant amount of extra memory regardless of the input size.

### Code (Java)
```java
class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int area = Math.min(height[i], height[j]) * (j - i);
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }
}
```
## Container With Most Water

### Approach: Two-Pointer Technique

The problem asks us to find two lines that, together with the x-axis, form a container that holds the most water. To solve this efficiently, we can use a **greedy two-pointer approach**:

1. **Initialization:** We place one pointer at the beginning of the array (`left = 0`) and one at the end (`right = height.length - 1`). We also initialize a variable `maxArea` to keep track of the maximum water volume found so far.
2. **Calculate Area:** The width of the container is the distance between the two pointers (`right - left`). The height of the container is limited by the shorter of the two lines: `Math.min(height[left], height[right])`. The area is simply `width * height`.
3. **Update Maximum:** If the current area is greater than `maxArea`, we update `maxArea`.
4. **Move Pointers (The Greedy Choice):** To maximize the area in subsequent steps, we must keep the taller line and move the pointer pointing to the shorter line inward. Moving the taller line would guarantee a decrease or equal area because the width decreases and the height is always bottlenecked by the shorter line. 
    * If `height[left]` is smaller, we increment `left`.
    * Otherwise, we decrement `right`.
5. **Termination:** The loop continues until the two pointers meet (`left < right`).

### Complexity Analysis

* **Time Complexity:** `O(n)`
  We traverse the `height` array exactly once. In each step of the `while` loop, we move at least one pointer, meaning the loop will run at most $n$ times (where $n$ is the length of the array).
* **Space Complexity:** `O(1)`
  The algorithm only uses a few integer variables (`left`, `right`, `maxArea`, `min`, `area`) for its calculations, which requires constant extra space regardless of the input array size.

### Java Solution

```java
class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        
        while (left < right) {
            int min = Math.min(height[left], height[right]);
            int area = (right - left) * min;
            
            if (area > maxArea) {
                maxArea = area;
            }
            
            // Move the pointer pointing to the shorter line
            if (min == height[left]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxArea;
    }
}
```
