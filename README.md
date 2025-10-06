# Gestion des Cartes Bancaires & Détection de Fraude

## Description

Ce projet simule la gestion des cartes bancaires (débit, crédit, prépayée) pour une banque, avec suivi des opérations et détection automatique de fraudes. Il s'appuie sur une architecture Java 17 en couches (Entity, DAO, Service, UI), avec persistance des données via JDBC & MySQL.

## Fonctionnalités principales

- **Gestion du cycle de vie d'une carte** (création, activation, suspension, blocage, renouvellement)
- **Gestion des clients** (création, recherche)
- **Enregistrement des opérations** (paiement, retrait, achat en ligne)
- **Historique et recherche d'opérations par carte ou client**
- **Détection automatique de fraudes** :
    - Montant anormalement élevé
    - Opérations rapprochées dans des lieux différents
    - Plusieurs opérations en un temps très court
- **Alerte et blocage automatique en cas de suspicion**
- **Statistiques et rapports** (top cartes, statistiques mensuelles…)
- **Import/export de fichiers Excel (simulé)**

## Architecture

- **Entity** : records & sealed classes (`Client`, `Carte`, `OperationCarte`, `AlerteFraude`)
- **DAO** : accès CRUD pour chaque entité, JDBC/MySQL
- **Service** : logique métier (gestion clients/cartes, opérations, fraude, rapports)
- **Util** : règles de fraude, utilitaires dates, génération numéros, etc.
- **UI** : menu textuel interactif (console Java)

## Structure des dossiers

```
src/
  ├── Entity/
  ├── DAO/
  ├── Service/
  ├── Util/
  └── UI/
```

## Prérequis

- Java 17+
- MySQL
- JDBC Driver MySQL
- IDE Java (IntelliJ, Eclipse, etc.)

## Installation

1. **Clone le dépôt**

    ```bash
    git clone https://github.com/adilaitelhoucine1/Gestion-des-Comptes-Bancaires-et-Transactions.git
    cd Gestion-des-Comptes-Bancaires-et-Transactions
    ```

2. **Configure la base MySQL**
    - Crée la base de données et les tables requises (voir `/sql/` si fourni).

3. **Configure le fichier de connexion JDBC**
    - Mets à jour l’URL, le user et le mot de passe dans la classe utilitaire JDBC.

4. **Compile et exécute le projet**

    ```bash
    # via IDE ou en ligne de commande :
    javac -d bin src/**/*.java
    java -cp bin UI.Main
    ```

## Utilisation

- Lancement du programme : menu interactif dans la console
- Choisissez parmi : création client, émission carte, opération, historique, analyse fraude, blocage carte, rapports, etc.

## Exemples de règles de fraude implémentées

- **Montant élevé** : opération > 10 000€
- **Opérations dans des lieux différents en <5min**
- **≥3 opérations en <1min**

## Contribuer

1. Fork ce repo
2. Crée une branche (`git checkout -b feature/ma-nouvelle-fonction`)
3. Commit tes changements
4. Push ta branche
5. Ouvre une Pull Request

## Auteurs

- [adilaitelhoucine1](https://github.com/adilaitelhoucine1)

## Licence

Projet open-source, sous licence MIT.
