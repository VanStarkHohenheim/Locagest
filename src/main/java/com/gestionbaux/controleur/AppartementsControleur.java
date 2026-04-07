package com.gestionbaux.controleur;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.gestionbaux.dao.AppartementDAO;
import com.gestionbaux.modele.Appartement;
import com.gestionbaux.modele.Role;
import com.gestionbaux.modele.Utilisateur;
import com.gestionbaux.util.NavigationUtil;
import com.gestionbaux.util.SessionManager;

public class AppartementsControleur {

    @FXML private TableView<Appartement> tableAppartements;
    @FXML private Button btnAjouter;
    @FXML private Button btnSupprimer;

    @FXML
    public void initialize() {
        TableColumn<Appartement, String>  colAdresse = new TableColumn<>("Adresse");
        TableColumn<Appartement, Integer> colSurface = new TableColumn<>("Surface (m²)");
        TableColumn<Appartement, Integer> colPieces  = new TableColumn<>("Pièces");
        TableColumn<Appartement, Double>  colLoyer   = new TableColumn<>("Loyer (€)");

        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colSurface.setCellValueFactory(new PropertyValueFactory<>("surface"));
        colPieces.setCellValueFactory(new PropertyValueFactory<>("nombrePieces"));
        colLoyer.setCellValueFactory(new PropertyValueFactory<>("loyer"));

        tableAppartements.getColumns().addAll(colAdresse, colSurface, colPieces, colLoyer);
        btnSupprimer.disableProperty().bind(tableAppartements.getSelectionModel().selectedItemProperty().isNull());

        Utilisateur u = SessionManager.getUtilisateur();
        if (u.getRole() == Role.LOCATAIRE) {
            btnAjouter.setVisible(false);  btnAjouter.setManaged(false);
            btnSupprimer.setVisible(false); btnSupprimer.setManaged(false);
        }

        chargerAppartements();
    }

    private void chargerAppartements() {
        Utilisateur u = SessionManager.getUtilisateur();
        var liste = (u.getRole() == Role.BAILLEUR)
            ? AppartementDAO.getParBailleur(u.getId())
            : AppartementDAO.getTous();
        tableAppartements.setItems(FXCollections.observableArrayList(liste));
    }

    @FXML
    public void ajouterAppartement(ActionEvent e) {
        NavigationUtil.naviguerVers("formappartement");
    }

    @FXML
    public void supprimerAppartement(ActionEvent e) {
        Appartement selection = tableAppartements.getSelectionModel().getSelectedItem();
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Supprimer : " + selection.getAdresse() + " ?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(rep -> {
            if (rep == ButtonType.YES) {
                AppartementDAO.supprimer(selection.getId());
                chargerAppartements();
            }
        });
    }

    @FXML
    private void retourAccueil() { NavigationUtil.naviguerVers("accueil"); }
}
