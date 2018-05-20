package com.openclassrooms.jeudelogique.controler;

import com.openclassrooms.jeudelogique.model.Model;
import com.openclassrooms.jeudelogique.observer.Observable;

public class Controler {
	private String proposition;
	private String gameName;
	private String gameMode;
	private Model model;
	
	public Controler(Observable model) {
		this.model = (Model)model;
	}
	
	public void setProposition(String proposition) {
		this.proposition = proposition;
		this.model.setProposition(this.proposition);
	}
	
	public void setGameName(String gameName) {
		this.gameName = gameName;
		this.model.setGameName(this.gameName);
	}
	
	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
		this.model.setGameMode(this.gameMode);
	}

}
