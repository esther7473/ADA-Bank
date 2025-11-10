

#  ADA BANK — Spring Boot Edition

##  Description

**ADA BANK** est une application web de gestion bancaire développée avec **Spring Boot**, conçue pour permettre aux institutions bancaires de gérer efficacement leurs **clients**, **comptes** et **transactions**.

---

##  Contexte

Le projet vise à fournir une solution complète pour la gestion bancaire, en combinant les principes de **sécurité**, **traçabilité** et **maintenabilité**, adaptés aux besoins des banques modernes.

---

##  Objectifs

* Créer un système de gestion bancaire complet et sécurisé
* Assurer la traçabilité des opérations (clients, comptes, transactions)
* Respecter les **design patterns** et bonnes pratiques Spring

---

##  Spécifications techniques

| Technologie / Outil | Version / Détails               |
| ------------------- | ------------------------------- |
| Langage             | **Java 17**                     |
| Framework           | **Spring Boot**                 |
| Base de données     | **PostgreSQL**                  |
| Build               | **Maven**                       |
| Sécurité            | Spring Security                 |
| Envoi d’emails      | Spring Boot Starter Mail        |
| IDE recommandé      | IntelliJ IDEA                   |


---

##  Architecture

L’application suit une architecture en couches.


##  Fonctionnalités principales

### 🔹 Gestion des banques

* **Inscription des banques** avec envoi automatique d’e-mail de bienvenue
* **Consultation** : liste des 15 banques avec le plus grand nombre de clients
* **Recherche** : filtrage par pays, ville ou autres critères

### 🔹 Gestion des clients

* **Inscription des clients** avec e-mail automatique
* **Consultation** : clients d’une banque donnée
* **Recherche multicritères** : nom, email, numéro client

### 🔹 Gestion des comptes

* **Ouverture et clôture** de comptes
* **Consultation** de la liste des comptes par client
* **Détails complets** d’un compte bancaire


Note : les variables d’environnement sont configurées dans `.env`,( **non versionné**) 


**Note** : Le projet est encore en cours de développement et certaines fonctionnalités restent à implémenter.
