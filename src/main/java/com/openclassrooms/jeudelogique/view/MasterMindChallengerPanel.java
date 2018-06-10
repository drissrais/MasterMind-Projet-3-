package com.openclassrooms.jeudelogique.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import com.openclassrooms.jeudelogique.controler.MastermindChallengerControler;
import com.openclassrooms.jeudelogique.model.MastermindModel;
import com.openclassrooms.jeudelogique.observer.Observer;
import com.openclassrooms.jeudelogique.utilities.RandomCombination;

public class MastermindChallengerPanel extends ZContainer implements Observer {
	private JLabel propositionLabel;
	private JFormattedTextField propositionTextField;
	private JTextArea storyTextArea;
	private JLabel nombreCoupLabel, solution;
	private MastermindChallengerControler controler;

	private int nbCases, nbCoups, nbChiffresAUtiliser;
	private String combinaisonSecrete = "";
	private boolean developerMode;

	private int nbCoupsConstant;

	public MastermindChallengerPanel(Dimension dim, MastermindModel mod, int nbCoups, int nbCases,
			int nbChiffresAUtiliser, boolean developerMode) {
		super(dim);
		this.controler = new MastermindChallengerControler(mod);
		this.nbCoups = nbCoups;
		this.nbCoupsConstant = nbCoups;
		this.nbCases = nbCases;
		this.nbChiffresAUtiliser = nbChiffresAUtiliser;
		this.developerMode = developerMode;
		initPanel();
	}

