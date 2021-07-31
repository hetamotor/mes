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

 Date: 01/08/2021 03:12:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for notice_inf
-- ----------------------------
DROP TABLE IF EXISTS `notice_inf`;
CREATE TABLE `notice_inf`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pcs` int NULL DEFAULT NULL,
  `workorder` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `feedtime` datetime NULL DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_notice_user`(`user_id`) USING BTREE,
  CONSTRAINT `notice_inf_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_inf` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice_inf
-- ----------------------------
INSERT INTO `notice_inf` VALUES (13, '分流器', 'VF2162402', 180, 'W21070011', '2021-07-07 19:24:10', '2021-07-31 19:26:31', 1);
INSERT INTO `notice_inf` VALUES (14, '分流器', 'VF2162402', 180, 'W21070011', '2021-07-21 19:24:13', '2021-07-31 19:24:17', 1);
INSERT INTO `notice_inf` VALUES (15, '分流器', 'fdafdas', NULL, 'W21070011', '2021-07-31 15:54:51', '2021-07-31 19:26:26', 1);
INSERT INTO `notice_inf` VALUES (16, 'NTC', '不知道啊不知道', 540, 'W21070011', '2021-07-31 15:55:24', '2021-07-31 19:32:11', 1);
INSERT INTO `notice_inf` VALUES (17, '外壳', 'dafdafdafda', NULL, NULL, '2021-07-06 19:24:05', '2021-07-31 19:24:09', 8);
INSERT INTO `notice_inf` VALUES (18, '分流器', 'fdasfdasfdas', NULL, NULL, '2021-07-31 17:34:10', '2021-07-31 19:23:47', 1);
INSERT INTO `notice_inf` VALUES (19, '分流器', 'fdafdasfda', 540, 'W21070011', '2021-07-01 19:23:51', '2021-07-31 19:46:08', 7);
INSERT INTO `notice_inf` VALUES (22, '外壳', '法发大水发', 54254, 'W1354644', '2021-07-31 18:29:58', '2021-07-31 19:46:26', 1);

SET FOREIGN_KEY_CHECKS = 1;
