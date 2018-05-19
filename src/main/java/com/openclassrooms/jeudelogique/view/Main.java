package com.openclassrooms.jeudelogique.view;

import com.openclassrooms.jeudelogique.model.Model;
import com.openclassrooms.jeudelogique.observer.Observable;

public class Main {

	public static void main(String[] args) {
		Observable model = new Model();
		Fenetre fen = new Fenetre(model);
		fen.setVisible(true);
	}

}
