package com.openclassrooms.jeudelogique.utilities;

import java.util.Random;

public class RandomCombination {
	
	public static int[] generateRandomCombination(int n) {
		int[] combinaison = new int[n]; 
		for (int i = 0; i < n; i++) {
			Random randomGenerator = new Random();
			combinaison[i] = randomGenerator.nextInt(10);
		}
		return combinaison;
	}
}
