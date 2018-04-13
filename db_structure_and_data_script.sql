CREATE DATABASE  IF NOT EXISTS `computer_shop` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `computer_shop`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: computer_shop
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `date_of_registration` date NOT NULL,
  `customer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`login`),
  UNIQUE KEY `password_UNIQUE` (`password`),
  KEY `customer_id` (`customer_id`),
  KEY `login_index` (`login`,`password`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (9,'mrfarinq','AcocietO3941po$$','2018-01-08',16),(10,'majka666','Maja3941po%','2018-01-08',17),(11,'jawniak666','Jawniaczek1@','2018-01-08',19),(12,'dominik123','Domino1@','2018-01-08',20),(13,'majarogal','Majarogal1$','2018-01-10',21),(15,'witold123','Witold1$','2018-01-10',26),(16,'konewka123','Konewka1$','2018-01-10',27),(17,'marcinek123','Marcinek123$','2018-01-10',28),(18,'majakrog','Majakrog1$','2018-01-10',29),(19,'majarol','Majarol1$','2018-01-10',30),(20,'majapol','Majapol1$$','2018-01-10',31),(21,'kamama','Kamama1$','2018-01-10',33);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `computer_shop`.`account_BEFORE_INSERT` BEFORE INSERT ON `account` FOR EACH ROW
BEGIN
SET new.date_of_registration=now();
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `computer_shop`.`account_BEFORE_UPDATE` BEFORE UPDATE ON `account` FOR EACH ROW
BEGIN
SET new.date_of_registration=old.date_of_registration;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `street` varchar(40) NOT NULL,
  `city` varchar(30) NOT NULL,
  `postal_code` varchar(6) NOT NULL,
  `country` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `address_index` (`street`,`city`,`postal_code`,`country`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (44,'Czarna 21','Wrocław','51-201','Polska'),(12,'Czekoladowa 7-9','Bielany Wrocławskie','55-040','Polska'),(46,'Duża 21','Portfelisko','40-212','Polska'),(23,'Grochowa 2','Wrocław','51-212','Polska'),(43,'Jaglana','Wrocław','50-123','Polska'),(29,'Jaglana 21','Jaglanowo','51-212','Polska'),(30,'Jk 3/1','Maa','44-555','Pao'),(31,'Jk 3/1','Maa','44-555','Pao'),(32,'Jk 3/1','Maa','44-555','Pao'),(33,'Jk 3/1','Maa','44-555','Pao'),(34,'Jm 3','La','55-555','La'),(42,'Kaa 2','Lk','25-636','Lk'),(38,'Kk 2','Lo','22-555','Lo'),(40,'Ko 2','Ao','22-333','Ao'),(39,'Ko 2','Po','55-999','Po'),(28,'Koszykowa 21','Wrocław','51-212','Polska'),(13,'Legnicka 58','Wrocław','54-204','Polska'),(41,'Maa 1','La','15-555','La'),(22,'Malinowska 21','Syców','41-214','Polska'),(45,'Mała 21','Portfelisko','50-123','Polska'),(14,'Nowy Świat 33','Wrocław','50-135','Polska'),(27,'Nowywiat 32/6','Wrocław','50-135','Polska'),(25,'Ogrodowa 38','Wrocław','50-135','Polska'),(37,'Wapienniczna 21','Wapiennik','41-201','Polska'),(35,'Witkacego 21','Wrocław','51-210','Polska'),(36,'Wypalanka 21','Olsztyn','41-102','Polska'),(24,'Wypalanki 21/22','Poznań','51-211','Polska'),(26,'Zimowa 12','Miedźno','45-124','Polska');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `connector_set_details`
--

DROP TABLE IF EXISTS `connector_set_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `connector_set_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `set_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `set_id_idx` (`set_id`),
  KEY `product_id_idx` (`product_id`),
  CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `set_id` FOREIGN KEY (`set_id`) REFERENCES `product_set` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `connector_set_details`
--

