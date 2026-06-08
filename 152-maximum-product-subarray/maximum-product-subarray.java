class Solution {
    public int maxProduct(int[] nums) {
        int maxP = nums[0];
        int max = nums[0];
        int min = nums[0];
        
        // Start from index 1 to avoid processing nums[0] twice
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            
            int tempMax = max;
            
            max = Math.max(num, Math.max(min * num, max * num));
            min = Math.min(num, Math.min(min * num, tempMax * num));
            
            maxP = Math.max(max, maxP);
        }
        
        return maxP;
    }
}