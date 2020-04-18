-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 18, 2020 at 08:46 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE `se2`;
USE `se2`;

--
-- Database: `se2`
--

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE `city` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `confirmed` int(11) NOT NULL,
  `recovered` int(11) NOT NULL,
  `deaths` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `continent`
--

CREATE TABLE `continent` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `confirmed` int(11) NOT NULL,
  `recovered` int(11) NOT NULL,
  `deaths` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `country`
--

CREATE TABLE `country` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `confirmed` int(11) NOT NULL,
  `recovered` int(11) NOT NULL,
  `deaths` int(11) NOT NULL,
  `continent_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `world`
--

CREATE TABLE `world` (
  `id` int(11) NOT NULL,
  `comfirmed` int(11) NOT NULL,
  `recovered` int(11) NOT NULL,
  `deaths` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `continent`
--
ALTER TABLE `continent`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `country`
--
ALTER TABLE `country`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk` (`continent_id`);

--
-- Indexes for table `world`
--
ALTER TABLE `world`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `city`
--
ALTER TABLE `city`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `continent`
--
ALTER TABLE `continent`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `country`
--
ALTER TABLE `country`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `country`
--
ALTER TABLE `country`
  ADD CONSTRAINT `fk` FOREIGN KEY (`continent_id`) REFERENCES `continent` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

INSERT INTO `world` (`id`, `comfirmed`, `recovered`, `deaths`) VALUES ('1', '2240191', '568343', '153822');
INSERT INTO `continent` (`id`, `name`, `confirmed`, `recovered`, `deaths`) VALUES ('1', 'Asia', '358921', '170488', '14079');
INSERT INTO `country` (`id`, `name`, `confirmed`, `recovered`, `deaths`, `continent_id`) VALUES ('1', 'China', '83597', '77029', '4632', '1');
INSERT INTO `city` (`id`, `name`, `confirmed`, `recovered`, `deaths`) VALUES ('1', 'Hà Nội', '116', '82', '0');
