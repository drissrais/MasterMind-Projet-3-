package com.openclassrooms.jeudelogique.view;

import com.openclassrooms.jeudelogique.model.MastermindModel;
import com.openclassrooms.jeudelogique.model.SearchModel;

public class Main {

	public static void main(String[] args) {
		 SearchModel sModel = new SearchModel();
		 MastermindModel mModel = new MastermindModel();
		 Fenetre fen = new Fenetre(sModel, mModel);
		 fen.setVisible(true);
	}
}
