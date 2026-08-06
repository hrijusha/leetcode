class Solution {
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Map<Integer, int[][]> map = new HashMap<>();
        map.put(1, new int[][] { { 0, -1 }, { 0, 1 } }); // Left,Right
        map.put(2, new int[][] { { -1, 0 }, { 1, 0 } }); // Up,Down
        map.put(3, new int[][] { { 0, -1 }, { 1, 0 } }); // Left,Down
        map.put(4, new int[][] { { 0, 1 }, { 1, 0 } }); // Right,Down
        map.put(5, new int[][] { { 0, -1 }, { -1, 0 } }); // Left,Up
        map.put(6, new int[][] { { 0, 1 }, { -1, 0 } }); // Right,Up

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        q.offer(new int[] { 0, 0 });
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int r = current[0];
            int c = current[1];
            int currentVal = grid[r][c];
            if (r == m - 1 && c == n - 1) {
                return true;
            }
            //get all the connections from that index
            int[][] directions = map.get(currentVal);
            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                boolean hasConnection = false;
                if (nr >= 0 && nc >= 0 && nr < m && nc < n && visited[nr][nc] == false) {
                    int nextVal = grid[nr][nc];
                    int[][] nextDirections = map.get(nextVal);
                    for (int[] ndir : nextDirections) {
                        if (ndir[0] == -dir[0] && ndir[1] == -dir[1]) {
                            hasConnection = true;
                            break;
                        }
                    }
                    if (hasConnection) {
                        visited[nr][nc] = true;
                        q.offer(new int[] { nr, nc });
                    }
                }
            }
        }
        return false;
    }
}