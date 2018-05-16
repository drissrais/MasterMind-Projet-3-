package com.openclassrooms.jeudelogique.model;

import java.util.ArrayList;

import com.openclassrooms.jeudelogique.observer.Observable;
import com.openclassrooms.jeudelogique.observer.Observer;

public class Model implements Observable {
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	private String proposition;
	
	public void setProposition(String proposition) {
		this.proposition = proposition;
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
		
	}
	
}
