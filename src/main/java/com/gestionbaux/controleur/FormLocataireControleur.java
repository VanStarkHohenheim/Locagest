package com.gestionbaux.controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import com.gestionbaux.modele.Locataire;
import com.gestionbaux.util.NavigationUtil;

public class FormLocataireControleur {

    @FXML private TextField champNom;
    @FXML private TextField champPrenom;
    @FXML private TextField champEmail;
    @FXML private TextField champTelephone;

    private static int compteurId = 1;

    @FXML
    public void enregistrerLocataire(ActionEvent actionEvent) {
        // Validation : nom et prénom obligatoires
        if (champNom.getText().isBlank()) {
            afficherErreur("Le nom est obligatoire.");
            return;
        }
        if (champPrenom.getText().isBlank()) {
            afficherErreur("Le prénom est obligatoire.");
            return;
        }

        Locataire locataire = new Locataire(
            compteurId++,
            champNom.getText(),
            champPrenom.getText(),
            champEmail.getText(),
            champTelephone.getText()
        );

        LocatairesControleur.locataires.add(locataire);
        NavigationUtil.naviguerVers("locataires");
    }

    @FXML
    public void annuler(ActionEvent actionEvent) {
        NavigationUtil.naviguerVers("locataires");
    }

    private void afficherErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
