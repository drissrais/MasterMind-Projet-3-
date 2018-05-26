package com.openclassrooms.jeudelogique.model;

import java.util.ArrayList;

import com.openclassrooms.jeudelogique.observer.Observable;
import com.openclassrooms.jeudelogique.observer.Observer;

public class SearchModel implements Observable {
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
		char[] tab = new char[this.combinaisonSecrete.length()];
		for (int i = 0; i < this.combinaisonSecrete.length(); i++) {
			if (combinaisonSecrete.charAt(i) == proposition.charAt(i))
				tab[i] = '=';
			if (combinaisonSecrete.charAt(i) < proposition.charAt(i))
				tab[i] = '-';
			if (combinaisonSecrete.charAt(i) > proposition.charAt(i))
				tab[i] = '+';
		}
		return String.valueOf(tab);
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
