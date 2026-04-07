package com.gestionbaux.controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import com.gestionbaux.dao.LocataireDAO;
import com.gestionbaux.modele.Locataire;
import com.gestionbaux.util.NavigationUtil;

public class FormLocataireControleur {

    @FXML private TextField champNom;
    @FXML private TextField champPrenom;
    @FXML private TextField champEmail;
    @FXML private TextField champTelephone;

    @FXML
    public void enregistrerLocataire(ActionEvent e) {
        if (champNom.getText().isBlank() || champPrenom.getText().isBlank()
                || champEmail.getText().isBlank() || champTelephone.getText().isBlank()) {
            new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs.").showAndWait();
            return;
        }
        LocataireDAO.ajouter(new Locataire(0, champNom.getText(), champPrenom.getText(), champEmail.getText(), champTelephone.getText()));
        NavigationUtil.naviguerVers("locataires");
    }

    @FXML
    public void annuler(ActionEvent e) { NavigationUtil.naviguerVers("locataires"); }
}
