class Solution {
    public int firstMissingPositive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 1; i <= nums.length + 1; i++) {
            if (!map.containsKey(i)) {
                return i;
            }
        }
        return 0;
    }
}