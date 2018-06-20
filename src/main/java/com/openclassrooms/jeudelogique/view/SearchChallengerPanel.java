package com.openclassrooms.jeudelogique.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.openclassrooms.jeudelogique.controller.SearchChallengerController;
import com.openclassrooms.jeudelogique.model.SearchModel;
import com.openclassrooms.jeudelogique.observer.Observer;
import com.openclassrooms.jeudelogique.utilities.RandomCombination;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Classe relative au jeu RecherchePlusMoins en mode challenger.
public class SearchChallengerPanel extends ZContainer implements Observer {
	private static final Logger LOGGER = LogManager.getLogger();

	private JLabel propositionLabel;
	private JFormattedTextField propositionTextField;
	private JTextArea storyTextArea;
	private JLabel nombreCoupLabel;
	private SearchChallengerController controller;

	private int nbCases, nbCoups;
	private String combinaisonSecrete = "";
	private boolean developerMode = false;

	private int nbCoupsConstant;

	private JLabel solution;

	public SearchChallengerPanel(Dimension dim, SearchModel mod, int nbCoups, int nbCases, boolean developerMode) {
		super(dim);
		LOGGER.trace("Instanciation du jeu RecherchePlusMoins en mode Challenger");

		this.controller = new SearchChallengerController(mod);
		this.nbCoups = nbCoups;
		this.nbCases = nbCases;
		this.nbCoupsConstant = nbCoups;
		this.developerMode = developerMode;
		initPanel();
	}

