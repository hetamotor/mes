/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : mes

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 01/08/2021 03:13:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_inf
-- ----------------------------
DROP TABLE IF EXISTS `user_inf`;
CREATE TABLE `user_inf`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `loginname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `estatus` int NOT NULL DEFAULT 1,
  `userrole` int NULL DEFAULT NULL,
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_inf
-- ----------------------------
INSERT INTO `user_inf` VALUES (1, 'admin', '123456', 1, 3, '2021-07-31 17:07:30', '管理员');
INSERT INTO `user_inf` VALUES (4, 'leoli', 'sadf', 1, 2, '2021-07-31 17:44:49', 'Leo Li');
INSERT INTO `user_inf` VALUES (5, 'olivia', 'olivia', 1, 2, '2021-07-31 17:07:34', 'Olivia Wang');
INSERT INTO `user_inf` VALUES (7, 'jack', '147369', 1, 1, '2021-07-31 17:07:35', '漆吉');
INSERT INTO `user_inf` VALUES (8, 'zhangfei', 'zhang3', 0, 0, '2021-07-31 18:06:28', '张飞');
INSERT INTO `user_inf` VALUES (9, 'liubei', 'liubei', 1, 0, '2021-07-31 18:06:03', '刘备');
INSERT INTO `user_inf` VALUES (10, 'huanggai', 'huang9', 1, 0, '2021-07-31 18:05:12', '黄盖');
INSERT INTO `user_inf` VALUES (11, 'lisi', 'lisisi', 1, 0, '2021-07-31 17:52:52', '李四');
INSERT INTO `user_inf` VALUES (12, 'wangwu', 'wangwu', 0, 0, '2021-07-31 17:53:45', '王五');
INSERT INTO `user_inf` VALUES (13, 'fdafdas', 'fdasfdas', 1, 0, '2021-07-31 17:39:40', 'fdafdas');
INSERT INTO `user_inf` VALUES (14, 'fdafdas', 'fdafdsa', 1, 0, '2021-07-31 17:42:41', 'fda fda');
INSERT INTO `user_inf` VALUES (15, 'zhouyu', 'zhouyu', 1, 0, '2021-07-31 18:03:49', '周瑜');

SET FOREIGN_KEY_CHECKS = 1;
