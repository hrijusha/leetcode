class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int l1 = Integer.MIN_VALUE;
        int l2 = Integer.MIN_VALUE;
        int r1 = Integer.MAX_VALUE;
        int r2 = Integer.MAX_VALUE;
        int low = 0;
        int high = m;
        int half = (m + n + 1) / 2;
        while (low <= high) {
            int partition1 = (low + high) / 2;
            int partition2 = half - partition1;
            l1 = (partition1 == 0)
                    ? Integer.MIN_VALUE
                    : nums1[partition1 - 1];

            r1 = (partition1 == m)
                    ? Integer.MAX_VALUE
                    : nums1[partition1];

            l2 = (partition2 == 0)
                    ? Integer.MIN_VALUE
                    : nums2[partition2 - 1];

            r2 = (partition2 == n)
                    ? Integer.MAX_VALUE
                    : nums2[partition2];
            if (l1 <= r2 && l2 <= r1) {
                if ((m + n) % 2 == 1) {
                    return (double) Math.max(l1, l2);
                } else {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                }

            } else if (l1 > r2) {
                high = partition1 - 1;
            } else {
                low = partition1 + 1;
            }
        }
        return 0.0;

    }
}