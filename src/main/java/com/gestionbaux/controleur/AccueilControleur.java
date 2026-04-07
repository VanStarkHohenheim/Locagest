package com.gestionbaux.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import com.gestionbaux.modele.Utilisateur;
import com.gestionbaux.util.NavigationUtil;
import com.gestionbaux.util.SessionManager;

public class AccueilControleur {

    @FXML private Button btnAppartements;
    @FXML private Button btnLocataires;
    @FXML private Button btnBaux;
    @FXML private Label labelUtilisateur;

    @FXML
    public void initialize() {
        Utilisateur u = SessionManager.getUtilisateur();
        labelUtilisateur.setText(u.getLogin() + " (" + u.getRole() + ")");

        switch (u.getRole()) {
            case BAILLEUR -> masquer(btnLocataires);
            case LOCATAIRE -> {
                masquer(btnAppartements);
                masquer(btnLocataires);
                btnBaux.setText("Mon Bail");
            }
        }
    }

    private void masquer(Button btn) {
        btn.setVisible(false);
        btn.setManaged(false);
    }

    @FXML private void allerAppartements() { NavigationUtil.naviguerVers("appartements"); }
    @FXML private void allerLocataires()   { NavigationUtil.naviguerVers("locataires"); }
    @FXML private void allerBaux()         { NavigationUtil.naviguerVers("baux"); }

    @FXML
    private void seDeconnecter() {
        SessionManager.deconnecter();
        NavigationUtil.naviguerVers("connexion");
    }
}
