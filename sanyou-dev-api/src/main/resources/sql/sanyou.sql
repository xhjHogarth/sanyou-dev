/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50729
Source Host           : localhost:3306
Source Database       : sanyou

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2021-06-01 15:24:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for factory
-- ----------------------------
DROP TABLE IF EXISTS `factory`;
CREATE TABLE `factory` (
  `id` varchar(20) NOT NULL,
  `factory_name` varchar(100) NOT NULL,
  `delete_mark` tinyint(4) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `deletetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of factory
-- ----------------------------
INSERT INTO `factory` VALUES ('2105261HWC2S51P0', '金冠铜业', '0', '2021-05-26 02:14:44', '2021-05-26 02:14:44', null);
INSERT INTO `factory` VALUES ('2105268394P0Y8M8', '杭州铜业', '0', '2021-05-26 11:22:33', '2021-05-26 11:22:33', null);

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` varchar(20) NOT NULL,
  `res_code` varchar(255) NOT NULL,
  `title` varchar(100) NOT NULL,
  `layer` tinyint(4) DEFAULT NULL,
  `parent_id` varchar(64) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `deletetime` datetime DEFAULT NULL,
  `delete_mark` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('210526ANMT2MPZ2W', 'user_manage', '用户管理', '0', null, '2021-05-26 14:59:46', '2021-05-31 16:38:54', '2021-05-31 16:38:54', '0');
INSERT INTO `resource` VALUES ('210526B60RS7DSY8', 'user_manage_add', '添加用户', '2', '210526B8X5B5RXGC', '2021-05-26 15:42:52', '2021-05-31 16:39:03', '2021-05-31 16:39:03', '0');
INSERT INTO `resource` VALUES ('210526B63DGKD028', 'user_manage_delete', '删除用户', '2', '210526B8X5B5RXGC', '2021-05-26 15:43:10', '2021-05-31 16:39:03', '2021-05-31 16:39:03', '0');
INSERT INTO `resource` VALUES ('210526B664G1846W', 'user_manage_edit', '编辑用户', '2', '210526B8X5B5RXGC', '2021-05-26 15:43:27', '2021-05-31 16:39:03', '2021-05-31 16:39:03', '0');
INSERT INTO `resource` VALUES ('210526B6TWADCKKP', 'user_manage_enable', '启用/禁用用户', '2', '210526B8X5B5RXGC', '2021-05-26 15:45:21', '2021-05-31 16:39:03', '2021-05-31 16:39:03', '0');
INSERT INTO `resource` VALUES ('210526B6ZZ4F0YRP', 'user_manage_search', '查看用户', '2', '210526B8X5B5RXGC', '2021-05-26 15:45:47', '2021-05-31 16:39:03', '2021-05-31 16:39:03', '0');
INSERT INTO `resource` VALUES ('210526B8X5B5RXGC', 'user_list', '用户列表', '1', '210526ANMT2MPZ2W', '2021-05-26 15:51:30', '2021-05-31 16:39:03', '2021-05-31 16:39:03', '0');
INSERT INTO `resource` VALUES ('210526BA6WG208DP', 'user_manage_download', '用户资料下载', '2', '210526B8X5B5RXGC', '2021-05-26 15:55:32', '2021-05-31 16:39:03', '2021-05-31 16:39:03', '0');
INSERT INTO `resource` VALUES ('210526BAS9F27XAW', 'user_manage_auth', '用户设备权限', '2', '210526B8X5B5RXGC', '2021-05-26 15:57:11', '2021-05-31 16:39:03', '2021-05-31 16:39:03', '0');
INSERT INTO `resource` VALUES ('210526BBP4P7BMW0', 'factory_manage', '厂家管理', '1', '210526ANMT2MPZ2W', '2021-05-26 15:59:58', '2021-05-31 16:39:03', '2021-05-31 16:39:03', '0');
INSERT INTO `resource` VALUES ('210526BC256DHPH0', 'factory_manage_add', '添加厂家', '2', '210526BBP4P7BMW0', '2021-05-26 16:01:02', '2021-05-31 16:39:03', '2021-05-31 16:39:03', '0');
INSERT INTO `resource` VALUES ('210526BCD1SGM5P0', 'factory_manage_edit', '编辑厂家', '2', '210526BBP4P7BMW0', '2021-05-26 16:02:12', '2021-05-26 17:22:53', null, '0');
INSERT INTO `resource` VALUES ('210526BCG87BBB0H', 'factory_manage_delete', '删除厂家', '2', '210526BBP4P7BMW0', '2021-05-26 16:02:26', '2021-05-26 16:02:26', null, '0');
INSERT INTO `resource` VALUES ('210526BCPSDXGK68', 'factory_manage_search', '查看厂家', '2', '210526BBP4P7BMW0', '2021-05-26 16:03:02', '2021-05-26 16:03:02', null, '0');
INSERT INTO `resource` VALUES ('210526CGH887KT7C', 'auth_manage', '权限管理', '0', null, '2021-05-26 17:35:38', '2021-05-26 17:35:38', null, '0');
INSERT INTO `resource` VALUES ('210526CK50NMBDYW', 'role_manage', '角色管理', '1', '210526CGH887KT7C', '2021-05-26 17:40:25', '2021-05-26 17:40:25', null, '0');
INSERT INTO `resource` VALUES ('210526CNA7NZZKGC', 'resource_manage', '资源管理', '1', '210526CGH887KT7C', '2021-05-26 17:46:59', '2021-05-26 17:46:59', null, '0');
INSERT INTO `resource` VALUES ('210526CNK9N3TSCH', 'resource_manage_add', '添加资源', '2', '210526CNA7NZZKGC', '2021-05-26 17:47:45', '2021-05-26 17:47:45', null, '0');
INSERT INTO `resource` VALUES ('210526CNZNP9FT0H', 'resource_manage_edit', '更新资源', '2', '210526CNA7NZZKGC', '2021-05-26 17:48:52', '2021-05-26 17:48:52', null, '0');
INSERT INTO `resource` VALUES ('210526CP2M93K4ZC', 'resource_manage_delete', '删除资源', '2', '210526CNA7NZZKGC', '2021-05-26 17:49:10', '2021-05-26 17:49:10', null, '0');
INSERT INTO `resource` VALUES ('210526FB6N5D9Z54', 'role_manage_add', '添加角色', '2', '210526CK50NMBDYW', '2021-05-26 20:10:43', '2021-05-26 20:10:43', null, '0');
INSERT INTO `resource` VALUES ('210526FBMBTWXZF8', 'role_manage_edit', '更新角色', '2', '210526CK50NMBDYW', '2021-05-26 20:11:59', '2021-05-26 20:11:59', null, '0');
INSERT INTO `resource` VALUES ('210526FBRX6DW5GC', 'role_manage_delete', '删除角色', '2', '210526CK50NMBDYW', '2021-05-26 20:12:21', '2021-05-26 20:12:21', null, '0');
INSERT INTO `resource` VALUES ('210526FC6R5M3YA8', 'role_manage_auth', '角色分配资源', '2', '210526CK50NMBDYW', '2021-05-26 20:13:44', '2021-05-26 20:13:44', null, '0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(20) NOT NULL,
  `role_code` varchar(50) NOT NULL,
  `title` varchar(50) NOT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `deletetime` datetime DEFAULT NULL,
  `delete_mark` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('210526G6A5T5WNXP', 'client', '客户', '2021-05-26 21:20:10', '2021-05-31 19:32:32', null, '0');
INSERT INTO `role` VALUES ('210531APA38M4T54', 'test', '测试', '2021-05-31 15:01:50', '2021-05-31 19:32:32', null, '0');

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `id` varchar(20) NOT NULL,
  `roleId` varchar(20) NOT NULL,
  `resourceId` varchar(20) NOT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES ('2105270YH5SYS51P', '210526G6A5T5WNXP', '210526FB6N5D9Z54', '2021-05-27 01:19:48');
INSERT INTO `role_resource` VALUES ('2105270YH5W4XFCH', '210526G6A5T5WNXP', '210526FBMBTWXZF8', '2021-05-27 01:19:48');
INSERT INTO `role_resource` VALUES ('2105270YH5WBPH94', '210526G6A5T5WNXP', '210526FBRX6DW5GC', '2021-05-27 01:19:48');
INSERT INTO `role_resource` VALUES ('2105270YH5WMGM5P', '210526G6A5T5WNXP', '210526FC6R5M3YA8', '2021-05-27 01:19:48');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `realname` varchar(50) DEFAULT NULL,
  `sex` tinyint(4) DEFAULT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `qq` varchar(50) DEFAULT NULL,
  `wechat` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `province` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `area` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `enable_mark` tinyint(4) DEFAULT '1',
  `delete_mark` tinyint(4) DEFAULT '0',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `deletetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2105260FBK56RZTC', 'xhj', '4QrcOUm6Wau+VuBX8g+IPg==', null, '0', '21312', null, null, null, null, null, null, null, '1', '0', '2021-05-26 00:43:17', null, null);
SET FOREIGN_KEY_CHECKS=1;
