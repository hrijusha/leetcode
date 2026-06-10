class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String sortedString = new String(array);
            if (map.get(sortedString) != null) {
                List<String> value = map.get(sortedString);
                value.add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(sortedString, list);
            }
        }
        return map.values()
                .stream()
                .collect(Collectors.toList());
    }
}