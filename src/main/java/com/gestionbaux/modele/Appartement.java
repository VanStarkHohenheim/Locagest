package com.gestionbaux.modele;

/**
 * Représente un appartement géré dans l'application.
 * Contient les informations essentielles de chaque logement.
 */
public class Appartement {

    private int id;
    private String adresse;
    private int surface;        // en m²
    private int nombrePieces;
    private double loyer;       // loyer mensuel en euros

    public Appartement(int id, String adresse, int surface, int nombrePieces, double loyer) {
        this.id = id;
        this.adresse = adresse;
        this.surface = surface;
        this.nombrePieces = nombrePieces;
        this.loyer = loyer;
    }

    // --- Getters ---

    public int getId() { return id; }
    public String getAdresse() { return adresse; }
    public int getSurface() { return surface; }
    public int getNombrePieces() { return nombrePieces; }
    public double getLoyer() { return loyer; }

    // --- Setters ---

    public void setAdresse(String adresse) { this.adresse = adresse; }
    public void setSurface(int surface) { this.surface = surface; }
    public void setNombrePieces(int nombrePieces) { this.nombrePieces = nombrePieces; }
    public void setLoyer(double loyer) { this.loyer = loyer; }

    @Override
    public String toString() {
        return "Appartement #" + id + " - " + adresse;
    }
}
