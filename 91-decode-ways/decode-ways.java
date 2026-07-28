class Solution {
    public int numDecodings(String s) {
        // Initialize the cache
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        
        // Pass the dp array into the recursive function
        return decode(s, 0, dp);
    }

    private int decode(String s, int i, int[] dp) {
        // BASE CASE 1: We successfully reached the end of the string.
        // This means the current combination is a valid path.
        if (i == s.length()) {
            return 1;
        }
        
        // BASE CASE 2: A path cannot start with '0'
        if (s.charAt(i) == '0') {
            return 0;
        }
        
        if (dp[i] != -1) {
            return dp[i];
        }

        int res = 0;

        // Branch 1: Single-digit decode
        res += decode(s, i + 1, dp);

        // Branch 2: Two-digit decode
        // Check bounds AND ensure the number formed is between 10 and 26
        if (i + 1 < s.length() && 
           (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) <= '6'))) {
            
            res += decode(s, i + 2, dp);
        }

        dp[i] = res;
        return res;
    }
}