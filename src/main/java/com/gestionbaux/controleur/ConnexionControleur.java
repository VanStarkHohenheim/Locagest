package com.gestionbaux.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.gestionbaux.dao.UtilisateurDAO;
import com.gestionbaux.modele.Utilisateur;
import com.gestionbaux.util.NavigationUtil;
import com.gestionbaux.util.SessionManager;

public class ConnexionControleur {

    @FXML private TextField champLogin;
    @FXML private PasswordField champMotDePasse;
    @FXML private Label labelErreur;

    @FXML
    private void seConnecter() {
        if (champLogin.getText().isBlank() || champMotDePasse.getText().isBlank()) {
            labelErreur.setText("Veuillez remplir tous les champs.");
            return;
        }

        Utilisateur u = UtilisateurDAO.authentifier(champLogin.getText(), champMotDePasse.getText());
        if (u == null) {
            labelErreur.setText("Identifiants incorrects.");
            return;
        }

        SessionManager.connecter(u);
        NavigationUtil.naviguerVers("accueil");
    }

    @FXML
    private void allerInscription() {
        NavigationUtil.naviguerVers("inscription");
    }
}
