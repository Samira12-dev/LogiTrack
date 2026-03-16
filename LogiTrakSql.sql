create database logiTrack;
use logiTrack;
create table client(
id bigint primary key auto_increment,
nom varchar(50) not null,
email varchar(100),
 telephone VARCHAR(20),
ville VARCHAR(100)
);
create table commande(
id bigint primary key auto_increment,
datecommande date,
statu varchar(50)
);
create table produit(
id bigint primary key auto_increment,
nom varchar(100),
category varchar(100),
prix DECIMAL(10,2),
quantitystock int
);
create table lignecommande(
id int primary key auto_increment,
quantity int
);