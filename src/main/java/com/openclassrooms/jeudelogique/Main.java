package com.openclassrooms.jeudelogique;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.openclassrooms.jeudelogique.model.MastermindModel;
import com.openclassrooms.jeudelogique.model.SearchModel;
import com.openclassrooms.jeudelogique.view.Fenetre;

/*La classe Main du programme.*/
public class Main {
	private static final Logger logger = LogManager.getLogger();

	/*
	 * La méthode main qui permet d'instancier les modèles de données relatifs aux
	 * jeux RecherchePlusMoins et Mastermind et également la fenêtre principale.
	 */
	public static void main(String[] args) {
		boolean developerMode = false;
		Properties config = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
			config.load(input);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Si l'application est lancée avec le paramètre -dev ou que la proprièté dans
		// le fichier config est 'param.modeDeveloppeur=true'
		if (config.getProperty("param.modeDeveloppeur").equals("true") || (args.length > 0 && args[0].equals("dev"))) {
			logger.info("Mode développeur activé");
			developerMode = true;
		}

		// Instanciation des modèles de données relatifs aux jeux RecherchePlusMoins et
		// Mastermind.
		SearchModel sModel = new SearchModel();
		MastermindModel mModel = new MastermindModel();

		// Instanciation de la fenêtre principale.
		Fenetre fen = new Fenetre(sModel, mModel, developerMode);
		fen.setVisible(true);
	}

}
