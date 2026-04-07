package com.gestionbaux.controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import com.gestionbaux.dao.AppartementDAO;
import com.gestionbaux.modele.Appartement;
import com.gestionbaux.util.NavigationUtil;
import com.gestionbaux.util.SessionManager;

public class FormAppartementControleur {

    @FXML private TextField champAdresse;
    @FXML private TextField champSurface;
    @FXML private TextField champPieces;
    @FXML private TextField champLoyer;

    @FXML
    public void enregistrerAppartement(ActionEvent e) {
        if (champAdresse.getText().isBlank() || champSurface.getText().isBlank()
                || champPieces.getText().isBlank() || champLoyer.getText().isBlank()) {
            new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs.").showAndWait();
            return;
        }
        try {
            Appartement a = new Appartement(
                0,
                champAdresse.getText(),
                Integer.parseInt(champSurface.getText().trim()),
                Integer.parseInt(champPieces.getText().trim()),
                Double.parseDouble(champLoyer.getText().trim())
            );
            AppartementDAO.ajouter(a, SessionManager.getUtilisateur().getId());
            NavigationUtil.naviguerVers("appartements");
        } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.ERROR, "Surface, pièces et loyer doivent être des nombres.").showAndWait();
        }
    }

    @FXML
    public void annuler(ActionEvent e) { NavigationUtil.naviguerVers("appartements"); }
}
