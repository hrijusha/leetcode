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
    private TreeNode prev = null;

    public boolean isValidBST(TreeNode root) {
        return inOrderDFS(root);
    }

    private boolean inOrderDFS(TreeNode node) {
        if (node == null) {
            return true;
        }

        if (!inOrderDFS(node.left)) {
            return false;
        }

        if (prev != null && node.val <= prev.val) {
            return false;
        }

        prev = node;

        return inOrderDFS(node.right);

    }
}
