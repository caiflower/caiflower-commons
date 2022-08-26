/*
 Navicat MySQL Data Transfer

 Source Server         : mysql--root
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : 47.100.229.145:3306
 Source Schema         : auth_center

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 22/08/2022 15:28:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `account` varchar(31) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户密码',
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(31) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '头像',
  `role` tinyint(4) NOT NULL COMMENT '权限[0: 超级管理员 1:管理员 2:普通用户]',
  `nick_name` varchar(31) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '大小',
  `sex` tinyint(4) NULL DEFAULT NULL COMMENT '性别',
  `age` tinyint(4) NULL DEFAULT NULL COMMENT '年龄',
  `real_name` varchar(31) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '真实姓名',
  `id_number` varchar(31) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份号码',
  `country` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所在国家',
  `region` varchar(31) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所在地区',
  `address` varchar(31) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '家庭住址',
  `yn` tinyint(4) NULL DEFAULT 1 COMMENT '逻辑删除',
  `enable` tinyint(4) NULL DEFAULT 1 COMMENT '启用[0:禁用 1:启用]',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `account_unique`(`account`) USING BTREE COMMENT '账号唯一',
  UNIQUE INDEX `phone`(`phone`) USING BTREE COMMENT '手机唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '系统用户信息表' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
