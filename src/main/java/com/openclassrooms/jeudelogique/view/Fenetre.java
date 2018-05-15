package com.openclassrooms.jeudelogique.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Fenetre extends JFrame {

	private static final long serialVersionUID = 1L;

	private JMenuBar menubar = new JMenuBar();
	private JMenu fichier = new JMenu("Fichier");
	private JMenu APropos = new JMenu("À Propos");
	private JMenuItem nouveau = new JMenuItem("Nouveau Jeu");
	private JMenuItem quitter = new JMenuItem("Quitter");
	private JMenuItem regles = new JMenuItem("Règles du jeu");
	
	private JPanel conteneur = new JPanel();
	private Dimension size;

	public Fenetre() {
		setTitle("Jeux de Logique");
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initMenu();
		size = new Dimension(this.getWidth(), this.getHeight());
		conteneur.setBackground(Color.WHITE);
		conteneur.add(new AccueilPanel(this.size).getPanel());
		setContentPane(conteneur);
		
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
			this.conteneur.removeAll();
			this.conteneur.add(new RulesPanel(this.size).getPanel(), BorderLayout.CENTER);
			this.conteneur.revalidate();
		});

		nouveau.addActionListener((e) -> {
			new BoiteDialogueParametrage(null, "Sélectionner le jeu et le mode", false);
		});
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(900, 600);
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
