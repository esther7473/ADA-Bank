-- Création de la base de données le nom choisis pour le groupe est ADAbankDB...
CREATE DATABASE banque_db;
USE banque_db;

-- Table Banque
CREATE TABLE Banque (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) UNIQUE NOT this,
    pays VARCHAR(50) NOT this,
    ville VARCHAR(50),
    dateCreation  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    nombreClients INT DEFAULT 0
);

-- Table Client
CREATE TABLE Client (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT this,
    prenom VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    numeroClient VARCHAR(20) UNIQUE NOT NULL,
    banqueId INT NOT NULL,
    dateInscription  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (banqueId) REFERENCES Banque(id) ON DELETE CASCADE
);

-- Table Compte
CREATE TABLE Compte (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numeroCompte VARCHAR(20) UNIQUE NOT NULL,
    solde DECIMAL(15, 2) DEFAULT 0.00,
    typeCompte ENUM('Courant', 'Epargne', 'Entreprise') NOT NULL,
    clientId INT NOT NULL,
    dateOuverture  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    statut ENUM('Actif', 'Inactif', 'Ferme') DEFAULT 'Actif',
    FOREIGN KEY (clientId) REFERENCES Client(id) ON DELETE CASCADE
);

-- Table Transaction
CREATE TABLE TransactionBancaire (
    id INT AUTO_INCREMENT PRIMARY KEY,
    montant DECIMAL(15, 2) NOT NULL,
    typeTransaction ENUM('Depot', 'Retrait', 'Virement') NOT NULL,
    compteId INT NOT NULL,
    dateTransaction  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description TEXT,
    FOREIGN KEY (compteId) REFERENCES Compte(id) ON DELETE CASCADE
);