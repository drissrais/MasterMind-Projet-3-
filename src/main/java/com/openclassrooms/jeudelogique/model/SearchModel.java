package com.openclassrooms.jeudelogique.model;

import java.util.ArrayList;

import com.openclassrooms.jeudelogique.observer.Observable;
import com.openclassrooms.jeudelogique.observer.Observer;
import com.openclassrooms.jeudelogique.utilities.RandomCombination;

public class SearchModel implements Observable {
	private ArrayList<Observer> listObserver;

	// Attributs relatifs au mode challenger
	private String proposition;
	private String combinaisonSecreteModeChallenger;

	// Attributs relatifs au mode defenseur
	private String combinaisonSecreteModeDefenseur;
	private String reponseCorrespondanteModeDefenseur = "";
	private String propositionOrdinateurModeDefenseur;
	private int[] bornesMin;
	private int[] bornesMax;
	
	// Attributs communs
	private String mode;
	private String choixFinDePartie;

	// Constructeur par defaut
	public SearchModel() {
		listObserver = new ArrayList<>();
	}

	// Methodes relatives au mode challenger
	public void setProposition(String proposition) {
		this.proposition = proposition;
		this.compare();
		this.notifyObserver();
	}

	public void setCombinaisonSecreteModeChallenger(String combinaisonSecrete) {
		this.combinaisonSecreteModeChallenger = combinaisonSecrete;
	}

	public String compare() {
		char[] tab = new char[this.combinaisonSecreteModeChallenger.length()];
		for (int i = 0; i < this.combinaisonSecreteModeChallenger.length(); i++) {
			if (combinaisonSecreteModeChallenger.charAt(i) == proposition.charAt(i))
				tab[i] = '=';
			if (combinaisonSecreteModeChallenger.charAt(i) < proposition.charAt(i))
				tab[i] = '-';
			if (combinaisonSecreteModeChallenger.charAt(i) > proposition.charAt(i))
				tab[i] = '+';
		}
		return String.valueOf(tab);
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
			this.reponseCorrespondanteModeDefenseur = genererReponseCorrespondante();
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
				tabIntermediate[i] = Character.forDigit(tabReponse[i], 10);
			}
			this.propositionOrdinateurModeDefenseur = String.valueOf(tabIntermediate);
			this.reponseCorrespondanteModeDefenseur = genererReponseCorrespondante();
			this.notifyObserver();
		}
	}

	private String genererReponseCorrespondante() {
		int[] tabAnalyse = new int[combinaisonSecreteModeDefenseur.length()];
		char[] charArray = new char[combinaisonSecreteModeDefenseur.length()];
		for (int i = 0; i < combinaisonSecreteModeDefenseur.length(); i++) {
			tabAnalyse[i] = Integer.valueOf(String.valueOf(this.propositionOrdinateurModeDefenseur.charAt(i)));
			if (tabAnalyse[i] == Integer.valueOf(String.valueOf(this.combinaisonSecreteModeDefenseur.charAt(i)))) {
				charArray[i] = '=';
			} else if (tabAnalyse[i] < Integer
					.valueOf(String.valueOf(this.combinaisonSecreteModeDefenseur.charAt(i)))) {
				charArray[i] = '+';
			} else if (tabAnalyse[i] > Integer
					.valueOf(String.valueOf(this.combinaisonSecreteModeDefenseur.charAt(i)))) {
				charArray[i] = '-';
			}
		}
		return String.valueOf(charArray);
	}

	// Methodes communes
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
				obs.update(this.proposition, this.compare());
			}
		}
		if (mode.equals("DEFENSEUR")) {
			for (Observer obs : listObserver) {
				obs.updateModeDefenseur(this.propositionOrdinateurModeDefenseur, this.reponseCorrespondanteModeDefenseur, this.combinaisonSecreteModeDefenseur);
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
