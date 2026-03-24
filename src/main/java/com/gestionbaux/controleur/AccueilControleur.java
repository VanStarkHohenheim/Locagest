package com.gestionbaux.controleur;

import javafx.fxml.FXML;
import com.gestionbaux.util.NavigationUtil;

/**
 * Contrôleur de l'écran d'accueil.
 * Gère uniquement la navigation vers les autres sections.
 */
public class AccueilControleur {

    /** Navigue vers la liste des appartements. */
    @FXML
    private void allerAppartements() {
        NavigationUtil.naviguerVers("appartements");
    }

    /** Navigue vers la liste des locataires. */
    @FXML
    private void allerLocataires() {
        NavigationUtil.naviguerVers("locataires");
    }

    /** Navigue vers la liste des baux. */
    @FXML
    private void allerBaux() {
        NavigationUtil.naviguerVers("baux");
    }
}
