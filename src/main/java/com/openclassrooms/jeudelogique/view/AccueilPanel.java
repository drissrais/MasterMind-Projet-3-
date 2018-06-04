package com.openclassrooms.jeudelogique.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class AccueilPanel extends ZContainer {
	
	public AccueilPanel(Dimension dim) {
		super(dim);
		initPanel();
	}

	@Override
	protected void initPanel() {
		this.panel.setBackground(Color.WHITE);
		
		JLabel welcomeMessage = new JLabel("Bienvenue dans MindGames");
		welcomeMessage.setFont(comics30);
		welcomeMessage.setForeground(Color.decode("#ee5100"));
		this.panel.add(welcomeMessage, BorderLayout.NORTH);
		
		JLabel icone = new JLabel(new ImageIcon(getClass().getResource("/master.jpg")));
		this.panel.add(icone, BorderLayout.CENTER);
		
		JTextArea texte = new JTextArea("Vous avez dix coups pour trouver la combinaison cachée.\n\t" + "Alors, à vous de jouer !");
		texte.setFont(comics20);
		texte.setEditable(false);
		this.panel.add(texte, BorderLayout.SOUTH);
		
	}

}
