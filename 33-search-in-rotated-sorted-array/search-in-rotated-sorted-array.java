class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] <= nums[mid]) {
                //left side is sorted and target falls in sorted side
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                }
                //right side is unsorted and target falls in unsorted side
                else {
                    left = mid + 1;
                }

            } else {
                //right side is sorted and target falls in sorted side
                if (target <= nums[right] && target > nums[mid]) {
                    left = mid + 1;
                }
                //left side is unsorted and target falls in unsorted side
                else {
                    right = mid - 1;
                }
            }
        }
        return -1;

    }
}