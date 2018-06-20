package com.openclassrooms.jeudelogique.controler;

import com.openclassrooms.jeudelogique.model.SearchModel;

/* Pattern MVC - Classe relative au Controler du jeu RecherchePlusMoins en mode defenseur.
 * Dans le cadre de ce programme, le but du Controler est de transferer
 * les donnees saisies par l'utilisateur dans la vue au modele de donnees
 * associe au jeu Mastermind.
 */
public class SearchDefenderControler {

	private SearchModel searchModel;

	public SearchDefenderControler(SearchModel model) {
		this.searchModel = model;
	}

	// Methode relative au mode Defenseur qui permet de transferer la combinaison
	// secrete de l'ordinateur au modele.
	public void setCombinaisonSecreteModeDefenseur(String combinaisonSecrete) {
		this.searchModel.setCombinaisonSecreteModeDefenseur(combinaisonSecrete);
	}

	// Methode qui permet de transferer le mode de jeu au modele.
	public void setMode(String mode) {
		this.searchModel.setMode(mode);
	}

	// Methode qui permet de transferer le choix du joueur en fin de partie au
	// modele.
	public void setChoixFinDePartie(String choixFinDePartie) {
		this.searchModel.setChoixFinDePartie(choixFinDePartie);
	}

	// Methode qui permet de transferer l'appel de la vue au modele pour generer une
	// propoition de l'ordinateur
	public void genererPropositionOrdinateurModeDefenseur() {
		this.searchModel.genererPropositionOrdinateurModeDefenseur();
	}

}
