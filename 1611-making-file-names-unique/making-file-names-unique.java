// Brute force
// class Solution {
//     public String[] getFolderNames(String[] names) {
//         Set<String> set = new HashSet<>();
//         for (int i = 0; i < names.length; i++) {
//             String s = names[i];
//             if (set.contains(s)) {
//                 int k = 1;
//                 while (set.contains(s + "(" + k + ")")) {
//                     k++;
//                 }
//                 s = s+"(" + k + ")";
//                 names[i] = s;
//             }
//             set.add(s);
//         }
//         return names;
//     }
// }

class Solution {
    public String[] getFolderNames(String[] names) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            String s = names[i];
            if (map.containsKey(s)) {
                int k = map.getOrDefault(s, 0);
                String can = s + "(" + k + ")";
                while (map.containsKey(can)) {
                    k++;
                    can = s + "(" + k + ")";
                }
                names[i] = can;
                map.put(s, k + 1);
                map.put(can, 1);
            } else {
                map.put(s, 1);
            }
        }
        return names;

    }
}