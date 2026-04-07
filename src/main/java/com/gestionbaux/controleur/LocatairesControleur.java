package com.gestionbaux.controleur;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.gestionbaux.dao.LocataireDAO;
import com.gestionbaux.modele.Locataire;
import com.gestionbaux.modele.Role;
import com.gestionbaux.util.NavigationUtil;
import com.gestionbaux.util.SessionManager;

public class LocatairesControleur {

    @FXML private TableView<Locataire> tableLocataires;
    @FXML private Button btnAjouter;
    @FXML private Button btnSupprimer;

    @FXML
    public void initialize() {
        TableColumn<Locataire, String> colNom       = new TableColumn<>("Nom");
        TableColumn<Locataire, String> colPrenom    = new TableColumn<>("Prénom");
        TableColumn<Locataire, String> colEmail     = new TableColumn<>("Email");
        TableColumn<Locataire, String> colTelephone = new TableColumn<>("Téléphone");

        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        tableLocataires.getColumns().addAll(colNom, colPrenom, colEmail, colTelephone);
        btnSupprimer.disableProperty().bind(tableLocataires.getSelectionModel().selectedItemProperty().isNull());

        // Seul l'ADMIN peut ajouter/supprimer des locataires
        if (SessionManager.getUtilisateur().getRole() != Role.ADMIN) {
            btnAjouter.setVisible(false);  btnAjouter.setManaged(false);
            btnSupprimer.setVisible(false); btnSupprimer.setManaged(false);
        }

        tableLocataires.setItems(FXCollections.observableArrayList(LocataireDAO.getTous()));
    }

    @FXML
    public void ajouterLocataire(ActionEvent e) {
        NavigationUtil.naviguerVers("formlocataire");
    }

    @FXML
    public void supprimerLocataire(ActionEvent e) {
        Locataire selection = tableLocataires.getSelectionModel().getSelectedItem();
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Supprimer : " + selection.getPrenom() + " " + selection.getNom() + " ?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(rep -> {
            if (rep == ButtonType.YES) {
                LocataireDAO.supprimer(selection.getId());
                tableLocataires.setItems(FXCollections.observableArrayList(LocataireDAO.getTous()));
            }
        });
    }

    @FXML
    private void retourAccueil() { NavigationUtil.naviguerVers("accueil"); }
}
