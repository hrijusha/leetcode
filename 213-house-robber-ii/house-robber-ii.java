class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return nums[0];
        if (n == 2)
            return Math.max(nums[0], nums[1]);

        int[] dp0 = new int[n];
        Arrays.fill(dp0, -1);

        int[] dp1 = new int[n];
        Arrays.fill(dp1, -1);

        int max0 = maxSum(nums, n - 2, 0, dp0);
        int max1 = maxSum(nums, n - 1, 1, dp1);

        return Math.max(max0, max1);
    }

    private int maxSum(int[] nums, int index, int start, int[] dp) {
        if (index == start) {
            return nums[start];
        }
        if (index < start) {
            return 0;
        }

        if (dp[index] != -1) {
            return dp[index];
        }

        int pick = nums[index] + maxSum(nums, index - 2, start, dp);
        int notPick = maxSum(nums, index - 1, start, dp);

        int ret = Math.max(pick, notPick);

        dp[index] = ret;
        return ret;
    }
}