class Solution {
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }
        Map<Character, Integer> tmap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tmap.put(c, tmap.getOrDefault(c, 0) + 1);
        }
        Map<Character, Integer> windowMap = new HashMap<>();
        int left = 0;
        int right = 0;
        int matchCount = 0;

        int minLen = Integer.MAX_VALUE;
        int minLeft = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);
            if (tmap.containsKey(c) && tmap.get(c).equals(windowMap.get(c))) {
                matchCount++;
            }
            while (matchCount == tmap.size()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }
                char lc = s.charAt(left);
                windowMap.put(lc, windowMap.get(lc) - 1);
                if (tmap.containsKey(lc) && windowMap.get(lc) < tmap.get(lc)) {
                    matchCount--;
                }
                left++;
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }
}