package com.gestionbaux.controleur;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.gestionbaux.dao.UtilisateurDAO;
import com.gestionbaux.modele.Role;
import com.gestionbaux.util.NavigationUtil;

public class InscriptionControleur {

    @FXML private TextField     champLogin;
    @FXML private PasswordField champMotDePasse;
    @FXML private PasswordField champConfirmation;
    @FXML private ComboBox<Role> champRole;
    @FXML private Label         labelErreur;

    @FXML
    public void initialize() {
        champRole.setItems(FXCollections.observableArrayList(Role.values()));
    }

    @FXML
    private void sInscrire(ActionEvent e) {
        if (champLogin.getText().isBlank() || champMotDePasse.getText().isBlank()
                || champConfirmation.getText().isBlank() || champRole.getValue() == null) {
            labelErreur.setText("Veuillez remplir tous les champs.");
            return;
        }
        if (!champMotDePasse.getText().equals(champConfirmation.getText())) {
            labelErreur.setText("Les mots de passe ne correspondent pas.");
            return;
        }
        if (UtilisateurDAO.loginExiste(champLogin.getText())) {
            labelErreur.setText("Cet identifiant est déjà utilisé.");
            return;
        }

        UtilisateurDAO.ajouter(champLogin.getText(), champMotDePasse.getText(), champRole.getValue());
        NavigationUtil.naviguerVers("connexion");
    }

    @FXML
    private void retourConnexion(ActionEvent e) {
        NavigationUtil.naviguerVers("connexion");
    }
}
