class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        int[] result1 = new int[nums.length];
        int[] result2 = new int[nums.length];

        int leftProduct = 1;
        for (int i = 0; i < nums.length; i++) {
            result1[i] = leftProduct;
            leftProduct = leftProduct * nums[i];
        }
        int rightProduct = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result2[i] = rightProduct;
            rightProduct = rightProduct * nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            result[i] = result1[i] * result2[i];
        }
        return result;

    }
}