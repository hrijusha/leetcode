class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int max = 0;
        for (int n : piles) {
            max = Math.max(max, n);
        }
        int left = 1;
        int right = max;
        while (left < right) {
            int mid = left + (right - left) / 2;
            long sum = 0;
            for (int n : piles) {
                sum += (n + mid - 1) / mid;
            }
            if (sum <= h) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}