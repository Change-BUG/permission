/*
 Navicat Premium Data Transfer

 Source Server         : 本地虚拟机_公司
 Source Server Type    : MySQL
 Source Server Version : 80022 (8.0.22)
 Source Host           : 192.168.10.8:3306
 Source Schema         : permission

 Target Server Type    : MySQL
 Target Server Version : 80022 (8.0.22)
 File Encoding         : 65001

 Date: 14/02/2023 11:18:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `_id` int NOT NULL AUTO_INCREMENT COMMENT '部门编号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '部门名称',
  `introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '部门描述',
  `permission` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '部门权限',
  `status` int NOT NULL COMMENT '部门状态',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `upd_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门';

-- ----------------------------
-- Records of sys_department
-- ----------------------------
BEGIN;
INSERT INTO `sys_department` (`_id`, `name`, `introduction`, `permission`, `status`, `add_time`, `upd_time`) VALUES (1, '系统管理员', '管理系统', 'admin', 1, '2023-02-13 17:54:40', '2023-02-13 17:54:42');
INSERT INTO `sys_department` (`_id`, `name`, `introduction`, `permission`, `status`, `add_time`, `upd_time`) VALUES (2, '普通用户', '普通用户', 'admin.user', 1, '2023-02-13 17:54:44', '2023-02-13 17:54:47');
INSERT INTO `sys_department` (`_id`, `name`, `introduction`, `permission`, `status`, `add_time`, `upd_time`) VALUES (3, 'abc', 'abcd001', 'admin.abc', 3, '2023-02-13 13:27:34', '2023-02-13 13:34:06');
COMMIT;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `_id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `introduction` varchar(50) NOT NULL COMMENT '描述',
  `status` int NOT NULL COMMENT '状态',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `upd_time` datetime NOT NULL COMMENT '修改时间',
  `name` varchar(50) NOT NULL COMMENT '接口名称',
  `url` text NOT NULL COMMENT '接口URL',
  `permission` varchar(50) NOT NULL COMMENT '接口权限',
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='接口';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
BEGIN;
INSERT INTO `sys_resource` (`_id`, `introduction`, `status`, `add_time`, `upd_time`, `name`, `url`, `permission`) VALUES (1, '关于登录接口', 1, '2023-02-13 22:35:30', '2023-02-13 22:35:31', '登录', '/api/v1/sysUser/login', 'admin.user');
INSERT INTO `sys_resource` (`_id`, `introduction`, `status`, `add_time`, `upd_time`, `name`, `url`, `permission`) VALUES (2, '关于查询个人信息', 1, '2023-02-13 23:02:15', '2023-02-13 23:02:14', '查询个人信息', '/api/v1/sysUser/getUser', 'admin');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `_id` int NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `avatar` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户头像',
  `account` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户账号',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
  `introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '用户描述',
  `department` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户部门',
  `status` int NOT NULL COMMENT '用户状态 1正常 2封禁 3删除',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`_id`, `avatar`, `account`, `password`, `introduction`, `department`, `status`, `add_time`, `upd_time`) VALUES (1, '123.png', 'admin', '$2a$10$ccnvGMFxE3tjzEpuZBC2B.7Z7aICXXLohpK7Lk8LCVQco/O51d9Ra', '管理员用户', '管理员', 1, '2023-02-13 17:27:21', '2023-02-13 17:27:23');
INSERT INTO `sys_user` (`_id`, `avatar`, `account`, `password`, `introduction`, `department`, `status`, `add_time`, `upd_time`) VALUES (6, '123.png', 'abc001', '$2a$10$rjI.aYkODCojv2AKWWjLV.yeHna6j2kxZrhJABW1GDqf5/L8aGnn2', '你好用户！！！', '普通用户', 2, '2023-02-13 17:27:26', '2023-02-13 17:27:27');
INSERT INTO `sys_user` (`_id`, `avatar`, `account`, `password`, `introduction`, `department`, `status`, `add_time`, `upd_time`) VALUES (7, '456.png', 'abc002', '$2a$10$qXTwZHexbpZ9s6IGwKjNA.kx55qFTWExGG/5CjuWIJZWxdMHuGUka', '你好用户！！！', '普通用户', 1, '2023-02-13 14:06:40', '2023-02-13 14:06:40');
INSERT INTO `sys_user` (`_id`, `avatar`, `account`, `password`, `introduction`, `department`, `status`, `add_time`, `upd_time`) VALUES (12, '123.png', 'abc003', '$2a$10$lteovVMK1/LrdxXVT03.r.rOW4gEUk0eOCrRz3u9r9F56vSzOrYle', '你好用户！！！', '普通用户', 1, '2023-02-13 17:27:32', '2023-02-13 14:19:11');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
