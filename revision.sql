-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: weuller-lad    Database: agendamento_desktop
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
-- Table structure for table `cobranca`
--

DROP TABLE IF EXISTS `cobranca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cobranca` (
  `id_cobranca` int(11) NOT NULL AUTO_INCREMENT,
  `numero_chamado` int(11) NOT NULL,
  `valor` double NOT NULL,
  PRIMARY KEY (`id_cobranca`),
  KEY `visita_cobranca_idx` (`numero_chamado`),
  CONSTRAINT `Visita_Tecnica` FOREIGN KEY (`numero_chamado`) REFERENCES `visita_tecnica` (`numero_chamado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cobranca`
--

LOCK TABLES `cobranca` WRITE;
/*!40000 ALTER TABLE `cobranca` DISABLE KEYS */;
/*!40000 ALTER TABLE `cobranca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(14) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `sobrenome` varchar(150) DEFAULT NULL,
  `mail` varchar(45) NOT NULL,
  `senha` varchar(200) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `mail_UNIQUE` (`mail`),
  UNIQUE KEY `cpf_UNIQUE` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (6,'063.503.461-14','João','Victor','joaovictorvr6@gmail.com','0FCA75EF54D02A68D853F40EB867B50E9554671821943289535AF293FDCBEEB6'),(7,'043.490.571-29','Weuller','Brenneguer Brito Santana','wbrenneguer07@gmail.com','463CA5194C4FEA7AAAADE57711F7AD4DBEC5F04243F4FD40A5379D9441411968'),(8,'02181434190','Daniel','Cordeiro','daniel.cordeiro@ladsistemas.com.br','0EA56165849CAEF0FFA3E315BED19C17A8D0046278BBDABA91E7916E2B559F23');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visita_tecnica`
--

DROP TABLE IF EXISTS `visita_tecnica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `visita_tecnica` (
  `id_visita_tecnica` int(11) NOT NULL AUTO_INCREMENT,
  `numero_chamado` int(11) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `data_inicio` date NOT NULL,
  `data_fim` date DEFAULT NULL,
  `empresa` varchar(70) NOT NULL,
  `valor` double DEFAULT NULL,
  `tecnico` varchar(100) NOT NULL,
  `is_lad` tinyint(1) NOT NULL,
  `situacao` varchar(45) DEFAULT NULL,
  `obs` varchar(180) DEFAULT NULL,
  PRIMARY KEY (`id_visita_tecnica`,`numero_chamado`),
  UNIQUE KEY `numero_chamado_UNIQUE` (`numero_chamado`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visita_tecnica`
--

LOCK TABLES `visita_tecnica` WRITE;
/*!40000 ALTER TABLE `visita_tecnica` DISABLE KEYS */;
INSERT INTO `visita_tecnica` VALUES (1,7597,'Visita Técnica','2019-01-09','2019-01-09','Tabu Cervejas',400,'Raphael',1,'Concluida','Duas vezes de R$ 200,00'),(2,7649,'Visita Técnica','2019-01-14','2019-01-14','Agostinho Restaurante',NULL,'Raphael',1,'Concluida',NULL),(3,7637,'Visita Técnica','2019-01-08','2019-01-08','Pimentinha Vivaz',NULL,'Raphael',0,'Concluida',NULL),(4,7577,'Implantação','2019-01-03','2019-01-04','Mimy Açai',NULL,'João',0,'Resolvida',NULL),(5,7340,'Implantação','2019-01-03','2019-01-04','Atelie Do Grão',NULL,'Raphael',0,'Resolvida',NULL),(6,7589,'Implantação','2019-01-07','2019-01-08','Pimentinha Praça',NULL,'Raphael',0,'Resolvida',NULL),(7,7608,'Implantação','2019-01-10','2019-01-11','Nosso Buteco',NULL,'Ary',0,'Resolvida',NULL),(8,7632,'Implantação','2019-01-21','2019-01-22','Vila dos Pães',NULL,'Lucas Maranhão',0,'adiada',NULL),(9,7628,'Implantação','2019-01-17','2019-01-19','Burger House',NULL,'Ary',0,'adiada',NULL),(10,7630,'Implantação','2019-01-21','2019-01-22','Quatro Xicaras Cafe',NULL,'Lucas Maranhão',0,'adiada',NULL),(11,7634,'Visita Técnica','2019-01-11','2019-01-11','Galpão Pasteis',NULL,'Raphael',0,'Concluida',NULL),(12,7641,'Visita Técnica','2019-01-09','2019-01-09','Toca do Dino',NULL,'Lucas Maranhão',1,'Concluida',NULL),(13,7598,'Visita Técnica','2019-01-07','2019-01-07','Sand. Ponto do Sabor',NULL,'Lucas Maranhão',1,'Concluida',NULL),(14,7594,'Visita Técnica','2019-01-07','2019-01-07','Opera Café',NULL,'Lucas Maranhão',1,'Concluida',NULL),(15,7575,'Visita Técnica','2019-01-07','2019-01-07','Kabutia',NULL,'Ary',0,'Concluida',NULL),(16,7535,'Visita Técnica','2019-01-08','2019-01-08','New Trend Burguer',NULL,'Ary',0,'Concluida',NULL),(17,7673,'Visita Técnica','2019-01-15','2019-01-15','Bistro Chica Doida',NULL,'Ary',1,'Concluida',NULL),(18,7662,'Visita Técnica','2019-01-14','2019-01-14','PokeHonolulu',NULL,'Ary',1,'Concluida',NULL),(20,7655,'Visita Técnica','2019-01-23','2019-01-23','Pimentinha Praça',NULL,'Ary',0,'Concluida',NULL),(21,7633,'Visita Técnica','2019-01-22','2019-01-22','Biscoito Pereira',NULL,'Raphael',1,'Concluida',NULL),(22,7681,'Visita Técnica','2019-01-18','2019-01-18','Underdog Jd Goias',NULL,'Lucas Maranhão',0,'Concluida',NULL),(23,7677,'Visita Técnica','2019-01-23','2019-01-23','Tacho de Cobre',NULL,'Raphael',1,'Concluida',NULL),(24,7685,'Visita Técnica','2019-01-18','2019-01-18','Burger SA',NULL,'Lucas Maranhão',1,'Concluida',NULL),(25,7636,'Visita Técnica','2019-01-24','2019-01-24','Mimy Açai',NULL,'Ary',1,'Concluida',NULL),(26,7687,'Visita Técnica','2019-01-18','2019-01-18','Costelaria Fogo na Brasa',NULL,'Lucas Maranhão',0,'Concluida',NULL),(27,7663,'Visita Técnica','2019-01-24','2019-01-24','Catenas',1.56,'Weuller',1,'Pendente','não Tem'),(28,7719,'Visita Técnica','2019-01-21','2019-01-21','Ponto do Sabor',NULL,'Raphael',0,'Concluida',NULL),(29,7684,'Visita Técnica','2019-01-22','2019-01-22','Ponto do Sabor',NULL,'Raphael',1,'Concluida',NULL),(30,7725,'Visita Técnica','2019-01-23','2019-01-23','Luiz Café Conceito',NULL,'Ary',1,'Concluida',NULL),(31,7717,'Visita Técnica','2019-01-21','2019-01-21','Calango Delivery',NULL,'Lucas Maranhão',1,'Concluida',NULL),(32,7730,'Visita Técnica','2019-01-24','2019-01-24','Calango Delivery',0,'Ary',1,'Concluida','Não Tem'),(33,7743,'Visita Técnica','2019-01-25','2019-01-25','Cafe SA',NULL,'Lucas Maranhão',0,'Concluida',NULL),(34,7739,'Visita Técnica','2019-01-25','2019-01-25','Zer062ois (Tremendão)',NULL,'Lucas Maranhão',1,'Concluida',NULL),(35,7654,'Visita Técnica','2019-01-23','2019-01-23','Pimentina Sanduicheria - Vivaz',NULL,'Ary',1,'Concluida',NULL),(36,7765,'Visita Técnica','2019-01-28','2019-01-28','Oktos Burguer',NULL,'Thiago',1,'Concluida',NULL),(37,7640,'Implantação','2019-01-15','2019-01-18','Estação Oktos',NULL,'Lucas Maranhao',0,'Finalizada',NULL),(38,7345,'Implantação','2019-01-16','2019-01-18','Tribo Restaurante',NULL,'Raphael',0,'finalizada',NULL),(39,7689,'Implantação','2019-01-21','2019-01-22','Paidim Urias',NULL,'Ary',0,'finalizada',NULL),(40,7602,'Implantação','2019-01-22','2019-01-24','Simbora 4 restaurante',NULL,'Lucas Maranhao',0,'finalizada',NULL),(41,7721,'Implantação','2019-01-23','2019-01-24','Bacanas Quiosque',NULL,'Raphael',0,'Finalizada',NULL),(42,7757,'Visita Técnica','2019-02-01','2019-02-01','Maria Cafe',NULL,'Ary',0,'Confirmada',NULL),(43,7749,'Visita Técnica','2019-01-28','2019-01-28','Paidim',NULL,'Ary',0,'Confirmada',NULL),(44,7668,'Implantação','2019-01-30','2019-01-31','Panela Velha',NULL,'Lucas Maranhão',0,'Finalizada',NULL),(45,7771,'Implantação','2019-01-30','2019-01-21','Cheff Burger',NULL,'Ary',0,'Finalizada',NULL),(46,7775,'Implantação','2019-01-31','2019-02-01','tioBák Eldorado',NULL,'Raphael',0,'Finalizada',NULL),(47,7789,'Visita Técnica','2019-02-05','2019-02-05','Zer062ois - São Judas',NULL,'Lucas Maranhão',1,'Confirmada',NULL),(48,7755,'Visita Técnica','2019-01-28','2019-01-28','Rio Bahia',NULL,'Raphael',0,'Concluida',NULL),(49,7772,'Implantação','2019-01-31','2019-01-31','Panela Santa Cruzeiro',NULL,'Ary',0,'Finalizada',NULL),(50,9999,'Visita Técnica','1997-07-21','1997-07-21','Catenas',100,'Weuller',0,'aberto','sem controle de estoque');
/*!40000 ALTER TABLE `visita_tecnica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'agendamento_desktop'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-06  0:53:35
