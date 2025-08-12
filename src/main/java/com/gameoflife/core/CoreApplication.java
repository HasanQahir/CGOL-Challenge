package com.gameoflife.core;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);

		Game game = new Game();
		int finalScore = 0;
		for (int i = 0; i < game.seed.length; i++) {
			System.out.print(game.seed[i]);
		}
		
		System.out.println("\n");

		while (true) {
		
			game.iterate();

			// System.out.println();
			// for (int i = 0; i < game.grid.board.length; i++) {
            // 	System.out.println();
            // 	for (int j = 0; j < game.grid.board[0].length; j++) {
            //     	System.out.print(game.grid.board[i][j]);
            // 	}
        	// }

			if (game.storage.matchGrid(game.grid) || game.grid.generation > 999) {
				finalScore = game.score;
				break;
			}			
		}

		System.out.println("Game Over! Final Score: " + finalScore);
		System.out.println("Total Generations: " + game.grid.generation);

		System.exit(0);
	}
}
