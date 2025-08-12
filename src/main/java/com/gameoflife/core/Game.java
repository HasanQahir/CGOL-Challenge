package com.gameoflife.core;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Game {

    public String[] seed;
    public Grid grid;
    public StoredGrids storage;
    public int score;

    public Game() {
        seed = getSeed();
        grid = new Grid();
        storage = new StoredGrids();
        score = 0;
        instantiateGrid();
    }

    public String[] getSeed() {
        System.out.println("Enter Seed Phrase:");

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        byte[] hash = digest.digest(name.getBytes(StandardCharsets.UTF_8));

        scanner.close();
        
		String[] hex = new String[hash.length];
		String[] bin = new String[hash.length];
		for (int i = 0; i < hash.length; i++) {
			 int val = (hash[i] & 0xFF);

			 hex[i] = Integer.toHexString(val);
			 if (hex[i].length() < 2) {
				 hex[i] = "0" + hex[i]; // Ensure two-digit hex representation
			 }
			 
			 bin[i] = Integer.toBinaryString(val);
			 if (bin[i].length() < 8) {
				 bin[i] = "0".repeat(8 - bin[i].length()) + bin[i]; // Ensure eight-digit binary representation
			 }
		}
        return bin;
    }

    public void instantiateGrid() {
        int midX = grid.board.length / 2;
		int midY = grid.board[0].length / 2;

		// Use binary hash to fill out cells around the centre
		for (int i = 0; i < 4; i++) {
			int dirX = 0, dirY = 0;
			switch (i) {
				case 0: // Upper-left quadrant
					dirX = -1;
					dirY = 1;
					break;
			
				case 1: // Upper-right quadrant
					dirX = 1;
					dirY = 1;
					break;

				case 2: // Lower-left quadrant
					dirX = -1;
					dirY = -1;
					break;
				
				case 3: // Lower-right quadrant
					dirX = 1;
					dirY = -1;
					break;
			}
				
			for (int j = 0; j < 64; j++) {
				int pos = (i * 64) + j;
				char val = seed[pos / 8].charAt(pos % 8);
				String str = String.valueOf(val);
			
				grid.board[midX + (dirX * j / 8)][midY + (dirY * j % 8)] = Integer.parseInt(str);
			}
		}
        // Set initial score
        score = grid.countLiveCells();
    }

    public void iterate() {
        storage.storeGrid(grid);
        grid.nextGeneration();
        score += grid.countLiveCells();
    }
}
