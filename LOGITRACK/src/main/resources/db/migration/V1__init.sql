CREATE TABLE client (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nom VARCHAR(255) NOT NULL,
                        email VARCHAR(255) NOT NULL,
                        telephone VARCHAR(50) NOT NULL,
                        ville VARCHAR(100) NOT NULL
);

CREATE TABLE produit (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nom VARCHAR(255) NOT NULL,
                         category VARCHAR(100) NOT NULL,
                         price DOUBLE NOT NULL,
                         quantity_stock INT NOT NULL
);

CREATE TABLE commande (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          datecommand DATE NOT NULL,
                          command_statut VARCHAR(50) NOT NULL,
                          client_id BIGINT
);

CREATE TABLE ligne_commande (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               quantity INT NOT NULL,
                               produit_id BIGINT,
                               commande_id BIGINT
);

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       nom VARCHAR(255) NOT NULL,
                       prenom VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL
);