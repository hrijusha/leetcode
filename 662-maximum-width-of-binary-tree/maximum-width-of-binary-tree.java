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
    public int widthOfBinaryTree(TreeNode root) {
        return bfs(root);
    }

    private int bfs(TreeNode root) {
        int maxWidth = 0;
        Queue<TreeNode> nq = new LinkedList<>();
        Queue<Integer> iq = new LinkedList<>();
        nq.offer(root);
        iq.offer(0);
        while (!nq.isEmpty()) {
            int size = nq.size();
            int first = iq.peek();
            int last = first;
            for (int i = 0; i < size; i++) {
                TreeNode curr = nq.poll();
                int index = iq.poll();
                last = index;
                int currIndex = index - first;
                if (curr.left != null) {
                    nq.offer(curr.left);
                    iq.offer(2 * currIndex);
                }
                if (curr.right != null) {
                    nq.offer(curr.right);
                    iq.offer(2 * currIndex + 1);
                }
            }
            maxWidth = Math.max(maxWidth, last - first + 1);
        }
        return maxWidth;
    }
}