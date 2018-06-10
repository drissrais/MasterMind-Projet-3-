package com.openclassrooms.jeudelogique.controler;

import com.openclassrooms.jeudelogique.model.MastermindModel;

public class MastermindChallengerControler {
	private String combinaisonSecrete;
	private int nbChiffresAUtiliser;
	private MastermindModel mastermindModel = null;

	public MastermindChallengerControler(MastermindModel model) {
		this.mastermindModel = model;
	}
	
	public void setProposition(String proposition) {
		this.mastermindModel.setPropositionModeChallenger(proposition);
	}
	
	public void setCombinaisonSecrete(String combinaisonSecrete) {
		this.combinaisonSecrete = combinaisonSecrete;
		control();
	}
	
	public void setChoixFinDePartie(String choixFinDePartie) {
		this.mastermindModel.setChoixFinDePartie(choixFinDePartie);
	}

	public void setMode(String mode) {
		this.mastermindModel.setMode(mode);
	}
	
	public void setNbChiffresAUtiliser(int nbChiffresAUtiliser) {
		this.nbChiffresAUtiliser = nbChiffresAUtiliser;
		this.mastermindModel.setNbChiffresAUtiliser(nbChiffresAUtiliser);
	}
	
	private void control() {
		if (this.nbChiffresAUtiliser == 4) {
			if (this.combinaisonSecrete.matches("[0-3]+")) {
				this.mastermindModel.setCombinaisonSecreteModeChallenger(combinaisonSecrete);
			}
		}
		if (this.nbChiffresAUtiliser == 5) {
			if (this.combinaisonSecrete.matches("[0-4]+")) {
				this.mastermindModel.setCombinaisonSecreteModeChallenger(combinaisonSecrete);
			}
		}
		if (this.nbChiffresAUtiliser == 6) {
			if (this.combinaisonSecrete.matches("[0-5]+")) {
				this.mastermindModel.setCombinaisonSecreteModeChallenger(combinaisonSecrete);
			}
		}
		if (this.nbChiffresAUtiliser == 7) {
			if (this.combinaisonSecrete.matches("[0-6]+")) {
				this.mastermindModel.setCombinaisonSecreteModeChallenger(combinaisonSecrete);
			}
		}
		if (this.nbChiffresAUtiliser == 8) {
			if (this.combinaisonSecrete.matches("[0-7]+")) {
				this.mastermindModel.setCombinaisonSecreteModeChallenger(combinaisonSecrete);
			}
		}
		if (this.nbChiffresAUtiliser == 9) {
			if (this.combinaisonSecrete.matches("[0-8]+")) {
				this.mastermindModel.setCombinaisonSecreteModeChallenger(combinaisonSecrete);
			}
		}
		if (this.nbChiffresAUtiliser == 10) {
			if (this.combinaisonSecrete.matches("[0-9]+")) {
				this.mastermindModel.setCombinaisonSecreteModeChallenger(combinaisonSecrete);
			}
		}
	}

}
