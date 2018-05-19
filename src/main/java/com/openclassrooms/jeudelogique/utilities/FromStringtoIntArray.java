package com.openclassrooms.jeudelogique.utilities;

public class FromStringtoIntArray {
	public static int[] convert(String str) {
		String[] stringArray = str.split("");
		int[] intArray = new int[stringArray.length];
		for (int i = 0; i < intArray.length; i++) {
			try {
				intArray[i] = Integer.parseInt(stringArray[i]);
			} catch (NumberFormatException e) {
				System.out.println("not a number");
				e.printStackTrace();
			}
		}
		return intArray;
	}
}
