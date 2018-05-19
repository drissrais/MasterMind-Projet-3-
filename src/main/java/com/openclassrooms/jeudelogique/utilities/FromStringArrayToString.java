package com.openclassrooms.jeudelogique.utilities;

public class FromStringArrayToString {
	
	public static String convert(String[] tab) {
		StringBuilder builder = new StringBuilder();
		for(String s : tab) {
		    builder.append(s);
		}
		String str = builder.toString();
		return str;
	}
	
}
