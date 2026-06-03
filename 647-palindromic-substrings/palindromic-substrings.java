//BruteForce
class Solution {
    int count = 0;
    public int countSubstrings(String s) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (checkPalindrome(s, i, j))
                    count++;
            }
        }
        return count;
    }

    private boolean checkPalindrome(String s, int i, int j) {
        String sub = s.substring(i, j + 1);
        int left = 0;
        int right = sub.length() - 1;
        while (left < right) {
            if (sub.charAt(left) != sub.charAt(right)) {
                return false;
            }
            else{
                left++;
                right--;
            }
        }
        return true;
    }
}