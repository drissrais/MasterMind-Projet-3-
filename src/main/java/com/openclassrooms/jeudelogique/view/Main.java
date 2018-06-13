package com.openclassrooms.jeudelogique.view;

import java.util.Scanner;

import com.openclassrooms.jeudelogique.model.MastermindModel;
import com.openclassrooms.jeudelogique.model.SearchModel;

/*La classe Main du programme.*/
public class Main {

	/*
	 * La méthode main qui permet d'instancier les modèles de données relatifs aux
	 * jeux RecherchePlusMoins et Mastermind et également la fenêtre principale. Il
	 * est aussi possible de choisir via la console d'activer ou de désactiver le mode développeur
	 */
	public static void main(String[] args) {
		
		// Activation ou non du mode développeur via la console.
		Scanner sc = new Scanner(System.in);
		String reponse = "";
		boolean developerMode = false;
		do {
			System.out.println(
					"Voulez-vous lancer l'application en mode développeur (solution affichée) ? O pour oui/N pour non");
			reponse = sc.nextLine();
		} while ((!reponse.equals("O")) && (!reponse.equals("N")));
		sc.close();

		if (reponse.equals("O")) {
			developerMode = true;
		} else {
			developerMode = false;
		}
		
		//Instanciation des modèles de données relatifs aux jeux RecherchePlusMoins et Mastermind.
		SearchModel sModel = new SearchModel();
		MastermindModel mModel = new MastermindModel();
		
		//Instanciation de la fenêtre principale.
		Fenetre fen = new Fenetre(sModel, mModel, developerMode);
		fen.setVisible(true);
	}

}
