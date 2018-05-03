-- MySQL dump 10.13  Distrib 5.7.19, for macos10.12 (x86_64)
--
-- Host: localhost    Database: controlescolar
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `alumno`
--

DROP TABLE IF EXISTS `alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alumno` (
  `idAlumno` int(11) NOT NULL AUTO_INCREMENT,
  `matricula` varchar(18) NOT NULL,
  `direccion` varchar(150) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `idPrograma` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `idEstado` int(11) NOT NULL,
  PRIMARY KEY (`idAlumno`,`idPrograma`,`idUsuario`,`idEstado`),
  KEY `fk_alumno_programa1_idx` (`idPrograma`),
  KEY `fk_alumno_usuario1_idx` (`idUsuario`),
  KEY `fk_alumno_estado1_idx` (`idEstado`),
  CONSTRAINT `fk_alumno_estado1` FOREIGN KEY (`idEstado`) REFERENCES `estado` (`idEstado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_alumno_programa1` FOREIGN KEY (`idPrograma`) REFERENCES `programa` (`idPrograma`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_alumno_usuario1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumno`
--

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `docentes`
--

DROP TABLE IF EXISTS `docentes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `docentes` (
  `idDocente` int(11) NOT NULL AUTO_INCREMENT,
  `rfc` varchar(18) NOT NULL,
  `direccion` varchar(150) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `idEstado` int(11) NOT NULL,
  PRIMARY KEY (`idDocente`,`idUsuario`,`idEstado`),
  KEY `fk_docentes_usuario1_idx` (`idUsuario`),
  KEY `fk_docentes_estado1_idx` (`idEstado`),
  CONSTRAINT `fk_docentes_estado1` FOREIGN KEY (`idEstado`) REFERENCES `estado` (`idEstado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_docentes_usuario1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docentes`
--

LOCK TABLES `docentes` WRITE;
/*!40000 ALTER TABLE `docentes` DISABLE KEYS */;
/*!40000 ALTER TABLE `docentes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado` (
  `idEstado` int(11) NOT NULL AUTO_INCREMENT,
  `estado` varchar(100) NOT NULL,
  PRIMARY KEY (`idEstado`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` VALUES (1,'Aguascalientes'),(2,'Baja California'),(3,'Campeche'),(4,'Sonora'),(5,'Zacatecas');
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materias`
--

DROP TABLE IF EXISTS `materias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materias` (
  `idMateria` int(11) NOT NULL AUTO_INCREMENT,
  `materia` varchar(100) NOT NULL,
  `creditos` smallint(2) NOT NULL,
  `idMateriaAnt` int(11) DEFAULT NULL,
  `idPrograma` int(11) NOT NULL,
  PRIMARY KEY (`idMateria`,`idPrograma`),
  KEY `fk_materias_programa_idx` (`idPrograma`),
  CONSTRAINT `fk_materias_programa` FOREIGN KEY (`idPrograma`) REFERENCES `programa` (`idPrograma`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materias`
--

LOCK TABLES `materias` WRITE;
/*!40000 ALTER TABLE `materias` DISABLE KEYS */;
INSERT INTO `materias` VALUES (1,'matemáticas',8,NULL,1),(2,'matemáticas 2',8,1,1),(5,'español 1',10,NULL,2),(6,'español 2',10,5,2),(7,'español 3',10,6,2);
/*!40000 ALTER TABLE `materias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programa`
--

DROP TABLE IF EXISTS `programa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `programa` (
  `idPrograma` int(11) NOT NULL AUTO_INCREMENT,
  `programa` varchar(100) NOT NULL,
  PRIMARY KEY (`idPrograma`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programa`
--

LOCK TABLES `programa` WRITE;
/*!40000 ALTER TABLE `programa` DISABLE KEYS */;
INSERT INTO `programa` VALUES (1,'Ingeniería de Software'),(2,'Ingeniería en Computación');
/*!40000 ALTER TABLE `programa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellidoPaterno` varchar(50) NOT NULL,
  `apellidoMaterno` varchar(50) NOT NULL,
  `usuario` varchar(30) NOT NULL,
  `password` varchar(60) NOT NULL,
  `perfil` int(1) NOT NULL,
  `fechaNac` date NOT NULL,
  `mail` varchar(75) NOT NULL,
  `idEstado` int(11) NOT NULL,
  `estatus` varchar(1) NOT NULL DEFAULT '0',
  `cadena` varchar(100) NOT NULL,
  PRIMARY KEY (`idUsuario`,`idEstado`),
  KEY `fk_usuario_estado1_idx` (`idEstado`),
  CONSTRAINT `fk_usuario_estado1` FOREIGN KEY (`idEstado`) REFERENCES `estado` (`idEstado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Sodel','Vázquez','Reyes','sodel','Sodel123',1,'2018-03-10','sodel@hotcakes.com',3,'0',''),(2,'Alejandro','Mauricio','González','alex','8c710486ceb03f08de3ebdae34ead9f249a2f699',1,'1080-11-20','ASDASD@ASDAS.ES',1,'0',''),(3,'alejandro','mauricio','gonzalez','alex','829d025c8c341a0ae5c63d74396e706978ec0675',1,'2018-05-01','amg_dark@hotmail.com',2,'0','THGLNSLTMTGIDGJZQVAPTWXKQMKUIJOCPRDNRNKVAVQOACMZHD'),(4,'armando','olmedo','zumaya','armando','18e7a0d0f936fc6fcca72f86c8e99346e2a410d1',1,'2018-05-15','yo6648@hotmail.com',3,'0','MONVBFANCMPMXVEBBWEYDGJPAGQJTVEYNZIKGEVQYDBVCOJARW'),(5,'panchito','lopez','lopez','panchito','97693132707a32d5d7c633646b9e72e2731fd012',1,'2018-05-10','amg_dark@hotmail.com',4,'0','HXEYGRNBSSZRERFNWPWWEHXZEQXRPBVPQCZTMKNMFOXDEFQSGA'),(6,'juanito','mendez','mendez','juanito','952ae21888ca5b79408196eacc8dd628e492fddb',1,'2018-05-03','amg_dark@hotmail.com',5,'1',''),(7,'maria','mercedes','lopez','maria','acfc263082190360dce09f701f19b17f1042c22d',1,'2018-04-26','amg_Dark@hotmail.com',1,'1','');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-03 17:04:14
