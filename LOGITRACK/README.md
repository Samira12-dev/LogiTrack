# LogiTrack : Système de Gestion Logistique

## Description

**LogiTrack** est une application web de gestion logistique développée avec **Spring Boot** pour le backend et **React JS** pour le frontend.

L'application permet de gérer les principales opérations logistiques :

* Les clients
* Les produits
* Les commandes
* Les utilisateurs
* Les statistiques
* Le stock

L'application intègre également un système complet de **sécurisation avec Spring Security et JWT**, ainsi qu'une **gestion des rôles et des permissions**.

Les utilisateurs peuvent accéder aux fonctionnalités de l'application selon leur rôle :

* **ADMIN** : administration complète de l'application
* **MANAGER** : gestion et pilotage des opérations
* **AGENT** : suivi et exécution des tâches quotidiennes

---

# Fonctionnalités

## Gestion des Clients

Permettre de :

* Ajouter un client
* Modifier un client
* Supprimer un client
* Lister les clients
* Consulter un client
* Rechercher un client par nom

---

## Gestion des Produits

Permettre de :

* Ajouter un produit
* Modifier un produit
* Supprimer un produit
* Lister les produits
* Consulter un produit
* Rechercher les produits par catégorie
* Rechercher les produits selon leur prix
* Afficher les produits avec un stock faible
* Trier les produits
* Gérer la quantité en stock

---

## Gestion des Commandes

Permettre de :

* Créer une commande
* Consulter une commande
* Lister les commandes
* Ajouter un produit à une commande
* Modifier le statut d'une commande
* Afficher les commandes d'un client
* Filtrer les commandes par statut
* Consulter les détails d'une commande

### Statuts disponibles

* `EN_ATTENTE`
* `EXPEDIEE`
* `LIVREE`

---

## Pagination et Tri

L'application utilise des endpoints paginés afin d'améliorer l'affichage et les performances.

Fonctionnalités :

* Changement de page
* Choix du nombre d'éléments par page
* Affichage du nombre total d'éléments
* Tri des données

Le tri peut être effectué sur :

* Nom du client
* Nom du produit
* Prix
* Quantité en stock
* Date de commande
* Statut de commande

---

# Gestion des Utilisateurs

L'application possède une entité `User` contenant :

* `id`
* `nom`
* `prenom`
* `email`
* `password`
* `role`

L'email utilisateur doit être **unique**.

---

# Gestion des Rôles

L'application utilise trois rôles principaux :

```text
ADMIN
MANAGER
AGENT
```

## ADMIN

L'administrateur peut accéder à toutes les fonctionnalités :

* Gestion des utilisateurs
* Gestion des clients
* Gestion des produits
* Gestion des commandes
* Suppression des données
* Consultation des statistiques
* Gestion complète de l'application

---

## MANAGER

Le manager peut :

* Gérer les clients
* Gérer les produits
* Gérer les commandes
* Modifier le statut des commandes
* Consulter les statistiques
* Consulter les produits avec un stock faible

---

## AGENT

L'agent peut :

* Consulter les clients
* Consulter les produits
* Consulter les commandes
* Consulter les détails d'une commande
* Modifier le statut d'une commande selon les autorisations définies

### Règle générale

> **ADMIN administre l'application, MANAGER pilote les opérations, AGENT exécute et suit les tâches quotidiennes.**

---

# Les trois diagrammes UML

## Diagramme de Classes

Le diagramme de classes représente les principales entités de l'application :

* User
* Client
* Produit
* Commande
* LigneCommande

---

## Diagramme de Cas d'Utilisation

Les principaux acteurs sont :

* ADMIN
* MANAGER
* AGENT

Chaque rôle possède des permissions différentes selon les fonctionnalités de l'application.

---

## Diagramme de Séquence

### Exemple : Ajouter un produit

Le processus permet à un utilisateur autorisé de :

1. Accéder au formulaire produit
2. Saisir les informations du produit
3. Envoyer la requête au frontend
4. Le frontend envoie la requête à l'API
5. Spring Security vérifie le JWT
6. Le backend vérifie les permissions
7. Le service traite la demande
8. Le repository sauvegarde le produit
9. L'API retourne la réponse

---

