package com.openclassrooms.jeudelogique.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTextArea;

public class RulesPanel extends ZContainer {

	public RulesPanel(Dimension dim) {
		super(dim);
		initPanel();
	}

	@Override
	protected void initPanel() {
		String message = "Recherche +/-\r\n" + "\r\n"
				+ "Le but : découvrir la combinaison à x chiffres de l'adversaire (le défenseur).\nPour ce faire, l'attaquant fait une proposition. Le défenseur indique pour chaque chiffre de la\ncombinaison proposée si le chiffre de sa combinaison est plus grand (+), plus petit (-) ou si\nc'est le bon chiffre (=).\r\n"
				+ "L'attaquant doit deviner la combinaison secrète en un nombre limité d'essais.\r\n" + "\r\n"
				+ "(Combinaison secrète : 1234)\r\n" + "Proposition : 4278 -> Réponse : -=--\r\n"
				+ "Proposition : 2214 -> Réponse : -=+=\r\n" + "\r\n" + "Mastermind\r\n" + "\r\n"
				+ "Le but : découvrir la combinaison à x chiffres/couleurs de l'adversaire (le défenseur). Pour\nce faire, l'attaquant fait une proposition. Le défenseur indique pour chaque proposition le\nnombre de chiffre/couleur de la proposition qui apparaissent à la bonne place et à la\nmauvaise place dans la combinaison secrète.\r\n"
				+ "L'attaquant doit deviner la combinaison secrète en un nombre limité d'essais.\r\n" + "\r\n"
				+ "(Combinaison secrète : 1234)\r\n" + "Proposition : 4278 -> Réponse : 1 présent, 1 bien placé\r\n"
				+ "Proposition : 6274 -> Réponse : 2 bien placés\r\n" + "\r\n";
		JTextArea instructions = new JTextArea(message);
		instructions.setFont(arial);
		instructions.setEditable(false);
		this.panel.add(instructions, BorderLayout.CENTER);
		
	}

}
