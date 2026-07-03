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
    int result = 0;
    int count = 0;

    public int kthSmallest(TreeNode root, int k) {
        count = k;
        findElement(root);
        return result;
    }

    private void findElement(TreeNode node) {
        if (node == null) {
            return;
        } else {
            findElement(node.left);

            count--;
            if (count == 0) {
                result = node.val;
                return;
            }
            findElement(node.right);
        }

    }
}