package com.openclassrooms.jeudelogique.model;

import java.util.ArrayList;

import com.openclassrooms.jeudelogique.observer.Observable;
import com.openclassrooms.jeudelogique.observer.Observer;
import com.openclassrooms.jeudelogique.utilities.FromStringArrayToString;

public class MastermindModel implements Observable {
	private ArrayList<Observer> listObserver = new ArrayList<>();
	private String proposition;
	private String combinaisonSecrete;
	private String choixFinDePartie;

	public void setProposition(String proposition) {
		this.proposition = proposition;
		this.compare();
		this.notifyObserver();
	}
	
	public void setCombinaisonSecrete(String combinaisonSecrete) {
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
			obs.update(this.proposition, this.compare());
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
