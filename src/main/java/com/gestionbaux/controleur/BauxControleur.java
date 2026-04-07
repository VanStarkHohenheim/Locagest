package com.gestionbaux.controleur;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.gestionbaux.dao.BailDAO;
import com.gestionbaux.modele.Bail;
import com.gestionbaux.modele.Role;
import com.gestionbaux.modele.Utilisateur;
import com.gestionbaux.util.NavigationUtil;
import com.gestionbaux.util.SessionManager;

public class BauxControleur {

    @FXML private TableView<Bail> tableBaux;
    @FXML private Button btnAjouter;
    @FXML private Button btnSupprimer;

    @FXML
    public void initialize() {
        TableColumn<Bail, String> colLocataire   = new TableColumn<>("Locataire");
        TableColumn<Bail, String> colAppartement = new TableColumn<>("Appartement");
        TableColumn<Bail, String> colDateDebut   = new TableColumn<>("Date début");
        TableColumn<Bail, String> colDateFin     = new TableColumn<>("Date fin");
        TableColumn<Bail, Double> colLoyer       = new TableColumn<>("Loyer (€)");

        colLocataire.setCellValueFactory(new PropertyValueFactory<>("locataire"));
        colAppartement.setCellValueFactory(new PropertyValueFactory<>("appartement"));
        colDateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        colDateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        colLoyer.setCellValueFactory(new PropertyValueFactory<>("loyerMensuel"));

        tableBaux.getColumns().addAll(colLocataire, colAppartement, colDateDebut, colDateFin, colLoyer);
        btnSupprimer.disableProperty().bind(tableBaux.getSelectionModel().selectedItemProperty().isNull());

        Utilisateur u = SessionManager.getUtilisateur();
        if (u.getRole() == Role.LOCATAIRE) {
            btnAjouter.setVisible(false);  btnAjouter.setManaged(false);
            btnSupprimer.setVisible(false); btnSupprimer.setManaged(false);
        }

        chargerBaux();
    }

    private void chargerBaux() {
        Utilisateur u = SessionManager.getUtilisateur();
        var liste = switch (u.getRole()) {
            case ADMIN     -> BailDAO.getTous();
            case BAILLEUR  -> BailDAO.getParBailleur(u.getId());
            case LOCATAIRE -> BailDAO.getParLocataire(u.getId());
        };
        tableBaux.setItems(FXCollections.observableArrayList(liste));
    }

    @FXML
    public void formubail(ActionEvent e) { NavigationUtil.naviguerVers("formbaux"); }

    @FXML
    public void supprimerBail(ActionEvent e) {
        Bail selection = tableBaux.getSelectionModel().getSelectedItem();
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Supprimer ce bail ?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(rep -> {
            if (rep == ButtonType.YES) {
                BailDAO.supprimer(selection.getId());
                chargerBaux();
            }
        });
    }

    @FXML
    private void retourAccueil() { NavigationUtil.naviguerVers("accueil"); }
}
