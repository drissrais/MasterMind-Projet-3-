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

import com.openclassrooms.jeudelogique.controler.MastermindDefenderControler;
import com.openclassrooms.jeudelogique.model.MastermindModel;
import com.openclassrooms.jeudelogique.observer.Observer;

public class MastermindDefenderPanel extends ZContainer implements Observer {
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
	private MastermindDefenderControler controler;

	private int nbCases, nbCoups, nbChiffresAUtiliser;
	private int activerBoutonValiderCombiSecrete;
	private String combinaisonSecreteModeDefenseur;
	
	private int nbCoupsConstant;
	
	public MastermindDefenderPanel(Dimension dim, MastermindModel mod, int nbCoups, int nbCases, int nbChiffresAUtiliser) {
		super(dim);
		this.controler = new MastermindDefenderControler(mod);
		this.nbCoups = nbCoups;
		this.nbCoupsConstant = nbCoups;
		this.nbCases = nbCases;
		this.nbChiffresAUtiliser = nbChiffresAUtiliser;
		initPanel();
	}
	
	@Override
	protected void initPanel() {
		JPanel northContent = new JPanel();
		northContent.setPreferredSize(new Dimension(800, 45));
		northContent.setBackground(Color.WHITE);
		JLabel welcomeMessage = new JLabel("mastermind | mode defenseur".toUpperCase());
		welcomeMessage.setHorizontalAlignment(JLabel.CENTER);
		welcomeMessage.setFont(comics30);
		welcomeMessage.setForeground(Color.decode("#ee5100"));
		northContent.add(welcomeMessage);

		JPanel centerContent = new JPanel();
		centerContent.setPreferredSize(new Dimension(800, 410));
		centerContent.setBackground(Color.WHITE);

		JTextArea texte = new JTextArea("L'ordinateur a ".toUpperCase() + this.nbCoups
				+ " essais pour trouver votre combinaison secrète.".toUpperCase()
				+ "\n# : Chiffre bien placé\t O : Chiffre mal placé");
		texte.setForeground(Color.BLUE);
		texte.setPreferredSize(new Dimension(800, 50));
		texte.setFont(arial15);
		texte.setEditable(false);
		texte.setFocusable(false);
		centerContent.add(texte);

		combinaisonLabel = new JLabel("Veuillez saisir votre combinaison secrète (" + this.nbCases + " chiffres) : ");
		combinaisonLabel.setHorizontalAlignment(JLabel.LEFT);
		combinaisonLabel.setPreferredSize(new Dimension(415, 25));
		combinaisonLabel.setFont(arial15);
		centerContent.add(combinaisonLabel);

		combinaisonTextField = new JFormattedTextField();
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
			combinaisonTextField = new JFormattedTextField(maskFormatter);
		} catch (ParseException e) {
			e.printStackTrace();
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

		propOrdinateurLabel = new JLabel("Proposition de l'ordinateur:");
		propOrdinateurLabel.setPreferredSize(new Dimension(200, 35));
		propOrdinateurLabel.setHorizontalAlignment(JLabel.LEFT);
		propOrdinateurLabel.setFont(arial15);
		centerContent.add(propOrdinateurLabel);

		propositionOrdinateurLabel = new JLabel("");
		propositionOrdinateurLabel.setPreferredSize(new Dimension(95, 25));
		propositionOrdinateurLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		propositionOrdinateurLabel.setHorizontalAlignment(JTextField.CENTER);
		propositionOrdinateurLabel.setForeground(Color.BLUE);
		propositionOrdinateurLabel.setFont(arial15);
		centerContent.add(propositionOrdinateurLabel);
		
		repOrdinateurLabel = new JLabel("Réponse correspondante :");
		repOrdinateurLabel.setPreferredSize(new Dimension(192, 35));
		repOrdinateurLabel.setHorizontalAlignment(JLabel.LEFT);
		repOrdinateurLabel.setFont(arial15);
		centerContent.add(repOrdinateurLabel);
		
		reponseOrdiLabel = new JLabel("");
		reponseOrdiLabel.setPreferredSize(new Dimension(90, 25));
		reponseOrdiLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		reponseOrdiLabel.setHorizontalAlignment(JTextField.CENTER);
		reponseOrdiLabel.setForeground(Color.BLUE);
		reponseOrdiLabel.setFont(arial15);
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
		storyTextArea.setPreferredSize(new Dimension(580, 275));
		storyTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerContent.add(storyTextArea);

		JPanel southContent = new JPanel();
		southContent.setPreferredSize(new Dimension(800, 30));
		southContent.setBackground(Color.WHITE);
		nombreCoupLabel = new JLabel("Nombre de coups restants : " + this.nbCoups);
		nombreCoupLabel.setForeground(Color.decode("#51b46d"));
		nombreCoupLabel.setHorizontalAlignment(JLabel.CENTER);
		nombreCoupLabel.setFont(arial15);
		southContent.add(nombreCoupLabel);
		
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
			this.controler.setMode("DEFENSEUR");
			this.controler.setNbCases(this.nbCases);
			this.controler.setNbChiffresAUtiliser(this.nbChiffresAUtiliser);
			this.controler.setCombinaisonSecreteModeDefenseur(combinaisonTextField.getText());
			this.gestionFinDePartie(this.reponseOrdiLabel.getText());
		});
		
		passerButton.addActionListener((e) -> {
			this.nbCoups--;
			this.controler.setMode("DEFENSEUR");
			this.controler.genererPropositionOrdinateurModeDefenseur();
			this.gestionFinDePartie(this.reponseOrdiLabel.getText());
		});
	}
	
	public int getNbEssais() {
		return nbCoupsConstant - nbCoups;
	}
	
	public void gestionFinDePartie(String reponse) {
		if (reponse.matches("[#]*") && reponse.length() == this.nbCases && this.nbCoups >= 0) {
			JOptionPane.showMessageDialog(null, "PERDU!\nL'IA a trouvé votre combinaison secrète "
					+ this.combinaisonSecreteModeDefenseur + " en moins de " + this.nbCoupsConstant + " coups.", "Fin de partie",
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
			JOptionPane.showMessageDialog(null, "Vous avez gagné!\n" + "Votre combinaison secrète "
					+ this.combinaisonSecreteModeDefenseur + " n'a pas été trouvée par l'IA.", "Fin de partie",
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
	}

	@Override
	public void update(String proposition, String reponse) {
		this.storyTextArea.append(proposition + "\t\t:\t\t" + reponse + "\n");
		this.propositionOrdinateurLabel.setText(proposition);
		this.reponseOrdiLabel.setText(reponse);
		this.nombreCoupLabel.setText("Nombre de coups restants : " + this.nbCoups);
	}

	@Override
	public void updateModeDuel(String proposition, String reponse, String combiSecrete) {
	}

	@Override
	public void restart() {
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
