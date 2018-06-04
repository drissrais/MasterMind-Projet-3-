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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.MaskFormatter;

import com.openclassrooms.jeudelogique.controler.SearchDualControler;
import com.openclassrooms.jeudelogique.model.SearchModel;
import com.openclassrooms.jeudelogique.model.TableModel;
import com.openclassrooms.jeudelogique.observer.Observer;
import com.openclassrooms.jeudelogique.utilities.RandomCombination;

public class SearchDualPanel extends ZContainer implements Observer {
	private JLabel combinaisonLabel;
	private JFormattedTextField combinaisonTextField;
	private JButton validerButton;
	private JLabel propositionJoueurLabel;
	private JTextField propositionJoueurTextField;
	private JLabel propositionOrdinateurLabel;
	private JLabel propositionOrdinateur;
	private JTable storyTable;
	private TableModel tableModel;
	private LabelRenderer labelRenderer;
	private JLabel couleurjoueurLabel, couleurOrdinateurLabel;
	private SearchDualControler controler;

	private int nbCases = 4, rowIndex = 0, columnIndex = 0;
	private int activerBoutonValiderCombiSecrete;
	private String combinaisonSecreteOrdinateurModeDuel;
	private String reponseOrdinateur, reponseJoueur;

	public SearchDualPanel(Dimension dim, SearchModel mod) {
		super(dim);
		this.controler = new SearchDualControler(mod);
		initPanel();
	}

