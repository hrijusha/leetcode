class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char s1 = s.charAt(i);
            char t1 = t.charAt(i);
            map1.put(s1, map1.getOrDefault(s1, 0) + 1);
            map2.put(t1, map2.getOrDefault(t1, 0) + 1);
        }
        return map1.equals(map2);
    }
}