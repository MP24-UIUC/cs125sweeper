package com.cs125.cs125sweeper.util;

import java.util.Random;

public class Generator {
    // This generates our grid with every tile either being a mine or not.
    public static int[][] generateGrid(int mineCount, final int row, final int column) {
        // The random number generator provides a randomized value for each tile.
        Random r = new Random();
        int [][] grid = new int[row][column];
        for (int x = 0; x < row; x++) {
            grid[x] = new int[column];
        }
        // While the number of mines are greater than 0.
        while (mineCount > 0) {
            int x = r.nextInt(row);
            int y = r.nextInt(column);
            // Mines are stored in the grid as -1.
            if (grid[x][y] != -1) {
                grid[x][y] = -1;
                mineCount--;
            }
        }
        grid = calculateAdjacent(grid, row, column);
        return grid;
    }

    // This calculates the number of mines around a tile.
    private static int[][] calculateAdjacent(int[][] grid , final int row , final int column) {
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                grid[x][y] = getAdjacentNumber(grid, x, y, row, column);
            }
        }
        return grid;
    }

    // Checks if there's a mine at current tile.
    private static boolean isMineAt(final int [][] grid, final int x , final int y , final int row , final int column) {
        if (x >= 0 && y >= 0 && x < row && y < column) {
            // If the tile has value -1, it's a mine.
            if (grid[x][y] == -1) {
                return true;
            }
        }
        return false;
    }

    // This is the helper function for calculateAdjacent.
    private static int getAdjacentNumber(final int grid[][] , final int x , final int y , final int row , final int column) {
        // If the current tile is a mine.
        if (grid[x][y] == -1) {
            return -1;
        }
        // Otherwise, count number of mines adjacent to the current tile.
        int count = 0;
        if (isMineAt(grid, x - 1, y + 1, row, column)) count++; // Check Up Left
        if (isMineAt(grid, x, y + 1, row, column)) count++; // Check Up
        if (isMineAt(grid, x + 1, y + 1, row, column)) count++; // Check Up Right
        if (isMineAt(grid, x - 1, y, row, column)) count++; // Check Left
        if (isMineAt(grid, x + 1, y, row, column)) count++; // Check Right
        if (isMineAt(grid, x - 1, y - 1, row, column)) count++; // Check Down Left
        if (isMineAt(grid, x, y - 1, row, column)) count++; // Check Down
        if (isMineAt(grid, x + 1, y - 1,row,column)) count++; // Check Down Right
        return count;
    }
}