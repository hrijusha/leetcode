class Solution {
    public String gcdOfStrings(String str1, String str2) {
        // Compare string contents using equals()
        if ((str1 + str2).equals(str2 + str1)) {
            int gcdLen = gcd(str1.length(), str2.length());
            return str1.substring(0, gcdLen);
        }
        return "";
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}