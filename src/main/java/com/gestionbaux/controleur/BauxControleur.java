package com.gestionbaux.controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.gestionbaux.modele.Bail;
import com.gestionbaux.util.NavigationUtil;

public class BauxControleur {

    @FXML private TableView<Bail> tableBaux;
    @FXML private Button btnSupprimer;

    static ObservableList<Bail> baux = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        TableColumn<Bail, String> colLocataire = new TableColumn<>("Locataire");
        colLocataire.setCellValueFactory(new PropertyValueFactory<>("locataire"));

        TableColumn<Bail, String> colAppartement = new TableColumn<>("Appartement");
        colAppartement.setCellValueFactory(new PropertyValueFactory<>("appartement"));

        TableColumn<Bail, String> colDateDebut = new TableColumn<>("Date début");
        colDateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));

        TableColumn<Bail, String> colDateFin = new TableColumn<>("Date fin");
        colDateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));

        TableColumn<Bail, Double> colLoyer = new TableColumn<>("Loyer (€)");
        colLoyer.setCellValueFactory(new PropertyValueFactory<>("loyerMensuel"));

        tableBaux.getColumns().addAll(colLocataire, colAppartement, colDateDebut, colDateFin, colLoyer);
        tableBaux.setItems(baux);

        // Désactive le bouton tant qu'aucune ligne n'est sélectionnée
        btnSupprimer.disableProperty().bind(
            tableBaux.getSelectionModel().selectedItemProperty().isNull()
        );
    }

    @FXML
    private void retourAccueil() {
        NavigationUtil.naviguerVers("accueil");
    }

    @FXML
    public void formubail(ActionEvent actionEvent) {
        NavigationUtil.naviguerVers("formbaux");
    }

    @FXML
    public void supprimerBail(ActionEvent actionEvent) {
        Bail selectionne = tableBaux.getSelectionModel().getSelectedItem();

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Supprimer ce bail ?");
        confirmation.setContentText(selectionne.toString());

        confirmation.showAndWait().ifPresent(reponse -> {
            if (reponse == ButtonType.OK) {
                baux.remove(selectionne);
            }
        });
    }
}
