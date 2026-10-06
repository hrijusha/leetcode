// Take k cards from both ends = leave n-k contiguous cards in middle.
// Find minimum middle-window sum; answer = total sum - minimum window sum.

class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int start = 0;
        int max = 0;
        int n = cardPoints.length;
        int windowSize = n - k;
        int total = 0;
        for (int num : cardPoints) {
            total = total + num;
        }
        int sum = 0;

        for (int end = 0; end < cardPoints.length; end++) {
            if (end - start + 1 > windowSize) {
                sum = sum - cardPoints[start];
                start++;
            }
            sum = sum + cardPoints[end];

            if (end - start + 1 == windowSize) {
                max = Math.max(max, total - sum);
            }
        }
        return max;
    }
}