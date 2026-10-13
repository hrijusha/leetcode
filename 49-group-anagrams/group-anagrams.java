
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            int[] frequency = new int[26];

            for (char c : str.toCharArray()) {
                frequency[c - 'a']++;
            }

            StringBuilder key = new StringBuilder();
            for (int count : frequency) {
                key.append(count).append("#");
            }

            String s = key.toString();

            if (map.containsKey(s)) {
                map.get(s).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(s, list);
            }
        }

        return new ArrayList<>(map.values());
    }
}