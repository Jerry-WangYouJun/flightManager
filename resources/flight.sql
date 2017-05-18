/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50130
Source Host           : localhost:3306
Source Database       : flight

Target Server Type    : MYSQL
Target Server Version : 50130
File Encoding         : 65001

Date: 2017-05-19 06:48:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_airport`
-- ----------------------------
DROP TABLE IF EXISTS `t_airport`;
CREATE TABLE `t_airport` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `airport` varchar(20) DEFAULT NULL,
  `scope` varchar(20) DEFAULT NULL,
  `towers` int(2) DEFAULT NULL,
  `level` varchar(10) DEFAULT NULL,
  `runway` int(10) DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_airport
-- ----------------------------

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
  `flight` varchar(20) DEFAULT NULL,
  `ontime` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_airway
-- ----------------------------
INSERT INTO `t_airway` VALUES ('1', '11', '11', '直飞', '', '111', null, '1', null);
INSERT INTO `t_airway` VALUES ('2', '11', '11', '', '', '111', '', '1', null);

-- ----------------------------
-- Table structure for `t_bus`
-- ----------------------------
DROP TABLE IF EXISTS `t_bus`;
CREATE TABLE `t_bus` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `bus` varchar(10) DEFAULT NULL,
  `airport` varchar(10) DEFAULT NULL,
  `startpoint` varchar(10) DEFAULT NULL,
  `endpoint` varchar(10) DEFAULT NULL,
  `times` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bus
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
  `airways` int(10) DEFAULT NULL,
  `scope` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_company
-- ----------------------------
INSERT INTO `t_company` VALUES ('1', '2', '2', '2', '2', '2');

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
  `tourist` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_flight
-- ----------------------------
INSERT INTO `t_flight` VALUES ('3', '1212', '空客A380', '12', '12', '12', '12');

-- ----------------------------
-- Table structure for `t_hotel`
-- ----------------------------
DROP TABLE IF EXISTS `t_hotel`;
CREATE TABLE `t_hotel` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `hotel` varchar(10) DEFAULT NULL,
  `rooms` int(10) DEFAULT NULL,
  `stars` varchar(10) DEFAULT NULL,
  `rest` varchar(10) DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_hotel
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
  `flight` varchar(20) DEFAULT NULL,
  `classtype` varchar(10) DEFAULT NULL COMMENT '舱位',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_price
-- ----------------------------
INSERT INTO `t_price` VALUES ('2', '1.000', '1', '11.000', '11', '11');

-- ----------------------------
-- Table structure for `t_status`
-- ----------------------------
DROP TABLE IF EXISTS `t_status`;
CREATE TABLE `t_status` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `flight` varchar(20) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `pretime` varchar(20) DEFAULT NULL,
  `reason` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_status
-- ----------------------------
INSERT INTO `t_status` VALUES ('3', '1', '延误起飞', '1', '1');
INSERT INTO `t_status` VALUES ('4', '1', '1', null, null);
INSERT INTO `t_status` VALUES ('5', '1', '1', null, null);

-- ----------------------------
-- Table structure for `t_ticket`
-- ----------------------------
DROP TABLE IF EXISTS `t_ticket`;
CREATE TABLE `t_ticket` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `flight` varchar(20) DEFAULT NULL,
  `ordercode` varchar(20) DEFAULT NULL,
  `ticketdate` varchar(20) DEFAULT NULL,
  `price` varchar(10) DEFAULT NULL,
  `status` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_ticket
-- ----------------------------
INSERT INTO `t_ticket` VALUES ('3', '1', '11', '1111', '11', '111');

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

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `email` varchar(20) DEFAULT NULL,
  `position` varchar(20) DEFAULT NULL,
  `pwd` varchar(10) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `sex` varchar(5) DEFAULT NULL,
  `telphone` varchar(20) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `userno` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '222', '222', null, '222', '0', '222', '222', '22');
