<h2><a href="https://leetcode.com/problems/valid-parentheses">Valid Parentheses</a></h2> <img src='https://img.shields.io/badge/Difficulty-Easy-brightgreen' alt='Difficulty: Easy' /><hr><p>Given a string <code>s</code> containing just the characters <code>&#39;(&#39;</code>, <code>&#39;)&#39;</code>, <code>&#39;{&#39;</code>, <code>&#39;}&#39;</code>, <code>&#39;[&#39;</code> and <code>&#39;]&#39;</code>, determine if the input string is valid.</p>

<p>An input string is valid if:</p>

<ol>
	<li>Open brackets must be closed by the same type of brackets.</li>
	<li>Open brackets must be closed in the correct order.</li>
	<li>Every close bracket has a corresponding open bracket of the same type.</li>
</ol>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;()&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;()[]{}&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;(]&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;([])&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>
</div>

<p><strong class="example">Example 5:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;([)]&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists of parentheses only <code>&#39;()[]{}&#39;</code>.</li>
</ul>

## Approach: Stack (LIFO)
The nature of nested parentheses perfectly matches the "Last-In, First-Out" (LIFO) property of a Stack data structure. The most recently opened bracket is the very first one that needs to be closed.

**Core Logic:**
1. **Initialization:** We initialize a `Deque` (Double-Ended Queue) to act as our stack. `ArrayDeque` is the preferred and most efficient implementation for stacks in modern Java.
2. **Iteration:** We iterate through every character in the string `s`.
3. **Processing Open Brackets:** If the character is an opening bracket (`(`, `{`, or `[`), we "push" it onto the top of the stack.
4. **Processing Close Brackets:** If the character is a closing bracket, we must verify two things:
   * **Is the stack empty?** If it is, we have a closing bracket without a matching opening bracket. Return `false`.
   * **Does it match?** We "peek" at the top of the stack. If the opening bracket at the top matches our current closing bracket, we "pop" (remove) it from the stack and continue. If it's a mismatch, return `false`.
5. **Final Check:** After processing all characters, a valid string will leave us with an empty stack. If there are any remaining brackets on the stack (e.g., `s = "(("`), we return `false`.

## Complexity Analysis
* **Time Complexity:** **`O(N)`** Where `N` is the length of the string. We iterate through the string exactly once. Pushing, popping, and peeking on an `ArrayDeque` all take constant time `O(1)`.
* **Space Complexity:** **`O(N)`** In the worst-case scenario (a string composed entirely of opening brackets like `"(((((("`), we will push every single character onto the stack, requiring memory proportional to the size of the string.
