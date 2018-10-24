/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : seckill

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2018-09-30 16:10:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `commodity_model` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `commodity_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `commodity_num` int(11) DEFAULT NULL,
  `commodity_unit` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `commodity_unit_price` decimal(10,2) DEFAULT NULL,
  `commodity_brand` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `commodity_place` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `commodity_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `commodity_net_weight` decimal(10,0) DEFAULT NULL,
  `commodity_gross_weight` decimal(10,0) DEFAULT NULL,
  `commodity_box_num` int(11) DEFAULT NULL,
  `commodity_po` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `commodity_batch_num` int(11) DEFAULT NULL,
  `commodity_receive_address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `commodity_logistics_dealer` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('ES636A', '集成电路1', '400', 'USD', '3.29', 'Cyrustek ', '台湾', '2018-001', '98', '100', '6', 'POO', '9001', '深圳南山', '华富洋');
INSERT INTO `commodity` VALUES ('ES51997A', '集成电路2', '400', 'USD', '3.29', 'Cyrustek ', '台湾', '2018-001', '98', '100', '6', 'POO', '9001', '深圳南山', '华富洋');
INSERT INTO `commodity` VALUES ('ES51920A', '集成电路3', '400', 'USD', '3.29', 'Cyrustek ', '台湾', '2018-001', '98', '100', '6', 'POO', '9001', '深圳南山', '华富洋');
INSERT INTO `commodity` VALUES ('ES51919Q', '集成电路4', '400', 'USD', '3.29', 'Cyrustek ', '台湾', '2018-001', '98', '100', '6', 'POO', '9001', '深圳南山', '华富洋');
