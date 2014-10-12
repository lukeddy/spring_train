/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : sy

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2014-10-12 17:11:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tbug`
-- ----------------------------
DROP TABLE IF EXISTS `tbug`;
CREATE TABLE `tbug` (
  `id` int(11) NOT NULL auto_increment,
  `createdatetime` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  `name` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbug
-- ----------------------------

-- ----------------------------
-- Table structure for `tmenu`
-- ----------------------------
DROP TABLE IF EXISTS `tmenu`;
CREATE TABLE `tmenu` (
  `id` int(11) NOT NULL auto_increment,
  `iconcls` varchar(200) default NULL,
  `seq` varchar(200) default NULL,
  `text` varchar(200) default NULL,
  `url` varchar(200) default NULL,
  `pid` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tmenu
-- ----------------------------

-- ----------------------------
-- Table structure for `tonline`
-- ----------------------------
DROP TABLE IF EXISTS `tonline`;
CREATE TABLE `tonline` (
  `id` int(11) NOT NULL auto_increment,
  `ip` varchar(100) default NULL,
  `logindatetime` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  `loginname` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tonline
-- ----------------------------

-- ----------------------------
-- Table structure for `tresource`
-- ----------------------------
DROP TABLE IF EXISTS `tresource`;
CREATE TABLE `tresource` (
  `id` int(11) NOT NULL auto_increment,
  `seq` decimal(10,0) default NULL,
  `text` varchar(200) default NULL,
  `url` varchar(200) default NULL,
  `pid` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tresource
-- ----------------------------

-- ----------------------------
-- Table structure for `trole`
-- ----------------------------
DROP TABLE IF EXISTS `trole`;
CREATE TABLE `trole` (
  `id` int(11) NOT NULL auto_increment,
  `text` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trole
-- ----------------------------

-- ----------------------------
-- Table structure for `trole_tresource`
-- ----------------------------
DROP TABLE IF EXISTS `trole_tresource`;
CREATE TABLE `trole_tresource` (
  `id` int(11) NOT NULL auto_increment,
  `resource_id` int(11) default NULL,
  `role_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trole_tresource
-- ----------------------------

-- ----------------------------
-- Table structure for `tuser`
-- ----------------------------
DROP TABLE IF EXISTS `tuser`;
CREATE TABLE `tuser` (
  `id` int(11) NOT NULL auto_increment,
  `createdatetime` timestamp NULL default NULL,
  `modifydatetime` timestamp NULL default NULL,
  `name` varchar(50) default NULL,
  `pwd` varchar(100) default NULL,
  `create_time` date default NULL,
  `update_time` date default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tuser
-- ----------------------------
INSERT INTO `tuser` VALUES ('1', '2014-10-12 17:07:01', '2014-10-12 17:07:01', '爱美', '666666', '2014-10-12', '2014-10-12');
INSERT INTO `tuser` VALUES ('2', '2014-10-12 17:04:48', '2014-10-12 17:04:48', 'lili', '12312312', '2014-10-12', '2014-10-12');
INSERT INTO `tuser` VALUES ('3', '2014-10-12 17:05:41', '2014-10-12 17:05:41', 'lili', '12312312', '2014-10-12', '2014-10-12');
INSERT INTO `tuser` VALUES ('4', '2014-10-12 17:06:14', '2014-10-12 17:06:14', 'lili', '12312312', '2014-10-12', '2014-10-12');
INSERT INTO `tuser` VALUES ('5', '2014-10-12 17:07:01', '2014-10-12 17:07:01', 'lili', '12312312', '2014-10-12', '2014-10-12');

-- ----------------------------
-- Table structure for `tuser_trole`
-- ----------------------------
DROP TABLE IF EXISTS `tuser_trole`;
CREATE TABLE `tuser_trole` (
  `id` int(11) NOT NULL auto_increment,
  `role_id` int(11) default NULL,
  `user_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tuser_trole
-- ----------------------------
