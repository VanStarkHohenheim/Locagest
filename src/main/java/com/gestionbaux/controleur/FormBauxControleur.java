package com.gestionbaux.controleur;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import com.gestionbaux.dao.AppartementDAO;
import com.gestionbaux.dao.BailDAO;
import com.gestionbaux.dao.LocataireDAO;
import com.gestionbaux.modele.Appartement;
import com.gestionbaux.modele.Bail;
import com.gestionbaux.modele.Locataire;
import com.gestionbaux.modele.Role;
import com.gestionbaux.util.NavigationUtil;
import com.gestionbaux.util.SessionManager;

public class FormBauxControleur {

    @FXML private ComboBox<Locataire>   champLocataire;
    @FXML private ComboBox<Appartement> champAppartement;
    @FXML private DatePicker champDateDebut;
    @FXML private DatePicker champDateFin;
    @FXML private TextField  champLoyer;

    @FXML
    public void initialize() {
        var u = SessionManager.getUtilisateur();
        var appartements = (u.getRole() == Role.BAILLEUR)
            ? AppartementDAO.getParBailleur(u.getId())
            : AppartementDAO.getTous();

        champLocataire.setItems(FXCollections.observableArrayList(LocataireDAO.getTous()));
        champAppartement.setItems(FXCollections.observableArrayList(appartements));
    }

    @FXML
    public void enregistrerBail(ActionEvent e) {
        if (champLocataire.getValue() == null || champAppartement.getValue() == null
                || champDateDebut.getValue() == null || champDateFin.getValue() == null
                || champLoyer.getText().isBlank()) {
            new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs.").showAndWait();
            return;
        }
        try {
            Bail bail = new Bail(
                0,
                champAppartement.getValue(),
                champLocataire.getValue(),
                champDateDebut.getValue(),
                champDateFin.getValue(),
                Double.parseDouble(champLoyer.getText().trim())
            );
            BailDAO.ajouter(bail);
            NavigationUtil.naviguerVers("baux");
        } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.ERROR, "Le loyer doit être un nombre valide.").showAndWait();
        }
    }

    @FXML
    public void annuler(ActionEvent e) { NavigationUtil.naviguerVers("baux"); }
}
