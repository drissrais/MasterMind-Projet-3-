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

import com.openclassrooms.jeudelogique.controler.SearchChallengerControler;
import com.openclassrooms.jeudelogique.model.SearchModel;
import com.openclassrooms.jeudelogique.observer.Observer;
import com.openclassrooms.jeudelogique.utilities.RandomCombination;

public class SearchChallengerPanel extends ZContainer implements Observer {
	private JLabel propositionLabel;
	private JFormattedTextField propositionTextField;
	private JTextArea storyTextArea;
	private JLabel nombreCoupLabel;
	private SearchChallengerControler controler;

	private int nbCases = 4, nbEssais = 10;
	private String combinaisonSecrete = "";

	public SearchChallengerPanel(Dimension dim, SearchModel mod) {
		super(dim);
		this.controler = new SearchChallengerControler(mod);
		initPanel();
	}

	@Override
	protected void initPanel() {
		Dimension dim = new Dimension(800, 50);

		JPanel northContent = new JPanel();
		northContent.setPreferredSize(dim);
		JLabel welcomeMessage = new JLabel("recherche +/- | challenger mode".toUpperCase());
		welcomeMessage.setHorizontalAlignment(JLabel.CENTER);
		welcomeMessage.setFont(comics30);
		welcomeMessage.setForeground(Color.decode("#ee5100"));
		northContent.add(welcomeMessage);

		JPanel centerContent = new JPanel();
		centerContent.setPreferredSize(new Dimension(800, 400));

		JTextArea texte = new JTextArea(
				"Saurez-vous trouver la combinaison cachée en moins de 10 coups?\n(Chiffres compris entre 0 et 9 avec répétitions possibles)\n+ : Chiffre plus grand\t - : Chiffre plus petit\t= : Bon chiffre");
		texte.setEditable(false);
		texte.setFocusable(false);
		texte.setBackground(Color.decode("#eeeeee"));
		texte.setPreferredSize(new Dimension(700, 55));
		texte.setFont(arial);
		texte.setForeground(Color.BLUE);
		centerContent.add(texte);

		propositionLabel = new JLabel("Entrez les 4 chiffres de votre proposition :");
		propositionLabel.setHorizontalAlignment(JLabel.LEFT);
		propositionLabel.setPreferredSize(new Dimension(300, 50));
		propositionLabel.setFont(arial);
		centerContent.add(propositionLabel);

		propositionTextField = new JFormattedTextField();
		try {
			MaskFormatter maskFormatter = new MaskFormatter("####");
			propositionTextField = new JFormattedTextField(maskFormatter);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		propositionTextField.setPreferredSize(new Dimension(300, 30));
		propositionTextField.setHorizontalAlignment(JTextField.CENTER);
		propositionTextField.setForeground(Color.BLUE);
		propositionTextField.setFont(arial);
		propositionTextField.requestFocusInWindow();
		centerContent.add(propositionTextField);

		storyTextArea = new JTextArea();
		storyTextArea.setEditable(false);
		storyTextArea.setFocusable(false);
		storyTextArea.setFont(arial);
		storyTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		storyTextArea.setPreferredSize(new Dimension(270, 275));
		storyTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerContent.add(storyTextArea);

		JPanel southContent = new JPanel();
		southContent.setPreferredSize(dim);
		nombreCoupLabel = new JLabel("Nombre de coups restants : 10");
		nombreCoupLabel.setPreferredSize(new Dimension(800, 20));
		nombreCoupLabel.setHorizontalAlignment(JLabel.CENTER);
		nombreCoupLabel.setFont(arial);
		southContent.add(nombreCoupLabel);

		this.panel.add(northContent, BorderLayout.NORTH);
		this.panel.add(centerContent, BorderLayout.CENTER);
		this.panel.add(southContent, BorderLayout.SOUTH);

		combinaisonSecrete = RandomCombination.generateRandomCombination(this.nbCases);
		controler.setCombinaisonSecrete(combinaisonSecrete);

		propositionTextField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controler.setMode("CHALLENGER");
				controler.setProposition(((JTextField) e.getSource()).getText());
			}
		});
	}

	public void gestionFinDePartie(String reponse) {
		this.nbEssais--;

		if (reponse.equals("====") && this.nbEssais > 0) {
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
		if ((!reponse.equals("====")) && this.nbEssais <= 0) {
			JOptionPane.showMessageDialog(null, "Désolé, vous avez perdu!\n" + "La bonne combinaison était "
					+ this.combinaisonSecrete + "\nRetentez votre chance !", "Fin de partie",
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
	
	public int getNbEssais() {
		return 10 - this.nbEssais;
	}

	@Override
	public void update(String proposition, String reponse) {
		this.propositionTextField.setText("");
		this.storyTextArea.append(proposition + "\t:\t" + reponse + "\n");
		this.gestionFinDePartie(reponse);
		this.nombreCoupLabel.setText("Nombre de coups restants : " + this.nbEssais);
	}

	@Override
	public void restart() {
		this.storyTextArea.setText("");
		this.nombreCoupLabel.setText("Nombre de coups restants : 10");
		this.nbEssais = 10;
		this.combinaisonSecrete = RandomCombination.generateRandomCombination(this.nbCases);
		controler.setCombinaisonSecrete(this.combinaisonSecrete);
	}

	@Override
	public void accueil() {
	}

	@Override
	public void exitApplication() {
	}

	@Override
	public void updateModeDefenseur(String proposition, String reponse, String combiSecrete) {
	}

}
