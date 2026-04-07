package com.gestionbaux.util;

import com.gestionbaux.modele.Utilisateur;

public class SessionManager {

    private static Utilisateur utilisateurCourant;

    public static void connecter(Utilisateur u)  { utilisateurCourant = u; }
    public static void deconnecter()              { utilisateurCourant = null; }
    public static Utilisateur getUtilisateur()    { return utilisateurCourant; }
}
