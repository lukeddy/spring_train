/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : testdemo

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2015-05-15 15:53:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(50) NOT NULL,
  `pwd` varchar(100) default NULL,
  `role` tinyint(4) default NULL COMMENT '用户角色：1超级管理员，2开发人员，3测试人员，4项目管理人员',
  `lockState` tinyint(4) default '0' COMMENT '账户锁定状态：0正常,1锁定',
  `lastLoginTime` bigint(20) default NULL COMMENT '上次登录时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'super', '5b2571fe1a58880557293a8aa15c8ce5', '1', '0', '1431675517823');
INSERT INTO `t_user` VALUES ('2', 'test', '5b2571fe1a58880557293a8aa15c8ce5', '1', '0', '1431675517823');
