class Solution {
    int[][] directions = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        char first = word.charAt(0);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == first) {
                    if (dfs(board, word, i, j, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int row, int col, int index) {
        if (index == word.length()) {
            return true;
        }

        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
                || board[row][col] != word.charAt(index)) {
            return false;
        }

        char temp = board[row][col];
        board[row][col] = '#';

        for (int[] dir : directions) {
            int nrow = row + dir[0];
            int ncol = col + dir[1];

            if (dfs(board, word, nrow, ncol, index + 1)) {
                return true;
            }
        }

        board[row][col] = temp;
        return false;
    }
}