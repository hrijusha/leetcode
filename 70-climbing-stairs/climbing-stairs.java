class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        return climb(dp, n);
    }

    private int climb(int[] dp, int n) {
        if (n < 0)
            return 0;
        if (n == 0)
            return 1;
        if (dp[n] > 0) {
            return dp[n];
        } else {
            dp[n] = climb(dp, n - 1) + climb(dp, n - 2);
            return dp[n];
        }
    }
}