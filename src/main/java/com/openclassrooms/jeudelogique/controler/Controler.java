package com.openclassrooms.jeudelogique.controler;

import com.openclassrooms.jeudelogique.model.Model;
import com.openclassrooms.jeudelogique.observer.Observable;

public class Controler {
	private String proposition;
	private Model model;
	
	public Controler(Observable model) {
		this.model = (Model)model;
	}
	
	public void setProposition(String proposition) {
		this.proposition = proposition;
		control();
	}
	
	public void control() {
		if (this.proposition.matches("^[0-9][0-9][0-9][0-9]$")) {
			this.model.setProposition(this.proposition);
		}
	}

}
