//Brute force
class Solution {
    public String longestPalindrome(String s) {
        int maxLen = 0;
        String longestPalindrome = "";
        if (s == null || s.length() < 1)
            return "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String s1 = s.substring(i, j + 1);
                if (checkPalindrome(s1)) {
                    if (s1.length() > maxLen) {
                        longestPalindrome = s1;
                        maxLen = s1.length();
                    }
                }
            }
        }
        return longestPalindrome;
    }

    private boolean checkPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}