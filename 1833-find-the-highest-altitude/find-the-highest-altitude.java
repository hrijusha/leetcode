class Solution {
    public int largestAltitude(int[] gain) {
        int[] alt = new int[gain.length + 1];
        int prev = 0;
        alt[0] = 0;
        int max = 0;
        for (int i = 1; i < gain.length + 1; i++) {
            int curr = gain[i - 1] + prev;
            alt[i] = curr;
            prev = curr;
            max = Math.max(curr, max);
        }
        return max;
    }
}