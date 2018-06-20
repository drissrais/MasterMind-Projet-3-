package com.openclassrooms.jeudelogique.controler;

import com.openclassrooms.jeudelogique.model.SearchModel;

/* Pattern MVC - Classe relative au Controler du jeu RecherchePlusMoins en mode challenger.
 * Dans le cadre de ce programme, le but du Controler est de transferer
 * les donnees saisies par l'utilisateur dans la vue au modele de donnees
 * associe au jeu Mastermind.
 */
public class SearchChallengerControler {
	private SearchModel searchModel;

	public SearchChallengerControler(SearchModel model) {
		this.searchModel = model;
	}

	// Methode relative au mode Challenger qui permet de transferer la proposition
	// du joueur au modele.
	public void setProposition(String proposition) {
		this.searchModel.setProposition(proposition);
	}

	// Methode relative au mode Challenger qui permet de transferer la combinaison
	// secrete de l'ordinateur au modele.
	public void setCombinaisonSecrete(String combinaisonSecrete) {
		this.searchModel.setCombinaisonSecreteModeChallenger(combinaisonSecrete);
	}

	// Methode qui permet de transferer le choix du joueur en fin de partie au
	// modele.
	public void setChoixFinDePartie(String choixFinDePartie) {
		this.searchModel.setChoixFinDePartie(choixFinDePartie);
	}

	// Methode qui permet de transferer le mode de jeu au modele.
	public void setMode(String mode) {
		this.searchModel.setMode(mode);
	}

}
