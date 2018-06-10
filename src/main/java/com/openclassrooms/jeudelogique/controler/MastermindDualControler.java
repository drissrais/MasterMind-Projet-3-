package com.openclassrooms.jeudelogique.controler;

import com.openclassrooms.jeudelogique.model.MastermindModel;

public class MastermindDualControler {
	private String combinaisonSecreteJoueurModeDuel;
	private int nbChiffresAUtiliser;
	private MastermindModel model;
	
	public MastermindDualControler(MastermindModel model) {
		this.model = model;
	}
	
	public void setCombinaisonSecreteJoueurModeDuel(String combinaisonSecreteJoueur) {
		this.combinaisonSecreteJoueurModeDuel = combinaisonSecreteJoueur;
		control();
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
	
	public void setNbChiffresAUtiliser(int nbChiffresAUtiliser) {
		this.nbChiffresAUtiliser = nbChiffresAUtiliser;
		this.model.setNbChiffresAUtiliser(nbChiffresAUtiliser);
	}
	
	public void setChoixFinDePartie(String choixFinDePartie) {
		this.model.setChoixFinDePartie(choixFinDePartie);
	}
	
	public void setNbCases(int nbCases) {
		this.model.setNbCases(nbCases);
	}
	
	private void control() {
		if (this.nbChiffresAUtiliser == 4) {
			if (this.combinaisonSecreteJoueurModeDuel.matches("[0-3]+")) {
				this.model.setCombinaisonSecreteJoueurModeDuel(this.combinaisonSecreteJoueurModeDuel);
			}
		}
		if (this.nbChiffresAUtiliser == 5) {
			if (this.combinaisonSecreteJoueurModeDuel.matches("[0-4]+")) {
				this.model.setCombinaisonSecreteJoueurModeDuel(this.combinaisonSecreteJoueurModeDuel);
			}
		}
		if (this.nbChiffresAUtiliser == 6) {
			if (this.combinaisonSecreteJoueurModeDuel.matches("[0-5]+")) {
				this.model.setCombinaisonSecreteJoueurModeDuel(this.combinaisonSecreteJoueurModeDuel);
			}
		}
		if (this.nbChiffresAUtiliser == 7) {
			if (this.combinaisonSecreteJoueurModeDuel.matches("[0-6]+")) {
				this.model.setCombinaisonSecreteJoueurModeDuel(this.combinaisonSecreteJoueurModeDuel);
			}
		}
		if (this.nbChiffresAUtiliser == 8) {
			if (this.combinaisonSecreteJoueurModeDuel.matches("[0-7]+")) {
				this.model.setCombinaisonSecreteJoueurModeDuel(this.combinaisonSecreteJoueurModeDuel);
			}
		}
		if (this.nbChiffresAUtiliser == 9) {
			if (this.combinaisonSecreteJoueurModeDuel.matches("[0-8]+")) {
				this.model.setCombinaisonSecreteJoueurModeDuel(this.combinaisonSecreteJoueurModeDuel);
			}
		}
		if (this.nbChiffresAUtiliser == 10) {
			if (this.combinaisonSecreteJoueurModeDuel.matches("[0-9]+")) {
				this.model.setCombinaisonSecreteJoueurModeDuel(this.combinaisonSecreteJoueurModeDuel);
			}
		}
	}

}
