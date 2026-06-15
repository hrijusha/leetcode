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
    int preorderIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int left, int right) {
        if (left > right) {
            return null;
        }
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        int inOrderIndex = left;
        for (int i = left; i < inorder.length; i++) {
            if (inorder[inOrderIndex] == rootValue) {
                break;
            } else {
                inOrderIndex++;
            }
        }

        root.left = buildTree(preorder, inorder, left, inOrderIndex - 1);
        root.right = buildTree(preorder, inorder, inOrderIndex + 1, right);

        return root;
    }
}