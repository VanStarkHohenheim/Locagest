package com.gestionbaux.controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.gestionbaux.modele.Appartement;
import com.gestionbaux.util.NavigationUtil;

public class AppartementsControleur {

    @FXML private TableView<Appartement> tableAppartements;
    @FXML private Button btnSupprimer;

    static ObservableList<Appartement> appartements = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        TableColumn<Appartement, String> colAdresse = new TableColumn<>("Adresse");
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        TableColumn<Appartement, Integer> colSurface = new TableColumn<>("Surface (m²)");
        colSurface.setCellValueFactory(new PropertyValueFactory<>("surface"));

        TableColumn<Appartement, Integer> colPieces = new TableColumn<>("Pièces");
        colPieces.setCellValueFactory(new PropertyValueFactory<>("nombrePieces"));

        TableColumn<Appartement, Double> colLoyer = new TableColumn<>("Loyer (€)");
        colLoyer.setCellValueFactory(new PropertyValueFactory<>("loyer"));

        tableAppartements.getColumns().addAll(colAdresse, colSurface, colPieces, colLoyer);
        tableAppartements.setItems(appartements);

        // Désactive le bouton tant qu'aucune ligne n'est sélectionnée
        btnSupprimer.disableProperty().bind(
            tableAppartements.getSelectionModel().selectedItemProperty().isNull()
        );
    }

    @FXML
    private void retourAccueil() {
        NavigationUtil.naviguerVers("accueil");
    }

    @FXML
    public void ajouterAppartement(ActionEvent actionEvent) {
        NavigationUtil.naviguerVers("formappartement");
    }

    @FXML
    public void supprimerAppartement(ActionEvent actionEvent) {
        Appartement selectionne = tableAppartements.getSelectionModel().getSelectedItem();

        // Demande confirmation avant de supprimer
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Supprimer cet appartement ?");
        confirmation.setContentText(selectionne.getAdresse());

        confirmation.showAndWait().ifPresent(reponse -> {
            if (reponse == ButtonType.OK) {
                appartements.remove(selectionne);
            }
        });
    }
}
