-- Création de la base
CREATE DATABASE banque_db;

-- Connexion à la base (dans psql)
\c banque_db

-- Types ENUM
CREATE TYPE type_compte AS ENUM ('Courant', 'Epargne', 'Entreprise');
CREATE TYPE statut_compte AS ENUM ('Actif', 'Inactif', 'Ferme');
CREATE TYPE type_transaction AS ENUM ('Depot', 'Retrait', 'Virement');

-- Table Banque
CREATE TABLE Banque (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) UNIQUE NOT NULL,
    pays VARCHAR(50) NOT NULL,
    ville VARCHAR(50),
    dateCreation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    nombreClients INT DEFAULT 0
);

-- Table Client
CREATE TABLE Client (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    numeroClient VARCHAR(20) UNIQUE NOT NULL,
    banqueId INT NOT NULL REFERENCES Banque(id) ON DELETE CASCADE,
    dateInscription TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table Compte
CREATE TABLE Compte (
    id SERIAL PRIMARY KEY,
    numeroCompte VARCHAR(20) UNIQUE NOT NULL,
    solde DECIMAL(15, 2) DEFAULT 0.00,
    typeCompte  VARCHAR(20) NOT NULL,
    clientId INT NOT NULL REFERENCES Client(id) ON DELETE CASCADE,
    dateOuverture TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    statut statut_compte DEFAULT 'Actif'
);

-- Table TransactionBancaire
CREATE TABLE TransactionBancaire (
    id SERIAL PRIMARY KEY,
    montant DECIMAL(15, 2) NOT NULL,
    typeTransaction type_transaction NOT NULL,
    compteId INT NOT NULL REFERENCES Compte(id) ON DELETE CASCADE,
    dateTransaction TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description TEXT
);
