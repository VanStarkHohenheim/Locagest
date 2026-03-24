package com.gestionbaux.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Utilitaire centralisé pour la navigation entre les vues FXML.
 * Toutes les transitions d'écran passent par cette classe.
 */
public class NavigationUtil {

    private static Stage stage;

    /** Enregistre la fenêtre principale de l'application. */
    public static void setStage(Stage s) {
        stage = s;
    }

    /**
     * Charge et affiche une vue FXML dans la fenêtre principale.
     *
     * @param nomVue Le nom du fichier FXML sans extension (ex: "accueil")
     */
    public static void naviguerVers(String nomVue) {
        try {
            FXMLLoader loader = new FXMLLoader(
                NavigationUtil.class.getResource("/com/gestionbaux/vue/" + nomVue + ".fxml")
            );
            Parent root = loader.load();
            stage.setScene(new Scene(root, 800, 600));
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la vue : " + nomVue);
            e.printStackTrace();
        }
    }
}
