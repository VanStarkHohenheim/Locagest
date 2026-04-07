package com.gestionbaux.dao;

import com.gestionbaux.modele.Appartement;
import com.gestionbaux.util.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppartementDAO {

    public static List<Appartement> getTous() {
        return charger("SELECT * FROM appartements", -1);
    }

    public static List<Appartement> getParBailleur(int bailleurId) {
        return charger("SELECT * FROM appartements WHERE bailleur_id = ?", bailleurId);
    }

    private static List<Appartement> charger(String sql, int param) {
        List<Appartement> liste = new ArrayList<>();
        try {
            PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql);
            if (param >= 0) stmt.setInt(1, param);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                liste.add(new Appartement(
                    rs.getInt("id"),
                    rs.getString("adresse"),
                    rs.getInt("surface"),
                    rs.getInt("nombre_pieces"),
                    rs.getDouble("loyer")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erreur chargement appartements : " + e.getMessage());
        }
        return liste;
    }

    public static void ajouter(Appartement a, int bailleurId) {
        String sql = "INSERT INTO appartements (adresse, surface, nombre_pieces, loyer, bailleur_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            stmt.setString(1, a.getAdresse());
            stmt.setInt(2, a.getSurface());
            stmt.setInt(3, a.getNombrePieces());
            stmt.setDouble(4, a.getLoyer());
            stmt.setInt(5, bailleurId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur ajout appartement : " + e.getMessage());
        }
    }

    public static void supprimer(int id) {
        String sql = "DELETE FROM appartements WHERE id = ?";
        try (PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur suppression appartement : " + e.getMessage());
        }
    }
}
