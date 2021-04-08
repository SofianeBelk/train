-- phpMyAdmin SQL Dump
-- version 4.6.6deb5ubuntu0.5
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Jeu 01 Avril 2021 à 23:52
-- Version du serveur :  5.7.33-0ubuntu0.18.04.1
-- Version de PHP :  7.2.24-0ubuntu0.18.04.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `TrainBase`
--

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `pseudo` varchar(32) NOT NULL,
  `idTrain` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `session`
--

CREATE TABLE `session` (
  `pseudo` varchar(32) NOT NULL,
  `clef` varchar(32) NOT NULL,
  `temps` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tarifLine`
--

CREATE TABLE `tarifLine` (
  `origin` varchar(32) NOT NULL,
  `pleinTarif1ere` int(3) NOT NULL,
  `destination` varchar(32) NOT NULL,
  `pleinTarif2nde` varchar(32) NOT NULL,
  `prixdappel2nde` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `nom` varchar(32) NOT NULL,
  `prenom` varchar(32) NOT NULL,
  `pseudo` varchar(32) NOT NULL,
  `email` varchar(32) NOT NULL,
  `motDePasse` blob NOT NULL,
  `age` int(11) NOT NULL,
  `telephone` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`pseudo`,`idTrain`);

--
-- Index pour la table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`clef`,`pseudo`);

--
-- Index pour la table `tarifLine`
--
ALTER TABLE `tarifLine`
  ADD PRIMARY KEY (`origin`,`destination`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`pseudo`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;