	@Override
	protected void initPanel() {
		Dimension dim = new Dimension(800, 50);

		JPanel northContent = new JPanel();
		northContent.setBackground(Color.WHITE);
		northContent.setPreferredSize(dim);
		JLabel welcomeMessage = new JLabel("recherche +/- | mode challenger".toUpperCase());
		welcomeMessage.setHorizontalAlignment(JLabel.CENTER);
		welcomeMessage.setFont(comics30);
		welcomeMessage.setForeground(Color.decode("#ee5100"));
		northContent.add(welcomeMessage);

		JPanel centerContent = new JPanel();
		centerContent.setBackground(Color.WHITE);
		centerContent.setPreferredSize(new Dimension(800, 400));

		JTextArea texte = new JTextArea("Saurez-vous trouver la combinaison cachée en moins de " + nbCoupsConstant
				+ " coups?\n(Chiffres compris entre 0 et 9 avec répétitions possibles)\n+ : Chiffre plus grand\t - : Chiffre plus petit\t= : Bon chiffre");
		texte.setEditable(false);
		texte.setFocusable(false);
		texte.setPreferredSize(new Dimension(700, 55));
		texte.setFont(arial15);
		texte.setForeground(Color.BLUE);
		centerContent.add(texte);

		combinaisonSecrete = RandomCombination.generateRandomCombination(this.nbCases);
		LOGGER.debug("Jeu RecherchePlusMoins en mode Challenger - Génération de la combinaison secrète:"
				+ combinaisonSecrete);
		controller.setCombinaisonSecrete(combinaisonSecrete);

		if (this.developerMode == true) {
			solution = new JLabel("Solution : " + combinaisonSecrete);
			solution.setPreferredSize(new Dimension(200, 55));
			solution.setFont(arial15);
			solution.setForeground(Color.RED);
			texte.setPreferredSize(new Dimension(500, 55));
			texte.setText("Saurez-vous trouver la combinaison cachée en moins de " + nbCoupsConstant
					+ " coups?\n(Chiffres compris entre 0 et 9 avec répétitions possibles)\n+ : Chiffre plus grand - : Chiffre plus petit = : Bon chiffre ");
			centerContent.add(solution);
		}

		propositionLabel = new JLabel("Entrez les " + this.nbCases + " chiffres de votre proposition :");
		propositionLabel.setHorizontalAlignment(JLabel.LEFT);
		propositionLabel.setPreferredSize(new Dimension(300, 50));
		propositionLabel.setFont(arial15);
		centerContent.add(propositionLabel);
		
		//Mise en place du JFormattedTextField suivant le nombre de cases choisies.
		try {
			switch (this.nbCases) {
			case 4:
				MaskFormatter maskFormatter = new MaskFormatter("####");
				propositionTextField = new JFormattedTextField(maskFormatter);
				break;
			case 5:
				MaskFormatter maskFormatter2 = new MaskFormatter("#####");
				propositionTextField = new JFormattedTextField(maskFormatter2);
				break;
			case 6:
				MaskFormatter maskFormatter3 = new MaskFormatter("######");
				propositionTextField = new JFormattedTextField(maskFormatter3);
				break;
			case 7:
				MaskFormatter maskFormatter4 = new MaskFormatter("#######");
				propositionTextField = new JFormattedTextField(maskFormatter4);
				break;
			case 8:
				MaskFormatter maskFormatter5 = new MaskFormatter("########");
				propositionTextField = new JFormattedTextField(maskFormatter5);
				break;
			case 9:
				MaskFormatter maskFormatter6 = new MaskFormatter("#########");
				propositionTextField = new JFormattedTextField(maskFormatter6);
				break;
			case 10:
				MaskFormatter maskFormatter7 = new MaskFormatter("##########");
				propositionTextField = new JFormattedTextField(maskFormatter7);
				break;
			default:
				LOGGER.error(
						"Jeu RecherchePlusMoins en mode Challenger - Erreur d'initialisation pour le JFormattedTextField");
				break;
			}
		} catch (ParseException e) {
			LOGGER.error("Jeu RecherchePlusMoins en mode Challenger -" + e.getMessage());
		}
		propositionTextField.setPreferredSize(new Dimension(300, 30));
		propositionTextField.setHorizontalAlignment(JTextField.CENTER);
		propositionTextField.setForeground(Color.BLUE);
		propositionTextField.setFont(arial15);
		propositionTextField.requestFocusInWindow();
		centerContent.add(propositionTextField);

		storyTextArea = new JTextArea();
		storyTextArea.setBackground(Color.decode("#eeeeee"));
		storyTextArea.setEditable(false);
		storyTextArea.setFocusable(false);
		storyTextArea.setFont(arial15);
		storyTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		storyTextArea.setPreferredSize(new Dimension(540, 275));
		storyTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerContent.add(storyTextArea);

		JPanel southContent = new JPanel();
		southContent.setBackground(Color.WHITE);
		southContent.setPreferredSize(dim);
		nombreCoupLabel = new JLabel("Nombre de coups restants : " + this.nbCoups);
		nombreCoupLabel.setForeground(Color.decode("#51b46d"));
		nombreCoupLabel.setPreferredSize(new Dimension(800, 20));
		nombreCoupLabel.setHorizontalAlignment(JLabel.CENTER);
		nombreCoupLabel.setFont(arial15);
		southContent.add(nombreCoupLabel);

		this.panel.setBackground(Color.WHITE);
		this.panel.add(northContent, BorderLayout.NORTH);
		this.panel.add(centerContent, BorderLayout.CENTER);
		this.panel.add(southContent, BorderLayout.SOUTH);
		
		// Définition des listeners
		propositionTextField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nbCoups--;
				controller.setMode("CHALLENGER");
				controller.setProposition(((JTextField) e.getSource()).getText());
			}
		});
	}

	public void gestionFinDePartie(String reponse) {
		if (reponse.matches("[=]*") && this.nbCoups > 0) {
			LOGGER.trace("Jeu RecherchePlusMoins en mode Challenger - Fin de partie");
			JOptionPane.showMessageDialog(null, "Bravo, vous avez trouvé la combinaison secrète "
					+ this.combinaisonSecrete + " en " + this.getNbEssais() + " coups.", "Fin de partie",
					JOptionPane.INFORMATION_MESSAGE);
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
			LOGGER.trace("Jeu RecherchePlusMoins en mode Challenger - Fin de partie");
			JOptionPane.showMessageDialog(null, "Désolé, vous avez perdu!\n" + "La bonne combinaison était "
					+ this.combinaisonSecrete + "\nRetentez votre chance !", "Fin de partie",
					JOptionPane.INFORMATION_MESSAGE);
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
		return nbCoupsConstant - this.nbCoups;
	}
	
	// Implémentation du pattern Observer

	@Override
	public void update(String proposition, String reponse) {
		this.propositionTextField.setText("");
		this.storyTextArea.append(proposition + "\t\t:\t\t" + reponse + "\n");
		this.nombreCoupLabel.setText("Nombre de coups restants : " + this.nbCoups);
		this.gestionFinDePartie(reponse);
	}

	@Override
	public void restart() {
		LOGGER.trace("Jeu RecherchePlusMoins en mode Challenger - Partie rejouée");
		this.storyTextArea.setText("");
		this.nombreCoupLabel.setText("Nombre de coups restants : " + this.nbCoupsConstant);
		this.nbCoups = this.nbCoupsConstant;
		this.combinaisonSecrete = RandomCombination.generateRandomCombination(this.nbCases);
		if (developerMode) {
			solution.setText("Solution : " + this.combinaisonSecrete);
		}
		controller.setCombinaisonSecrete(this.combinaisonSecrete);
	}

	@Override
	public void accueil() {
	}

	@Override
	public void exitApplication() {
	}

	@Override
	public void updateModeDuel(String proposition, String reponse, String combiSecrete) {
	}

}
