CREATE DATABASE  IF NOT EXISTS `ems` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ems`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ems
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `E_Number` int(11) NOT NULL,
  `E_Name` varchar(30) NOT NULL,
  `E_Sex` varchar(2) NOT NULL,
  `E_BornDate` datetime NOT NULL,
  `E_Marriage` varchar(4) NOT NULL,
  `E_PoliticsVisage` varchar(20) NOT NULL,
  `E_SchoolAge` varchar(20) DEFAULT NULL,
  `E_EnterDate` datetime DEFAULT NULL,
  `E_InDueFormDate` datetime NOT NULL,
  `D_Number` int(11) NOT NULL,
  `E_Headship` varchar(20) NOT NULL,
  `E_Estate` varchar(10) NOT NULL,
  `E_Remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`E_Number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'nv','we','1997-09-04 00:00:00','dfaa','adfasf','asfdsaf','1997-09-04 00:00:00','1997-09-04 00:00:00',234,'sadfasf','sadfsaf','adfaf'),(34,'dsafsda','sa','2008-03-13 00:00:00','asf','fdsaf','dsfasf','2008-03-13 00:00:00','2008-03-13 00:00:00',21321,'sfsaf','sadfsaf','asdfsaf'),(47,'erju','fd','1997-09-04 00:00:00','sfsd','sfs','sdfs','1997-09-04 00:00:00','1997-09-04 00:00:00',875,'gdfg','sdfs','sdfs'),(54,'trw','ew','2004-04-23 00:00:00','qwe','qweq','cxb','2004-04-23 00:00:00','2004-04-23 00:00:00',23,'rsdds','sdfa','sada'),(123,'ewrw','ew','2008-03-13 00:00:00','asd','asdsa','asd','2008-03-13 00:00:00','2008-03-13 00:00:00',321,'sfdsa','adsaad','asdad'),(3441,'dsafsda','sa','2008-03-13 00:00:00','asd','fdsaf','dsfasf','2008-03-13 00:00:00','2008-03-13 00:00:00',21321,'sfsaf','sadfsaf','asdfsaf'),(14131,'qeq','ew','2008-03-13 00:00:00','we','we','wre','2008-03-13 00:00:00','2008-03-13 00:00:00',432,'ew','we','we'),(1030415205,'汪霞','女','1998-02-27 00:00:00','未婚','共产党员','硕士','2003-02-23 00:00:00','2004-04-23 00:00:00',10,'设计部','在职',''),(1030415208,'杨伊宁','女','1997-09-04 00:00:00','未婚','共青团员','本科','2008-03-13 00:00:00','2039-10-01 00:00:00',12,'厂长','在职','   ');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-05 14:01:09
