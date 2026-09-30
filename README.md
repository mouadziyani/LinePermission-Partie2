# LinePermission

LinePermission est une application console Java qui reproduit de manière simplifiée le système de permissions de fichiers sous Linux.

Le projet a été développé progressivement à travers trois briefs : gestion des utilisateurs, gestion des fichiers avec permissions, puis analyse des logs et migration vers une base de données.

---

## Description du projet

LinePermission permet à un utilisateur de créer un compte, se connecter, créer des fichiers, gérer leurs permissions, lire ou modifier leur contenu, puis suivre les actions effectuées dans l’application.

L’objectif principal est de pratiquer Java, la programmation orientée objet, la gestion des fichiers, la sécurité des mots de passe, les permissions, les logs, les Streams Java et l’intégration d’une base de données SQLite.

---

## Brief 1 — Authentification et gestion des utilisateurs

Dans cette première partie, l’application gère les comptes utilisateurs.

### Fonctionnalités

- Créer un compte utilisateur
- Se connecter avec un nom d’utilisateur et un mot de passe
- Se déconnecter
- Garder l’utilisateur connecté pendant la session
- Sécuriser les mots de passe avec BCrypt
- Sauvegarder les utilisateurs

### Objectifs techniques

- Utiliser les classes et objets Java
- Appliquer l’encapsulation
- Séparer le code en packages
- Utiliser un service d’authentification
- Gérer une session utilisateur simple

---

## Brief 2 — Gestion des fichiers et permissions

Dans cette deuxième partie, l’application permet de créer et manipuler des fichiers protégés par des permissions.

Chaque fichier appartient à un propriétaire et possède des permissions séparées entre :

- le propriétaire du fichier
- les autres utilisateurs

### Permissions

Chaque fichier possède trois permissions :

- `r` : lecture
- `w` : écriture
- `d` : suppression

Exemple de permission :

```txt
rwd|r--
```

Cela signifie :

- Le propriétaire peut lire, écrire et supprimer
- Les autres utilisateurs peuvent seulement lire

### Fonctionnalités

- Créer un fichier avec `touch`
- Lister les fichiers avec `ls`
- Lire le contenu d’un fichier avec `cat`
- Modifier le contenu d’un fichier avec `nano`
- Modifier les permissions avec `chmod`
- Supprimer un fichier si l’utilisateur possède la permission
- Vérifier les droits avant chaque action

### Objectifs techniques

- Manipuler les fichiers avec Java
- Utiliser `Path`, `Files` et les exceptions
- Appliquer la logique métier dans des services
- Comprendre les permissions propriétaire/autres
- Organiser le code avec une architecture claire

---

## Brief 3 — Logs, analyse et base de données

Dans cette troisième partie, l’application ajoute un système de suivi des actions et une analyse des logs.

Chaque action importante effectuée dans l’application est enregistrée : utilisateur, action, fichier, résultat, date et heure.

### Fonctionnalités

- Enregistrer les actions dans des logs
- Afficher le nombre total d’actions
- Compter les accès refusés
- Afficher les utilisateurs distincts
- Afficher les actions par utilisateur
- Afficher les fichiers les plus consultés
- Identifier l’utilisateur le plus actif
- Analyser les logs avec Stream API
- Migrer le stockage vers une base de données SQLite

### Objectifs techniques

- Utiliser Java Stream API
- Utiliser `filter`, `map`, `collect`, `groupingBy`, `counting`, `sorted`
- Manipuler les dates avec `LocalDate` et `LocalTime`
- Utiliser JDBC
- Créer une couche DAO
- Séparer les responsabilités entre DAO, Service et UI
- Remplacer le stockage texte par une base de données SQLite

---

## Technologies utilisées

- Java
- Programmation orientée objet
- jBCrypt
- Java NIO
- Stream API
- JDBC
- SQLite
- Git / GitHub

---

## Structure du projet

```txt
src/main/java/ma/youcode/lineperm
│
├── access
│   └── ControleAcces.java
│
├── dao
│   ├── AbstractDao.java
│   ├── UserDao.java
│   ├── FichierDao.java
│   └── LogDao.java
│
├── database
│   └── DBConnection.java
│
├── model
│   ├── Users.java
│   ├── FichierProtege.java
│   └── AccessLog.java
│
├── service
│   ├── AuthService.java
│   ├── UserService.java
│   ├── FileService.java
│   └── LogService.java
│
└── ui
    └── ConsoleApp.java
```

---

## Commandes principales

### Authentification

```txt
signup
login
logout
exit
```

### Gestion des fichiers

```txt
touch nom_fichier
ls
cat nom_fichier
nano nom_fichier
chmod permission nom_fichier
delete nom_fichier
```

### Analyse des logs

```txt
total actions
access refused
users distinct
actions by user
top files
most active user
```

---

## Exemple de permission

```txt
rwd|---
```

Cela signifie :

- Le propriétaire peut lire, écrire et supprimer
- Les autres utilisateurs n’ont aucune permission

```txt
rwd|r--
```

Cela signifie :

- Le propriétaire a tous les droits
- Les autres utilisateurs peuvent seulement lire

---

## Objectif pédagogique

Ce projet permet de pratiquer plusieurs notions importantes en Java :

- Programmation orientée objet
- Encapsulation
- Services
- DAO
- Gestion des fichiers
- Gestion des permissions
- Sécurité des mots de passe
- Exceptions
- Stream API
- JDBC
- SQLite
- Organisation d’un projet Java
- Utilisation de Git

---

## Auteur

Projet réalisé par **Mouad Ziyani** dans le cadre de la formation Java / YouCode.
