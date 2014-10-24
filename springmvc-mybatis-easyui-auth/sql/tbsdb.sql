/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : tbsdb

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2014-10-24 13:51:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tbccontemp`
-- ----------------------------
DROP TABLE IF EXISTS `tbccontemp`;
CREATE TABLE `tbccontemp` (
  `id` int(11) NOT NULL auto_increment,
  `type` varchar(50) default NULL,
  `title` varchar(200) default NULL,
  `text` varchar(1000) default NULL,
  `image` varchar(500) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbccontemp
-- ----------------------------

-- ----------------------------
-- Table structure for `tbclisttemp`
-- ----------------------------
DROP TABLE IF EXISTS `tbclisttemp`;
CREATE TABLE `tbclisttemp` (
  `id` int(11) NOT NULL auto_increment,
  `href` varchar(200) default NULL,
  `type` varchar(50) default NULL,
  `text` varchar(1000) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbclisttemp
-- ----------------------------

-- ----------------------------
-- Table structure for `tbctemp`
-- ----------------------------
DROP TABLE IF EXISTS `tbctemp`;
CREATE TABLE `tbctemp` (
  `id` int(11) NOT NULL auto_increment,
  `href` varchar(200) default NULL,
  `text` varchar(500) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbctemp
-- ----------------------------

-- ----------------------------
-- Table structure for `tbshttprequest`
-- ----------------------------
DROP TABLE IF EXISTS `tbshttprequest`;
CREATE TABLE `tbshttprequest` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) default NULL,
  `header` varchar(200) default NULL,
  `firstPage` varchar(200) default NULL,
  `encoding` varchar(100) default NULL,
  `listName` varchar(50) default NULL,
  `regexs` varchar(200) default NULL,
  `arrtSplit` varchar(100) default NULL,
  `inserts` varchar(500) default NULL,
  `listUrlRegex` varchar(500) default NULL,
  `listUrlTest` int(11) default NULL,
  `listPageId` int(11) default NULL,
  `listSplitUrlChar` varchar(200) default NULL,
  `listSplitUrl` varchar(200) default NULL,
  `listRegexs` varchar(500) default NULL,
  `listAttrSplit` varchar(200) default NULL,
  `listInserts` varchar(200) default NULL,
  `contUrlRegex` varchar(200) default NULL,
  `contUrlTest` int(11) default NULL,
  `contRegexs` varchar(200) default NULL,
  `contAttrSplit` varchar(200) default NULL,
  `contInserts` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbshttprequest
-- ----------------------------

-- ----------------------------
-- Table structure for `tbsmenu`
-- ----------------------------
DROP TABLE IF EXISTS `tbsmenu`;
CREATE TABLE `tbsmenu` (
  `id` int(11) NOT NULL auto_increment,
  `parentId` int(11) default NULL,
  `name` varchar(50) default NULL,
  `isMenu` int(11) default NULL,
  `type` int(11) default NULL,
  `sortNumber` int(11) default NULL,
  `url` varchar(255) default NULL,
  `status` int(11) default NULL,
  `createTime` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbsmenu
-- ----------------------------

-- ----------------------------
-- Table structure for `tbsrole`
-- ----------------------------
DROP TABLE IF EXISTS `tbsrole`;
CREATE TABLE `tbsrole` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) default NULL,
  `text` varchar(1000) default NULL,
  `createTime` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbsrole
-- ----------------------------

-- ----------------------------
-- Table structure for `tbsrolemenu`
-- ----------------------------
DROP TABLE IF EXISTS `tbsrolemenu`;
CREATE TABLE `tbsrolemenu` (
  `id` int(11) NOT NULL auto_increment,
  `roleId` int(11) default NULL,
  `menuIdFun` varchar(200) default NULL,
  `menuId` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbsrolemenu
-- ----------------------------

-- ----------------------------
-- Table structure for `tbsroleuser`
-- ----------------------------
DROP TABLE IF EXISTS `tbsroleuser`;
CREATE TABLE `tbsroleuser` (
  `id` int(11) NOT NULL auto_increment,
  `userId` int(11) default NULL,
  `roleId` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbsroleuser
-- ----------------------------

-- ----------------------------
-- Table structure for `tbsuser`
-- ----------------------------
DROP TABLE IF EXISTS `tbsuser`;
CREATE TABLE `tbsuser` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(50) default NULL,
  `password` varchar(200) default NULL,
  `time` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  `ip` varchar(200) default NULL,
  `count` int(11) default NULL,
  `isLock` int(11) default NULL,
  `lockTime` timestamp NULL default NULL,
  `failCount` int(11) default NULL,
  `isAdmin` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbsuser
-- ----------------------------
INSERT INTO `tbsuser` VALUES ('1', 'admin', 'a45f95af5d76c986', '2014-10-24 11:35:37', null, null, null, null, null, '0');
