package com.jizumer.dsa;

//2352. Equal Row and Column Pairs
public class EqualRowAndColumnsPairs {

    public int equalPairs(int[][] grid) {
        int[] rowSums = new int[grid.length];
        int[] colSums = new int[grid.length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                rowSums[i] += grid[i][j];
                colSums[j] += grid[i][j];
            }
        }
        int count = 0;
        for (int r = 0; r < rowSums.length; r++) {
            for (int c = 0; c < colSums.length; c++) {
                if (rowSums[r] == colSums[c] && checkRowAndCol(r, c, grid)) count++;
            }
        }
        return count;
    }

    private boolean checkRowAndCol(int r, int c, int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            if (grid[r][i] != grid[i][c]) return false;
        }
        return true;
    }


}
