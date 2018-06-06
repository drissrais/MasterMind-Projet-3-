package com.openclassrooms.jeudelogique.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

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
	private JComboBox<Integer> nbCoupsRechercheComboBox;
	private JLabel nbCasesRechercheLabel;
	private JComboBox<Integer> nbCasesRechercheComboBox;
	private JLabel nbCoupsMastermindLabel;
	private JComboBox<Integer> nbCoupsMastermindComboBox;
	private JLabel nbCasesMastermindLabel;
	private JComboBox<Integer> nbCasesMastermindComboBox;
	private JLabel nbChiffresMastermindLabel;
	private JComboBox<Integer> nbChiffresMastermindComboBox;
	private JCheckBox modeDeveloppeurCheckBox;
	private JButton confirmButton;
	private JButton cancelButton;

	private static final long serialVersionUID = -3838016887347550142L;
	
	public BoiteDialogueParametrage(JFrame owner, String title, boolean modal) {
		super(owner, title, modal);
		setSize(600, 290);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		initDialog();
		setVisible(true);
	}

	private void initDialog() {
		Integer[] nbCoupsArray = {5, 10, 15};
		Integer[] nbCasesArray = {4, 5, 6, 7, 8, 9, 10};
		Dimension dim = new Dimension(600, 65);
		
		JPanel recherchePanel = new JPanel();
		recherchePanel.setBorder(BorderFactory.createTitledBorder("RecherchePlusMoins"));
		recherchePanel.setPreferredSize(dim);
		
		nbCoupsRechercheLabel = new JLabel("Nombre de coups :");
		nbCoupsRechercheComboBox = new JComboBox<Integer>(nbCoupsArray);
		nbCoupsRechercheComboBox.setSelectedIndex(1);
		nbCasesRechercheLabel = new JLabel("Nombre de cases :");
		nbCasesRechercheComboBox = new JComboBox<Integer>(nbCasesArray);
		recherchePanel.add(nbCoupsRechercheLabel);
		recherchePanel.add(nbCoupsRechercheComboBox);
		recherchePanel.add(nbCasesRechercheLabel);
		recherchePanel.add(nbCasesRechercheComboBox);
		
		JPanel mastermindPanel = new JPanel();
		mastermindPanel.setBorder(BorderFactory.createTitledBorder("  Mastermind"));
		mastermindPanel.setPreferredSize(dim);
		
		nbCoupsMastermindLabel = new JLabel("Nombre de coups :");
		nbCoupsMastermindComboBox = new JComboBox<Integer>(nbCoupsArray);
		nbCoupsMastermindComboBox.setSelectedIndex(1);
		nbCasesMastermindLabel = new JLabel("Nombre de cases :");
		nbCasesMastermindComboBox = new JComboBox<>();
		nbCasesMastermindComboBox.addItem(4);
		nbCasesMastermindComboBox.addItem(5);
		nbCasesMastermindComboBox.addItem(6);
		nbChiffresMastermindLabel = new JLabel("Nombre de chiffres à utiliser :");
		nbChiffresMastermindComboBox = new JComboBox<Integer>(nbCasesArray);
		mastermindPanel.add(nbCoupsMastermindLabel);
		mastermindPanel.add(nbCoupsMastermindComboBox);
		mastermindPanel.add(nbCasesMastermindLabel);
		mastermindPanel.add(nbCasesMastermindComboBox);
		mastermindPanel.add(nbChiffresMastermindLabel);
		mastermindPanel.add(nbChiffresMastermindComboBox);
		
		JPanel developerModePanel = new JPanel();
		developerModePanel.setPreferredSize(dim);
		developerModePanel.setBorder(BorderFactory.createTitledBorder("  Mode développeur"));
		modeDeveloppeurCheckBox = new JCheckBox("Mode développeur ");
		developerModePanel.add(modeDeveloppeurCheckBox);
		
		JPanel centerPanel = new JPanel();
		centerPanel.add(mastermindPanel);
		centerPanel.add(developerModePanel);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setPreferredSize(new Dimension(600, 50));
		confirmButton = new JButton("Confirmer");
		cancelButton = new JButton("Quitter");
		controlPanel.add(confirmButton);
		controlPanel.add(cancelButton);
		
		
		add(recherchePanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(controlPanel, BorderLayout.SOUTH);
	}

}
