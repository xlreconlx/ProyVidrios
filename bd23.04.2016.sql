-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: vidrieria
-- ------------------------------------------------------
-- Server version	5.6.25-log

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
-- Table structure for table `abonodetalle`
--

DROP TABLE IF EXISTS `abonodetalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `abonodetalle` (
  `idabonoDetalle` int(11) NOT NULL AUTO_INCREMENT,
  `idabonos` int(6) NOT NULL,
  `idproductos` int(11) NOT NULL,
  `nombrepro` varchar(45) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precioventa` bigint(15) NOT NULL,
  `total` bigint(15) DEFAULT NULL,
  `preccioTrabajo` int(11) DEFAULT NULL,
  `porcentajeganancia` int(11) DEFAULT NULL,
  `alto` varchar(45) DEFAULT NULL,
  `ancho` varchar(45) DEFAULT NULL,
  `fondo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idabonoDetalle`),
  KEY `fk_abonoDetalle_abonos1_idx` (`idabonos`),
  KEY `fk_abonoDetalle_productos1_idx` (`idproductos`),
  CONSTRAINT `fk_abonoDetalle_abonos1` FOREIGN KEY (`idabonos`) REFERENCES `abonos` (`idabonos`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_abonoDetalle_productos1` FOREIGN KEY (`idproductos`) REFERENCES `productos` (`idproductos`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abonodetalle`
--

LOCK TABLES `abonodetalle` WRITE;
/*!40000 ALTER TABLE `abonodetalle` DISABLE KEYS */;
INSERT INTO `abonodetalle` VALUES (1,1,2,'3 cuerpos 120*150',4,320255,1281020,50000,50,'120','150',NULL),(2,2,2,'3 cuerpos 120*150',1,359415,359415,60000,50,'120','150',NULL),(3,3,1,'2 cuerpos 210*234',1,389081,389081,45000,50,'210','234',NULL),(4,4,1,'2 cuerpos 123*123',1,236713,236713,45000,50,'123','123',NULL),(5,5,7,'vitrina 123*200 fondo: 50',1,787400,787400,50000,50,'123','200','50'),(6,6,5,'Puerta Aluminio T87 con Vidrio 190*110',6,282002,1692012,50000,50,'190','110',''),(7,7,1,'2 cuerpos 190*110',1,297009,297009,50000,50,'190','110',''),(8,8,1,'2 cuerpos 190*234',1,409319,409319,50000,50,'190','234','');
/*!40000 ALTER TABLE `abonodetalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `abonos`
--

DROP TABLE IF EXISTS `abonos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `abonos` (
  `idabonos` int(6) NOT NULL AUTO_INCREMENT,
  `idempleado` int(6) NOT NULL,
  `idcliente` int(6) NOT NULL,
  `precioTotal` bigint(15) DEFAULT NULL,
  `montoabono` bigint(15) DEFAULT NULL,
  `saldofinal` bigint(15) DEFAULT NULL,
  `fecharegistro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idabonos`),
  KEY `fk_abonos_cliente1_idx` (`idcliente`),
  KEY `fk_abonos_empleado1_idx` (`idempleado`),
  CONSTRAINT `fk_abonos_cliente1` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_abonos_empleado1` FOREIGN KEY (`idempleado`) REFERENCES `empleado` (`idempleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abonos`
--

LOCK TABLES `abonos` WRITE;
/*!40000 ALTER TABLE `abonos` DISABLE KEYS */;
INSERT INTO `abonos` VALUES (1,1,2,1281020,145500,1135520,'2016-03-23 02:02:56'),(2,1,2,359415,245000,93315,'2016-03-23 14:03:07'),(3,2,2,389081,34000,355081,'2016-04-12 19:45:51'),(4,1,2,236713,34000,-51887,'2016-04-14 05:00:54'),(5,1,2,787400,400000,387400,'2016-04-18 23:41:18'),(6,1,2,1692012,200000,1492012,'2016-04-28 18:16:40'),(7,1,2,297009,120000,127009,'2016-05-04 19:33:05'),(8,1,2,409319,45000,304319,'2016-05-04 19:35:40');
/*!40000 ALTER TABLE `abonos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `abonosecundario`
--

DROP TABLE IF EXISTS `abonosecundario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `abonosecundario` (
  `idabonosecun` int(11) NOT NULL AUTO_INCREMENT,
  `idabonos` int(6) NOT NULL,
  `idempleado` int(6) NOT NULL,
  `valorAbono` bigint(15) NOT NULL,
  `saldoTotaL` bigint(15) DEFAULT NULL,
  `fechaRegistro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idabonosecun`),
  KEY `idabonos` (`idabonos`),
  KEY `idempleado` (`idempleado`),
  CONSTRAINT `abonosecundario_ibfk_1` FOREIGN KEY (`idabonos`) REFERENCES `abonos` (`idabonos`),
  CONSTRAINT `abonosecundario_ibfk_2` FOREIGN KEY (`idempleado`) REFERENCES `empleado` (`idempleado`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abonosecundario`
--

LOCK TABLES `abonosecundario` WRITE;
/*!40000 ALTER TABLE `abonosecundario` DISABLE KEYS */;
INSERT INTO `abonosecundario` VALUES (1,2,1,3000,NULL,'2016-04-08 03:51:24'),(2,2,1,4000,NULL,'2016-04-09 00:26:22'),(3,2,1,4300,NULL,'2016-04-09 03:55:07'),(4,2,1,5000,93115,'2016-04-09 16:00:54'),(5,2,2,2400,93315,'2016-04-12 04:05:26'),(6,2,2,2400,90915,'2016-04-12 04:07:13'),(7,4,1,2300,198113,'2016-04-14 18:13:08'),(8,4,1,2300,195813,'2016-04-14 18:15:18'),(9,4,2,250000,-301887,'2016-04-14 23:37:01'),(10,7,1,50000,77009,'2016-05-04 19:34:09'),(11,8,1,60000,244319,'2016-05-04 19:36:36');
/*!40000 ALTER TABLE `abonosecundario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ciudad`
--

DROP TABLE IF EXISTS `ciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ciudad` (
  `idciudad` int(4) NOT NULL,
  `nombreCiu` varchar(45) NOT NULL,
  `iddepartamento` int(4) NOT NULL,
  PRIMARY KEY (`idciudad`),
  KEY `fk_ciudad_departamento1_idx` (`iddepartamento`),
  CONSTRAINT `fk_ciudad_departamento1` FOREIGN KEY (`iddepartamento`) REFERENCES `departamento` (`iddepartamento`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudad`
--

LOCK TABLES `ciudad` WRITE;
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
INSERT INTO `ciudad` VALUES (1,'Medellin',1),(2,'Cali',2),(3,'palmira',2);
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `idcliente` int(6) NOT NULL AUTO_INCREMENT,
  `idtipodocumento` int(5) NOT NULL,
  `idciudad` int(4) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(45) DEFAULT NULL,
  `numeroDocumentoC` varchar(15) NOT NULL,
  `correoElectronico` varchar(45) DEFAULT NULL,
  `telefono` int(13) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcliente`),
  KEY `fk_cliente_tipodocumento1_idx` (`idtipodocumento`),
  KEY `fk_cliente_ciudad1_idx` (`idciudad`),
  CONSTRAINT `fk_cliente_ciudad1` FOREIGN KEY (`idciudad`) REFERENCES `ciudad` (`idciudad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_tipodocumento1` FOREIGN KEY (`idtipodocumento`) REFERENCES `tipodocumento` (`idtipodocumento`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (2,1,1,'wilson luisito','gamba','16245356','wilson@hotmail.com',5363627,'calle 57a 34-21');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cotizacion`
--

DROP TABLE IF EXISTS `cotizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cotizacion` (
  `idcotizacion` int(8) NOT NULL AUTO_INCREMENT,
  `idempleado` int(6) NOT NULL,
  `idcliente` int(6) NOT NULL,
  `subtotal` bigint(15) DEFAULT NULL,
  `iva` bigint(15) DEFAULT NULL,
  `preciototal` bigint(15) DEFAULT NULL,
  `fechacotizacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idcotizacion`),
  KEY `fk_compras_empleado1_idx` (`idempleado`),
  KEY `fk_cotizacion_cliente1_idx` (`idcliente`),
  CONSTRAINT `fk_compras_empleado1` FOREIGN KEY (`idempleado`) REFERENCES `empleado` (`idempleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cotizacion_cliente1` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cotizacion`
--

LOCK TABLES `cotizacion` WRITE;
/*!40000 ALTER TABLE `cotizacion` DISABLE KEYS */;
INSERT INTO `cotizacion` VALUES (1,1,2,1886402,359314,2245716,'2016-03-22 20:38:49'),(2,1,2,1550500,295333,1845833,'2016-03-22 21:13:42'),(3,1,2,1550500,295333,1845833,'2016-03-22 21:16:49'),(4,2,2,1222170,232794,1454964,'2016-03-23 14:00:51'),(5,1,2,1001270,190718,1191988,'2016-03-26 17:53:27'),(6,1,2,713297,135865,849162,'2016-04-14 02:51:19'),(7,1,2,713297,135865,849162,'2016-04-14 02:55:16'),(8,1,2,713297,135865,849162,'2016-04-14 02:56:46'),(9,1,2,297250,56618,353868,'2016-05-03 04:13:18'),(10,1,2,297250,56618,353868,'2016-05-03 04:25:12'),(11,1,2,1069916,203793,1273709,'2016-05-05 15:06:30'),(12,1,2,1069916,203793,1273709,'2016-05-05 15:11:03'),(13,1,2,1069916,203793,1273709,'2016-05-05 15:13:05'),(14,1,2,1069916,203793,1273709,'2016-05-05 15:13:38'),(15,1,2,1069916,203793,1273709,'2016-05-05 15:17:48');
/*!40000 ALTER TABLE `cotizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cotizaciondetalle`
--

DROP TABLE IF EXISTS `cotizaciondetalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cotizaciondetalle` (
  `idcotizacionDetalle` int(11) NOT NULL AUTO_INCREMENT,
  `idcotizacion` int(8) NOT NULL,
  `idproductos` int(11) NOT NULL,
  `nombrepro` varchar(45) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precioventa` bigint(15) NOT NULL,
  `total` bigint(15) DEFAULT NULL,
  `preciotrabajo` int(11) DEFAULT NULL,
  `porcentajeganancia` int(11) DEFAULT NULL,
  `alto` varchar(45) DEFAULT NULL,
  `ancho` varchar(45) DEFAULT NULL,
  `fondo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcotizacionDetalle`),
  KEY `fk_cotizacionDetalle_productos1_idx` (`idproductos`),
  KEY `fk_cotizacionDetalle_cotizacion1_idx` (`idcotizacion`),
  CONSTRAINT `fk_cotizacionDetalle_cotizacion1` FOREIGN KEY (`idcotizacion`) REFERENCES `cotizacion` (`idcotizacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cotizacionDetalle_productos1` FOREIGN KEY (`idproductos`) REFERENCES `productos` (`idproductos`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cotizaciondetalle`
--

LOCK TABLES `cotizaciondetalle` WRITE;
/*!40000 ALTER TABLE `cotizaciondetalle` DISABLE KEYS */;
INSERT INTO `cotizaciondetalle` VALUES (3,1,1,'2 cuerpos 440*340',3,748572,2245716,50000,50,'440','340',NULL),(4,2,2,'3 cuerpos 134*239',3,407845,1223535,45000,50,'134','239',NULL),(5,2,2,'3 cuerpos 134*110',2,311149,622298,45000,50,'134','110',NULL),(6,3,2,'3 cuerpos 134*239',3,407845,1223535,45000,50,'134','239',NULL),(7,3,2,'3 cuerpos 134*110',2,311149,622298,45000,50,'134','110',NULL),(8,4,1,'2 cuerpos 440*240',2,727482,1454964,56000,50,'440','240',NULL),(9,5,2,'3 cuerpos 240*340',2,595994,1191988,50000,50,'240','340',NULL),(10,6,2,'3 cuerpos 234*123',2,424581,849162,45000,50,'234','123',NULL),(11,7,2,'3 cuerpos 234*123',2,424581,849162,45000,50,'234','123',NULL),(12,8,2,'3 cuerpos 234*123',2,424581,849162,45000,50,'234','123',NULL),(13,9,7,'vitrina Eliot 110*120 fondo: 040',1,353868,353868,80000,50,'110','120','040'),(14,10,7,'vitrina Eliot 110*120 fondo: 040',1,353868,353868,80000,50,'110','120','040'),(15,11,5,'Puerta Aluminio T87 con Vidrio 220*110',1,467690,467690,50000,50,'220','110',''),(16,11,1,'2 cuerpos 220*110',1,325112,325112,50000,50,'220','110',''),(17,11,7,'vitrina Eliot 120*110 fondo: 040',1,480907,480907,50000,50,'120','110','040'),(18,12,5,'Puerta Aluminio T87 con Vidrio 220*110',1,467690,467690,50000,50,'220','110',''),(19,12,1,'2 cuerpos 220*110',1,325112,325112,50000,50,'220','110',''),(20,12,7,'vitrina Eliot 120*110 fondo: 040',1,480907,480907,50000,50,'120','110','040'),(21,13,5,'Puerta Aluminio T87 con Vidrio 220*110',1,467690,467690,50000,50,'220','110',''),(22,13,1,'2 cuerpos 220*110',1,325112,325112,50000,50,'220','110',''),(23,13,7,'vitrina Eliot 120*110 fondo: 040',1,480907,480907,50000,50,'120','110','040'),(24,14,5,'Puerta Aluminio T87 con Vidrio 220*110',1,467690,467690,50000,50,'220','110',''),(25,14,1,'2 cuerpos 220*110',1,325112,325112,50000,50,'220','110',''),(26,14,7,'vitrina Eliot 120*110 fondo: 040',1,480907,480907,50000,50,'120','110','040'),(27,15,5,'Puerta Aluminio T87 con Vidrio 220*110',1,467690,467690,50000,50,'220','110',''),(28,15,1,'2 cuerpos 220*110',1,325112,325112,50000,50,'220','110',''),(29,15,7,'vitrina Eliot 120*110 fondo: 040',1,480907,480907,50000,50,'120','110','040');
/*!40000 ALTER TABLE `cotizaciondetalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamento`
--

DROP TABLE IF EXISTS `departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departamento` (
  `iddepartamento` int(4) NOT NULL,
  `nombreDept` varchar(45) NOT NULL,
  PRIMARY KEY (`iddepartamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamento`
--

LOCK TABLES `departamento` WRITE;
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;
INSERT INTO `departamento` VALUES (1,'antioquia'),(2,'bolivar');
/*!40000 ALTER TABLE `departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empleado` (
  `idempleado` int(6) NOT NULL AUTO_INCREMENT,
  `idrol` int(3) NOT NULL,
  `idciudad` int(4) NOT NULL,
  `idtipodocumento` int(5) NOT NULL,
  `numeroDocumento` varchar(15) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `edad` int(11) NOT NULL,
  `genero` tinyint(1) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `correoElectronico` varchar(65) NOT NULL,
  `password` varchar(705) NOT NULL,
  `fechaIngreso` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `telefono` varchar(15) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idempleado`),
  KEY `fk_empleado_rol1_idx` (`idrol`),
  KEY `fk_empleado_ciudad1_idx` (`idciudad`),
  KEY `fk_empleado_tipodocumento1_idx` (`idtipodocumento`),
  CONSTRAINT `fk_empleado_ciudad1` FOREIGN KEY (`idciudad`) REFERENCES `ciudad` (`idciudad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_empleado_rol1` FOREIGN KEY (`idrol`) REFERENCES `rol` (`idrol`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_empleado_tipodocumento1` FOREIGN KEY (`idtipodocumento`) REFERENCES `tipodocumento` (`idtipodocumento`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (1,1,1,1,'94321235','william','Sanchez fajardo',41,1,'2013-05-07','william@hotmail.com','ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413','2016-01-27 04:39:59','52524','fsfsf'),(2,2,2,1,'29663120','Margarita','Duran Narvaez',35,0,'2013-05-02','margarita@hotmail.com','3627909a29c31381a071ec27f7c9ca97726182aed29a7ddd2e54353322cfb30abb9e3a6df2ac2c20fe23436311d678564d0c8d305930575f60e2d3d048184d79','2016-01-27 04:39:59','535335','bggw');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facturas`
--

DROP TABLE IF EXISTS `facturas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facturas` (
  `idfacturas` int(11) NOT NULL AUTO_INCREMENT,
  `idempleado` int(6) NOT NULL,
  `idcliente` int(6) NOT NULL,
  `subtotal` bigint(15) DEFAULT NULL,
  `iva` bigint(15) DEFAULT NULL,
  `preciototal` bigint(15) DEFAULT NULL,
  `fechaventa` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idfacturas`),
  KEY `fk_ventas_cliente_idx` (`idcliente`),
  KEY `fk_ventas_empleado1_idx` (`idempleado`),
  CONSTRAINT `fk_ventas_cliente` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ventas_empleado1` FOREIGN KEY (`idempleado`) REFERENCES `empleado` (`idempleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facturas`
--

LOCK TABLES `facturas` WRITE;
/*!40000 ALTER TABLE `facturas` DISABLE KEYS */;
INSERT INTO `facturas` VALUES (19,1,2,2833660,539744,3373404,'2016-03-22 22:35:56'),(20,2,2,2466597,469827,2936424,'2016-03-23 14:02:01'),(21,1,2,2977890,567216,3545106,'2016-04-14 23:34:01'),(22,1,2,1473804,280724,1754528,'2016-04-23 22:41:54'),(23,1,2,1473804,280724,1754528,'2016-04-23 22:44:15'),(24,1,2,1072434,204272,1276706,'2016-04-26 00:14:59'),(28,1,2,249488,47521,297009,'2016-05-04 19:30:51');
/*!40000 ALTER TABLE `facturas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materiales`
--

DROP TABLE IF EXISTS `materiales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materiales` (
  `idmateriales` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `preciocost` int(11) DEFAULT NULL,
  PRIMARY KEY (`idmateriales`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materiales`
--

LOCK TABLES `materiales` WRITE;
/*!40000 ALTER TABLE `materiales` DISABLE KEYS */;
INSERT INTO `materiales` VALUES (1,'cabezal',6125),(2,'sillar',6800),(3,'jamba',5785),(4,'enganche',6820),(5,'traslape',6820),(6,'hsuperior',4884),(7,'hinferior',6384),(8,'rodamiento',5400),(9,'chapa',5000),(10,'guias',2000),(11,'empaque',1500),(12,'adaptador',3750),(13,'cabezal5020',8703),(14,'sillar5020',8867),(15,'jamba5020',8700),(16,'enganche5020',8583),(17,'traslape5020',8867),(18,'hsuperior5020',6852),(19,'hinferior5020',8317),(20,'rodamiento5020',9500),(21,'chapa5020',5000),(22,'guias5020',2000),(23,'empaque5020',1500),(24,'adaptador5020',5416),(25,'cabezal8025',10367),(26,'sillar8025',10533),(27,'jamba8025',10367),(28,'enganche8025',10250),(29,'traslape8025',10533),(30,'hsuperior8025',8517),(31,'hinferior8025',10000),(32,'rodamiento8025',9500),(33,'chapa8025',5000),(34,'guias8025',2000),(35,'empaque8025',1500),(36,'adaptador8025',7083);
/*!40000 ALTER TABLE `materiales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `idproductos` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idproductos`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'Vitrina 2 cuerpos','ventana'),(2,'Vitrina 3 Cuerpos','ventana'),(3,'Vitrina 4 Cuerpos','ventana'),(4,'vidrio','vidrio'),(5,'Puerta Aluminio T87 con Vidrio','aluminio'),(6,'Puerta Aluminio T103 con entamborado U71','entamborada'),(7,'Vitrina Cuarto Circulo 1 1/2','vitrina'),(8,'Puerta Aluminio T87  con entamborado F06','aluminio y entamborado'),(9,'puerta Aluminio T103 con vidrio','aluminio y vidrio'),(10,'puerta Aluminio Alamo 1 1/2','aluminioAlamo y Vidrio'),(11,'vitrina PerfilEsquinero 1','vitrina corta'),(12,'vitrina PerfilEsquinero 1 Larga','vitrina larga');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `puertas`
--

DROP TABLE IF EXISTS `puertas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `puertas` (
  `idpuertas` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `preciocot` int(11) DEFAULT NULL,
  PRIMARY KEY (`idpuertas`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `puertas`
--

LOCK TABLES `puertas` WRITE;
/*!40000 ALTER TABLE `puertas` DISABLE KEYS */;
INSERT INTO `puertas` VALUES (1,'aluminio3conAleta',7334),(2,'aluminioT87',5333),(3,'partidorT103',12833),(4,'pisavidrios',4000),(5,'chapa',90000),(6,'pibotesAmericanos',30000),(7,'varillaTensora',5000),(8,'escuadras',500),(9,'perfilU71',6250),(10,'entamborado',12750),(11,'entamboradoF06',10000),(12,'Alamo UnoMedia',9700),(13,'bisagra',1800),(14,'empaque',1400);
/*!40000 ALTER TABLE `puertas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `idrol` int(3) NOT NULL,
  `nombreRol` varchar(45) NOT NULL,
  PRIMARY KEY (`idrol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'adminisrador'),(2,'cajero');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sueldo`
--

DROP TABLE IF EXISTS `sueldo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sueldo` (
  `idsueldo` int(11) NOT NULL AUTO_INCREMENT,
  `idempleado` int(6) NOT NULL,
  `sueldoBase` decimal(18,2) NOT NULL,
  `comision` decimal(18,2) NOT NULL,
  `horasExtras` int(11) DEFAULT NULL,
  `descuentos` decimal(18,2) DEFAULT NULL,
  `sueldoTotal` decimal(18,2) NOT NULL,
  `fechaCreacionSueldo` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idsueldo`),
  KEY `fk_sueldo_empleado1_idx` (`idempleado`),
  CONSTRAINT `fk_sueldo_empleado1` FOREIGN KEY (`idempleado`) REFERENCES `empleado` (`idempleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sueldo`
--

LOCK TABLES `sueldo` WRITE;
/*!40000 ALTER TABLE `sueldo` DISABLE KEYS */;
INSERT INTO `sueldo` VALUES (4,1,670000.00,134200.00,12,234560.00,617640.00,'2016-03-23 02:04:43'),(5,2,500000.00,120000.00,13,58000.00,614000.00,'2016-04-14 23:39:23');
/*!40000 ALTER TABLE `sueldo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipodocumento`
--

DROP TABLE IF EXISTS `tipodocumento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipodocumento` (
  `idtipodocumento` int(5) NOT NULL,
  `nombretd` varchar(45) NOT NULL,
  PRIMARY KEY (`idtipodocumento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipodocumento`
--

LOCK TABLES `tipodocumento` WRITE;
/*!40000 ALTER TABLE `tipodocumento` DISABLE KEYS */;
INSERT INTO `tipodocumento` VALUES (1,'Cedula'),(2,'Nit'),(3,'Rut');
/*!40000 ALTER TABLE `tipodocumento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventanadetalle`
--

DROP TABLE IF EXISTS `ventanadetalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ventanadetalle` (
  `idventanaDetalle` int(11) NOT NULL AUTO_INCREMENT,
  `idfacturas` int(11) NOT NULL,
  `idproductos` int(11) NOT NULL,
  `nombrepro` varchar(45) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precioventa` bigint(15) NOT NULL,
  `total` bigint(15) DEFAULT NULL,
  `preciotrabajo` int(11) DEFAULT NULL,
  `porcentajeganancia` int(11) DEFAULT NULL,
  `alto` varchar(45) DEFAULT NULL,
  `ancho` varchar(45) DEFAULT NULL,
  `fondo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idventanaDetalle`),
  KEY `fk_ventanaDetalle_facturas1_idx` (`idfacturas`),
  KEY `fk_ventanaDetalle_productos1_idx` (`idproductos`),
  CONSTRAINT `fk_ventanaDetalle_facturas1` FOREIGN KEY (`idfacturas`) REFERENCES `facturas` (`idfacturas`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ventanaDetalle_productos1` FOREIGN KEY (`idproductos`) REFERENCES `productos` (`idproductos`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventanadetalle`
--

LOCK TABLES `ventanadetalle` WRITE;
/*!40000 ALTER TABLE `ventanadetalle` DISABLE KEYS */;
INSERT INTO `ventanadetalle` VALUES (39,19,2,'3 cuerpos 440*340',3,1124468,3373404,67000,50,'440','340',NULL),(40,20,3,'4 cuerpos 440*240',3,978808,2936424,55000,45,'440','240',NULL),(41,21,3,'4 cuerpos 123*345',4,501229,2004916,34400,50,'123','345',NULL),(42,21,3,'4 cuerpos 254*345',2,770095,1540190,34400,50,'254','345',NULL),(43,22,2,'3 cuerpos 345*354',2,877264,1754528,50000,50,'345','354',''),(44,23,2,'3 cuerpos 345*354',2,877264,1754528,50000,50,'345','354',''),(45,24,1,'2 cuerpos 345*354',2,638353,1276706,50000,50,'345','354',''),(55,28,1,'2 cuerpos 190*110',1,297009,297009,50000,50,'190','110','');
/*!40000 ALTER TABLE `ventanadetalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vidrios`
--

DROP TABLE IF EXISTS `vidrios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vidrios` (
  `idvidrios` int(11) NOT NULL AUTO_INCREMENT,
  `calibre` varchar(45) NOT NULL,
  `preciocost` int(11) NOT NULL,
  PRIMARY KEY (`idvidrios`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vidrios`
--

LOCK TABLES `vidrios` WRITE;
/*!40000 ALTER TABLE `vidrios` DISABLE KEYS */;
INSERT INTO `vidrios` VALUES (1,'2mm',9624),(2,'2mm Antireflejo',20400),(3,'3mm',10684),(4,'4mm',16001),(5,'5mm ',22552),(6,'6mm ',26168),(7,'grabado',26800),(8,'azul ',25488),(9,'3mm Espejo',19850),(10,'4mm Espejo',22630);
/*!40000 ALTER TABLE `vidrios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vitrinas`
--

DROP TABLE IF EXISTS `vitrinas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vitrinas` (
  `idvitrinas` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `preciocot` int(11) DEFAULT NULL,
  PRIMARY KEY (`idvitrinas`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vitrinas`
--

LOCK TABLES `vitrinas` WRITE;
/*!40000 ALTER TABLE `vitrinas` DISABLE KEYS */;
INSERT INTO `vitrinas` VALUES (1,'cuartoCirculo',5750),(2,'anguloMedia',1334),(3,'acoples',3500),(4,'carrileras',4417),(5,'Piañas',300),(6,'rodamientosPiso',11250),(7,'naveDivisionDucha',3250),(8,'rodamientosDucha',1000),(9,'empaque',1400),(10,'perfilEsqunero Una',5700),(11,'tubular Una',4917),(12,'rodamiento Economico',4000),(13,'anclaje A15',400);
/*!40000 ALTER TABLE `vitrinas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'vidrieria'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-05 10:49:32
