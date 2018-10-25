CREATE DATABASE  IF NOT EXISTS `youth_club` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `youth_club`;
-- MySQL dump 10.16  Distrib 10.1.36-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: youth_club
-- ------------------------------------------------------
-- Server version	10.1.36-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `ID` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comune`
--

DROP TABLE IF EXISTS `comune`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comune` (
  `ID` int(11) NOT NULL,
  `ID_Provincia` int(11) NOT NULL,
  `Nome` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_Provincia` (`ID_Provincia`),
  CONSTRAINT `ID_Provincia` FOREIGN KEY (`ID_Provincia`) REFERENCES `province` (`ID_Provincia`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comune`
--

LOCK TABLES `comune` WRITE;
/*!40000 ALTER TABLE `comune` DISABLE KEYS */;
/*!40000 ALTER TABLE `comune` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locale`
--

DROP TABLE IF EXISTS `locale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locale` (
  `ID` int(11) NOT NULL,
  `ID_Comune` int(11) DEFAULT NULL,
  `Nome` varchar(30) NOT NULL,
  `Via` varchar(45) NOT NULL,
  `Numero_telefono` varchar(45) DEFAULT NULL,
  `Sito_web` varchar(45) DEFAULT NULL,
  `Numero_votanti` int(11) DEFAULT NULL,
  `Tot_voti` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_Comune` (`ID_Comune`),
  CONSTRAINT `ID_Comune` FOREIGN KEY (`ID_Comune`) REFERENCES `comune` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locale`
--

LOCK TABLES `locale` WRITE;
/*!40000 ALTER TABLE `locale` DISABLE KEYS */;
/*!40000 ALTER TABLE `locale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nazioni`
--

DROP TABLE IF EXISTS `nazioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nazioni` (
  `Nome` varchar(30) NOT NULL,
  PRIMARY KEY (`Nome`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nazioni`
--

LOCK TABLES `nazioni` WRITE;
/*!40000 ALTER TABLE `nazioni` DISABLE KEYS */;
/*!40000 ALTER TABLE `nazioni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `province`
--

DROP TABLE IF EXISTS `province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `province` (
  `ID_Provincia` int(11) NOT NULL,
  `Nome` varchar(45) NOT NULL,
  `Nome_Regione` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_Provincia`),
  KEY `Nome_Regione` (`Nome_Regione`),
  CONSTRAINT `Nome_Regione` FOREIGN KEY (`Nome_Regione`) REFERENCES `regione` (`Nome`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `province`
--

LOCK TABLES `province` WRITE;
/*!40000 ALTER TABLE `province` DISABLE KEYS */;
/*!40000 ALTER TABLE `province` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recensioni`
--

DROP TABLE IF EXISTS `recensioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recensioni` (
  `ID` int(11) NOT NULL,
  `Account_ID` varchar(30) DEFAULT NULL,
  `ID_Locale` int(11) NOT NULL,
  `Testo` varchar(45) DEFAULT NULL,
  `Titolo_recensione` varchar(45) DEFAULT NULL,
  `Voto` int(11) DEFAULT NULL,
  `Voto_Servizio` int(11) DEFAULT NULL,
  `Voto_qualità_prezzo` int(11) DEFAULT NULL,
  `Voto_cibo` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Account_ID` (`Account_ID`),
  KEY `ID_Locale` (`ID_Locale`),
  CONSTRAINT `Account_ID` FOREIGN KEY (`Account_ID`) REFERENCES `account` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ID_Locale` FOREIGN KEY (`ID_Locale`) REFERENCES `locale` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recensioni`
--

LOCK TABLES `recensioni` WRITE;
/*!40000 ALTER TABLE `recensioni` DISABLE KEYS */;
/*!40000 ALTER TABLE `recensioni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regione`
--

DROP TABLE IF EXISTS `regione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `regione` (
  `Nome` varchar(20) NOT NULL,
  `Nome_Nazione` varchar(45) NOT NULL,
  PRIMARY KEY (`Nome`),
  KEY `Nome_Nazione` (`Nome_Nazione`),
  CONSTRAINT `Nome_Nazione` FOREIGN KEY (`Nome_Nazione`) REFERENCES `nazioni` (`Nome`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regione`
--

LOCK TABLES `regione` WRITE;
/*!40000 ALTER TABLE `regione` DISABLE KEYS */;
/*!40000 ALTER TABLE `regione` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-25 13:47:22
