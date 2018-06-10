package com.openclassrooms.jeudelogique.model;

import java.util.ArrayList;

import com.openclassrooms.jeudelogique.observer.Observable;
import com.openclassrooms.jeudelogique.observer.Observer;
import com.openclassrooms.jeudelogique.utilities.RandomCombination;

public class SearchModel implements Observable {
	private ArrayList<Observer> listObserver;

	// Attributs relatifs au mode challenger
	private String propositionJoueurModeChallenger;
	private String combinaisonSecreteModeChallenger;

	// Attributs relatifs au mode defenseur
	private String combinaisonSecreteModeDefenseur;
	private String reponseCorrespondanteModeDefenseur = "";
	private String propositionOrdinateurModeDefenseur;
	private int[] bornesMin;
	private int[] bornesMax;

	// Attributs relatifs au mode duel
	private String combinaisonSecreteJoueurModeDuel;
	private String combinaisonSecreteOrdinateurModeDuel;
	private String propositionJoueurModeDuel;
	private String propositionOrdinateurModeDuel = "";
	private String reponseCorrespondanteModeDuel = "";

	// Attributs communs
	private String mode;
	private String choixFinDePartie;

	// Constructeur par defaut
	public SearchModel() {
		listObserver = new ArrayList<>();
	}

	// Methodes relatives au mode challenger
	public void setProposition(String proposition) {
		this.propositionJoueurModeChallenger = proposition;
		this.compare(this.combinaisonSecreteModeChallenger, this.propositionJoueurModeChallenger);
		this.notifyObserver();
	}

	public void setCombinaisonSecreteModeChallenger(String combinaisonSecrete) {
		this.combinaisonSecreteModeChallenger = combinaisonSecrete;
	}

	// Methodes relatives au mode defenseur
	public void setCombinaisonSecreteModeDefenseur(String combinaisonSecreteModeDefender) {
		this.combinaisonSecreteModeDefenseur = combinaisonSecreteModeDefender;
		bornesMin = new int[this.combinaisonSecreteModeDefenseur.length()];
		bornesMax = new int[this.combinaisonSecreteModeDefenseur.length()];
		for (int i = 0; i < bornesMin.length; i++) {
			bornesMin[i] = 0;
			bornesMax[i] = 9;
		}
		this.reponseCorrespondanteModeDefenseur = "";
		this.genererPropositionOrdinateurModeDefenseur();
	}

	public void genererPropositionOrdinateurModeDefenseur() {
		int[] tabAnalyse = new int[this.combinaisonSecreteModeDefenseur.length()];
		int[] tabReponse = new int[this.combinaisonSecreteModeDefenseur.length()];
		char[] tabIntermediate = new char[this.combinaisonSecreteModeDefenseur.length()];

		if (this.reponseCorrespondanteModeDefenseur.equals("")) {
			this.propositionOrdinateurModeDefenseur = RandomCombination
					.generateRandomCombination(this.combinaisonSecreteModeDefenseur.length());
			this.reponseCorrespondanteModeDefenseur = this.compare(this.combinaisonSecreteModeDefenseur,
					this.propositionOrdinateurModeDefenseur);
			this.notifyObserver();
		} else {
			for (int i = 0; i < this.combinaisonSecreteModeDefenseur.length(); i++) {
				tabAnalyse[i] = Integer.valueOf(String.valueOf(this.propositionOrdinateurModeDefenseur.charAt(i)));
				if (this.reponseCorrespondanteModeDefenseur.charAt(i) == '=') {
					tabReponse[i] = tabAnalyse[i];
				} else if (this.reponseCorrespondanteModeDefenseur.charAt(i) == '-') {
					bornesMax[i] = tabAnalyse[i] - 1;
					tabReponse[i] = (int) ((bornesMax[i] + bornesMin[i]) / 2);
				} else if (this.reponseCorrespondanteModeDefenseur.charAt(i) == '+') {
					bornesMin[i] = tabAnalyse[i] + 1;
					if (((bornesMin[i] + bornesMax[i]) / 2) % 2 == 1) {
						tabReponse[i] = ((int) ((bornesMin[i] + bornesMax[i]) / 2)) + 1;
					} else {
						tabReponse[i] = (int) ((bornesMin[i] + bornesMax[i]) / 2);
					}
				}
				tabIntermediate[i] = String.valueOf(tabReponse[i]).charAt(0);
			}
			this.propositionOrdinateurModeDefenseur = String.valueOf(tabIntermediate);
			this.reponseCorrespondanteModeDefenseur = this.compare(this.combinaisonSecreteModeDefenseur,
					this.propositionOrdinateurModeDefenseur);
			this.notifyObserver();
		}
	}

	// Methodes relatives au mode duel
	public void setCombinaisonSecreteJoueurModeDuel(String combinaisonSecreteJoueurModeDuel) {
		this.combinaisonSecreteJoueurModeDuel = combinaisonSecreteJoueurModeDuel;
		bornesMin = new int[this.combinaisonSecreteJoueurModeDuel.length()];
		bornesMax = new int[this.combinaisonSecreteJoueurModeDuel.length()];
		for (int i = 0; i < bornesMin.length; i++) {
			bornesMin[i] = 0;
			bornesMax[i] = 9;
		}
		this.reponseCorrespondanteModeDuel = "";
	}

