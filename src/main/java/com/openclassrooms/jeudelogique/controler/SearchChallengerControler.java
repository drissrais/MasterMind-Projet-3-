package com.openclassrooms.jeudelogique.controler;

import com.openclassrooms.jeudelogique.model.SearchModel;

public class SearchChallengerControler {
	private SearchModel searchModel;

	public SearchChallengerControler(SearchModel model) {
		this.searchModel = model;
	}
	
	public void setProposition(String proposition) {
		this.searchModel.setProposition(proposition);
	}
	
	public void setCombinaisonSecrete(String combinaisonSecrete) {
		this.searchModel.setCombinaisonSecreteModeChallenger(combinaisonSecrete);
	}
	
	public void setChoixFinDePartie(String choixFinDePartie) {
		this.searchModel.setChoixFinDePartie(choixFinDePartie);
	}
	
	public void setMode(String mode) {
		this.searchModel.setMode(mode);
	}
	
}
