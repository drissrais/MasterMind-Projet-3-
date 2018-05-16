package com.openclassrooms.jeudelogique.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Component;

public class SearchChallengerPanel extends ZContainer {

	public SearchChallengerPanel(Dimension dim) {
		super(dim);
		initPanel();
	}

	@Override
	protected void initPanel() {
		Dimension dim = new Dimension(800, 50);

		JPanel northContent = new JPanel();
		northContent.setBackground(Color.WHITE);
		northContent.setPreferredSize(dim);
		JLabel welcomeMessage = new JLabel("Bienvenue dans le jeu de recherche +/- en mode challenger".toUpperCase());
		welcomeMessage.setPreferredSize(new Dimension(800, 50));
		welcomeMessage.setHorizontalAlignment(JLabel.CENTER);
		welcomeMessage.setFont(comics20);
		welcomeMessage.setForeground(Color.GRAY);
		northContent.add(welcomeMessage);

		JPanel centerContent = new JPanel();
		centerContent.setPreferredSize(new Dimension(800, 400));
		centerContent.setBackground(Color.WHITE);

		JTextArea texte = new JTextArea(
				"Saurez-vous trouver la combinaison cachée en moins de 10 coups?\n(Chiffres compris entre 0 et 9 avec répétitions possibles)\n+ : Chiffre plus grand\t - : Chiffre plus petit\t= : Bon chiffre");
		texte.setPreferredSize(new Dimension(700, 150));
		texte.setFont(arial);
		centerContent.add(texte, BorderLayout.NORTH);
		
		JLabel propositionLabel = new JLabel("Entrez les 4 chiffres de votre proposition :");
		propositionLabel.setHorizontalAlignment(JLabel.LEFT);
		propositionLabel.setPreferredSize(new Dimension(300, 100));
		propositionLabel.setFont(arial);
		centerContent.add(propositionLabel, BorderLayout.EAST);
		
		JTextField propositionTextField = new JTextField();
		propositionTextField.setPreferredSize(new Dimension(200, 30));
		propositionTextField.setHorizontalAlignment(JTextField.CENTER);
		centerContent.add(propositionTextField, BorderLayout.WEST);
		JTextField response = new JTextField();
		response.setPreferredSize(new Dimension(400, 30));
		response.setHorizontalAlignment(JTextField.CENTER);
		response.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerContent.add(response, BorderLayout.SOUTH);

		JPanel southContent = new JPanel();
		southContent.setBackground(Color.WHITE);
		southContent.setPreferredSize(dim);
		JLabel nombreCoup = new JLabel("Nombre de coups restants : 10");
		nombreCoup.setPreferredSize(new Dimension(700, 20));
		nombreCoup.setHorizontalAlignment(JLabel.CENTER);
		nombreCoup.setFont(arial);
		southContent.add(nombreCoup);
		
		this.panel.add(northContent, BorderLayout.NORTH);
		this.panel.add(centerContent, BorderLayout.CENTER);
		this.panel.add(southContent, BorderLayout.SOUTH);
	}

}
