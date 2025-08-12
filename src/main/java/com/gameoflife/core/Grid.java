package com.gameoflife.core;



public class Grid {

    int[][] board;
    int generation;

    public Grid() {
        board = new int[60][40];
        generation = 0;
    }
    public Grid(Grid grid) {
        this.board = grid.board;
        this.generation = grid.generation;
    }
    
    public int countLiveCells() {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getAdjCells(int x, int y) {
        int cells = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) continue; // Skip the cell itself
                int newX = x + i;
                int newY = y + j;

                // Check bounds
                if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length) {
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

        if (board[x][y] == 1) {
            // Cell is currently alive
            if (aliveCount < 2 || aliveCount > 3) {
                board[x][y] = 0; // Cell dies
            } else if (aliveCount == 2) {
                board[x][y] = 1; // Cell remains alive
            }
        } else {
            // Cell is currently dead
            if (aliveCount == 3) {
                board[x][y] = 1; // Cell becomes alive
            }
        }
    }

    //! Ensure the current grid has been stored before invoking this method
    public void nextGeneration() {
        int[][] newGrid = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // Check each cell and update the new grid
                checkCell(i, j);
                newGrid[i][j] = board[i][j];
            }
        }

        // Update the current grid to the new grid
        board = newGrid;
        generation++;
    }
}
