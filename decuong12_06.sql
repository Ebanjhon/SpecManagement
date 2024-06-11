-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: decuong
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat` (
  `idChat` int NOT NULL AUTO_INCREMENT,
  `userID` int DEFAULT NULL,
  `roomID` int DEFAULT NULL,
  PRIMARY KEY (`idChat`),
  KEY `userID` (`userID`),
  KEY `roomID` (`roomID`),
  CONSTRAINT `chat_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`idUser`) ON DELETE CASCADE,
  CONSTRAINT `chat_ibfk_2` FOREIGN KEY (`roomID`) REFERENCES `roomchat` (`idRoomChat`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES (1,2,3);
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `idComment` int NOT NULL AUTO_INCREMENT,
  `cmParentID` int DEFAULT NULL,
  `cmDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `userID` int DEFAULT NULL,
  `specID` int DEFAULT NULL,
  PRIMARY KEY (`idComment`),
  KEY `userID` (`userID`),
  KEY `specID` (`specID`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`idUser`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`specID`) REFERENCES `specification` (`idSpec`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coursestudy`
--

DROP TABLE IF EXISTS `coursestudy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coursestudy` (
  `idCourse` int NOT NULL AUTO_INCREMENT,
  `nameCourse` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `teacherID` int DEFAULT NULL,
  `specID` int DEFAULT NULL,
  PRIMARY KEY (`idCourse`),
  KEY `teacherID` (`teacherID`),
  KEY `specID` (`specID`),
  CONSTRAINT `coursestudy_ibfk_1` FOREIGN KEY (`teacherID`) REFERENCES `user` (`idUser`),
  CONSTRAINT `coursestudy_ibfk_2` FOREIGN KEY (`specID`) REFERENCES `specification` (`idSpec`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursestudy`
--

LOCK TABLES `coursestudy` WRITE;
/*!40000 ALTER TABLE `coursestudy` DISABLE KEYS */;
INSERT INTO `coursestudy` VALUES (3,'Toán ',2,2);
/*!40000 ALTER TABLE `coursestudy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gradingsheet`
--

DROP TABLE IF EXISTS `gradingsheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gradingsheet` (
  `idGrade` int NOT NULL AUTO_INCREMENT,
  `nameColumn` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idGrade`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gradingsheet`
--

LOCK TABLES `gradingsheet` WRITE;
/*!40000 ALTER TABLE `gradingsheet` DISABLE KEYS */;
INSERT INTO `gradingsheet` VALUES (1,'Giữa kỳ'),(2,'Cuối Kỳ');
/*!40000 ALTER TABLE `gradingsheet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoidong`
--

DROP TABLE IF EXISTS `hoidong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoidong` (
  `idHoiDong` int NOT NULL AUTO_INCREMENT,
  `chuTichID` int NOT NULL,
  `thuKyID` int DEFAULT NULL,
  PRIMARY KEY (`idHoiDong`),
  KEY `chuTichID` (`chuTichID`),
  KEY `thuKyID` (`thuKyID`),
  CONSTRAINT `HoiDong_ibfk_1` FOREIGN KEY (`chuTichID`) REFERENCES `user` (`idUser`),
  CONSTRAINT `HoiDong_ibfk_2` FOREIGN KEY (`thuKyID`) REFERENCES `user` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoidong`
--

LOCK TABLES `hoidong` WRITE;
/*!40000 ALTER TABLE `hoidong` DISABLE KEYS */;
INSERT INTO `hoidong` VALUES (1,2,3);
/*!40000 ALTER TABLE `hoidong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oderdc`
--

DROP TABLE IF EXISTS `oderdc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oderdc` (
  `idOrder` int NOT NULL AUTO_INCREMENT,
  `userID` int DEFAULT NULL,
  `specID` int DEFAULT NULL,
  PRIMARY KEY (`idOrder`),
  KEY `userID` (`userID`),
  KEY `specID` (`specID`),
  CONSTRAINT `oderdc_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`idUser`) ON DELETE CASCADE,
  CONSTRAINT `oderdc_ibfk_2` FOREIGN KEY (`specID`) REFERENCES `specification` (`idSpec`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oderdc`
--

LOCK TABLES `oderdc` WRITE;
/*!40000 ALTER TABLE `oderdc` DISABLE KEYS */;
/*!40000 ALTER TABLE `oderdc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roomchat`
--

DROP TABLE IF EXISTS `roomchat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roomchat` (
  `idRoomChat` int NOT NULL AUTO_INCREMENT,
  `nameRoom` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idRoomChat`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomchat`
--

LOCK TABLES `roomchat` WRITE;
/*!40000 ALTER TABLE `roomchat` DISABLE KEYS */;
INSERT INTO `roomchat` VALUES (1,'Room 1'),(2,'Room 2'),(3,'Room 3');
/*!40000 ALTER TABLE `roomchat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specgrande`
--

DROP TABLE IF EXISTS `specgrande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specgrande` (
  `idGrandeSpec` int NOT NULL AUTO_INCREMENT,
  `specifiID` int DEFAULT NULL,
  `grandeID` int DEFAULT NULL,
  `weight` decimal(5,2) NOT NULL,
  PRIMARY KEY (`idGrandeSpec`),
  KEY `specifiID` (`specifiID`),
  KEY `grandeID` (`grandeID`),
  CONSTRAINT `specgrande_ibfk_1` FOREIGN KEY (`specifiID`) REFERENCES `specification` (`idSpec`) ON DELETE CASCADE,
  CONSTRAINT `specgrande_ibfk_2` FOREIGN KEY (`grandeID`) REFERENCES `gradingsheet` (`idGrade`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specgrande`
--

LOCK TABLES `specgrande` WRITE;
/*!40000 ALTER TABLE `specgrande` DISABLE KEYS */;
/*!40000 ALTER TABLE `specgrande` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specification`
--

DROP TABLE IF EXISTS `specification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specification` (
  `idSpec` int NOT NULL AUTO_INCREMENT,
  `nameSpec` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `authorID` int DEFAULT NULL,
  `credit` int DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `subjectID` int DEFAULT NULL,
  `dateCreate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `price` decimal(10,2) DEFAULT NULL,
  `typeSpecID` int DEFAULT NULL,
  `fileSpec` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `hoiDongID` int DEFAULT NULL,
  `status` enum('none','editing','pass') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'none',
  PRIMARY KEY (`idSpec`),
  KEY `authorID` (`authorID`),
  KEY `subjectID` (`subjectID`),
  KEY `typeSpecID` (`typeSpecID`),
  KEY `FK_HoiDongID` (`hoiDongID`),
  CONSTRAINT `FK_HoiDongID` FOREIGN KEY (`hoiDongID`) REFERENCES `hoidong` (`idHoiDong`),
  CONSTRAINT `specification_ibfk_1` FOREIGN KEY (`authorID`) REFERENCES `user` (`idUser`),
  CONSTRAINT `specification_ibfk_2` FOREIGN KEY (`subjectID`) REFERENCES `subject` (`idSubject`) ON DELETE CASCADE,
  CONSTRAINT `specification_ibfk_3` FOREIGN KEY (`typeSpecID`) REFERENCES `typeofspecifi` (`idType`) ON DELETE CASCADE,
  CONSTRAINT `specification_chk_1` CHECK (((`credit` >= 2) and (`credit` <= 4)))
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specification`
--

LOCK TABLES `specification` WRITE;
/*!40000 ALTER TABLE `specification` DISABLE KEYS */;
INSERT INTO `specification` VALUES (2,'Đê cương môn toán',2,4,'abc',2,'2024-05-22 17:00:00',1.00,1,'',1,'none'),(3,'ádasdasd',2,3,'sdfsdf',3,'2024-05-22 17:00:00',1.00,2,'',1,'none'),(8,'Đề cương W3',1,3,'Crypto to the ',1,'2024-06-10 10:43:40',100.00,1,'https://res.cloudinary.com/dfbykxwru/image/upload/v1718016223/jk7db0siqpkv3zventrp.pdf',1,'editing'),(10,'Đề cương W3ssss',1,3,'Crypto to the ',1,'2024-06-10 10:46:18',100.00,1,'https://res.cloudinary.com/dfbykxwru/raw/upload/v1718016381/wr87ve0pxmac1mum0v90',1,'editing');
/*!40000 ALTER TABLE `specification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `idSubject` int NOT NULL AUTO_INCREMENT,
  `nameSubject` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idSubject`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'Math'),(2,'Science'),(3,'History'),(4,'English'),(7,'Testing Project');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typeofspecifi`
--

DROP TABLE IF EXISTS `typeofspecifi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `typeofspecifi` (
  `idType` int NOT NULL AUTO_INCREMENT,
  `nameType` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idType`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typeofspecifi`
--

LOCK TABLES `typeofspecifi` WRITE;
/*!40000 ALTER TABLE `typeofspecifi` DISABLE KEYS */;
INSERT INTO `typeofspecifi` VALUES (1,'Dũ liệu File Doc'),(2,'Dũ liệu File  PDF'),(3,'Dữ liệu dạng văn bản');
/*!40000 ALTER TABLE `typeofspecifi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `idUser` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `firstname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `lastname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `dateOfBirth` date DEFAULT NULL,
  `gender` enum('Nam','Nữ','Khác') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role` enum('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `unique_username` (`username`),
  UNIQUE KEY `unique_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'john_doe','John','Doe','$2a$10$5X9k5N1sTc1/CjVH5XJoje3QMYijH3ETpgkox00R0MdPaJPPrf7wO','2024-05-20','Nam','john@example.com','123 Main St, City','1234567890','ROLE_ADMIN',1,'avatar1.jpg'),(2,'jane_smith','Jane','Smith','p@ssw0rd','2024-05-20','Nữ','jane@example.com','456 Elm St, Town','0987654321','ROLE_TEACHER',1,'avatar2.jpg'),(3,'alice_wonder','Alice','Wonder','pass123','2024-05-20','Nữ','alice@example.com','789 Oak St, Village',NULL,'ROLE_STUDENT',1,'avatar3.jpg'),(4,'testapicreateuser','VanMau','Dk','$2a$10$oUPRXj9thCTiKUpAkbjaq.AWFIEg2QYYuTbCe1BCnr42Kgn5Z8c1m',NULL,'Nam','zfsfsd13@gmail.com',NULL,NULL,'ROLE_ADMIN',1,'https://res.cloudinary.com/dfbykxwru/image/upload/v1717958522/jnznbkqe0imlqkjztgxi.jpg'),(8,'testapicreateuserSS','VanMau','Dk','$2a$10$3./NNObUGPs6LLYKvncKduWoB6LgpL1a2P3R3VANnuKzZjB/lAFdG',NULL,'Nam','zfsfsd1S3@gmail.com',NULL,NULL,'ROLE_STUDENT',1,'https://res.cloudinary.com/dfbykxwru/image/upload/v1718015342/vkbbu5jlaodv3c3roigg.jpg');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-12  0:44:13
