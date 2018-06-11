package com.openclassrooms.jeudelogique.view;

import java.util.ArrayList;

import com.openclassrooms.jeudelogique.model.MastermindModel;
import com.openclassrooms.jeudelogique.model.SearchModel;

public class Main {


	public static void main(String[] args) {
		ArrayList<byte[]> listPossibilities = new ArrayList<>();
		long startTime = System.currentTimeMillis();
		for (byte i = 0; i <= 9; i++) {
			for (byte j = 0; j <= 9; j++) {
				for (byte k = 0; k <= 9; k++) {
					for (byte l = 0; l <= 9; l++) {
						for (byte m = 0; m <= 9; m++) {
							for (byte n = 0; n <= 9; n++) {
								byte[] tab = {i, j, k, l, m, n};
//								String str = "";
//								for (byte b : tab) {
//									str += b;
//								}
								listPossibilities.add(tab);
							}
						}
					}
				}
			}
		}
		System.out.println("Temps de lecture avec String : " + (System.currentTimeMillis() - startTime));
		System.out.println(listPossibilities.size());
		// SearchModel sModel = new SearchModel();
		// MastermindModel mModel = new MastermindModel();
		// Fenetre fen = new Fenetre(sModel, mModel, true);
		// fen.setVisible(true);
		// byte[] bytes = {1, 2, 3, 4};
		// String str = "";
		// for (byte b : bytes) {
		// str += b;
		// }
		// System.out.println(str);
	}

}
