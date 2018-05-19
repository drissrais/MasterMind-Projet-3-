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
		this.model.setProposition(this.proposition);
	}

}
