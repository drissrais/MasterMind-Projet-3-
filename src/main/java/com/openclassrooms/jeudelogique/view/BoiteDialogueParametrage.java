package com.openclassrooms.jeudelogique.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class BoiteDialogueParametrage extends JDialog {

	private static final long serialVersionUID = -3838016887347550142L;

	private JRadioButton recherche = new JRadioButton("Recherche +/-");
	private JRadioButton mastermind = new JRadioButton("MasterMind");
	private JComboBox<String> modeComboBox = new JComboBox<>();
	private JLabel modeLabel = new JLabel("Mode");
	private JButton confirmButton = new JButton("Confirmer");
	private JButton cancelButton = new JButton("Quitter");
	
	private ZDialogInfo zInfo = new ZDialogInfo("", "");
	
	public BoiteDialogueParametrage(JFrame owner, String title, boolean modal) {
		super(owner, title, modal);
		setSize(500, 470);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		initDialog();
		setVisible(true);
	}

	private void initDialog() {
		JPanel panIcone = new JPanel();
		panIcone.setBackground(Color.WHITE);
		JLabel icone = new JLabel(new ImageIcon(getClass().getResource("/mastermind3.gif")));
		panIcone.add(icone);

		JPanel panGame = new JPanel();
		panGame.setBackground(Color.WHITE);
		panGame.setPreferredSize(new Dimension(250, 120));
		panGame.setBorder(BorderFactory.createTitledBorder("Choix du jeu"));
		recherche.setSelected(true);
		ButtonGroup bg = new ButtonGroup();
		bg.add(recherche);
		bg.add(mastermind);
		panGame.add(recherche);
		panGame.add(mastermind);

		JPanel panMode = new JPanel();
		panMode.setBackground(Color.WHITE);
		panMode.setPreferredSize(new Dimension(250, 120));
		panMode.setBorder(BorderFactory.createTitledBorder("Mode de jeu"));
		modeComboBox.addItem("CHALLENGER");
		modeComboBox.addItem("DUEL");
		modeComboBox.addItem("DEFENSEUR");
		panMode.add(modeLabel);
		panMode.add(modeComboBox);

		JPanel south = new JPanel();
		south.setBackground(Color.WHITE);
		south.add(confirmButton);
		south.add(cancelButton);

		cancelButton.addActionListener((e) -> {
			zInfo = new ZDialogInfo("", "");
			setVisible(false);
		});
		
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				zInfo = new ZDialogInfo(getGame(), (String)modeComboBox.getSelectedItem());
				setVisible(false);
			}
			private String getGame() {
				String str = "";
				if (recherche.isSelected()) {
					str = recherche.getText();
				} else if (mastermind.isSelected()) {
					str = mastermind.getText();
				}
				return str;
			}
		});

		JPanel content = new JPanel();
		content.setBackground(Color.WHITE);
		content.add(panGame);
		content.add(panMode);

		add(panIcone, BorderLayout.WEST);
		add(content, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);

	}
	
	public ZDialogInfo getzInfo() {
		return zInfo;
	}

}
