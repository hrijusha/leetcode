class Solution {
    public String longestPalindrome(String s) {
        int globalMax = 0;
        int start = 0;
        
        if (s == null || s.length() < 1)
            return "";
            
        for (int i = 0; i < s.length(); i++) {
            int oddLen = findLargestPalindromeLength(s, i, i);
            int evenLen = findLargestPalindromeLength(s, i, i + 1);
            int maxLen = Math.max(oddLen, evenLen);
            if (maxLen > globalMax) {
                globalMax = maxLen;
                start = i - (maxLen - 1) / 2; 
            }
        }
        
        return s.substring(start, start + globalMax);
    }

    private int findLargestPalindromeLength(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;   
    }
}