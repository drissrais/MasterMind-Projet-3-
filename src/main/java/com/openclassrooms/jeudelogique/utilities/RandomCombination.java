package com.openclassrooms.jeudelogique.utilities;

import java.util.Random;

public class RandomCombination {
	
	public static void generateRandomCombinaison(int[] combinaison) {
		for (int i = 0; i < 4; i++) {
			Random randomGenerator = new Random();
			combinaison[i] = randomGenerator.nextInt(10);
		}
	}
}
