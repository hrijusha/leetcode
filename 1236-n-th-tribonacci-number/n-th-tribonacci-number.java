class Solution {
    public int tribonacci(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return recursion(n, dp);
    }

    private int recursion(int n, int[] dp) {
        if (n <= 2) {
            dp[n] = (n == 0) ? 0 : 1;
            return dp[n];
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        dp[n] = recursion(n - 1, dp) + recursion(n - 2, dp) + recursion(n - 3, dp);
        return dp[n];
    }
}