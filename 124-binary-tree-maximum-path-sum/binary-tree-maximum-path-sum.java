/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        // Calculate max path from left and right. Ignore negative paths by taking Math.max(0, ...)
        int left = Math.max(0, dfs(root.left));
        
        int right = Math.max(0, dfs(root.right)); 
        
        // Calculate the sum of the path that "arches" over the current node
        int pathSum = root.val + left + right;
        
        // Update the global maxSum if this new pathSum is larger
        maxSum = Math.max(maxSum, pathSum);
        
        // Return the maximum single branch going downward from this node
        return Math.max(left, right) + root.val;
    }
}