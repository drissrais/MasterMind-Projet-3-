package com.openclassrooms.jeudelogique.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class RulesPanel extends ZContainer {
	JLabel titre2Label = new JLabel("Mastermind\r\n");

	public RulesPanel(Dimension dim) {
		super(dim);
		initPanel();
	}

	@Override
	protected void initPanel() {
		Dimension dim = new Dimension(800, 225);
		
		JLabel titre1Label = new JLabel("Recherche +/-\r\n");
		titre1Label.setForeground(Color.decode("#ee5100"));
		titre1Label.setFont(arial20);
		titre1Label.setPreferredSize(new Dimension(800, 20));
		titre2Label.setForeground(Color.decode("#ee5100"));
		titre2Label.setFont(arial20);
		titre2Label.setPreferredSize(new Dimension(800, 20));
		
		JPanel searchPanel = new JPanel();
		searchPanel.setPreferredSize(dim);
		String message = "\r\n"
				+ "Le but : découvrir la combinaison à x chiffres de l'adversaire (le défenseur).\nPour ce faire, l'attaquant fait une proposition. Le défenseur indique pour chaque chiffre de la\ncombinaison proposée si le chiffre de sa combinaison est plus grand (+), plus petit (-) ou si\nc'est le bon chiffre (=).\r\n"
				+ "L'attaquant doit deviner la combinaison secrète en un nombre limité d'essais. Exemple :\r\n" + "\r\n"
				+ "(Combinaison secrète : 1234)\r\n" + "Proposition : 4278 -> Réponse : -=--\r\n"
				+ "Proposition : 2214 -> Réponse : -=+=\r\n" + "\r\n";
		JTextArea texte1 = new JTextArea(message);
		texte1.setFont(arial);
		texte1.setBackground(Color.decode("#eeeeee"));
		texte1.setEditable(false);
		searchPanel.add(titre1Label);
		searchPanel.add(texte1);
		
		JPanel mastermindPanel = new JPanel();
		mastermindPanel.setPreferredSize(dim);
		String message2 = "\r\n"
				+ "Le but : découvrir la combinaison à x chiffres/couleurs de l'adversaire (le défenseur). Pour\nce faire, l'attaquant fait une proposition. Le défenseur indique pour chaque proposition le\nnombre de chiffre/couleur de la proposition qui apparaissent à la bonne place et à la\nmauvaise place dans la combinaison secrète.\r\n"
				+ "L'attaquant doit deviner la combinaison secrète en un nombre limité d'essais. Exemple :\r\n" + "\r\n"
				+ "(Combinaison secrète : 1234)\r\n" + "Proposition : 4278 -> Réponse : 1 présent, 1 bien placé\r\n"
				+ "Proposition : 6274 -> Réponse : 2 bien placés\r\n" + "\r\n";
		JTextArea texte2 = new JTextArea(message2);
		texte2.setFont(arial);
		texte2.setBackground(Color.decode("#eeeeee"));
		texte2.setEditable(false);
		mastermindPanel.add(titre2Label);
		mastermindPanel.add(texte2);
		
		this.panel.add(searchPanel);
		this.panel.add(mastermindPanel);
		
	}

}
