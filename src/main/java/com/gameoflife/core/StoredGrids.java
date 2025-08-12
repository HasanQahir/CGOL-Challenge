package com.gameoflife.core;


public class StoredGrids {

    Grid[] grids;
    int currentIndex = 0;

    public StoredGrids() {
        grids = new Grid[10]; // We want to store up to 10 grids
    }

    public void storeGrid(Grid grid) {
        if (currentIndex < grids.length) {
            grids[currentIndex] = new Grid(grid);
            currentIndex++;
        } else {
            currentIndex = 0;
            grids[currentIndex] = new Grid(grid); // Overwrite the oldest grid
        }
    }

    public Grid[] getGrids() {
        Grid[] retGrids = new Grid[10];
        for (int i = 0; i < grids.length; i++) {
            int j = (i + currentIndex) % grids.length;
            retGrids[i] = grids[j];
        }

        return retGrids;

    }

    public Boolean matchGrid(Grid grid) {
        outer:
        for (Grid storedGrid : grids) {
            for (int i = 0; i < storedGrid.board.length; i++) {
                for (int j = 0; j < storedGrid.board[0].length; j++) {
                    int a = (int)storedGrid.board[i][j];
                    int b = (int)grid.board[i][j];
                    // System.out.println("Comparing " + a + " with " + b);
                    if (a != b) {
                        // System.out.println("Found mismatch!");
                        break outer;
                    }
                }
            }
        return true; // Found a matching grid    
        }
    
    return false; // No match found
    }
}
