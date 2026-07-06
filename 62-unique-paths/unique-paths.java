class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m+1][n+1];
        return findPaths(dp, m, n);  
    }

    private int findPaths(int[][] dp, int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        if (dp[m][n] > 0) {
            return dp[m][n];
        } 
            dp[m][n] = findPaths(dp, m - 1, n) + findPaths(dp, m, n - 1);
            return dp[m][n];
    }
}