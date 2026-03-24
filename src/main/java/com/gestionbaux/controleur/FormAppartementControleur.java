package com.gestionbaux.controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import com.gestionbaux.modele.Appartement;
import com.gestionbaux.util.NavigationUtil;

public class FormAppartementControleur {

    @FXML private TextField champAdresse;
    @FXML private TextField champSurface;
    @FXML private TextField champPieces;
    @FXML private TextField champLoyer;

    private static int compteurId = 1;

    @FXML
    public void enregistrerAppartement(ActionEvent actionEvent) {
        // Validation : vérifie que les champs ne sont pas vides et que les nombres sont valides
        if (champAdresse.getText().isBlank()) {
            afficherErreur("L'adresse est obligatoire.");
            return;
        }
        if (!estNombreEntier(champSurface.getText())) {
            afficherErreur("La surface doit être un nombre entier.");
            return;
        }
        if (!estNombreEntier(champPieces.getText())) {
            afficherErreur("Le nombre de pièces doit être un nombre entier.");
            return;
        }
        if (!estNombreDecimal(champLoyer.getText())) {
            afficherErreur("Le loyer doit être un nombre valide.");
            return;
        }

        Appartement appartement = new Appartement(
            compteurId++,
            champAdresse.getText(),
            Integer.parseInt(champSurface.getText()),
            Integer.parseInt(champPieces.getText()),
            Double.parseDouble(champLoyer.getText())
        );

        AppartementsControleur.appartements.add(appartement);
        NavigationUtil.naviguerVers("appartements");
    }

    @FXML
    public void annuler(ActionEvent actionEvent) {
        NavigationUtil.naviguerVers("appartements");
    }

    private boolean estNombreEntier(String valeur) {
        try {
            Integer.parseInt(valeur.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean estNombreDecimal(String valeur) {
        try {
            Double.parseDouble(valeur.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void afficherErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
