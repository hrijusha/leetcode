// Brute force
// class Solution {
//     public List<List<String>> groupAnagrams(String[] strs) {
//         List<List<String>> result = new ArrayList<>();
//         boolean[] visited = new boolean[strs.length];
//         for (int i = 0; i < strs.length; i++) {
//             List<String> list = new ArrayList<>();
//             if (visited[i]) {
//                 continue;
//             }
//             char[] si = strs[i].toCharArray();
//             Arrays.sort(si);
//             list.add(strs[i]);
//             visited[i] = true;

//             for (int j = i + 1; j < strs.length; j++) {
//                 if (visited[j]) {
//                     continue;
//                 }
//                 char[] sj = strs[j].toCharArray();
//                 Arrays.sort(sj);
//                 if (Arrays.equals(si, sj)) {
//                     list.add(strs[j]);
//                     visited[j] = true;
//                 }
//             }
//             result.add(list);
//         }
//         return result;
//     }
// }

// Convert the string to a char array, sort it, and convert it back to a String.
// Anagrams produce the same sorted string, which can be used as a HashMap key.
// class Solution {
//     public List<List<String>> groupAnagrams(String[] strs) {
//         Map<String, List<String>> map = new HashMap<>();

//         for (String str : strs) {
//             char[] chars = str.toCharArray();
//             Arrays.sort(chars);
//             String s = new String(chars);
//             if (map.containsKey(s)) {
//                 map.get(s).add(str);
//             } else {
//                 List<String> list = new ArrayList<>();
//                 list.add(str);
//                 map.put(s, list);
//             }
//         }
//         return new ArrayList<>(map.values());
//     }
// }

// Convert the string to a frequency map of all chars, create a key using count of chars
// Anagrams produce the same string, which can be used as a HashMap key.
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