	@Override
	protected void initPanel() {
		JPanel northContent = new JPanel();
		northContent.setBackground(Color.WHITE);
		northContent.setPreferredSize(new Dimension(800, 45));
		JLabel welcomeMessage = new JLabel("recherche +/- | mode duel".toUpperCase());
		welcomeMessage.setHorizontalAlignment(JLabel.CENTER);
		welcomeMessage.setFont(comics30);
		welcomeMessage.setForeground(Color.decode("#ee5100"));
		northContent.add(welcomeMessage);

		JPanel centerContent = new JPanel();
		centerContent.setPreferredSize(new Dimension(800, 410));
		centerContent.setBackground(Color.WHITE);

		JTextArea texte = new JTextArea(
				"l'ordinateur et vous jouez tour à tour, le premier à trouver la combinaison secrète\nde l'autre a gagné.\t"
						.toUpperCase() + " + : Chiffre plus grand       - : Chiffre plus petit        = : Bon chiffre");
		texte.setForeground(Color.BLUE);
		texte.setPreferredSize(new Dimension(800, 50));
		texte.setFont(arial);
		texte.setEditable(false);
		texte.setFocusable(false);
		centerContent.add(texte);

		combinaisonLabel = new JLabel("Veuillez saisir votre combinaison secrète (" + this.nbCases + " chiffres) : ");
		combinaisonLabel.setHorizontalAlignment(JLabel.LEFT);
		combinaisonLabel.setPreferredSize(new Dimension(400, 25));
		combinaisonLabel.setFont(arial);
		centerContent.add(combinaisonLabel);

		combinaisonTextField = new JFormattedTextField();
		try {
			MaskFormatter maskFormatter = new MaskFormatter("####");
			combinaisonTextField = new JFormattedTextField(maskFormatter);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		combinaisonTextField.setPreferredSize(new Dimension(140, 25));
		combinaisonTextField.setHorizontalAlignment(JTextField.CENTER);
		combinaisonTextField.setForeground(Color.BLUE);
		combinaisonTextField.setFont(arial);
		combinaisonTextField.requestFocusInWindow();
		centerContent.add(combinaisonTextField);

		validerButton = new JButton("Valider");
		validerButton.setPreferredSize(new Dimension(100, 25));
		validerButton.setEnabled(false);
		centerContent.add(validerButton);

		propositionJoueurLabel = new JLabel("Votre proposition : ");
		propositionJoueurLabel.setPreferredSize(new Dimension(150, 35));
		propositionJoueurLabel.setHorizontalAlignment(JLabel.LEFT);
		propositionJoueurLabel.setFont(arial);
		centerContent.add(propositionJoueurLabel);

		propositionJoueurTextField = new JTextField();
		try {
			MaskFormatter maskFormatter = new MaskFormatter("####");
			propositionJoueurTextField = new JFormattedTextField(maskFormatter);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		propositionJoueurTextField.setEnabled(false);
		propositionJoueurTextField.setPreferredSize(new Dimension(90, 25));
		propositionJoueurTextField.setHorizontalAlignment(JTextField.CENTER);
		propositionJoueurTextField.setForeground(Color.decode("#51b46d"));
		propositionJoueurTextField.setFont(arial);
		centerContent.add(propositionJoueurTextField);

		JTextArea vide = new JTextArea("\t");
		vide.setEnabled(false);
		vide.setEditable(false);
		vide.setFocusable(false);
		centerContent.add(vide);

		propositionOrdinateurLabel = new JLabel("Proposition de l'ordinateur : ");
		propositionOrdinateurLabel.setPreferredSize(new Dimension(215, 35));
		propositionOrdinateurLabel.setHorizontalAlignment(JLabel.LEFT);
		propositionOrdinateurLabel.setFont(arial);
		centerContent.add(propositionOrdinateurLabel);

		propositionOrdinateur = new JLabel("");
		propositionOrdinateur.setPreferredSize(new Dimension(90, 25));
		propositionOrdinateur.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		propositionOrdinateur.setHorizontalAlignment(JTextField.CENTER);
		propositionOrdinateur.setForeground(Color.RED);
		propositionOrdinateur.setFont(arial);
		centerContent.add(propositionOrdinateur);

		JPanel containerTable = new JPanel(new BorderLayout());
		containerTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		containerTable.setPreferredSize(new Dimension(500, 275));
		String[] titles = { "Proposition", "Réponse" };
		Object[][] data = new Object[20][2];
		tableModel = new TableModel(data, titles);
		storyTable = new JTable(tableModel);
		labelRenderer = new LabelRenderer();
		storyTable.getColumn("Proposition").setCellRenderer(labelRenderer);
		storyTable.getColumn("Réponse").setCellRenderer(labelRenderer);
		storyTable.setBackground(Color.decode("#eeeeee"));
		storyTable.setAlignmentX(Component.CENTER_ALIGNMENT);
		storyTable.setFont(arial);
		containerTable.add(new JScrollPane(storyTable), BorderLayout.CENTER);
		centerContent.add(containerTable);

		JPanel southContent = new JPanel();
		southContent.setBackground(Color.WHITE);
		southContent.setPreferredSize(new Dimension(800, 30));
		couleurjoueurLabel = new JLabel("Vous êtes en vert.");
		couleurjoueurLabel.setForeground(Color.decode("#51b46d"));
		couleurjoueurLabel.setHorizontalAlignment(JLabel.CENTER);
		couleurjoueurLabel.setFont(arial);
		couleurOrdinateurLabel = new JLabel("L'ordinateur est en rouge.");
		couleurOrdinateurLabel.setForeground(Color.RED);
		couleurOrdinateurLabel.setHorizontalAlignment(JLabel.CENTER);
		couleurOrdinateurLabel.setFont(arial);
		southContent.add(couleurjoueurLabel);
		southContent.add(couleurOrdinateurLabel);

		this.combinaisonSecreteOrdinateurModeDuel = RandomCombination.generateRandomCombination(this.nbCases);
		this.controler.setCombinaisonSecreteOrdinateurModeDuel(this.combinaisonSecreteOrdinateurModeDuel);

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
			this.propositionJoueurTextField.setEnabled(true);
			this.propositionJoueurTextField.requestFocusInWindow();
			this.controler.setMode("DUEL");
			this.controler.setCombinaisonSecreteJoueurModeDuel(combinaisonTextField.getText());
		});

		propositionJoueurTextField.addActionListener((e) -> {
			this.controler.setMode("DUEL");
			this.controler.setPropositionJoueurModeDuel(((JTextField) e.getSource()).getText());
			this.propositionJoueurTextField.setText("");
			this.propositionJoueurTextField.requestFocusInWindow();
			this.gestionFinDePartie(this.reponseOrdinateur, this.reponseJoueur);
		});

	}

