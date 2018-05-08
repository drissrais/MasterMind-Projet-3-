package com.openclassrooms.jeudelogique.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Fenetre extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JMenuBar menubar = new JMenuBar();
	private JMenu fichier = new JMenu("Fichier");
	private JMenu APropos = new JMenu("À Propos");
	private JMenuItem nouveau = new JMenuItem("Nouveau Jeu");
	private JMenuItem quitter = new JMenuItem("Quitter");
	private JMenuItem regles = new JMenuItem("Règles du jeu");
	
	public Fenetre() {
		initMenu();
		initComponent();
		setTitle("Jeux de Logique");
		pack();
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void initMenu() {
		fichier.add(nouveau);
		fichier.addSeparator();
		fichier.add(quitter);
		APropos.add(regles);
		menubar.add(fichier);
		menubar.add(APropos);
		setJMenuBar(menubar);
		
		quitter.addActionListener((e) -> System.exit(1));
		regles.addActionListener((e) -> {
			String message = "Recherche +/-\r\n" + 
					"\r\n" + 
					"Le but : découvrir la combinaison à x chiffres de l'adversaire (le défenseur).\nPour ce faire, l'attaquant fait une proposition. Le défenseur indique pour chaque chiffre de la\ncombinaison proposée si le chiffre de sa combinaison est plus grand (+), plus petit (-) ou si\nc'est le bon chiffre (=).\r\n" + 
					"L'attaquant doit deviner la combinaison secrète en un nombre limité d'essais.\r\n" + 
					"\r\n" + 
					"(Combinaison secrète : 1234)\r\n" + 
					"Proposition : 4278 -> Réponse : -=--\r\n" + 
					"Proposition : 2214 -> Réponse : -=+=\r\n" + 
					"\r\n" + 
					"Mastermind\r\n" + 
					"\r\n" + 
					"Le but : découvrir la combinaison à x chiffres/couleurs de l'adversaire (le défenseur). Pour\nce faire, l'attaquant fait une proposition. Le défenseur indique pour chaque proposition le\nnombre de chiffre/couleur de la proposition qui apparaissent à la bonne place et à la\nmauvaise place dans la combinaison secrète.\r\n" + 
					"L'attaquant doit deviner la combinaison secrète en un nombre limité d'essais.\r\n" + 
					"\r\n" + 
					"(Combinaison secrète : 1234)\r\n" + 
					"Proposition : 4278 -> Réponse : 1 présent, 1 bien placé\r\n" + 
					"Proposition : 6274 -> Réponse : 2 bien placés\r\n" + 
					"\r\n";
			JOptionPane.showMessageDialog(Fenetre.this, message, "Instructions", JOptionPane.INFORMATION_MESSAGE);
		});
		
		nouveau.addActionListener((e) -> {
			new BoiteDialogueParametrage(null, "", false);
		});
	}
	
	private void initComponent() {
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.WHITE);
		JLabel icone = new JLabel(new ImageIcon(getClass().getResource("/master.jpg")));
		panIcon.add(icone);
		
		Font font = new Font("Verdana", Font.BOLD, 20);
		JPanel panWelcome = new JPanel();
		panWelcome.setBackground(Color.WHITE);
		JLabel welcomeMessage = new JLabel("bienvenue au célébre mastermind !".toUpperCase());
		welcomeMessage.setFont(font);
		welcomeMessage.setForeground(Color.DARK_GRAY);
		panWelcome.add(welcomeMessage);
		
		add(panWelcome, BorderLayout.NORTH);
		add(panIcon, BorderLayout.CENTER);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
	
	@Override
	public Dimension getMaximumSize() {
		return getPreferredSize();
	}
	
	@Override
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

}
