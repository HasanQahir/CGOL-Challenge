package com.gameoflife.core;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);

		Game game = new Game();
		int finalScore = 0;

		while (true) {
		
			game.iterate();
			System.out.println("Current Generation: " + game.grid.generation);

			if (game.storage.matchGrid(game.grid) || game.grid.generation > 999) {
				finalScore = game.score;
				break;
			}	
		}

		

		System.out.println("Game Over! Final Score: " + finalScore);
		System.out.println("Total Generations: " + game.grid.generation);
	}
}
