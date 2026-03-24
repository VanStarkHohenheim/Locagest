# LocaGest

Application de gestion de baux locatifs développée en Java avec JavaFX.

## Fonctionnalités

- **Appartements** — ajouter, lister et supprimer des appartements
- **Locataires** — ajouter, lister et supprimer des locataires
- **Baux** — créer, lister et supprimer des baux locatifs
- Validation des formulaires (champs obligatoires, formats numériques)
- Confirmation avant toute suppression

## Technologies

- Java 21
- JavaFX 21
- Maven

## Lancer l'application

```bash
mvn javafx:run
```

## Structure du projet

```
src/
└── main/
    ├── java/com/gestionbaux/
    │   ├── Main.java                      # Point d'entrée
    │   ├── controleur/                    # Contrôleurs JavaFX
    │   │   ├── AccueilControleur.java
    │   │   ├── AppartementsControleur.java
    │   │   ├── FormAppartementControleur.java
    │   │   ├── LocatairesControleur.java
    │   │   ├── FormLocataireControleur.java
    │   │   ├── BauxControleur.java
    │   │   └── FormBauxControleur.java
    │   ├── modele/                        # Classes métier
    │   │   ├── Appartement.java
    │   │   ├── Locataire.java
    │   │   └── Bail.java
    │   └── util/
    │       └── NavigationUtil.java        # Navigation entre les vues
    └── resources/com/gestionbaux/vue/    # Fichiers FXML (interfaces)
        ├── accueil.fxml
        ├── appartements.fxml
        ├── formappartement.fxml
        ├── locataires.fxml
        ├── formlocataire.fxml
        ├── baux.fxml
        └── formbaux.fxml
```

## Remarque

Les données sont stockées en mémoire uniquement. Elles sont perdues à la fermeture de l'application.
