package com.gestionbaux.dao;

import com.gestionbaux.modele.Appartement;
import com.gestionbaux.modele.Bail;
import com.gestionbaux.modele.Locataire;
import com.gestionbaux.util.DatabaseManager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BailDAO {

    private static final String SELECT_BASE = """
        SELECT b.*, a.adresse, a.surface, a.nombre_pieces, a.loyer AS loyer_appart,
               l.nom, l.prenom, l.email, l.telephone
        FROM baux b
        JOIN appartements a ON b.appartement_id = a.id
        JOIN locataires l   ON b.locataire_id   = l.id
    """;

    public static List<Bail> getTous() {
        return charger(SELECT_BASE, -1);
    }

    public static List<Bail> getParBailleur(int bailleurId) {
        return charger(SELECT_BASE + " WHERE a.bailleur_id = ?", bailleurId);
    }

    public static List<Bail> getParLocataire(int utilisateurId) {
        return charger(SELECT_BASE + " WHERE l.utilisateur_id = ?", utilisateurId);
    }

    private static List<Bail> charger(String sql, int param) {
        List<Bail> liste = new ArrayList<>();
        try {
            PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql);
            if (param >= 0) stmt.setInt(1, param);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                liste.add(depuisResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Erreur chargement baux : " + e.getMessage());
        }
        return liste;
    }

    private static Bail depuisResultSet(ResultSet rs) throws SQLException {
        Appartement appartement = new Appartement(
            rs.getInt("appartement_id"),
            rs.getString("adresse"),
            rs.getInt("surface"),
            rs.getInt("nombre_pieces"),
            rs.getDouble("loyer_appart")
        );
        Locataire locataire = new Locataire(
            rs.getInt("locataire_id"),
            rs.getString("nom"),
            rs.getString("prenom"),
            rs.getString("email"),
            rs.getString("telephone")
        );
        return new Bail(
            rs.getInt("id"),
            appartement,
            locataire,
            LocalDate.parse(rs.getString("date_debut")),
            LocalDate.parse(rs.getString("date_fin")),
            rs.getDouble("loyer_mensuel")
        );
    }

    public static void ajouter(Bail bail) {
        String sql = "INSERT INTO baux (appartement_id, locataire_id, date_debut, date_fin, loyer_mensuel) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, bail.getAppartement().getId());
            stmt.setInt(2, bail.getLocataire().getId());
            stmt.setString(3, bail.getDateDebut().toString());
            stmt.setString(4, bail.getDateFin().toString());
            stmt.setDouble(5, bail.getLoyerMensuel());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur ajout bail : " + e.getMessage());
        }
    }

    public static void supprimer(int id) {
        String sql = "DELETE FROM baux WHERE id = ?";
        try (PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur suppression bail : " + e.getMessage());
        }
    }
}
