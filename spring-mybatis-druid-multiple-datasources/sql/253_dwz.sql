/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.253
Source Server Version : 50173
Source Host           : 192.168.1.253:3306
Source Database       : dwz

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-01-22 11:42:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_flow`
-- ----------------------------
DROP TABLE IF EXISTS `t_flow`;
CREATE TABLE `t_flow` (
  `flowId` varchar(200) NOT NULL,
  `insuranceId` int(11) DEFAULT NULL,
  `codeNumber` int(11) DEFAULT NULL,
  `operatingTime` date DEFAULT NULL,
  PRIMARY KEY (`flowId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_flow
-- ----------------------------
INSERT INTO `t_flow` VALUES ('94003d29-a7b0-42f0-839c-fa609b209ff1', '1', '1', '2015-01-21');
