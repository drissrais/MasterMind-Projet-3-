package com.openclassrooms.jeudelogique.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.openclassrooms.jeudelogique.controler.SearchChallengerControler;
import com.openclassrooms.jeudelogique.model.SearchModel;
import com.openclassrooms.jeudelogique.observer.Observer;

public class RulesPanel extends ZContainer implements Observer {
	private JLabel titre2Label = new JLabel("Mastermind\r\n");
	private JLabel titre1Label = new JLabel("Recherche +/-\r\n");
	private JTextArea texte1;
	private JTextArea texte2;
	private JButton accueilButton;
	
	private SearchChallengerControler controler;

	public RulesPanel(Dimension dim, SearchModel model) {
		super(dim);
		this.controler = new SearchChallengerControler(model);
		initPanel();
	}

	@Override
	protected void initPanel() {
		Dimension dim = new Dimension(800, 220);
		Dimension dim2 = new Dimension(800, 20);
		
		titre1Label.setForeground(Color.decode("#ee5100"));
		titre1Label.setFont(arial20);
		titre1Label.setPreferredSize(dim2);
		titre2Label.setForeground(Color.decode("#ee5100"));
		titre2Label.setFont(arial20);
		titre2Label.setPreferredSize(dim2);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(Color.WHITE);
		searchPanel.setPreferredSize(dim);
		String message = "\r\n"
				+ "Le but : découvrir la combinaison à x chiffres de l'adversaire (le défenseur).\nPour ce faire, l'attaquant fait une proposition. Le défenseur indique pour chaque chiffre de la\ncombinaison proposée si le chiffre de sa combinaison est plus grand (+), plus petit (-) ou si\nc'est le bon chiffre (=).\r\n"
				+ "L'attaquant doit deviner la combinaison secrète en un nombre limité d'essais. Exemple :\r\n" + "\r\n"
				+ "(Combinaison secrète : 1234)\r\n" + "Proposition : 4278 -> Réponse : -=--\r\n"
				+ "Proposition : 2214 -> Réponse : -=+=\r\n" + "\r\n";
		texte1 = new JTextArea(message);
		texte1.setFont(arial);
		texte1.setEditable(false);
		searchPanel.add(titre1Label);
		searchPanel.add(texte1);
		
		JPanel mastermindPanel = new JPanel();
		mastermindPanel.setBackground(Color.WHITE);
		mastermindPanel.setPreferredSize(dim);
		String message2 = "\r\n"
				+ "Le but : découvrir la combinaison à x chiffres/couleurs de l'adversaire (le défenseur). Pour\nce faire, l'attaquant fait une proposition. Le défenseur indique pour chaque proposition le\nnombre de chiffre/couleur de la proposition qui apparaissent à la bonne place et à la\nmauvaise place dans la combinaison secrète.\r\n"
				+ "L'attaquant doit deviner la combinaison secrète en un nombre limité d'essais. Exemple :\r\n" + "\r\n"
				+ "(Combinaison secrète : 1234)\r\n" + "Proposition : 4278 -> Réponse : 1 présent, 1 bien placé\r\n"
				+ "Proposition : 6274 -> Réponse : 2 bien placés\r\n" + "\r\n";
		texte2 = new JTextArea(message2);
		texte2.setFont(arial);
		texte2.setEditable(false);
		mastermindPanel.add(titre2Label);
		mastermindPanel.add(texte2);
		
		accueilButton = new JButton("Retour à l'accueil");
		accueilButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		accueilButton.addActionListener((e) -> controler.setChoixFinDePartie("Revenir au menu"));
		
		this.panel.setBackground(Color.WHITE);
		this.panel.add(searchPanel);
		this.panel.add(mastermindPanel);
		this.panel.add(accueilButton);
		
	}

	@Override
	public void update(String proposition, String reponse) {
	}

	@Override
	public void updateModeDuel(String proposition, String reponse, String combiSecrete) {
	}

	@Override
	public void restart() {
	}

	@Override
	public void accueil() {
	}

	@Override
	public void exitApplication() {
	}

}
