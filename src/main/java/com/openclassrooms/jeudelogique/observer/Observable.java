package com.openclassrooms.jeudelogique.observer;

public interface Observable {
	public void addObserver(Observer o);
	public void deleteObserver();
	public void notifyObserver();
	public void restartObserver();
	public void accueilObserver();
	public void exitApplicationObserver();
}
