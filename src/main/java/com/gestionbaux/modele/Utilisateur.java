package com.gestionbaux.modele;

public class Utilisateur {

    private int id;
    private String login;
    private String motDePasse;
    private Role role;

    public Utilisateur(int id, String login, String motDePasse, Role role) {
        this.id = id;
        this.login = login;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    public int getId()         { return id; }
    public String getLogin()   { return login; }
    public Role getRole()      { return role; }
}
