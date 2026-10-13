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
