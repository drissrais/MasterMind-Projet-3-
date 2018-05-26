package com.openclassrooms.jeudelogique.controler;

import com.openclassrooms.jeudelogique.model.SearchModel;

public class SearchControler {
	private String proposition;
	private String combinaisonSecrete;
	private String choixFinDePartie;
	private SearchModel searchModel;

	public SearchControler(SearchModel model) {
		this.searchModel = model;
	}
	
	public void setProposition(String proposition) {
		this.proposition = proposition;
		control();
	}
	
	public void setCombinaisonSecrete(String combinaisonSecrete) {
		this.combinaisonSecrete = combinaisonSecrete;
		this.searchModel.setCombinaisonSecrete(this.combinaisonSecrete);
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
	
}