LOCK TABLES `connector_set_details` WRITE;
/*!40000 ALTER TABLE `connector_set_details` DISABLE KEYS */;
INSERT INTO `connector_set_details` VALUES (29,12,21),(30,12,22),(31,13,25),(32,13,22),(33,13,21);
/*!40000 ALTER TABLE `connector_set_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address_id` int(11) NOT NULL,
  `first_name` varchar(40) NOT NULL,
  `last_name` varchar(60) NOT NULL,
  `email` varchar(70) NOT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `address_id` (`address_id`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (13,22,'Clark','Cent','super@man.com','512812351'),(14,23,'Mateusz','Wójcik','mati221@onet.pl','512817261'),(15,24,'Zenon','Wiatrak','wiatraczek@gmail.com','412666123'),(16,25,'Kamil','Cieślik','mrfarinq@hotmail.com','530274763'),(17,26,'Maja','Rogal','majka.rogal@onet.pl','518712351'),(18,27,'Maja','Rogal','majka.rogal@onet.pl','515052325'),(19,28,'Marcin','Jawniak','jawniaczek@gmail.com','512333125'),(20,29,'Dominik','Poprawny','poprawny.dominik@gmail.com','512341234'),(21,30,'Maja','Rrogal','maja@onet.pl','456235856'),(25,34,'Maja','Rogal','maja@onet.pl','545256523'),(26,35,'Witold','Kołdra','kolderka21@gmail.com','212312312'),(27,36,'Marek','Konewka','konewka@gmail.com','412817231'),(28,37,'Marcin','Rogal','rogal@o2.pl','817231283'),(29,38,'Maja','Rog','maja@onet.pl','564555646`'),(30,39,'Maja','Rol','maja@onet.pl','858585858'),(31,40,'Maja','Pol','maja@onet.pl','569659569'),(32,41,'Kaja','Kaa','maja@onet.pl','258526585'),(33,42,'Kama','Maa','mama@ma.pl','286325963'),(35,45,'Patryk','Zdral','patryk@gmail.com','998172312'),(36,46,'Patryk','Zdral','patryk@gmail.com','987123412');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `discount_code` varchar(45) NOT NULL,
  `account_id` int(11) NOT NULL,
  `product_category_id` int(11) NOT NULL,
  `discount_percentage` int(11) NOT NULL DEFAULT '10',
  `is_used` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `discount_code_UNIQUE` (`discount_code`),
  KEY `account_id_idx` (`account_id`),
  KEY `product_category_id_idx` (`product_category_id`),
  CONSTRAINT `account_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `discount_ibfk_1` FOREIGN KEY (`product_category_id`) REFERENCES `product_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (18,'ABCBCA',9,25,10,1),(19,'ABCBCB',9,24,10,1),(20,'0SS4Y3',10,24,20,0),(21,'JKWIW8',9,24,30,1),(22,'DQZUKH',9,24,50,1),(23,'A09O7J',9,25,40,1),(24,'FQGDJR',9,19,20,0),(25,'AXSFLB',9,24,20,0),(26,'5BUI2Q',18,19,10,0),(27,'OBYFRP',18,25,10,0),(28,'CT7JNQ',19,19,10,0),(29,'B46P3B',19,25,10,0),(30,'TON8T7',20,19,10,1),(31,'BOXCGC',20,25,20,1),(32,'KVR4DN',20,19,50,0),(33,'9XZX15',21,24,10,0),(34,'MZ5ZKZ',9,20,10,0),(35,'R3FKPB',9,25,10,0);
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `computer_shop`.`discount_BEFORE_INSERT` BEFORE INSERT ON `discount` FOR EACH ROW
BEGIN
declare ready int default 0;
declare rnd_str text;
if new.discount_code is null then
	while not ready do
		set rnd_str := lpad(conv(floor(rand()*pow(36,6)), 10, 36), 6, 0);
		if not exists (select * from discount where discount_code = rnd_str) then
			set new.discount_code = rnd_str;
			set ready := 1;
		end if;
	end while;
end if;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `place_of_work_id` int(11) NOT NULL,
  `first_name` varchar(40) NOT NULL,
  `last_name` varchar(60) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `place_of_work_id_idx` (`place_of_work_id`),
  KEY `employee_index` (`first_name`,`last_name`,`phone_number`),
  CONSTRAINT `place_of_work_id` FOREIGN KEY (`place_of_work_id`) REFERENCES `stationary_shop` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (10,5,'Cezary','Pazura','698031465'),(11,5,'Jacek','Rozenek','421231231');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_position`
--

DROP TABLE IF EXISTS `order_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `barcode` varchar(45) NOT NULL,
  `purchase_price_brutto` float NOT NULL,
  `purchase_price_netto` float NOT NULL,
  `vat_rate` float NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `barcode_UNIQUE` (`barcode`),
  KEY `used_specimen_ibfk_1` (`product_id`),
  KEY `order_position_ibfk_2_idx` (`order_id`),
  CONSTRAINT `order_position_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `order_position_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `torder` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=986 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_position`
--

LOCK TABLES `order_position` WRITE;
/*!40000 ALTER TABLE `order_position` DISABLE KEYS */;
INSERT INTO `order_position` VALUES (677,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','MES4M6',394.84,304.03,23,11),(678,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','7T6DBT',394.84,304.03,23,11),(680,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','FO68A4',255.84,197,23,12),(692,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','PWC6Q3',394.84,304.03,23,13),(693,20,'Logitech G213 PRODIGY','XU767L',250,192.5,23,14),(694,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','4HAFXE',394.84,304.03,23,14),(695,20,'Logitech G213 PRODIGY','SVUP25',250,192.5,23,15),(696,20,'Logitech G213 PRODIGY','MZE5KF',250,192.5,23,15),(698,20,'Logitech G213 PRODIGY','BQL8Q5',250,192.5,23,16),(699,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','7GDA2A',255.84,197,23,17),(700,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','222O6W',255.84,197,23,17),(703,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','MRK5V0',255.84,197,23,18),(727,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','9VFON1',255.84,197,23,NULL),(748,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','EXIM7F',394.84,304.03,23,19),(749,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','U10230',255.84,197,23,19),(774,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','63406Z',394.84,304.03,23,20),(775,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','NU514Z',394.84,304.03,23,20),(776,20,'Logitech G213 PRODIGY','SXD55V',250,192.5,23,20),(777,20,'Logitech G213 PRODIGY','KE3GVC',250,192.5,23,21),(778,20,'Logitech G213 PRODIGY','Q89RXE',250,192.5,23,22),(781,19,'Apple MacBook Air i5/8GB/128GB/HD','9GDPA1',3799,2925.23,23,23),(782,19,'Apple MacBook Air i5/8GB/128GB/HD','5Q2YND',3799,2925.23,23,23),(790,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','S0YV4Q',394.84,304.03,23,24),(791,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','CMMXJQ',394.84,304.03,23,24),(792,19,'Apple MacBook Air i5/8GB/128GB/HD','F2A2EF',3799,2925.23,23,24),(799,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','HY99LY',394.84,304.03,23,26),(800,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','NT5FRP',394.84,304.03,23,26),(801,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','T6ZE2L',394.84,304.03,23,26),(802,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','2JGN3M',255.84,197,23,26),(803,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','X4D1C9',255.84,197,23,26),(804,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','DZF9GA',255.84,197,23,26),(805,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','YNPOBY',394.84,304.03,23,27),(806,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','NWMTGX',394.84,304.03,23,27),(807,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','FK12EN',394.84,304.03,23,27),(808,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','628VOD',255.84,197,23,27),(809,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','JN577Q',255.84,197,23,27),(810,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','80ZD3F',255.84,197,23,27),(811,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','H7H7VS',255.84,197,23,28),(812,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','PYG06J',255.84,197,23,28),(813,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','65SDB7',255.84,197,23,28),(814,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','OXLU29',394.84,304.03,23,28),(815,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','Y6O2FI',394.84,304.03,23,28),(816,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','O4IU0P',394.84,304.03,23,28),(817,19,'Apple MacBook Air i5/8GB/128GB/HD','I2LYUS',3799,2925.23,23,28),(818,19,'Apple MacBook Air i5/8GB/128GB/HD','HZHC9P',3799,2925.23,23,28),(819,19,'Apple MacBook Air i5/8GB/128GB/HD','ZPKSU1',3799,2925.23,23,28),(820,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','GLFZEW',394.84,304.03,23,29),(822,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','9G3MWK',394.84,304.03,23,29),(823,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','BP1MPT',394.84,304.03,23,29),(830,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','BNY3DA',394.84,304.03,23,31),(831,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','F76L57',394.84,304.03,23,31),(832,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','502NNZ',255.84,197,23,31),(834,20,'Logitech G213 PRODIGY','Q1VNSO',250,192.5,23,31),(835,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','C0XQ6R',394.84,304.03,23,31),(836,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','WJJRTK',394.84,304.03,23,NULL),(837,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','Y5JIJ4',394.84,304.03,23,32),(838,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','HOUNJM',394.84,304.03,23,33),(839,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','KWV72E',394.84,304.03,23,34),(840,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','U7KS0U',255.84,197,23,35),(841,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','M0MWWU',255.84,197,23,35),(842,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','JGG8JK',255.84,197,23,35),(843,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','V8CG14',394.84,304.03,23,35),(844,19,'Apple MacBook Air i5/8GB/128GB/HD','PSDIKU',3799,2925.23,23,35),(845,19,'Apple MacBook Air i5/8GB/128GB/HD','Z8U8UP',3799,2925.23,23,35),(846,19,'Apple MacBook Air i5/8GB/128GB/HD','QV2OKT',3799,2925.23,23,35),(847,19,'Apple MacBook Air i5/8GB/128GB/HD','LJU2C7',3799,2925.23,23,36),(848,19,'Apple MacBook Air i5/8GB/128GB/HD','U7PZK1',3799,2925.23,23,36),(849,19,'Apple MacBook Air i5/8GB/128GB/HD','EF3QTF',3799,2925.23,23,36),(850,19,'Apple MacBook Air i5/8GB/128GB/HD','HGCRGX',3799,2925.23,23,36),(851,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','80GKY7',255.84,197,23,36),(852,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','NP8ME1',255.84,197,23,36),(853,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','MGTD5F',255.84,197,23,36),(854,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','58CYMY',394.84,304.03,23,36),(855,19,'Apple MacBook Air i5/8GB/128GB/HD','URCPSB',3799,2925.23,23,37),(857,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','KFZDY1',255.84,197,23,38),(858,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','PDY069',255.84,197,23,38),(859,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','TLRV33',255.84,197,23,38),(860,19,'Apple MacBook Air i5/8GB/128GB/HD','ZV1AYI',3799,2925.23,23,38),(861,19,'Apple MacBook Air i5/8GB/128GB/HD','IHUXL3',3799,2925.23,23,38),(862,19,'Apple MacBook Air i5/8GB/128GB/HD','KW6R3T',3799,2925.23,23,38),(863,19,'Apple MacBook Air i5/8GB/128GB/HD','CZCYTM',3799,2925.23,23,38),(864,19,'Apple MacBook Air i5/8GB/128GB/HD','288KUK',3799,2925.23,23,39),(865,19,'Apple MacBook Air i5/8GB/128GB/HD','873ZTT',3799,2925.23,23,40),(866,19,'Apple MacBook Air i5/8GB/128GB/HD','YAU8NB',3799,2925.23,23,41),(867,19,'Apple MacBook Air i5/8GB/128GB/HD','2WV7SW',3799,2925.23,23,41),(868,19,'Apple MacBook Air i5/8GB/128GB/HD','JNTD4G',3799,2925.23,23,41),(869,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','HKH69H',255.84,197,23,41),(870,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','SUXRZV',255.84,197,23,41),(871,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','JL9D8T',255.84,197,23,41),(872,20,'Logitech G213 PRODIGY','BDHIAB',250,192.5,23,42),(873,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','Y3NFQY',394.84,304.03,23,43),(874,19,'Apple MacBook Air i5/8GB/128GB/HD','SDSNXN',3799,2925.23,23,44),(875,19,'Apple MacBook Air i5/8GB/128GB/HD','3M10HC',3799,2925.23,23,45),(876,19,'Apple MacBook Air i5/8GB/128GB/HD','4WR2NK',3799,2925.23,23,46),(877,19,'Apple MacBook Air i5/8GB/128GB/HD','DPOBVM',3799,2925.23,23,47),(878,19,'Apple MacBook Air i5/8GB/128GB/HD','HU4FHA',3799,2925.23,23,47),(879,19,'Apple MacBook Air i5/8GB/128GB/HD','C1L5TH',3799,2925.23,23,47),(880,19,'Apple MacBook Air i5/8GB/128GB/HD','6PKIPG',3799,2925.23,23,48),(881,19,'Apple MacBook Air i5/8GB/128GB/HD','XF344L',3799,2925.23,23,48),(882,19,'Apple MacBook Air i5/8GB/128GB/HD','2YQ0KI',3799,2925.23,23,48),(883,19,'Apple MacBook Air i5/8GB/128GB/HD','MKCQIN',3799,2925.23,23,49),(884,19,'Apple MacBook Air i5/8GB/128GB/HD','VXLMXK',3799,2925.23,23,49),(885,19,'Apple MacBook Air i5/8GB/128GB/HD','JYXZ5Q',3799,2925.23,23,49),(886,19,'Apple MacBook Air i5/8GB/128GB/HD','41WZ1T',3799,2925.23,23,50),(887,19,'Apple MacBook Air i5/8GB/128GB/HD','WCQXTR',3799,2925.23,23,50),(888,19,'Apple MacBook Air i5/8GB/128GB/HD','5LZS17',3799,2925.23,23,50),(889,19,'Apple MacBook Air i5/8GB/128GB/HD','2ZQ2QN',3799,2925.23,23,51),(890,19,'Apple MacBook Air i5/8GB/128GB/HD','Y4N1NH',3799,2925.23,23,51),(891,19,'Apple MacBook Air i5/8GB/128GB/HD','HO103A',3799,2925.23,23,51),(892,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','LY7VJV',255.84,197,23,52),(894,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','1WE92U',394.84,304.03,23,52),(895,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','J8Y4U7',394.84,304.03,23,53),(896,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','IJJX09',394.84,304.03,23,53),(897,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','YYX6KL',394.84,304.03,23,53),(898,24,'XFX Radeon RX 580 8GB GTS XXX Edition OC+ 8GB','VFMKR4',1599,1359.15,15,NULL),(899,24,'XFX Radeon RX 580 8GB GTS XXX Edition OC+ 8GB','R7UWIA',1599,1359.15,15,NULL),(900,24,'XFX Radeon RX 580 8GB GTS XXX Edition OC+ 8GB','5SESC0',1599,1359.15,15,NULL),(901,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','JAH872',394.84,304.03,23,NULL),(902,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','735S13',255.84,197,23,NULL),(904,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','AKCQ1B',394.84,304.03,23,NULL),(905,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','C4NZDY',394.84,304.03,23,54),(906,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','SY9QVR',394.84,304.03,23,54),(907,24,'XFX Radeon RX 580 8GB GTS XXX Edition OC+ 8GB','0DCSAQ',1599,1359.15,15,55),(908,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','MZYOW9',255.84,197,23,56),(909,20,'Logitech G213 PRODIGY','5VR3N0',250,192.5,23,56),(910,23,'Zotac GeForce GTX 1060 AMP! Edition 3GB GDDR5','WEVFK4',1059,900.15,15,56),(911,19,'Apple MacBook Air i5/8GB/128GB/HD','0F3UXG',3799,2925.23,23,57),(912,19,'Apple MacBook Air i5/8GB/128GB/HD','CUX00R',3799,2925.23,23,57),(913,19,'Apple MacBook Air i5/8GB/128GB/HD','R19FDC',3799,2925.23,23,57),(914,24,'XFX Radeon RX 580 8GB GTS XXX Edition OC+ 8GB','OLK4U9',1599,1359.15,15,58),(915,24,'XFX Radeon RX 580 8GB GTS XXX Edition OC+ 8GB','5W0E53',1599,1359.15,15,58),(917,25,'ASUS GeForce GTX 1060 Dual OC 6GB GDDR5','CKUMTJ',1449,1231.65,15,58),(918,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','FY4HFS',394.84,304.03,23,58),(919,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','602IS4',255.84,197,23,58),(920,25,'ASUS GeForce GTX 1060 Dual OC 6GB GDDR5','I5Z5N5',1449,1231.65,15,58),(921,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','0TO7X8',394.84,304.03,23,58),(922,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','LMFMQN',255.84,197,23,58),(923,25,'ASUS GeForce GTX 1060 Dual OC 6GB GDDR5','XN5HZB',1449,1231.65,15,58),(924,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','VCGLQI',394.84,304.03,23,58),(925,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','JSUISG',255.84,197,23,58),(926,25,'ASUS GeForce GTX 1060 Dual OC 6GB GDDR5','4YUSSN',1449,1231.65,15,58),(927,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','1FQFNS',394.84,304.03,23,58),(928,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','SA3RYR',255.84,197,23,58),(929,25,'ASUS GeForce GTX 1060 Dual OC 6GB GDDR5','T3BKWD',1449,1231.65,15,58),(930,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','OMAKNG',394.84,304.03,23,58),(931,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','ZTI4LY',255.84,197,23,58),(932,25,'ASUS GeForce GTX 1060 Dual OC 6GB GDDR5','X8MX5C',1449,1231.65,15,58),(933,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','MQO7YN',394.84,304.03,23,58),(934,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','DZGCF4',255.84,197,23,58),(935,25,'ASUS GeForce GTX 1060 Dual OC 6GB GDDR5','1P929H',1449,1231.65,15,58),(936,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','2JWA3V',394.84,304.03,23,58),(937,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','7NQ7SU',255.84,197,23,58),(938,25,'ASUS GeForce GTX 1060 Dual OC 6GB GDDR5','UMY8QE',1449,1231.65,15,58),(939,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','M7KKBD',394.84,304.03,23,58),(940,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','J503FK',255.84,197,23,58),(941,25,'ASUS GeForce GTX 1060 Dual OC 6GB GDDR5','T2AS9J',1449,1231.65,15,58),(942,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','FWHN2U',394.84,304.03,23,58),(943,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','SBJUNF',255.84,197,23,58),(944,25,'ASUS GeForce GTX 1060 Dual OC 6GB GDDR5','LWAC2H',1449,1231.65,15,58),(945,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','OIS4FY',394.84,304.03,23,58),(946,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','KX1M2A',255.84,197,23,58),(947,25,'ASUS GeForce GTX 1060 Dual OC 6GB GDDR5','V0VP1D',1449,1231.65,15,58),(948,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','KD9N1V',394.84,304.03,23,58),(949,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','8RP471',255.84,197,23,58),(950,25,'ASUS GeForce GTX 1060 Dual OC 6GB GDDR5','IQONVJ',1449,1231.65,15,58),(951,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','VEBYUD',394.84,304.03,23,58),(952,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','SRLUN3',255.84,197,23,58),(953,25,'ASUS GeForce GTX 1060 Dual OC 6GB GDDR5','DN1CQ7',1449,1231.65,15,58),(954,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','HWD7RO',394.84,304.03,23,58),(955,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','CKQ0PJ',255.84,197,23,58),(956,25,'ASUS GeForce GTX 1060 Dual OC 6GB GDDR5','96IGAM',1449,1231.65,15,58),(957,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','86E7EA',394.84,304.03,23,58),(958,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','DCFO5H',255.84,197,23,58),(959,25,'ASUS GeForce GTX 1060 Dual OC 6GB GDDR5','66ZQME',1449,1231.65,15,58),(960,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','QXNOPE',394.84,304.03,23,58),(961,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','83B7PP',255.84,197,23,58),(962,25,'ASUS GeForce GTX 1060 Dual OC 6GB GDDR5','VNL0I5',1449,1231.65,15,58),(963,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','PZZFFG',394.84,304.03,23,58),(964,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','Z163OO',255.84,197,23,58),(965,25,'ASUS GeForce GTX 1060 Dual OC 6GB GDDR5','P5W86X',1449,1231.65,15,58),(966,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','KPYTYG',394.84,304.03,23,58),(967,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','S45H9F',255.84,197,23,58),(968,25,'ASUS GeForce GTX 1060 Dual OC 6GB GDDR5','6EUWHJ',1449,1231.65,15,58),(969,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','JPU2P9',394.84,304.03,23,58),(970,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','7CLO3J',255.84,197,23,58),(971,25,'ASUS GeForce GTX 1060 Dual OC 6GB GDDR5','DLI4FS',1449,1231.65,15,58),(972,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','9XPLY8',394.84,304.03,23,58),(973,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','8W1OHL',255.84,197,23,58),(974,25,'ASUS GeForce GTX 1060 Dual OC 6GB GDDR5','EN3KN6',1449,1231.65,15,58),(975,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','AJCTSX',394.84,304.03,23,58),(976,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','8RHF51',255.84,197,23,58),(977,25,'ASUS GeForce GTX 1060 Dual OC 6GB GDDR5','C7CMC6',1449,1231.65,15,58),(978,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','YQAUBO',394.84,304.03,23,58),(979,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','T1GCNN',255.84,197,23,58),(980,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','50D8D6',394.84,304.03,23,58),(981,21,'Razer DeathAdder Elite RZ01-02010100-R3G1','9XH3WP',255.84,197,23,58),(983,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','H4KFVV',394.84,304.03,23,59),(984,23,'Zotac GeForce GTX 1060 AMP! Edition 3GB GDDR5','LEKO5B',1059,900.15,15,60),(985,22,'Razer ORNATA CHROMA RZ03-02040100-R3M1','59SOWF',394.84,304.03,23,61);
/*!40000 ALTER TABLE `order_position` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `computer_shop`.`used_specimen_BEFORE_INSERT` BEFORE INSERT ON `order_position` FOR EACH ROW
BEGIN

declare namee varchar(45);
declare purchase_price_netto decimal(8,2);
declare purchase_price_brutto decimal(8,2);
declare vat_rate integer;
declare amount integer;
declare ready int default 0;
declare rnd_str text;
 
select @namee := p.name, @purchase_price_netto := p.selling_price_netto, @purchase_price_brutto := p.selling_price_brutto,@vat_rate := p.vat_rate
into namee,purchase_price_netto,purchase_price_brutto,vat_rate
from product p
where new.product_id = p.id;
 
set new.name= @namee;
set new.purchase_price_netto= @purchase_price_netto;
set new.purchase_price_brutto= @purchase_price_brutto;
set new.vat_rate= @vat_rate;
 
select @amount:= p.amount
into amount
from product p 
where p.id=new.id;
 
        IF(@amount<=0) then
                SIGNAL SQLSTATE '45001'
                SET MESSAGE_TEXT = 'Produktu nie ma na stanie.';
        end if;

if new.barcode is null then
	while not ready do
		set rnd_str := lpad(conv(floor(rand()*pow(36,6)), 10, 36), 6, 0);
		if not exists (select * from order_position where barcode = rnd_str) then
			set new.barcode = rnd_str;
			set ready := 1;
		end if;
	end while;
end if;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `computer_shop`.`used_specimen_AFTER_INSERT` AFTER INSERT ON `order_position` FOR EACH ROW
BEGIN

update product p
set amount = amount - 1
where p.id = new.product_id;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `computer_shop`.`used_specimen_AFTER_DELETE` AFTER DELETE ON `order_position` FOR EACH ROW
BEGIN

update product p
set amount = amount + 1
where p.id = old.product_id;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `product_category_id` int(11) NOT NULL,
  `vat_rate` decimal(6,2) NOT NULL,
  `selling_price_netto` decimal(8,2) DEFAULT NULL,
  `selling_price_brutto` decimal(8,2) NOT NULL,
  `amount` int(11) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `producer` varchar(40) NOT NULL,
  `model` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `product_category_id` (`product_category_id`),
  KEY `product_index` (`name`,`selling_price_brutto`,`product_category_id`),
  CONSTRAINT `product_category_id` FOREIGN KEY (`id`) REFERENCES `product_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`product_category_id`) REFERENCES `product_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (19,'Apple MacBook Air i5/8GB/128GB/HD',19,23.00,2925.23,3799.00,62,'MacBook wyposażony jest w procesor Intel Core i5 (2 rdzenie), pamięć 8GB (SO-DIMM DDR3), dysk 128GB SSD M.2 PCIe, kartę graficzną Intel HD Graphics 6000. Bez trudu poradzi sobie z obróbką zdjęć, oglądaniem filmów czy też przeglądaniem stron internetowych. Potężna technologia mieści się w niezwykle smukłej i eleganckiej obudowie.','Apple','MacBook Air i5/8GB/128GB/HD'),(20,'Logitech G213 PRODIGY',25,23.00,192.50,250.00,97,'Każdy klawisz klawiatury G213 zbudowaliśmy z myślą o tym, by naciskało się go jak najwygodniej i by działał nawet cztery razy szybciej niż w przypadku standardowych klawiatur. Wykrywanie wielu klawiszy zapewnia optymalną wydajności w grach, zapewniając pełną kontrolę podczas jednoczesnego wydawania wielu poleceń.','Logitech','G213 PRODIGY'),(21,'Razer DeathAdder Elite RZ01-02010100-R3G1',25,23.00,197.00,255.84,62,'Stworzona po to, aby zapewniać Ci niesłychaną przewagę w intensywnej rozgrywce, mysz DeathAdder Elite wyposażona jest w zupełnie nowe klawisze z przełącznikami mechanicznymi Razer™. Zaprojektowane i wyprodukowane we współpracy z wiodącym globalnym wytwórcą switchy mechanicznych, firmą Omron, nowe switche zostały zoptymalizowane oraz dostosowane do uzyskiwania najszybszych czasów reakcji podczas rozgrywki, oferując jednocześnie zwiększoną wytrzymałość nawet do 50 milionów kliknięć.','Razer','DeathAdder Elite RZ01-02010100-R3G1'),(22,'Razer ORNATA CHROMA RZ03-02040100-R3M1',24,23.00,304.03,394.84,58,'Klawiatura Razer Ornata posiada agresywne podświetlenie Chroma, które dostarczy Ci niesamowitych efektów świetlnych. Poczuj się pewniejszy dzięki klawiaturze, która reaguje na Twoje ruchy w grze. Klawisze zostały zaprojektowane w sposób niestandardowy, aby maksymalnie zmniejszyć czas, który jest potrzebny do rejestracji klawisza. Taka konstrukcja pozwala na bardziej swobodne przemieszczanie się pomiędzy każdym klawiszem.','Razer','ORNATA CHROMA RZ03-02040100-R3M1'),(23,'Zotac GeForce GTX 1060 AMP! Edition 3GB GDDR5',20,15.00,900.15,1059.00,22,'Wysoka wydajność oraz niezachwiana stabilność jest na Twoje rozkazy dzięki Zotac GeForce GTX 1060 AMP! Edition 3 GB. Najnowsze tytuły nie są już wyzwaniem, ustaw parametry układu zgodnie ze swoją wolą, korzystając z autorskiego oprogramowania FireStorm. A kiedy walka pochłonie Cię bez reszty, potężny układ chłodzenia usunie ciepłe powietrze, gwarantując niezmiennie wysokie osiągi.','Zotac GeForce','GTX 1060 AMP! Edition 3GB GDDR5'),(24,'XFX Radeon RX 580 8GB GTS XXX Edition OC+ 8GB ',20,15.00,1359.15,1599.00,0,'Oparta na architekturze Polaris oraz rewolucyjnym procesie technologicznym Next FinFET 14 nm karta graficzna XFX Radeon RX 580 GTS XXX Edition OC+ pozwoli Ci zagrać w Twoją ulubioną grę na najwyższych ustawieniach graficznych. Wyposażona w najnowsze technologie, sterowniki oraz poprawki w oprogramowaniu, karta ta będzie świetną inwestycją w gaming na lata.','XFX Radeon','RX 580 8GB GTS XXX Edition OC+ 8GB'),(25,'ASUS GeForce GTX 1060 Dual OC 6GB GDDR5',20,15.00,1231.65,1449.00,0,'Poznaj kartę graficzną GeForce GTX 1060 Dual OC – najbardziej zaawansowany układ GPU na świecie. Kartę wypełniają po brzegi ekskluzywne technologie ASUS, w tym Auto-Extreme, zapewniającą najwyższą jakość i niezawodność. Do tego porty HDMI wspierające VR, GPU Tweak II z XSplit Gamecaster.','ASUS GeForce','GTX 1060 Dual OC 6GB GDDR5');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `computer_shop`.`product_BEFORE_INSERT` BEFORE INSERT ON `product` FOR EACH ROW
BEGIN

DECLARE selling_price decimal(8,2);
SET @selling_price=(100-new.vat_rate)/100;
 
set new.selling_price_netto = @selling_price*new.selling_price_brutto;

IF(new.amount<0) then
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Danego produktu nie ma na stanie.';
end if;
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `computer_shop`.`product_BEFORE_UPDATE` BEFORE UPDATE ON `product` FOR EACH ROW
BEGIN

DECLARE selling_price decimal(8,2);
SET @selling_price=(100-new.vat_rate)/100;
 
set new.selling_price_netto = @selling_price*new.selling_price_brutto;
 
IF(new.amount<0) then
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Danego produktu nie ma na stanie.';
end if;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `computer_shop`.`product_BEFORE_DELETE` BEFORE DELETE ON `product` FOR EACH ROW
BEGIN

IF(old.amount>0) then
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Produkt istnieje jeszcze w bazie danych.';
end if;
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `product_category_index` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (20,'Karty graficzne'),(24,'Klawiatury'),(21,'Konsole'),(25,'Myszy komputerowe'),(19,'PC/Laptopy'),(23,'Słuchawki'),(22,'Telefony');
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_photo`
--

DROP TABLE IF EXISTS `product_photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `photo_path` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_id_idx` (`product_id`),
  CONSTRAINT `product_photo_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_photo`
--

LOCK TABLES `product_photo` WRITE;
/*!40000 ALTER TABLE `product_photo` DISABLE KEYS */;
INSERT INTO `product_photo` VALUES (1,19,'images\\product_images\\1.png'),(2,19,'images\\product_images\\2.png'),(3,19,'images\\product_images\\3.png'),(4,20,'images\\product_images\\12.png'),(5,20,'images\\product_images\\13.png'),(6,20,'images\\product_images\\14.png'),(7,20,'images\\product_images\\15.png'),(8,21,'images\\product_images\\4.png'),(9,21,'images\\product_images\\5.png'),(10,21,'images\\product_images\\6.png'),(11,21,'images\\product_images\\7.png'),(12,22,'images\\product_images\\8.png'),(13,22,'images\\product_images\\9.png'),(14,22,'images\\product_images\\10.png'),(15,22,'images\\product_images\\11.png'),(16,23,'images\\product_images\\16.png'),(17,23,'images\\product_images\\17.png'),(18,23,'images\\product_images\\18.png'),(19,24,'images\\product_images\\19.png'),(20,24,'images\\product_images\\20.png'),(21,24,'images\\product_images\\21.png'),(22,25,'images\\product_images\\22.png'),(23,25,'images\\product_images\\23.png'),(24,25,'images\\product_images\\24.png');
/*!40000 ALTER TABLE `product_photo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_set`
--

DROP TABLE IF EXISTS `product_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_set` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `employee_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `employee_id_idx` (`employee_id`),
  KEY `set_index` (`name`),
  CONSTRAINT `employee_id` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_set`
--

LOCK TABLES `product_set` WRITE;
/*!40000 ALTER TABLE `product_set` DISABLE KEYS */;
INSERT INTO `product_set` VALUES (12,'Razer Ornata Pack (mysz + klawiatura)',10),(13,'Zestaw gamingowy Razer Geaforce (karta graficzna, mysz i klawiatura)',11);
/*!40000 ALTER TABLE `product_set` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stationary_shop`
--

DROP TABLE IF EXISTS `stationary_shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stationary_shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `address_id_idx` (`address_id`),
  CONSTRAINT `address_id` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stationary_shop`
--

LOCK TABLES `stationary_shop` WRITE;
/*!40000 ALTER TABLE `stationary_shop` DISABLE KEYS */;
INSERT INTO `stationary_shop` VALUES (4,'Y-COM Bielany',12),(5,'Y-COM Magnolia',13);
/*!40000 ALTER TABLE `stationary_shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `torder`
--

DROP TABLE IF EXISTS `torder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `torder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `stationary_shop_id` int(11) NOT NULL,
  `promotion_price` decimal(8,2) DEFAULT '0.00',
  `discount_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  KEY `stationary_shop_id` (`stationary_shop_id`),
  KEY `order_ibfk_3_idx` (`discount_id`),
  CONSTRAINT `torder_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `torder_ibfk_2` FOREIGN KEY (`stationary_shop_id`) REFERENCES `stationary_shop` (`id`),
  CONSTRAINT `torder_ibfk_3` FOREIGN KEY (`discount_id`) REFERENCES `discount` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `torder`
--

LOCK TABLES `torder` WRITE;
/*!40000 ALTER TABLE `torder` DISABLE KEYS */;
INSERT INTO `torder` VALUES (11,13,'2018-01-08 02:55:01',4,0.00,NULL),(12,14,'2018-01-08 02:56:33',4,0.00,NULL),(13,15,'2018-01-08 05:54:24',5,0.00,NULL),(14,16,'2018-01-08 06:11:51',4,0.00,NULL),(15,17,'2018-01-08 06:16:03',4,0.00,NULL),(16,16,'2018-01-08 08:16:08',5,0.00,NULL),(17,16,'2018-01-08 08:16:57',5,0.00,NULL),(18,16,'2018-01-08 08:35:21',5,0.00,NULL),(19,16,'2018-01-08 14:05:52',5,25.58,18),(20,16,'2018-01-08 14:25:22',5,78.97,19),(21,17,'2018-01-08 15:25:06',4,0.00,NULL),(22,18,'2018-01-08 15:26:29',4,0.00,NULL),(23,20,'2018-01-08 15:49:43',5,0.00,NULL),(24,16,'2018-01-10 03:02:52',5,236.90,21),(26,16,'2018-01-10 03:41:56',5,0.00,NULL),(27,16,'2018-01-10 03:43:34',5,0.00,NULL),(28,16,'2018-01-10 03:44:22',5,0.00,NULL),(29,16,'2018-01-10 03:45:50',5,197.42,22),(30,21,'2018-01-10 04:09:13',5,0.00,NULL),(31,25,'2018-01-10 04:15:06',5,0.00,NULL),(32,26,'2018-01-10 04:36:43',4,0.00,NULL),(33,27,'2018-01-10 04:42:32',4,0.00,NULL),(34,28,'2018-01-10 04:44:45',4,0.00,NULL),(35,29,'2018-01-10 04:47:13',5,0.00,NULL),(36,30,'2018-01-10 04:53:13',5,0.00,NULL),(37,30,'2018-01-10 04:58:15',5,0.00,NULL),(38,31,'2018-01-10 05:11:29',4,0.00,NULL),(39,31,'2018-01-10 05:13:02',4,0.00,NULL),(40,31,'2018-01-10 05:16:44',4,379.90,30),(41,31,'2018-01-10 05:18:24',4,0.00,NULL),(42,32,'2018-01-10 05:20:38',4,0.00,NULL),(43,31,'2018-01-10 05:25:09',4,0.00,31),(44,31,'2018-01-10 05:27:20',4,0.00,NULL),(45,31,'2018-01-10 05:27:30',4,0.00,NULL),(46,31,'2018-01-10 05:28:09',4,0.00,NULL),(47,31,'2018-01-10 05:28:32',4,0.00,NULL),(48,31,'2018-01-10 05:28:43',4,0.00,NULL),(49,31,'2018-01-10 05:29:08',4,0.00,NULL),(50,31,'2018-01-10 05:29:18',4,0.00,NULL),(51,31,'2018-01-10 05:29:38',4,0.00,NULL),(52,31,'2018-01-10 05:30:20',4,0.00,NULL),(53,33,'2018-01-10 05:32:55',4,0.00,NULL),(54,35,'2018-01-10 06:19:53',5,0.00,NULL),(55,36,'2018-01-10 06:21:16',5,0.00,NULL),(56,16,'2018-01-10 06:25:08',5,202.34,23),(57,16,'2018-01-10 06:25:25',5,0.00,NULL),(58,16,'2018-01-10 06:28:17',5,0.00,NULL),(59,16,'2018-01-10 06:47:23',5,0.00,NULL),(60,16,'2018-01-10 06:52:06',4,0.00,NULL),(61,16,'2018-01-10 07:00:46',5,0.00,NULL);
/*!40000 ALTER TABLE `torder` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `computer_shop`.`order_BEFORE_INSERT` BEFORE INSERT ON `torder` FOR EACH ROW
BEGIN
SET new.date=now();
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Temporary view structure for view `view_info_about_customers`
--

DROP TABLE IF EXISTS `view_info_about_customers`;
/*!50001 DROP VIEW IF EXISTS `view_info_about_customers`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_info_about_customers` AS SELECT 
 1 AS `first_name`,
 1 AS `last_name`,
 1 AS `email`,
 1 AS `phone_number`,
 1 AS `account_login`,
 1 AS `street`,
 1 AS `city`,
 1 AS `postal_code`,
 1 AS `country`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_info_about_discounts`
--

DROP TABLE IF EXISTS `view_info_about_discounts`;
/*!50001 DROP VIEW IF EXISTS `view_info_about_discounts`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_info_about_discounts` AS SELECT 
 1 AS `id`,
 1 AS `discount_code`,
 1 AS `account_id`,
 1 AS `product_category`,
 1 AS `discount_percentage`,
 1 AS `is_used`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_info_about_employees_and_their_place_of_work`
--

DROP TABLE IF EXISTS `view_info_about_employees_and_their_place_of_work`;
/*!50001 DROP VIEW IF EXISTS `view_info_about_employees_and_their_place_of_work`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_info_about_employees_and_their_place_of_work` AS SELECT 
 1 AS `first_name`,
 1 AS `last_name`,
 1 AS `workplace_address`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_info_about_products`
--

DROP TABLE IF EXISTS `view_info_about_products`;
/*!50001 DROP VIEW IF EXISTS `view_info_about_products`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_info_about_products` AS SELECT 
 1 AS `product_category`,
 1 AS `product_name`,
 1 AS `price_netto`,
 1 AS `price_brutto`,
 1 AS `amount`,
 1 AS `belongs_to_set`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_info_about_stationary_shop`
--

DROP TABLE IF EXISTS `view_info_about_stationary_shop`;
/*!50001 DROP VIEW IF EXISTS `view_info_about_stationary_shop`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_info_about_stationary_shop` AS SELECT 
 1 AS `id`,
 1 AS `name`,
 1 AS `street`,
 1 AS `city`,
 1 AS `postal_code`,
 1 AS `country`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_order_details`
--

DROP TABLE IF EXISTS `view_order_details`;
/*!50001 DROP VIEW IF EXISTS `view_order_details`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_order_details` AS SELECT 
 1 AS `id`,
 1 AS `date`,
 1 AS `stationary_shop_name`,
 1 AS `standard_order_price`,
 1 AS `discount_code`,
 1 AS `promotion_order_price`,
 1 AS `customer_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_order_positions_of_order`
--

DROP TABLE IF EXISTS `view_order_positions_of_order`;
/*!50001 DROP VIEW IF EXISTS `view_order_positions_of_order`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_order_positions_of_order` AS SELECT 
 1 AS `id`,
 1 AS `order_id`,
 1 AS `name`,
 1 AS `barcode`,
 1 AS `purchase_price_netto`,
 1 AS `purchase_price_brutto`,
 1 AS `vat_rate`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_products_of_set`
--

DROP TABLE IF EXISTS `view_products_of_set`;
/*!50001 DROP VIEW IF EXISTS `view_products_of_set`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_products_of_set` AS SELECT 
 1 AS `set_name`,
 1 AS `product_category`,
 1 AS `product_name`,
 1 AS `price`,
 1 AS `amount`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_set_details`
--

DROP TABLE IF EXISTS `view_set_details`;
/*!50001 DROP VIEW IF EXISTS `view_set_details`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_set_details` AS SELECT 
 1 AS `id`,
 1 AS `set_name`,
 1 AS `total_price`,
 1 AS `set_patron`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping events for database 'computer_shop'
--

--
-- Dumping routines for database 'computer_shop'
--

--
-- Final view structure for view `view_info_about_customers`
--

/*!50001 DROP VIEW IF EXISTS `view_info_about_customers`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_info_about_customers` AS select `c`.`first_name` AS `first_name`,`c`.`last_name` AS `last_name`,`c`.`email` AS `email`,ifnull(`c`.`phone_number`,'-') AS `phone_number`,ifnull(`ac`.`login`,'-') AS `account_login`,`a`.`street` AS `street`,`a`.`city` AS `city`,`a`.`postal_code` AS `postal_code`,`a`.`country` AS `country` from ((`customer` `c` left join `address` `a` on((`a`.`id` = `c`.`address_id`))) left join `account` `ac` on((`ac`.`customer_id` = `c`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_info_about_discounts`
--

/*!50001 DROP VIEW IF EXISTS `view_info_about_discounts`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_info_about_discounts` AS select `d`.`id` AS `id`,`d`.`discount_code` AS `discount_code`,`d`.`account_id` AS `account_id`,`c`.`name` AS `product_category`,`d`.`discount_percentage` AS `discount_percentage`,(case when (`d`.`is_used` = 1) then 'wykorzystany' else 'niewykorzystany' end) AS `is_used` from (`discount` `d` left join `product_category` `c` on((`c`.`id` = `d`.`product_category_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_info_about_employees_and_their_place_of_work`
--

/*!50001 DROP VIEW IF EXISTS `view_info_about_employees_and_their_place_of_work`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_info_about_employees_and_their_place_of_work` AS select `e`.`first_name` AS `first_name`,`e`.`last_name` AS `last_name`,concat(concat(concat(concat(concat(concat(concat(`a`.`street`,' '),`a`.`postal_code`),' '),' '),`a`.`city`),' '),`a`.`country`) AS `workplace_address` from ((`employee` `e` left join `stationary_shop` `s` on((`s`.`id` = `e`.`place_of_work_id`))) left join `address` `a` on((`a`.`id` = `s`.`address_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_info_about_products`
--

/*!50001 DROP VIEW IF EXISTS `view_info_about_products`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_info_about_products` AS select `c`.`name` AS `product_category`,`p`.`name` AS `product_name`,`p`.`selling_price_netto` AS `price_netto`,`p`.`selling_price_brutto` AS `price_brutto`,`p`.`amount` AS `amount`,ifnull(`s`.`name`,'-') AS `belongs_to_set` from (((`product` `p` left join `product_category` `c` on((`c`.`id` = `p`.`product_category_id`))) left join `connector_set_details` `d_s` on((`d_s`.`product_id` = `p`.`id`))) left join `product_set` `s` on((`s`.`id` = `d_s`.`set_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_info_about_stationary_shop`
--

/*!50001 DROP VIEW IF EXISTS `view_info_about_stationary_shop`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_info_about_stationary_shop` AS select `s`.`id` AS `id`,`s`.`name` AS `name`,`a`.`street` AS `street`,`a`.`city` AS `city`,`a`.`postal_code` AS `postal_code`,`a`.`country` AS `country` from (`stationary_shop` `s` left join `address` `a` on((`a`.`id` = `s`.`address_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_order_details`
--

/*!50001 DROP VIEW IF EXISTS `view_order_details`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_order_details` AS select `o`.`id` AS `id`,`o`.`date` AS `date`,`sh`.`name` AS `stationary_shop_name`,sum(`op`.`purchase_price_brutto`) AS `standard_order_price`,ifnull(`d`.`discount_code`,'-') AS `discount_code`,(sum(`op`.`purchase_price_brutto`) - `o`.`promotion_price`) AS `promotion_order_price`,`c`.`id` AS `customer_id` from ((((`torder` `o` left join `order_position` `op` on((`op`.`order_id` = `o`.`id`))) left join `stationary_shop` `sh` on((`sh`.`id` = `o`.`stationary_shop_id`))) left join `customer` `c` on((`c`.`id` = `o`.`customer_id`))) left join `discount` `d` on((`d`.`id` = `o`.`discount_id`))) group by `o`.`date`,`sh`.`name`,`c`.`first_name`,`c`.`last_name`,`c`.`id`,`d`.`discount_code`,`o`.`promotion_price` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_order_positions_of_order`
--

/*!50001 DROP VIEW IF EXISTS `view_order_positions_of_order`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_order_positions_of_order` AS select uuid() AS `id`,`o`.`id` AS `order_id`,`op`.`name` AS `name`,`op`.`barcode` AS `barcode`,`op`.`purchase_price_netto` AS `purchase_price_netto`,`op`.`purchase_price_brutto` AS `purchase_price_brutto`,`op`.`vat_rate` AS `vat_rate` from (`order_position` `op` left join `torder` `o` on((`o`.`id` = `op`.`order_id`))) where (`o`.`date` is not null) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_products_of_set`
--

/*!50001 DROP VIEW IF EXISTS `view_products_of_set`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_products_of_set` AS select `s`.`name` AS `set_name`,`c`.`name` AS `product_category`,`p`.`name` AS `product_name`,`p`.`selling_price_brutto` AS `price`,`p`.`amount` AS `amount` from (((`product` `p` left join `product_category` `c` on((`c`.`id` = `p`.`product_category_id`))) left join `connector_set_details` `d_s` on((`d_s`.`product_id` = `p`.`id`))) left join `product_set` `s` on((`s`.`id` = `d_s`.`set_id`))) where (`s`.`name` is not null) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_set_details`
--

/*!50001 DROP VIEW IF EXISTS `view_set_details`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_set_details` AS select `s`.`id` AS `id`,`s`.`name` AS `set_name`,sum(`p`.`selling_price_brutto`) AS `total_price`,concat(concat(`e`.`first_name`,' '),`e`.`last_name`) AS `set_patron` from (((`product_set` `s` left join `connector_set_details` `d_s` on((`s`.`id` = `d_s`.`set_id`))) left join `product` `p` on((`d_s`.`product_id` = `p`.`id`))) left join `employee` `e` on((`e`.`id` = `s`.`employee_id`))) group by `s`.`name`,`e`.`first_name`,`e`.`last_name` */;
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

-- Dump completed on 2018-01-24  6:00:18