	@Override
	protected void initPanel() {
		Dimension dim = new Dimension(800, 50);

		JPanel northContent = new JPanel();
		northContent.setBackground(Color.WHITE);
		northContent.setPreferredSize(dim);
		JLabel welcomeMessage = new JLabel("mastermind | mode challenger".toUpperCase());
		welcomeMessage.setHorizontalAlignment(JLabel.CENTER);
		welcomeMessage.setFont(comics30);
		welcomeMessage.setForeground(Color.decode("#ee5100"));
		welcomeMessage.setFocusable(false);
		northContent.add(welcomeMessage);

		JPanel centerContent = new JPanel();
		centerContent.setPreferredSize(new Dimension(800, 400));
		centerContent.setBackground(Color.white);

		JTextArea texte = new JTextArea("Saurez-vous trouver la combinaison cachée en moins de " + nbCoupsConstant
				+ " coups?\n(Chiffres compris entre 0 et " + (this.nbChiffresAUtiliser - 1)
				+ "  avec répétitions possibles)\nO : Chiffre mal placé ; # : Chiffre bien placé");
		texte.setEditable(false);
		texte.setFocusable(false);
		texte.setPreferredSize(new Dimension(700, 55));
		texte.setFont(arial15);
		texte.setForeground(Color.BLUE);
		centerContent.add(texte);
		
		for (int i = 0; i < this.nbCases; i++) {
			Random randomGenerator = new Random();
			this.combinaisonSecrete += randomGenerator.nextInt(this.nbChiffresAUtiliser);
		}
		controler.setNbChiffresAUtiliser(this.nbChiffresAUtiliser);
		controler.setCombinaisonSecrete(combinaisonSecrete);
		
		if (this.developerMode == true) {
			solution = new JLabel("Solution : " + this.combinaisonSecrete);
			solution.setPreferredSize(new Dimension(150, 50));
			solution.setFont(arial15);
			solution.setForeground(Color.RED);
			texte.setPreferredSize(new Dimension(550, 55));
			texte.setAlignmentX(SwingConstants.LEFT);
			texte.setText(
					"Saurez-vous trouver la combinaison cachée en moins de " + nbCoupsConstant
					+ " coups?\n(Chiffres compris entre 0 et " + (this.nbChiffresAUtiliser - 1)
					+ "  avec répétitions possibles)\nO : Chiffre mal placé ; # : Chiffre bien placé");
			centerContent.add(solution);
		}

		propositionLabel = new JLabel("Entrez les " + this.nbCases + " chiffres de votre proposition :");
		propositionLabel.setHorizontalAlignment(JLabel.LEFT);
		propositionLabel.setPreferredSize(new Dimension(300, 50));
		propositionLabel.setFont(arial15);
		centerContent.add(propositionLabel);

		propositionTextField = new JFormattedTextField();
		MaskFormatter maskFormatter;
		try {
			switch (this.nbCases) {
			case 4:
				maskFormatter = new MaskFormatter("####");
				break;
			case 5:
				maskFormatter = new MaskFormatter("#####");
				break;
			case 6:
				maskFormatter = new MaskFormatter("######");
				break;
			case 7:
				maskFormatter = new MaskFormatter("#######");
				break;
			case 8:
				maskFormatter = new MaskFormatter("########");
				break;
			case 9:
				maskFormatter = new MaskFormatter("#########");
				break;
			case 10:
				maskFormatter = new MaskFormatter("##########");
				break;
			default:
				maskFormatter = new MaskFormatter("####");
				break;
			}
			propositionTextField = new JFormattedTextField(maskFormatter);
		} catch (ParseException e) {
			e.printStackTrace();
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
		storyTextArea.setPreferredSize(new Dimension(590, 275));
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

		propositionTextField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nbCoups--;
				controler.setMode("CHALLENGER");
				// controler.setNbChiffresAUtiliser(nbChiffresAUtiliser);
				controler.setProposition(((JTextField) e.getSource()).getText());
			}
		});
	}

	public int getNbEssais() {
		return nbCoupsConstant - this.nbCoups;
	}

	public void gestionFinDePartie(String reponse) {
		if (reponse.matches("[#]*") && reponse.length() == this.nbCases && this.nbCoups > 0) {
			JOptionPane.showMessageDialog(null, "Bravo, vous avez trouvé la combinaison secrète "
					+ this.combinaisonSecrete + " en " + this.getNbEssais() + " coups.", "Fin de partie",
					JOptionPane.INFORMATION_MESSAGE);
			String[] choix = { "Rejouer", "Revenir au menu", "Quitter" };
			int rang = JOptionPane.showOptionDialog(null, "Voulez-vous rejouer?", "Rejouer",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);
			switch (choix[rang]) {
			case "Rejouer":
				controler.setChoixFinDePartie("Rejouer");
				break;
			case "Revenir au menu":
				controler.setChoixFinDePartie("Revenir au menu");
				break;
			case "Quitter":
				controler.setChoixFinDePartie("Quitter");
				break;
			default:
				break;
			}
		}
		if ((!reponse.matches("[#]*")) && reponse.length() != this.nbCases && this.nbCoups <= 0) {
			JOptionPane.showMessageDialog(null,
					"Perdu!\n" + "La bonne combinaison était " + this.combinaisonSecrete + "\nRetentez votre chance !",
					"Fin de partie", JOptionPane.INFORMATION_MESSAGE);
			String[] choix = { "Rejouer", "Revenir au menu", "Quitter" };
			int rang = JOptionPane.showOptionDialog(null, "Voulez-vous rejouer?", "Rejouer",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);
			switch (choix[rang]) {
			case "Rejouer":
				controler.setChoixFinDePartie("Rejouer");
				break;
			case "Revenir au menu":
				controler.setChoixFinDePartie("Revenir au menu");
				break;
			case "Quitter":
				controler.setChoixFinDePartie("Quitter");
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void update(String proposition, String reponse) {
		this.propositionTextField.setText("");
		this.storyTextArea.append(proposition + "\t\t:\t\t" + reponse + "\n");
		this.nombreCoupLabel.setText("Nombre de coups restants : " + this.nbCoups);
		this.gestionFinDePartie(reponse);
	}

	@Override
	public void restart() {
		this.storyTextArea.setText("");
		this.nombreCoupLabel.setText("Nombre de coups restants : " + this.nbCoupsConstant);
		this.nbCoups = this.nbCoupsConstant;
		this.combinaisonSecrete = RandomCombination.generateRandomCombination(this.nbCases);
		if (developerMode) {
			solution.setText("Solution : " + this.combinaisonSecrete);
		}
		controler.setCombinaisonSecrete(this.combinaisonSecrete);
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