### Exemple : Lister les produits

1. L'utilisateur accède à la page des produits
2. React envoie une requête HTTP
3. Axios ajoute automatiquement le JWT
4. Spring Security valide le token
5. Le contrôleur reçoit la requête
6. Le service récupère les produits
7. Le repository interroge la base de données
8. Les produits sont retournés au frontend
9. React affiche la liste

---

### Exemple : Supprimer un produit

1. L'utilisateur clique sur supprimer
2. Une confirmation est demandée
3. React envoie une requête `DELETE`
4. Axios ajoute le JWT
5. Spring Security vérifie l'authentification
6. Le rôle de l'utilisateur est vérifié
7. Le produit est supprimé si l'utilisateur possède les permissions nécessaires
8. Le frontend actualise la liste

---

### Exemple : Recherche des commandes par client

1. L'utilisateur sélectionne un client
2. React envoie une requête de recherche
3. Axios ajoute le JWT
4. Spring Security valide le token
5. Le backend recherche les commandes du client
6. Les résultats sont retournés
7. React affiche les commandes correspondantes

---

# LogiTrack : Part 2 - Sécurisation avec Spring Security & JWT

## Description

Dans cette deuxième partie du projet, l'application LogiTrack a été sécurisée avec **Spring Security** et **JWT (JSON Web Token)**.

L'objectif est de protéger les endpoints de l'API et de contrôler l'accès aux différentes fonctionnalités selon le rôle de l'utilisateur.

L'application utilise une authentification basée sur JWT ainsi qu'une gestion des rôles :

* ADMIN
* MANAGER
* AGENT

---

# Authentification Utilisateur

L'application possède les endpoints suivants :

```text
POST /api/auth/register
POST /api/auth/login
```

Fonctionnalités :

* Inscription utilisateur
* Connexion utilisateur
* Génération du JWT
* Validation du JWT
* Gestion de l'expiration du token
* Chiffrement des mots de passe
* Récupération du rôle utilisateur
* Gestion de la session côté frontend

---

# Register

Endpoint :

```http
POST /api/auth/register
```

Exemple :

```json
{
  "nom": "Admin",
  "prenom": "LogiTrack",
  "email": "admin@gmail.com",
  "password": "123456",
  "role": "ADMIN"
}
```

Le mot de passe est chiffré avant d'être enregistré dans la base de données.

---

# Login

Endpoint :

```http
POST /api/auth/login
```

Exemple :

```json
{
  "email": "admin@gmail.com",
  "password": "123456"
}
```

---

# Response Login

Après une authentification réussie, l'API retourne :

* JWT
* Informations utilisateur
* Rôle utilisateur

Exemple :

```json
{
  "token": "eyJhbGciOiJIUzI1Ni...",
  "user": {
    "id": 1,
    "nom": "Admin",
    "prenom": "LogiTrack",
    "email": "admin@gmail.com",
    "role": "ADMIN"
  }
}
```

---

# JWT

Le token JWT est envoyé dans le header HTTP :

```http
Authorization: Bearer <token>
```

Le frontend ajoute automatiquement le token à chaque requête grâce à un **Axios Request Interceptor**.

---

# Sécurisation API

Les endpoints de l'application sont protégés par Spring Security.

Les endpoints publics sont :

```text
/api/auth/register
/api/auth/login
```

Les autres endpoints nécessitent une authentification.

Spring Security vérifie :

1. La présence du JWT
2. La validité du JWT
3. L'expiration du JWT
4. L'identité de l'utilisateur
5. Le rôle de l'utilisateur
6. Les permissions associées au rôle

---

# Gestion des Autorisations

L'autorisation est basée sur les rôles.

### Exemple

```text
ADMIN
 ├── Users
 ├── Clients
 ├── Produits
 ├── Commandes
 └── Statistiques

MANAGER
 ├── Clients
 ├── Produits
 ├── Commandes
 └── Statistiques

AGENT
 ├── Consultation Clients
 ├── Consultation Produits
 └── Consultation Commandes
```

---

# Concepts Spring Security utilisés

