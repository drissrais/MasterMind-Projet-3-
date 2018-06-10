package com.openclassrooms.jeudelogique.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoiteDialogueParametrage extends JDialog {
	private JLabel nbCoupsRechercheLabel;
	private JComboBox<String> nbCoupsRechercheComboBox;
	private JLabel nbCasesRechercheLabel;
	private JComboBox<String> nbCasesRechercheComboBox;
	private JLabel nbCoupsMastermindLabel;
	private JComboBox<String> nbCoupsMastermindComboBox;
	private JLabel nbCasesMastermindLabel;
	private JComboBox<String> nbCasesMastermindComboBox;
	private JLabel nbChiffresMastermindLabel;
	private JComboBox<String> nbChiffresMastermindComboBox;
	private JCheckBox modeDeveloppeurCheckBox;
	private JButton confirmButton;
	private JButton cancelButton;
	
	private boolean developerMode;
	private String developerModeString;

	private static final long serialVersionUID = -3838016887347550142L;

	public BoiteDialogueParametrage(JFrame owner, String title, boolean modal, boolean developerMode) {
		super(owner, title, modal);
		setSize(600, 290);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.developerMode = developerMode;
		initDialog();
		setVisible(true);
	}

	private void initDialog() {
		String[] nbCoupsArray = { "5", "10", "15" };
		String[] nbCasesArray = { "4", "5", "6", "7", "8", "9", "10" };
		Dimension dim = new Dimension(600, 75);

		Properties properties = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
			properties.load(input);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		JPanel recherchePanel = new JPanel();
		recherchePanel.setBorder(BorderFactory.createTitledBorder("RecherchePlusMoins"));
		recherchePanel.setPreferredSize(dim);

		nbCoupsRechercheLabel = new JLabel("Nombre de coups :");
		nbCoupsRechercheComboBox = new JComboBox<String>(nbCoupsArray);
		nbCoupsRechercheComboBox
				.setSelectedItem(properties.getProperty("param.nbCoupsRecherchePlusMoins"));
		nbCasesRechercheLabel = new JLabel("Nombre de cases :");
		nbCasesRechercheComboBox = new JComboBox<String>(nbCasesArray);
		nbCasesRechercheComboBox
				.setSelectedItem(properties.getProperty("param.nbCasesRecherchePlusMoins"));
		recherchePanel.add(nbCoupsRechercheLabel);
		recherchePanel.add(nbCoupsRechercheComboBox);
		recherchePanel.add(nbCasesRechercheLabel);
		recherchePanel.add(nbCasesRechercheComboBox);

		JPanel mastermindPanel = new JPanel();
		mastermindPanel.setBorder(BorderFactory.createTitledBorder("  Mastermind"));
		mastermindPanel.setPreferredSize(dim);

		nbCoupsMastermindLabel = new JLabel("Nombre de coups :");
		nbCoupsMastermindComboBox = new JComboBox<String>(nbCoupsArray);
		nbCoupsMastermindComboBox.setSelectedItem(properties.getProperty("param.nbCoupsMastermind"));
		nbCasesMastermindLabel = new JLabel("Nombre de cases :");
		nbCasesMastermindComboBox = new JComboBox<>();
		nbCasesMastermindComboBox.addItem("4");
		nbCasesMastermindComboBox.addItem("5");
		nbCasesMastermindComboBox.addItem("6");
		nbCasesMastermindComboBox.setSelectedItem(properties.getProperty("param.nbCasesMastermind"));
		nbChiffresMastermindLabel = new JLabel("Nombre de chiffres à utiliser :");
		nbChiffresMastermindComboBox = new JComboBox<String>(nbCasesArray);
		nbChiffresMastermindComboBox
				.setSelectedItem(properties.getProperty("param.nbChiffresAUtiliserMastermind"));
		mastermindPanel.add(nbCoupsMastermindLabel);
		mastermindPanel.add(nbCoupsMastermindComboBox);
		mastermindPanel.add(nbCasesMastermindLabel);
		mastermindPanel.add(nbCasesMastermindComboBox);
		mastermindPanel.add(nbChiffresMastermindLabel);
		mastermindPanel.add(nbChiffresMastermindComboBox);

		modeDeveloppeurCheckBox = new JCheckBox("Mode développeur ");
		if (developerMode) {
			modeDeveloppeurCheckBox.setSelected(true);
		} else {
			modeDeveloppeurCheckBox.setSelected(false);
		}

		JPanel centerPanel = new JPanel();
		centerPanel.add(mastermindPanel);
		centerPanel.add(modeDeveloppeurCheckBox);

		JPanel controlPanel = new JPanel();
		controlPanel.setPreferredSize(new Dimension(600, 50));
		confirmButton = new JButton("Confirmer");
		cancelButton = new JButton("Quitter");
		controlPanel.add(confirmButton);
		controlPanel.add(cancelButton);

		add(recherchePanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(controlPanel, BorderLayout.SOUTH);

		confirmButton.addActionListener((e) -> {
			if (modeDeveloppeurCheckBox.isSelected()) {
				developerModeString = "true";
			} else {
				developerModeString = "false";
			}
			
			Properties prop = new Properties();
			try (InputStream input = new FileInputStream("src/main/resources/config.properties");
					OutputStream output = new FileOutputStream("src/main/resources/config.properties")) {
				prop.load(input);

				// Traitement pour le jeu RecherchePlusMoins
				prop.setProperty("param.nbCoupsRecherchePlusMoins",
						(String) nbCoupsRechercheComboBox.getSelectedItem());
				prop.setProperty("param.nbCasesRecherchePlusMoins",
						(String) nbCasesRechercheComboBox.getSelectedItem());

				// Traitement pour le jeu Mastermind
				prop.setProperty("param.nbCoupsMastermind", (String) nbCoupsMastermindComboBox.getSelectedItem());
				prop.setProperty("param.nbCasesMastermind", (String) nbCasesMastermindComboBox.getSelectedItem());
				prop.setProperty("param.nbChiffresAUtiliserMastermind",
						(String) nbChiffresMastermindComboBox.getSelectedItem());
				prop.setProperty("param.modeDeveloppeur", developerModeString);

				prop.store(output, "Fichier de configuration config.properties");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			setVisible(false);
		});

		cancelButton.addActionListener((e) -> setVisible(false));
	}

}
