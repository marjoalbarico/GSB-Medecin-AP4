-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Dim 18 Juin 2012 à 21:04
-- Version du serveur: 5.1.36
-- Version de PHP: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Base de données: 'bdmedecins'
--

-- --------------------------------------------------------

--
-- Structure de la table 'medecin'
--

CREATE TABLE IF NOT EXISTS medecin (
  id int(11) NOT NULL AUTO_INCREMENT,
  nom varchar(30) NOT NULL,
  prenom varchar(30) NOT NULL,
  adresse varchar(80) NOT NULL,
  tel varchar(15) DEFAULT NULL,
  specialiteComplementaire varchar(50) DEFAULT NULL,
  departement int(11) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table 'medecin'
--

