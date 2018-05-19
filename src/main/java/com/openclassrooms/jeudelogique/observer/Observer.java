package com.openclassrooms.jeudelogique.observer;

public interface Observer {
	public void update(int nbCases, String story, int nbCoups);
	public void restart();
	public void accueil();
}
