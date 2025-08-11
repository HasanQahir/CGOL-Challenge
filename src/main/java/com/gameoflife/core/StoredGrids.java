package com.gameoflife.core;


public class StoredGrids {

    Grid[] grids;
    int currentIndex = 0;

    public StoredGrids() {
        grids = new Grid[10]; // We want to store up to 10 grids
        for (int i = 0; i < grids.length; i++) {
            grids[i] = new Grid(); // Initialize each grid
        }
    }

    public void storeGrid(Grid grid) {
        if (currentIndex < grids.length) {
            grids[currentIndex] = grid;
            currentIndex++;
        } else {
            currentIndex = 0;
            grids[currentIndex] = grid; // Overwrite the oldest grid
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
}
