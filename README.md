
# ADA BANK 🏦

## Description

**ADA BANK** est une application de gestion bancaire conçue pour permettre aux banques de gérer efficacement leurs clients, comptes et transactions.
Cette application propose une interface **console** intuitive pour l’administration des données bancaires et des banques.

---

## Contexte

Le projet consiste en le développement d’une solution complète pour la gestion bancaire, répondant aux besoins des banques modernes en termes de sécurité, traçabilité et robustesse.

---

## Objectifs 🎯

* Créer un système de gestion bancaire complet et sécurisé
* Implémenter une architecture en couches solide (3-tiers)
* Assurer la traçabilité de toutes les opérations effectuées
* Respecter les design patterns et bonnes pratiques de développement
* Garantir la qualité du code via des tests unitaires

---

## Spécifications techniques ⚙️

| Technologie     | Version / Outil |
| --------------- | --------------- |
| Langage         | Java SE 17      |
| Base de données | PostgreSQL      |
| Tests           | JUnit           |
| Build & gestion | Maven           |

---

## Architecture

L’application suit une architecture **3-tiers** :

* **Couche Models** : définition des entités métier (Banque, Client, Compte...)
* **Couche Services** : logique métier et règles de gestion
* **Couche DAO** : accès aux données (interactions avec la base PostgreSQL)

---

## Fonctionnalités principales

### Gestion des Banques 🏦

* Enregistrement : Inscription des banques par l’administrateur avec envoi automatique d’email de bienvenue
* Consultation : Visualisation des 15 banques ayant le plus de clients
* Recherche : Filtrage des banques par pays ou ville

### Gestion des Clients 👥

* Enregistrement : Inscription des clients avec envoi automatique d’email de bienvenue
* Recherche par banque : Liste des clients d’une banque spécifique
* Recherche multicritères : Filtrage par nom, email, numéro client

### Gestion des Comptes 💳

* Ouverture de compte : Création de nouveaux comptes clients
* Clôture de compte : Fermeture des comptes existants
* Consultation : Affichage de la liste des comptes par client
* Détails : Consultation des informations détaillées d’un compte

---

## Installation et utilisation 🚀

### Prérequis

* Java SE 17 installé
* PostgreSQL configuré et opérationnel
* Maven installé pour la gestion du projet

### Lancement

1. Cloner le projet
2. Configurer la connexion à la base PostgreSQL dans le fichier de configuration
3. Compiler et lancer avec Maven :

   ```bash
   mvn clean install
   mvn exec:java -Dexec.mainClass="com.ada_bank.Main"
   ```
4. Utiliser l’interface console pour naviguer dans les différentes fonctionnalités

---

## Tests et Qualité 🧪

* Des tests unitaires sont développés avec **JUnit** pour garantir la fiabilité de la logique métier.
* Le code respecte les bonnes pratiques et utilise les design patterns pour une maintenance facilitée.

---

## Contribution 🤝

Nous sommes ouverts aux contributions !
Pour contribuer :

* Forker le dépôt
* Créer une branche feature/bugfix
* Soumettre une merge request avec une description claire
* Assurer que tous les tests passent avant de soumettre

---

## Auteurs et Remerciements 🙏

Merci à toute l’équipe de développement pour son engagement dans ce projet.

---

## Licence

Ce projet est sous licence **MIT** (à adapter selon votre choix).

---

## Contact et Support

Pour toute question, problème ou suggestion, merci d’ouvrir une issue sur GitLab ou de contacter l’administrateur du projet.

---