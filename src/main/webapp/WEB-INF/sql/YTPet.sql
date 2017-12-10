/*
Date: 2017-12-10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(12) DEFAULT NULL,
  `account` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_custom_blc
-- ----------------------------
DROP TABLE IF EXISTS `t_custom_blc`;
CREATE TABLE `t_custom_blc` (
  `blcid` int(11) NOT NULL AUTO_INCREMENT,
  `balance` double DEFAULT NULL,
  `f_customid` int(11) DEFAULT NULL,
  `updtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`blcid`),
  UNIQUE KEY `f_customid` (`f_customid`),
  CONSTRAINT `t_custom_blc_ibfk_1` FOREIGN KEY (`f_customid`) REFERENCES `t_custom_prim` (`customid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_custom_chrginfo
-- ----------------------------
DROP TABLE IF EXISTS `t_custom_chrginfo`;
CREATE TABLE `t_custom_chrginfo` (
  `chargeid` int(11) NOT NULL AUTO_INCREMENT,
  `f_customid` int(11) DEFAULT NULL,
  `chargetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `amount` double DEFAULT NULL,
  PRIMARY KEY (`chargeid`),
  KEY `t_custom_chrginfo_ibfk_1` (`f_customid`),
  CONSTRAINT `t_custom_chrginfo_ibfk_1` FOREIGN KEY (`f_customid`) REFERENCES `t_custom_prim` (`customid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_custom_pet
-- ----------------------------
DROP TABLE IF EXISTS `t_custom_pet`;
CREATE TABLE `t_custom_pet` (
  `petid` int(11) NOT NULL AUTO_INCREMENT,
  `f_customid` int(11) DEFAULT NULL,
  `pettype` enum('very big','big','small','very small') NOT NULL,
  `petname` varchar(32) DEFAULT NULL,
  `petimage` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`petid`),
  UNIQUE KEY `f_customid` (`f_customid`),
  CONSTRAINT `t_custom_pet_ibfk_1` FOREIGN KEY (`f_customid`) REFERENCES `t_custom_prim` (`customid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_custom_prim
-- ----------------------------
DROP TABLE IF EXISTS `t_custom_prim`;
CREATE TABLE `t_custom_prim` (
  `customid` int(11) NOT NULL AUTO_INCREMENT,
  `customname` varchar(12) DEFAULT NULL,
  `customphone` varchar(32) DEFAULT NULL,
  `crttime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`customid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_custom_record
-- ----------------------------
DROP TABLE IF EXISTS `t_custom_record`;
CREATE TABLE `t_custom_record` (
  `recordid` int(11) NOT NULL AUTO_INCREMENT,
  `f_customid` int(11) DEFAULT NULL,
  `paytime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `paywhat` varchar(64) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `remark` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`recordid`),
  KEY `t_custom_record_ibfk_1` (`f_customid`),
  CONSTRAINT `t_custom_record_ibfk_1` FOREIGN KEY (`f_customid`) REFERENCES `t_custom_prim` (`customid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
