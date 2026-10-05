// class Solution {
//     public int characterReplacement(String s, int k) {
//         int n = s.length();
//         int maxL = 0;
//         for (int start = 0; start < n; start++) {
//             Map<Character, Integer> map = new HashMap<>();
//             int maxFreq = 0;
//             for (int end = start; end < n; end++) {
//                 int len = end - start + 1;
//                 char ch = s.charAt(end);
//                 int freq = map.getOrDefault(ch, 0) + 1;
//                 map.put(ch, freq);
//                 maxFreq = Math.max(freq, maxFreq);
//                 if (k >= len - maxFreq) {
//                     maxL = Math.max(len, maxL);
//                 }
//             }
//         }
//         return maxL;
//     }
// }

class Solution {
    public int characterReplacement(String s, int k) {
        int left = 0;
        int maxL = 0;
        int maxF = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            int freq = map.getOrDefault(ch, 0) + 1;
            maxF = Math.max(freq, maxF);
            map.put(ch, freq);
            int len = right - left + 1;
            if (k < len - maxF) {
                char chl = s.charAt(left);
                map.put(chl, map.get(chl) - 1);
                left++;
            }
            len = right - left + 1;
            maxL = Math.max(len, maxL);
        }
        return maxL;
    }
}
