class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int i = 0;
        int j = 0;
        double prev = -1.0;
        double current = -1.0;
        for (int count = 0; count <= (m + n) / 2; count++) {
            prev = current;
            if (i < m && (j >= n || nums1[i] <= nums2[j])) {
                current = nums1[i];
                i++;
            } else {
                current = nums2[j];
                j++;
            }
        }
        if ((m + n) % 2 == 0) {
            return (prev + current) / 2;
        } else {
            return current;
        }

    }
}