class Solution {
    public int rob(int[] nums) {
        int n = nums.length - 1;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = nums[0];
        return maxSum(nums, n, dp);
    }

    private int maxSum(int[] nums, int index, int[] dp) {
        if (index == 0) {
            return nums[0];
        }
        if (index < 0) {
            return 0;
        }
        if (dp[index] != -1) {
            return dp[index];
        }

        int pick = nums[index] + maxSum(nums, index - 2, dp);
        int notPick = maxSum(nums, index - 1, dp);

        int ret = Math.max(pick, notPick);

        dp[index] = ret;
        return ret;
    }
}