-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: luminar_servlet_project
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `assembly`
--

DROP TABLE IF EXISTS `assembly`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assembly` (
  `assembly_id` int NOT NULL AUTO_INCREMENT,
  `assembly_name` varchar(45) DEFAULT NULL,
  `district_id` int DEFAULT NULL,
  PRIMARY KEY (`assembly_id`),
  KEY `fk_assembly_district` (`district_id`),
  CONSTRAINT `fk_assembly_district` FOREIGN KEY (`district_id`) REFERENCES `districts` (`district_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assembly`
--

LOCK TABLES `assembly` WRITE;
/*!40000 ALTER TABLE `assembly` DISABLE KEYS */;
INSERT INTO `assembly` VALUES (1,'Thaliparamba',13),(2,'Irikkoor',13),(3,'Azhikode',13),(4,'Kannur',13),(5,'Dharmadam',13),(6,'Thalassery',13),(7,'Kuthuparamba',13),(8,'Mattannur',13),(9,'Iritty',13),(10,'Peravoor',13),(11,'Kalliasseri',13);
/*!40000 ALTER TABLE `assembly` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booths`
--

DROP TABLE IF EXISTS `booths`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booths` (
  `booth_id` int NOT NULL AUTO_INCREMENT,
  `booth_no` int DEFAULT NULL,
  `ward_id` int DEFAULT NULL,
  PRIMARY KEY (`booth_id`),
  KEY `fk_booth_ward` (`ward_id`),
  CONSTRAINT `fk_booth_ward` FOREIGN KEY (`ward_id`) REFERENCES `wards` (`ward_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booths`
--

LOCK TABLES `booths` WRITE;
/*!40000 ALTER TABLE `booths` DISABLE KEYS */;
INSERT INTO `booths` VALUES (1,1,12),(2,2,12),(3,3,12),(4,4,12),(5,5,12),(6,6,12),(7,7,12),(8,8,12),(9,9,12),(10,10,12);
/*!40000 ALTER TABLE `booths` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `current_form_status_view`
--

DROP TABLE IF EXISTS `current_form_status_view`;
/*!50001 DROP VIEW IF EXISTS `current_form_status_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `current_form_status_view` AS SELECT 
 1 AS `form_status_id`,
 1 AS `voter_id`,
 1 AS `voter_name`,
 1 AS `form_current_status`,
 1 AS `form_last_updated`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `districts`
--

DROP TABLE IF EXISTS `districts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `districts` (
  `district_id` int NOT NULL AUTO_INCREMENT,
  `district_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`district_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `districts`
--

LOCK TABLES `districts` WRITE;
/*!40000 ALTER TABLE `districts` DISABLE KEYS */;
INSERT INTO `districts` VALUES (1,'Thiruvananthapuram'),(2,'Kollam'),(3,'Pathanamthitta'),(4,'Alappuzha'),(5,'Kottayam'),(6,'Idukki'),(7,'Ernakulam'),(8,'Thrissur'),(9,'Palakkad'),(10,'Malappuram'),(11,'Kozhikode'),(12,'Wayanad'),(13,'Kannur'),(14,'Kasaragod');
/*!40000 ALTER TABLE `districts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `form_collection`
--

DROP TABLE IF EXISTS `form_collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `form_collection` (
  `form_collection_id` int NOT NULL AUTO_INCREMENT,
  `form_id` int DEFAULT NULL,
  `form_collection_date` date DEFAULT NULL,
  `form_collection_mode` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`form_collection_id`),
  KEY `fk_forms_coll` (`form_id`),
  CONSTRAINT `fk_forms_coll` FOREIGN KEY (`form_id`) REFERENCES `forms` (`form_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `form_collection`
--

