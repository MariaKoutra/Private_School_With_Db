CREATE DATABASE  IF NOT EXISTS `school_project` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `school_project`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: school_project
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `assignment`
--

DROP TABLE IF EXISTS `assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment` (
  `ID_ASSIGNMENT` int NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(45) NOT NULL,
  `DESCRIPTION` varchar(45) NOT NULL,
  `SUBMISSION_DATE` date NOT NULL,
  `MAX_ORAL_MARK` int DEFAULT '20',
  `MAX_TOTAL_MARK` int DEFAULT '80',
  PRIMARY KEY (`ID_ASSIGNMENT`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment`
--

LOCK TABLES `assignment` WRITE;
/*!40000 ALTER TABLE `assignment` DISABLE KEYS */;
INSERT INTO `assignment` VALUES (1,'Project1','Private school','2020-11-11',20,80),(2,'Project2','Pet shop','2020-12-10',20,80),(3,'Project3','Teamwork project','2020-12-23',20,80),(4,'Project 4','Library','2021-01-10',20,80);
/*!40000 ALTER TABLE `assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `ID_COURSE` int NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(45) NOT NULL,
  `STREAM` varchar(45) NOT NULL,
  `TYPE` varchar(45) NOT NULL,
  `START_DATE` date DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  PRIMARY KEY (`ID_COURSE`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'CB12 Java','Java','Part Time','2020-10-15','2020-12-23'),(2,'CB12 Java','Java','Full Time','2020-10-15','2020-12-23'),(3,'CB12 Python','Python','Part Time','2020-11-11','2020-12-23'),(4,'CB13 C++','C++','Part Time','2021-01-04','2021-01-31');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrolled_stud`
--

DROP TABLE IF EXISTS `enrolled_stud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enrolled_stud` (
  `ID_COURSE` int NOT NULL,
  `ID_STUDENT` int NOT NULL,
  PRIMARY KEY (`ID_COURSE`,`ID_STUDENT`),
  KEY `FK_ID_S_idx` (`ID_STUDENT`),
  CONSTRAINT `FK_ID_C` FOREIGN KEY (`ID_COURSE`) REFERENCES `course` (`ID_COURSE`),
  CONSTRAINT `FK_ID_S` FOREIGN KEY (`ID_STUDENT`) REFERENCES `student` (`ID_STUDENT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrolled_stud`
--

LOCK TABLES `enrolled_stud` WRITE;
/*!40000 ALTER TABLE `enrolled_stud` DISABLE KEYS */;
INSERT INTO `enrolled_stud` VALUES (1,1),(2,1),(1,2),(2,2),(3,3),(3,4),(4,4),(2,5),(3,5),(3,6),(3,7),(4,9);
/*!40000 ALTER TABLE `enrolled_stud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `has_assign`
--

DROP TABLE IF EXISTS `has_assign`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `has_assign` (
  `ID_COURSE` int NOT NULL,
  `ID_ASSIGNMENT` int NOT NULL,
  PRIMARY KEY (`ID_COURSE`,`ID_ASSIGNMENT`),
  KEY `FK_ID_ASSIGNM_idx` (`ID_ASSIGNMENT`),
  CONSTRAINT `FK_ID_ASSIGNM` FOREIGN KEY (`ID_ASSIGNMENT`) REFERENCES `assignment` (`ID_ASSIGNMENT`),
  CONSTRAINT `FK_ID_COUR` FOREIGN KEY (`ID_COURSE`) REFERENCES `course` (`ID_COURSE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `has_assign`
--

LOCK TABLES `has_assign` WRITE;
/*!40000 ALTER TABLE `has_assign` DISABLE KEYS */;
INSERT INTO `has_assign` VALUES (1,1),(1,2),(2,2),(1,3),(3,3),(4,4);
/*!40000 ALTER TABLE `has_assign` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `ID_STUDENT` int NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(45) NOT NULL,
  `LAST_NAME` varchar(45) NOT NULL,
  `BIRTHDATE` date NOT NULL,
  `TUITION_FEES` int DEFAULT '0',
  PRIMARY KEY (`ID_STUDENT`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'Maria','Koutra','1997-01-04',1000),(2,'Tasos','Kladis','1996-11-27',1000),(3,'Andreas','Strebelias','2001-03-13',1000),(4,'Margarita','Stratigi','1998-10-07',1000),(5,'Eirini','Kamaledaki','2002-07-09',1000),(6,'Katerina','Koutra','2001-03-10',1000),(7,'Nikolia','Koutra','2010-05-22',1000),(8,'Alex','Koutras','1999-04-05',1000),(9,'Giannis','Tsioris','1974-01-28',1500);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainer`
--

DROP TABLE IF EXISTS `trainer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trainer` (
  `ID_TRAINER` int NOT NULL AUTO_INCREMENT,
  `ID_COURSE` int DEFAULT NULL,
  `FIRST_NAME` varchar(45) NOT NULL,
  `LAST_NAME` varchar(45) NOT NULL,
  `SUBJECT` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_TRAINER`),
  KEY `FK_IDT_IDC_idx` (`ID_COURSE`),
  CONSTRAINT `FK_IDT_IDC` FOREIGN KEY (`ID_COURSE`) REFERENCES `course` (`ID_COURSE`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainer`
--

LOCK TABLES `trainer` WRITE;
/*!40000 ALTER TABLE `trainer` DISABLE KEYS */;
INSERT INTO `trainer` VALUES (1,1,'Vaso','Badourou','Java'),(2,2,'Xristos','Kapopoulos','Java'),(3,1,'Stelios','Stergiou','JavaScript'),(4,4,'Anna','Papadatou','Python'),(5,3,'Aris','Aristidou','C++');
/*!40000 ALTER TABLE `trainer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `working_assign`
--

DROP TABLE IF EXISTS `working_assign`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `working_assign` (
  `ID_ASSIGNMENT` int NOT NULL,
  `ID_STUDENT` int NOT NULL,
  PRIMARY KEY (`ID_ASSIGNMENT`,`ID_STUDENT`),
  KEY `FK_ID_ST_idx` (`ID_STUDENT`),
  CONSTRAINT `FK_ID_AS` FOREIGN KEY (`ID_ASSIGNMENT`) REFERENCES `assignment` (`ID_ASSIGNMENT`),
  CONSTRAINT `FK_ID_ST` FOREIGN KEY (`ID_STUDENT`) REFERENCES `student` (`ID_STUDENT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `working_assign`
--

LOCK TABLES `working_assign` WRITE;
/*!40000 ALTER TABLE `working_assign` DISABLE KEYS */;
INSERT INTO `working_assign` VALUES (1,1),(4,1),(1,2),(2,2),(1,3),(1,4),(2,4),(4,4),(1,5),(2,5),(4,5),(1,6),(2,6),(4,6),(4,8),(4,9);
/*!40000 ALTER TABLE `working_assign` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'school_project'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-15 23:46:08
