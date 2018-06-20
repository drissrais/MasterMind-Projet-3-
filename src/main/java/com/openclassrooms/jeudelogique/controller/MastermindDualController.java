package com.openclassrooms.jeudelogique.controller;

import com.openclassrooms.jeudelogique.model.MastermindModel;

/* Pattern MVC - Classe relative au Controler du jeu Mastermind en mode Duel.
 * Dans le cadre de ce programme, le but du Controler est de transferer
 * les donnees saisies par l'utilisateur dans la vue au modele de donnees
 * associe au jeu Mastermind.
 */
public class MastermindDualController {
	private String combinaisonSecreteJoueurModeDuel;
	private int nbChiffresAUtiliser;
	private MastermindModel model;

	public MastermindDualController(MastermindModel model) {
		this.model = model;
	}

	// Methode relative au mode Duel qui permet de transferer la combinaison secrete
	// du joueur au modele.
	public void setCombinaisonSecreteJoueurModeDuel(String combinaisonSecreteJoueur) {
		this.combinaisonSecreteJoueurModeDuel = combinaisonSecreteJoueur;
		control();
	}

	// Methode relative au mode Duel qui permet de transferer la combinaison secrete
	// de l'ordinateur au modele.
	public void setCombinaisonSecreteOrdinateurModeDuel(String combinaisonSecreteOrdinateur) {
		this.model.setCombinaisonSecreteOrdinateurModeDuel(combinaisonSecreteOrdinateur);
	}

	// Methode relative au mode Duel qui permet de transferer la proposition du
	// joueur au modele.
	public void setPropositionJoueurModeDuel(String propositionJoueurModeDuel) {
		this.model.setPropositionJoueurModeDuel(propositionJoueurModeDuel);
	}

	// Methode qui permet de transferer le mode de jeu au modele.
	public void setMode(String mode) {
		this.model.setMode(mode);
	}

	// Methode qui permet de transferer le nombre de chiffres utilisables au modele.
	public void setNbChiffresAUtiliser(int nbChiffresAUtiliser) {
		this.nbChiffresAUtiliser = nbChiffresAUtiliser;
		this.model.setNbChiffresAUtiliser(nbChiffresAUtiliser);
	}

	// Methode qui permet de transferer le choix du joueur en fin de partie au
	// modele.
	public void setChoixFinDePartie(String choixFinDePartie) {
		this.model.setChoixFinDePartie(choixFinDePartie);
	}

	// Methode qui permet de transferer le nombre de cases au modele.
	public void setNbCases(int nbCases) {
		this.model.setNbCases(nbCases);
	}

	// Methode qui permet de verifier la bonne saisie combinaison secrete en
	// fonction du nombre de chiffres utilisables
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
