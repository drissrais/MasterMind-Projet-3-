package com.openclassrooms.jeudelogique.model;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import com.openclassrooms.jeudelogique.observer.Observable;
import com.openclassrooms.jeudelogique.observer.Observer;
import com.openclassrooms.jeudelogique.utilities.FromIntArrayToString;
import com.openclassrooms.jeudelogique.utilities.FromStringArrayToString;
import com.openclassrooms.jeudelogique.utilities.FromStringtoIntArray;
import com.openclassrooms.jeudelogique.utilities.RandomCombination;

public class Model implements Observable {
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	private int nbCoups = 0;
	private String proposition;
	private int nbCases = 4;
	private int[] solution = RandomCombination.generateRandomCombination(nbCases);
	
	private String gameName;
	private String gameMode;

	public void setProposition(String proposition) {
		this.proposition = proposition;
		if (this.gameName.equals("Recherche +/-") && this.gameMode.equals("Challenger")) {
			this.searchShowResponse();
		}
		if (this.gameName.equals("MasterMind") && this.gameMode.equals("Challenger")) {
			this.mastermindShowResult();
		}
	}
	
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}

	public String[] compare() {
		int[] combinaison2 = FromStringtoIntArray.convert(this.proposition);
		String[] tab = new String[nbCases];
		for (int i = 0; i < nbCases; i++) {
			if (solution[i] == combinaison2[i])
				tab[i] = "=";
			if (solution[i] < combinaison2[i])
				tab[i] = "-";
			if (solution[i] > combinaison2[i])
				tab[i] = "+";
		}
		return tab;
	}

	public void searchShowResponse() {
		this.nbCoups++;
		boolean isAllEqual = Arrays.stream(this.compare()).allMatch(e -> e.equals("="));

		if (isAllEqual && nbCoups < 10) {
			this.notifyObserver();
			JOptionPane
					.showMessageDialog(null,
							"Bravo, vous avez trouvé la bonne combinaison " + FromIntArrayToString.convert(solution)
									+ " en " + +this.getNombreCoups() + " coups.",
							"Résultat", JOptionPane.INFORMATION_MESSAGE);
			String[] choix = { "Rejouer", "Revenir au menu", "Quitter" };
			int rang = JOptionPane.showOptionDialog(null, "Voulez-vous rejouer?", "Rejouer",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);
			switch (choix[rang]) {
			case "Rejouer":
				solution = RandomCombination.generateRandomCombination(nbCases);
				nbCoups = 0;
				nbCases = 4;
				this.restartObserver();
				break;
			case "Revenir au menu":
				this.accueilObserver();
				break;
			case "Quitter":
				System.exit(1);
				break;
			default:
				break;
			}
		}
		if (!isAllEqual && nbCoups < 10) {
			notifyObserver();
		}
		if (!isAllEqual && nbCoups >= 10) {
			JOptionPane.showMessageDialog(null, "Désolé, vous avez perdu!\n" + "La bonne combinaison était "
					+ FromIntArrayToString.convert(solution) + "\nRetentez votre chance !",
					"Résultat", JOptionPane.INFORMATION_MESSAGE);
			String[] choix = { "Rejouer", "Revenir au menu", "Quitter" };
			int rang = JOptionPane.showOptionDialog(null, "Voulez-vous rejouer?", "Rejouer",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);
			switch (choix[rang]) {
			case "Rejouer":
				solution = RandomCombination.generateRandomCombination(nbCases);
				nbCoups = 0;
				nbCases = 4;
				this.restartObserver();
				break;
			case "Revenir au menu":
				this.accueilObserver();
				break;
			case "Quitter":
				System.exit(1);
				break;
			default:
				break;
			}
		}
	}

	public int getNombreCoups() {
		return nbCoups;
	}

	public String[] mastermindCompare() {
		int[] combinaison2 = FromStringtoIntArray.convert(this.proposition);
		int nbChiffreMalPlace = 0;
		int nbChiffreBienPlace = 0;
		boolean[] marque = new boolean[this.nbCases];
		/*
		 * cette boucle sert à trouver les éléments bien devinés et correctement placés.
		 * Le tableau 'marque' permet de marquer de tels éléments pour qu'ils ne soient
		 * pas comptés plusieurs fois.
		 */
		for (int i = 0; i < nbCases; i++) {
			if (solution[i] == combinaison2[i]) {
				nbChiffreBienPlace++;
				marque[i] = true;
			} else {
				marque[i] = false;
			}
		}
		// la deuxième boucle suivante sert à identifier les
		// éléments bien devinés mais mal placés.
		for (int i = 0; i < nbCases; i++) {
			if (solution[i] != combinaison2[i]) {
				int j = 0;
				boolean trouveMalPlace = false;
				while ((j < nbCases) && !trouveMalPlace) {
					if (!marque[j] && (solution[i] == combinaison2[j])) {
						nbChiffreMalPlace++;
//						marque[j] = true;
						trouveMalPlace = true;
					}
					j++;
				}
			}
		}
		int[] reponse = new int[2];
		reponse[0] = nbChiffreBienPlace;
		reponse[1] = nbChiffreMalPlace;
		
		ArrayList<String> tabList = new ArrayList<>();
		for (int i = 0; i < reponse[0]; i++) {
			tabList.add("#");
		}
		for (int i = 0; i < reponse[1]; i++) {
			tabList.add("O");
		}
		String[] tab = new String[tabList.size()];
		for (int i = 0; i < tab.length; i++) {
			tab[i] = tabList.get(i);
		}
		return tab;
	}

	public void mastermindShowResult() {
		this.nbCoups++;
		boolean isAllEqual = Arrays.stream(this.mastermindCompare()).allMatch(e -> e.equals("#"));
		if (isAllEqual && this.mastermindCompare().length == this.nbCases && nbCoups < 10) {
			this.notifyObserver();
			JOptionPane
					.showMessageDialog(null,
							"Bravo, vous avez trouvé la bonne combinaison " + FromIntArrayToString.convert(solution)
									+ " en " + +this.getNombreCoups() + " coups.",
							"Résultat", JOptionPane.INFORMATION_MESSAGE);
			String[] choix = { "Rejouer", "Revenir au menu", "Quitter" };
			int rang = JOptionPane.showOptionDialog(null, "Voulez-vous rejouer?", "Rejouer",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);
			switch (choix[rang]) {
			case "Rejouer":
				solution = RandomCombination.generateRandomCombination(nbCases);
				nbCoups = 0;
				nbCases = 4;
				this.restartObserver();
				break;
			case "Revenir au menu":
				nbCoups = 0;
				this.accueilObserver();
				break;
			case "Quitter":
				System.exit(1);
				break;
			default:
				break;
			}
		}
		if (this.mastermindCompare().length != this.nbCases && this.nbCoups < 10) {
			this.notifyObserver();
		}
		if (this.nbCoups >= 10) {
			JOptionPane.showMessageDialog(null,
					"Désolé, vous avez perdu!\n" + "La bonne combinaison était "
							+ FromIntArrayToString.convert(solution) + "\nRetentez votre chance !",
					"Résultat", JOptionPane.INFORMATION_MESSAGE);
			String[] choix = { "Rejouer", "Revenir au menu", "Quitter" };
			int rang = JOptionPane.showOptionDialog(null, "Voulez-vous rejouer?", "Rejouer",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);
			switch (choix[rang]) {
			case "Rejouer":
				solution = RandomCombination.generateRandomCombination(nbCases);
				nbCoups = 0;
				nbCases = 4;
				this.restartObserver();
				break;
			case "Revenir au menu":
				this.accueilObserver();
				break;
			case "Quitter":
				System.exit(1);
				break;
			default:
				break;
			}
		}
	}

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
		if (this.gameName.equals("Recherche +/-") && this.gameMode.equals("Challenger")) {
			for (Observer obs : listObserver) {
				obs.update(this.nbCases, this.proposition + " : " + FromStringArrayToString.convert(this.compare()),
						10 - this.getNombreCoups());
			}
		}
		if (this.gameName.equals("MasterMind") && this.gameMode.equals("Challenger")) {
			for (Observer obs : listObserver) {
				obs.update(this.nbCases, this.proposition + " : " + FromStringArrayToString.convert(this.mastermindCompare()),
						10 - this.getNombreCoups());
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

}
