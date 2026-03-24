package com.gestionbaux.controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.gestionbaux.modele.Locataire;
import com.gestionbaux.util.NavigationUtil;

public class LocatairesControleur {

    @FXML private TableView<Locataire> tableLocataires;
    @FXML private Button btnSupprimer;

    static ObservableList<Locataire> locataires = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        TableColumn<Locataire, String> colNom = new TableColumn<>("Nom");
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Locataire, String> colPrenom = new TableColumn<>("Prénom");
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        TableColumn<Locataire, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Locataire, String> colTelephone = new TableColumn<>("Téléphone");
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        tableLocataires.getColumns().addAll(colNom, colPrenom, colEmail, colTelephone);
        tableLocataires.setItems(locataires);

        // Désactive le bouton tant qu'aucune ligne n'est sélectionnée
        btnSupprimer.disableProperty().bind(
            tableLocataires.getSelectionModel().selectedItemProperty().isNull()
        );
    }

    @FXML
    private void retourAccueil() {
        NavigationUtil.naviguerVers("accueil");
    }

    @FXML
    public void ajouterLocataire(ActionEvent actionEvent) {
        NavigationUtil.naviguerVers("formlocataire");
    }

    @FXML
    public void supprimerLocataire(ActionEvent actionEvent) {
        Locataire selectionne = tableLocataires.getSelectionModel().getSelectedItem();

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Supprimer ce locataire ?");
        confirmation.setContentText(selectionne.getPrenom() + " " + selectionne.getNom());

        confirmation.showAndWait().ifPresent(reponse -> {
            if (reponse == ButtonType.OK) {
                locataires.remove(selectionne);
            }
        });
    }
}
