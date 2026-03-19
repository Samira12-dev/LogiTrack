create database LogiTrack;
use LogiTrack;

-- TABLE CLIENT
create table client(
                       id bigint primary key auto_increment,
                       nom varchar(50) not null,
                       email varchar(100),
                       telephone varchar(20),
                       ville varchar(100)
);

-- TABLE COMMANDE
create table commande(
                         id bigint primary key auto_increment,
                         datecommande date,
                         statut varchar(50),
                         client_id bigint,

                         constraint fk_commande_client
                             foreign key (client_id) references client(id)
                                 on delete cascade
);

-- TABLE PRODUIT
create table produit(
                        id bigint primary key auto_increment,
                        nom varchar(100),
                        category varchar(100),
                        prix decimal(10,2),
                        quantitystock int
);

-- TABLE LIGNECOMMANDE
create table lignecommande(
                              id int primary key auto_increment,
                              quantity int,
                              commande_id bigint,
                              produit_id bigint,

                              constraint fk_lignecommande_commande
                                  foreign key (commande_id) references commande(id)
                                      on delete cascade,

                              constraint fk_lignecommande_produit
                                  foreign key (produit_id) references produit(id)
                                      on delete cascade
);