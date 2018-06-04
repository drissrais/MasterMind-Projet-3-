package com.openclassrooms.jeudelogique.controler;

import com.openclassrooms.jeudelogique.model.SearchModel;

public class SearchDualControler {
	private SearchModel model;
	
	public SearchDualControler(SearchModel model) {
		this.model = model;
	}
	
	public void setCombinaisonSecreteJoueurModeDuel(String combinaisonSecreteJoueur) {
		this.model.setCombinaisonSecreteJoueurModeDuel(combinaisonSecreteJoueur);
	}
	
	public void setCombinaisonSecreteOrdinateurModeDuel(String combinaisonSecreteOrdinateur) {
		this.model.setCombinaisonSecreteOrdinateurModeDuel(combinaisonSecreteOrdinateur);
	}
	
	public void setPropositionJoueurModeDuel(String propositionJoueurModeDuel) {
		this.model.setPropositionJoueurModeDuel(propositionJoueurModeDuel);
	}
	
	public void setMode(String mode) {
		this.model.setMode(mode);
	}
	
	public void setChoixFinDePartie(String choixFinDePartie) {
		this.model.setChoixFinDePartie(choixFinDePartie);
	}
	
	public void genererPropositionOrdinateurModeDefenseur() {
		this.model.genererPropositionOrdinateurModeDefenseur();
	}

}
