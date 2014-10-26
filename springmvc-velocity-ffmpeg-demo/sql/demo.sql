DROP DATABASE IF EXISTS demodb;
CREATE DATABASE demodb DEFAULT CHARSET utf8;
USE demodb;
-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of init users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'super', 'super');
INSERT INTO `users` VALUES ('2', 'admin', 'admin');


-- ----------------------------
-- Table structure for `files`
-- ----------------------------
DROP TABLE IF EXISTS `files`;
CREATE TABLE `files` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(100) NOT NULL,
  `store_id` varchar(100) NOT NULL,
  `title` varchar(100) NOT NULL,
  `subtitle` varchar(100) NOT NULL,
  `thumb_url` varchar(300) NOT NULL,
  `movie_url` varchar(300) NOT NULL,
  `movie_store_path` varchar(300) NOT NULL,
  `thumb_store_path` varchar(300) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
