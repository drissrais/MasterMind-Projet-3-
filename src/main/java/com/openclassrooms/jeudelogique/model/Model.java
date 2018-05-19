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

	public void setProposition(String proposition) {
		this.proposition = proposition;
		showResponse();
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

	public void showResponse() {
		this.nbCoups++;
		boolean isAllEqual = Arrays.stream(compare()).allMatch(e -> e.equals("="));

		if (isAllEqual && nbCoups <= 10) {
			this.notifyObserver();
			JOptionPane
					.showMessageDialog(null,
							"Bravo, vous avez trouvé la bonne combinaison " + FromIntArrayToString.convert(solution)
									+ " en " + +this.getNombreCoups() + " coups.",
							"Résultat", JOptionPane.INFORMATION_MESSAGE);
			String[] choix = { "Rejouer", "Revenir au menu", "Quitter" };
			JOptionPane jop = new JOptionPane();
			int rang = jop.showOptionDialog(null, "Voulez-vous rejouer?", "Rejouer", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);
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
		if (!isAllEqual && nbCoups <= 10) {
			notifyObserver();
		}
		if (!isAllEqual && nbCoups > 10) {
			JOptionPane.showMessageDialog(null, "Désolé, vous avez perdu!\n" + "Retentez votre chance à l'occasion !",
					"Résultat", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public int getNombreCoups() {
		return nbCoups;
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
		for (Observer obs : listObserver) {
			obs.update(this.nbCases, this.proposition + " : " + FromStringArrayToString.convert(this.compare()),
					10 - this.getNombreCoups());
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