* `AuthenticationManager`
* `PasswordEncoder`
* `BCryptPasswordEncoder`
* `UserDetails`
* `UserDetailsService`
* `SecurityFilterChain`
* `JwtFilter`
* `JwtUtil`
* `UsernamePasswordAuthenticationToken`
* `SecurityContextHolder`
* Role-based Authorization

---

# Flux d'authentification JWT

1. L'utilisateur crée un compte :

```text
POST /api/auth/register
```

2. Le mot de passe est chiffré avec BCrypt.

3. L'utilisateur se connecte :

```text
POST /api/auth/login
```

4. `AuthenticationManager` vérifie les informations.

5. Si l'authentification réussit, un JWT est généré.

6. Le frontend enregistre le token.

7. Le token est envoyé dans :

```http
Authorization: Bearer Token
```

8. Le JWT Filter intercepte les requêtes.

9. Le token est validé.

10. Le rôle de l'utilisateur est vérifié.

11. Si l'utilisateur possède les permissions nécessaires, la requête est autorisée.

---

# LogiTrack : Part 3 - Frontend React JS

## Description

Le frontend de LogiTrack est développé avec **React JS**.

Il permet aux utilisateurs d'interagir avec l'API REST sécurisée et d'accéder aux fonctionnalités selon leur rôle.

L'interface est conçue pour être :

* Moderne
* Claire
* Responsive
* Simple à utiliser

---

# Authentification Frontend

Le frontend contient :

* Page Inscription
* Page Connexion
* Déconnexion
* Gestion de session
* Gestion du JWT
* Gestion des rôles

Après une connexion réussie :

1. Le frontend récupère le JWT
2. Les informations utilisateur sont récupérées
3. Le rôle est récupéré
4. La session est enregistrée
5. L'utilisateur est redirigé vers le Dashboard
6. Axios ajoute automatiquement le JWT aux requêtes

---

# Axios Interceptors

Une configuration Axios centralisée est utilisée pour communiquer avec l'API.

## Request Interceptor

Le Request Interceptor :

* Récupère le JWT depuis le stockage
* Ajoute automatiquement le token
* Envoie le header :

```http
Authorization: Bearer <token>
```

---

## Response Interceptor

Le Response Interceptor gère les erreurs HTTP :

```text
401 Unauthorized
403 Forbidden
404 Not Found
500 Internal Server Error
```

### Erreur 401

Lorsque le token est invalide ou expiré :

1. La session est supprimée
2. L'utilisateur est déconnecté
3. L'utilisateur est redirigé vers `/login`

---

# Protected Route

Le composant `ProtectedRoute` protège les routes privées.

Un utilisateur non authentifié ne peut pas accéder aux pages internes.

Exemples :

```text
/dashboard
/clients
/products
/orders
/users
```

Si l'utilisateur n'est pas connecté :

```text
→ /login
```

---

# Role Guard

Le composant `RoleGuard` contrôle l'accès selon le rôle.

Exemples :

```text
/users
→ ADMIN uniquement

/statistics
→ ADMIN + MANAGER

DELETE
→ ADMIN selon les permissions définies
```

Si l'utilisateur ne possède pas les permissions nécessaires :

```text
→ /access-denied
```

---

# Tableau de Bord

Le Dashboard est adapté au rôle de l'utilisateur connecté.

Il peut afficher :

* Nombre de clients
* Nombre de produits
* Nombre de commandes
* Commandes en attente
* Commandes expédiées
* Commandes livrées
* Produits avec stock faible
* Produit le plus commandé
* Commandes récentes

---

# Composants React

Les composants principaux sont :

```text
Navbar
Sidebar
DashboardCard
ClientList
ClientForm
ProductList
ProductForm
OrderList
OrderForm
Pagination
SearchBar
StatusFilter
ProtectedRoute
RoleGuard
Loader
ConfirmDialog
NotFound
```

---

# Structure du Frontend

```text
src/
│
├── components/
│   ├── clients/
│   ├── products/
│   ├── orders/
│   ├── common/
│   ├── dashboard/
│   └── layout/
│
├── pages/
│   ├── Login.jsx
│   ├── Register.jsx
│   ├── Dashboard.jsx
│   ├── Clients.jsx
│   ├── Products.jsx
│   ├── Orders.jsx
│   ├── Users.jsx
│   └── AccessDenied.jsx
│
├── services/
│   ├── api.js
│   ├── authService.js
│   ├── clientService.js
│   ├── productService.js
│   └── orderService.js
│
├── guards/
│   ├── ProtectedRoute.jsx
│   └── RoleGuard.jsx
│
├── App.jsx
└── main.jsx
```

