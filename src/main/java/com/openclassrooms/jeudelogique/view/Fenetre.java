package com.openclassrooms.jeudelogique.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import com.openclassrooms.jeudelogique.observer.Observable;
import com.openclassrooms.jeudelogique.observer.Observer;

public class Fenetre extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;

	private JMenuBar menubar = new JMenuBar();
	private JMenu fichier = new JMenu("Fichier");
	private JMenu APropos = new JMenu("À Propos");
	private JMenuItem nouveau = new JMenuItem("Nouveau Jeu");
	private JMenuItem quitter = new JMenuItem("Quitter");
	private JMenuItem regles = new JMenuItem("Règles du jeu");
	
	private JToolBar toolbar = new JToolBar(JToolBar.HORIZONTAL);
	private JButton newGameButton = new JButton(new ImageIcon(getClass().getResource("/new.png")));
	private JButton exitButton = new JButton(new ImageIcon(getClass().getResource("/quit.png")));
	
	private JPanel conteneur = new JPanel(new BorderLayout());
	private Dimension size;
	private Observable model;

	public Fenetre(Observable obs) {
		setTitle("Jeux de Logique");
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		size = new Dimension(this.getWidth(), this.getHeight());
		
		this.model = obs;
		this.model.addObserver(this);
		
		initMenu();
		initToolBar();
		conteneur.setBackground(Color.WHITE);
		conteneur.add(toolbar, BorderLayout.PAGE_START);
		conteneur.add(new AccueilPanel(this.size).getPanel(), BorderLayout.CENTER);
		setContentPane(conteneur);
		
	}
	
	private void initToolBar() {
		toolbar.setBorder(BorderFactory.createEmptyBorder());
		toolbar.setBackground(Color.WHITE);
		toolbar.add(newGameButton);
		toolbar.add(exitButton);
		newGameButton.addActionListener(new newGameListener());
		exitButton.addActionListener((e) -> System.exit(1));
	}

	private void initMenu() {
		nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));
		
		fichier.add(nouveau);
		fichier.addSeparator();
		fichier.add(quitter);
		fichier.setMnemonic('F');
		
		APropos.add(regles);
		APropos.setMnemonic('O');
		
		menubar.add(fichier);
		menubar.add(APropos);
		setJMenuBar(menubar);

		quitter.addActionListener((e) -> System.exit(1));
		regles.addActionListener((e) -> {
			this.conteneur.removeAll();
			this.conteneur.add(new RulesPanel(this.size).getPanel(), BorderLayout.CENTER);
			this.conteneur.revalidate();
		});

		nouveau.addActionListener(new newGameListener());
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

	@Override
	public void update(int nbCases, String story, int nbCoups) {	}

	@Override
	public void restart() {}

	@Override
	public void accueil() {
		conteneur.removeAll();
		conteneur.add(new AccueilPanel(size).getPanel(), BorderLayout.CENTER);
		conteneur.revalidate();
		newGameButton.doClick();
	}
	
	public class newGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			BoiteDialogueParametrage boite = new BoiteDialogueParametrage(null, "Sélectionner le jeu et le mode", true);
			if ((!boite.getzInfo().getGame().equals("")) && (!boite.getzInfo().getMode().equals(""))) {
				if (boite.getzInfo().getGame().equals("Recherche +/-") && boite.getzInfo().getMode().equals("CHALLENGER")) {
					conteneur.removeAll();
					SearchChallengerPanel scp = new SearchChallengerPanel(size, model);
					model.addObserver(scp);
					conteneur.add(scp.getPanel(), BorderLayout.CENTER);
					conteneur.revalidate();
				}
			}
		}
		
	}

}
