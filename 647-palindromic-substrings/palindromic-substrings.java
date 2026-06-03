class Solution {
    public int countSubstrings(String s) {
        int globalCount = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            globalCount = globalCount + palindromeCount(s, i, i) + palindromeCount(s, i, i + 1);
        }

        return globalCount;
    }

    private int palindromeCount(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

}