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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.openclassrooms.jeudelogique.controler.Controler;
import com.openclassrooms.jeudelogique.observer.Observable;
import com.openclassrooms.jeudelogique.observer.Observer;

public class SearchChallengerPanel extends ZContainer implements Observer {
	private JLabel propositionLabel;
	private JFormattedTextField propositionTextField;
	private JTextArea storyTextArea;
	private JLabel nombreCoupLabel;
	private Controler controler;

	public SearchChallengerPanel(Dimension dim, Observable mod) {
		super(dim);
		this.controler = new Controler(mod);
		initPanel();
	}

	@Override
	protected void initPanel() {
		Dimension dim = new Dimension(800, 50);

		JPanel northContent = new JPanel();
		northContent.setBackground(Color.WHITE);
		northContent.setPreferredSize(dim);
		JLabel welcomeMessage = new JLabel("Bienvenue dans le jeu de recherche +/- en mode challenger".toUpperCase());
		welcomeMessage.setPreferredSize(new Dimension(800, 50));
		welcomeMessage.setHorizontalAlignment(JLabel.CENTER);
		welcomeMessage.setFont(comics20);
		welcomeMessage.setForeground(Color.GRAY);
		northContent.add(welcomeMessage);

		JPanel centerContent = new JPanel();
		centerContent.setPreferredSize(new Dimension(800, 400));
		centerContent.setBackground(Color.WHITE);

		JTextArea texte = new JTextArea(
				"Saurez-vous trouver la combinaison cachée en moins de 10 coups?\n(Chiffres compris entre 0 et 9 avec répétitions possibles)\n+ : Chiffre plus grand\t - : Chiffre plus petit\t= : Bon chiffre");
		texte.setEditable(false);
		texte.setPreferredSize(new Dimension(700, 75));
		texte.setFont(arial);
		centerContent.add(texte);
		
		propositionLabel = new JLabel("Entrez les 4 chiffres de votre proposition :");
		propositionLabel.setHorizontalAlignment(JLabel.LEFT);
		propositionLabel.setPreferredSize(new Dimension(300, 40));
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
		centerContent.add(propositionTextField);
		
		storyTextArea = new JTextArea();
		storyTextArea.setEditable(false);
		storyTextArea.setFont(arial);
		storyTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		storyTextArea.setPreferredSize(new Dimension(250, 250));
		storyTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerContent.add(storyTextArea);

		JPanel southContent = new JPanel();
		southContent.setBackground(Color.WHITE);
		southContent.setPreferredSize(dim);
		nombreCoupLabel = new JLabel("Nombre de coups restants : 10");
		nombreCoupLabel.setPreferredSize(new Dimension(800, 20));
		nombreCoupLabel.setHorizontalAlignment(JLabel.CENTER);
		nombreCoupLabel.setFont(arial);
		southContent.add(nombreCoupLabel);
		
		this.panel.add(northContent, BorderLayout.NORTH);
		this.panel.add(centerContent, BorderLayout.CENTER);
		this.panel.add(southContent, BorderLayout.SOUTH);
		
		propositionTextField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controler.setProposition(((JTextField)e.getSource()).getText());
				propositionTextField.setText("");
			}
		});
	}

	@Override
	public void update(int nbCases, String story, int nbCoups) {
		this.propositionLabel.setText("Entrez les " + nbCases + " chiffres de votre proposition :");
		this.storyTextArea.append(story + "\n");
		this.nombreCoupLabel.setText("Nombre de coups restants : " + nbCoups);
	}

	@Override
	public void restart() {
		this.storyTextArea.setText("");
		this.nombreCoupLabel.setText("Nombre de coups restants : 10");
	}

	@Override
	public void accueil() {}

}
