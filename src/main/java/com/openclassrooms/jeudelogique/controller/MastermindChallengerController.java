package com.openclassrooms.jeudelogique.controller;

import com.openclassrooms.jeudelogique.model.MastermindModel;

/* Pattern MVC - Classe relative au Controler du jeu Mastermind en mode challenger.
 * Dans le cadre de ce programme, le but du Controler est de transferer
 * les donnees saisies par l'utilisateur dans la vue au modele de donnees
 * associe au jeu Mastermind.
 */
public class MastermindChallengerController {
	private String combinaisonSecrete;
	private int nbChiffresAUtiliser;
	private MastermindModel mastermindModel = null;

	public MastermindChallengerController(MastermindModel model) {
		this.mastermindModel = model;
	}

	// Methode relative au mode Challenger qui permet de transferer la proposition
	// du joueur au modele.
	public void setProposition(String proposition) {
		this.mastermindModel.setPropositionModeChallenger(proposition);
	}

	// Methode relative au mode Challenger qui permet de transferer la combinaison
	// secrete de l'ordinateur au modele.
	public void setCombinaisonSecrete(String combinaisonSecrete) {
		this.combinaisonSecrete = combinaisonSecrete;
		control();
	}

	// Methode qui permet de transferer le choix du joueur en fin de partie au
	// modele.
	public void setChoixFinDePartie(String choixFinDePartie) {
		this.mastermindModel.setChoixFinDePartie(choixFinDePartie);
	}

	// Methode qui permet de transferer le mode de jeu au modele.
	public void setMode(String mode) {
		this.mastermindModel.setMode(mode);
	}

	// Methode qui permet de transferer le nombre de cases au modele.
	public void setNbCases(int nbCases) {
		this.mastermindModel.setNbCases(nbCases);
	}

	// Methode qui permet de transferer le nombre de chiffres utilisables au modele.
	public void setNbChiffresAUtiliser(int nbChiffresAUtiliser) {
		this.nbChiffresAUtiliser = nbChiffresAUtiliser;
		this.mastermindModel.setNbChiffresAUtiliser(nbChiffresAUtiliser);
	}

	// Methode qui permet de verifier la bonne saisie combinaison secrete en
	// fonction du nombre de chiffres utilisables
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
