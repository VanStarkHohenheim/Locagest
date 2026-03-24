package com.gestionbaux.modele;

/**
 * Représente un locataire pouvant être associé à un bail.
 */
public class Locataire {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    public Locataire(int id, String nom, String prenom, String email, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
    }

    // --- Getters ---

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }

    // --- Setters ---

    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setEmail(String email) { this.email = email; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    @Override
    public String toString() {
        return prenom + " " + nom;
    }
}