LOCK TABLES `form_collection` WRITE;
/*!40000 ALTER TABLE `form_collection` DISABLE KEYS */;
INSERT INTO `form_collection` VALUES (1,2,'2026-01-07','In person'),(2,3,'2026-01-08','Collection center');
/*!40000 ALTER TABLE `form_collection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `form_collection_info`
--

DROP TABLE IF EXISTS `form_collection_info`;
/*!50001 DROP VIEW IF EXISTS `form_collection_info`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `form_collection_info` AS SELECT 
 1 AS `form_collection_id`,
 1 AS `voter_id`,
 1 AS `voter_name`,
 1 AS `form_collection_date`,
 1 AS `form_collection_mode`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `form_distribution`
--

DROP TABLE IF EXISTS `form_distribution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `form_distribution` (
  `form_distribution_id` int NOT NULL AUTO_INCREMENT,
  `form_id` int DEFAULT NULL,
  `form_distribution_date` date DEFAULT NULL,
  `form_distribution_mode` varchar(45) DEFAULT NULL,
  `form_distribution_collected_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`form_distribution_id`),
  KEY `fk_forms_dist` (`form_id`),
  CONSTRAINT `fk_forms_dist` FOREIGN KEY (`form_id`) REFERENCES `forms` (`form_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `form_distribution`
--

LOCK TABLES `form_distribution` WRITE;
/*!40000 ALTER TABLE `form_distribution` DISABLE KEYS */;
INSERT INTO `form_distribution` VALUES (1,1,'2026-01-01','Relative','Mary'),(2,2,'2026-01-01','In person','Sumesh'),(3,3,'2026-01-02','Collection center','Vineeth');
/*!40000 ALTER TABLE `form_distribution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `form_distribution_info_master_view`
--

DROP TABLE IF EXISTS `form_distribution_info_master_view`;
/*!50001 DROP VIEW IF EXISTS `form_distribution_info_master_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `form_distribution_info_master_view` AS SELECT 
 1 AS `voter_name`,
 1 AS `voter_epic_no`,
 1 AS `voter_mobile_no`,
 1 AS `form_distribution_date`,
 1 AS `form_distribution_mode`,
 1 AS `form_distribution_collected_by`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `form_distribution_view`
--

DROP TABLE IF EXISTS `form_distribution_view`;
/*!50001 DROP VIEW IF EXISTS `form_distribution_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `form_distribution_view` AS SELECT 
 1 AS `form_distribution_id`,
 1 AS `voter_id`,
 1 AS `voter_name`,
 1 AS `voter_mobile_no`,
 1 AS `form_distribution_date`,
 1 AS `form_distribution_mode`,
 1 AS `form_distribution_collected_by`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `form_status`
--

DROP TABLE IF EXISTS `form_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `form_status` (
  `form_status_id` int NOT NULL AUTO_INCREMENT,
  `form_id` int DEFAULT NULL,
  `form_current_status` varchar(45) DEFAULT 'not_distriuted',
  `form_last_updated` date DEFAULT NULL,
  PRIMARY KEY (`form_status_id`),
  KEY `fk_forms_stat` (`form_id`),
  CONSTRAINT `fk_forms_stat` FOREIGN KEY (`form_id`) REFERENCES `forms` (`form_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `form_status`
--

LOCK TABLES `form_status` WRITE;
/*!40000 ALTER TABLE `form_status` DISABLE KEYS */;
INSERT INTO `form_status` VALUES (1,1,'Distributed','2026-01-01'),(2,2,'Upload/fail','2026-01-08'),(3,3,'Upload/fail','2026-01-08');
/*!40000 ALTER TABLE `form_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `form_upload`
--

DROP TABLE IF EXISTS `form_upload`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `form_upload` (
  `upload_id` int NOT NULL AUTO_INCREMENT,
  `form_id` int DEFAULT NULL,
  `upload_date` date DEFAULT NULL,
  `modified_date` date DEFAULT NULL,
  `upload_status` varchar(45) DEFAULT NULL,
  `remarks` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`upload_id`),
  KEY `fk_forms_up` (`form_id`),
  CONSTRAINT `fk_forms_up` FOREIGN KEY (`form_id`) REFERENCES `forms` (`form_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `form_upload`
--

LOCK TABLES `form_upload` WRITE;
/*!40000 ALTER TABLE `form_upload` DISABLE KEYS */;
INSERT INTO `form_upload` VALUES (1,2,'2026-01-08','2026-01-08','fail','bad image quality'),(2,3,'2026-01-08','2026-01-08','success','success');
/*!40000 ALTER TABLE `form_upload` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `form_upload_view`
--

DROP TABLE IF EXISTS `form_upload_view`;
/*!50001 DROP VIEW IF EXISTS `form_upload_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `form_upload_view` AS SELECT 
 1 AS `upload_id`,
 1 AS `voter_id`,
 1 AS `voter_name`,
 1 AS `upload_date`,
 1 AS `modified_date`,
 1 AS `upload_status`,
 1 AS `remarks`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `forms`
--

DROP TABLE IF EXISTS `forms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forms` (
  `form_id` int NOT NULL AUTO_INCREMENT,
  `voter_id` int DEFAULT NULL,
  PRIMARY KEY (`form_id`),
  KEY `fk_form_voter` (`voter_id`),
  CONSTRAINT `fk_form_voter` FOREIGN KEY (`voter_id`) REFERENCES `voter_details` (`voter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forms`
--

LOCK TABLES `forms` WRITE;
/*!40000 ALTER TABLE `forms` DISABLE KEYS */;
INSERT INTO `forms` VALUES (1,1),(2,2),(3,3),(4,4);
/*!40000 ALTER TABLE `forms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_details`
--

DROP TABLE IF EXISTS `login_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_details` (
  `login_id` int NOT NULL AUTO_INCREMENT,
  `login_name` varchar(45) DEFAULT NULL,
  `login_username` varchar(45) NOT NULL,
  `login_password` varchar(45) NOT NULL,
  `login_email` varchar(45) DEFAULT NULL,
  `login_phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_details`
--

LOCK TABLES `login_details` WRITE;
/*!40000 ALTER TABLE `login_details` DISABLE KEYS */;
INSERT INTO `login_details` VALUES (1,'Ragil K P','ragil','ragil123','ragil@gmail.com','9876543210'),(2,'Amal T','amal','amal123','amal@gmail.com','9798969400');
/*!40000 ALTER TABLE `login_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voter_details`
--

DROP TABLE IF EXISTS `voter_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voter_details` (
  `voter_id` int NOT NULL AUTO_INCREMENT,
  `voter_name` varchar(45) DEFAULT NULL,
  `voter_epic_no` varchar(45) DEFAULT NULL,
  `voter_address` varchar(100) DEFAULT NULL,
  `voter_dob` date DEFAULT NULL,
  `voter_adhar_no` varchar(45) DEFAULT NULL,
  `voter_mobile_no` varchar(45) DEFAULT NULL,
  `booth_id` int DEFAULT NULL,
  PRIMARY KEY (`voter_id`),
  UNIQUE KEY `voter_epic_no_UNIQUE` (`voter_epic_no`),
  UNIQUE KEY `voter_adhar_no_UNIQUE` (`voter_adhar_no`),
  KEY `fk_voter_booth` (`booth_id`),
  CONSTRAINT `fk_voter_booth` FOREIGN KEY (`booth_id`) REFERENCES `booths` (`booth_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voter_details`
--

LOCK TABLES `voter_details` WRITE;
/*!40000 ALTER TABLE `voter_details` DISABLE KEYS */;
INSERT INTO `voter_details` VALUES (1,'Amal K','6780','House no:12, Kannur, kerala','2003-06-03','7894567893456','8765876589',5),(2,'Sumesh R','4586','House no:12,Kannur, Kerala','2002-02-06','3456789876543','987654321',5),(3,'Nithin V V','80698','House no:50, Kannur, kerala','1999-02-10','757745454545','987654321',10),(4,'Biju R','8888','House no:78,Kannur, kerala','1995-02-14','5678907654567','8787868656',4);
/*!40000 ALTER TABLE `voter_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `voter_information_view`
--

DROP TABLE IF EXISTS `voter_information_view`;
/*!50001 DROP VIEW IF EXISTS `voter_information_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `voter_information_view` AS SELECT 
 1 AS `voter_id`,
 1 AS `voter_name`,
 1 AS `voter_epic_no`,
 1 AS `voter_address`,
 1 AS `voter_dob`,
 1 AS `voter_adhar_no`,
 1 AS `voter_mobile_no`,
 1 AS `booth_no`,
 1 AS `ward_no`,
 1 AS `assembly_name`,
 1 AS `district_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `wards`
--

DROP TABLE IF EXISTS `wards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wards` (
  `ward_id` int NOT NULL AUTO_INCREMENT,
  `ward_no` int DEFAULT NULL,
  `assembly_id` int DEFAULT NULL,
  PRIMARY KEY (`ward_id`),
  KEY `fk_ward_assembly` (`assembly_id`),
  CONSTRAINT `fk_ward_assembly` FOREIGN KEY (`assembly_id`) REFERENCES `assembly` (`assembly_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wards`
--

LOCK TABLES `wards` WRITE;
/*!40000 ALTER TABLE `wards` DISABLE KEYS */;
INSERT INTO `wards` VALUES (1,1,11),(2,2,11),(3,3,11),(4,4,11),(5,5,11),(6,6,11),(7,7,11),(8,8,11),(9,9,11),(10,10,11),(11,11,11),(12,12,11);
/*!40000 ALTER TABLE `wards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `current_form_status_view`
--

/*!50001 DROP VIEW IF EXISTS `current_form_status_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `current_form_status_view` AS select `fs`.`form_status_id` AS `form_status_id`,`f`.`voter_id` AS `voter_id`,`v`.`voter_name` AS `voter_name`,`fs`.`form_current_status` AS `form_current_status`,`fs`.`form_last_updated` AS `form_last_updated` from ((`form_status` `fs` join `forms` `f` on((`f`.`form_id` = `fs`.`form_id`))) join `voter_details` `v` on((`f`.`voter_id` = `v`.`voter_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `form_collection_info`
--

/*!50001 DROP VIEW IF EXISTS `form_collection_info`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `form_collection_info` AS select `fc`.`form_collection_id` AS `form_collection_id`,`v`.`voter_id` AS `voter_id`,`v`.`voter_name` AS `voter_name`,`fc`.`form_collection_date` AS `form_collection_date`,`fc`.`form_collection_mode` AS `form_collection_mode` from ((`voter_details` `v` join `forms` `f` on((`v`.`voter_id` = `f`.`voter_id`))) join `form_collection` `fc` on((`fc`.`form_id` = `f`.`form_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `form_distribution_info_master_view`
--

/*!50001 DROP VIEW IF EXISTS `form_distribution_info_master_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `form_distribution_info_master_view` AS select `v`.`voter_name` AS `voter_name`,`v`.`voter_epic_no` AS `voter_epic_no`,`v`.`voter_mobile_no` AS `voter_mobile_no`,`fd`.`form_distribution_date` AS `form_distribution_date`,`fd`.`form_distribution_mode` AS `form_distribution_mode`,`fd`.`form_distribution_collected_by` AS `form_distribution_collected_by` from ((`voter_details` `v` left join `forms` `f` on((`v`.`voter_id` = `f`.`voter_id`))) left join `form_distribution` `fd` on((`fd`.`form_id` = `f`.`form_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `form_distribution_view`
--

/*!50001 DROP VIEW IF EXISTS `form_distribution_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `form_distribution_view` AS select `fd`.`form_distribution_id` AS `form_distribution_id`,`v`.`voter_id` AS `voter_id`,`v`.`voter_name` AS `voter_name`,`v`.`voter_mobile_no` AS `voter_mobile_no`,`fd`.`form_distribution_date` AS `form_distribution_date`,`fd`.`form_distribution_mode` AS `form_distribution_mode`,`fd`.`form_distribution_collected_by` AS `form_distribution_collected_by` from ((`voter_details` `v` join `forms` `f` on((`v`.`voter_id` = `f`.`voter_id`))) join `form_distribution` `fd` on((`fd`.`form_id` = `f`.`form_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `form_upload_view`
--

/*!50001 DROP VIEW IF EXISTS `form_upload_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `form_upload_view` AS select `fu`.`upload_id` AS `upload_id`,`f`.`voter_id` AS `voter_id`,`v`.`voter_name` AS `voter_name`,`fu`.`upload_date` AS `upload_date`,`fu`.`modified_date` AS `modified_date`,`fu`.`upload_status` AS `upload_status`,`fu`.`remarks` AS `remarks` from ((`form_upload` `fu` join `forms` `f` on((`f`.`form_id` = `fu`.`form_id`))) join `voter_details` `v` on((`f`.`voter_id` = `v`.`voter_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `voter_information_view`
--

/*!50001 DROP VIEW IF EXISTS `voter_information_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `voter_information_view` AS select `v`.`voter_id` AS `voter_id`,`v`.`voter_name` AS `voter_name`,`v`.`voter_epic_no` AS `voter_epic_no`,`v`.`voter_address` AS `voter_address`,`v`.`voter_dob` AS `voter_dob`,`v`.`voter_adhar_no` AS `voter_adhar_no`,`v`.`voter_mobile_no` AS `voter_mobile_no`,`b`.`booth_no` AS `booth_no`,`w`.`ward_no` AS `ward_no`,`a`.`assembly_name` AS `assembly_name`,`d`.`district_name` AS `district_name` from ((((`voter_details` `v` join `booths` `b` on((`v`.`booth_id` = `b`.`booth_id`))) join `wards` `w` on((`b`.`ward_id` = `w`.`ward_id`))) join `assembly` `a` on((`w`.`assembly_id` = `a`.`assembly_id`))) join `districts` `d` on((`a`.`district_id` = `d`.`district_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-12 13:41:02
