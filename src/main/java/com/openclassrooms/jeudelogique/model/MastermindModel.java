package com.openclassrooms.jeudelogique.model;

import java.util.ArrayList;
import java.util.Random;

import com.openclassrooms.jeudelogique.observer.Observable;
import com.openclassrooms.jeudelogique.observer.Observer;
import com.openclassrooms.jeudelogique.utilities.FromStringArrayToString;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MastermindModel implements Observable {
	private static final Logger LOGGER = LogManager.getLogger();
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

	/*
	 * Methode relative au mode Challenger qui permet de recuperer la proposition du
	 * joueur. Suite a la proposition du joueur, l'ordinateur devra repondre.
	 */
	public void setPropositionModeChallenger(String proposition) {
		this.propositionJoueurModeChallenger = proposition;
		LOGGER.debug("Mode Challenger - proposition Joueur :" + this.propositionJoueurModeChallenger);
		this.compare(this.combinaisonSecreteModeChallenger, this.propositionJoueurModeChallenger);
		this.notifyObserver();
	}

	// Methode relative au mode Challenger qui permet de recuperer la combinaison
	// secrete de l'ordinateur.
	public void setCombinaisonSecreteModeChallenger(String combinaisonSecrete) {
		this.combinaisonSecreteModeChallenger = combinaisonSecrete;
	}

	// Methodes relatives au mode defenseur

	/*
	 * Methode relative au mode Defenseur qui permet de recuperer la combinaison
	 * secrete du joueur. Apres recuperation de la combinaison secrete du joueur,
	 * l'ordinateur devra faire une proposition.
	 */
	public void setCombinaisonSecreteModeDefenseur(String combinaisonSecreteModeDefenseur) {
		this.combinaisonSecreteModeDefenseur = combinaisonSecreteModeDefenseur;
		LOGGER.debug(
				"Mode Défenseur - Combinaison secrète Joueur Mode Défenseur :" + this.combinaisonSecreteModeDefenseur);
		this.initListPossibilities();
		LOGGER.debug("Mode Défenseur - Taille de la liste :" + listPossibilities.size());
		this.reponseCorrespondanteModeDefenseur = "";
		this.propositionOrdinateurModeDefenseur = "";
		this.genererPropositionOrdinateurModeDefenseur();
	}

	/*
	 * Methode relative au mode Defenseur qui permet de generer une proposition de
	 * l'ordinateur a la base de la reponse a la proposition precedante (Au 1er
	 * coup, proposition aleatoire). Par la suite, nous adoptons un raisonnement
	 * KNUTH
	 */
	public void genererPropositionOrdinateurModeDefenseur() {
		String[] resultatsComparaisons = new String[this.listPossibilities.size()];
		ArrayList<String> listeARejeter = new ArrayList<>();

		if (this.reponseCorrespondanteModeDefenseur.equals("") && this.propositionOrdinateurModeDefenseur.equals("")) {
			this.propositionOrdinateurModeDefenseur = String
					.valueOf(this.listPossibilities.get(new Random().nextInt(this.listPossibilities.size())));
			LOGGER.debug("Mode Défenseur - proposition Ordinateur Mode Défenseur :"
					+ this.propositionOrdinateurModeDefenseur);
			this.reponseCorrespondanteModeDefenseur = this.compare(this.combinaisonSecreteModeDefenseur,
					this.propositionOrdinateurModeDefenseur);
			LOGGER.debug("Mode Défenseur - réponse correspondante à la proposition de l'ordinateur Mode Défenseur :"
					+ this.reponseCorrespondanteModeDefenseur);
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
			this.propositionOrdinateurModeDefenseur = String
					.valueOf(this.listPossibilities.get(new Random().nextInt(this.listPossibilities.size())));
			LOGGER.debug("Mode Défenseur - proposition Ordinateur Mode Défenseur :"
					+ this.propositionOrdinateurModeDefenseur);
			this.reponseCorrespondanteModeDefenseur = this.compare(this.combinaisonSecreteModeDefenseur,
					this.propositionOrdinateurModeDefenseur);
			LOGGER.debug("Mode Défenseur - réponse correspondante à la proposition de l'ordinateur Mode Défenseur :"
					+ this.reponseCorrespondanteModeDefenseur);
			this.notifyObserver();
		}
	}

	// Methodes relatives au mode duel

	/*
	 * Methode relative au mode Duel qui permet de recuperer la combinaison secrete
	 * du joueur.
	 */
	public void setCombinaisonSecreteJoueurModeDuel(String combinaisonSecreteJoueurModeDuel) {
		this.combinaisonSecreteJoueurModeDuel = combinaisonSecreteJoueurModeDuel;
		LOGGER.debug(
				"Mode Duel - Combinaison Secrète Joueur Modèle de données :" + this.combinaisonSecreteJoueurModeDuel);
		this.reponseCorrespondanteModeDuel = "";
		this.propositionOrdinateurModeDuel = "";
	}

	/*
	 * Methode relative au mode Duel qui permet de recuperer la combinaison secrete
	 * de l'ordinateur.
	 */
	public void setCombinaisonSecreteOrdinateurModeDuel(String combinaisonSecreteOrdinateurModeDuel) {
		this.combinaisonSecreteOrdinateurModeDuel = combinaisonSecreteOrdinateurModeDuel;
		this.initListPossibilities();
		LOGGER.debug("Mode Duel - Taille de la liste des possibilités :" + listPossibilities.size());
	}

	/*
	 * Methode relative au mode duel qui permet de recuperer la proposition du
	 * joueur et la comparer a la combinaison secrete de l'ordinateur.
	 */
	public void setPropositionJoueurModeDuel(String propositionJoueurModeDuel) {
		this.propositionJoueurModeDuel = propositionJoueurModeDuel;
		LOGGER.debug("Mode Duel - Proposition Joueur Modèle de données :" + this.propositionJoueurModeDuel);
		this.compare(this.combinaisonSecreteOrdinateurModeDuel, this.propositionJoueurModeDuel);
		this.genererPropositionOrdinateurModeDuel();
		this.notifyObserver();
	}

	/*
	 * Methode relative au mode Duel qui permet de generer une proposition de
	 * l'ordinateur a la base de la reponse a la proposition precedante (Au 1er
	 * coup, proposition aleatoire). Par la suite, nous adoptons un raisonnement
	 * KNUTH
	 */
	public void genererPropositionOrdinateurModeDuel() {
		String[] resultatsComparaisons = new String[this.listPossibilities.size()];
		ArrayList<String> listeARejeter = new ArrayList<>();

		if (this.reponseCorrespondanteModeDuel.equals("") && this.propositionOrdinateurModeDuel.equals("")) {
			this.propositionOrdinateurModeDuel = String
					.valueOf(this.listPossibilities.get(new Random().nextInt(this.listPossibilities.size())));
			LOGGER.debug("Mode Duel - proposition Ordinateur Mode Duel :" + this.propositionOrdinateurModeDefenseur);
			this.reponseCorrespondanteModeDuel = this.compare(this.combinaisonSecreteJoueurModeDuel,
					this.propositionOrdinateurModeDuel);
			LOGGER.debug("Mode Duel - réponse correspondante à la proposition de l'ordinateur Mode Défenseur :"
					+ this.reponseCorrespondanteModeDefenseur);
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
			LOGGER.debug("Mode Duel - proposition Ordinateur Mode Duel :" + this.propositionOrdinateurModeDefenseur);
			this.reponseCorrespondanteModeDuel = this.compare(this.combinaisonSecreteJoueurModeDuel,
					this.propositionOrdinateurModeDuel);
			LOGGER.debug("Mode Duel - réponse correspondante à la proposition de l'ordinateur Mode Défenseur :"
					+ this.reponseCorrespondanteModeDefenseur);
		}
	}

	// Methodes communes aux 3 modes de jeu

	/*
	 * Methode relative aux trois modes qui permet d'analyser deux chaines de
	 * caracteres pour en generer une reponse sous forme d'indices # et/ou O
	 */
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

	/*
	 * Methode commune a tous les modes de jeu qui permet de recuperer le choix du
	 * joueur en fin de partie et en fonction de cela, faire appel a la methode
	 * adequate correspondant au choix du joueur.
	 */
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

	// Mutateur commune a tous les modes de jeu qui permet de modifier le mode de
	// jeu.
	public void setMode(String mode) {
		this.mode = mode;
	}

	// Mutateur commune a tous les modes de jeu qui permet de modifier le nombre
	// de cases.
	public void setNbCases(int nbCases) {
		this.nbCases = nbCases;
	}

	// Mutateur commune a tous les modes de jeu qui permet de modifier le nombre
	// de chiffres utilisables.
	public void setNbChiffresAUtiliser(int nbChiffresAUtiliser) {
		this.nbChiffresAUtiliser = nbChiffresAUtiliser;
	}

	/*
	 * Methode qui permet de generer la liste de toutes les combinaisons possibles
	 * afin de s'en servir dans l'algorithme de knuth
	 */
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

	// Methodes du pattern observer a redefinir
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
