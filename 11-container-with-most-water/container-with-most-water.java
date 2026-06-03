class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left =0;
        int right = height.length-1;
        while(left<right){
            int min = Math.min(height[left], height[right]);
            int area = (right-left) * min;
            if(area>maxArea){
                maxArea=area;
            }
            if(min == height[left]){
                left++;
            } else {
                right --;
            }
        }
        return maxArea;
    }
}