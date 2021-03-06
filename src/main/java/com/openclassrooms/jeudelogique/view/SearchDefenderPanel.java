package com.openclassrooms.jeudelogique.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.openclassrooms.jeudelogique.controller.SearchDefenderController;
import com.openclassrooms.jeudelogique.model.SearchModel;
import com.openclassrooms.jeudelogique.observer.Observer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchDefenderPanel extends ZContainer implements Observer {
	private JLabel combinaisonLabel;
	private JFormattedTextField combinaisonTextField;
	private JButton validerButton;
	private JLabel propOrdinateurLabel;
	private JLabel propositionOrdinateurLabel;
	private JLabel repOrdinateurLabel;
	private JLabel reponseOrdiLabel;
	private JButton passerButton;
	private JTextArea storyTextArea;
	private JLabel nombreCoupLabel;
	private SearchDefenderController controller;

	private int nbCases, nbCoups;
	private int activerBoutonValiderCombiSecrete;
	private String combinaisonSecreteModeDefenseur;

	private int nbCoupsConstant;

	private static final Logger LOGGER = LogManager.getLogger();

	public SearchDefenderPanel(Dimension dim, SearchModel mod, int nbCoups, int nbCases) {
		super(dim);
		LOGGER.trace("Instanciation du jeu RecherchePlusMoins en mode Défenseur");

		this.controller = new SearchDefenderController(mod);
		this.nbCoups = nbCoups;
		this.nbCoupsConstant = nbCoups;
		this.nbCases = nbCases;
		initPanel();
	}

	@Override
	protected void initPanel() {
		JPanel northContent = new JPanel();
		northContent.setBackground(Color.WHITE);
		northContent.setPreferredSize(new Dimension(800, 45));
		JLabel welcomeMessage = new JLabel("recherche +/- | mode defenseur".toUpperCase());
		welcomeMessage.setHorizontalAlignment(JLabel.CENTER);
		welcomeMessage.setFont(comics30);
		welcomeMessage.setForeground(Color.decode("#ee5100"));
		northContent.add(welcomeMessage);

		JPanel centerContent = new JPanel();
		centerContent.setPreferredSize(new Dimension(800, 410));
		centerContent.setBackground(Color.WHITE);

		JTextArea texte = new JTextArea("L'ordinateur a ".toUpperCase() + nbCoupsConstant
				+ " essais pour trouver votre combinaison secrète.".toUpperCase()
				+ "\n+ : Chiffre plus grand\t - : Chiffre plus petit\t= : Bon chiffre");
		texte.setForeground(Color.BLUE);
		texte.setPreferredSize(new Dimension(800, 50));
		texte.setFont(arial15);
		texte.setEditable(false);
		texte.setFocusable(false);
		centerContent.add(texte);

		combinaisonLabel = new JLabel("Veuillez saisir votre combinaison secrète (" + this.nbCases + " chiffres) : ");
		combinaisonLabel.setHorizontalAlignment(JLabel.LEFT);
		combinaisonLabel.setPreferredSize(new Dimension(410, 25));
		combinaisonLabel.setFont(arial15);
		centerContent.add(combinaisonLabel);

		try {
			switch (this.nbCases) {
			case 4:
				MaskFormatter maskFormatter = new MaskFormatter("####");
				combinaisonTextField = new JFormattedTextField(maskFormatter);
				break;
			case 5:
				MaskFormatter maskFormatter2 = new MaskFormatter("#####");
				combinaisonTextField = new JFormattedTextField(maskFormatter2);
				break;
			case 6:
				MaskFormatter maskFormatter3 = new MaskFormatter("######");
				combinaisonTextField = new JFormattedTextField(maskFormatter3);
				break;
			case 7:
				MaskFormatter maskFormatter4 = new MaskFormatter("#######");
				combinaisonTextField = new JFormattedTextField(maskFormatter4);
				break;
			case 8:
				MaskFormatter maskFormatter5 = new MaskFormatter("########");
				combinaisonTextField = new JFormattedTextField(maskFormatter5);
				break;
			case 9:
				MaskFormatter maskFormatter6 = new MaskFormatter("#########");
				combinaisonTextField = new JFormattedTextField(maskFormatter6);
				break;
			case 10:
				MaskFormatter maskFormatter7 = new MaskFormatter("##########");
				combinaisonTextField = new JFormattedTextField(maskFormatter7);
				break;
			default:
				LOGGER.error(
						"Jeu RecherchePlusMoins en mode Défenseur - Erreur d'initialisation des JFormattedTextField");
				break;
			}
		} catch (ParseException e) {
			LOGGER.error("Jeu RecherchePlusMoins en mode Défenseur -" + e.getMessage());
		}
		combinaisonTextField.setPreferredSize(new Dimension(150, 25));
		combinaisonTextField.setHorizontalAlignment(JTextField.CENTER);
		combinaisonTextField.setForeground(Color.BLUE);
		combinaisonTextField.setFont(arial15);
		combinaisonTextField.requestFocusInWindow();
		centerContent.add(combinaisonTextField);

		validerButton = new JButton("Valider");
		validerButton.setPreferredSize(new Dimension(100, 25));
		validerButton.setEnabled(false);
		centerContent.add(validerButton);

		propOrdinateurLabel = new JLabel(" Proposition de l'ordinateur:");
		propOrdinateurLabel.setPreferredSize(new Dimension(200, 35));
		propOrdinateurLabel.setHorizontalAlignment(JLabel.LEFT);
		propOrdinateurLabel.setFont(arial15);
		centerContent.add(propOrdinateurLabel);

		propositionOrdinateurLabel = new JLabel("");
		propositionOrdinateurLabel.setPreferredSize(new Dimension(95, 25));
		propositionOrdinateurLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		propositionOrdinateurLabel.setHorizontalAlignment(JTextField.CENTER);
		propositionOrdinateurLabel.setForeground(Color.BLUE);
		propositionOrdinateurLabel.setFont(arial14);
		centerContent.add(propositionOrdinateurLabel);

		repOrdinateurLabel = new JLabel(" Réponse correspondante:");
		repOrdinateurLabel.setPreferredSize(new Dimension(192, 35));
		repOrdinateurLabel.setHorizontalAlignment(JLabel.LEFT);
		repOrdinateurLabel.setFont(arial15);
		centerContent.add(repOrdinateurLabel);

		reponseOrdiLabel = new JLabel("");
		reponseOrdiLabel.setPreferredSize(new Dimension(90, 25));
		reponseOrdiLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		reponseOrdiLabel.setHorizontalAlignment(JTextField.CENTER);
		reponseOrdiLabel.setForeground(Color.BLUE);
		reponseOrdiLabel.setFont(arial14);
		centerContent.add(reponseOrdiLabel);

		passerButton = new JButton("Passer");
		passerButton.setPreferredSize(new Dimension(80, 25));
		passerButton.setEnabled(false);
		centerContent.add(passerButton);

		storyTextArea = new JTextArea();
		storyTextArea.setBackground(Color.decode("#eeeeee"));
		storyTextArea.setEditable(false);
		storyTextArea.setFocusable(false);
		storyTextArea.setFont(arial15);
		storyTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		storyTextArea.setPreferredSize(new Dimension(560, 275));
		storyTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerContent.add(storyTextArea);

		JPanel southContent = new JPanel();
		southContent.setBackground(Color.WHITE);
		southContent.setPreferredSize(new Dimension(800, 30));
		nombreCoupLabel = new JLabel("Nombre de coups restants : " + this.nbCoups);
		nombreCoupLabel.setForeground(Color.decode("#51b46d"));
		nombreCoupLabel.setHorizontalAlignment(JLabel.CENTER);
		nombreCoupLabel.setFont(arial15);
		southContent.add(nombreCoupLabel, BorderLayout.NORTH);

		this.panel.setBackground(Color.WHITE);
		this.panel.add(northContent, BorderLayout.NORTH);
		this.panel.add(centerContent, BorderLayout.CENTER);
		this.panel.add(southContent, BorderLayout.SOUTH);

		combinaisonTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				for (int i = 0; i < nbCases; i++) {
					if (combinaisonTextField.getText().charAt(i) != ' ') {
						activerBoutonValiderCombiSecrete++;
					}
				}
				if (activerBoutonValiderCombiSecrete == nbCases) {
					validerButton.setEnabled(true);
					validerButton.requestFocusInWindow();
					activerBoutonValiderCombiSecrete = 0;
				} else {
					activerBoutonValiderCombiSecrete = 0;
					validerButton.setEnabled(false);
				}
			}
		});

		validerButton.addActionListener((e) -> {
			combinaisonTextField.setEnabled(false);
			validerButton.setEnabled(false);
			passerButton.setEnabled(true);
			passerButton.requestFocusInWindow();
			this.nbCoups--;
			this.combinaisonSecreteModeDefenseur = this.combinaisonTextField.getText();
			LOGGER.debug("Jeu RecherchePlusMoins en mode Défenseur - Définition de la combinaison secrète du joueur:"
					+ combinaisonSecreteModeDefenseur);
			this.controller.setMode("DEFENSEUR");
			this.controller.setCombinaisonSecreteModeDefenseur(combinaisonTextField.getText());
			this.gestionFinDePartie(this.reponseOrdiLabel.getText());
		});

		passerButton.addActionListener((e) -> {
			this.nbCoups--;
			this.controller.setMode("DEFENSEUR");
			this.controller.genererPropositionOrdinateurModeDefenseur();
			this.gestionFinDePartie(this.reponseOrdiLabel.getText());
		});

	}

	public void gestionFinDePartie(String reponse) {
		if (reponse.matches("[=]*") && this.nbCoups >= 0) {
			LOGGER.trace("Jeu RecherchePlusMoins en mode Défenseur - Fin de partie");
			JOptionPane.showMessageDialog(null,
					"PERDU!\nL'IA a trouvé votre combinaison secrète " + this.combinaisonSecreteModeDefenseur
							+ " en moins de " + this.nbCoupsConstant + " coups.",
					"Fin de partie", JOptionPane.INFORMATION_MESSAGE);
			String[] choix = { "Rejouer", "Revenir au menu", "Quitter" };
			int rang = JOptionPane.showOptionDialog(null, "Voulez-vous rejouer?", "Rejouer",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);
			switch (choix[rang]) {
			case "Rejouer":
				controller.setChoixFinDePartie("Rejouer");
				break;
			case "Revenir au menu":
				controller.setChoixFinDePartie("Revenir au menu");
				break;
			case "Quitter":
				controller.setChoixFinDePartie("Quitter");
				break;
			default:
				break;
			}
		}
		if ((!reponse.matches("[=]*")) && this.nbCoups <= 0) {
			LOGGER.trace("Jeu RecherchePlusMoins en mode Défenseur - Fin de partie");
			JOptionPane.showMessageDialog(null,
					"Vous avez gagné!\n" + "Votre combinaison secrète " + this.combinaisonSecreteModeDefenseur
							+ " n'a pas été trouvée par l'ordinateur en moins de " + this.nbCoupsConstant + " coups.",
					"Fin de partie", JOptionPane.INFORMATION_MESSAGE);
			String[] choix = { "Rejouer", "Revenir au menu", "Quitter" };
			int rang = JOptionPane.showOptionDialog(null, "Voulez-vous rejouer?", "Rejouer",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);
			switch (choix[rang]) {
			case "Rejouer":
				controller.setChoixFinDePartie("Rejouer");
				break;
			case "Revenir au menu":
				controller.setChoixFinDePartie("Revenir au menu");
				break;
			case "Quitter":
				controller.setChoixFinDePartie("Quitter");
				break;
			default:
				break;
			}
		}
	}

	public int getNbEssais() {
		return nbCoupsConstant - nbCoups;
	}

	@Override
	public void updateModeDuel(String proposition, String reponse, String combiSecrete) {
	}

	@Override
	public void update(String proposition, String reponse) {
		this.storyTextArea.append(proposition + "\t\t:\t\t" + reponse + "\n");
		this.propositionOrdinateurLabel.setText(proposition);
		this.reponseOrdiLabel.setText(reponse);
		this.nombreCoupLabel.setText("Nombre de coups restants : " + this.nbCoups);
	}

	@Override
	public void restart() {
		LOGGER.trace("Jeu RecherchePlusMoins en mode Défenseur - Partie relancée");
		this.storyTextArea.setText("");
		this.nbCoups = nbCoupsConstant;
		this.nombreCoupLabel.setText("Nombre de coups restants : " + this.nbCoups);
		this.propositionOrdinateurLabel.setText("");
		this.reponseOrdiLabel.setText("");
		this.passerButton.setEnabled(false);
		this.combinaisonTextField.setText("");
		this.combinaisonTextField.setEnabled(true);
	}

	@Override
	public void accueil() {

	}

	@Override
	public void exitApplication() {

	}

}
