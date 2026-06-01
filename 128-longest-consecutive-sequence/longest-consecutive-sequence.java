import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return 1;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int maxLength = 0;
        for (int num : set) {
            int length = 0;
            int num1 = 0;
            if (!set.contains(num - 1)) {
                num1 = num;
                length = 1;
                while (set.contains(num1 + 1)) {
                    length++;
                    num1++;
                }
            }
            maxLength = Math.max(length, maxLength);
        }
        return maxLength;
    }
}