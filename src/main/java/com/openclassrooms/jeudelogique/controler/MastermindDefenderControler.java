package com.openclassrooms.jeudelogique.controler;

import com.openclassrooms.jeudelogique.model.MastermindModel;

public class MastermindDefenderControler {
	
	private MastermindModel model;
	
	public MastermindDefenderControler(MastermindModel mod) {
		this.model = mod;
	}
	
	public void setCombinaisonSecreteModeDefenseur(String combinaisonSecreteModeDefenseur) {
		this.model.setCombinaisonSecreteModeDefenseur(combinaisonSecreteModeDefenseur);
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
