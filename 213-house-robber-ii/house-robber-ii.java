class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] dp0 = new int[n + 1];
        int[] dp1 = new int[n + 1];
        Arrays.fill(dp0, -1);
        Arrays.fill(dp1, -1);
        int max = Math.max(maxSum(nums, dp0, 0, n - 2), maxSum(nums, dp1, 1, n - 1));
        return max;

    }

    private int maxSum(int[] nums, int[] dp, int start, int index) {
        if (index == start) {
            return nums[index];
        }
        if (index < start) {
            return 0;
        }
        if (dp[index] != -1) {
            return dp[index];
        }
        int max = Math.max(maxSum(nums, dp, start, index - 1), nums[index] + maxSum(nums, dp, start, index - 2));
        dp[index] = max;
        return max;
    }
}