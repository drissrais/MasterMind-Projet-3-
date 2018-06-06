package com.openclassrooms.jeudelogique.controler;

import com.openclassrooms.jeudelogique.model.MastermindModel;

public class MastermindDualControler {
	private MastermindModel model;
	
	public MastermindDualControler(MastermindModel model) {
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

}
