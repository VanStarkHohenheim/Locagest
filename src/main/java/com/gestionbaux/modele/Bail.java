package com.gestionbaux.modele;

import java.time.LocalDate;

/**
 * Représente un bail locatif.
 * Un bail relie un locataire à un appartement pour une période donnée.
 */
public class Bail {

    private int id;
    private Appartement appartement;
    private Locataire locataire;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private double loyerMensuel;

    public Bail(int id, Appartement appartement, Locataire locataire,
                LocalDate dateDebut, LocalDate dateFin, double loyerMensuel) {
        this.id = id;
        this.appartement = appartement;
        this.locataire = locataire;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.loyerMensuel = loyerMensuel;
    }

    // --- Getters ---

    public int getId() { return id; }
    public Appartement getAppartement() { return appartement; }
    public Locataire getLocataire() { return locataire; }
    public LocalDate getDateDebut() { return dateDebut; }
    public LocalDate getDateFin() { return dateFin; }
    public double getLoyerMensuel() { return loyerMensuel; }

    // --- Setters ---

    public void setAppartement(Appartement appartement) { this.appartement = appartement; }
    public void setLocataire(Locataire locataire) { this.locataire = locataire; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }
    public void setLoyerMensuel(double loyerMensuel) { this.loyerMensuel = loyerMensuel; }

    @Override
    public String toString() {
        return "Bail #" + id + " - " + locataire + " / " + appartement;
    }
}
