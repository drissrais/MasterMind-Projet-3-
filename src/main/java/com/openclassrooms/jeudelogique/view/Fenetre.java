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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * La classe Fenetre est la fenêtre principale de l'application. Elle correspond à la page d'accueil du jeu.
 * Elle est composée d'une barre de menu et d'une barre d'outils permettant d'accèder aux jeux RecherchePlusMoins
 * et Mastermind dans les modes Challenger, Défenseur et Duel, mais également aux paramètres de jeu et aux instructions.
 * Elle implémente l'interface Observer.
 */
public class Fenetre extends JFrame implements Observer {

	static final Logger LOGGER = LogManager.getLogger();
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

	private String developerModeString = "";

	public Fenetre(SearchModel model1, MastermindModel model2, boolean developerMode) {
		LOGGER.trace("Instanciation de la fenêtre principale");
		setTitle("Jeux de Logique");
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		size = new Dimension(this.getWidth(), this.getHeight());

		this.developerMode = developerMode;
		if (developerMode == true) {
			developerModeString = "true";
		} else {
			developerModeString = "false";
		}

		this.searchModel = model1;
		this.mastermindModel = model2;
		LOGGER.trace("Initialisation des modèles de données");

		this.searchModel.addObserver(this);
		this.mastermindModel.addObserver(this);

		initMenu();
		initToolBar();

		conteneur.setBackground(Color.WHITE);
		conteneur.add(new AccueilPanel(this.size).getPanel(), BorderLayout.CENTER);

		contentPane.add(toolbarConteneur, BorderLayout.NORTH);
		contentPane.add(conteneur, BorderLayout.CENTER);
		setContentPane(contentPane);

		setIconImage(new ImageIcon(getClass().getResource("/mastermindIcone.png")).getImage());
		
		properties = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
			properties.load(input);
			nbCoupsRecherchePlusMoins = Integer.parseInt(properties.getProperty("param.nbCoupsRecherchePlusMoins"));
			nbCasesRecherchePlusMoins = Integer.parseInt(properties.getProperty("param.nbCasesRecherchePlusMoins"));
			nbCoupsMastermind = Integer.parseInt(properties.getProperty("param.nbCoupsMastermind"));
			nbCasesMastermind = Integer.parseInt(properties.getProperty("param.nbCasesMastermind"));
			nbChiffresAUtiliserMastermind = Integer
					.parseInt(properties.getProperty("param.nbChiffresAUtiliserMastermind"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try (InputStream input = new FileInputStream("src/main/resources/config.properties");
				OutputStream output = new FileOutputStream("src/main/resources/config.properties")) {
			properties.load(input);
			// Traitement pour le jeu RecherchePlusMoins
			properties.setProperty("param.nbCoupsRecherchePlusMoins", String.valueOf(nbCoupsRecherchePlusMoins));
			properties.setProperty("param.nbCasesRecherchePlusMoins", String.valueOf(nbCasesRecherchePlusMoins));
			
			// Traitement pour le jeu Mastermind
			properties.setProperty("param.nbCoupsMastermind", String.valueOf(nbCoupsMastermind));
			properties.setProperty("param.nbCasesMastermind", String.valueOf(nbCasesMastermind));
			properties.setProperty("param.nbChiffresAUtiliserMastermind", String.valueOf(nbChiffresAUtiliserMastermind));
			
			// Traitement pour le mode developpeur
			properties.setProperty("param.modeDeveloppeur", this.developerModeString);
			properties.store(output, "Fichier de configuration config.properties");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

	// Méthode permettant d'initialiser la barre d'outils de la fenêtre principale.
	private void initToolBar() {
		LOGGER.trace("Initialisation de la barre d'outils");

		toolbar.setBorder(BorderFactory.createEmptyBorder());
		newGameButton.setFocusable(false);
		exitButton.setFocusable(false);
		toolbar.add(newGameButton);
		toolbar.add(exitButton);
		toolbarConteneur.add(toolbar, BorderLayout.PAGE_START);
		newGameButton.addActionListener(new newGameListener());
		exitButton.addActionListener((e) -> {
			reinitializeConfigFile();
			System.exit(0);
		});
	}

	// Méthode permettant d'initialiser le menu de la fenêtre principale.
	private void initMenu() {
		LOGGER.trace("Initialisation du menu");

		// Définition des accélérateurs et mnémoniques
		nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));
		regles.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
		parametres.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
		fichier.setMnemonic('F');
		aPropos.setMnemonic('O');
		param.setMnemonic('P');

		// Construction du menu
		fichier.add(nouveau);
		fichier.addSeparator();
		fichier.add(quitter);
		aPropos.add(regles);
		param.add(parametres);
		menubar.add(fichier);
		menubar.add(aPropos);
		menubar.add(param);

		setJMenuBar(menubar);

		// Définition des listeners
		nouveau.addActionListener(new newGameListener());

		quitter.addActionListener((e) -> {
			reinitializeConfigFile();
			System.exit(0);
		});

		regles.addActionListener((e) -> {
			this.conteneur.removeAll();
			this.conteneur.add(new RulesPanel(this.size, this.searchModel).getPanel(), BorderLayout.CENTER);
			this.conteneur.revalidate();
			param.setEnabled(true);
		});

		parametres.addActionListener((e) -> {
			new BoiteDialogueParametrage(null, "Paramètres de jeu", true, developerMode);
		});

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				reinitializeConfigFile();
				System.exit(0);
			}
		});
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension dim = new Dimension(900, 600);
		LOGGER.debug("Largeur et hauteur de la fenêtre de l'application", dim);
		return dim;
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
		LOGGER.trace("Retour à l'accueil");
	}

	@Override
	public void exitApplication() {
		LOGGER.trace("Fin de l'application");
		reinitializeConfigFile();
		System.exit(0);
	}

	public class newGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LOGGER.trace("Initialisation de la boite dialogue de début de partie");
			BoiteDialogueDebutDePartie boite = new BoiteDialogueDebutDePartie(null, "Nouveau Jeu", true);
			readConfigFile();
			
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
					MasterMindChallengerPanel mcp = new MasterMindChallengerPanel(size, mastermindModel,
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
	
	private void readConfigFile() {
		// On récupère les données enregistrées dans le fichier config.properties
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
			LOGGER.debug("Menu Fichier -> Nouveau jeu - Nb coups RecherchePlusMoins :" + nbCoupsRecherchePlusMoins);
			LOGGER.debug("Menu Fichier -> Nouveau jeu - Nb cases RecherchePlusMoins :" + nbCasesRecherchePlusMoins);
			LOGGER.debug("Menu Fichier -> Nouveau jeu - Nb coups Mastermind :" + nbCoupsMastermind);
			LOGGER.debug("Menu Fichier -> Nouveau jeu - Nb cases Mastermind :" + nbCasesMastermind);
			LOGGER.debug("Menu Fichier -> Nouveau jeu - Nb chiffres utilisables Mastermind :"
					+ nbChiffresAUtiliserMastermind);
			LOGGER.debug("Menu Fichier -> Nouveau jeu - Etat du mode développeur :" + developerMode);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void reinitializeConfigFile() {
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
			
			// Traitement pour le mode developpeur
			properties.setProperty("param.modeDeveloppeur", "false");
			properties.store(output, "Fichier de configuration config.properties");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// Réinitialiser les modèles
	private void initModel() {
		this.searchModel = new SearchModel();
		this.mastermindModel = new MastermindModel();
		this.searchModel.addObserver(this);
		this.mastermindModel.addObserver(this);
		LOGGER.trace("Réinitialisation des modèles de données");
	}

}
