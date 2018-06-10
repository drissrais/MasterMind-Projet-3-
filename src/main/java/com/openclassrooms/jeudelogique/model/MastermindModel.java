package com.openclassrooms.jeudelogique.model;

import java.util.ArrayList;
import java.util.Random;

import com.openclassrooms.jeudelogique.observer.Observable;
import com.openclassrooms.jeudelogique.observer.Observer;
import com.openclassrooms.jeudelogique.utilities.FromStringArrayToString;

public class MastermindModel implements Observable {
	private ArrayList<Observer> listObserver;

	// Attributs relatifs au mode challenger
	private String propositionJoueurModeChallenger;
	private String combinaisonSecreteModeChallenger;

	// Attributs relatifs au mode defenseur
	private String combinaisonSecreteModeDefenseur;
	private String reponseCorrespondanteModeDefenseur = "";
	private String propositionOrdinateurModeDefenseur = "";

	// Attributs relatifs au mode duel
	private String combinaisonSecreteJoueurModeDuel;
	private String combinaisonSecreteOrdinateurModeDuel;
	private String propositionJoueurModeDuel;
	private String propositionOrdinateurModeDuel = "";
	private String reponseCorrespondanteModeDuel = "";

	// Attributs communs
	private ArrayList<String> listPossibilities;
	private int nbCases = 4;
	private int nbChiffresAUtiliser = 10;
	private String mode;
	private String choixFinDePartie;

	// Constructeur par defaut
	public MastermindModel() {
		listObserver = new ArrayList<>();
	}

	// Methodes relatives au mode challenger
	public void setPropositionModeChallenger(String proposition) {
		this.propositionJoueurModeChallenger = proposition;
		this.compare(this.combinaisonSecreteModeChallenger, this.propositionJoueurModeChallenger);
		this.notifyObserver();
	}

