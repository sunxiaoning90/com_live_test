/*
Navicat MySQL Data Transfer

Source Server         : MySQL-本地(PC机)
Source Server Version : 50520
Source Host           : 127.0.0.1:3306
Source Database       : test_spring_dao

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2020-11-16 14:57:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` varchar(10) NOT NULL,
  `balance` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('A', '1000');
INSERT INTO `account` VALUES ('B1', '2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `userpwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('3', '悟空', '1');
INSERT INTO `user` VALUES ('4', '悟空', '1');
INSERT INTO `user` VALUES ('5', '悟空', '123');
INSERT INTO `user` VALUES ('6', '悟空', '123');
INSERT INTO `user` VALUES ('7', '悟空', '123');
INSERT INTO `user` VALUES ('8', '悟空', '123');
INSERT INTO `user` VALUES ('9', '悟空', '123');
INSERT INTO `user` VALUES ('10', '悟空', '123');
INSERT INTO `user` VALUES ('11', '八戒', '123');
INSERT INTO `user` VALUES ('12', '沙僧', '123');
INSERT INTO `user` VALUES ('13', '八戒', '123');
INSERT INTO `user` VALUES ('14', '沙僧', '123');
INSERT INTO `user` VALUES ('15', '八戒', '123');
INSERT INTO `user` VALUES ('16', '沙僧', '123');
INSERT INTO `user` VALUES ('17', '八戒', '123');
INSERT INTO `user` VALUES ('18', '沙僧', '123');
