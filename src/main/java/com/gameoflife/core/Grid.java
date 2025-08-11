package com.gameoflife.core;

import java.util.*;


public class Grid {

    int[][] grid;
    String seed;

    public Grid() {
        grid = new int[60][40];
        seed = setSeed();
    }
    
    public static String setSeed() {
        System.out.println("Enter Seed Phrase:");

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        
        scanner.close();
        System.out.println("Seed Entered.");
        
        return name;
    }

}
