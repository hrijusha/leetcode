//Brute force

// class Solution {
//     public int subarraySum(int[] nums, int k) {
//         int n = nums.length;
//         int count = 0;
//         for (int i = 0; i < n; i++) {
//             int sum = 0;
//             for (int j = i; j < n; j++) {
//                 sum = sum + nums[j];
//                 if (sum == k) {
//                     count++;
//                 }
//             }
//         }
//         return count;
//     }
// }


/*
Approach: Prefix Sum + HashMap

- Maintain currentSum (prefix sum) while traversing the array.
- If currentSum - k exists in the map, its frequency tells us
  how many subarrays ending at the current index have sum k.
- Add that frequency to count.
- Store/update the frequency of currentSum in the map.
- Initialize map with (0, 1) to handle subarrays starting at index 0.
- Check the map before updating it to ensure subarrays are non-empty.

Time: O(n) expected
Space: O(n)
*/

class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int currentSum = 0;
        int count = 0;

        for (int n : nums) {
            currentSum += n;

            int req = currentSum - k;

            if (map.containsKey(req)) {
                count += map.get(req);
            }

            map.put(currentSum,
                    map.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }
}