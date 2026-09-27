class Solution {
    public int longestSubstring(String s, int k) {
        return solve(s, 0, s.length()-1, k);
    }

    private int solve(String s, int start, int end, int k) {
        if (start > end) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            if (map.get(c) < k) {
                int left = solve(s, start, i-1, k);
                int right = solve(s, i+1, end, k);
                return Math.max(left, right);
            }
        }
        return end-start+1;
    }
}