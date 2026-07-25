class Solution {
    public int characterReplacement(String s, int k) {
        HashMap<Character, Integer> countMap = new HashMap<>();
        int left = 0;
        int maxCount = 0;
        int maxSize = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            int count = countMap.getOrDefault(c, 0) + 1;
            maxCount = Math.max(maxCount, count);
            countMap.put(c, count);

            int windowSize = right - left + 1;
            if (windowSize - maxCount <= k) {
                maxSize = Math.max(maxSize, windowSize);
            } else {
                //Move the sliding window
                char leftChar = s.charAt(left);
                countMap.put(leftChar, countMap.get(leftChar) - 1);
                left++;
            }
        }
        return maxSize;
    }
}