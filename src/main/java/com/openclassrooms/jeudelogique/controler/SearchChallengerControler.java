package com.openclassrooms.jeudelogique.controler;

import com.openclassrooms.jeudelogique.model.SearchModel;

public class SearchChallengerControler {
	private String proposition;
	private String combinaisonSecrete;
	private String choixFinDePartie;
	private String mode;
	
	private SearchModel searchModel;

	public SearchChallengerControler(SearchModel model) {
		this.searchModel = model;
	}
	
	public void setProposition(String proposition) {
		this.proposition = proposition;
		control();
	}
	
	public void setCombinaisonSecrete(String combinaisonSecrete) {
		this.combinaisonSecrete = combinaisonSecrete;
		this.searchModel.setCombinaisonSecreteModeChallenger(this.combinaisonSecrete);
	}
	
	public void setChoixFinDePartie(String choixFinDePartie) {
		this.choixFinDePartie = choixFinDePartie;
		this.searchModel.setChoixFinDePartie(this.choixFinDePartie);
	}

	public void control() {
		if (proposition.matches("^[0-9][0-9][0-9][0-9]$")) {
			this.searchModel.setProposition(this.proposition);
		}
	}
	
	public void setMode(String mode) {
		this.mode = mode;
		this.searchModel.setMode(this.mode);
	}
	
}