	public void setCombinaisonSecreteOrdinateurModeDuel(String combinaisonSecreteOrdinateurModeDuel) {
		this.combinaisonSecreteOrdinateurModeDuel = combinaisonSecreteOrdinateurModeDuel;
	}

	public void setPropositionJoueurModeDuel(String propositionJoueurModeDuel) {
		this.propositionJoueurModeDuel = propositionJoueurModeDuel;
		this.compare(this.combinaisonSecreteOrdinateurModeDuel, this.propositionJoueurModeDuel);
		this.genererPropositionOrdinateurModeDuel();
	}

	public void genererPropositionOrdinateurModeDuel() {
		int[] tabAnalyse = new int[this.combinaisonSecreteOrdinateurModeDuel.length()];
		int[] tabReponse = new int[this.combinaisonSecreteOrdinateurModeDuel.length()];
		char[] tabIntermediate = new char[this.combinaisonSecreteOrdinateurModeDuel.length()];

		if (this.reponseCorrespondanteModeDuel.equals("")) {
			this.propositionOrdinateurModeDuel = RandomCombination
					.generateRandomCombination(this.combinaisonSecreteOrdinateurModeDuel.length());
			this.reponseCorrespondanteModeDuel = this.compare(this.combinaisonSecreteJoueurModeDuel,
					this.propositionOrdinateurModeDuel);
		} else {
			for (int i = 0; i < this.combinaisonSecreteOrdinateurModeDuel.length(); i++) {
				tabAnalyse[i] = Integer.valueOf(String.valueOf(this.propositionOrdinateurModeDuel.charAt(i)));
				if (this.reponseCorrespondanteModeDuel.charAt(i) == '=') {
					tabReponse[i] = tabAnalyse[i];
				} else if (this.reponseCorrespondanteModeDuel.charAt(i) == '-') {
					bornesMax[i] = tabAnalyse[i] - 1;
					tabReponse[i] = (int) ((bornesMax[i] + bornesMin[i]) / 2);
				} else if (this.reponseCorrespondanteModeDuel.charAt(i) == '+') {
					bornesMin[i] = tabAnalyse[i] + 1;
					if (((bornesMin[i] + bornesMax[i]) / 2) % 2 == 1) {
						tabReponse[i] = ((int) ((bornesMin[i] + bornesMax[i]) / 2)) + 1;
					} else {
						tabReponse[i] = (int) ((bornesMin[i] + bornesMax[i]) / 2);
					}
				}
				tabIntermediate[i] = Character.forDigit(tabReponse[i], 10);
			}
			this.propositionOrdinateurModeDuel = String.valueOf(tabIntermediate);
			this.reponseCorrespondanteModeDuel = this.compare(this.combinaisonSecreteJoueurModeDuel,
					this.propositionOrdinateurModeDuel);
		}
		this.notifyObserver();
	}

	// Methodes communes
	public String compare(String proposition1, String proposition2) {
		char[] tab = new char[proposition1.length()];
		for (int i = 0; i < proposition1.length(); i++) {
			if (proposition1.charAt(i) == proposition2.charAt(i))
				tab[i] = '=';
			if (proposition1.charAt(i) < proposition2.charAt(i))
				tab[i] = '-';
			if (proposition1.charAt(i) > proposition2.charAt(i))
				tab[i] = '+';
		}
		return String.valueOf(tab);
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public void setChoixFinDePartie(String choixFinDePartie) {
		this.choixFinDePartie = choixFinDePartie;
		if (this.choixFinDePartie.equals("Quitter"))
			this.exitApplicationObserver();
		else if (this.choixFinDePartie.equals("Revenir au menu"))
			this.accueilObserver();
		else if (this.choixFinDePartie.equals("Rejouer")) {
			this.restartObserver();
		}
	}

	// Methodes a redefinir
	@Override
	public void addObserver(Observer o) {
		this.listObserver.add(o);
	}

	@Override
	public void deleteObserver() {
		this.listObserver = new ArrayList<>();
	}

	@Override
	public void notifyObserver() {
		if (mode.equals("CHALLENGER")) {
			for (Observer obs : listObserver) {
				obs.update(this.propositionJoueurModeChallenger,
						this.compare(this.combinaisonSecreteModeChallenger, this.propositionJoueurModeChallenger));
			}
		}
		if (mode.equals("DEFENSEUR")) {
			for (Observer obs : listObserver) {
				obs.update(this.propositionOrdinateurModeDefenseur, this.reponseCorrespondanteModeDefenseur);
			}
		}
		if (mode.equals("DUEL")) {
			for (Observer obs : listObserver) {
				obs.updateModeDuel(this.propositionOrdinateurModeDuel, this.reponseCorrespondanteModeDuel,
						this.compare(this.combinaisonSecreteOrdinateurModeDuel, this.propositionJoueurModeDuel));
			}
		}
	}

	@Override
	public void restartObserver() {
		for (Observer obs : listObserver) {
			obs.restart();
		}
	}

	@Override
	public void accueilObserver() {
		for (Observer obs : listObserver) {
			obs.accueil();
		}
	}

	@Override
	public void exitApplicationObserver() {
		for (Observer obs : listObserver) {
			obs.exitApplication();
		}
	}

}
