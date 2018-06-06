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

import com.openclassrooms.jeudelogique.model.MastermindModel;
import com.openclassrooms.jeudelogique.model.SearchModel;
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
	private JButton newGameButton = new JButton(new ImageIcon(getClass().getResource("/newGame.png")));
	private JButton exitButton = new JButton(new ImageIcon(getClass().getResource("/quitGame.png")));

	BorderLayout layout = new BorderLayout();
	private JPanel conteneur = new JPanel();
	private JPanel contentPane = new JPanel(layout);
	private JPanel toolbarConteneur = new JPanel(new BorderLayout());

	private Dimension size;
	private SearchModel searchModel;
	private MastermindModel mastermindModel;

	public Fenetre(SearchModel model1, MastermindModel model2) {
		setTitle("Jeux de Logique");
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		size = new Dimension(this.getWidth(), this.getHeight());

		this.searchModel = model1;
		this.mastermindModel = model2;
		this.searchModel.addObserver(this);
		this.mastermindModel.addObserver(this);

		initMenu();
		initToolBar();

		conteneur.setBackground(Color.WHITE);
		conteneur.add(new AccueilPanel(this.size).getPanel(), BorderLayout.CENTER);

		contentPane.add(toolbarConteneur, BorderLayout.NORTH);
		contentPane.add(conteneur, BorderLayout.CENTER);
		setContentPane(contentPane);

	}

	private void initToolBar() {
		toolbar.setBorder(BorderFactory.createEmptyBorder());
		newGameButton.setFocusable(false);
		exitButton.setFocusable(false);
		toolbar.add(newGameButton);
		toolbar.add(exitButton);
		toolbarConteneur.add(toolbar, BorderLayout.PAGE_START);
		newGameButton.addActionListener(new newGameListener());
		exitButton.addActionListener((e) -> System.exit(1));
	}

	private void initMenu() {
		nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));
		regles.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));

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
			this.conteneur.add(new RulesPanel(this.size, this.searchModel).getPanel(), BorderLayout.CENTER);
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
		conteneur.removeAll();
		conteneur.add(new AccueilPanel(size).getPanel(), BorderLayout.CENTER);
		conteneur.revalidate();
		newGameButton.doClick();
	}

	@Override
	public void exitApplication() {
		System.exit(1);
	}

	public class newGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			BoiteDialogueDebutDePartie boite = new BoiteDialogueDebutDePartie(null, "Nouveau Jeu", true);
			if ((!boite.getzInfo().getGame().equals("")) && (!boite.getzInfo().getMode().equals(""))) {
				if (boite.getzInfo().getGame().equals("Recherche +/-")
						&& boite.getzInfo().getMode().equals("CHALLENGER")) {
					conteneur.removeAll();
					SearchChallengerPanel scp = new SearchChallengerPanel(size, searchModel);
					searchModel.addObserver(scp);
					conteneur.add(scp.getPanel(), BorderLayout.CENTER);
					conteneur.revalidate();
					initModel();
				}
				if (boite.getzInfo().getGame().equals("MasterMind")
						&& boite.getzInfo().getMode().equals("CHALLENGER")) {
					conteneur.removeAll();
					MastermindChallengerPanel mcp = new MastermindChallengerPanel(size, mastermindModel);
					mastermindModel.addObserver(mcp);
					conteneur.add(mcp.getPanel(), BorderLayout.CENTER);
					conteneur.revalidate();
					initModel();
				}
				if (boite.getzInfo().getGame().equals("Recherche +/-")
						&& boite.getzInfo().getMode().equals("DEFENSEUR")) {
					conteneur.removeAll();
					SearchDefenderPanel sdp = new SearchDefenderPanel(size, searchModel);
					searchModel.addObserver(sdp);
					conteneur.add(sdp.getPanel(), BorderLayout.CENTER);
					conteneur.revalidate();
					initModel();
				}
				if (boite.getzInfo().getGame().equals("MasterMind")
						&& boite.getzInfo().getMode().equals("DEFENSEUR")) {
					conteneur.removeAll();
					MastermindDefenderPanel mdp = new MastermindDefenderPanel(size, mastermindModel);
					mastermindModel.addObserver(mdp);
					conteneur.add(mdp.getPanel(), BorderLayout.CENTER);
					conteneur.revalidate();
					initModel();
				}
				if (boite.getzInfo().getGame().equals("Recherche +/-")
						&& boite.getzInfo().getMode().equals("DUEL")) {
					conteneur.removeAll();
					SearchDualPanel sdp = new SearchDualPanel(size, searchModel);
					searchModel.addObserver(sdp);
					conteneur.add(sdp.getPanel(), BorderLayout.CENTER);
					conteneur.revalidate();
					initModel();
				}
				if (boite.getzInfo().getGame().equals("MasterMind")
						&& boite.getzInfo().getMode().equals("DUEL")) {
					conteneur.removeAll();
					MastermindDualPanel mdp = new MastermindDualPanel(size, mastermindModel);
					mastermindModel.addObserver(mdp);
					conteneur.add(mdp.getPanel(), BorderLayout.CENTER);
					conteneur.revalidate();
					initModel();
				}
			}
		}

	}

	private void initModel() {
		this.searchModel = new SearchModel();
		this.mastermindModel = new MastermindModel();
		this.searchModel.addObserver(this);
		this.mastermindModel.addObserver(this);
	}

}
