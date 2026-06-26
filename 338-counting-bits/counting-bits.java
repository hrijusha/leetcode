class Solution {
    public int[] countBits(int n) {
        int[] result = new int[n+1];
        for (int i = 0; i <= n; i++) {
            String s = Integer.toBinaryString(i);
            int count = s.length() - s.replace("1", "").length();
            result[i] = count;
        }
        return result;
    }
}