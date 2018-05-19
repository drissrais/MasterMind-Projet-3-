package com.openclassrooms.jeudelogique.utilities;

public class FromIntArrayToString {
	
	public static String convert(int[] tab) {
		String str = "";
		for (int i : tab) {
			str += i;
		}
		return str;
	}

}
