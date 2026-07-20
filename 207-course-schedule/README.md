<h2><a href="https://leetcode.com/problems/course-schedule">Course Schedule</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>There are a total of <code>numCourses</code> courses you have to take, labeled from <code>0</code> to <code>numCourses - 1</code>. You are given an array <code>prerequisites</code> where <code>prerequisites[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that you <strong>must</strong> take course <code>b<sub>i</sub></code> first if you want to take course <code>a<sub>i</sub></code>.</p>

<ul>
	<li>For example, the pair <code>[0, 1]</code>, indicates that to take course <code>0</code> you have to first take course <code>1</code>.</li>
</ul>

<p>Return <code>true</code> if you can finish all courses. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> numCourses = 2, prerequisites = [[1,0]]
<strong>Output:</strong> true
<strong>Explanation:</strong> There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> numCourses = 2, prerequisites = [[1,0],[0,1]]
<strong>Output:</strong> false
<strong>Explanation:</strong> There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= numCourses &lt;= 2000</code></li>
	<li><code>0 &lt;= prerequisites.length &lt;= 5000</code></li>
	<li><code>prerequisites[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; numCourses</code></li>
	<li>All the pairs prerequisites[i] are <strong>unique</strong>.</li>
</ul>

## Approach: Topological Sort (Kahn's Algorithm)

This solution models the course prerequisites as a Directed Graph and uses Kahn's Algorithm (BFS-based Topological Sorting) to detect if a cycle exists. If a cycle exists (e.g., Course A requires B, and B requires A), it is impossible to finish all courses.

1. **Graph Representation:** 
   We represent the courses and prerequisites using an adjacency list (`adj`). A directed edge from `u` to `v` means course `u` is a prerequisite for course `v`. We also maintain an `inDegree` array to track how many prerequisites each course currently has.
2. **Graph Construction:** 
   We iterate through the `prerequisites` array. For each pair `[to, from]`, we add an edge from `from` to `to` in our adjacency list and increment the `inDegree` of the `to` course.
3. **Initialization (Finding Starting Points):** 
   We scan the `inDegree` array and add all courses with an in-degree of $0$ to a Queue (`q`). These are courses with no prerequisites, meaning they can be taken immediately.
4. **BFS Traversal (Processing Courses):** 
   While the queue is not empty, we pop a course and add it to our processed `list` (simulating taking the course). Then, we iterate through all of its dependent `neighbors` in the adjacency list. Since we just "completed" their prerequisite, we decrement the in-degree of each neighbor. If a neighbor's in-degree drops to $0$, it means all of its prerequisites are fulfilled, so we push it into the queue.
5. **Cycle Detection (Final Result):** 
   After the BFS finishes, we check how many courses were successfully processed (the size of `list`). If `list.size() == numCourses`, it means we were able to topologically sort the entire graph (no cycles), and we return `true`. If the count is less than `numCourses`, a cycle exists, blocking completion, and we return `false`.

## Complexity Analysis

* **Time Complexity:** $O(V + E)$
  Where $V$ is the number of vertices (`numCourses`) and $E$ is the number of edges (length of the `prerequisites` array). Building the adjacency list and the `inDegree` array takes $O(V + E)$ time. During the BFS, every vertex is pushed and popped from the queue exactly once, and every edge is traversed exactly once, which also takes $O(V + E)$ time.
* **Space Complexity:** $O(V + E)$
  * The adjacency list `adj` stores all vertices and edges, taking $O(V + E)$ space.
  * The `inDegree` array, the Queue `q`, and the processed `list` each require $O(V)$ space.
  * The dominant term is the adjacency list, resulting in an overall space complexity of $O(V + E)$.
