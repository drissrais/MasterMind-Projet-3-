package com.openclassrooms.jeudelogique.controller;

import com.openclassrooms.jeudelogique.model.SearchModel;

/* Pattern MVC - Classe relative au Controler du jeu RecherchePlusMoins en mode Duel.
 * Dans le cadre de ce programme, le but du Controler est de transferer
 * les donnees saisies par l'utilisateur dans la vue au modele de donnees
 * associe au jeu Mastermind.
 */
public class SearchDualController {
	private SearchModel model;

	public SearchDualController(SearchModel model) {
		this.model = model;
	}

	// Methode relative au mode Duel qui permet de transferer la combinaison secrete
	// du joueur au modele.
	public void setCombinaisonSecreteJoueurModeDuel(String combinaisonSecreteJoueur) {
		this.model.setCombinaisonSecreteJoueurModeDuel(combinaisonSecreteJoueur);
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

	// Methode qui permet de transferer le choix du joueur en fin de partie au
	// modele.
	public void setChoixFinDePartie(String choixFinDePartie) {
		this.model.setChoixFinDePartie(choixFinDePartie);
	}

}
