class Solution {
    public int numIslands(char[][] grid) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        int count = 0;
        boolean[][] visited = new boolean[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (grid[i][j] == '1' && visited[i][j] == false) {
                    bfs(grid, visited, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, boolean[][] visited, int i, int j) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { i, j });
        visited[i][j] = true;

        int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currRow = current[0];
            int currCol = current[1];
            for (int[] dir : directions) {
                int row = currRow + dir[0];
                int col = currCol + dir[1];
                if (row >= 0 && row < numRows && col >= 0 && col < numCols && grid[row][col] == '1'
                        && visited[row][col] == false) {
                    queue.add(new int[] { row, col });
                    visited[row][col] = true;
                }
            }
        }

    }
}