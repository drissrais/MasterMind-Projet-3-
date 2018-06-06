package com.openclassrooms.jeudelogique.observer;

public interface Observer {
	public void update(String proposition, String reponse);
	public void updateModeDuel(String proposition, String reponseJoueur, String reponseOrdinateur);
	public void restart();
	public void accueil();
	public void exitApplication();
}
