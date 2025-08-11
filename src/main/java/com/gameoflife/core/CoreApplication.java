package com.gameoflife.core;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);

		System.out.println("Enter Seed Phrase:");

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        byte[] hash = digest.digest(name.getBytes(StandardCharsets.UTF_8));

        scanner.close();
        System.out.println("Seed Entered.");

		Grid newGrid = new Grid();
		StoredGrids storage = new StoredGrids();
		String[] hex = new String[hash.length];
		for (int i = 0; i < hash.length; i++) {
			 int val = (hash[i] & 0xFF);
			 hex[i] = Integer.toHexString(val);
			System.out.println(hex[i]);		
			System.out.print(hash[i]);

		}
	}
}
