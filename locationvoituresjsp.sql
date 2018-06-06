-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mer. 06 juin 2018 à 23:04
-- Version du serveur :  5.7.19
-- Version de PHP :  7.0.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `locationvoituresjsp`
--

-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

DROP TABLE IF EXISTS `adresse`;
CREATE TABLE IF NOT EXISTS `adresse` (
  `Identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `Code_postal` varchar(255) NOT NULL,
  `Ville` varchar(255) NOT NULL,
  PRIMARY KEY (`Identifiant`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `agence`
--

DROP TABLE IF EXISTS `agence`;
CREATE TABLE IF NOT EXISTS `agence` (
  `Identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `Rue` varchar(255) NOT NULL,
  `Numero` varchar(20) NOT NULL,
  `FK_adresse` int(11) NOT NULL,
  PRIMARY KEY (`Identifiant`),
  KEY `FK_adresse` (`FK_adresse`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `carburant`
--

DROP TABLE IF EXISTS `carburant`;
CREATE TABLE IF NOT EXISTS `carburant` (
  `Identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `Prix` float NOT NULL,
  PRIMARY KEY (`Identifiant`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `Identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  PRIMARY KEY (`Identifiant`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `employer`
--

DROP TABLE IF EXISTS `employer`;
CREATE TABLE IF NOT EXISTS `employer` (
  `Identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `Type_de_contrat` varchar(255) DEFAULT 'Aucun',
  PRIMARY KEY (`Identifiant`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

DROP TABLE IF EXISTS `facture`;
CREATE TABLE IF NOT EXISTS `facture` (
  `Identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `Date_de_facturation` date NOT NULL,
  `FK_personne` int(11) NOT NULL,
  `FK_reservation` int(11) NOT NULL,
  PRIMARY KEY (`Identifiant`),
  KEY `FK_personne` (`FK_personne`),
  KEY `FK_reservation` (`FK_reservation`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `option_comprise_reservation`
--

DROP TABLE IF EXISTS `option_comprise_reservation`;
CREATE TABLE IF NOT EXISTS `option_comprise_reservation` (
  `Identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `FK_reservation` int(11) NOT NULL,
  `FK_option_reservation` int(11) NOT NULL,
  `Quantite` int(11) DEFAULT '0',
  PRIMARY KEY (`Identifiant`),
  KEY `FK_reservation` (`FK_reservation`),
  KEY `FK_option_reservation` (`FK_option_reservation`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `option_comprise_voiture`
--

DROP TABLE IF EXISTS `option_comprise_voiture`;
CREATE TABLE IF NOT EXISTS `option_comprise_voiture` (
  `Identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `FK_voiture` int(11) NOT NULL,
  `FK_option_voiture` int(11) NOT NULL,
  `Quantite` int(11) DEFAULT '0',
  PRIMARY KEY (`Identifiant`),
  KEY `FK_voiture` (`FK_voiture`),
  KEY `FK_option_voiture` (`FK_option_voiture`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `option_reservation`
--

DROP TABLE IF EXISTS `option_reservation`;
CREATE TABLE IF NOT EXISTS `option_reservation` (
  `Identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `Prix` float NOT NULL,
  `Assurance` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`Identifiant`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `option_voiture`
--

DROP TABLE IF EXISTS `option_voiture`;
CREATE TABLE IF NOT EXISTS `option_voiture` (
  `Identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `Prix` float NOT NULL,
  PRIMARY KEY (`Identifiant`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

DROP TABLE IF EXISTS `personne`;
CREATE TABLE IF NOT EXISTS `personne` (
  `Identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `Mot_de_passe` varchar(255) NOT NULL,
  `Nom` varchar(255) NOT NULL,
  `Prenom` varchar(255) NOT NULL,
  `Date_de_naissance` date NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Rue` varchar(255) NOT NULL,
  `Numero` varchar(20) NOT NULL,
  `FK_adresse` int(11) NOT NULL,
  PRIMARY KEY (`Identifiant`),
  KEY `FK_adresse` (`FK_adresse`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `Identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `Date_de_reservation` date NOT NULL,
  `Date_de_retour` date NOT NULL,
  `Prix` float NOT NULL,
  `FK_personne` int(11) NOT NULL,
  PRIMARY KEY (`Identifiant`),
  KEY `FK_personne` (`FK_personne`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reservation_agence`
--

DROP TABLE IF EXISTS `reservation_agence`;
CREATE TABLE IF NOT EXISTS `reservation_agence` (
  `Identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `FK_agence` int(11) NOT NULL,
  `FK_reservation` int(11) NOT NULL,
  `Retour` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`Identifiant`),
  KEY `FK_agence` (`FK_agence`),
  KEY `FK_reservation` (`FK_reservation`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reservation_voiture`
--

DROP TABLE IF EXISTS `reservation_voiture`;
CREATE TABLE IF NOT EXISTS `reservation_voiture` (
  `Identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `FK_reservation` int(11) NOT NULL,
  `FK_voiture` int(11) NOT NULL,
  `Quantite` int(11) DEFAULT '0',
  PRIMARY KEY (`Identifiant`),
  KEY `FK_reservation` (`FK_reservation`),
  KEY `FK_voiture` (`FK_voiture`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `Identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `Actif` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`Identifiant`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `role_personne`
--

DROP TABLE IF EXISTS `role_personne`;
CREATE TABLE IF NOT EXISTS `role_personne` (
  `Identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `FK_role` int(11) NOT NULL,
  `FK_personne` int(11) NOT NULL,
  PRIMARY KEY (`Identifiant`),
  KEY `FK_role` (`FK_role`),
  KEY `FK_personne` (`FK_personne`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `voiture`
--

DROP TABLE IF EXISTS `voiture`;
CREATE TABLE IF NOT EXISTS `voiture` (
  `Identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `Annee` int(11) NOT NULL,
  `Boite_vitesse` int(11) NOT NULL,
  `Prix` float NOT NULL,
  `FK_categorie` int(11) NOT NULL,
  `FK_agence` int(11) NOT NULL,
  `FK_carburant` int(11) NOT NULL,
  PRIMARY KEY (`Identifiant`),
  KEY `FK_categorie` (`FK_categorie`),
  KEY `FK_agence` (`FK_agence`),
  KEY `FK_carburant` (`FK_carburant`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
