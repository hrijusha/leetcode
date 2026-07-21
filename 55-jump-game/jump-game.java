class Solution {
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        int target = nums.length - 1;

        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) {
                return false;
            }

            maxReach = Math.max(maxReach, i + nums[i]);

            if (maxReach >= target) {
                return true;
            }
        }

        return true;
    }
}