package com.gestionbaux.util;

import java.sql.*;

public class DatabaseManager {

    private static final String URL = "jdbc:sqlite:locagest.db";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL);
        }
        return connection;
    }

    public static void initialiser() {
        try {
            creerTables();
            insererDonneesInitiales();
        } catch (SQLException e) {
            System.err.println("Erreur initialisation BDD : " + e.getMessage());
        }
    }

    private static void creerTables() throws SQLException {
        try (Statement stmt = getConnection().createStatement()) {
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS utilisateurs (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    login TEXT NOT NULL UNIQUE,
                    mot_de_passe TEXT NOT NULL,
                    role TEXT NOT NULL
                )
            """);
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS appartements (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    adresse TEXT NOT NULL,
                    surface INTEGER NOT NULL,
                    nombre_pieces INTEGER NOT NULL,
                    loyer REAL NOT NULL,
                    bailleur_id INTEGER,
                    FOREIGN KEY (bailleur_id) REFERENCES utilisateurs(id)
                )
            """);
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS locataires (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nom TEXT NOT NULL,
                    prenom TEXT NOT NULL,
                    email TEXT NOT NULL,
                    telephone TEXT NOT NULL,
                    utilisateur_id INTEGER,
                    FOREIGN KEY (utilisateur_id) REFERENCES utilisateurs(id)
                )
            """);
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS baux (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    appartement_id INTEGER NOT NULL,
                    locataire_id INTEGER NOT NULL,
                    date_debut TEXT NOT NULL,
                    date_fin TEXT NOT NULL,
                    loyer_mensuel REAL NOT NULL,
                    FOREIGN KEY (appartement_id) REFERENCES appartements(id),
                    FOREIGN KEY (locataire_id) REFERENCES locataires(id)
                )
            """);
        }
    }

    private static void insererDonneesInitiales() throws SQLException {
        // Vérifie si les données de départ existent déjà
        try (ResultSet rs = getConnection().createStatement()
                .executeQuery("SELECT COUNT(*) FROM utilisateurs WHERE login = 'admin'")) {
            if (rs.getInt(1) > 0) return;
        }

        try (Statement stmt = getConnection().createStatement()) {
            // Comptes utilisateurs
            stmt.executeUpdate("INSERT INTO utilisateurs (login, mot_de_passe, role) VALUES ('admin', 'admin123', 'ADMIN')");
            stmt.executeUpdate("INSERT INTO utilisateurs (login, mot_de_passe, role) VALUES ('bailleur1', 'bailleur123', 'BAILLEUR')");
            stmt.executeUpdate("INSERT INTO utilisateurs (login, mot_de_passe, role) VALUES ('locataire1', 'locataire123', 'LOCATAIRE')");

            // Appartement exemple lié au bailleur (id=2)
            stmt.executeUpdate("INSERT INTO appartements (adresse, surface, nombre_pieces, loyer, bailleur_id) VALUES ('12 rue de la Paix, Paris', 45, 2, 850.0, 2)");

            // Locataire exemple lié au compte locataire (id=3)
            stmt.executeUpdate("INSERT INTO locataires (nom, prenom, email, telephone, utilisateur_id) VALUES ('Dupont', 'Jean', 'jean.dupont@email.com', '0612345678', 3)");

            // Bail exemple
            stmt.executeUpdate("INSERT INTO baux (appartement_id, locataire_id, date_debut, date_fin, loyer_mensuel) VALUES (1, 1, '2024-01-01', '2025-01-01', 850.0)");
        }
    }
}