	public void setCombinaisonSecreteModeChallenger(String combinaisonSecrete) {
		this.combinaisonSecreteModeChallenger = combinaisonSecrete;
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
			this.propositionOrdinateurModeDefenseur = String.valueOf(this.listPossibilities.get(new Random().nextInt(this.listPossibilities.size())));
			this.reponseCorrespondanteModeDefenseur = this.compare(this.combinaisonSecreteModeDefenseur,
					this.propositionOrdinateurModeDefenseur);
			this.notifyObserver();
		} else {
			for (int i = 0; i < listPossibilities.size(); i++) {
				resultatsComparaisons[i] = this.compare(String.valueOf(this.listPossibilities.get(i)),
						this.propositionOrdinateurModeDefenseur);
				if (!resultatsComparaisons[i].equals(this.reponseCorrespondanteModeDefenseur)) {
					listeARejeter.add(this.listPossibilities.get(i));
				}
			}
			for (String str : listeARejeter) {
				this.listPossibilities.remove(str);
			}
			this.propositionOrdinateurModeDefenseur = String.valueOf(this.listPossibilities.get(new Random().nextInt(this.listPossibilities.size())));
			this.reponseCorrespondanteModeDefenseur = this.compare(this.combinaisonSecreteModeDefenseur,
					this.propositionOrdinateurModeDefenseur);
			this.notifyObserver();
		}
	}

	// Methodes relatives au mode duel
	public void setCombinaisonSecreteJoueurModeDuel(String combinaisonSecreteJoueurModeDuel) {
		this.combinaisonSecreteJoueurModeDuel = combinaisonSecreteJoueurModeDuel;
		this.reponseCorrespondanteModeDuel = "";
		this.propositionOrdinateurModeDuel = "";
	}

	public void setCombinaisonSecreteOrdinateurModeDuel(String combinaisonSecreteOrdinateurModeDuel) {
		this.combinaisonSecreteOrdinateurModeDuel = combinaisonSecreteOrdinateurModeDuel;
		this.initListPossibilities();
	}

	public void setPropositionJoueurModeDuel(String propositionJoueurModeDuel) {
		this.propositionJoueurModeDuel = propositionJoueurModeDuel;
		this.compare(this.combinaisonSecreteOrdinateurModeDuel, this.propositionJoueurModeDuel);
		this.genererPropositionOrdinateurModeDuel();
		this.notifyObserver();
	}

	public void genererPropositionOrdinateurModeDuel() {
		String[] resultatsComparaisons = new String[this.listPossibilities.size()];
		ArrayList<String> listeARejeter = new ArrayList<>();

		if (this.reponseCorrespondanteModeDuel.equals("") && this.propositionOrdinateurModeDuel.equals("")) {
			this.propositionOrdinateurModeDuel = String
					.valueOf(this.listPossibilities.get(new Random().nextInt(this.listPossibilities.size())));
			this.reponseCorrespondanteModeDuel = this.compare(this.combinaisonSecreteJoueurModeDuel,
					this.propositionOrdinateurModeDuel);
		} else {
			for (int i = 0; i < listPossibilities.size(); i++) {
				resultatsComparaisons[i] = this.compare(String.valueOf(this.listPossibilities.get(i)),
						this.propositionOrdinateurModeDuel);
				if (!resultatsComparaisons[i].equals(this.reponseCorrespondanteModeDuel)) {
					listeARejeter.add(this.listPossibilities.get(i));
				}
			}
			for (String str : listeARejeter) {
				this.listPossibilities.remove(str);
			}
			this.propositionOrdinateurModeDuel = String
					.valueOf(this.listPossibilities.get(new Random().nextInt(this.listPossibilities.size())));
			this.reponseCorrespondanteModeDuel = this.compare(this.combinaisonSecreteJoueurModeDuel,
					this.propositionOrdinateurModeDuel);
		}
	}

	// Methodes communes aux 3 modes de jeu
	public String compare(String combinaisonSecreteOrdinateur, String propositionJoueur) {
		int nbChiffreMalPlace = 0;
		int nbChiffreBienPlace = 0;
		boolean[] marque = new boolean[this.nbCases];
		/*
		 * cette boucle sert à trouver les éléments bien devinés et correctement placés.
		 * Le tableau 'marque' permet de marquer de tels éléments pour qu'ils ne soient
		 * pas comptés plusieurs fois.
		 */
		for (int i = 0; i < this.nbCases; i++) {
			if (combinaisonSecreteOrdinateur.charAt(i) == propositionJoueur.charAt(i)) {
				nbChiffreBienPlace++;
				marque[i] = true;
			} else {
				marque[i] = false;
			}
		}
		// la deuxième boucle suivante sert à identifier les
		// éléments bien devinés mais mal placés.
		for (int i = 0; i < this.nbCases; i++) {
			if (combinaisonSecreteOrdinateur.charAt(i) != propositionJoueur.charAt(i)) {
				int j = 0;
				boolean trouveMalPlace = false;
				while ((j < this.nbCases) && !trouveMalPlace) {
					if (!marque[j] && (combinaisonSecreteOrdinateur.charAt(i) == propositionJoueur.charAt(j))) {
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

	public void setNbCases(int nbCases) {
		this.nbCases = nbCases;
	}
	
	public void setNbChiffresAUtiliser(int nbChiffresAUtiliser) {
		this.nbChiffresAUtiliser = nbChiffresAUtiliser;
	}

	public void initListPossibilities() {
		listPossibilities = new ArrayList<>();
		if (nbCases == 4) {
			for (byte i = 0; i < nbChiffresAUtiliser; i++) {
				for (byte j = 0; j < nbChiffresAUtiliser; j++) {
					for (byte k = 0; k < nbChiffresAUtiliser; k++) {
						for (byte l = 0; l < nbChiffresAUtiliser; l++) {
							listPossibilities
									.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(k) + String.valueOf(l));
						}
					}
				}
			}
		} else if (nbCases == 5) {
			for (byte i = 0; i < nbChiffresAUtiliser; i++) {
				for (byte j = 0; j < nbChiffresAUtiliser; j++) {
					for (byte k = 0; k < nbChiffresAUtiliser; k++) {
						for (byte l = 0; l < nbChiffresAUtiliser; l++) {
							for (byte m = 0; m < nbChiffresAUtiliser; m++) {
								listPossibilities.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(k)
										+ String.valueOf(l) + String.valueOf(m));
							}
						}

					}
				}
			}
		} else {
			for (byte i = 0; i < nbChiffresAUtiliser; i++) {
				for (byte j = 0; j < nbChiffresAUtiliser; j++) {
					for (byte k = 0; k < nbChiffresAUtiliser; k++) {
						for (byte l = 0; l < nbChiffresAUtiliser; l++) {
							for (byte m = 0; m < nbChiffresAUtiliser; m++) {
								for (byte n = 0; n < nbChiffresAUtiliser; n++) {
									listPossibilities.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(k)
											+ String.valueOf(l) + String.valueOf(m) + String.valueOf(n));
								}
							}
						}
					}
				}
			}
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
		this.listPossibilities = new ArrayList<>();
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
