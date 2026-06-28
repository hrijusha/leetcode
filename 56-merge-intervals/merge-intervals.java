class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int prevStart = intervals[0][0];
        int prevEnd = intervals[0][1];
        List<int[]> list = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            //merge
            if (start <= prevEnd) {
                prevEnd = Math.max(prevEnd, end);
            } else {
                list.add(new int[] { prevStart, prevEnd });
                prevStart = start;
                prevEnd = end;
            }
        }
        list.add(new int[] { prevStart, prevEnd });

        return list.toArray(new int[list.size()][]);

    }
}