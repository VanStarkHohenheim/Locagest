package com.gestionbaux.controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import com.gestionbaux.modele.Appartement;
import com.gestionbaux.modele.Bail;
import com.gestionbaux.modele.Locataire;
import com.gestionbaux.util.NavigationUtil;

public class FormBauxControleur {

    @FXML private TextField champNom;
    @FXML private TextField champPrenom;
    @FXML private TextField champAdresse;
    @FXML private DatePicker champDateDebut;
    @FXML private DatePicker champDateFin;
    @FXML private TextField champLoyer;

    private static int compteurId = 1;

    @FXML
    public void enregistrerBail(ActionEvent actionEvent) {
        // Validation des champs obligatoires
        if (champNom.getText().isBlank()) {
            afficherErreur("Le nom du locataire est obligatoire.");
            return;
        }
        if (champPrenom.getText().isBlank()) {
            afficherErreur("Le prénom du locataire est obligatoire.");
            return;
        }
        if (champAdresse.getText().isBlank()) {
            afficherErreur("L'adresse de l'appartement est obligatoire.");
            return;
        }
        if (champDateDebut.getValue() == null) {
            afficherErreur("La date de début est obligatoire.");
            return;
        }
        if (champDateFin.getValue() == null) {
            afficherErreur("La date de fin est obligatoire.");
            return;
        }
        if (!estNombreDecimal(champLoyer.getText())) {
            afficherErreur("Le loyer doit être un nombre valide.");
            return;
        }

        Locataire locataire = new Locataire(compteurId, champNom.getText(), champPrenom.getText(), "", "");
        Appartement appartement = new Appartement(compteurId, champAdresse.getText(), 0, 0, 0);

        Bail bail = new Bail(
            compteurId++,
            appartement,
            locataire,
            champDateDebut.getValue(),
            champDateFin.getValue(),
            Double.parseDouble(champLoyer.getText())
        );

        BauxControleur.baux.add(bail);
        NavigationUtil.naviguerVers("baux");
    }

    @FXML
    public void annuler(ActionEvent actionEvent) {
        NavigationUtil.naviguerVers("baux");
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
