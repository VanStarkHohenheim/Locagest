package com.gestionbaux.dao;

import com.gestionbaux.modele.Locataire;
import com.gestionbaux.util.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocataireDAO {

    public static List<Locataire> getTous() {
        List<Locataire> liste = new ArrayList<>();
        String sql = "SELECT * FROM locataires";
        try (Statement stmt = DatabaseManager.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                liste.add(new Locataire(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("email"),
                    rs.getString("telephone")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erreur chargement locataires : " + e.getMessage());
        }
        return liste;
    }

    public static void ajouter(Locataire l) {
        String sql = "INSERT INTO locataires (nom, prenom, email, telephone) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            stmt.setString(1, l.getNom());
            stmt.setString(2, l.getPrenom());
            stmt.setString(3, l.getEmail());
            stmt.setString(4, l.getTelephone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur ajout locataire : " + e.getMessage());
        }
    }

    public static void supprimer(int id) {
        String sql = "DELETE FROM locataires WHERE id = ?";
        try (PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur suppression locataire : " + e.getMessage());
        }
    }
}
