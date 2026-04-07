package com.gestionbaux.dao;

import com.gestionbaux.modele.Role;
import com.gestionbaux.modele.Utilisateur;
import com.gestionbaux.util.DatabaseManager;

import java.sql.*;

public class UtilisateurDAO {

    public static Utilisateur authentifier(String login, String motDePasse) {
        String sql = "SELECT * FROM utilisateurs WHERE login = ? AND mot_de_passe = ?";
        try (PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, motDePasse);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Utilisateur(
                    rs.getInt("id"),
                    rs.getString("login"),
                    rs.getString("mot_de_passe"),
                    Role.valueOf(rs.getString("role"))
                );
            }
        } catch (SQLException e) {
            System.err.println("Erreur authentification : " + e.getMessage());
        }
        return null;
    }

    public static boolean loginExiste(String login) {
        String sql = "SELECT COUNT(*) FROM utilisateurs WHERE login = ?";
        try (PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.err.println("Erreur vérification login : " + e.getMessage());
        }
        return false;
    }

    public static void ajouter(String login, String motDePasse, Role role) {
        String sql = "INSERT INTO utilisateurs (login, mot_de_passe, role) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, motDePasse);
            stmt.setString(3, role.name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur création compte : " + e.getMessage());
        }
    }
}
