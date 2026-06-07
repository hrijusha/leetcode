class Solution {
    public boolean isPalindrome(String s) {

        String result = removeNonAlphaNumericChar(s);
        if (result.isEmpty()) {
            return true;
        }
        int left = 0;
        int right = result.length() - 1;
        while (left < right) {
            if (result.charAt(left) != result.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;

    }

    private String removeNonAlphaNumericChar(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toLowerCase().toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}