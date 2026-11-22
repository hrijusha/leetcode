class Solution {
    public boolean isPalindrome(int x) {
        int reverse = 0;
        int num = x;
        while (x > 0) {
            reverse = reverse * 10 + x % 10;
            x = x /10;
        }
        if(num == reverse){
            return true;
        }
        return false;
    }
}