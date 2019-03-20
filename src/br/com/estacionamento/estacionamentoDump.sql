CREATE DATABASE  IF NOT EXISTS `estacionamento` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `estacionamento`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: estacionamento
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Temporary view structure for view `preco_por_minuto`
--

DROP TABLE IF EXISTS `preco_por_minuto`;
/*!50001 DROP VIEW IF EXISTS `preco_por_minuto`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `preco_por_minuto` AS SELECT 
 1 AS `PRECO_MINUTO`,
 1 AS `TB_TABELA_PRECO_TEMPO_MAX`,
 1 AS `TB_TABELA_PRECO_TEMPO_MIN`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `tb_cidade`
--

DROP TABLE IF EXISTS `tb_cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_cidade` (
  `TB_CIDADE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TB_CIDADE_NOME` varchar(50) NOT NULL,
  `TB_ESTADO_ID` int(11) NOT NULL,
  `TB_CIDADE_STATUS` enum('ATIVO','INATIVO') NOT NULL,
  PRIMARY KEY (`TB_CIDADE_ID`),
  KEY `fk_TB_CIDADE_TB_ESTADO_idx` (`TB_ESTADO_ID`),
  CONSTRAINT `FKm22c9u3ig9jjk8gyutlnkbmuy` FOREIGN KEY (`TB_ESTADO_ID`) REFERENCES `tb_estado` (`tb_estado_id`),
  CONSTRAINT `fk_TB_CIDADE_TB_ESTADO` FOREIGN KEY (`TB_ESTADO_ID`) REFERENCES `tb_estado` (`tb_estado_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cidade`
--

LOCK TABLES `tb_cidade` WRITE;
/*!40000 ALTER TABLE `tb_cidade` DISABLE KEYS */;
INSERT INTO `tb_cidade` VALUES (1,'Londrina',1,'ATIVO'),(2,'Cambé',1,'ATIVO'),(3,'Rolândia',1,'ATIVO');
/*!40000 ALTER TABLE `tb_cidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_endereco`
--

DROP TABLE IF EXISTS `tb_endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_endereco` (
  `TB_ENDERECO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TB_ENDERECO_CEP` varchar(8) NOT NULL,
  `TB_ENDERECO_LOGRADOURO` varchar(100) NOT NULL,
  `TB_ENDERECO_NUM` varchar(10) NOT NULL,
  `TB_ENDERECO_COMP` varchar(100) DEFAULT NULL,
  `TB_CIDADE_ID` int(11) NOT NULL,
  `TB_MENSALISTA_ID` int(11) NOT NULL,
  `TB_ENDERECO_STATUS` enum('ATIVO','INATIVO') DEFAULT NULL,
  PRIMARY KEY (`TB_ENDERECO_ID`),
  KEY `fk_TB_ENDERECO_TB_CIDADE1_idx` (`TB_CIDADE_ID`),
  KEY `fk_TB_ENDERECO_TB_MENSALISTA1_idx` (`TB_MENSALISTA_ID`),
  CONSTRAINT `FKbagc0hn1ggtbr87p50wm9eiew` FOREIGN KEY (`TB_CIDADE_ID`) REFERENCES `tb_cidade` (`tb_cidade_id`),
  CONSTRAINT `FKfvw17cey82s6m9tmwdooi3h04` FOREIGN KEY (`TB_MENSALISTA_ID`) REFERENCES `tb_mensalista` (`tb_mensalista_id`),
  CONSTRAINT `fk_TB_ENDERECO_TB_CIDADE1` FOREIGN KEY (`TB_CIDADE_ID`) REFERENCES `tb_cidade` (`tb_cidade_id`),
  CONSTRAINT `fk_TB_ENDERECO_TB_MENSALISTA1` FOREIGN KEY (`TB_MENSALISTA_ID`) REFERENCES `tb_mensalista` (`tb_mensalista_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_endereco`
--

LOCK TABLES `tb_endereco` WRITE;
/*!40000 ALTER TABLE `tb_endereco` DISABLE KEYS */;
INSERT INTO `tb_endereco` VALUES (1,'86055784','Rua João Candido','103','bloco b apt 102',1,1,NULL),(2,'86055784','Rua João Candido','103','bloco b apt 102',2,4,NULL),(3,'86055746','Rua Pernanbuco','574',NULL,1,4,NULL),(4,'86055447','Rua Hugo Cabral','1697','sala 5',1,4,NULL);
/*!40000 ALTER TABLE `tb_endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_estadia`
--

DROP TABLE IF EXISTS `tb_estadia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_estadia` (
  `TB_ESTADIA_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TB_ESTADIA_INICIO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `TB_ESTADIA_TERMINO` timestamp NULL DEFAULT NULL,
  `TB_ESTADIA_PLACA` varchar(8) NOT NULL,
  `TB_ESTADIA_STATUS` enum('ativo','inativo') NOT NULL,
  `TB_TABELA_PRECO_ID` int(11) NOT NULL,
  `TB_VAGA_ID` int(11) NOT NULL,
  `TB_VEICULO_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`TB_ESTADIA_ID`),
  KEY `fk_TB_ESTADIA_TB_TABELA_PRECO1_idx` (`TB_TABELA_PRECO_ID`),
  KEY `fk_TB_ESTADIA_TB_VAGA1_idx` (`TB_VAGA_ID`),
  KEY `fk_TB_ESTADIA_TB_VEICULO1_idx` (`TB_VEICULO_ID`),
  CONSTRAINT `fk_TB_ESTADIA_TB_TABELA_PRECO1` FOREIGN KEY (`TB_TABELA_PRECO_ID`) REFERENCES `tb_tabela_preco` (`tb_tabela_preco_id`),
  CONSTRAINT `fk_TB_ESTADIA_TB_VAGA1` FOREIGN KEY (`TB_VAGA_ID`) REFERENCES `tb_vaga` (`tb_vaga_id`),
  CONSTRAINT `fk_TB_ESTADIA_TB_VEICULO1` FOREIGN KEY (`TB_VEICULO_ID`) REFERENCES `tb_veiculo` (`tb_veiculo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_estadia`
--

LOCK TABLES `tb_estadia` WRITE;
/*!40000 ALTER TABLE `tb_estadia` DISABLE KEYS */;
INSERT INTO `tb_estadia` VALUES (1,'2019-02-27 21:07:03','2019-02-28 09:15:43','aaa1111','ativo',2,1,1),(2,'2019-02-28 14:54:21','2019-02-28 15:11:47','asd1234','ativo',1,1,1),(3,'2019-02-28 16:02:39',NULL,'aaa1111','ativo',2,1,1),(4,'2019-02-28 16:02:39','2019-02-28 18:10:10','aaa1111','ativo',2,1,1),(5,'2019-02-28 16:02:39','2019-02-28 18:10:55','aaa1111','ativo',2,1,NULL),(6,'2019-01-04 15:02:39',NULL,'aaa1111','ativo',2,1,NULL),(7,'2019-01-04 15:02:39','2019-03-04 16:19:53','aaa1221','ativo',2,1,NULL),(8,'2019-02-28 11:02:39','2019-02-28 12:37:39','aaa1111','ativo',2,1,NULL),(9,'2019-02-28 11:02:39','2019-02-28 12:37:39','bbb2222','ativo',2,1,NULL),(10,'2019-02-28 11:02:39','2019-02-28 12:37:39','bbb2222','ativo',2,1,NULL),(11,'2019-02-28 16:02:39',NULL,'aaa1111','ativo',2,1,2),(12,'2019-01-04 15:02:39','2019-03-06 16:54:18','aaa1221','ativo',2,1,2),(13,'2019-02-28 16:02:39',NULL,'aaa1111','ativo',2,1,2),(14,'2019-01-04 15:02:39','2019-03-06 17:46:27','aaa1221','ativo',2,1,2);
/*!40000 ALTER TABLE `tb_estadia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_estado`
--

DROP TABLE IF EXISTS `tb_estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_estado` (
  `TB_ESTADO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TB_ESTADO_NOME` varchar(50) NOT NULL,
  `TB_ESTADO_SIGLA` varchar(2) NOT NULL,
  `TB_ESTADO_STATUS` enum('ATIVO','INATIVO') NOT NULL,
  PRIMARY KEY (`TB_ESTADO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_estado`
--

LOCK TABLES `tb_estado` WRITE;
/*!40000 ALTER TABLE `tb_estado` DISABLE KEYS */;
INSERT INTO `tb_estado` VALUES (1,'Paraná','PR','ATIVO'),(3,'Rio de Janeiro','RJ','ATIVO'),(4,'São Paulo','SC','ATIVO'),(5,'Santa Catarina','SC','INATIVO'),(7,'Teste','TT','ATIVO'),(17,'Teste2','TB','ATIVO');
/*!40000 ALTER TABLE `tb_estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_marca`
--

DROP TABLE IF EXISTS `tb_marca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_marca` (
  `TB_MARCA_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TB_MARCA_DESC` varchar(50) NOT NULL,
  `TB_MARCA_STATUS` enum('ATIVO','INATIVO') DEFAULT NULL,
  PRIMARY KEY (`TB_MARCA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_marca`
--

LOCK TABLES `tb_marca` WRITE;
/*!40000 ALTER TABLE `tb_marca` DISABLE KEYS */;
INSERT INTO `tb_marca` VALUES (1,'Fiat','ATIVO'),(2,'Toyota','ATIVO'),(3,'Nissan','INATIVO'),(5,'teste2','INATIVO');
/*!40000 ALTER TABLE `tb_marca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_mensalista`
--

DROP TABLE IF EXISTS `tb_mensalista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_mensalista` (
  `TB_MENSALISTA_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TB_MENSALISTA_NOME` varchar(100) NOT NULL,
  `TB_MENSALISTA_CPF` varchar(11) NOT NULL,
  `TB_MENSALISTA_STATUS` enum('ATIVO','INATIVO') DEFAULT NULL,
  PRIMARY KEY (`TB_MENSALISTA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_mensalista`
--

LOCK TABLES `tb_mensalista` WRITE;
/*!40000 ALTER TABLE `tb_mensalista` DISABLE KEYS */;
INSERT INTO `tb_mensalista` VALUES (1,'João','11122233344',NULL),(2,'Maria','22211144433',NULL),(3,'Pedro','44433322211',NULL),(4,'Ana','33322233311',NULL);
/*!40000 ALTER TABLE `tb_mensalista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_mensalista_veiculo`
--

DROP TABLE IF EXISTS `tb_mensalista_veiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_mensalista_veiculo` (
  `TB_MENSALISTA_VEICULO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TB_MENSALISTA_VEICULO_PROPRIETARIO` tinyint(1) NOT NULL,
  `TB_MENSALISTA_ID` int(11) NOT NULL,
  `TB_VEICULO_ID` int(11) NOT NULL,
  `TB_MENSALISTA_VEICULO_STATUS` enum('ativo','inativo') NOT NULL,
  PRIMARY KEY (`TB_MENSALISTA_VEICULO_ID`),
  KEY `fk_TB_MENSALISTA_VEICULO_TB_MENSALISTA1_idx` (`TB_MENSALISTA_ID`),
  KEY `fk_TB_MENSALISTA_VEICULO_TB_VEICULO1_idx` (`TB_VEICULO_ID`),
  CONSTRAINT `fk_TB_MENSALISTA_VEICULO_TB_MENSALISTA1` FOREIGN KEY (`TB_MENSALISTA_ID`) REFERENCES `tb_mensalista` (`tb_mensalista_id`),
  CONSTRAINT `fk_TB_MENSALISTA_VEICULO_TB_VEICULO1` FOREIGN KEY (`TB_VEICULO_ID`) REFERENCES `tb_veiculo` (`tb_veiculo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_mensalista_veiculo`
--

LOCK TABLES `tb_mensalista_veiculo` WRITE;
/*!40000 ALTER TABLE `tb_mensalista_veiculo` DISABLE KEYS */;
INSERT INTO `tb_mensalista_veiculo` VALUES (1,1,1,1,'ativo'),(2,0,2,1,'ativo'),(3,1,4,2,'ativo'),(4,1,4,3,'ativo');
/*!40000 ALTER TABLE `tb_mensalista_veiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_modelo`
--

DROP TABLE IF EXISTS `tb_modelo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_modelo` (
  `TB_MODELO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TB_MODELO_DESC` varchar(50) NOT NULL,
  `TB_TIPO_VEICULO_ID` int(11) NOT NULL,
  `TB_MARCA_ID` int(11) NOT NULL,
  `TB_MODELO_STATUS` enum('ATIVO','INATIVO') DEFAULT NULL,
  PRIMARY KEY (`TB_MODELO_ID`),
  KEY `fk_TB_MODELO_TB_TIPO_VEICULO1_idx` (`TB_TIPO_VEICULO_ID`),
  KEY `fk_TB_MODELO_TB_MARCA1_idx` (`TB_MARCA_ID`),
  CONSTRAINT `FK10af2ll0y4xaolyrai9jin4xi` FOREIGN KEY (`TB_TIPO_VEICULO_ID`) REFERENCES `tb_tipo_veiculo` (`tb_tipo_veiculo_id`),
  CONSTRAINT `FKoofah883cn405jc2f73h6ms2s` FOREIGN KEY (`TB_MARCA_ID`) REFERENCES `tb_marca` (`TB_MARCA_ID`),
  CONSTRAINT `fk_TB_MODELO_TB_MARCA1` FOREIGN KEY (`TB_MARCA_ID`) REFERENCES `tb_marca` (`TB_MARCA_ID`),
  CONSTRAINT `fk_TB_MODELO_TB_TIPO_VEICULO1` FOREIGN KEY (`TB_TIPO_VEICULO_ID`) REFERENCES `tb_tipo_veiculo` (`tb_tipo_veiculo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_modelo`
--

LOCK TABLES `tb_modelo` WRITE;
/*!40000 ALTER TABLE `tb_modelo` DISABLE KEYS */;
INSERT INTO `tb_modelo` VALUES (1,'Toro',1,1,NULL),(2,'Corolla',3,2,NULL),(3,'HRV',2,1,NULL);
/*!40000 ALTER TABLE `tb_modelo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_tabela_preco`
--

DROP TABLE IF EXISTS `tb_tabela_preco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_tabela_preco` (
  `TB_TABELA_PRECO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TB_TABELA_PRECO_VALOR` double NOT NULL,
  `TB_TABELA_PRECO_STATUS` enum('ativo','inativo') NOT NULL,
  `TB_TABELA_PRECO_TEMPO_MIN` int(11) NOT NULL,
  `TB_TABELA_PRECO_TEMPO_MAX` int(11) NOT NULL,
  PRIMARY KEY (`TB_TABELA_PRECO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_tabela_preco`
--

LOCK TABLES `tb_tabela_preco` WRITE;
/*!40000 ALTER TABLE `tb_tabela_preco` DISABLE KEYS */;
INSERT INTO `tb_tabela_preco` VALUES (1,1.5,'ativo',0,30),(2,2,'inativo',30,60),(3,5,'ativo',60,120),(4,3,'ativo',30,60),(5,10,'ativo',0,720);
/*!40000 ALTER TABLE `tb_tabela_preco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_telefone`
--

DROP TABLE IF EXISTS `tb_telefone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_telefone` (
  `TB_TELEFONE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TB_TELEFONE_NUM` varchar(11) NOT NULL,
  `TB_MENSALISTA_ID` int(11) NOT NULL,
  `TB_TELEFONE_STATUS` enum('ATIVO','INATIVO') DEFAULT NULL,
  PRIMARY KEY (`TB_TELEFONE_ID`),
  KEY `fk_TB_TELEFONE_TB_MENSALISTA1_idx` (`TB_MENSALISTA_ID`),
  CONSTRAINT `FK8x6gkgub7lqm489lgoyu9ml21` FOREIGN KEY (`TB_MENSALISTA_ID`) REFERENCES `tb_mensalista` (`tb_mensalista_id`),
  CONSTRAINT `fk_TB_TELEFONE_TB_MENSALISTA1` FOREIGN KEY (`TB_MENSALISTA_ID`) REFERENCES `tb_mensalista` (`tb_mensalista_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_telefone`
--

LOCK TABLES `tb_telefone` WRITE;
/*!40000 ALTER TABLE `tb_telefone` DISABLE KEYS */;
INSERT INTO `tb_telefone` VALUES (1,'33448877',1,NULL),(2,'33425567',1,NULL),(3,'33774512',4,NULL),(4,'33448877',2,NULL);
/*!40000 ALTER TABLE `tb_telefone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_tipo_veiculo`
--

DROP TABLE IF EXISTS `tb_tipo_veiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_tipo_veiculo` (
  `TB_TIPO_VEICULO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TB_TIPO_VEICULO_DESC` varchar(50) NOT NULL,
  `TB_TIPO_VEICULO_STATUS` enum('ATIVO','INATIVO') DEFAULT NULL,
  PRIMARY KEY (`TB_TIPO_VEICULO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_tipo_veiculo`
--

LOCK TABLES `tb_tipo_veiculo` WRITE;
/*!40000 ALTER TABLE `tb_tipo_veiculo` DISABLE KEYS */;
INSERT INTO `tb_tipo_veiculo` VALUES (1,'Utilitário',NULL),(2,'SUV',NULL),(3,'Passeio',NULL);
/*!40000 ALTER TABLE `tb_tipo_veiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_usuario`
--

DROP TABLE IF EXISTS `tb_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_usuario` (
  `TB_USUARIO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TB_USUARIO_NOME` varchar(10) NOT NULL,
  `TB_USUARIO_SENHA` varchar(10) NOT NULL,
  `TB_USUARIO_STATUS` enum('ATIVO','INATIVO') DEFAULT NULL,
  PRIMARY KEY (`TB_USUARIO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuario`
--

LOCK TABLES `tb_usuario` WRITE;
/*!40000 ALTER TABLE `tb_usuario` DISABLE KEYS */;
INSERT INTO `tb_usuario` VALUES (1,'admin','admin123','ATIVO'),(2,'teste','1337','INATIVO'),(3,'teste22','12345','INATIVO');
/*!40000 ALTER TABLE `tb_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_vaga`
--

DROP TABLE IF EXISTS `tb_vaga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_vaga` (
  `TB_VAGA_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TB_VAGA_DESC` varchar(45) NOT NULL,
  `TB_VAGA_STATUS` enum('ativo','inativo') NOT NULL,
  PRIMARY KEY (`TB_VAGA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_vaga`
--

LOCK TABLES `tb_vaga` WRITE;
/*!40000 ALTER TABLE `tb_vaga` DISABLE KEYS */;
INSERT INTO `tb_vaga` VALUES (1,'Vaga 1','ativo'),(2,'Vaga 2','ativo'),(3,'Vaga 3','ativo'),(4,'Vaga 4','ativo'),(5,'Vaga 5','ativo');
/*!40000 ALTER TABLE `tb_vaga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_veiculo`
--

DROP TABLE IF EXISTS `tb_veiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_veiculo` (
  `TB_VEICULO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TB_VEICULO_PLACA` varchar(50) NOT NULL,
  `TB_MODELO_ID` int(11) NOT NULL,
  `TB_VEICULO_STATUS` enum('ATIVO','INATIVO') DEFAULT NULL,
  PRIMARY KEY (`TB_VEICULO_ID`),
  KEY `fk_TB_VEICULO_TB_MODELO1_idx` (`TB_MODELO_ID`),
  CONSTRAINT `FKtog78ctbj08lwx3npitfsq3md` FOREIGN KEY (`TB_MODELO_ID`) REFERENCES `tb_modelo` (`TB_MODELO_ID`),
  CONSTRAINT `fk_TB_VEICULO_TB_MODELO1` FOREIGN KEY (`TB_MODELO_ID`) REFERENCES `tb_modelo` (`TB_MODELO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_veiculo`
--

LOCK TABLES `tb_veiculo` WRITE;
/*!40000 ALTER TABLE `tb_veiculo` DISABLE KEYS */;
INSERT INTO `tb_veiculo` VALUES (1,'aaa1111',2,NULL),(2,'bbb2222',1,NULL),(3,'ccc3333',2,NULL);
/*!40000 ALTER TABLE `tb_veiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `view_tempo_medio_geral`
--

DROP TABLE IF EXISTS `view_tempo_medio_geral`;
/*!50001 DROP VIEW IF EXISTS `view_tempo_medio_geral`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `view_tempo_medio_geral` AS SELECT 
 1 AS `MEDIA_TEMPO_GERAL`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_veiculo_modemo_marca`
--

DROP TABLE IF EXISTS `view_veiculo_modemo_marca`;
/*!50001 DROP VIEW IF EXISTS `view_veiculo_modemo_marca`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `view_veiculo_modemo_marca` AS SELECT 
 1 AS `TB_MARCA_DESC`,
 1 AS `QTD`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `preco_por_minuto`
--

/*!50001 DROP VIEW IF EXISTS `preco_por_minuto`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `preco_por_minuto` AS select (`tb_tabela_preco`.`TB_TABELA_PRECO_VALOR` / (`tb_tabela_preco`.`TB_TABELA_PRECO_TEMPO_MAX` - `tb_tabela_preco`.`TB_TABELA_PRECO_TEMPO_MIN`)) AS `PRECO_MINUTO`,`tb_tabela_preco`.`TB_TABELA_PRECO_TEMPO_MAX` AS `TB_TABELA_PRECO_TEMPO_MAX`,`tb_tabela_preco`.`TB_TABELA_PRECO_TEMPO_MIN` AS `TB_TABELA_PRECO_TEMPO_MIN` from `tb_tabela_preco` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_tempo_medio_geral`
--

/*!50001 DROP VIEW IF EXISTS `view_tempo_medio_geral`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_tempo_medio_geral` AS select avg(timestampdiff(MINUTE,`tb_estadia`.`TB_ESTADIA_INICIO`,`tb_estadia`.`TB_ESTADIA_TERMINO`)) AS `MEDIA_TEMPO_GERAL` from `tb_estadia` where (`tb_estadia`.`TB_ESTADIA_TERMINO` is not null) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_veiculo_modemo_marca`
--

/*!50001 DROP VIEW IF EXISTS `view_veiculo_modemo_marca`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_veiculo_modemo_marca` AS select `tb_marca`.`TB_MARCA_DESC` AS `TB_MARCA_DESC`,count(`tb_marca`.`TB_MARCA_DESC`) AS `QTD` from (((`tb_estadia` join `tb_veiculo` on((`tb_estadia`.`TB_VEICULO_ID` = `tb_veiculo`.`TB_VEICULO_ID`))) join `tb_modelo` on((`tb_veiculo`.`TB_MODELO_ID` = `tb_modelo`.`TB_MODELO_ID`))) join `tb_marca` on((`tb_modelo`.`TB_MARCA_ID` = `tb_marca`.`TB_MARCA_ID`))) group by `tb_marca`.`TB_MARCA_DESC` */;
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

-- Dump completed on 2019-03-20 15:47:24
