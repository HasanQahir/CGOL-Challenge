package com.gameoflife.core;



public class Grid {

    int[][] grid;
    int generation;

    public Grid() {
        grid = new int[60][40];
        generation = 0;
    }
    

    public int getAdjCells(int x, int y) {
        int cells = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; i < 2; j++) {
                if (i == 0 && j == 0) continue; // Skip the cell itself
                int newX = x + i;
                int newY = y + j;

                // Check bounds
                if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length) {
                    cells++;
                } else {
                    continue; // Out of bounds, treat as dead cell
                }
            }
        }

        return cells;
    }

    // Method to check the state of a cell and update it based on the rules of Conway's Game of Life
    public void checkCell(int x, int y) {
        int aliveCount = getAdjCells(x, y);

        if (grid[x][y] == 1) {
            // Cell is currently alive
            if (aliveCount < 2 || aliveCount > 3) {
                grid[x][y] = 0; // Cell dies
            } else if (aliveCount == 2) {
                grid[x][y] = 1; // Cell remains alive
            }
        } else {
            // Cell is currently dead
            if (aliveCount == 3) {
                grid[x][y] = 1; // Cell becomes alive
            }
        }
    }

    //! Ensure the current grid has been stored before invoking this method
    public void nextGeneration() {
        int[][] newGrid = new int[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // Check each cell and update the new grid
                checkCell(i, j);
                newGrid[i][j] = grid[i][j];
            }
        }

        // Update the current grid to the new grid
        grid = newGrid;
        generation++;
    }
}
