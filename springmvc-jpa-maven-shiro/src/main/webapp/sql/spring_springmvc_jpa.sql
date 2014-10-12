/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50096
Source Host           : 127.0.0.1:3306
Source Database       : spring_springmvc_jpa

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2013-10-10 23:50:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info` (
  `id` decimal(10,0) NOT NULL default '0',
  `account` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `name` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_info
-- ----------------------------
