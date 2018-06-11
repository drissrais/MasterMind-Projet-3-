package com.openclassrooms.jeudelogique.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

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
	private JMenu aPropos = new JMenu("À Propos");
	private JMenu param = new JMenu("Paramètres");
	private JMenuItem nouveau = new JMenuItem("Nouveau Jeu");
	private JMenuItem quitter = new JMenuItem("Quitter");
	private JMenuItem regles = new JMenuItem("Règles du jeu");
	private JMenuItem parametres = new JMenuItem("Paramètres");

	private JToolBar toolbar = new JToolBar(JToolBar.HORIZONTAL);
	private JButton newGameButton = new JButton(new ImageIcon(getClass().getResource("/newGame.png")));
	private JButton exitButton = new JButton(new ImageIcon(getClass().getResource("/quitGame.png")));

	BorderLayout layout = new BorderLayout();
	private JPanel conteneur = new JPanel();
	private JPanel contentPane = new JPanel(layout);
	private JPanel toolbarConteneur = new JPanel(new BorderLayout());

	private Properties properties;
	private int nbCoupsRecherchePlusMoins, nbCasesRecherchePlusMoins, nbCoupsMastermind, nbCasesMastermind,
			nbChiffresAUtiliserMastermind;
	private boolean developerMode;

	private Dimension size;
	private SearchModel searchModel;
	private MastermindModel mastermindModel;

	public Fenetre(SearchModel model1, MastermindModel model2, boolean developerMode) {
		setTitle("Jeux de Logique");
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		size = new Dimension(this.getWidth(), this.getHeight());

		this.developerMode = developerMode;
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

		setIconImage(new ImageIcon(getClass().getResource("/MastermindFormatIcone.png")).getImage());

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
		parametres.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));

		fichier.add(nouveau);
		fichier.addSeparator();
		fichier.add(quitter);
		fichier.setMnemonic('F');

		aPropos.add(regles);
		aPropos.setMnemonic('O');

		param.add(parametres);
		param.setMnemonic('P');

		menubar.add(fichier);
		menubar.add(aPropos);
		menubar.add(param);
		setJMenuBar(menubar);

		nouveau.addActionListener(new newGameListener());
		quitter.addActionListener((e) -> {
			properties = new Properties();
			try (InputStream input = new FileInputStream("src/main/resources/config.properties");
					OutputStream output = new FileOutputStream("src/main/resources/config.properties")) {
				properties.load(input);

				properties.setProperty("param.nbCoupsRecherchePlusMoins", "10");
				properties.setProperty("param.nbCasesRecherchePlusMoins", "4");

				properties.setProperty("param.nbCoupsMastermind", "10");
				properties.setProperty("param.nbCasesMastermind", "4");
				properties.setProperty("param.nbChiffresAUtiliserMastermind", "10");

				properties.store(output, "Fichier de configuration config.properties");
				System.exit(0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		regles.addActionListener((e) -> {
			this.conteneur.removeAll();
			this.conteneur.add(new RulesPanel(this.size, this.searchModel).getPanel(), BorderLayout.CENTER);
			this.conteneur.revalidate();
			param.setEnabled(true);
		});
		
		parametres.addActionListener((e) -> {
			new BoiteDialogueParametrage(null, "Paramètres de jeu", true, this.developerMode);
		});
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				properties = new Properties();
				try (InputStream input = new FileInputStream("src/main/resources/config.properties");
						OutputStream output = new FileOutputStream("src/main/resources/config.properties")) {
					properties.load(input);

					properties.setProperty("param.nbCoupsRecherchePlusMoins", "10");
					properties.setProperty("param.nbCasesRecherchePlusMoins", "4");

					properties.setProperty("param.nbCoupsMastermind", "10");
					properties.setProperty("param.nbCasesMastermind", "4");
					properties.setProperty("param.nbChiffresAUtiliserMastermind", "10");

					properties.store(output, "Fichier de configuration config.properties");
					System.exit(0);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
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
		// newGameButton.doClick();
		param.setEnabled(true);
	}

	@Override
	public void exitApplication() {
		properties = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/config.properties");
				OutputStream output = new FileOutputStream("src/main/resources/config.properties")) {
			properties.load(input);

			// Traitement pour le jeu RecherchePlusMoins
			properties.setProperty("param.nbCoupsRecherchePlusMoins", "10");
			properties.setProperty("param.nbCasesRecherchePlusMoins", "4");

			// Traitement pour le jeu Mastermind
			properties.setProperty("param.nbCoupsMastermind", "10");
			properties.setProperty("param.nbCasesMastermind", "4");
			properties.setProperty("param.nbChiffresAUtiliserMastermind", "10");

			properties.store(output, "Fichier de configuration config.properties");
			System.exit(0);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public class newGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			BoiteDialogueDebutDePartie boite = new BoiteDialogueDebutDePartie(null, "Nouveau Jeu", true);
			properties = new Properties();
			try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
				properties.load(input);
				nbCoupsRecherchePlusMoins = Integer.parseInt(properties.getProperty("param.nbCoupsRecherchePlusMoins"));
				nbCasesRecherchePlusMoins = Integer.parseInt(properties.getProperty("param.nbCasesRecherchePlusMoins"));
				nbCoupsMastermind = Integer.parseInt(properties.getProperty("param.nbCoupsMastermind"));
				nbCasesMastermind = Integer.parseInt(properties.getProperty("param.nbCasesMastermind"));
				nbChiffresAUtiliserMastermind = Integer
						.parseInt(properties.getProperty("param.nbChiffresAUtiliserMastermind"));
				developerMode = Boolean.parseBoolean(properties.getProperty("param.modeDeveloppeur"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if ((!boite.getzInfo().getGame().equals("")) && (!boite.getzInfo().getMode().equals(""))) {
				if (boite.getzInfo().getGame().equals("Recherche +/-")
						&& boite.getzInfo().getMode().equals("CHALLENGER")) {
					param.setEnabled(false);
					conteneur.removeAll();
					SearchChallengerPanel scp = new SearchChallengerPanel(size, searchModel, nbCoupsRecherchePlusMoins,
							nbCasesRecherchePlusMoins, developerMode);
					searchModel.addObserver(scp);
					conteneur.add(scp.getPanel(), BorderLayout.CENTER);
					conteneur.revalidate();
					initModel();
				}
				if (boite.getzInfo().getGame().equals("MasterMind")
						&& boite.getzInfo().getMode().equals("CHALLENGER")) {
					param.setEnabled(false);
					conteneur.removeAll();
					MastermindChallengerPanel mcp = new MastermindChallengerPanel(size, mastermindModel,
							nbCoupsMastermind, nbCasesMastermind, nbChiffresAUtiliserMastermind, developerMode);
					mastermindModel.addObserver(mcp);
					conteneur.add(mcp.getPanel(), BorderLayout.CENTER);
					conteneur.revalidate();
					initModel();
				}
				if (boite.getzInfo().getGame().equals("Recherche +/-")
						&& boite.getzInfo().getMode().equals("DEFENSEUR")) {
					param.setEnabled(false);
					conteneur.removeAll();
					SearchDefenderPanel sdp = new SearchDefenderPanel(size, searchModel, nbCoupsRecherchePlusMoins,
							nbCasesRecherchePlusMoins);
					searchModel.addObserver(sdp);
					conteneur.add(sdp.getPanel(), BorderLayout.CENTER);
					conteneur.revalidate();
					initModel();
				}
				if (boite.getzInfo().getGame().equals("MasterMind") && boite.getzInfo().getMode().equals("DEFENSEUR")) {
					param.setEnabled(false);
					conteneur.removeAll();
					MastermindDefenderPanel mdp = new MastermindDefenderPanel(size, mastermindModel, nbCoupsMastermind,
							nbCasesMastermind, nbChiffresAUtiliserMastermind);
					mastermindModel.addObserver(mdp);
					conteneur.add(mdp.getPanel(), BorderLayout.CENTER);
					conteneur.revalidate();
					initModel();
				}
				if (boite.getzInfo().getGame().equals("Recherche +/-") && boite.getzInfo().getMode().equals("DUEL")) {
					param.setEnabled(false);
					conteneur.removeAll();
					SearchDualPanel sdp = new SearchDualPanel(size, searchModel, nbCasesRecherchePlusMoins,
							developerMode);
					searchModel.addObserver(sdp);
					conteneur.add(sdp.getPanel(), BorderLayout.CENTER);
					conteneur.revalidate();
					initModel();
				}
				if (boite.getzInfo().getGame().equals("MasterMind") && boite.getzInfo().getMode().equals("DUEL")) {
					param.setEnabled(false);
					conteneur.removeAll();
					MastermindDualPanel mdp = new MastermindDualPanel(size, mastermindModel, nbCasesMastermind,
							nbChiffresAUtiliserMastermind, developerMode);
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
