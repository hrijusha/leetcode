/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    TreeNode lca = new TreeNode();
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return lca;
    }

    private void dfs(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return;
        }
        if (node.val > p.val && node.val > q.val) {
            dfs(node.left, p, q);
        } else if (node.val < p.val && node.val < q.val) {
            dfs(node.right, p, q);
        } else {
            lca = node;
        }
    }
}