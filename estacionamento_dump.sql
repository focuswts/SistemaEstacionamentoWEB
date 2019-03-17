-- MySQL dump 10.16  Distrib 10.1.33-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: estacionamento
-- ------------------------------------------------------
-- Server version	10.1.33-MariaDB-1~stretch

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
-- Temporary table structure for view `PRECO_POR_MINUTO`
--

DROP TABLE IF EXISTS `PRECO_POR_MINUTO`;
/*!50001 DROP VIEW IF EXISTS `PRECO_POR_MINUTO`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `PRECO_POR_MINUTO` (
  `PRECO_MINUTO` tinyint NOT NULL,
  `TB_TABELA_PRECO_TEMPO_MAX` tinyint NOT NULL,
  `TB_TABELA_PRECO_TEMPO_MIN` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `TB_CIDADE`
--

DROP TABLE IF EXISTS `TB_CIDADE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_CIDADE` (
  `TB_CIDADE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TB_CIDADE_NOME` varchar(50) NOT NULL,
  `TB_CIDADE_STATUS` enum('ativo','inativo') NOT NULL,
  `TB_ESTADO_ID` int(11) NOT NULL,
  PRIMARY KEY (`TB_CIDADE_ID`),
  KEY `fk_TB_CIDADE_TB_ESTADO_idx` (`TB_ESTADO_ID`),
  CONSTRAINT `fk_TB_CIDADE_TB_ESTADO` FOREIGN KEY (`TB_ESTADO_ID`) REFERENCES `TB_ESTADO` (`TB_ESTADO_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_CIDADE`
--

LOCK TABLES `TB_CIDADE` WRITE;
/*!40000 ALTER TABLE `TB_CIDADE` DISABLE KEYS */;
INSERT INTO `TB_CIDADE` VALUES (1,'Londrina','ativo',1),(2,'Cambé','ativo',1);
/*!40000 ALTER TABLE `TB_CIDADE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TB_ENDERECO`
--

DROP TABLE IF EXISTS `TB_ENDERECO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_ENDERECO` (
  `TB_ENDERECO_ID` int(11) NOT NULL,
  `TB_ENDERECO_CEP` varchar(8) NOT NULL,
  `TB_ENDERECO_LOGRADOURO` varchar(100) NOT NULL,
  `TB_ENDERECO_NUM` varchar(10) NOT NULL,
  `TB_ENDERECO_COMP` varchar(100) DEFAULT NULL,
  `TB_ENDERECO_STATUS` enum('ativo','inativo') NOT NULL,
  `TB_CIDADE_ID` int(11) NOT NULL,
  `TB_MENSALISTA_ID` int(11) NOT NULL,
  PRIMARY KEY (`TB_ENDERECO_ID`),
  KEY `fk_TB_ENDERECO_TB_CIDADE1_idx` (`TB_CIDADE_ID`),
  KEY `fk_TB_ENDERECO_TB_MENSALISTA1_idx` (`TB_MENSALISTA_ID`),
  CONSTRAINT `fk_TB_ENDERECO_TB_CIDADE1` FOREIGN KEY (`TB_CIDADE_ID`) REFERENCES `TB_CIDADE` (`TB_CIDADE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_ENDERECO_TB_MENSALISTA1` FOREIGN KEY (`TB_MENSALISTA_ID`) REFERENCES `TB_MENSALISTA` (`TB_MENSALISTA_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_ENDERECO`
--

LOCK TABLES `TB_ENDERECO` WRITE;
/*!40000 ALTER TABLE `TB_ENDERECO` DISABLE KEYS */;
INSERT INTO `TB_ENDERECO` VALUES (1,'86055784','Rua João Candido','103','bloco b apt 102','ativo',1,1),(2,'86055784','Rua João Candido','103','bloco b apt 102','ativo',2,4),(3,'86055746','Rua Pernanbuco','574',NULL,'ativo',1,4),(4,'86055447','Rua Hugo Cabral','1697','sala 5','ativo',1,4);
/*!40000 ALTER TABLE `TB_ENDERECO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TB_ESTADIA`
--

DROP TABLE IF EXISTS `TB_ESTADIA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_ESTADIA` (
  `TB_ESTADIA_ID` int(11) NOT NULL,
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
  CONSTRAINT `fk_TB_ESTADIA_TB_TABELA_PRECO1` FOREIGN KEY (`TB_TABELA_PRECO_ID`) REFERENCES `TB_TABELA_PRECO` (`TB_TABELA_PRECO_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_ESTADIA_TB_VAGA1` FOREIGN KEY (`TB_VAGA_ID`) REFERENCES `TB_VAGA` (`TB_VAGA_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_ESTADIA_TB_VEICULO1` FOREIGN KEY (`TB_VEICULO_ID`) REFERENCES `TB_VEICULO` (`TB_VEICULO_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_ESTADIA`
--

LOCK TABLES `TB_ESTADIA` WRITE;
/*!40000 ALTER TABLE `TB_ESTADIA` DISABLE KEYS */;
INSERT INTO `TB_ESTADIA` VALUES (1,'2019-02-27 21:07:03','2019-02-28 09:15:43','aaa1111','ativo',2,1,1),(2,'2019-02-28 14:54:21','2019-02-28 15:11:47','asd1234','ativo',1,1,1),(3,'2019-02-28 16:02:39',NULL,'aaa1111','ativo',2,1,1),(4,'2019-02-28 16:02:39','2019-02-28 18:10:10','aaa1111','ativo',2,1,1),(5,'2019-02-28 16:02:39','2019-02-28 18:10:55','aaa1111','ativo',2,1,NULL),(6,'2019-01-04 15:02:39',NULL,'aaa1111','ativo',2,1,NULL),(7,'2019-01-04 15:02:39','2019-03-04 16:19:53','aaa1221','ativo',2,1,NULL),(8,'2019-02-28 11:02:39','2019-02-28 12:37:39','aaa1111','ativo',2,1,NULL),(9,'2019-02-28 11:02:39','2019-02-28 12:37:39','bbb2222','ativo',2,1,NULL),(10,'2019-02-28 11:02:39','2019-02-28 12:37:39','bbb2222','ativo',2,1,NULL);
/*!40000 ALTER TABLE `TB_ESTADIA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TB_ESTADO`
--

DROP TABLE IF EXISTS `TB_ESTADO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_ESTADO` (
  `TB_ESTADO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TB_ESTADO_NOME` varchar(50) NOT NULL,
  `TB_ESTADO_SIGLA` varchar(2) NOT NULL,
  `TB_ESTADO_STATUS` enum('ativo','inativo') NOT NULL,
  PRIMARY KEY (`TB_ESTADO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_ESTADO`
--

LOCK TABLES `TB_ESTADO` WRITE;
/*!40000 ALTER TABLE `TB_ESTADO` DISABLE KEYS */;
INSERT INTO `TB_ESTADO` VALUES (1,'Paraná','PR','ativo');
/*!40000 ALTER TABLE `TB_ESTADO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TB_MARCA`
--

DROP TABLE IF EXISTS `TB_MARCA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_MARCA` (
  `TB_MARCA_ID` int(11) NOT NULL,
  `TB_MARCA_DESC` varchar(50) NOT NULL,
  `TB_MARCA_STATUS` enum('ativo','inativo') NOT NULL,
  PRIMARY KEY (`TB_MARCA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_MARCA`
--

LOCK TABLES `TB_MARCA` WRITE;
/*!40000 ALTER TABLE `TB_MARCA` DISABLE KEYS */;
INSERT INTO `TB_MARCA` VALUES (1,'Fiat','ativo'),(2,'Toyota','ativo'),(3,'Nissan','ativo');
/*!40000 ALTER TABLE `TB_MARCA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TB_MENSALISTA`
--

DROP TABLE IF EXISTS `TB_MENSALISTA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_MENSALISTA` (
  `TB_MENSALISTA_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TB_MENSALISTA_NOME` varchar(100) NOT NULL,
  `TB_MENSALISTA_CPF` varchar(11) NOT NULL,
  `TB_MENSALISTA_STATUS` enum('ativo','inativo') NOT NULL,
  PRIMARY KEY (`TB_MENSALISTA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_MENSALISTA`
--

LOCK TABLES `TB_MENSALISTA` WRITE;
/*!40000 ALTER TABLE `TB_MENSALISTA` DISABLE KEYS */;
INSERT INTO `TB_MENSALISTA` VALUES (1,'João','11122233344','ativo'),(2,'Maria','22211144433','ativo'),(3,'Pedro','44433322211','inativo'),(4,'Ana','33322233311','ativo');
/*!40000 ALTER TABLE `TB_MENSALISTA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TB_MENSALISTA_VEICULO`
--

DROP TABLE IF EXISTS `TB_MENSALISTA_VEICULO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_MENSALISTA_VEICULO` (
  `TB_MENSALISTA_VEICULO_ID` int(11) NOT NULL,
  `TB_MENSALISTA_VEICULO_PROPRIETARIO` tinyint(1) NOT NULL,
  `TB_MENSALISTA_ID` int(11) NOT NULL,
  `TB_VEICULO_ID` int(11) NOT NULL,
  `TB_MENSALISTA_VEICULO_STATUS` enum('ativo','inativo') NOT NULL,
  PRIMARY KEY (`TB_MENSALISTA_VEICULO_ID`),
  KEY `fk_TB_MENSALISTA_VEICULO_TB_MENSALISTA1_idx` (`TB_MENSALISTA_ID`),
  KEY `fk_TB_MENSALISTA_VEICULO_TB_VEICULO1_idx` (`TB_VEICULO_ID`),
  CONSTRAINT `fk_TB_MENSALISTA_VEICULO_TB_MENSALISTA1` FOREIGN KEY (`TB_MENSALISTA_ID`) REFERENCES `TB_MENSALISTA` (`TB_MENSALISTA_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_MENSALISTA_VEICULO_TB_VEICULO1` FOREIGN KEY (`TB_VEICULO_ID`) REFERENCES `TB_VEICULO` (`TB_VEICULO_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_MENSALISTA_VEICULO`
--

LOCK TABLES `TB_MENSALISTA_VEICULO` WRITE;
/*!40000 ALTER TABLE `TB_MENSALISTA_VEICULO` DISABLE KEYS */;
INSERT INTO `TB_MENSALISTA_VEICULO` VALUES (1,1,1,1,'ativo'),(2,0,2,1,'ativo'),(3,1,4,2,'ativo'),(4,1,4,3,'ativo');
/*!40000 ALTER TABLE `TB_MENSALISTA_VEICULO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TB_MODELO`
--

DROP TABLE IF EXISTS `TB_MODELO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_MODELO` (
  `TB_MODELO_ID` int(11) NOT NULL,
  `TB_MODELO_DESC` varchar(50) NOT NULL,
  `TB_MODELO_STATUS` enum('ativo','inativo') NOT NULL,
  `TB_TIPO_VEICULO_ID` int(11) NOT NULL,
  `TB_MARCA_ID` int(11) NOT NULL,
  PRIMARY KEY (`TB_MODELO_ID`),
  KEY `fk_TB_MODELO_TB_TIPO_VEICULO1_idx` (`TB_TIPO_VEICULO_ID`),
  KEY `fk_TB_MODELO_TB_MARCA1_idx` (`TB_MARCA_ID`),
  CONSTRAINT `fk_TB_MODELO_TB_MARCA1` FOREIGN KEY (`TB_MARCA_ID`) REFERENCES `TB_MARCA` (`TB_MARCA_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_MODELO_TB_TIPO_VEICULO1` FOREIGN KEY (`TB_TIPO_VEICULO_ID`) REFERENCES `TB_TIPO_VEICULO` (`TB_TIPO_VEICULO_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_MODELO`
--

LOCK TABLES `TB_MODELO` WRITE;
/*!40000 ALTER TABLE `TB_MODELO` DISABLE KEYS */;
INSERT INTO `TB_MODELO` VALUES (1,'Toro','ativo',1,1),(2,'Corolla','ativo',3,2),(3,'HRV','ativo',2,1);
/*!40000 ALTER TABLE `TB_MODELO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TB_TABELA_PRECO`
--

DROP TABLE IF EXISTS `TB_TABELA_PRECO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_TABELA_PRECO` (
  `TB_TABELA_PRECO_ID` int(11) NOT NULL,
  `TB_TABELA_PRECO_VALOR` double NOT NULL,
  `TB_TABELA_PRECO_STATUS` enum('ativo','inativo') NOT NULL,
  `TB_TABELA_PRECO_TEMPO_MIN` int(11) NOT NULL,
  `TB_TABELA_PRECO_TEMPO_MAX` int(11) NOT NULL,
  PRIMARY KEY (`TB_TABELA_PRECO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_TABELA_PRECO`
--

LOCK TABLES `TB_TABELA_PRECO` WRITE;
/*!40000 ALTER TABLE `TB_TABELA_PRECO` DISABLE KEYS */;
INSERT INTO `TB_TABELA_PRECO` VALUES (1,1.5,'ativo',0,30),(2,2,'inativo',30,60),(3,5,'ativo',60,120),(4,3,'ativo',30,60),(5,10,'ativo',0,720);
/*!40000 ALTER TABLE `TB_TABELA_PRECO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TB_TELEFONE`
--

DROP TABLE IF EXISTS `TB_TELEFONE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_TELEFONE` (
  `TB_TELEFONE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TB_TELEFONE_NUM` varchar(11) NOT NULL,
  `TB_TELEFONE_TIPO_TEL` enum('residencial','comercial','celular') NOT NULL,
  `TB_TELEFONE_STATUS` enum('ativo','inativo') NOT NULL,
  `TB_MENSALISTA_ID` int(11) NOT NULL,
  PRIMARY KEY (`TB_TELEFONE_ID`),
  KEY `fk_TB_TELEFONE_TB_MENSALISTA1_idx` (`TB_MENSALISTA_ID`),
  CONSTRAINT `fk_TB_TELEFONE_TB_MENSALISTA1` FOREIGN KEY (`TB_MENSALISTA_ID`) REFERENCES `TB_MENSALISTA` (`TB_MENSALISTA_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_TELEFONE`
--

LOCK TABLES `TB_TELEFONE` WRITE;
/*!40000 ALTER TABLE `TB_TELEFONE` DISABLE KEYS */;
INSERT INTO `TB_TELEFONE` VALUES (1,'33448877','residencial','ativo',1),(2,'33425567','residencial','inativo',1),(3,'33774512','comercial','ativo',4);
/*!40000 ALTER TABLE `TB_TELEFONE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TB_TIPO_VEICULO`
--

DROP TABLE IF EXISTS `TB_TIPO_VEICULO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_TIPO_VEICULO` (
  `TB_TIPO_VEICULO_ID` int(11) NOT NULL,
  `TB_TIPO_VEICULO_DESC` varchar(50) NOT NULL,
  `TB_TIPO_VEICULO_STATUS` enum('ativo','inativo') NOT NULL,
  PRIMARY KEY (`TB_TIPO_VEICULO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_TIPO_VEICULO`
--

LOCK TABLES `TB_TIPO_VEICULO` WRITE;
/*!40000 ALTER TABLE `TB_TIPO_VEICULO` DISABLE KEYS */;
INSERT INTO `TB_TIPO_VEICULO` VALUES (1,'Utilitário','ativo'),(2,'SUV','ativo'),(3,'Passeio','ativo');
/*!40000 ALTER TABLE `TB_TIPO_VEICULO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TB_USUARIO`
--

DROP TABLE IF EXISTS `TB_USUARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_USUARIO` (
  `TB_USUARIO_ID` int(11) NOT NULL,
  `TB_USUARIO_NOME` varchar(10) NOT NULL,
  `TB_USUARIO_SENHA` varchar(10) NOT NULL,
  `TB_USUARIO_STATUS` enum('ativo','inativo') NOT NULL,
  PRIMARY KEY (`TB_USUARIO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_USUARIO`
--

LOCK TABLES `TB_USUARIO` WRITE;
/*!40000 ALTER TABLE `TB_USUARIO` DISABLE KEYS */;
INSERT INTO `TB_USUARIO` VALUES (1,'admin','admin123','ativo');
/*!40000 ALTER TABLE `TB_USUARIO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TB_VAGA`
--

DROP TABLE IF EXISTS `TB_VAGA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_VAGA` (
  `TB_VAGA_ID` int(11) NOT NULL,
  `TB_VAGA_DESC` varchar(45) NOT NULL,
  `TB_VAGA_STATUS` enum('ativo','inativo') NOT NULL,
  PRIMARY KEY (`TB_VAGA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_VAGA`
--

LOCK TABLES `TB_VAGA` WRITE;
/*!40000 ALTER TABLE `TB_VAGA` DISABLE KEYS */;
INSERT INTO `TB_VAGA` VALUES (1,'Vaga 1','ativo'),(2,'Vaga 2','ativo'),(3,'Vaga 3','ativo'),(4,'Vaga 4','ativo'),(5,'Vaga 5','ativo');
/*!40000 ALTER TABLE `TB_VAGA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TB_VEICULO`
--

DROP TABLE IF EXISTS `TB_VEICULO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_VEICULO` (
  `TB_VEICULO_ID` int(11) NOT NULL,
  `TB_VEICULO_PLACA` varchar(50) NOT NULL,
  `TB_VEICULO_COR` enum('preto','branco','prata','vermelho') NOT NULL,
  `TB_VEICULO_STATUS` enum('ativo','inativo') NOT NULL,
  `TB_MODELO_ID` int(11) NOT NULL,
  PRIMARY KEY (`TB_VEICULO_ID`),
  KEY `fk_TB_VEICULO_TB_MODELO1_idx` (`TB_MODELO_ID`),
  CONSTRAINT `fk_TB_VEICULO_TB_MODELO1` FOREIGN KEY (`TB_MODELO_ID`) REFERENCES `TB_MODELO` (`TB_MODELO_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_VEICULO`
--

LOCK TABLES `TB_VEICULO` WRITE;
/*!40000 ALTER TABLE `TB_VEICULO` DISABLE KEYS */;
INSERT INTO `TB_VEICULO` VALUES (1,'aaa1111','preto','ativo',2),(2,'bbb2222','preto','ativo',1),(3,'ccc3333','preto','ativo',2);
/*!40000 ALTER TABLE `TB_VEICULO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `VIEW_TEMPO_MEDIO_GERAL`
--

DROP TABLE IF EXISTS `VIEW_TEMPO_MEDIO_GERAL`;
/*!50001 DROP VIEW IF EXISTS `VIEW_TEMPO_MEDIO_GERAL`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `VIEW_TEMPO_MEDIO_GERAL` (
  `MEDIA_TEMPO_GERAL` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `PRECO_POR_MINUTO`
--

/*!50001 DROP TABLE IF EXISTS `PRECO_POR_MINUTO`*/;
/*!50001 DROP VIEW IF EXISTS `PRECO_POR_MINUTO`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `PRECO_POR_MINUTO` AS select (`TB_TABELA_PRECO`.`TB_TABELA_PRECO_VALOR` / (`TB_TABELA_PRECO`.`TB_TABELA_PRECO_TEMPO_MAX` - `TB_TABELA_PRECO`.`TB_TABELA_PRECO_TEMPO_MIN`)) AS `PRECO_MINUTO`,`TB_TABELA_PRECO`.`TB_TABELA_PRECO_TEMPO_MAX` AS `TB_TABELA_PRECO_TEMPO_MAX`,`TB_TABELA_PRECO`.`TB_TABELA_PRECO_TEMPO_MIN` AS `TB_TABELA_PRECO_TEMPO_MIN` from `TB_TABELA_PRECO` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `VIEW_TEMPO_MEDIO_GERAL`
--

/*!50001 DROP TABLE IF EXISTS `VIEW_TEMPO_MEDIO_GERAL`*/;
/*!50001 DROP VIEW IF EXISTS `VIEW_TEMPO_MEDIO_GERAL`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `VIEW_TEMPO_MEDIO_GERAL` AS select avg(timestampdiff(MINUTE,`TB_ESTADIA`.`TB_ESTADIA_INICIO`,`TB_ESTADIA`.`TB_ESTADIA_TERMINO`)) AS `MEDIA_TEMPO_GERAL` from `TB_ESTADIA` where (`TB_ESTADIA`.`TB_ESTADIA_TERMINO` is not null) */;
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

-- Dump completed on 2019-03-06 12:42:20