package com.openclassrooms.jeudelogique.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import com.openclassrooms.jeudelogique.observer.Observable;
import com.openclassrooms.jeudelogique.observer.Observer;
import com.openclassrooms.jeudelogique.utilities.FromStringArrayToString;

public class MastermindModel implements Observable {
	private ArrayList<Observer> listObserver;

	// Attributs relatifs au mode challenger
	private String proposition;
	private String combinaisonSecrete;

	// Attributs relatifs au mode defenseur
	private String combinaisonSecreteModeDefenseur;
	private String reponseCorrespondanteModeDefenseur = "";
	private String propositionOrdinateurModeDefenseur = "";
	private LinkedList<String> listPossibilities;

	// Attributs communs
	private String mode;
	private String choixFinDePartie;

	// Constructeur par defaut
	public MastermindModel() {
		listObserver = new ArrayList<>();
	}

	// Methodes relatives au mode challenger
	public void setPropositionModeChallenger(String proposition) {
		this.proposition = proposition;
		this.compare();
		this.notifyObserver();
	}

	public void setCombinaisonSecreteModeChallenger(String combinaisonSecrete) {
		this.combinaisonSecrete = combinaisonSecrete;
	}

	public String compare() {
		int nbChiffreMalPlace = 0;
		int nbChiffreBienPlace = 0;
		boolean[] marque = new boolean[this.combinaisonSecrete.length()];
		/*
		 * cette boucle sert à trouver les éléments bien devinés et correctement placés.
		 * Le tableau 'marque' permet de marquer de tels éléments pour qu'ils ne soient
		 * pas comptés plusieurs fois.
		 */
		for (int i = 0; i < this.combinaisonSecrete.length(); i++) {
			if (this.combinaisonSecrete.charAt(i) == this.proposition.charAt(i)) {
				nbChiffreBienPlace++;
				marque[i] = true;
			} else {
				marque[i] = false;
			}
		}
		// la deuxième boucle suivante sert à identifier les
		// éléments bien devinés mais mal placés.
		for (int i = 0; i < this.combinaisonSecrete.length(); i++) {
			if (this.combinaisonSecrete.charAt(i) != this.proposition.charAt(i)) {
				int j = 0;
				boolean trouveMalPlace = false;
				while ((j < this.combinaisonSecrete.length()) && !trouveMalPlace) {
					if (!marque[j] && (this.combinaisonSecrete.charAt(i) == this.proposition.charAt(j))) {
						nbChiffreMalPlace++;
						trouveMalPlace = true;
					}
					j++;
				}
			}
		}
		int[] reponse = new int[2];
		reponse[0] = nbChiffreBienPlace;
		reponse[1] = nbChiffreMalPlace;

		ArrayList<String> tab = new ArrayList<>();
		for (int i = 0; i < reponse[0]; i++) {
			tab.add("#");
		}
		for (int i = 0; i < reponse[1]; i++) {
			tab.add("O");
		}
		String[] tab2 = new String[tab.size()];
		for (int i = 0; i < tab2.length; i++) {
			tab2[i] = tab.get(i);
		}

		return FromStringArrayToString.convert(tab2);
	}

	// Methodes relatives au mode defenseur
	public void setCombinaisonSecreteModeDefenseur(String combinaisonSecreteModeDefenseur) {
		this.combinaisonSecreteModeDefenseur = combinaisonSecreteModeDefenseur;
		this.initListPossibilities();
		this.reponseCorrespondanteModeDefenseur = "";
		this.propositionOrdinateurModeDefenseur = "";
		this.genererPropositionOrdinateurModeDefenseur();
	}

	public void genererPropositionOrdinateurModeDefenseur() {
		String[] resultatsComparaisons = new String[this.listPossibilities.size()];
		ArrayList<String> listeARejeter = new ArrayList<>();

		if (this.reponseCorrespondanteModeDefenseur.equals("") && this.propositionOrdinateurModeDefenseur.equals("")) {
			this.propositionOrdinateurModeDefenseur = this.listPossibilities
					.get(new Random().nextInt(this.listPossibilities.size()));
			this.reponseCorrespondanteModeDefenseur = genererReponseCorrespondante(
					this.combinaisonSecreteModeDefenseur);
			this.notifyObserver();
		} else {
			for (int i = 0; i < listPossibilities.size(); i++) {
				resultatsComparaisons[i] = genererReponseCorrespondante(this.listPossibilities.get(i));
				if (!resultatsComparaisons[i].equals(this.reponseCorrespondanteModeDefenseur)) {
					listeARejeter.add(this.listPossibilities.get(i));
				}
			}
			for (String str : listeARejeter) {
				this.listPossibilities.remove(str);
			}
			this.propositionOrdinateurModeDefenseur = this.listPossibilities
					.get(new Random().nextInt(this.listPossibilities.size()));
			this.reponseCorrespondanteModeDefenseur = genererReponseCorrespondante(
					this.combinaisonSecreteModeDefenseur);
			this.notifyObserver();
		}
	}

	public String genererReponseCorrespondante(String proposition) {

		int nbChiffreMalPlace = 0;
		int nbChiffreBienPlace = 0;
		boolean[] marque = new boolean[proposition.length()];
		/*
		 * cette boucle sert à trouver les éléments bien devinés et correctement placés.
		 * Le tableau 'marque' permet de marquer de tels éléments pour qu'ils ne soient
		 * pas comptés plusieurs fois.
		 */
		for (int i = 0; i < proposition.length(); i++) {
			if (proposition.charAt(i) == this.propositionOrdinateurModeDefenseur.charAt(i)) {
				nbChiffreBienPlace++;
				marque[i] = true;
			} else {
				marque[i] = false;
			}
		}
		// la deuxième boucle suivante sert à identifier les
		// éléments bien devinés mais mal placés.
		for (int i = 0; i < proposition.length(); i++) {
			if (proposition.charAt(i) != this.propositionOrdinateurModeDefenseur.charAt(i)) {
				int j = 0;
				boolean trouveMalPlace = false;
				while ((j < proposition.length()) && !trouveMalPlace) {
					if (!marque[j] && (proposition.charAt(i) == this.propositionOrdinateurModeDefenseur.charAt(j))) {
						nbChiffreMalPlace++;
						trouveMalPlace = true;
					}
					j++;
				}
			}
		}
		int[] reponse = new int[2];
		reponse[0] = nbChiffreBienPlace;
		reponse[1] = nbChiffreMalPlace;

		ArrayList<String> tab = new ArrayList<>();
		for (int i = 0; i < reponse[0]; i++) {
			tab.add("#");
		}
		for (int i = 0; i < reponse[1]; i++) {
			tab.add("O");
		}
		String[] tab2 = new String[tab.size()];
		for (int i = 0; i < tab2.length; i++) {
			tab2[i] = tab.get(i);
		}

		return FromStringArrayToString.convert(tab2);

	}

	public void initListPossibilities() {
		listPossibilities = new LinkedList<>();
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j <= 9; j++) {
				for (int k = 0; k <= 9; k++) {
					for (int l = 0; l <= 9; l++) {
						listPossibilities
								.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(k) + String.valueOf(l));
					}
				}
			}
		}
	}

	// Methodes communes
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

	public void setMode(String mode) {
		this.mode = mode;
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
				obs.updateModeDefenseurOuDuel(this.propositionOrdinateurModeDefenseur,
						this.reponseCorrespondanteModeDefenseur, this.combinaisonSecreteModeDefenseur);
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