	public void gestionFinDePartie(String reponseOrdinateur, String reponseJoueur) {
		if (reponseOrdinateur.equals("====") && (!reponseJoueur.equals("===="))) {
			JOptionPane.showMessageDialog(null,
					"Bravo!!! vous avez trouvé en premier la combinaison secrète de l'ordinateur.", "Fin de partie",
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
		if ((!reponseOrdinateur.equals("====")) && reponseJoueur.equals("====")) {
			JOptionPane.showMessageDialog(null,
					"Perdu! L'ordinateur a trouvé en premier votre combinaison secrète.\n"
							+ "La combinaison secrète de l'ordinateur était : " + this.combinaisonSecreteOrdinateurModeDuel,
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
		if (reponseOrdinateur.equals("====") && reponseJoueur.equals("====")) {
			JOptionPane.showMessageDialog(null,
					"Ni Gagné Ni Perdu!!\nChacun a trouvé, au même tour, la combinaison secrète de l'autre.",
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
		// TODO Auto-generated method stub

	}

	@Override
	public void updateModeDefenseurOuDuel(String proposition, String reponse, String reponse2) {
		this.propositionOrdinateur.setText(proposition);
		this.storyTable.getModel().setValueAt(this.propositionJoueurTextField.getText(), rowIndex, columnIndex);
		((AbstractTableModel) this.storyTable.getModel()).fireTableCellUpdated(rowIndex, columnIndex);
		this.storyTable.getModel().setValueAt(reponse2, rowIndex, columnIndex + 1);
		((AbstractTableModel) this.storyTable.getModel()).fireTableCellUpdated(rowIndex, columnIndex + 1);
		this.storyTable.getModel().setValueAt(proposition, rowIndex + 1, columnIndex);
		((AbstractTableModel) this.storyTable.getModel()).fireTableCellUpdated(rowIndex + 1, columnIndex);
		this.storyTable.getModel().setValueAt(reponse, rowIndex + 1, columnIndex + 1);
		((AbstractTableModel) this.storyTable.getModel()).fireTableCellUpdated(rowIndex + 1, columnIndex + 1);
		
		this.reponseOrdinateur = String.valueOf(this.storyTable.getModel().getValueAt(rowIndex, columnIndex + 1));
		this.reponseJoueur = String.valueOf(this.storyTable.getModel().getValueAt(rowIndex + 1, columnIndex + 1));
		
		rowIndex += 2;
	}

	@Override
	public void restart() {
		this.combinaisonSecreteOrdinateurModeDuel = RandomCombination.generateRandomCombination(this.nbCases);
		this.controler.setCombinaisonSecreteOrdinateurModeDuel(this.combinaisonSecreteOrdinateurModeDuel);
		this.combinaisonTextField.setEnabled(true);
		this.combinaisonTextField.setText("");
		this.propositionOrdinateur.setText("");
		this.propositionJoueurTextField.setEnabled(false);
		for(int i = 0; i <= rowIndex; i++) {
			for (int j = 0; j < 2; j++) {
				((AbstractTableModel)this.storyTable.getModel()).setValueAt("", i, j);
				((AbstractTableModel)this.storyTable.getModel()).fireTableCellUpdated(i, j);
			}
		}
		this.rowIndex = 0;
		this.columnIndex = 0;
	}

	@Override
	public void accueil() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitApplication() {
		// TODO Auto-generated method stub

	}

}
