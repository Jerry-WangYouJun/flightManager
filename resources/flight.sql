/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50130
Source Host           : localhost:3306
Source Database       : flight

Target Server Type    : MYSQL
Target Server Version : 50130
File Encoding         : 65001

Date: 2017-05-16 06:54:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_airway`
-- ----------------------------
DROP TABLE IF EXISTS `t_airway`;
CREATE TABLE `t_airway` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `startdate` varchar(20) DEFAULT NULL,
  `flighttime` int(10) DEFAULT NULL,
  `flighttype` varchar(10) DEFAULT NULL COMMENT '直飞、转机',
  `company` varchar(20) DEFAULT NULL,
  `fromairport` varchar(20) DEFAULT NULL,
  `toairport` varchar(20) DEFAULT NULL,
  `flightid` int(10) DEFAULT NULL,
  `ontime` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_airway
-- ----------------------------

-- ----------------------------
-- Table structure for `t_company`
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `company` varchar(10) DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  `flights` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_company
-- ----------------------------

-- ----------------------------
-- Table structure for `t_flight`
-- ----------------------------
DROP TABLE IF EXISTS `t_flight`;
CREATE TABLE `t_flight` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `flight` varchar(10) DEFAULT NULL,
  `flightmodel` varchar(10) DEFAULT '' COMMENT '飞机型号（A320/波音747）',
  `flighttype` varchar(10) DEFAULT NULL COMMENT '宽体/窄体',
  `useage` int(10) DEFAULT NULL,
  `business` varchar(20) DEFAULT NULL,
  `tourist
tourist` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_flight
-- ----------------------------

-- ----------------------------
-- Table structure for `t_passenger`
-- ----------------------------
DROP TABLE IF EXISTS `t_passenger`;
CREATE TABLE `t_passenger` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `passenger` varchar(20) DEFAULT NULL,
  `idcard` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `linkman` varchar(20) DEFAULT NULL,
  `linkphone` varchar(20) DEFAULT NULL,
  `order` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_passenger
-- ----------------------------

-- ----------------------------
-- Table structure for `t_price`
-- ----------------------------
DROP TABLE IF EXISTS `t_price`;
CREATE TABLE `t_price` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `added` double(10,3) DEFAULT NULL COMMENT '基建燃油',
  `rebatetype` varchar(10) DEFAULT NULL,
  `rebate` double(10,3) DEFAULT NULL,
  `airewayid` int(10) DEFAULT NULL,
  `classtype` varchar(10) DEFAULT NULL COMMENT '舱位',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_price
-- ----------------------------

-- ----------------------------
-- Table structure for `t_status`
-- ----------------------------
DROP TABLE IF EXISTS `t_status`;
CREATE TABLE `t_status` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `flightid` int(10) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `pretime` varchar(20) DEFAULT NULL,
  `reason` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_status
-- ----------------------------

-- ----------------------------
-- Table structure for `t_ticket`
-- ----------------------------
DROP TABLE IF EXISTS `t_ticket`;
CREATE TABLE `t_ticket` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `flightid` int(10) DEFAULT NULL,
  `order` varchar(20) DEFAULT NULL,
  `ticketdate` varchar(20) DEFAULT NULL,
  `price` double(10,3) DEFAULT NULL,
  `status` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_ticket
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
