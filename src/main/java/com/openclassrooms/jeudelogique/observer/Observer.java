package com.openclassrooms.jeudelogique.observer;

public interface Observer {
	public void update(String proposition, String reponse);
	public void updateModeDefenseurOuDuel(String proposition, String reponse, String combiSecrete);
	public void restart();
	public void accueil();
	public void exitApplication();
}
