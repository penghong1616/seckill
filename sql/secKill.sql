/*
 Navicat Premium Data Transfer

 Source Server         : CentOS
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 62.234.148.80:3306
 Source Schema         : secKill

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 29/08/2019 20:23:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sk_goods
-- ----------------------------
DROP TABLE IF EXISTS `sk_goods`;
CREATE TABLE `sk_goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `goods_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `goods_title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品标题',
  `goods_img` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `goods_detail` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品详情',
  `goods_price` decimal(10, 2) NULL DEFAULT NULL,
  `goods_stock` int(11) NULL DEFAULT 0 COMMENT '商品库存，-1表示没有限制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sk_goods
-- ----------------------------
INSERT INTO `sk_goods` VALUES (1, 'iphoneX', 'Apple/苹果iPhone X 全网通4G手机苹果X 10', '/img/iphonex.png', 'Apple/苹果iPhone X 全网通4G手机苹果X 10', 7788.00, 100);
INSERT INTO `sk_goods` VALUES (2, '华为 Mate 10', 'Huawei/华为 Mate 10 6G+128G 全网通4G智能手机', '/img/meta10.png', 'Huawei/华为 Mate 10 6G+128G 全网通4G智能手机', 4199.00, 50);
INSERT INTO `sk_goods` VALUES (3, '小米', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sk_goods` VALUES (4, 'oppo', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sk_goods` VALUES (5, 'vivo', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sk_goods` VALUES (6, '联想', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sk_goods` VALUES (7, '三星', NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for sk_goods_seckill
-- ----------------------------
DROP TABLE IF EXISTS `sk_goods_seckill`;
CREATE TABLE `sk_goods_seckill`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀商品id',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品id',
  `seckill_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '秒杀价',
  `stock_count` int(11) NULL DEFAULT NULL COMMENT '库存数量',
  `start_date` datetime(0) NULL DEFAULT NULL COMMENT '秒杀开始时间',
  `end_date` datetime(0) NULL DEFAULT NULL COMMENT '秒杀结束时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sk_goods_seckill
-- ----------------------------
INSERT INTO `sk_goods_seckill` VALUES (1, 1, 0.01, 7, '2019-08-22 17:22:52', '2019-11-22 18:23:00', 0);
INSERT INTO `sk_goods_seckill` VALUES (2, 2, 0.01, 5, '2019-07-29 22:56:10', '2019-11-01 22:56:15', 0);
INSERT INTO `sk_goods_seckill` VALUES (3, 3, 0.00, NULL, NULL, NULL, NULL);
INSERT INTO `sk_goods_seckill` VALUES (4, 4, 0.00, NULL, NULL, NULL, NULL);
INSERT INTO `sk_goods_seckill` VALUES (5, 5, 0.00, NULL, NULL, NULL, NULL);
INSERT INTO `sk_goods_seckill` VALUES (6, 6, 0.00, NULL, NULL, NULL, NULL);
INSERT INTO `sk_goods_seckill` VALUES (7, 7, 0.00, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sk_order
-- ----------------------------
DROP TABLE IF EXISTS `sk_order`;
CREATE TABLE `sk_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `order_id` bigint(20) NULL DEFAULT NULL,
  `goods_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `u_uid_gid`(`user_id`, `goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sk_order
-- ----------------------------
INSERT INTO `sk_order` VALUES (14, 17729855856, 14, 2);

-- ----------------------------
-- Table structure for sk_order_info
-- ----------------------------
DROP TABLE IF EXISTS `sk_order_info`;
CREATE TABLE `sk_order_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `goods_id` bigint(20) NULL DEFAULT NULL,
  `delivery_addr_id` bigint(20) NULL DEFAULT NULL,
  `goods_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_count` int(11) NULL DEFAULT NULL,
  `goods_price` decimal(10, 2) NULL DEFAULT NULL,
  `order_channel` tinyint(4) NULL DEFAULT NULL COMMENT '订单渠道，1在线，2android，3ios',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成',
  `create_date` datetime(0) NULL DEFAULT NULL,
  `pay_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sk_order_info
-- ----------------------------
INSERT INTO `sk_order_info` VALUES (14, 17729855856, 2, NULL, '华为 Mate 10', 1, 0.01, 1, 0, '2019-08-29 10:58:04', NULL);

-- ----------------------------
-- Table structure for sk_user
-- ----------------------------
DROP TABLE IF EXISTS `sk_user`;
CREATE TABLE `sk_user`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'MD5(MD5(pass明文+固定salt)+salt',
  `salt` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '混淆盐',
  `head` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像，云存储的ID',
  `register_date` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `last_login_date` datetime(0) NULL DEFAULT NULL COMMENT '上次登录时间',
  `login_count` int(11) NULL DEFAULT NULL COMMENT '登录次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sk_user
-- ----------------------------
INSERT INTO `sk_user` VALUES (17729855856, 'ph', '1552c3e10b0b3f64407df1a6440bad51', '1a2b3c4d', NULL, '2019-08-29 09:01:02', '2019-08-29 09:01:06', 1);

SET FOREIGN_KEY_CHECKS = 1;
