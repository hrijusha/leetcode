class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int[] prefix = new int[nums.length + 1];
        prefix[0] = 0;
        for (int i = 1; i <= nums.length; i++)
            prefix[i] = prefix[i - 1] + nums[i - 1];

        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (prefix[j+1] - prefix[i] == k)
                    count++;
            }
        }
        return count;
    }
}