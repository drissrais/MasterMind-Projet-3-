package com.openclassrooms.jeudelogique.utilities;

import java.util.Random;

public class RandomCombination {
	
	public static String generateRandomCombination(int n) {
		String str = "";
		for (int i = 0; i < n; i++) {
			Random randomGenerator = new Random();
			str += randomGenerator.nextInt(10);
		}
		return str;
	}
}
