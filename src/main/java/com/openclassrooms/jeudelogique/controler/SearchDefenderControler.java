package com.openclassrooms.jeudelogique.controler;

import com.openclassrooms.jeudelogique.model.SearchModel;

public class SearchDefenderControler {
	
	private SearchModel searchModel;
	
	public SearchDefenderControler(SearchModel model) {
		this.searchModel = model;
	}
	
	public void setCombinaisonSecreteModeDefenseur(String combinaisonSecrete) {
		this.searchModel.setCombinaisonSecreteModeDefenseur(combinaisonSecrete);
	}
	
	public void setMode(String mode) {
		this.searchModel.setMode(mode);
	}
	
	public void setChoixFinDePartie(String choixFinDePartie) {
		this.searchModel.setChoixFinDePartie(choixFinDePartie);
	}
	
	public void genererPropositionOrdinateurModeDefenseur() {
		this.searchModel.genererPropositionOrdinateurModeDefenseur();
	}

}
