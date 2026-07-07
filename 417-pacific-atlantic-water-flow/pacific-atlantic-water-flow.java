class Solution {
    int[][] directions = new int[][] { { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 0 } };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;
        boolean[][] pacificReachable = new boolean[rows][cols];
        boolean[][] atlanticReachable = new boolean[rows][cols];
        List<List<Integer>> returnList = new ArrayList<>();

        for (int i = 0; i < cols; i++) {
            dfs(heights, 0, i, pacificReachable, heights[0][i]);
            dfs(heights, rows - 1, i, atlanticReachable, heights[rows - 1][i]);
        }

        for (int i = 0; i < rows; i++) {
            dfs(heights, i, 0, pacificReachable, heights[i][0]);
            dfs(heights, i, cols - 1, atlanticReachable, heights[i][cols - 1]);
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                    returnList.add(Arrays.asList(i, j));
                }
            }
        }
        return returnList;
    }

    private void dfs(int[][] heights, int row, int col, boolean[][] visited, int prevHeight) {
        if (row < 0 || row > heights.length - 1 || col < 0 || col > heights[0].length - 1
                || heights[row][col] < prevHeight || visited[row][col]) {
            return;
        }
        visited[row][col] = true;

        for (int[] d : directions) {
            int newRow = row + d[0];
            int newCol = col + d[1];
            dfs(heights, newRow, newCol, visited, heights[row][col]);
        }

    }
}