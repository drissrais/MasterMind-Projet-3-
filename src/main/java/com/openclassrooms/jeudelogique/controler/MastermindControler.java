package com.openclassrooms.jeudelogique.controler;

import com.openclassrooms.jeudelogique.model.MastermindModel;

public class MastermindControler {
	private String proposition;
	private String choixFinDePartie;
	private String combinaisonSecrete;
	private MastermindModel mastermindModel;

	public MastermindControler(MastermindModel model) {
		this.mastermindModel = model;
	}
	
	public void setProposition(String proposition) {
		this.proposition = proposition;
		control();
	}
	
	public void setCombinaisonSecrete(String combinaisonSecrete) {
		this.combinaisonSecrete = combinaisonSecrete;
		this.mastermindModel.setCombinaisonSecrete(this.combinaisonSecrete);
	}
	
	public void setChoixFinDePartie(String choixFinDePartie) {
		this.choixFinDePartie = choixFinDePartie;
		this.mastermindModel.setChoixFinDePartie(this.choixFinDePartie);
	}

	public void control() {
		if (proposition.matches("^[0-9][0-9][0-9][0-9]$")) {
			this.mastermindModel.setProposition(this.proposition);
		}
	}

}
