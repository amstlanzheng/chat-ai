/*
 Navicat Premium Data Transfer

 Source Server         : gawaine
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : rm-uf636ul5p0240qhhkmo.mysql.rds.aliyuncs.com:3306
 Source Schema         : chat_ai

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 14/09/2024 16:48:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for message_log
-- ----------------------------
DROP TABLE IF EXISTS `message_log`;
CREATE TABLE `message_log`  (
  `id` bigint NOT NULL COMMENT '唯一标识',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '内容',
  `uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用唯一识别码',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `is_hidden` tinyint NULL DEFAULT 0 COMMENT '是否隐藏，0否，1是',
  `create_time` bigint NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建人',
  `update_time` bigint NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint NULL DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除标志，0表示未删除，1表示已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '消息日志表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
