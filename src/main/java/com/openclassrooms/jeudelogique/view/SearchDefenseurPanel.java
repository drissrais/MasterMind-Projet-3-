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

import com.openclassrooms.jeudelogique.controler.SearchControler;
import com.openclassrooms.jeudelogique.model.SearchModel;
import com.openclassrooms.jeudelogique.observer.Observer;

public class SearchDefenseurPanel extends ZContainer implements Observer {
	private JLabel combinaisonLabel;
	private JFormattedTextField combinaisonTextField;
	private JButton validerButton;
	private JLabel reponseLabel;
	private JFormattedTextField reponseTextField;
	private JButton validerReponseButton;
	private JTextArea storyTextArea;
	private JLabel nombreCoupLabel;
	private SearchControler controler;

	private int nbCases = 4, nbEssais = 10;
	private int activerBoutonValiderCombiSecrete, activerBoutonValiderReponse;
	private String combinaisonSecrete = "";

	public SearchDefenseurPanel(Dimension dim, SearchModel mod) {
		super(dim);
		initPanel();
	}

	@Override
	protected void initPanel() {
		Dimension dim = new Dimension(800, 40);

		JPanel northContent = new JPanel();
		northContent.setPreferredSize(dim);
		JLabel welcomeMessage = new JLabel("recherche +/- | defender mode".toUpperCase());
		welcomeMessage.setHorizontalAlignment(JLabel.CENTER);
		welcomeMessage.setFont(comics30);
		welcomeMessage.setForeground(Color.decode("#ee5100"));
		northContent.add(welcomeMessage);

		JPanel centerContent = new JPanel();
		centerContent.setPreferredSize(new Dimension(800, 420));

		JTextArea texte = new JTextArea("L'ordinateur a ".toUpperCase() + this.nbEssais
				+ " essais pour trouver votre combinaison secrète.".toUpperCase()
				+ "\n+ : Chiffre plus grand\t - : Chiffre plus petit\t= : Bon chiffre");
		texte.setBackground(Color.decode("#eeeeee"));
		texte.setForeground(Color.BLUE);
		texte.setPreferredSize(new Dimension(700, 40));
		texte.setFont(arial);
		texte.setEditable(false);
		texte.setFocusable(false);
		centerContent.add(texte);

		combinaisonLabel = new JLabel("Veuillez saisir votre combinaison secrète (" + this.nbCases + " chiffres) : ");
		combinaisonLabel.setHorizontalAlignment(JLabel.LEFT);
		combinaisonLabel.setPreferredSize(new Dimension(400, 40));
		combinaisonLabel.setFont(arial);
		centerContent.add(combinaisonLabel);

		combinaisonTextField = new JFormattedTextField();
		try {
			MaskFormatter maskFormatter = new MaskFormatter("####");
			combinaisonTextField = new JFormattedTextField(maskFormatter);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		combinaisonTextField.setPreferredSize(new Dimension(120, 30));
		combinaisonTextField.setHorizontalAlignment(JTextField.CENTER);
		combinaisonTextField.setForeground(Color.BLUE);
		combinaisonTextField.setFont(arial);
		combinaisonTextField.requestFocusInWindow();
		centerContent.add(combinaisonTextField);

		validerButton = new JButton("Valider");
		validerButton.setPreferredSize(new Dimension(80, 30));
		validerButton.setEnabled(false);
		centerContent.add(validerButton);

		reponseLabel = new JLabel("Votre réponse (+, -, =) : ");
		reponseLabel.setPreferredSize(new Dimension(400, 40));
		reponseLabel.setHorizontalAlignment(JLabel.LEFT);
		reponseLabel.setFont(arial);
		centerContent.add(reponseLabel);

		reponseTextField = new JFormattedTextField();
		try {
			MaskFormatter maskFormatter = new MaskFormatter("****");
			reponseTextField = new JFormattedTextField(maskFormatter);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		reponseTextField.setPreferredSize(new Dimension(120, 30));
		reponseTextField.setHorizontalAlignment(JTextField.CENTER);
		reponseTextField.setForeground(Color.BLUE);
		reponseTextField.setFont(arial);
		reponseTextField.setEnabled(false);
		centerContent.add(reponseTextField);

		validerReponseButton = new JButton("Valider");
		validerReponseButton.setPreferredSize(new Dimension(80, 30));
		validerReponseButton.setEnabled(false);
		centerContent.add(validerReponseButton);

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
		nombreCoupLabel = new JLabel("Nombre de coups restants : " + this.nbEssais);
		nombreCoupLabel.setPreferredSize(new Dimension(800, 20));
		nombreCoupLabel.setHorizontalAlignment(JLabel.CENTER);
		nombreCoupLabel.setFont(arial);
		southContent.add(nombreCoupLabel);

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
					activerBoutonValiderCombiSecrete = 0;
				} else {
					activerBoutonValiderCombiSecrete = 0;
					validerButton.setEnabled(false);
				}
			}
		});

		validerButton.addActionListener((e) -> {
			combinaisonTextField.setEnabled(false);
			reponseTextField.setEnabled(true);
			reponseTextField.requestFocusInWindow();
			validerButton.setEnabled(false);
		});

		reponseTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				for (int i = 0; i < nbCases; i++) {
					if (reponseTextField.getText().charAt(i) != '+' && reponseTextField.getText().charAt(i) != '-'
							&& reponseTextField.getText().charAt(i) != '='
							&& reponseTextField.getText().charAt(i) != ' ') {
						JOptionPane.showMessageDialog(null, "Veuillez saisir l'un des caractères suivants : +, - ou =",
								"Attention", JOptionPane.INFORMATION_MESSAGE);
						reponseTextField.setText("");
					}
					if (reponseTextField.getText().charAt(i) != ' ') {
						activerBoutonValiderReponse++;
					}
				}
				if (activerBoutonValiderReponse == nbCases) {
					validerReponseButton.setEnabled(true);
					activerBoutonValiderReponse = 0;
				} else {
					activerBoutonValiderReponse = 0;
					validerReponseButton.setEnabled(false);
				}
			}
		});
		
		validerReponseButton.addActionListener((e) -> {
			reponseTextField.setText("");
			validerReponseButton.setEnabled(false);
		});

	}

	@Override
	public void update(String proposition, String reponse) {

	}

	@Override
	public void restart() {

	}

	@Override
	public void accueil() {

	}

	@Override
	public void exitApplication() {

	}

}
