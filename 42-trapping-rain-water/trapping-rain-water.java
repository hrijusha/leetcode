// class Solution {
//     public int trap(int[] height) {
//         int n = height.length;
//         int prev = height[0];
//         int count = 0;
//         for(int i=0; i<n; i++){
//             int leftMax = 0;
//             int rightMax = 0;
//             for(int j=0; j<=i; j++){
//                 leftMax = Math.max(leftMax, height[j]);
//             }
//             for(int j=i; j<n; j++){
//                 rightMax = Math.max(rightMax, height[j]);
//             }
//             int water = Math.min(leftMax, rightMax) - height[i];
//             count = count + water;
//         }
//         return count;
//     }
// }
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int left = 0;
        int right = n - 1;
        int leftMax = height[0];
        int rightMax = height[n - 1];
        int count = 0;
        while (left < right) {
            if (height[left] <= height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    count += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] > rightMax) {
                    rightMax = height[right];
                } else {
                    count += rightMax - height[right];
                }
                right--;
            }
        }
        return count;
    }
}