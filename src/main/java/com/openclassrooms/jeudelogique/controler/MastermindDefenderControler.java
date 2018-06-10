package com.openclassrooms.jeudelogique.controler;

import com.openclassrooms.jeudelogique.model.MastermindModel;

public class MastermindDefenderControler {
	private String combinaisonSecreteModeDefenseur;
	private int nbChiffresAUtiliser;
	private MastermindModel model;
	
	public MastermindDefenderControler(MastermindModel mod) {
		this.model = mod;
	}
	
	public void setCombinaisonSecreteModeDefenseur(String combinaisonSecreteModeDefenseur) {
		this.combinaisonSecreteModeDefenseur = combinaisonSecreteModeDefenseur;
		control();
	}
	
	public void setMode(String mode) {
		this.model.setMode(mode);
	}
	
	public void setChoixFinDePartie(String choixFinDePartie) {
		this.model.setChoixFinDePartie(choixFinDePartie);
	}
	
	public void setNbCases(int nbCases) {
		this.model.setNbCases(nbCases);
	}
	
	public void setNbChiffresAUtiliser(int nbChiffresAUtiliser) {
		this.nbChiffresAUtiliser = nbChiffresAUtiliser;
		this.model.setNbChiffresAUtiliser(nbChiffresAUtiliser);
	}
	
	public void genererPropositionOrdinateurModeDefenseur() {
		this.model.genererPropositionOrdinateurModeDefenseur();
	}
	
	private void control() {
		if (this.nbChiffresAUtiliser == 4) {
			if (this.combinaisonSecreteModeDefenseur.matches("[0-3]+")) {
				this.model.setCombinaisonSecreteModeDefenseur(this.combinaisonSecreteModeDefenseur);
			}
		}
		if (this.nbChiffresAUtiliser == 5) {
			if (this.combinaisonSecreteModeDefenseur.matches("[0-4]+")) {
				this.model.setCombinaisonSecreteModeDefenseur(combinaisonSecreteModeDefenseur);
			}
		}
		if (this.nbChiffresAUtiliser == 6) {
			if (this.combinaisonSecreteModeDefenseur.matches("[0-5]+")) {
				this.model.setCombinaisonSecreteModeDefenseur(this.combinaisonSecreteModeDefenseur);
			}
		}
		if (this.nbChiffresAUtiliser == 7) {
			if (this.combinaisonSecreteModeDefenseur.matches("[0-6]+")) {
				this.model.setCombinaisonSecreteModeDefenseur(this.combinaisonSecreteModeDefenseur);
			}
		}
		if (this.nbChiffresAUtiliser == 8) {
			if (this.combinaisonSecreteModeDefenseur.matches("[0-7]+")) {
				this.model.setCombinaisonSecreteModeDefenseur(this.combinaisonSecreteModeDefenseur);
			}
		}
		if (this.nbChiffresAUtiliser == 9) {
			if (this.combinaisonSecreteModeDefenseur.matches("[0-8]+")) {
				this.model.setCombinaisonSecreteModeDefenseur(this.combinaisonSecreteModeDefenseur);
			}
		}
		if (this.nbChiffresAUtiliser == 10) {
			if (this.combinaisonSecreteModeDefenseur.matches("[0-9]+")) {
				this.model.setCombinaisonSecreteModeDefenseur(this.combinaisonSecreteModeDefenseur);
			}
		}
	}

}