---

# Technologies Utilisées

## Backend

* Java 17 / 21
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* JWT
* BCrypt
* Maven
* REST API
* DTO
* Mapper
* MySQL
* Swagger / OpenAPI
* JUnit

---

## Frontend

* React 19
* Vite
* JavaScript ES6+
* React Router DOM
* Axios
* React Hook Form
* Yup
* HTML5
* CSS3
* MUI
* React Icons

---

## DevOps & Versioning

* Git
* GitHub
* Docker
* Dockerfile

---

# Architecture Backend

```text
src/
│
├── controller/
├── service/
├── repository/
├── entity/
├── dto/
├── mapper/
├── config/
├── security/
├── filter/
└── exception/
```

---

# Architecture Globale

```text
                ┌─────────────────────┐
                │      React JS       │
                │      Frontend       │
                └──────────┬──────────┘
                           │
                        Axios
                           │
                     JWT Token
                           │
                           ▼
                ┌─────────────────────┐
                │   Spring Security   │
                │      JWT Filter     │
                └──────────┬──────────┘
                           │
                    Role / Permission
                           │
                           ▼
                ┌─────────────────────┐
                │    REST API         │
                │   Spring Boot       │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │      Service        │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │     Repository      │
                │      JPA/Hibernate  │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │       MySQL         │
                └─────────────────────┘
```

---

# Recherche et Filtrage

L'application permet plusieurs types de recherche :

### Clients

```text
Rechercher un client par nom
```

### Produits

```text
Rechercher par catégorie
Rechercher selon le prix
Afficher les produits avec stock faible
```

### Commandes

```text
Rechercher les commandes d'un client
Filtrer par statut
```

---

# Pagination

Les listes utilisent la pagination côté backend et frontend.

Exemple :

```http
GET /api/produits?page=0&size=10&sort=nom,asc
```

Le frontend permet :

* Page précédente
* Page suivante
* Numéro de page
* Taille de page
* Nombre total d'éléments
* Tri ascendant
* Tri descendant

---

# Gestion des erreurs

L'application gère notamment les erreurs :

```text
400 Bad Request
401 Unauthorized
403 Forbidden
404 Not Found
500 Internal Server Error
```

Le frontend affiche des messages adaptés à l'utilisateur.

---

# Docker

Le frontend possède un `Dockerfile` permettant de construire et déployer l'application React dans un environnement Docker.

Exemple de construction :

```bash
docker build -t logitrack-frontend .
```

Exécution :

```bash
docker run -p 3000:3000 logitrack-frontend
```

---

# Installation et lancement

## Backend

Cloner le projet :

```bash
git clone <repository-url>
```

Accéder au backend :

```bash
cd backend
```

Lancer l'application :

```bash
mvn spring-boot:run
```

---

## Frontend

Accéder au frontend :

```bash
cd frontend
```

Installer les dépendances :

```bash
npm install
```

Lancer le serveur de développement :

```bash
npm run dev
```

---

# Git

Initialiser le repository :

```bash
git init
```

Ajouter les fichiers :

```bash
git add .
```

Créer un commit :

```bash
git commit -m "Initial commit"
```

Ajouter le repository distant :

```bash
git remote add origin <repository-url>
```

Push :

```bash
git push -u origin main
```

---

# Résumé du projet

**LogiTrack** est une solution complète de gestion logistique permettant de gérer les clients, produits et commandes à travers une API REST sécurisée et une interface React moderne.

Le projet met en œuvre :

* Architecture REST
* Spring Boot
* Spring Data JPA
* Spring Security
* JWT Authentication
* Role-Based Authorization
* React 19
* Axios Interceptors
* Protected Routes
* Role Guards
* Pagination
* Recherche et filtrage
* Gestion du stock
* Dashboard
* Docker
* Git / GitHub

### Règle métier principale

> **ADMIN administre l'application, MANAGER pilote les opérations, AGENT exécute et suit les tâches quotidiennes.**
