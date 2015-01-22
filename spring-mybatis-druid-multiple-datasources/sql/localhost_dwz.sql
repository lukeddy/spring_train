/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : dwz

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2015-01-22 11:42:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL auto_increment,
  `userName` varchar(50) default NULL,
  `password` varchar(50) default NULL,
  `roleId` int(11) default NULL,
  `trueName` varchar(50) default NULL,
  `createTime` date default NULL,
  `modifyTime` date default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'John', 'lovefancy', null, 'wangwu', '2015-01-22', null);
INSERT INTO `t_user` VALUES ('2', 'Lucy', 'greatwd', null, 'lisi', '2015-01-22', null);
INSERT INTO `t_user` VALUES ('100', 'admin100', 'password100', null, '小李4', '2015-01-21', null);
