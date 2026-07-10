class Solution {

    public boolean wordBreak(String s, List<String> wordDict) {
        Map<String, Boolean> map = new HashMap<>();
        return wordBreakRecursive(s, wordDict, map);
    }

    private boolean wordBreakRecursive(String s, List<String> wordDict, Map<String, Boolean> map) {
        if (s.isEmpty()) {
            return true;
        }
        if (map.containsKey(s)) {
            return map.get(s);
        }
        for (int i = 1; i <= s.length(); i++) {
            String prefix = s.substring(0, i);
            if (wordDict.contains(prefix)) {
                String suffix = s.substring(i);
                if (wordBreakRecursive(suffix, wordDict, map)) {
                    map.put(s, true);
                    return true;
                }
            }
        }
        map.put(s, false);
        return false;
    }
}