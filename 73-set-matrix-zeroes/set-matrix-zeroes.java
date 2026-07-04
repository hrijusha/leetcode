class Solution {
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[] rowTracker = new boolean[rows];
        boolean[] colTracker = new boolean[cols];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    rowTracker[i] = true;
                    colTracker[j] = true;
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rowTracker[i] || colTracker[j]) {
                    matrix[i][j] = 0;
                }
            }
        }

    }
}