-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: mysqlproject
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods` (
  `gid` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品id',
  `gname` varchar(100) NOT NULL COMMENT '商品名',
  `price` int NOT NULL COMMENT '价格',
  `uid` varchar(100) NOT NULL,
  PRIMARY KEY (`gid`),
  KEY `goods_user_FK` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `is_bought`
--

DROP TABLE IF EXISTS `is_bought`;
/*!50001 DROP VIEW IF EXISTS `is_bought`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `is_bought` AS SELECT 
 1 AS `gid`,
 1 AS `price`,
 1 AS `gname`,
 1 AS `username`,
 1 AS `time`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `is_saling`
--

DROP TABLE IF EXISTS `is_saling`;
/*!50001 DROP VIEW IF EXISTS `is_saling`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `is_saling` AS SELECT 
 1 AS `gid`,
 1 AS `gname`,
 1 AS `price`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `salemanager`
--

DROP TABLE IF EXISTS `salemanager`;
/*!50001 DROP VIEW IF EXISTS `salemanager`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `salemanager` AS SELECT 
 1 AS `gid`,
 1 AS `gname`,
 1 AS `uid`,
 1 AS `price`,
 1 AS `time`,
 1 AS `status`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `salerecode`
--

DROP TABLE IF EXISTS `salerecode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salerecode` (
  `gid` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品id',
  `uid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '购买人',
  `status` varchar(10) NOT NULL COMMENT '当前销售状态',
  `time` datetime NOT NULL COMMENT '购买时间',
  UNIQUE KEY `salerecode_unique` (`gid`),
  KEY `NewTable_user_FK` (`uid`),
  CONSTRAINT `salerecode_goods_FK` FOREIGN KEY (`gid`) REFERENCES `goods` (`gid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='销售';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salerecode`
--

LOCK TABLES `salerecode` WRITE;
/*!40000 ALTER TABLE `salerecode` DISABLE KEYS */;
/*!40000 ALTER TABLE `salerecode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `money` int DEFAULT '0' COMMENT '余额',
  `pass` int NOT NULL COMMENT '权限',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','$2a$10$JuvhH/XUM7mDwE/MEAD4L.682TvaDiruMLQCgvydberPkcpim95tO',1137,1),('user001','$2a$10$JuvhH/XUM7mDwE/MEAD4L.682TvaDiruMLQCgvydberPkcpim95tO',1,0),('user002','$2a$10$JuvhH/XUM7mDwE/MEAD4L.682TvaDiruMLQCgvydberPkcpim95tO',0,0),('user003','$2a$10$JuvhH/XUM7mDwE/MEAD4L.682TvaDiruMLQCgvydberPkcpim95tO',0,0),('user004','$2a$10$JuvhH/XUM7mDwE/MEAD4L.682TvaDiruMLQCgvydberPkcpim95tO',0,0),('user005','$2a$10$JuvhH/XUM7mDwE/MEAD4L.682TvaDiruMLQCgvydberPkcpim95tO',0,0),('user006','$2a$10$JuvhH/XUM7mDwE/MEAD4L.682TvaDiruMLQCgvydberPkcpim95tO',0,0),('user007','$2a$10$JuvhH/XUM7mDwE/MEAD4L.682TvaDiruMLQCgvydberPkcpim95tO',0,0),('user008','$2a$10$JuvhH/XUM7mDwE/MEAD4L.682TvaDiruMLQCgvydberPkcpim95tO',0,0),('user009','$2a$10$JuvhH/XUM7mDwE/MEAD4L.682TvaDiruMLQCgvydberPkcpim95tO',0,0),('user010','$2a$10$JuvhH/XUM7mDwE/MEAD4L.682TvaDiruMLQCgvydberPkcpim95tO',0,0),('user011','$2a$10$JuvhH/XUM7mDwE/MEAD4L.682TvaDiruMLQCgvydberPkcpim95tO',0,0),('user012','$2a$10$JuvhH/XUM7mDwE/MEAD4L.682TvaDiruMLQCgvydberPkcpim95tO',0,0),('user013','$2a$10$JuvhH/XUM7mDwE/MEAD4L.682TvaDiruMLQCgvydberPkcpim95tO',0,0),('user014','$2a$10$JuvhH/XUM7mDwE/MEAD4L.682TvaDiruMLQCgvydberPkcpim95tO',0,0),('user015','$2a$10$JuvhH/XUM7mDwE/MEAD4L.682TvaDiruMLQCgvydberPkcpim95tO',0,0),('user016','$2a$10$JuvhH/XUM7mDwE/MEAD4L.682TvaDiruMLQCgvydberPkcpim95tO',128,0),('user017','$2a$10$JuvhH/XUM7mDwE/MEAD4L.682TvaDiruMLQCgvydberPkcpim95tO',0,0),('user018','$2a$10$JuvhH/XUM7mDwE/MEAD4L.682TvaDiruMLQCgvydberPkcpim95tO',0,0),('user019','$2a$10$JuvhH/XUM7mDwE/MEAD4L.682TvaDiruMLQCgvydberPkcpim95tO',0,0),('user020','$2a$10$JuvhH/XUM7mDwE/MEAD4L.682TvaDiruMLQCgvydberPkcpim95tO',0,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'mysqlproject'
--

--
-- Final view structure for view `is_bought`
--

/*!50001 DROP VIEW IF EXISTS `is_bought`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`mypro`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `is_bought` AS select `goods`.`gid` AS `gid`,`goods`.`price` AS `price`,`goods`.`gname` AS `gname`,`salerecode`.`uid` AS `username`,`salerecode`.`time` AS `time` from (`goods` join `salerecode` on((`salerecode`.`gid` = `goods`.`gid`))) where (`salerecode`.`status` = '1') order by `salerecode`.`time` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `is_saling`
--

/*!50001 DROP VIEW IF EXISTS `is_saling`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `is_saling` AS select `goods`.`gid` AS `gid`,`goods`.`gname` AS `gname`,`goods`.`price` AS `price` from (`goods` join `salerecode` on((`goods`.`gid` = `salerecode`.`gid`))) where (`salerecode`.`status` = '0') order by `salerecode`.`time` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `salemanager`
--

/*!50001 DROP VIEW IF EXISTS `salemanager`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`mypro`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `salemanager` AS select `goods`.`gid` AS `gid`,`goods`.`gname` AS `gname`,`goods`.`uid` AS `uid`,`goods`.`price` AS `price`,`salerecode`.`time` AS `time`,`salerecode`.`status` AS `status` from (`goods` join `salerecode` on((`salerecode`.`gid` = `goods`.`gid`))) */;
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

-- Dump completed on 2025-05-27 17:21:36
